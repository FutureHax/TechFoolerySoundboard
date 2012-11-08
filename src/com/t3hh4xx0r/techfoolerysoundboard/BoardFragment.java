package com.t3hh4xx0r.techfoolerysoundboard;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;

public class BoardFragment extends SherlockFragment {
	  Context ctx;
	  View v;
	  int pos;
	  private GridView grid;
	  static MediaPlayer player;
	  
	@Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		    ctx = container.getContext();
		    if (v != null) {
		        v.invalidate();
		    }
		    		    
		    v = inflater.inflate(R.layout.host_fragment, container, false);
			grid = (GridView) v.findViewById(R.id.grid);
			TileAdapter a = new TileAdapter(ctx);
			grid.setAdapter(a);
		    pos = getArguments().getInt("p");
		    return v;
	}
	  
		@Override
		public void onStart() {
			super.onStart();
		}
		
		@Override
		public void onResume() {
			super.onResume();
		}	
		
		public void onListItemClick(ListView lv, View v, int p, long id) {	

		}
		
		public class TileAdapter extends BaseAdapter {
			Context ctx;

			public TileAdapter(Context c) {
				ctx = c;
			}

			@Override
			public int getCount() {
				return MainActivity.host_tile_images_all[pos].length;
			}

			@Override
			public String getItem(int position) {
				return MainActivity.host_tracks_all[pos][position];				
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			public View getView(final int position, View convertView,
					ViewGroup parent) {				
				ImageView i = new ImageView(ctx);
				i.setImageResource(MainActivity.host_tile_images_all[pos][position]);
				convertView = i;
				
				int[] cellGrid = getCellGrid(ctx);
				convertView.setLayoutParams(new GridView.LayoutParams(cellGrid[0],
						cellGrid[1]));
								
				convertView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(final View v) {
						AssetFileDescriptor afd;
						try {
							if (player != null &&
									player.isPlaying()) {
								player.stop();
							}
							afd = v.getContext().getAssets().openFd(getItem(position));
							player = new MediaPlayer();
						    player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
						    player.prepare();
						    player.start();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});				
				return convertView;
			}
		}		
		
		@SuppressWarnings("deprecation")
		private int[] getCellGrid(Context c) {
			WindowManager wm = (WindowManager) c
					.getSystemService(Context.WINDOW_SERVICE);
			int width = wm.getDefaultDisplay().getWidth();
			int[] cell = { (width / 2) - 15, (width / 2) };
			return cell;
		}
}
