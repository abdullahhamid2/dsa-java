package edu.emory.cs.trie.autocomplete;
import edu.emory.cs.trie.Trie;
import edu.emory.cs.trie.TrieNode;
import java.util.*;
public class AutocompleteHWExtra extends Autocomplete<List<List<String>>> {
    List<String> result;
    Queue<String> sQueue;
    Queue<TrieNode<List<List<String>>>> nQueue;
    TrieNode<List<List<String>>> node;
    public AutocompleteHWExtra(String dict_file, int max)
    {
        super(dict_file, max);
    }
    @Override
    public List<String> getCandidates(String prefix) {
        prefix = prefix.replaceAll("\\s+", "");
        node = find(prefix);
        if (node == null) {
            result = new ArrayList<>();
            return result;
        }
        if (node.getValue() != null) {
            result = new ArrayList<>();
            for (List<String> tempList : node.getValue())
                result.add(tempList.get(0));
            return result;
        } else {
            result = new ArrayList<>();
            sQueue = new LinkedList<>();
            nQueue = new LinkedList<>();
            if (node.isEndState())
            {
                result.add(prefix);
            }
            sQueue.add(prefix);
            nQueue.add(node);
            while (result.size() < getMax()) {
                if (nQueue.isEmpty())
                {
                    break;
                }
                TrieNode<List<List<String>>> temp = nQueue.remove();
                String actual = sQueue.remove();
                char[] X = new char[temp.getChildrenMap().size()];
                int i = 0;
                for (Character key : temp.getChildrenMap().keySet()) {
                    X[i] = key;
                    i++;
                }
                Arrays.sort(X);
                for (Character newChar : X) {
                    if (result.size() >= getMax())
                    {
                        break;
                    }
                    nQueue.add(temp.getChild(newChar));
                    if (temp.getChild(newChar).isEndState())
                    {
                        result.add(actual + temp.getChild(newChar).getKey());
                    }
                    sQueue.add(actual + temp.getChild(newChar).getKey());
                }
            }
            List<List<String>> values = new ArrayList<>();
            for (String tempStr : result)
            {
                List<String> tempList = new ArrayList<>();
                tempList.add(tempStr);
                values.add(tempList);
            }
            node.setValue(values);
            return result;
        }
    }
    @Override
    public void pickCandidate(String prefix, String candidate)
    {
        prefix = prefix.replaceAll("\\s+", "");
        candidate = candidate.replaceAll("\\s+", "");
        node = find(prefix);
        if (node != null)
        {
            List<List<String>> picked = node.getValue();
            List<String> results = getCandidates(prefix);
            List<List<String>> values = new ArrayList<>();
            if (picked != null)
            {
                int index = results.indexOf(candidate);
                int i = 0;
                for (List<String> tempList : picked) {
                    if (i == index)
                        tempList.add(candidate);
                    values.add(tempList);
                    i++;
                }
                if (index >= 0)
                {
                    List<String> temp = values.remove(index);
                    int count = 0;
                    while (temp.size() < values.get(count).size())
                    {
                        count++;
                    }
                    values.add(count, temp);
                }
                node.setValue(values);
            } else
                {
                getCandidates(prefix);
                pickCandidate(prefix, candidate);
                }
        } else
            {
            System.out.println("Prefix does not exist");
            }
    }
}