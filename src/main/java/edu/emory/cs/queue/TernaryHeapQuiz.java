package edu.emory.cs.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TernaryHeapQuiz<T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    private final List<T> keys;
    private int size;


    public TernaryHeapQuiz() {
        this(Comparator.naturalOrder());
    }

    public TernaryHeapQuiz(Comparator<T> priority) {
        super(priority);
        keys = new ArrayList<>();
        keys.add(null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private int compare(int i1, int i2) {
        return priority.compare(keys.get(i1), keys.get(i2));
    }

    @Override
    public void add(T key) {
        keys.add(key);
        swim(++size);
    }

    //    private void swim(int k) // intended to identify parent method and swap if child is bigger than parent
//    {
//        while (1 < k && compare(((k-1)/3), (k)) < 0)
//        {
//            Collections.swap(keys, (k-1)/3, k);
//            k -= 1; k /= 3;
//        }
//    }
    private void swim(int k) {
        if (k == 2 && compare((k - 1), (k)) < 0) {
            Collections.swap(keys, k - 1, k);
            return;
        }
        while (k > 2 && compare(((k + 1) / 3), (k)) < 0) {
            Collections.swap(keys, (k + 1) / 3, k);
            k = (k + 1) / 3;
            if (k == 2 && compare((k - 1), (k)) < 0) {
                Collections.swap(keys, k - 1, k);
                break;
            }
        }
    }

    @Override
    public T remove() {
        Collections.swap(keys, 1, size);
        T max = keys.remove(size--);
        sink(1);
        return max;
    }

    //    private void sink(int k)
//    {
//        for (int i=k/3; i<=size(); k=i,i*=3)
//        {
//            if (i < size() && compare((i), (i+1)) < 0 && compare((i),(i+2)) < 0) i++;
//            if (compare((k),(i)) >= 0) {
//                break;
//            }
//            Collections.swap(keys, k, i);
//        }
//    }
    private void sink(int k) {
        int largest;
        for (int i = k + 1; i <= size; k = largest, i = (largest * 3) - 1) {
            largest = i;
            if (i < size && (compare((i), (i + 1)) < 0)) largest = i + 1;
            if (i < (size - 1) && (compare((i + 1), (i + 2)) < 0)) largest = i + 2;
            if (compare((k), (largest)) >= 0) break;
            Collections.swap(keys, k, largest);
        }
    }
}
