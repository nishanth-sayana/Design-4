//K=Number of Tweets Required in the feed
//N=TotalNumber of Tweets
//Time Complexity=O(NlogK)
//Space Complexity=O(K)
public class DesignTwitter {
    HashMap<Integer,List<Tweet>> tweets;
    HashMap<Integer,HashSet<Integer>> followed;
    int createTime;
    public Twitter() {
        tweets=new HashMap<>();
        followed=new HashMap<>();
    }

    private class Tweet{
        int id;
        int createdAt;

        public Tweet(int id,int createdAt){
            this.id=id;
            this.createdAt=createdAt;
        }

    }



    public void postTweet(int userId, int tweetId) {
        follow(userId,userId);
        if(!tweets.containsKey(userId)){
            tweets.put(userId,new ArrayList<Tweet>());
        }
        tweets.get(userId).add(new Tweet(tweetId,createTime++));
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> pq=new PriorityQueue<>((a,b)->a.createdAt-b.createdAt);
        Set<Integer> followeds=followed.get(userId);
        if(followeds!=null){
            for(int uid:followeds){
                List<Tweet> fTweets=tweets.get(uid);
                if(fTweets!=null){
                    for(Tweet fTweet: fTweets){
                        pq.add(fTweet);

                        while(pq.size()>10){
                            pq.poll();
                        }
                    }
                }
            }
        }
        ArrayList<Integer> result=new ArrayList<>();

        while(!pq.isEmpty()){
            result.add(0,pq.poll().id);
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        if(!followed.containsKey(followerId)){
            followed.put(followerId,new HashSet<Integer>());
        }
        followed.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if(followed.containsKey(followerId) && followeeId!=followerId){
            followed.get(followerId).remove(followeeId);
        }
    }

}
