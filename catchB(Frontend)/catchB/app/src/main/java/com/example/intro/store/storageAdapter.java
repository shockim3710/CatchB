package com.example.intro.store;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intro.R;
import com.example.intro.retrofit2.RetrofitClient;

import java.util.ArrayList;

public class storageAdapter extends BaseAdapter {

    private ArrayList<storageDTO> listCustom = new ArrayList<>();
    private RetrofitClient retrofitClient;
    private com.example.intro.retrofit2.initMyApi initMyApi;

    // ListView에 보여질 Item 수
    @Override
    public int getCount() {
        return listCustom.size();
    }

    // 하나의 Item(ImageView 1, TextView 2)
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Item의 id : Item을 구별하기 위한 것으로 position 사용
    @Override
    public Object getItem(int position) {
        return listCustom.get(position);
    }

    // 실제로 Item이 보여지는 부분
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.storage_listview, null, false);

            holder = new CustomViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.gift_pic);
            holder.textTitle = (TextView) convertView.findViewById(R.id.gift_title);
            holder.textContent = (TextView) convertView.findViewById(R.id.gift_credit);
            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }
        storageDTO dto1 = listCustom.get(position);
        holder.textTitle.setText(dto1.getGift_title());
        holder.textContent.setText(dto1.getGift_credit());
        Glide.with(convertView).load(dto1.getGift_pic()).override(250,150).into(holder.imageView);
        notifyDataSetChanged();
        return convertView;
    }

    class CustomViewHolder {
        ImageView imageView;
        TextView textTitle;
        TextView textContent;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem1(storageDTO dto1) {
        listCustom.add(dto1);
    }
}
