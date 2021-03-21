package sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class QuickSortTest {

  static Stream<List<Integer>> listProvider() {
    return Stream.of(HeapTest.descendingList, HeapTest.ascendingList, HeapTest.arbitraryList);
  }

  @ParameterizedTest
  @MethodSource("listProvider")
  void test(List<Integer> elements) {
    Integer[] underTest = elements.toArray(new Integer[0]);
    Integer[] expected = elements.stream().sorted().toList().toArray(new Integer[0]);
    QuickSort.quicksort(underTest);
    assertArrayEquals(expected, underTest);
  }
}
