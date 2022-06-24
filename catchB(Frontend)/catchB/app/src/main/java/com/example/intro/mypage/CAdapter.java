package com.example.intro.mypage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.intro.R;

import java.util.ArrayList;

public class CAdapter extends BaseAdapter {
    private ArrayList<CreditHistoryDTO> listCT = new ArrayList<>();

    @Override
    public  int getCount() {return listCT.size();}

    @Override
    public Object getItem(int position) {return listCT.get(position);}

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        CustomViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);

            holder = new CustomViewHolder();
            holder.historyname = (TextView) convertView.findViewById(R.id.text_historyname);
            holder.credit = (TextView) convertView.findViewById(R.id.text_credit);
            holder.credit_info = (TextView) convertView.findViewById(R.id.text_credit_info);
            convertView.setTag(holder);
        }else{
            holder = (CustomViewHolder) convertView.getTag();
        }
        CreditHistoryDTO dto1 = listCT.get(position);

        holder.historyname.setText(dto1.getContent());
        holder.credit.setText(dto1.getCredit());
        holder.credit_info.setText(dto1.getCredit_info());

        return convertView;
    }

    class CustomViewHolder{
        TextView historyname;
        TextView credit;
        TextView credit_info;
    }
    public void addItem(CreditHistoryDTO dto) {
        listCT.add(dto);
    }
}

