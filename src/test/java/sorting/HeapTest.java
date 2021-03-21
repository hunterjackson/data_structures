package sorting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class HeapTest {

  static final List<Integer> descendingList = List.of(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
  static final List<Integer> ascendingList = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
  static final List<Integer> arbitraryList =
      List.of(3, 2, 1, 2, 2, 4, 5, 6, 3, 8, 8, 6, 7, 4, 6, 9);

  private static Stream<Collection<Integer>> listProvider() {
    return Stream.of(descendingList, ascendingList, arbitraryList);
  }

  @ParameterizedTest
  @MethodSource("listProvider")
  void getMin(Collection<Integer> elements) {
    Heap<Integer> heap = Heap.minHeap(elements);
    assertEquals(Collections.min(elements), heap.head());
  }

  @ParameterizedTest
  @MethodSource("listProvider")
  void getMax(Collection<Integer> elements) {
    Heap<Integer> heap = Heap.maxHeap(elements);
    assertEquals(Collections.max(elements), heap.head());
  }

  @ParameterizedTest
  @MethodSource("listProvider")
  void popMin(List<Integer> elements) {
    Heap<Integer> heap = Heap.minHeap(elements);
    List<Integer> fromHeap = new ArrayList<>(elements.size());
    while (heap.size() > 0) {
      fromHeap.add(heap.pop());
    }

    List<Integer> sortedElements = elements.stream().sorted().collect(Collectors.toList());
    assertEquals(sortedElements, fromHeap);
  }

  @ParameterizedTest
  @MethodSource("listProvider")
  void popMax(List<Integer> elements) {
    Heap<Integer> heap = Heap.maxHeap(elements);
    List<Integer> fromHeap = new ArrayList<>(elements.size());
    while (heap.size() > 0) {
      fromHeap.add(heap.pop());
    }

    List<Integer> sortedElements =
        elements.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    assertEquals(sortedElements, fromHeap);
  }

  @ParameterizedTest
  @MethodSource("listProvider")
  void insertMin(List<Integer> elements) {
    Heap<Integer> heap = Heap.minHeap(elements);
    heap.insert(Integer.MIN_VALUE).insert(Integer.MAX_VALUE);
    assertEquals(Integer.MIN_VALUE, heap.head());
  }

  @ParameterizedTest
  @MethodSource("listProvider")
  void insertMax(List<Integer> elements) {
    Heap<Integer> heap = Heap.maxHeap(elements);
    heap.insert(Integer.MIN_VALUE).insert(Integer.MAX_VALUE);
    assertEquals(Integer.MAX_VALUE, heap.head());
  }
}
