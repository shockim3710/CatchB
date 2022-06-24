package com.example.intro.touchpicture;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.intro.R;
import com.example.intro.retrofit2.RetrofitClient;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Comment_activity extends AppCompatActivity {
    private RetrofitClient retrofitClient;
    private com.example.intro.retrofit2.initMyApi initMyApi;
    int[] iconlist = new int[]{R.drawable.profile1,R.drawable.profile2,R.drawable.profile3};
    int imageIcon = (int)(Math.random() * iconlist.length);

    ArrayList<String> comment_user_id = new ArrayList<>();
    ArrayList<String> comment_des = new ArrayList<>();
    ArrayList<Integer> comment_icon = new ArrayList<>();

    private ListViewAdapter adapter = new ListViewAdapter();
    private ListView listView;

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<Listview> listViewItemList = new ArrayList<Listview>() ;

    /* 댓글창 리스트뷰 구현 */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_layout);
        Intent intent = getIntent();
        Long image_id = intent.getLongExtra("image_id",0);

        adapter = new ListViewAdapter();
        listView = (ListView) findViewById(R.id.comment_list);
        setData();
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        final EditText edtItem = (EditText) findViewById(R.id.write_comment);
        Button btnAdd = (Button) findViewById(R.id.upload_comment);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String userID = intent.getStringExtra("user_id");

                writeComment();

                Listview listViewItem = new Listview();
                EditText write_comment = (EditText) findViewById(R.id.write_comment);
                String str = write_comment.getText().toString();
                listViewItem.setIcon(iconlist[imageIcon]);
                listViewItem.setNickname(userID);
                listViewItem.setContents(str);

                adapter.addItem1(listViewItem);
                write_comment.setText("");
                adapter.notifyDataSetChanged();
            }
        });
    }

    // 보통 ListView는 통신을 통해 가져온 데이터를 보여줌.
    // arrResId, titles, contents를 서버에서 가져온 데이터라고 생각하면됨.
    private void setData() {
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();
        Intent intent = getIntent();
        Long image_id = intent.getLongExtra("image_id",0);

        Call<List<CommentResponse>> call = initMyApi.searchCommentResponse(image_id);

        call.enqueue(new Callback<List<CommentResponse>>() {
            @Override
            public void onResponse(Call<List<CommentResponse>> call, Response<List<CommentResponse>> response) {
                List<CommentResponse> commentes = response.body();
                for(CommentResponse commentResponse : commentes){

                    comment_user_id.add(commentResponse.user_id);
                    comment_des.add(commentResponse.comment_des);
                    comment_icon.add(commentResponse.image_icon);

                }
                for (int i = 0; i < comment_user_id.size(); i++) {
                    Listview listViewItem = new Listview();
                    listViewItem.setIcon(comment_icon.get(i));
                    listViewItem.setNickname(comment_user_id.get(i));
                    listViewItem.setContents(comment_des.get(i));

                    adapter.addItem1(listViewItem);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<CommentResponse>> call, Throwable t) {

            }
        });


    }

    public void writeComment(){
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");
        Long image_id = intent.getLongExtra("image_id",0);
        EditText edtItem = (EditText) findViewById(R.id.write_comment);
        String comment_des = edtItem.getText().toString().trim();

        CommentRequest commentRequest = new CommentRequest(user_id,comment_des,image_id,iconlist[imageIcon]);
        initMyApi.writeCommentResponse(commentRequest).enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                Log.d("retrofit", "댓글이 등록되었습니다.");
                Toast.makeText(Comment_activity.this, "댓글이 등록되었습니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {

            }
        });
    }

}
