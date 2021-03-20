package sorting;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.OptionalInt;

/**
 * Limited implementation of a heap data structure
 *
 * <p>Known limitation are that the max ever size of the heap is the size of the initializing
 * collection, and the heap cannot be initialized with an empty collection.
 *
 * @param <T> element type
 */
public final class Heap<T extends Comparable<? super T>> {

  private final boolean minHeap;

  // zeroth element is always ignored for easy position calculation
  private final T[] heap;
  private int end = 0;

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
    this.heap = heap;
    elements.forEach(this::insert);
  }

  public Heap<T> insert(T element) {
    assert end < heap.length;
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

    int comparison = minHeap ? element.compareTo(parent) : -element.compareTo(parent);
    if (comparison < 0) {
      heap[position] = parent;
      heap[parentPosition] = element;
      bubbleUp(parentPosition);
    }
  }

  public T head() {
    return heap[1];
  }

  public T pop() {
    T out = heap[1];
    heap[1] = heap[end];
    end -= 1;
    bubbleDown(1);
    return out;
  }

  private void bubbleDown(int position) {
    OptionalInt dominantChildPosition = dominantChildPosition(position);
    if (dominantChildPosition.isEmpty()) {
      return;
    }
    int childPosition = dominantChildPosition.getAsInt();
    T element = heap[position];
    int comparison =
        minHeap ? element.compareTo(heap[childPosition]) : -element.compareTo(heap[childPosition]);
    if (comparison > 0) {
      heap[position] = heap[childPosition];
      heap[childPosition] = element;
      bubbleDown(childPosition);
    }
  }

  private OptionalInt dominantChildPosition(int position) {
    int leftChildPosition = position * 2;
    if (leftChildPosition > end) {
      return OptionalInt.empty();
    }

    int rightChildPosition = leftChildPosition + 1;
    if (rightChildPosition > end) {
      return OptionalInt.of(leftChildPosition);
    }

    int comparison = heap[leftChildPosition].compareTo(heap[rightChildPosition]);
    if (minHeap) {
      comparison *= -1;
    }
    if (comparison > 0) {
      return OptionalInt.of(leftChildPosition);
    } else {
      return OptionalInt.of(rightChildPosition);
    }
  }

  public int size() {
    return end;
  }
}
