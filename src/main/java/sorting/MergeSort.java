package sorting;

import java.util.LinkedList;

public class MergeSort {

  public static <T extends Comparable<? super T>> LinkedList<T> mergesort(LinkedList<T> elements) {
    assert elements.size() > 0;
    int middle = elements.size() / 2;

    return mergesort(
        new LinkedList<>(elements.subList(0, middle)),
        new LinkedList<>(elements.subList(middle, elements.size())));
  }

  private static <T extends Comparable<? super T>> LinkedList<T> mergesort(
      LinkedList<T> left, LinkedList<T> right) {
    if (left.size() > 1) {
      left = mergesort(left);
    }

    if (right.size() > 1) {
      right = mergesort(right);
    }
    return merge(left, right);
  }

  public static <T extends Comparable<? super T>> LinkedList<T> merge(
      LinkedList<T> left, LinkedList<T> right) {

    LinkedList<T> out = new LinkedList<>();

    while (!left.isEmpty() && !right.isEmpty()) {
      if (left.getFirst().compareTo(right.getFirst()) < 0) {
        out.add(left.removeFirst());
      } else {
        out.add(right.removeFirst());
      }
    }
    out.addAll(left);
    out.addAll(right);
    return out;
  }
}
