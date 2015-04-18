package com.karamagi.holysong;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockListFragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class HSListFragment extends SherlockListFragment {
	
	String holySongTitles[];
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		HolySongAdapter adapter = new HolySongAdapter(getActivity());
		holySongTitles = getResources().getStringArray(R.array.holysongs);
		for (int i = 0; i < 40; i++) {
			adapter.add(new HolySong(holySongTitles[i],String.valueOf(i+1)));
		}
		setListAdapter(adapter);
		
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		SherlockFragment newContent = null;
		//TextView tv = (TextView)v.findViewById(R.id.holy_song_title);
		
		newContent = new HolySongFragment(String.valueOf(position+1));
		HolySong song = (HolySong)l.getItemAtPosition(position);
		switchFragment(newContent, song.hs_title);
	
	}
	
	private void switchFragment(SherlockFragment fragment, String hs_title) {
		if (getActivity() == null)
			return;
		
		if (getActivity() instanceof MainActivity) {
			MainActivity ma = (MainActivity) getActivity();
			ma.switchContent(fragment , hs_title);
		} 
	}

	private class HolySong {
		private String hs_title;
		private String hs_number;
	
		public HolySong(String title, String number) {
			this.hs_title = title;
			this.hs_number = number;
		}
	
		public void setTitle(String title){
			this.hs_title = title;
		}
	
		public void setNumber(String number){
			this.hs_number = number;
		}
	
		public String getTitle(){
			return hs_title;
		}
	
		public String getNumber(){
			return hs_number;
		}	
	}

	public class HolySongAdapter extends ArrayAdapter<HolySong> {

		public HolySongAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, null);
			}
			TextView icon = (TextView) convertView.findViewById(R.id.row_icon);
			icon.setText(getItem(position).hs_number);
			TextView title = (TextView) convertView.findViewById(R.id.row_title);
			title.setText(getItem(position).hs_title);

			return convertView;
		}

	}
}
