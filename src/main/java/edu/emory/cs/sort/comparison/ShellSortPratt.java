package edu.emory.cs.sort.comparison;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
public class ShellSortPratt<T extends Comparable<T>> extends ShellSort<T> {
    public ShellSortPratt(){this(Comparator.naturalOrder());}
    public ShellSortPratt(Comparator<T> comparator){this(comparator,1000); }
    public ShellSortPratt(Comparator<T> comparator, int n){super(comparator);}
    @Override
    protected void populateSequence(int n) {
        this.sequence = new ArrayList<>(n/10);
        n=n/7;
        sequence.add(1);
        int seqInd = 0, pow2 = 1, pow3 = 1;
        for(int i=0;;i++){
            if (Math.pow(2, pow2) < Math.pow(3, pow3) && Math.pow(2, pow2) < (6 * sequence.get(seqInd)) && Math.pow(2, pow2) < n) {
                sequence.add((int) Math.pow(2, pow2));
                pow2++;
            }
            else if (Math.pow(3, pow3) < Math.pow(2, pow2) && Math.pow(3, pow3) < (6 * sequence.get(seqInd)) && Math.pow(3, pow3) < n) {
                sequence.add((int) Math.pow(3, pow3));
                pow3++;
            }
            else if ((6 * sequence.get(seqInd)) < Math.pow(3, pow3) && (6 * sequence.get(seqInd)) < Math.pow(2, pow2) && (6 * sequence.get(seqInd)) < n) {
                sequence.add(6 * sequence.get(seqInd));
                seqInd++;
            }
            else {
                break;
            }
        }
    }
    @Override
    protected int getSequenceStartIndex(int n) {
        int index = Collections.binarySearch(sequence, n/7);
        if (index < 0) index = -(index + 1);
        if (index == sequence.size()) index--;
        return index;
    }

}