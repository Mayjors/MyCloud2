package com.example.algorithm.ai;

import java.util.*;

/**
 * 122. TinyURL 的加密与解密
 * TinyURL 是一种 URL 简化服务。设计一个类来加密与解密 TinyURL。
 * 保证加密后的 URL 可以解密回原 URL。
 */
public class Problem122_EncodeDecodeTinyURL {
    
    class Codec {
        private Map<String, String> longToShort = new HashMap<>();
        private Map<String, String> shortToLong = new HashMap<>();
        private String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private String prefix = "http://tinyurl.com/";
        
        public String encode(String longUrl) {
            if (longToShort.containsKey(longUrl)) {
                return longToShort.get(longUrl);
            }
            
            String key = generateKey();
            while (shortToLong.containsKey(key)) {
                key = generateKey();
            }
            
            String shortUrl = prefix + key;
            longToShort.put(longUrl, shortUrl);
            shortToLong.put(shortUrl, longUrl);
            
            return shortUrl;
        }
        
        public String decode(String shortUrl) {
            return shortToLong.get(shortUrl);
        }
        
        private String generateKey() {
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                key.append(chars.charAt((int) (Math.random() * 62)));
            }
            return key.toString();
        }
    }
}
