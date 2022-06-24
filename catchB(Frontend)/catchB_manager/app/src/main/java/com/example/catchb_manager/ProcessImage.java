package com.example.catchb_manager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProcessImage extends AppCompatActivity {

    private CustomAdapter adapter;
    private ListView listView;
    private RetrofitClient retrofitClient;
    private initMyApi initMyApi;

    ArrayList<String> user_id = new ArrayList<>();
    ArrayList<String> submit_address = new ArrayList<>();
    ArrayList<String> submit_credit = new ArrayList<>();
    ArrayList<String> file_name = new ArrayList<>();
    ArrayList<String> hashtagList = new ArrayList<>();
    ArrayList<String> imageList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_image);

        getSupportActionBar().setTitle("사진 처리");

        adapter = new CustomAdapter();
        listView = (ListView) findViewById(R.id.listView);

        setData();

        listView.setAdapter(adapter);
    }

    private void sumbitResponse() {


    }




    // 보통 ListView는 통신을 통해 가져온 데이터를 보여줍니다.
    // arrResId, titles, contents를 서버에서 가져온 데이터라고 생각하시면 됩니다.
    private void setData() {
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        Call<List<SubmitResponse>> call = initMyApi.getSubmitResponse();
        call.enqueue(new Callback<List<SubmitResponse>>() {
            @Override
            public void onResponse(Call<List<SubmitResponse>> call, Response<List<SubmitResponse>> response) {
                if (!response.isSuccessful()) {
                    Log.d("code: ",response.toString());
                    return;
                }
                List<SubmitResponse> image = response.body();
                for(SubmitResponse submitResponse : image){
                    user_id.add(submitResponse.user_id);
                    submit_address.add(submitResponse.submit_address);
                    submit_credit.add(submitResponse.submit_credit);
                    file_name.add(submitResponse.file_name);
                    hashtagList.add(submitResponse.hashtag);
                    imageList.add("http://211.108.193.21:8080/api/submit/view?submit_address=" + submitResponse.submit_address +
                            "&file_name=" + submitResponse.file_name);

                }


                for(int i = 0; i<user_id.size(); i++){

                    CustomDTO dto = new CustomDTO();
                    dto.setUser_id(user_id.get(i));
                    dto.setSubmit_address(submit_address.get(i));
                    dto.setCredit(submit_credit.get(i));
                    dto.setImageUrl(imageList.get(i));
                    dto.setFilename(file_name.get(i));
                    dto.setHashtag(hashtagList.get(i));
                    adapter.addItem(dto);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<SubmitResponse>> call, Throwable t) {

            }

        });

        }
    }
