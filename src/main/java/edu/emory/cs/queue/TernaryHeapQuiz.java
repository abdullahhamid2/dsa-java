package edu.emory.cs.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TernaryHeapQuiz<T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    private final List<T> keys;


    public TernaryHeapQuiz() {
        this(Comparator.naturalOrder());
    }

    public TernaryHeapQuiz(Comparator<T> priority) {
        super(priority);
        keys = new ArrayList<>();
        keys.add(null);
    }
    private int compare(int i1, int i2) {
        return priority.compare(keys.get(i1), keys.get(i2));
    }

    @Override
    public void add(T key) {
        keys.add(key);
        swim(size());
    }
//    private void swim(int k) // intended to identify parent method and swap if child is bigger than parent
//    {
//        while (1 < k && compare(((k-1)/3), (k)) < 0)
//        {
//            Collections.swap(keys, (k-1)/3, k);
//            k -= 1; k /= 3;
//        }
//    }
    private void swim(int k){
            if(k%3==0) //center node
    {
        for (;1<=k ; k=k/3){
            int parentIndex = k/3;
            int childIndex = k;
            if(parentIndex < 1) parentIndex = 1;

            if(compare(parentIndex, childIndex) < 0) {
                Collections.swap(keys, parentIndex, childIndex);
            }
        }
    }
    else if((k-1)%3==0) //right node
        {
            for (; 1<k; k=(k-1)/3){
                int parentIndex = (k-1)/3;
                int childIndex = k;
                if(parentIndex < 1) parentIndex = 1;
                if(compare(parentIndex, childIndex) < 0) {
                    Collections.swap(keys, parentIndex, childIndex);
                }
            }
        }
    else if((k+1) % 3==0) { //left node
        for(; 1<k; k=(k+1)/3){
            int parentIndex = (k+1)/3;
            int childIndex = k;
            if(parentIndex < 1) parentIndex = 1;
            if(compare(parentIndex, childIndex) < 0) {
                Collections.swap(keys, parentIndex, childIndex);
            }
        }
    }
    }

    @Override
    public T remove() {
        if (isEmpty()) return null;
        Collections.swap(keys, 1, size());
        T max = keys.remove(size());
        sink();
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
    private void sink(){
                for (int k = 1, i = 2; i <= size()-1;k=i,i*=3) {
                    int Max = i;
                    if((i+1)<=size() && compare((i), (i+1)) < 0) {
                        if ((i+2)<=size() && compare((i + 1), i + 2) < 0) {
                            Max = i + 2;
                        } else {
                            Max = i + 1;
                        }
                    }
                    else if((i+2)<=size() && compare((i), (i+2)) < 0){
                            Max = i + 2;
                        }
                        else{
                            Max = i;
                        }
            if (compare((k), (Max)) >= 0) {
                break;
            }
            Collections.swap(keys, k, Max);
        }
    }

    @Override
    public int size() {
        // TODO: to be updated
        return keys.size()-1;
    }
}