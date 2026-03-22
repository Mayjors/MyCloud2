package com.example.algorithm.ai;

import java.util.*;

/**
 * 106. 设计 Twitter
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，
 * 能够看见关注人（包括自己）的最近 10 条推文。
 */
public class Problem106_DesignTwitter {
    
    class Twitter {
        private int timestamp;
        private Map<Integer, Set<Integer>> followees;
        private Map<Integer, List<Tweet>> tweets;
        
        class Tweet {
            int id;
            int timestamp;
            Tweet(int id, int timestamp) {
                this.id = id;
                this.timestamp = timestamp;
            }
        }
        
        public Twitter() {
            timestamp = 0;
            followees = new HashMap<>();
            tweets = new HashMap<>();
        }
        
        public void postTweet(int userId, int tweetId) {
            tweets.computeIfAbsent(userId, k -> new ArrayList<>()).add(new Tweet(tweetId, timestamp++));
        }
        
        public List<Integer> getNewsFeed(int userId) {
            PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> b.timestamp - a.timestamp);
            
            // 添加自己的推文
            if (tweets.containsKey(userId)) {
                List<Tweet> userTweets = tweets.get(userId);
                for (int i = userTweets.size() - 1; i >= Math.max(0, userTweets.size() - 10); i--) {
                    pq.offer(userTweets.get(i));
                }
            }
            
            // 添加关注人的推文
            if (followees.containsKey(userId)) {
                for (int followeeId : followees.get(userId)) {
                    if (tweets.containsKey(followeeId)) {
                        List<Tweet> followeeTweets = tweets.get(followeeId);
                        for (int i = followeeTweets.size() - 1; i >= Math.max(0, followeeTweets.size() - 10); i--) {
                            pq.offer(followeeTweets.get(i));
                        }
                    }
                }
            }
            
            List<Integer> result = new ArrayList<>();
            while (!pq.isEmpty() && result.size() < 10) {
                result.add(pq.poll().id);
            }
            
            return result;
        }
        
        public void follow(int followerId, int followeeId) {
            if (followerId != followeeId) {
                followees.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
            }
        }
        
        public void unfollow(int followerId, int followeeId) {
            if (followees.containsKey(followerId)) {
                followees.get(followerId).remove(followeeId);
            }
        }
    }
}
