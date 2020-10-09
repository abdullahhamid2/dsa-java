package edu.emory.cs.sort.hybrid;
import edu.emory.cs.sort.AbstractSort;
import edu.emory.cs.sort.comparison.InsertionSort;
import edu.emory.cs.sort.divide_conquer.QuickSort;
import java.lang.reflect.Array;
import java.util.*;

public class HybridSortHW<T extends Comparable<T>> extends AbstractSort<T> implements HybridSort<T> {

private AbstractSort<T> engineQuickSort;
private AbstractSort<T> engineInsertionSort;
private T[] temp;

    public HybridSortHW() {
        super(Comparator.naturalOrder());
        engineQuickSort = new QuickSort<>();
        engineInsertionSort = new InsertionSort<>();
    }
    @Override
    public T[] sort(T[][] input) {
        int[] count;
        count = new int[input.length + 1];
        count[0] = 0;
        for (int row = 0; row < input.length; row++)
        {
            int countA = 0;
            int countB = 0;
            if (input[row].length > 25)
            {
            for (int cols = 0; cols < 25; cols++)
            {
            if (input[row][cols].compareTo(input[row][cols + 1]) < 0)
            {
            countA++;
            }
            else if (input[row][cols].compareTo(input[row][cols + 1]) > 0)
            {
             countB++;
            }
                }
                if (countA == 25)
                {
                    engineInsertionSort.sort(input[row]);
                }
                else if (countB == 25)
                {
                    engineInsertionSort.sort(reverseArray(input[row]));
                }
                else if (countA > 20)
                {
                    engineQuickSort.sort(input[row]);
                }
                else if (countB > 20)
                {
                    engineQuickSort.sort(reverseArray(input[row]));
                }
                else engineQuickSort.sort(input[row]);
            } else
                {
                engineQuickSort.sort(input[row]);
                }
            count[row + 1] = input[row].length;
        }
        int size = Arrays.stream(input).mapToInt(t -> t.length).sum();
        int beginIndex = 0;
        T[] output = (T[]) Array.newInstance(input[0][0].getClass(), size);
        for (T[] t : input)
        {
            System.arraycopy(t, 0, output, beginIndex, t.length);
            beginIndex += t.length;
        }
        for (int i = 0; i < count.length - 1; i++)
        {
            count[i + 1] = count[i] + count[i + 1];
        }
        merge(output, count);
        return output;
    }
    private void merge(T[] array, int[] count) {
        if (count.length > 2) {
            int[] temp = new int[(count.length / 2) + 1];
            temp[0] = 0;
            for (int i = 0; i < count.length - 2; i += 2)
            {
                merge(array, count[i], count[i + 1], count[i + 2]);
            }
            for (int j = 1; j < temp.length - 1; j++)
            {
                temp[j] = count[j * 2];
            }
            temp[temp.length - 1] = count[count.length - 1];
            count = new int[temp.length];
            for (int i = 0; i < temp.length; i++) {
                count[i] = temp[i];
            }
            merge(array, count);
        }
    }
    //some auxillary methods.
    private T[] reverseArray(T[] array) {
        T[] result = Arrays.copyOf(array, array.length);
        for (int i = 0; i < array.length; i++) {
            result[i] = array[array.length - 1 - i];
        }
        return result;
    }
    //these two methods already exist in the MergeSort.java
    private void merge(T[] array, int beginIndex, int middleIndex, int endIndex) {
        int fst = beginIndex, snd = middleIndex;
        copy(array, beginIndex, endIndex);
        for (int k = beginIndex; k < endIndex; k++) {
            if (fst >= middleIndex)
                assign(array, k, temp[snd++]);
            else if (snd >= endIndex)
                assign(array, k, temp[fst++]);
            else if (compareTo(temp, fst, snd) < 0)
                assign(array, k, temp[fst++]);
            else
                assign(array, k, temp[snd++]);
        }
    }
    private void copy(T[] array, int beginIndex, int endIndex) {
        int N = array.length;
        if (temp == null || temp.length < N)
            temp = Arrays.copyOf(array, N);
        else {
            N = endIndex - beginIndex;
            System.arraycopy(array, beginIndex, temp, beginIndex, N);
        }
        assignments += N;
    }
    @Override
    public void sort(T[] array, int beginIndex, int endIndex) {

    }
}
