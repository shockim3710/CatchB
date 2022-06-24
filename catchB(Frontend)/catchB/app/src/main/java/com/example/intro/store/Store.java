package com.example.intro.store;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intro.R;
import com.example.intro.mainpage.MainPage;
import com.example.intro.mypage.MyPage;
import com.example.intro.mypage.MypageResponse;
import com.example.intro.retrofit2.RetrofitClient;
import com.example.intro.touchpicture.HistoryRequest;
import com.example.intro.touchpicture.HistoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Store extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private com.example.intro.retrofit2.initMyApi initMyApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);
        Intent intent = getIntent();
        String userID = intent.getStringExtra("user_id");
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();
        String item_name = "신라면";
        int item_credit = 100;
        TextView textView = (TextView) findViewById(R.id.getcount);

        StoreResponse();
        ImageView imageView = (ImageView) findViewById(R.id.cu_cupramen);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView3 = (TextView) findViewById(R.id.textView5);
                int credit = Integer.parseInt(textView3.getText().toString());
                String temp_count = textView.getText().toString().replaceAll("[^0-9]", "");
                int stock = Integer.parseInt(temp_count);
                if (credit >= item_credit && stock != 0) {
                    StoreDto dto = new StoreDto();
                    Call<MypageResponse> call3 = initMyApi.purchasegift(item_credit, userID);
                    call3.enqueue(new Callback<MypageResponse>() {
                        @Override
                        public void onResponse(Call<MypageResponse> call, Response<MypageResponse> response) {

                        }

                        @Override
                        public void onFailure(Call<MypageResponse> call, Throwable t) {

                        }
                    });
                    Call<StoreResponse> call = initMyApi.usegift(userID, item_name);
                    call.enqueue(new Callback<StoreResponse>() {
                        @Override
                        public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {

                        }

                        @Override
                        public void onFailure(Call<StoreResponse> call, Throwable t) {

                        }
                    });
                    HistoryRequest historyRequest = new HistoryRequest(userID, item_name, "-" + Integer.toString(item_credit), "구매");
                    Call<HistoryResponse> call4 = initMyApi.addHistroy(historyRequest);
                    call4.enqueue(new Callback<HistoryResponse>() {
                        @Override
                        public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                            Toast.makeText(getBaseContext(), "구매성공!", Toast.LENGTH_SHORT).show();
                            StoreResponse();
                        }

                        @Override
                        public void onFailure(Call<HistoryResponse> call, Throwable t) {

                        }
                    });

                }
                else if(stock == 0){
                    Toast.makeText(getBaseContext(),"현재 상품의 재고가 없습니다.",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getBaseContext(),"보유한 크레딧이 부족합니다.",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void StoreResponse() {
        Intent intent = getIntent();
        String userID = intent.getStringExtra("user_id");
        countStock();

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
                    textView3.setText(Integer.toString(mypageResponse.getUser_credit()));
                }
            }

            @Override
            public void onFailure(Call<MypageResponse> call, Throwable t) {
                Log.d("불러오기", "실패");
                AlertDialog.Builder builder = new AlertDialog.Builder(Store.this);
                builder.setTitle("알림")
                        .setMessage("예기치 못한 오류가 발생.")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
            }
        });

    }
    public void countStock(){
        ImageView imageView = (ImageView) findViewById(R.id.cu_cupramen);
        TextView textView = (TextView) findViewById(R.id.getcount);
        Call<StoreResponse> call2 = initMyApi.checkStock("신라면");
        call2.enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                StoreResponse storeResponse = response.body();
                int aa = storeResponse.getCount();
                textView.setText("현재 재고 : "+Integer.toString(aa));
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {

            }
        });
    }

    //--------------------------------------------------인텐트--------------------------------------------------
    public void clickStorage(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(Store.this, storage_layout.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        Store.this.finish();
    }

    public void clickStore(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(Store.this, Store.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        Store.this.finish();
    }

    public void LogoClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(Store.this, MainPage.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        Store.this.finish();
    }
    public void MypageClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(Store.this, MyPage.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        Store.this.finish();
    }
    public void onBackPressed() {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(Store.this, MainPage.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        finish();   //현재 액티비티 종료
    }

    //--------------------------------------------------인텐트--------------------------------------------------

}