/*
A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings.
There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:
*/
import java.util.*;
class Trie {

    private Map<Character, Trie> children;
    private int count;
    private boolean isEndOfWord;
    
    public Trie() {
        children = new HashMap<>();
    }
    
    public void addWord(String word) {
        addWord(word, 0);
    }
    
    public boolean containsWord(String word) {
        return contains(word, 0);
    }
    
    public boolean containsPrefix(String prefix) {
        return containsPrefix(prefix, 0);
    }
    
    public int getWordCount(String prefix) {
        return getWordCount(prefix, 0);
    }
	
	public List<String> getWordsForPrefix(String prefix) {
		List<String> words = new ArrayList<>();
		getWordsForPrefix(words, prefix, 0);
		return words;
	}
	
	private void getWordsForPrefix(List<String> words, String prefix, int index) {
		if (index == prefix.length()) {
		    addWords(words, prefix);
		    return;
		}
		
		char c = prefix.charAt(index);
		Trie node = children.get(c);
		if (node == null) {
		    return;
		}
		node.getWordsForPrefix(words, prefix, index + 1);
	}
	
	private void addWords(List<String> words, String prefix) {
	    for (char c: children.keySet()) {
            prefix += c;

            Trie child = children.get(c);
            if (child.isEndOfWord) {
                words.add(new String(prefix));
            }
            
            // add other words in the trie (ie. cart)
            child.addWords(words, prefix);

            prefix = prefix.substring(0, prefix.length() - 1);
	    }
	}
    
    private int getWordCount(String prefix, int index) {
        if (index == prefix.length()) {
            return count;
        }
        
        char c = prefix.charAt(index);
        Trie node = children.get(c);
        if (node == null) {
            return 0;
        }
        
        return node.getWordCount(prefix, index + 1);
    }
    
    private boolean containsPrefix(String prefix, int index) {
        if (index == prefix.length()) {
            return true;
        }
        
        char c = prefix.charAt(index);
        Trie node = children.get(c);
        if (node == null) {
            return false;
        }
        
        return node.containsPrefix(prefix, index + 1);
    }
    
    private boolean contains(String word, int index) {
        if (index == word.length()) {
            return isEndOfWord;
        }
        char c = word.charAt(index);
        Trie node = children.get(c);
        if (node == null) {
            return false;
        }
        return node.contains(word, index + 1);
    }
    
    private void addWord(String word, int index) {
        count++;
    
        if (index == word.length()) {
            isEndOfWord = true;
            return;
        }
        char c = word.charAt(index);
        Trie node = getNode(c);
        node.addWord(word, index + 1);
    }
    
    private Trie getNode(char c) {
        Trie node = children.get(c);
        if (node == null) {
            node = new Trie();
            children.put(c, node);
        }
        return node;
    }
    
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.addWord("cat");
        trie.addWord("car");
        trie.addWord("rat");
        trie.addWord("cart");
        trie.addWord("cartoon");
        trie.addWord("rate");
        System.out.println(trie.containsWord("bat"));
        System.out.println(trie.containsWord("cat"));
        System.out.println(trie.containsWord("car"));
        System.out.println(trie.containsWord("cab"));
        System.out.println(trie.containsWord("rat"));
        System.out.println("=========");

        System.out.println(trie.containsPrefix("ba"));
        System.out.println(trie.containsPrefix("ca"));
        System.out.println(trie.containsPrefix("ra"));
        System.out.println(trie.containsPrefix("ab"));
        System.out.println(trie.containsPrefix("rat"));
        System.out.println("=========");
        
        System.out.println(trie.getWordCount("ba"));
        System.out.println(trie.getWordCount("ca"));
        System.out.println(trie.getWordCount("ra"));
        System.out.println(trie.getWordCount("ab"));
        System.out.println(trie.getWordCount("rat"));
        System.out.println("=========");
        
        System.out.println(trie.getWordsForPrefix("ca"));
        System.out.println(trie.getWordsForPrefix("r"));
        System.out.println(trie.getWordsForPrefix("bl"));
    }
}