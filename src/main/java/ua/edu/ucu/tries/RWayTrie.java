package ua.edu.ucu.tries;
import ua.edu.ucu.collections.Queue;
import java.util.Arrays;
// частина коду з Algorithms, 4th edition - Robert Sedgewick and Kevin Wayne


public class RWayTrie<Value> implements Trie {

    private static final int R = 256; //extended ASCII
    private Node root = new Node();

    private static class Node {
        private Object value;
        private Node[] next = new Node[R];
    }

    @Override
    public void add(Tuple t) {
        root = put(root, t, 0);
    }

    private Node put(Node x, Tuple t, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == t.term.length()) {
            x.value = t.weight;
            return x;
        };
        char c = t.term.charAt(d);
        x.next[c] = put(x.next[c], t, d+1);
        return x;
    }

    @Override
    public boolean contains(String word) {
        return get(word) != null;
    }

    public Value get(String word) {
        Node x = get(root, word, 0);
        if (x == null) return null;
        return (Value) x.value;
    }

    private Node get(Node x, String word, int d) {
        if (x == null) return null;
        if (d == word.length()) return x;
        char c = word.charAt(d);
        return get(x.next[c], word, d+1);
    }

    @Override
    public boolean delete(String word) {
         root = delete(root, word, 0);
         return true;
        }

    private Node delete(Node x, String key, int d) {
            if (x == null) return null;
            if (d == key.length())
                x.value = null;
            else
            {
                char c = key.charAt(d);
                x.next[c] = delete(x.next[c], key, d+1);
            }
            if (x.value != null) return x;
            for (char c = 0; c < R; c++)
                if (x.next[c] != null) return x;
            return null;
        }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }


    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue q = new Queue();
        collect(get(root, s, 0), s, q);
        int resultSize = q.queue.size();
        String[] resultWords = new String[resultSize];
        for (int i = 0; i < resultSize; i++) {
            resultWords[i] =(String) q.dequeue();
        }
        Arrays.sort(resultWords, (a,b)-> a.length() - b.length());
        return Arrays.asList(resultWords);
    }

    private void collect(Node x, String pre, Queue q) {
        if (x == null) return;
        if (x.value != null) q.enqueue(pre);
        for (char c = 0; c < R; c++)
            collect(x.next[c], pre + c, q);
    }

    @Override
    public int size()
    { return size(root); }

    private int size(Node x)
    {
        if (x == null) return 0;
        int cnt = 0;
        if (x.value != null) cnt++;
        for (char c = 0; c < R; c++)
            cnt += size(x.next[c]);
        return cnt;
    }
}
