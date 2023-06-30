import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Rahul Sunkara
 * @version 1.0
 * @userid rsunkara3
 * @GTID 903401231
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */

public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Null array is being set as a comparator.");
        }
        for (int r = 1; r < arr.length; r++) {
            T dummy = arr[r];
            int s = r - 1;
            while (s >= 0 && comparator.compare(arr[s], dummy) > 0) {
                arr[s + 1] = arr[s--];
            }
            arr[s + 1] = dummy;
        }
    }

    /**
     * Implement cocktail sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Null array is being set as a comparator.");
        }
        boolean swap = true;
        int start = 0;
        int end = arr.length - 1;
        int nStart;
        int nEnd;
        while (swap) {
            nEnd = end;
            for (int i = start; i < end; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    swap = true;
                    nEnd = i;
                }
            }
            end = nEnd;
            if (swap) {
                swap = false;
                nStart = start;
                for (int i = end; i > start; i--) {
                    if (comparator.compare(arr[i - 1], arr[i]) > 0) {
                        swap(arr, i - 1, i);
                        swap = true;
                        nStart = i;
                    }
                }
                start = nStart;
            }
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Null array is being set as a comparator.");
        }
        if (arr.length > 1) {
            T[] l = (T[]) new Object[arr.length / 2];
            T[] r = (T[]) new Object[arr.length - l.length];
            for (int i = 0; i < l.length; i++) {
                l[i] = arr[i];
            }
            for (int j = 0; j < r.length; j++) {
                r[j] = arr[j + l.length];
            }
            mergeSort(l, comparator);
            mergeSort(r, comparator);
            merge(arr, comparator, l, r);
        }
    }

    /**
     * Merge helper method
     *
     * @param arr the array that needs to be sorted
     * @param comparator the Comparator used to compare the data
     * @param lArr the left array
     * @param rArr the right array
     * @param <T> data type for the sort
     */
    private static <T> void merge(T[] arr, Comparator<T> comparator, T[] lArr, T[] rArr) {
        int a = 0;
        int b = 0;
        for (int c = 0; c < arr.length; c++) {
            if (b >= rArr.length || (a < lArr.length && comparator.compare(lArr[a], rArr[b]) <= 0)) {
                arr[c] = lArr[a++];
            } else {
                arr[c] = rArr[b++];
            }
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("An Array can not be null");
        }
        LinkedList<Integer>[] buks = new LinkedList[19];
        for (int r = 0; r < 19; r++) {
            buks[r] = new LinkedList<>();
        }
        int mod = 10;
        int div = 1;
        boolean cont = true;
        while (cont) {
            cont = false;
            for (int num : arr) {
                int buk = num / div;
                if (buk / 10 != 0) {
                    cont = true;
                }
                if (buks[buk % mod + 9] == null) {
                    buks[buk % mod + 9] = new LinkedList<>();
                }
                buks[buk % mod + 9].add(num);
            }
            int arrInd = 0;
            for (LinkedList<Integer> buk : buks) {
                if (buk != null) {
                    for (int num : buk) {
                        arr[arrInd++] = num;
                    }
                    buk.clear();
                }
            }
            div *= 10;
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not receive
     * credit if you do not use the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @throws java.lang.IllegalArgumentException if the array or comparator or
     *                                            rand is null
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Null array is being set as a comparator.");
        }
        quickHelper(arr, 0, arr.length - 1, rand, comparator);
    }

    /**
     * Helper method for quick sort
     *
     * @param arr the array to be sorted
     * @param initial the starting index
     * @param last the last index
     * @param rand the random objecgt used to pick pivots
     * @param comparator the Comparator used to compare the data in arr
     * @param <T> the data type for the sort
     */
    private static <T> void quickHelper(T[] arr, int initial, int last,
                                        Random rand, Comparator<T> comparator) {
        if (last > initial) {
            int pivotIndex = partition(arr, initial, last, rand.nextInt(last - initial) + initial, comparator);
            quickHelper(arr, initial, pivotIndex - 1, rand, comparator);
            quickHelper(arr, pivotIndex + 1, last, rand, comparator);
        }
    }

    /**
     * Partitions for the quick sort
     *
     * @param arr the array to be sorted
     * @param initial the start
     * @param last the final
     * @param pivotIndex the pivot index
     * @param comparator the Comparator used to compare the data in arr
     * @param <T> the data type for sort
     * @return the new pivot index
     */
    private static <T> int partition(T[] arr, int initial, int last,
                                     int pivotIndex, Comparator<T> comparator) {
        T pivot = arr[pivotIndex];
        swap(arr, pivotIndex, last);
        int i = initial;
        for (int r = initial; r < last; r++) {
            if (comparator.compare(arr[r], pivot) <= 0) {
                swap(arr, r, i);
                i++;
            }
        }
        swap(arr, last, i);
        return i;
    }

    /**
     * Helper method for swapping elements
     *
     * @param arr the array to be sorted
     * @param a the first element
     * @param b the second element
     * @param <T> the data type for the sort
     */
    private static <T> void swap(T[] arr, int a, int b) {
        T dummy = arr[a];
        arr[a] = arr[b];
        arr[b] = dummy;
    }


}
