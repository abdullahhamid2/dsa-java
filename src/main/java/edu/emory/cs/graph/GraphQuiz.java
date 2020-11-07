package edu.emory.cs.graph;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class GraphQuiz extends Graph {
    public GraphQuiz(int size) { super(size); }
    public GraphQuiz(Graph g) { super(g); }
    private HashSet<List<Integer>> visited = new HashSet<>();
    /** @return the total number of cycles in this graph. */
    public int numberOfCycles() {
        Deque<Integer> notYetVisited = IntStream.range(0, size()).boxed().collect(Collectors.toCollection(ArrayDeque::new));
        List<List<Integer>> result = new ArrayList<>();
        while (!notYetVisited.isEmpty())
        {
            numCycles(notYetVisited.poll(), notYetVisited, new ArrayList<>());
        }
        visited = new HashSet<>(visited);
        int count = 0;
        for (List<Integer> NEW : visited) {
            boolean bool1 = true;
            if (NEW.size()>2) {
                for (int i = 0; i <= NEW.size() - 2; i++) {
                    if (!undirectedEdge(NEW.get(i), NEW.get(i+1))) {
                        bool1 = false;
                    }
                }
            } else
            {
                continue;
            }
            if (bool1)
            {
                count++;
            }
        }
        return visited.size() + count;
    }
    private void numCycles(int current, Deque<Integer> notVisited, List<Integer> inclusion)
    {
        inclusion.add(current);
        for (Edge edge : getIncomingEdges(current))
        {
            if (inclusion.get(0) == edge.getSource())
            {
                inclusion.sort(Comparator.naturalOrder());
                HashSet<Integer>temp = new HashSet(inclusion);
                if (inclusion.size() == temp.size())
                {
                    visited.add(new ArrayList<Integer>(inclusion));
                    return;
                }
            } else if (inclusion.size() >= size())
            {
                break;
            }
            numCycles(edge.getSource(), notVisited, new ArrayList<Integer>(inclusion));
        }
    }
    private boolean undirectedEdge (int i, int j)
    {
        boolean final1 = false;
        boolean final2 = false;
        for (Edge edge : getIncomingEdges(i))
        {
            if (edge.getSource() == j) final1 = true;
        }
        for (Edge edge : getIncomingEdges(j))
        {
            if (edge.getSource() == i) final2 = true;
        }
        boolean result = false;
        if (final1 && final2) result = true;
        return result;
    }
}