package com.example.intro.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.intro.R;
import com.example.intro.mainpage.MainPage;
import com.example.intro.mypage.MyPage;
import com.example.intro.retrofit2.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class storage_layout extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private com.example.intro.retrofit2.initMyApi initMyApi;
    private storageAdapter adapter;
    private ListView listView;

    ArrayList<String> storge_image_id = new ArrayList<>();
    ArrayList<String> storge_title_id = new ArrayList<>();
    ArrayList<String> storge_credit_id = new ArrayList<>();

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storage_layout);

        adapter = new storageAdapter();
        listView = (ListView) findViewById(R.id.listView3);

        StoreResponse();
        listView.setAdapter(adapter);
    }
    public void StoreResponse() {
        Intent intent = getIntent();
        String userID = intent.getStringExtra("user_id");

        //retrofit 생성
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        Call<List<storageResponse>> call = initMyApi.getStorage(userID);
        call.enqueue(new Callback<List<storageResponse>>() {
            @Override
            public void onResponse(Call<List<storageResponse>> call, Response<List<storageResponse>> response) {
                List<storageResponse> storageResponses = response.body();
                for(storageResponse storageResponse : storageResponses){
                    storge_image_id.add("http://211.108.193.21:8080/api/store/view?item_category=" + storageResponse.item_category +
                            "&fileName=" + storageResponse.file_name);
                    storge_title_id.add(storageResponse.item_name);
                    storge_credit_id.add(storageResponse.item_credit);

                }
                for(int i=0; i<storge_title_id.size();i++){
                    storageDTO dto2 =new storageDTO();

                    dto2.setGift_pic(storge_image_id.get(i));
                    dto2.setGift_title(storge_title_id.get(i));
                    dto2.setGift_credit(storge_credit_id.get(i));

                    adapter.addItem1(dto2);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<storageResponse>> call, Throwable t) {

            }
        });

    }

    //------------------------------인텐트--------------------------------------------------
    public void clickConvi(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(storage_layout.this, convi_layout.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        storage_layout.this.finish();
    }

    public void clickGift(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(storage_layout.this, gift_layout.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        storage_layout.this.finish();
    }

    public void clickGifticon(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(storage_layout.this, gifticon_layout.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        storage_layout.this.finish();
    }

    public void clickEtc(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(storage_layout.this, etc_layout.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        storage_layout.this.finish();
    }


    public void clickStore(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(storage_layout.this, Store.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        storage_layout.this.finish();
    }
    public void clickStorage(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(storage_layout.this, storage_layout.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        storage_layout.this.finish();
    }

    public void LogoClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(storage_layout.this, MainPage.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        storage_layout.this.finish();
    }
    public void MypageClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(storage_layout.this, MyPage.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        storage_layout.this.finish();
    }

    public void onBackPressed() {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(storage_layout.this, Store.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        finish();   //현재 액티비티 종료
    }


}