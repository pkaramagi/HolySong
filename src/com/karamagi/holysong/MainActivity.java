package com.karamagi.holysong;

import android.content.Intent;
import android.os.Bundle;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


public class MainActivity extends SherlockFragmentActivity {

	private SlidingMenu slidingMenu;
	private SherlockFragment hsContent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// set the Above View
		Intent intent = getIntent();
		int hs_number = intent.getIntExtra("hs_number", 1);
		String title = getResources().getStringArray(R.array.holysongs)[hs_number-1];
		setTitle(title);
		//TextView webview = (TextView) findViewById(R.id.textpk);
		//webview.setMovementMethod(new ScrollingMovementMethod());
		//webview.setText(Html.fromHtml(readTxt()));
		//webview.loadUrl("file:///android_asset/1.html");
		if (savedInstanceState != null)
			hsContent = (SherlockFragment)getSupportFragmentManager().getFragment(savedInstanceState, "hsContent");
		if (hsContent == null)
			hsContent = new HolySongFragment(String.valueOf(hs_number));
		
		setContentView(R.layout.activity_main);
		
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, hsContent)
		.commit();
		
		// configure the SlidingMenu
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setShadowWidthRes(R.dimen.slidingmenu_shadow_width);
		slidingMenu.setBehindWidthRes(R.dimen.slidingmenu_offset);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.slidingmenu);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame, new HSListFragment())
		.commit();
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "hsContent", hsContent);
	}
	
	public void switchContent(SherlockFragment fragment, String title) {
		hsContent = fragment;
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, fragment)
		.commit();
		slidingMenu.showContent();
		getSupportActionBar().setTitle(title);
		
		
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			slidingMenu.toggle();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if (slidingMenu.isMenuShowing()) {
			slidingMenu.showContent();
		} else {
			super.onBackPressed();
		}
	}
	
	

}
