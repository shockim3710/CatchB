package com.example.intro.touchpicture;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.intro.R;
import com.example.intro.retrofit2.RetrofitClient;


import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private RetrofitClient retrofitClient;
    private com.example.intro.retrofit2.initMyApi initMyApi;

    String comment_user_id;
    String comment_des;
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<Listview> listViewItemList = new ArrayList<>() ;

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, null, false);

            holder = new CustomViewHolder();
            holder.iconImageView = (ImageView) convertView.findViewById(R.id.comment_usericon);
            holder.nicknameTextView = (TextView) convertView.findViewById(R.id.comment_nickname);
            holder.commentTextView = (TextView) convertView.findViewById(R.id.comment_text);
            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Listview listViewItem = listViewItemList.get(position);

        holder.iconImageView.setImageResource(listViewItem.getIcon());
        holder.nicknameTextView.setText(listViewItem.getNickname());
        holder.commentTextView.setText(listViewItem.getContents());

        return convertView;
    }



    class CustomViewHolder {
        ImageView iconImageView;
        TextView nicknameTextView;
        TextView commentTextView;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem1(Listview listViewItem) {
        listViewItemList.add(listViewItem);
    }

}