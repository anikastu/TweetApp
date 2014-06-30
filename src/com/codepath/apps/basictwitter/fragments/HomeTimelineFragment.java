package com.codepath.apps.basictwitter.fragments;

import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.codepath.apps.basictwitter.fragments.ComposeFragment.onTweetPostedListener;
import com.codepath.apps.basictwitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class HomeTimelineFragment extends TweetsListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		populateTimeline();
	}

	public void populateTimeline() {
		client.getHomeTimeline(new JsonHttpResponseHandler() {
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

	public void customLoadMoreDataFromApi(long max_id) {
		client.getMoreTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray json) {
				addAll(Tweet.fromJSONArray(json));
			}

			@Override
			public void onFailure(Throwable e, String s) {
				Log.d("debug", e.toString());
				Log.d("debug", s.toString());
			}
		}, max_id);
	}
	
	public void showToast() {
		Toast.makeText(getActivity(), "Toasting from Fragment", Toast.LENGTH_LONG).show();
	}
	
}
