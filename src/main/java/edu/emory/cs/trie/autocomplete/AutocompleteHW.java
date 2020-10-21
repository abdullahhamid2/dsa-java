package edu.emory.cs.trie.autocomplete;
import java.util.List;
public class AutocompleteHW extends Autocomplete<List<String>> {
    public AutocompleteHW(String dict_file, int max) {
        super(dict_file, max);
    }

    @Override
    public List<String> getCandidates(String prefix) {
        // TODO: to be updated
        return List.of("dummy", "candidate");
    }

    @Override
    public void pickCandidate(String prefix, String candidate) {
        // TODO: to be filled
    }
}