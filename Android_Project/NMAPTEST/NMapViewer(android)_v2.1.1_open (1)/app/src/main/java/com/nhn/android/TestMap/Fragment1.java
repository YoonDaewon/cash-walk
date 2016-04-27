package com.nhn.android.TestMap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhn.android.mapviewer.R;

public class Fragment1 extends NMapFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(com.android.application.R.layout.fragment1, container, false);
	}
}
