package com.karamagi.holysong;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.actionbarsherlock.app.SherlockFragment;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HolySongFragment extends SherlockFragment {
	
	private String hs_number  = "1";
	
	public HolySongFragment() { 
		this("1");
	}
	
	public HolySongFragment(String number) {
		hs_number = number;
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (savedInstanceState != null)
			hs_number = savedInstanceState.getString("hs_number");
		// construct the RelativeLayout
		LinearLayout v = new LinearLayout(getActivity());		
		//v.setBackgroundColor(getResources().getColor(R.color.purple_light));
		TextView view = new TextView(container.getContext());
		//System.out.print(readTxt("1"));
		view.setText(Html.fromHtml(readTxt(hs_number)));
		view.setTextColor(getResources().getColor(R.color.almost_black));
		view.setTextSize(18);
		view.setId(20);
		view.setMovementMethod(new ScrollingMovementMethod());
		view.setPadding(10, 0, 10, 0);
		//H
		//view.setMovementMethod(new ScrollingMovementMethod());
		v.addView(view);
		return v;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("hs_number", hs_number);
	}
	
	private String readTxt(String number){

	     String url = number+".html";
	     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	     int i;
	  try {
		  InputStream inputStream = getResources().getAssets().open(url);
	   i = inputStream.read();
	   while (i != -1)
	      {
	       byteArrayOutputStream.write(i);
	       i = inputStream.read();
	      }
	      inputStream.close();
	  } catch (IOException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }

	     return byteArrayOutputStream.toString();
	    }
		
	}	

