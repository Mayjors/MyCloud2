package com.example.algorithm.ai;

/**
 * 193. 文本左右对齐
 * 给定一个单词数组 words 和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，
 * 且左右两端对齐的文本。
 * 
 * 示例:
 * 输入: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * 输出: [
 *   "This    is    an",
 *   "example  of text",
 *   "justification.  "
 * ]
 */
public class Problem193_TextJustification {
    
    public java.util.List<String> fullJustify(String[] words, int maxWidth) {
        java.util.List<String> result = new java.util.ArrayList<>();
        int i = 0;
        
        while (i < words.length) {
            int j = i + 1;
            int lineLength = words[i].length();
            
            while (j < words.length && 
                   lineLength + words[j].length() + (j - i - 1) < maxWidth) {
                lineLength += words[j].length();
                j++;
            }
            
            int diff = maxWidth - lineLength;
            int numberOfWords = j - i;
            
            if (numberOfWords == 1 || j >= words.length) {
                // 左对齐
                StringBuilder sb = new StringBuilder(words[i]);
                for (int k = i + 1; k < j; k++) {
                    sb.append(" ").append(words[k]);
                }
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
                result.add(sb.toString());
            } else {
                // 左右对齐
                int spaces = diff / (numberOfWords - 1);
                int extraSpaces = diff % (numberOfWords - 1);
                
                StringBuilder sb = new StringBuilder(words[i]);
                for (int k = i + 1; k < j; k++) {
                    int spacesToAdd = spaces + (extraSpaces-- > 0 ? 1 : 0);
                    for (int s = 0; s < spacesToAdd; s++) {
                        sb.append(" ");
                    }
                    sb.append(words[k]);
                }
                result.add(sb.toString());
            }
            
            i = j;
        }
        
        return result;
    }
}
