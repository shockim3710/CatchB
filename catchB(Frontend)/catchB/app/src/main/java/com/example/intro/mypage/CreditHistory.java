package com.example.intro.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.intro.R;
import com.example.intro.mainpage.MainPage;
import com.example.intro.retrofit2.RetrofitClient;
import com.example.intro.store.Store;
import com.example.intro.touchpicture.HistoryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreditHistory extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private com.example.intro.retrofit2.initMyApi initMyApi;
    private CAdapter adapter;
    private ListView listView;

    ArrayList<String> historyname = new ArrayList<>();
    ArrayList<String> credit = new ArrayList<>();
    ArrayList<String> credit_info = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit_history);

        adapter = new CAdapter();
        listView = (ListView) findViewById(R.id.listview1);

        MyPageResponse();
        listView.setAdapter(adapter);
    }

    public void MyPageResponse() {
        Intent intent = getIntent();
        String userID = intent.getStringExtra("user_id");

        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        Call<List<HistoryResponse>> call = initMyApi.getHistory(userID);
        call.enqueue(new Callback<List<HistoryResponse>>() {
            @Override
            public void onResponse(Call<List<HistoryResponse>> call, Response<List<HistoryResponse>> response) {
                List<HistoryResponse> history = response.body();
                for(HistoryResponse historyResponse : history){
                    historyname.add(historyResponse.histry_name);
                    credit.add(historyResponse.credit);
                    credit_info.add(historyResponse.credit_info);
                }
                for(int i=0; i<historyname.size();i++){
                    CreditHistoryDTO dto =new CreditHistoryDTO();
                    dto.setContent(historyname.get(i));
                    dto.setCredit(credit.get(i));
                    dto.setCredit_info(credit_info.get(i));
                    adapter.addItem(dto);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<HistoryResponse>> call, Throwable t) {
            }
        });
    }

    public void LogoClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(CreditHistory.this, MainPage.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        CreditHistory.this.finish();
    }

    public void MypageClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(CreditHistory.this, MyPage.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        CreditHistory.this.finish();
    }
    public void StoreClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(CreditHistory.this, Store.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        CreditHistory.this.finish();
    }
    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(CreditHistory.this, MyPage.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        finish();   //현재 액티비티 종료
    }
}