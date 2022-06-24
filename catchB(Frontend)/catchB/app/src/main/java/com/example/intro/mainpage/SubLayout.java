package com.example.intro.mainpage;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.intro.R;

//부산 관련 이미지를 여기서 비트맵으로 만들어서 레이아웃에 박아줄 틀작성
public class SubLayout extends LinearLayout {

    public SubLayout(Context context, AttributeSet attrs, Busanitem busanitem){
        super(context, attrs);
        init(context, busanitem);

    }
    public SubLayout(Context context, Busanitem busanitem){
        super(context);
        init(context, busanitem);
    }

    private void init(Context context, Busanitem busanitem){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_sub, this,true);

        ImageView img = (ImageView) findViewById(R.id.iv1);
        Glide.with(this).load(busanitem.getImageUrl())
                .override(300,300)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(img);
    }
}
