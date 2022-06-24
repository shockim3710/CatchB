package com.example.intro.store;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.intro.R;
import com.example.intro.mainpage.MainPage;
import com.example.intro.mypage.MyPage;
import com.example.intro.mypage.MypageResponse;
import com.example.intro.retrofit2.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class etc_layout extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private com.example.intro.retrofit2.initMyApi initMyApi;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etc_layout);

        StoreResponse();
    }
    public void StoreResponse() {
        Intent intent = getIntent();
        String userID = intent.getStringExtra("user_id");

        //retrofit 생성
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        Call<MypageResponse> call = initMyApi.getMypageResponse(userID);
        call.enqueue(new Callback<MypageResponse>() {
            @Override
            public void onResponse(Call<MypageResponse> call, Response<MypageResponse> response) {
                if (response.isSuccessful()) {
                    MypageResponse mypageResponse = response.body();

                    TextView textView2 = (TextView) findViewById(R.id.textView4);
                    textView2.setText(mypageResponse.getUser_nickname());
                    TextView textView3 = (TextView) findViewById(R.id.textView5);
                    if (mypageResponse.getUser_credit() == 0)
                        textView3.setText("0");
                    else
                        textView3.setText(Long.toString(mypageResponse.getUser_credit()));
                }
            }

            @Override
            public void onFailure(Call<MypageResponse> call, Throwable t) {
                Log.d("불러오기", "실패");
                AlertDialog.Builder builder = new AlertDialog.Builder(etc_layout.this);
                builder.setTitle("알림")
                        .setMessage("예기치 못한 오류가 발생.")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
            }
        });
    }

    //<------------------------------인텐트-------------------------------------------------->
    public void clickConvi(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(etc_layout.this, convi_layout.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        etc_layout.this.finish();
    }

    public void clickGift(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(etc_layout.this, gift_layout.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        etc_layout.this.finish();
    }

    public void clickGifticon(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(etc_layout.this, gifticon_layout.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        etc_layout.this.finish();
    }

    public void clickStorage(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(etc_layout.this, storage_layout.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        etc_layout.this.finish();
    }

    public void clickStore(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(etc_layout.this, Store.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        etc_layout.this.finish();
    }

    public void LogoClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(etc_layout.this, MainPage.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        etc_layout.this.finish();
    }
    public void MypageClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(etc_layout.this, MyPage.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        etc_layout.this.finish();
    }

    public void onBackPressed() {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(etc_layout.this, MainPage.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        finish();   //현재 액티비티 종료
    }


}
