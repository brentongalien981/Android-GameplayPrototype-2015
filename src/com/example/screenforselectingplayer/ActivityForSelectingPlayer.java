package com.example.screenforselectingplayer;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.example.prototype1.R;

public class ActivityForSelectingPlayer extends FragmentActivity implements
		Button.OnClickListener {

	private static final int NUM_PAGES = 2;
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;

	private Context context;
	private ArrayList<Player> playerArr;
	private ArrayAdapter<Player> arrAdapter;
	private ImageView iv;
	private Button buttonGoFight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_for_selecting_player);

		context = this;
		iv = (ImageView) findViewById(R.id.imageView_of_selected_player);

		setPlayerArr();
		setArrAdapter();
		setButtons();

		// Instantiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);

		mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				invalidateOptionsMenu();
			}
		});
	}

	private void setButtons() {
		buttonGoFight = (Button) findViewById(R.id.button_goFight);
		buttonGoFight.setOnClickListener(this);

	}

	// Inner-class
	private class MyGridAdapter extends ArrayAdapter<Player> {

		// Constructor
		public MyGridAdapter() {
			super(ActivityForSelectingPlayer.this, R.layout.items_of_grid_view, playerArr);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View itemView = convertView;
			// If convertView was null,
			if (itemView == null) {
				// then we manually assign item_view layout to itemView object.
				itemView = getLayoutInflater().inflate(
						R.layout.items_of_grid_view, parent, false);
			}

			// Get the index of the player that was tapped.
			Player currentPlayer = playerArr.get(position);

			ImageView imageView = (ImageView) itemView
					.findViewById(R.id.item_imageView);
			imageView.setImageResource(currentPlayer.getPlayerImageId());

			return itemView;
		}

	}

	private void setArrAdapter() {
		arrAdapter = new MyGridAdapter();

	}

	private void setPlayerArr() {
		playerArr = new ArrayList<Player>();

		playerArr.add(new Player("ai", R.drawable.ai));
		playerArr.add(new Player("kobe", R.drawable.kobe));
		playerArr.add(new Player("tmace", R.drawable.tmac));
		playerArr.add(new Player("ai", R.drawable.ai));
		playerArr.add(new Player("kobe", R.drawable.kobe));
		playerArr.add(new Player("tmace", R.drawable.tmac));
		playerArr.add(new Player("ai", R.drawable.ai));
		playerArr.add(new Player("kobe", R.drawable.kobe));
		playerArr.add(new Player("tmace", R.drawable.tmac));

	}

	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return ScreenSlidePageFragment.create(context, playerArr,
					arrAdapter, position, iv);
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_goFight:
			sendToGameplay();
			break;
		}
		
	}

	public void sendToGameplay() {
		Intent intent = new Intent(this, com.example.screenformaingameplayv3.GameplayMainActivity.class);
		startActivity(intent);
		//Toast.makeText(this, "send to gameplay", Toast.LENGTH_LONG).show();
	}
}

