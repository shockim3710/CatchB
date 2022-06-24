package com.example.intro.mainpage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.intro.R;

//수원 관련
public class SubLayout3 extends LinearLayout {
    public SubLayout3(Context context, AttributeSet attrs, Suwanitem suwanitem){
        super(context, attrs);
        init(context, suwanitem);

    }
    public SubLayout3(Context context, Suwanitem suwanitem){
        super(context);
        init(context, suwanitem);
    }

    private void init(Context context, Suwanitem suwanitem){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_sub3, this,true);

        ImageView img = (ImageView) findViewById(R.id.ivvv1);

        Glide.with(this).load(suwanitem.getImageUrl().toString())
                .override(300,300)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(img);
    }
}
