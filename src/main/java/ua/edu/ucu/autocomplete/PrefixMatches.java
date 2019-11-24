package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;
import java.util.ArrayList;

/**
 *
 * @author andrii
 */

public class PrefixMatches {

    private Trie trie;


    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int amount = 0;
        if (strings != null) {
            for (int i = 0; i < ((String[]) strings).length; i++) {
                String[] newWords = ((String[]) strings)[i].split(" ");
                amount += loadsHelp(newWords);
            }
        }
        return amount;
    }

    private int loadsHelp(String... strings) {
        int elAmount = 0;
        for (String el: strings) {
            if (el.length() > 2) {
                Tuple elInfo = new Tuple(el, el.length());
                trie.add(elInfo);
                elAmount++;
            }
        }
        return elAmount;
    }

    public boolean contains(String word) {
            return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() > 1) {
            return trie.wordsWithPrefix(pref);
        }
        return null;
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (pref.length() > 1) {
            ArrayList<String> result = new ArrayList<>();
            Iterable<String> words = trie.wordsWithPrefix(pref);
            int counter = 0;
            int prefLength = -1;
            for (String el: words) {
                if (counter < k) {
                    if (el.length() != prefLength && el.length() > 2) {
                        result.add(el);
                        prefLength = el.length();
                        counter++; }
                    else if (el.length() == prefLength) { result.add(el); } }
                else { break; } }
            return result;
        }
        return null;
    }

    public int size() {
        return trie.size();
    }
}
