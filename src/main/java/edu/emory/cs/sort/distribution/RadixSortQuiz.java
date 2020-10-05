package edu.emory.cs.sort.distribution;

import java.util.function.Function;

public class RadixSortQuiz extends RadixSort {
    @Override
//    public void sort(Integer[] array, int beginIndex, int endIndex) {
//        int maxBit = getMaxBit(array, beginIndex, endIndex);
//        int[] counter = new int[array.length];
//        for (int bit = maxBit; bit > 0; bit--) {
//            int div = (int) Math.pow(10, bit);
//            sort(array, beginIndex, endIndex, key -> (key / div) % 10);
//            for (int i = 0; i <= 9; i++) {
//                counter[i] = buckets.get(i).size();
//                sort(array, beginIndex, beginIndex + counter[i], key -> (key / div) % 10);
//                beginIndex = beginIndex + counter[i];
//            }
//
//        }
    public void sort(Integer[] array, int beginIndex, int endIndex) {
        int maxBit = getMaxBit(array, beginIndex, endIndex);
        if (maxBit< 1) {
            return;
        } else {
            int finalBeginIndex = beginIndex;
            sort(array, beginIndex, endIndex, key -> (key / (int) Math.pow(10, maxBit - 1)));
            for (int i = 0; i < buckets.size(); i++) {
                System.out.println(buckets.get(i).size());
                int FinalEndIndex = 0;
                FinalEndIndex +=buckets.get(i).size();
                int temp = 0;
                temp = temp + buckets.get(i).size();
                if(FinalEndIndex > endIndex) break;
                sort(array, beginIndex,temp, key -> (key / (int) Math.pow(10, maxBit - 1)));
            }

        }
    }
    protected void sort(Integer[] array, int beginIndex, int endIndex, Function<Integer, Integer> bucketIndex){
        for(int i= beginIndex; i<endIndex; i++)
        {
            buckets.get(bucketIndex.apply(array[i])).add(array[i]);
        }

    }


}