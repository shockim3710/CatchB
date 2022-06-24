package com.example.catchb_manager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomAdapter extends BaseAdapter {
    private RetrofitClient retrofitClient;
    private initMyApi initMyApi;
    private ArrayList<CustomDTO> listCustom = new ArrayList<>();

    // ListView에 보여질 Item 수
    @Override
    public int getCount() {
        return listCustom.size();
    }

    // 하나의 Item(ImageView 1, TextView 2)
    @Override
    public Object getItem(int position) {
        return listCustom.get(position);
    }

    // Item의 id : Item을 구별하기 위한 것으로 position 사용
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 실제로 Item이 보여지는 부분
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.process_custom, null, false);

            holder = new CustomViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.textTitle = (TextView) convertView.findViewById(R.id.text_title);
            holder.textContent = (TextView) convertView.findViewById(R.id.text_content);
            holder.textCredit = (TextView) convertView.findViewById(R.id.text_credit);
            holder.btnEqual = (Button) convertView.findViewById(R.id.btn_equal);
            holder.btnUnequal = (Button) convertView.findViewById(R.id.btn_unequal);
            holder.text_hashtag = (TextView) convertView.findViewById(R.id.text_hashtag);

            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        CustomDTO dto = listCustom.get(position);

        holder.textTitle.setText(dto.getUser_id());
        holder.textContent.setText(dto.getSubmit_address());
        holder.textCredit.setText(dto.getCredit());
        holder.text_hashtag.setText(dto.getHashtag());
        Glide.with(convertView).load(dto.getImageUrl()).override(130,100).into(holder.imageView);
        // 일치 버튼클릭 후 삭제
        holder.btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                retrofitClient = RetrofitClient.getInstance();
                initMyApi = RetrofitClient.getRetrofitInterface();

                Call<SubmitResponse> call = initMyApi.processSubmit(dto.getUser_id(), dto.getHashtag());
                call.enqueue(new Callback<SubmitResponse>() {
                    @Override
                    public void onResponse(Call<SubmitResponse> call, Response<SubmitResponse> response) {
                    }
                    @Override
                    public void onFailure(Call<SubmitResponse> call, Throwable t) {
                    }
                });
                int credit = Integer.parseInt(dto.getCredit());
                Call<UserResponse> call2 = initMyApi.addCredit(credit,dto.getUser_id());
                call2.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {

                    }
                });
                String answer = "정답";
                HistoryRequest historyRequest = new HistoryRequest(dto.getUser_id(), dto.getSubmit_address(),dto.getCredit(),answer);
                Call<HistroyResponse> call3 =initMyApi.addHistroy(historyRequest);
                call3.enqueue(new Callback<HistroyResponse>() {
                    @Override
                    public void onResponse(Call<HistroyResponse> call, Response<HistroyResponse> response) {
                    }

                    @Override
                    public void onFailure(Call<HistroyResponse> call, Throwable t) {

                    }
                });
                listCustom.remove(position);
                notifyDataSetChanged();
            }







        });

        // 불일치 버튼클릭 후 삭제
        holder.btnUnequal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                retrofitClient = RetrofitClient.getInstance();
                initMyApi = RetrofitClient.getRetrofitInterface();
                Call<SubmitResponse> call = initMyApi.processSubmit(dto.getUser_id(), dto.getHashtag());
                call.enqueue(new Callback<SubmitResponse>() {
                    @Override
                    public void onResponse(Call<SubmitResponse> call, Response<SubmitResponse> response) {
                    }
                    @Override
                    public void onFailure(Call<SubmitResponse> call, Throwable t) {
                    }
                });
                String answer = "오답";
                String result_credit = "0";
                HistoryRequest historyRequest = new HistoryRequest(dto.getUser_id(), dto.getSubmit_address(),result_credit,answer);
                Call<HistroyResponse> call2 =initMyApi.addHistroy(historyRequest);
                call2.enqueue(new Callback<HistroyResponse>() {
                    @Override
                    public void onResponse(Call<HistroyResponse> call, Response<HistroyResponse> response) {
                    }

                    @Override
                    public void onFailure(Call<HistroyResponse> call, Throwable t) {

                    }
                });
                listCustom.remove(position);
                notifyDataSetChanged();
            }
        });



        return convertView;
    }

    class CustomViewHolder {
        ImageView imageView;
        TextView textTitle;
        TextView textContent;
        TextView textCredit;
        Button btnEqual;
        Button btnUnequal;
        TextView text_hashtag;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(CustomDTO dto) {
        listCustom.add(dto);
    }
}