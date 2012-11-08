package com.t3hh4xx0r.techfoolerysoundboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.viewpagerindicator.TitlePageIndicator;


public class MainActivity extends SherlockFragmentActivity {
    ViewPager pager;
    static int p;
    public int account;    
	int place;
	
	//Step 1.
	//Add all the hosts names youd like to have in the app here.	
	String host_names[] = {"Beavis", "Butthead"};
	
	//Step 2.
	//For each host name, add an image for each tile.
	public static final int host_tile_images_beavis[] = {R.drawable.ic_action_search, R.drawable.ic_launcher};
	public static final int host_tile_images_butthead[] = {R.drawable.ic_action_search, R.drawable.ic_launcher};
	
	//Step 3.
	//Add each of the lists from step 2 here.
	public static final int host_tile_images_all[][] = { host_tile_images_beavis, host_tile_images_butthead};
	
	//Step 4.
	//Set all the mp3 files for the soundboard here.
	public static final String host_tracks_beavis[] = {"track_1.mp3", "track2.mp3"};
	public static final String host_tracks_butthead[] = {"track_1.mp3", "track2.mp3"};
	
	//Step 5
	//Add all the lists from step 4 here
	public static final String host_tracks_all[][] = { host_tracks_beavis, host_tracks_butthead};

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_main);
	   		
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new ExamplePagerAdapter(getSupportFragmentManager()));
        TitlePageIndicator indicator = (TitlePageIndicator)findViewById(R.id.indicator);
        indicator.setOnPageChangeListener(new OnPageChangeListener() {
        	@Override
        	public void onPageSelected(int position) {  
        		p = position;
    	    	setTitle(host_names[p]);
        	}

			@Override
			public void onPageScrollStateChanged(int arg0) {				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {	
				if (BoardFragment.player.isPlaying()) {
					BoardFragment.player.stop();
				}
			}
        });
        
        pager.setCurrentItem(0);
    	setTitle(host_names[0]);
        indicator.setViewPager(pager, 0);
	}
	
	public class ExamplePagerAdapter extends FragmentPagerAdapter {

		public ExamplePagerAdapter(FragmentManager fm) {
	       super(fm);
		}
			
		@Override
		public int getCount() {
		    return host_names.length;
		}
		
		@Override
		public Fragment getItem(int position) {
		    Fragment fragment = new BoardFragment();	
		    Bundle b = new Bundle(); 
		    b.putInt("p", position); 
		    fragment.setArguments(b);
		    return fragment;
	    }
		
	    @Override
	    public CharSequence getPageTitle(int position) {
	    	return host_names[position];
	    }
		
	}
}
