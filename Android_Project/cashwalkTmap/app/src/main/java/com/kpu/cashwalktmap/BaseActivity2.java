package com.kpu.cashwalktmap;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by ydwin on 2016-08-12.
 */
public class BaseActivity2 extends Activity implements View.OnClickListener {

    private RelativeLayout contentView = null;
    private static Context mCtx2 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.setContentView(R.layout.activity_time);
        mCtx2 = this;

        contentView  = (RelativeLayout)findViewById(R.id.contentView2);


        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    @Override
    public void setContentView(int res)  {

        contentView.removeAllViews();

        LayoutInflater inflater;
        inflater = LayoutInflater.from(this);

        View item = inflater.inflate(res, null);
        contentView.addView(item, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

    }



    @Override
    public void setContentView(View view) {

        contentView.removeAllViews();

        contentView.addView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

    }


    public void addView(View v)
    {
        contentView.removeAllViews();
        contentView.addView(v, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
    }



    @Override
    public void onClick(View v) {

    }
}

