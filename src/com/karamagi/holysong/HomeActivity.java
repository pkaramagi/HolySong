package com.karamagi.holysong;

import com.actionbarsherlock.app.SherlockActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeActivity extends SherlockActivity {
	HSListFragment hslist;
	String holySongTitles[];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		setTitle(R.string.app_name);
		holySongTitles = getResources().getStringArray(R.array.holysongs);
		for (int i = 0; i < 40; i++) {
			holySongTitles[i] = String.valueOf(i+1)+". "+holySongTitles[i];
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.home_list_item,holySongTitles);
		
		ListView lv = (ListView)findViewById(R.id.home_list_view);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent intent = new Intent(getApplicationContext(), com.karamagi.holysong.MainActivity.class);
				intent.putExtra("hs_number", position+1);
				startActivity(intent);
			}
		});
		
	}
}
