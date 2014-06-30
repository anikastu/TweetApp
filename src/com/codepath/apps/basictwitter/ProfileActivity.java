package com.codepath.apps.basictwitter;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.codepath.apps.basictwitter.fragments.UserProfileFragment;
import com.codepath.apps.basictwitter.fragments.UserTimelineFragment;
import com.codepath.apps.basictwitter.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

public class ProfileActivity extends FragmentActivity {

	private UserTimelineFragment userTimelineFragment;
	private UserProfileFragment userProfileFragment;

	private long userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		userId = getIntent().getLongExtra("userId", 0);
		loadProfileInfo();
		loadAllFragments();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	public void loadProfileInfo() {
		TwitterClientApp.getRestClient().verifyCredentials(
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject json) {
						User u = User.fromJSON(json);
						getActionBar().setTitle("@" + u.getScreenName());
					}
				});
	}

	private void loadAllFragments() {
		// create the fragments
		userProfileFragment = new UserProfileFragment();
		userProfileFragment.setUserId(userId);
		userTimelineFragment = new UserTimelineFragment();
		userTimelineFragment.setUserId(userId);

		// add fragments to activity
		android.support.v4.app.FragmentTransaction sft = getSupportFragmentManager()
				.beginTransaction();
		sft.add(R.id.flUserProfileContainer, userProfileFragment);
		sft.add(R.id.flUserTimelineContainer, userTimelineFragment);
		sft.commit();
	}
}
