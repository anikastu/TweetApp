package com.codepath.apps.basictwitter.fragments;

import org.json.JSONArray;

import com.codepath.apps.basictwitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.util.Log;

public class MentionsTimelineFragment extends TweetsListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		populateMentionsTimeline();
	}
	
	public void populateMentionsTimeline() {
		client.getMentionsTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray json) {
				clearAll();
				addAll(Tweet.fromJSONArray(json));
			}

			@Override
			public void onFailure(Throwable e, String s) {
				Log.d("debug", e.toString());
				Log.d("debug", s.toString());
			}
		});
	}
}
