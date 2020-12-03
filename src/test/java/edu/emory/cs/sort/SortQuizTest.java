package edu.emory.cs.sort;

import edu.emory.cs.sort.comparison.ShellSortKnuth;
import edu.emory.cs.sort.comparison.ShellSortPratt;
import edu.emory.cs.sort.comparison.ShellSortQuiz;
import edu.emory.cs.sort.distribution.LSDRadixSort;
import edu.emory.cs.sort.distribution.RadixSortQuiz;
public class SortQuizTest extends SortTest {
    public void testRobustness() {
      //  testRobustness(new ShellSortQuiz<>());
       //testRobustness(new RadixSortQuiz());
        //testRobustness(new MSDRadixSort());
        testRobustness(new ShellSortPratt<>());
    }

    public void testRuntime() {
        testRuntime(new ShellSortKnuth<>(), new ShellSortPratt<>());
        //testRuntime(new LSDRadixSort(), new RadixSortQuiz());
    }
}
