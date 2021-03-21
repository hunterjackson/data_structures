package sorting;

import java.util.Random;

public final class QuickSort {
  private static final Random randomGen = new Random();

  public static <T extends Comparable<? super T>> void quicksort(T[] elements) {
    quicksort(elements, 0, elements.length - 1);
  }

  public static <T extends Comparable<? super T>> T[] topKValues(T[] elements) {
    return elements;
  }

  private static <T extends Comparable<? super T>> void quicksort(T[] elements, int low, int high) {
    if (low < high) {
      int partition = partition(elements, low, high);
      quicksort(elements, low, partition - 1);
      quicksort(elements, partition + 1, high);
    }
  }

  /**
   * select a member of {@code elements} to be the partition, rearrange the array between {@code
   * low} and {@code high} so that elements from low - partition are strictly less than the value at
   * partition, elements from partition to high are greater than or equal to the value at partition
   * then return the partition index.
   */
  private static <T extends Comparable<? super T>> int partition(T[] elements, int low, int high) {
    int k = randomGen.nextInt(high + 1 - low) + low;
    swap(elements, k, high);
    int partition = high;
    int firstHigh = low;
    for (int i = low; i < high; i++) {
      if (elements[i].compareTo(elements[partition]) < 0) {
        swap(elements, i, firstHigh);
        firstHigh += 1;
      }
    }
    swap(elements, partition, firstHigh);
    return firstHigh;
  }

  private static <T> void swap(T[] array, int index1, int index2) {
    T tmp = array[index1];
    array[index1] = array[index2];
    array[index2] = tmp;
  }
}
