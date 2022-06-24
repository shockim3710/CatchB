package com.example.intro.mainpage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.intro.R;

//여수 관련
public class SubLayout2 extends LinearLayout {
    public SubLayout2(Context context, AttributeSet attrs, Yeo_su yeo_su){
        super(context, attrs);
        init(context, yeo_su);

    }
    public SubLayout2(Context context, Yeo_su yeo_su){
        super(context);
        init(context, yeo_su);
    }

    private void init(Context context, Yeo_su yeo_su){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_sub2, this,true);

        ImageView img = (ImageView) findViewById(R.id.ivv1);

        Glide.with(this).load(yeo_su.getImageUrl().toString())
                .override(300,300)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(img);
    }
}

