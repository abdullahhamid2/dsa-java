package edu.emory.cs.set;

/** @author Jinho D. Choi */
public class DisjointSetQuiz {
    static public void main(String[] args) {
        DisjointSet newSet = new DisjointSet(5);
        newSet.union(4, 3);
        newSet.union(2,3);
        newSet.union(0,1);
        newSet.union(1, 3);
        System.out.println(newSet.toString());
        newSet.find(0);
        System.out.println(newSet.toString());
    }
}