package edu.emory.cs.trie;

import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
public class TrieQuizTest {
    @Test
    public void testGetEntities() {
//        final List<String> L = List.of("United States", "South Korea");
//        TrieQuiz trie = new TrieQuiz();
//        for (int i = 0; i < L.size(); i++)
//            trie.put(L.get(i), i);
//
//        String input = "I was born in South Korea and raised in the United States";
//        List<Entity> entities = List.of(new Entity(44, 57, 0), new Entity(14, 25, 1));
//        Set<String> expected = entities.stream().map(Entity::toString).collect(Collectors.toSet());
//        Set<String> actual = trie.getEntities(input).stream().map(Entity::toString).collect(Collectors.toSet());
//        assertEquals(expected, actual);
        final List<String> L = List.of("United States", "South Korea", "United States Check");
        TrieQuiz trie = new TrieQuiz();
        for (int i = 0; i < L.size(); i++)
            trie.put(L.get(i), i);

        String input = "I was born in South Korea and raised in the United States Check";
        List<Entity> entities = List.of(new Entity(44, 63, 2), new Entity(44, 57, 0), new Entity(14, 25, 1));
        Set<String> expected = entities.stream().map(Entity::toString).collect(Collectors.toSet());
        Set<String> actual = trie.getEntities(input).stream().map(Entity::toString).collect(Collectors.toSet());
        assertEquals(expected, actual);
    }
}

