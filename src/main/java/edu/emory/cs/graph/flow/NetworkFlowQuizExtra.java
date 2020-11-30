package edu.emory.cs.graph.flow;
import edu.emory.cs.graph.Edge;
import edu.emory.cs.graph.Graph;
import edu.emory.cs.graph.Subgraph;
import java.util.*;
public class NetworkFlowQuizExtra
{
    public Set<Subgraph> getAugmentingPaths(Graph graph, int source, int target)
    {
        Queue<Subgraph> queue = new LinkedList<>();
        Set<Subgraph> result = new HashSet<Subgraph>();
        Set<Integer> visited;
        Subgraph temp;
        Subgraph sub;
        int count;
        if (source == target || target >= graph.size() || target < 0 || source >= graph.size() || source < 0)
        {
            return result;
        }
        for (Edge edge : graph.getIncomingEdges(target))
        {
            sub = new Subgraph();
            sub.addEdge(edge);
            queue.offer(sub);
        }
        while (!queue.isEmpty())
        {
            sub = new Subgraph(queue.poll());
            visited = sub.getVertices();
            count = sub.getEdges().size() - 1;
            for (Edge edge : graph.getIncomingEdges(sub.getEdges().get(count).getSource()))
            {
                if (visited.contains(edge.getSource()))
                {
                    continue;
                }
                temp = new Subgraph(sub);
                temp.addEdge(edge);
                if (edge.getSource() == source)
                {
                    result.add(temp);
                }
                else
                    {
                    queue.offer(temp);
                }
            }
        }
        return result;
    }
}
