package com.example.intro.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intro.R;
import com.example.intro.mainpage.MainPage;
import com.example.intro.mainpage.PicturesResponse2;
import com.example.intro.retrofit2.RetrofitClient;
import com.example.intro.store.Store;
import com.example.intro.touchpicture.Listview;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishList extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private com.example.intro.retrofit2.initMyApi initMyApi;

    private WishListAdapter adapter1;
    private ListView listView;

    ArrayList<Long> imageList = new ArrayList<>();   //이미지 아이디 저장


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wish_list);

        adapter1 = new WishListAdapter();
        listView = (ListView) findViewById(R.id.listView2);
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        Intent intent = getIntent();
        String userID = intent.getStringExtra("user_id");

        Call<List<WishListResponse>> call = initMyApi.getWishListAllResponse(userID);
        call.enqueue(new Callback<List<WishListResponse>>() {
            @Override
            public void onResponse(Call<List<WishListResponse>> call, Response<List<WishListResponse>> response) {
                List<WishListResponse> wishlist = response.body();
                for(WishListResponse wishListResponse : wishlist) {
                    imageList.add(wishListResponse.image_id);
                }
                for(int i = 0; i < imageList.size(); i++){
                    push(imageList.get(i));
                }
            }
            @Override
            public void onFailure(Call<List<WishListResponse>> call, Throwable t) {
            }
        });
        listView.setAdapter(adapter1);
    }

    public void push(Long i){
        Long i2 = i;
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        Call<List<PicturesResponse2>> call2 = initMyApi.getPicturesNameResponse2(i);
        call2.enqueue(new Callback<List<PicturesResponse2>>() {
            @Override
            public void onResponse(Call<List<PicturesResponse2>> call2, Response<List<PicturesResponse2>> response2) {
                List<PicturesResponse2> pictures2 = response2.body();
                for(PicturesResponse2 picturesResponse2 : pictures2){
                    WishListDTO dto1 = new WishListDTO();
                    dto1.setWish_pic("http://211.108.193.21:8080/api/images/view?image_address=" + picturesResponse2.pictures_loc +
                            "&image_name=" + picturesResponse2.pictures_name);
                    dto1.setLoc_title(picturesResponse2.pictures_loc);
                    dto1.setLoc_credit(picturesResponse2.pictures_credit);
                    adapter1.addItem1(dto1);
                    adapter1.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<PicturesResponse2>> call2, Throwable t) {
            }
        });

    }
    public void LogoClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(WishList.this, MainPage.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        WishList.this.finish();
    }
    public void MypageClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(WishList.this, MyPage.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        WishList.this.finish();
    }
    public void StoreClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(WishList.this, Store.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        WishList.this.finish();
    }
    public void onBackPressed() {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(WishList.this, MyPage.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        finish();   //현재 액티비티 종료
    }
}