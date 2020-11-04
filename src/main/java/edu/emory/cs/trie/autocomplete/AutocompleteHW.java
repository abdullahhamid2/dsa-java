package edu.emory.cs.trie.autocomplete;
import java.util.*;
import edu.emory.cs.trie.Trie;
import edu.emory.cs.trie.TrieNode;
public class AutocompleteHW extends Autocomplete<List<String>> {
    List<String> result ;
    Queue<String> sQueue ;
    Queue<TrieNode<List<String>>> nQueue;
    TrieNode<List<String>> node;
    public AutocompleteHW(String dict_file, int max)
    {
        super(dict_file, max);
    }
    @Override
    public List<String> getCandidates(String prefix)
    {
        prefix = prefix.replaceAll("\\s+","");
        node = find(prefix);
        if(node == null){
            put(prefix,null);
            node = find(prefix);
            node.setEndState(false);
            return new ArrayList<>();
        }
        if(node.getValue()!=null)
        {
            List<String> tempString = node.getValue();
            List<String> newList = createList(prefix);
            for(int i=0;i<tempString.size();i++)
            {
                if(!newList.contains(tempString.get(0)))
                {
                    newList.add(i,tempString.get(0));
                    tempString.remove(0);
                }
                else if(!newList.get(i).equals(tempString.get(0)))
                {
                    if(!tempString.contains(newList.get(i)))
                    {
                        tempString.remove(0);
                    }
                    else
                        {
                        newList.remove(tempString.get(0));
                        newList.add(i, tempString.get(0));
                        tempString.remove(0);
                    }
                }
                else
                    {
                    tempString.remove(0);
                }
            }
            node.setValue(newList);
            return newList;
        }
        else
            {
            result=createList(prefix);
            node.setValue(result);
            return result;
        }
    }
    public List<String> createList(String prefix){
        node = find(prefix);
        result = new ArrayList<>();
        sQueue = new LinkedList<>();
        nQueue = new LinkedList<>();;
        if (node.isEndState())
        {
            result.add(prefix);
        }
        sQueue.add(prefix);
        nQueue.add(node);
        while (result.size() < getMax())
        {
            if(nQueue.isEmpty()) break;
            TrieNode<List<String>> temp = nQueue.remove();
            String actual = sQueue.remove();
            char[] X = new char[temp.getChildrenMap().size()];
            int i =0;
            for (Character key : temp.getChildrenMap().keySet())
            {
                X[i] = key;
                i++;
            }
            Arrays.sort(X);
            for (Character newChar : X)
            {
                if(result.size()>=getMax()) break;
                nQueue.add(temp.getChild(newChar));
                if (temp.getChild(newChar).isEndState())
                {
                    result.add(actual + temp.getChild(newChar).getKey());
                }
                sQueue.add(actual + temp.getChild(newChar).getKey());
            }
        }
        return result;
    }
    @Override
    public void pickCandidate(String prefix, String candidate)
    {
        prefix = prefix.replaceAll("\\s+","");
        candidate = candidate.replaceAll("\\s+","");
        node = find(prefix);
        if(node!=null)
        {
            List<String> pickList = node.getValue();
            List<String> resultList = getCandidates(prefix);
            if (pickList != null)
            {
                if(find(candidate)==null||!find(candidate).isEndState())
                {
                    put(candidate,null);
                    resultList.add(0,candidate);
                    resultList.remove(resultList.size()-1);
                }
                else if (resultList.remove(candidate))
                {
                    resultList.add(0, candidate);
                }
                else
                    {
                    resultList.add(0,candidate);
                    }
                node.setValue(resultList);
            } else
                {
                getCandidates(prefix);
                pickCandidate(prefix, candidate);
            }
        }
        else
            {
            getCandidates(prefix);
            List<String> resultList = new ArrayList<>();
            resultList.add(candidate);
            node.setValue(resultList);
            if (!contains(candidate))
            {
                if(find(candidate)==null) {
                    put(candidate, null);
                }
                else
                    {
                    put(candidate,find(candidate).getValue());
                }
            }
        }
    }
}

