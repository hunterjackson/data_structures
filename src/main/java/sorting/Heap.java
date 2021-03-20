package sorting;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

/**
 * Limited implementation of a heap data structure
 *
 * <p>Known limitation are that the heap cannot be initialized with an empty collection.
 *
 * @param <T> element type
 */
public final class Heap<T extends Comparable<? super T>> {

  private final boolean minHeap;

  // zeroth element is always ignored for easy position calculation
  private T[] heap;
  private int end;

  public static <T extends Comparable<? super T>> Heap<T> minHeap(Collection<T> elements) {
    return new Heap<>(true, elements);
  }

  public static <T extends Comparable<? super T>> Heap<T> maxHeap(Collection<T> elements) {
    return new Heap<>(false, elements);
  }

  private Heap(boolean minHeap, Collection<T> elements) {
    assert elements.size() > 0;
    this.minHeap = minHeap;
    @SuppressWarnings("unchecked")
    T[] heap = (T[]) Array.newInstance(elements.iterator().next().getClass(), elements.size() + 1);

    int pos = 1;
    for (T element : elements) {
      heap[pos] = element;
      pos++;
    }
    this.heap = heap;

    // shortcut for array initialization
    // the last half the heap array are all leaf nodes with no children so they already dominate
    // their non-existent children
    end = heap.length - 1;
    for (int i = end; i > 0; i--) {
      bubbleDown(i);
    }
  }

  public Heap<T> insert(T element) {
    if (end == heap.length - 1) { // double the size of the internal heap array if needed
      this.heap = Arrays.copyOf(this.heap, this.heap.length * 2);
    }
    end += 1;
    heap[end] = element;
    bubbleUp(end);
    return this;
  }

  private void bubbleUp(int position) {
    int parentPosition = position / 2;
    if (parentPosition <= 0) {
      return;
    }

    T element = heap[position];
    T parent = heap[parentPosition];

    int comparison = comparePositions(position, parentPosition);
    if (comparison < 0) {
      heap[position] = parent;
      heap[parentPosition] = element;
      bubbleUp(parentPosition);
    }
  }

  /** @return the value at the current head of the heap */
  public T head() {
    return heap[1];
  }

  /** @return and remove the current head of the heap */
  public T pop() {
    T out = heap[1];
    heap[1] = heap[end];
    end -= 1;
    bubbleDown(1);
    return out;
  }

  private void bubbleDown(int position) {
    int dominantChildPosition = dominantChildPosition(position);
    if (dominantChildPosition <= 0) {
      return;
    }
    T element = heap[position];
    int comparison = comparePositions(position, dominantChildPosition);
    if (comparison > 0) {
      heap[position] = heap[dominantChildPosition];
      heap[dominantChildPosition] = element;
      bubbleDown(dominantChildPosition);
    }
  }

  private int dominantChildPosition(int position) {
    int leftChildPosition = position * 2;
    if (leftChildPosition > end) {
      return -1;
    }

    int rightChildPosition = leftChildPosition + 1;
    if (rightChildPosition > end) {
      return leftChildPosition;
    }

    int comparison = comparePositions(leftChildPosition, rightChildPosition);
    if (comparison < 0) {
      return leftChildPosition;
    } else {
      return rightChildPosition;
    }
  }

  private int comparePositions(int firstPosition, int secondPosition) {
    return minHeap
        ? heap[firstPosition].compareTo(heap[secondPosition])
        : -heap[firstPosition].compareTo(heap[secondPosition]);
  }

  public int size() {
    return end;
  }
}
