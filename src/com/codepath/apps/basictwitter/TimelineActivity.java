package com.codepath.apps.basictwitter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.apps.basictwitter.fragments.HomeTimelineFragment;
import com.codepath.apps.basictwitter.fragments.MentionsTimelineFragment;
import com.codepath.apps.basictwitter.listeners.FragmentTabListener;

public class TimelineActivity extends FragmentActivity {
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		actionBar = getActionBar();
		setupTabs();
	}

	private void setupTabs() {
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		Tab tab1 = actionBar
				.newTab()
				.setText("Home")
				.setIcon(R.drawable.ic_home)
				.setTag("HomeTimelineFragment")
				.setTabListener(
						new FragmentTabListener<HomeTimelineFragment>(
								R.id.flContainer, this, "home",
								HomeTimelineFragment.class));

		actionBar.addTab(tab1);
		actionBar.selectTab(tab1);

		Tab tab2 = actionBar
				.newTab()
				.setText("Mentions")
				.setIcon(R.drawable.ic_mentions)
				.setTag("MentionsTimelineFragment")
				.setTabListener(
						new FragmentTabListener<MentionsTimelineFragment>(
								R.id.flContainer, this, "mentions",
								MentionsTimelineFragment.class));

		actionBar.addTab(tab2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline_menu, menu);
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

	private boolean composeIntentHasResult = false;

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent composeIntent) {
		Toast.makeText(this, "onActivityResult", Toast.LENGTH_LONG).show();
		Log.d("VK", "Inside onActivityResult");

		// REQUEST_CODE is defined above
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
			// Postpone doing Fragment Transactions
			Log.d("VK", "Setting compostResult to TRUE");
			composeIntentHasResult = true;
		}
	}

	// Called by onResume that's called by on ActivityResult
	@Override
	protected void onPostResume() {
		Log.d("VK", "Inside onPostResume");

		super.onPostResume();
		if (composeIntentHasResult == true) {
			refreshTab();
		}
		// Reset the boolean flag back to false for next time.
		composeIntentHasResult = false;
	}

	private void refreshTab() {

		
//		HomeTimelineFragment homeTimeLineFragment = (HomeTimelineFragment) getSupportFragmentManager()
//				.findFragmentByTag("HomeTimelineFragment");
//		Log.d("VK", "got fragment");
//
//		sft.detach(homeTimeLineFragment);
//		sft.commit();
//
//		Log.d("VK", "detached");
//		FragmentTransaction sft1 = getSupportFragmentManager()
//				.beginTransaction();
//		
//		sft1.attach(homeTimeLineFragment);
//		sft1.commit();
//
//		Log.d("VK", "attached");
		// if (actionBar.getSelectedTab().getTag().toString()
		// .equals("HomeTimelineFragment")) {
		// HomeTimelineFragment homeTimeLineFragment = (HomeTimelineFragment)
		// getSupportFragmentManager()
		// .findFragmentByTag("HomeTimelineFragment");
		//
		// sft.detach(homeTimeLineFragment);
		// sft.attach(homeTimeLineFragment);
		// } else {
		// MentionsTimelineFragment mentionsTimelineFragment =
		// (MentionsTimelineFragment) getSupportFragmentManager()
		// .findFragmentByTag("MentionsTimelineFragment");
		// sft.detach(mentionsTimelineFragment);
		// sft.attach(mentionsTimelineFragment);
		// }
		//sft.commit();
	}
	
	public void onProfileView(MenuItem mi) {
		Intent profileIntent = new Intent(this, ProfileActivity.class);
		startActivity(profileIntent);
	}
}
