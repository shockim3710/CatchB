package com.example.intro.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.intro.mainpage.MainPage;
import com.example.intro.R;
import com.example.intro.retrofit2.RetrofitClient;
import com.example.intro.store.Store;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyPage extends AppCompatActivity {
    private RetrofitClient retrofitClient;
    private com.example.intro.retrofit2.initMyApi initMyApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_page);
        MyPageResponse();
}
    public void MyPageResponse() {
        Intent intent = getIntent();
        String userID = intent.getStringExtra("user_id");
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        Call<MypageResponse> call = initMyApi.getMypageResponse(userID);
        call.enqueue(new Callback<MypageResponse>() {
            @Override
            public void onResponse(Call<MypageResponse> call, Response<MypageResponse> response) {
                if (response.isSuccessful()) {
                    MypageResponse mypageResponse = response.body();

                    TextView textView = (TextView) findViewById(R.id.get_id);
                    textView.setText(mypageResponse.getUser_id2());
                    TextView textView2 = (TextView) findViewById(R.id.get_nickname);
                    textView2.setText(mypageResponse.getUser_nickname());
                    TextView textView3 = (TextView) findViewById(R.id.get_credit);
                    if (mypageResponse.getUser_credit() == 0)
                        textView3.setText("0");
                    else
                        textView3.setText(Long.toString(mypageResponse.getUser_credit()));
                }
            }
            @Override
            public void onFailure(Call<MypageResponse> call, Throwable t) {
                Log.d("불러오기", "실패");
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPage.this);
                builder.setTitle("알림")
                        .setMessage("예기치 못한 오류가 발생.")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
            }
        });
    }

    public void LogoClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(MyPage.this, MainPage.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        MyPage.this.finish();
    }
    public void StoreClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(MyPage.this, Store.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        MyPage.this.finish();
    }

    public void wishListClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(MyPage.this, WishList.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        MyPage.this.finish();
    }

    public void creditHistoryClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(MyPage.this, CreditHistory.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        MyPage.this.finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(MyPage.this, MainPage.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        finish();   //현재 액티비티 종료
    }
}