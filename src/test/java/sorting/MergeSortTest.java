package sorting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class MergeSortTest {

  private static final List<Integer> descendingList = List.of(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
  private static final List<Integer> ascendingList = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
  private static final List<Integer> arbitraryList =
      List.of(3, 2, 1, 2, 2, 4, 5, 6, 3, 8, 8, 6, 7, 4, 6, 9);

  static Stream<List<Integer>> listProvider() {
    return Stream.of(descendingList, ascendingList, arbitraryList);
  }

  @ParameterizedTest
  @MethodSource("listProvider")
  void test(List<Integer> elements) {
    assertEquals(
        elements.stream().sorted().collect(Collectors.toList()),
        MergeSort.mergesort(new LinkedList<>(elements)));
  }
}
