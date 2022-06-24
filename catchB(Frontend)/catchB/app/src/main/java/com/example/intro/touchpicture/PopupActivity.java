package com.example.intro.touchpicture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intro.R;
import com.example.intro.mainpage.PicturesResponse2;
import com.example.intro.retrofit2.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopupActivity extends Activity {
    private RetrofitClient retrofitClient;
    private com.example.intro.retrofit2.initMyApi initMyApi;

    TextView txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.hint_popup);

    }

    //사용 버튼 클릭
    public void mOnClick(View v){
        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");
        Long image_id = intent.getLongExtra("image_id",0);
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        Call<List<PicturesResponse2>> hintcall = initMyApi.getPicturesNameResponse2(image_id);
        hintcall.enqueue(new Callback<List<PicturesResponse2>>() {
            @Override
            public void onResponse(Call<List<PicturesResponse2>> call, Response<List<PicturesResponse2>> response) {
                List<PicturesResponse2> response2 = response.body();
                String hint = "";
                for(PicturesResponse2 picturesResponse2 : response2){
                    hint = picturesResponse2.image_hint;
                    Toast.makeText(getBaseContext(), hint, Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<List<PicturesResponse2>> call, Throwable t) {

            }
        });
        Call<HintResponse> call = initMyApi.useHintResponse(user_id);
        call.enqueue(new Callback<HintResponse>() {
            @Override
            public void onResponse(Call<HintResponse> call, Response<HintResponse> response) {

            }

            @Override
            public void onFailure(Call<HintResponse> call, Throwable t) {

            }
        });

        String hintstr = "힌트";
        String usestr = "사용";
        HistoryRequest historyRequest = new HistoryRequest(user_id,hintstr,"-20",usestr);
        Call<HistoryResponse> call2 = initMyApi.addHistroy(historyRequest);
        call2.enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {

            }

            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {

            }
        });
        finish();
    }

    //닫기 버튼 클릭
    public void mOnClose(View v){
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}