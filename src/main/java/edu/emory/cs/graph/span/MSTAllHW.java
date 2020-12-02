package edu.emory.cs.graph.span;
import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;
public class MSTAllHW implements MSTAll {
    private double weights;
    private List<SpanningTree> trees = new ArrayList<SpanningTree>();
    @Override
    public List<SpanningTree> getMinimumSpanningTrees(Graph graph) {
        SpanningTree temp;
        MST prim = new MSTPrim();
        temp = prim.getMinimumSpanningTree(graph);
        weights = temp.getTotalWeight();
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();
        SpanningTree tree = new SpanningTree();
        List<Edge> startingEdges = new ArrayList<Edge>();
        startingEdges = graph.getIncomingEdges(0);
        queue.addAll(startingEdges);
        visited.add(0);
        getMinimumSpanningTrees(graph, queue, visited, tree);
        return trees;
    }
    private void getMinimumSpanningTrees(Graph graph, PriorityQueue<Edge> queue, Set<Integer> visited, SpanningTree tree) {
        if (tree.getTotalWeight() > weights)
        {
            return;
        }
        if (visited.size() == graph.size())
        {
            trees.add(tree);
            System.out.println(tree + "\n--------\n");
            return;
        }
        while (!queue.isEmpty())
        {
            Edge edge = queue.poll();
            PriorityQueue<Edge> copied = new PriorityQueue<>(queue);
            Set<Integer> copiedVisited = new HashSet<>(visited);
            SpanningTree copiedTree = new SpanningTree(tree);
            copiedTree.addEdge(edge);
            if (!copiedVisited.contains(edge.getSource())) copiedVisited.add(edge.getSource());
            for (Edge e : graph.getIncomingEdges(edge.getSource())) if (!copied.contains(e)) copied.add(e);
            getMinimumSpanningTrees(graph, copied, copiedVisited, copiedTree);
        }
    }
}
