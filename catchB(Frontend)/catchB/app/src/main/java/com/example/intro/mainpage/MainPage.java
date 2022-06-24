package com.example.intro.mainpage;

import android.app.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.intro.R;
import com.example.intro.mypage.MyPage;
import com.example.intro.retrofit2.RetrofitClient;
import com.example.intro.store.Store;
import com.example.intro.touchpicture.TouchPicture;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPage extends Activity {

    private RetrofitClient retrofitClient;
    private com.example.intro.retrofit2.initMyApi initMyApi;

    ArrayList<Busanitem> arrayList;
    ArrayList<Yeo_su> arrayList2;
    ArrayList<Suwanitem> arrayList3;

    ArrayList<String> testList;
    ArrayList<String> testList2;
    ArrayList<String> testList3;

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onStart", "onStart");
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage_activity);
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        arrayList = new ArrayList<Busanitem>(); //부산관련
        arrayList2 = new ArrayList<Yeo_su>();   //여수관련
        arrayList3 = new ArrayList<Suwanitem>();//수원관련

        testList = new ArrayList<String>();     //부산관련
        testList2 = new ArrayList<String>();    //여수관련
        testList3 = new ArrayList<String>();    //수원관련

        ArrayList<String> image_loc = new ArrayList<String>();      //지역인텐트 담기
        ArrayList<String> image_name = new ArrayList<String>();
        ArrayList<Long> image_id = new ArrayList<Long>();

        ArrayList<String> image_loc2 = new ArrayList<String>();      //지역인텐트 담기
        ArrayList<String> image_name2 = new ArrayList<String>();
        ArrayList<Long> image_id2 = new ArrayList<Long>();

        ArrayList<String> image_loc3 = new ArrayList<String>();      //지역인텐트 담기
        ArrayList<String> image_name3 = new ArrayList<String>();
        ArrayList<Long> image_id3 = new ArrayList<Long>();

        String pictures_loc = "Busan";
        String pictures_loc2 = "Yeo_su";
        String pictures_loc3 = "Suwan";

        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        //부산 이미지 받아오기
        Call<List<PicturesResponse>> call = initMyApi.getPicturesResponse(pictures_loc);
        call.enqueue(new Callback<List<PicturesResponse>>() {
            @Override
            public void onResponse(Call<List<PicturesResponse>> call, Response<List<PicturesResponse>> response) {
                if (!response.isSuccessful()) {
                    Log.d("code: ", response.toString());
                    return;
                }
                List<PicturesResponse> pictures = response.body();
                for (PicturesResponse picturesResponse : pictures) {
                    int i = 0;
                    testList.add("http://211.108.193.21:8080/api/images/view?image_address=" + picturesResponse.pictures_loc +
                            "&image_name=" + picturesResponse.pictures_name);
                    image_name.add(picturesResponse.pictures_name);
                    image_loc.add(picturesResponse.pictures_loc);
                    image_id.add(picturesResponse.pictures_id);
                }
                for (int i = 0; i < testList.size(); i++) {
                    Busanitem busanitem = new Busanitem(testList.get(i));
                    arrayList.add(busanitem);
                }
                for (int i = 0; i < arrayList.size(); i++) {
                    SubLayout subLayout = new SubLayout(getApplicationContext(), arrayList.get(i));
                    LinearLayout layout = (LinearLayout) findViewById(R.id.input_here_layout);
                    layout.addView(subLayout);
                    ImageView ivtest = (ImageView) findViewById(R.id.iv1);
                    ivtest.setId(R.id.iv2);
                    int finalI = i;
                    ivtest.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent0 = new Intent(MainPage.this, TouchPicture.class);
                            intent0.putExtra("image_name", image_name.get(finalI));
                            intent0.putExtra("image_loc",image_loc.get(finalI));
                            intent0.putExtra("image_id",image_id.get(finalI));
                            intent0.putExtra("user_id",userid);
                            startActivity(intent0);
                            MainPage.this.finish();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<List<PicturesResponse>> call, Throwable t) { }
        });

        //여수 이미지 받아오기
        Call<List<PicturesResponse>> call2 = initMyApi.getPicturesResponse(pictures_loc2);
        call2.enqueue(new Callback<List<PicturesResponse>>() {
            @Override
            public void onResponse(Call<List<PicturesResponse>> call2, Response<List<PicturesResponse>> response) {
                if (!response.isSuccessful()) {
                    Log.d("code: ", response.toString());
                    return;
                }
                List<PicturesResponse> pictures = response.body();
                for (PicturesResponse picturesResponse : pictures) {
                    int i = 0;
                    testList2.add("http://211.108.193.21:8080/api/images/view?image_address=" + picturesResponse.pictures_loc +
                            "&image_name=" + picturesResponse.pictures_name);
                    image_name2.add(picturesResponse.pictures_name);
                    image_loc2.add(picturesResponse.pictures_loc);
                    image_id2.add(picturesResponse.pictures_id);
                }
                for (int i = 0; i < testList2.size(); i++) {
                    Yeo_su yeo_su = new Yeo_su(testList2.get(i));
                    arrayList2.add(yeo_su);

                }
                for (int i = 0; i < arrayList2.size(); i++) {
                    SubLayout2 subLayout2 = new SubLayout2(getApplicationContext(), arrayList2.get(i));
                    LinearLayout layout2 = (LinearLayout) findViewById(R.id.input_here_layout2);
                    layout2.addView(subLayout2);
                    ImageView ivtest2 = (ImageView) findViewById(R.id.ivv1);
                    ivtest2.setId(R.id.ivv2);
                    int finalI = i;
                    ivtest2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent0 = new Intent(MainPage.this, TouchPicture.class);
                            intent0.putExtra("image_name", image_name2.get(finalI));
                            intent0.putExtra("image_loc",image_loc2.get(finalI));
                            intent0.putExtra("image_id",image_id2.get(finalI));
                            intent0.putExtra("user_id",userid);
                            startActivity(intent0);
                            MainPage.this.finish();

                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<List<PicturesResponse>> call2, Throwable t) {   }
        });

        //수원 이미지 받아오기
        Call<List<PicturesResponse>> call3 = initMyApi.getPicturesResponse(pictures_loc3);
        call3.enqueue(new Callback<List<PicturesResponse>>() {
            @Override
            public void onResponse(Call<List<PicturesResponse>> call3, Response<List<PicturesResponse>> response) {
                if (!response.isSuccessful()) {
                    Log.d("code: ", response.toString());
                    return;
                }
                List<PicturesResponse> pictures = response.body();
                for (PicturesResponse picturesResponse : pictures) {
                    int i = 0;
                    testList3.add("http://211.108.193.21:8080/api/images/view?image_address=" + picturesResponse.pictures_loc +
                            "&image_name=" + picturesResponse.pictures_name);
                    image_name3.add(picturesResponse.pictures_name);
                    image_loc3.add(picturesResponse.pictures_loc);
                    image_id3.add(picturesResponse.pictures_id);
                }
                for (int i = 0; i < testList3.size(); i++) {
                    Suwanitem suwanitem = new Suwanitem(testList3.get(i));
                    arrayList3.add(suwanitem);

                }
                for (int i = 0; i < arrayList3.size(); i++) {
                    SubLayout3 subLayout3 = new SubLayout3(getApplicationContext(), arrayList3.get(i));
                    LinearLayout layout3 = (LinearLayout) findViewById(R.id.input_here_layout3);
                    layout3.addView(subLayout3);
                    ImageView ivtest3 = (ImageView) findViewById(R.id.ivvv1);
                    ivtest3.setId(R.id.ivvv2);
                    int finalI = i;
                    ivtest3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent0 = new Intent(MainPage.this, TouchPicture.class);
                            intent0.putExtra("image_name", image_name3.get(finalI));
                            intent0.putExtra("image_loc",image_loc3.get(finalI));
                            intent0.putExtra("image_id",image_id3.get(finalI));
                            intent0.putExtra("user_id",userid);
                            startActivity(intent0);
                            MainPage.this.finish();

                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<List<PicturesResponse>> call3, Throwable t) {  }
        });
    }



    // 마지막으로 뒤로 가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로 가기 버튼을 누를 때 표시
    private Toast toast;
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        // 기존 뒤로 가기 버튼의 기능을 막기 위해 주석 처리 또는 삭제
        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간에 2.5초를 더해 현재 시간과 비교 후
        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간이 2.5초가 지났으면 Toast 출력
        if (System.currentTimeMillis() > backKeyPressedTime + 2500) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "뒤로 가기 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간에 2.5초를 더해 현재 시간과 비교 후
        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간이 2.5초가 지나지 않았으면 종료
        if (System.currentTimeMillis() <= backKeyPressedTime + 2500)
            finish();
            toast.cancel();

    }

    public void LogoClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(MainPage.this, MainPage.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        MainPage.this.finish();
    }
    public void MypageClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(MainPage.this, MyPage.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        MainPage.this.finish();
    }
    public void StoreClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(MainPage.this, Store.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        MainPage.this.finish();
    }


}