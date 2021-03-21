package sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class QuickSortTest {

  static Stream<List<Integer>> listProvider() {
    return Stream.of(HeapTest.descendingList, HeapTest.ascendingList, HeapTest.arbitraryList);
  }

  @ParameterizedTest
  @MethodSource("listProvider")
  void quicksort(List<Integer> elements) {
    Integer[] underTest = elements.toArray(new Integer[0]);
    Integer[] expected = elements.stream().sorted().toList().toArray(new Integer[0]);
    QuickSort.quicksort(underTest);
    assertArrayEquals(expected, underTest);
  }

  @ParameterizedTest
  @MethodSource("listProvider")
  void topK(List<Integer> elements) {
    int k = elements.size() / 2;
    Set<Integer> expected = elements.stream().sorted().limit(k).collect(Collectors.toSet());
    Integer[] result = QuickSort.topKValues(elements.toArray(new Integer[0]), k);
    assertEquals(expected, Arrays.stream(result).collect(Collectors.toSet()));
  }
}
