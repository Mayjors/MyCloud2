package com.example.algorithm.ai;

/**
 * 36. 实现 Trie (前缀树)
 * Trie（发音类似 "try"）或者说前缀树是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 
 * 示例:
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 */
public class Problem36_ImplementTrie {
    
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }
    
    class Trie {
        private TrieNode root;
        
        public Trie() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }
        
        public boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd;
        }
        
        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }
        
        private TrieNode searchPrefix(String prefix) {
            TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }
    }
}
