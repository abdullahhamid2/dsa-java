package edu.emory.cs.graph.flow;
import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.Subgraph;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class NetworkFlowQuiz
{
    public Set<Subgraph> getAugmentingPaths(Graph graph, int source, int target)
    {
        Set<Subgraph> result = new HashSet<Subgraph>();
        Subgraph sub = new Subgraph();
        if (source == target || target >= graph.size() || target < 0 || source >= graph.size() || source < 0)
        {
            return result;
        }
        getPaths(graph, sub, result, source, target);
        return result;
    }
    public void getPaths(Graph graph, Subgraph sub, Set<Subgraph> set, int source, int target)
    {
        if (source == target)
        {
            set.add(sub);
            return;
        }
        Subgraph temp;
        for (Edge edge : graph.getIncomingEdges(target))
        {
            if (sub.contains(edge.getSource())) continue;
            temp = new Subgraph(sub);
            temp.addEdge(edge);
            getPaths(graph, temp, set, source, edge.getSource());
        }
        return;
    }
}