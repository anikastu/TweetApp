package com.codepath.apps.basictwitter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.apps.basictwitter.fragments.HomeTimelineFragment;
import com.codepath.apps.basictwitter.fragments.MentionsTimelineFragment;
import com.codepath.apps.basictwitter.listeners.FragmentTabListener;

public class TimelineActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);

		setupTabs();
////		lvTweets.setOnScrollListener(new EndlessScrollListener() {
////			@Override
////			public void onLoadMore(int page, int totalItemsCount) {
////				long max_id = tweets.get(totalItemsCount - 1).getUid();
////				customLoadMoreDataFromApi(max_id);
////			}
//		});

	}

	
	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		Tab tab1 = actionBar
			.newTab()
			.setText("Home")
			.setIcon(R.drawable.ic_home)
			.setTag("HomeTimelineFragment")
			.setTabListener(
				new FragmentTabListener<HomeTimelineFragment>(R.id.flContainer, this, "home",
								HomeTimelineFragment.class));

		actionBar.addTab(tab1);
		actionBar.selectTab(tab1);

		Tab tab2 = actionBar
			.newTab()
			.setText("Second")
			.setIcon(R.drawable.ic_mentions)
			.setTag("MentionsTimelineFragment")
			.setTabListener(
			    new FragmentTabListener<MentionsTimelineFragment>(R.id.flContainer, this, "mentions",
								MentionsTimelineFragment.class));

		actionBar.addTab(tab2);
	}
	
//	// Append more data into the adapter
//	public void customLoadMoreDataFromApi(long max_id) {
//		populateMoreTimeline(max_id);
//	}

	


//	public void postTweet(String tweet) {
//		client.postTweet((new JsonHttpResponseHandler() {
//			@Override
//			public void onSuccess(JSONObject json) {
//				populateTimeline();
//			}
//
//			@Override
//			public void onFailure(Throwable e, String s) {
//				Log.d("debug", e.toString());
//				Log.d("debug", s.toString());
//			}
//		}), tweet);
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onComposeAction(MenuItem mi) {
		Intent composeIntent = new Intent(this, ComposeActivity.class);
		startActivityForResult(composeIntent, REQUEST_CODE);
	}

	private final int REQUEST_CODE = 20;
	private final int RESULT_OK = 200;

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent composeIntent) {
		// REQUEST_CODE is defined above
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
			String tweet = composeIntent.getExtras().getString("newTweet");
//			postTweet(tweet);
		}
	}
}
