package nz.ac.auckland.se281.a4;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nz.ac.auckland.se281.a4.ds.Graph;
import nz.ac.auckland.se281.a4.ds.Node;

//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************
public class TweetGraph extends Graph {

	protected List<Tweet> tweets;
	// Change this to map
	protected Map<TwitterHandle, List<Tweet>> nodeTweets;

	public TweetGraph(List<String> edges, List<Tweet> tweets, Map<TwitterHandle, List<Tweet>> map) throws Exception {
		super(edges);
		this.tweets = tweets;
		// changed to LinkedHashMap for fixed order
		this.nodeTweets = new LinkedHashMap<>();
		nodeTweets = map;
	}

	public List<Tweet> getTweets(Node n) {
		return nodeTweets.get(n);
	}

	public List<String> getTweetsTexts(TwitterHandle n) {
		List<String> texts = new ArrayList<>(); // Only allowed to use ArrayList HERE !!!
		for (Tweet t : getTweets(n)) {
			texts.add(t.getTextString());
		}
		return texts;
	}

	// search for a keyword in a tweet starting from a given node
	public String searchTweet(TwitterHandle user, String tweetKeyword) {
		List<String> tweetsFromUser = getTweetsTexts(user);
		for (int z = 1; z < tweetsFromUser.size(); z++) {
			String message = tweetsFromUser.get(z);
			if (message.contains(tweetKeyword)) {
				return "The tweet string found is: " + message + "\n" + "User " + user.getName() + " {" + user.getID()
						+ "}" + " tweeted " + tweetKeyword;
			}
		}

		List<Node<String>> tweets = depthFirstSearch(user, true);
		for (int x = 1; x < tweets.size(); x++) {
			TwitterHandle handle = (TwitterHandle) tweets.get(x);
			List<String> tweetsForUser = getTweetsTexts(handle);
			for (int y = 0; y < tweetsForUser.size(); y++) {
				if (tweetsForUser.get(y).contains(tweetKeyword)) {
					return "The tweet string found is: " + tweetsForUser.get(y) + "\n" + "User " + handle.getName()
							+ " {" + handle.getID() + "}" + " tweeted " + tweetKeyword;
				}
			}
		}
		return "No successor of " + user.getName() + " tweeted " + tweetKeyword;
	}
}
