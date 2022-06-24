package com.example.catchb_manager;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddImage extends AppCompatActivity {

    private static final String TAG = "catchB";

    private Boolean isPermission = true;

    private static final int PICK_FROM_ALBUM = 1;

    private File tempFile;
    private File selectFile;

    private RetrofitClient retrofitClient;
    private initMyApi initMyApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_image);
        getSupportActionBar().setTitle("사진 등록");

        tedPermission();


        findViewById(R.id.btnGallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 권한 허용에 동의하지 않았을 경우 토스트를 띄웁니다.
                if(isPermission) goToAlbum();
                else Toast.makeText(view.getContext(), getResources().getString(R.string.permission_2), Toast.LENGTH_LONG).show();
            }
        });
        findViewById(R.id.upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filepath = selectFile.getAbsolutePath();
                upload(filepath);

                imageResponse();
            }
        });



    }
    public void imageResponse(){
        String image_address = "Busan";
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        Call<List<ImageResponse>> call = initMyApi.getImageResponse(image_address);
        call.enqueue(new Callback<List<ImageResponse>>() {
            @Override
            public void onResponse(Call<List<ImageResponse>> call, Response<List<ImageResponse>> response) {
                if (!response.isSuccessful()) {
                    Log.d("code: ",response.toString());
                    return;
                }
                List<ImageResponse> image = response.body();
                for(ImageResponse imageResponse : image){
                    String content = "";
                    content += "ID: " + imageResponse.image_id + "\n";
                    content += "경로: " + imageResponse.image_route + "\n";
                    content += "이미지 이름: " + imageResponse.image_name + "\n";
                    content += "지역: " + imageResponse.image_address + "\n";
                }

            }

            @Override
            public void onFailure(Call<List<ImageResponse>> call, Throwable t) {

            }

        });
    }

    public void upload(String path){

        EditText editTitle = (EditText) findViewById(R.id.Title_position);
        EditText editCredit = (EditText) findViewById(R.id.Credit);
        EditText editStartdate = (EditText) findViewById(R.id.Title_startdate);
        EditText editEnddate = (EditText) findViewById(R.id.Title_enddate);
        EditText editHashtag = (EditText) findViewById(R.id.hashtag);
        EditText editHint = (EditText) findViewById(R.id.hint);

        String image_address = editTitle.getText().toString();
        String credit = editCredit.getText().toString();
        String start_time = editStartdate.getText().toString();
        String end_time = editEnddate.getText().toString();
        String image_description = editHashtag.getText().toString();
        String image_hint = editHint.getText().toString();

        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        HashMap<String, RequestBody> map = new HashMap<>();

        RequestBody address = RequestBody.create(MediaType.parse("text/plain"),image_address);
        RequestBody image_credit = RequestBody.create(MediaType.parse("text/plain"),credit);
        RequestBody startDate = RequestBody.create(MediaType.parse("text/plain"),start_time);
        RequestBody endDate = RequestBody.create(MediaType.parse("text/plain"),end_time);
        RequestBody content = RequestBody.create(MediaType.parse("text/plain"),image_description);
        RequestBody hint = RequestBody.create(MediaType.parse("text/plain"),image_hint);

        map.put("image_address", address);
        map.put("image_credit", image_credit);
        map.put("image_starttime", startDate);
        map.put("image_endtime", endDate);
        map.put("image_description", content);
        map.put("image_hint", hint);

        ImageRequest imageRequest = new ImageRequest(image_address,credit ,start_time,end_time,image_description,image_hint,path);
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part uploadFile = MultipartBody.Part.createFormData("files", file.getName(), requestFile);
        Call <ImageResponse> call = initMyApi.imagerequest(uploadFile,map);

        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if(response.isSuccessful()){
                }else{
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Log.i("test", "onFailure: "+t.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();

            if (tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        Log.e(TAG, tempFile.getAbsolutePath() + " 삭제 성공");
                        tempFile = null;
                    }
                }
            }

            return;
        }

        if (requestCode == PICK_FROM_ALBUM) {

            Uri photoUri = data.getData();
            Log.d(TAG, "PICK_FROM_ALBUM photoUri : " + photoUri);

            Cursor cursor = null;

            try {

                /*
                 *  Uri 스키마를
                 *  content:/// 에서 file:/// 로  변경한다.
                 */
                String[] proj = {MediaStore.Images.Media.DATA};

                assert photoUri != null;
                cursor = getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));
                selectFile = tempFile;

                Log.d(TAG, "tempFile Uri : " + Uri.fromFile(tempFile));

            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

            setImage();

        }
    }

    /**
     *  앨범에서 이미지 가져오기
     */
    private void goToAlbum() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }



    /**
     *  tempFile 을 bitmap 으로 변환 후 ImageView 에 설정한다.
     */
    private void setImage() {

        ImageView picture = findViewById(R.id.picture);

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);
        Log.d(TAG, "setImage : " + tempFile.getAbsolutePath());

        picture.setImageBitmap(originalBm);

        /**
         *  tempFile 사용 후 null 처리를 해줘야 합니다.
         *  (resultCode != RESULT_OK) 일 때 tempFile 을 삭제하기 때문에
         *  기존에 데이터가 남아 있게 되면 원치 않은 삭제가 이뤄집니다.
         */
        tempFile = null;

    }

    /**
     *  권한 설정
     */
    private void tedPermission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // 권한 요청 성공
                isPermission = true;

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // 권한 요청 실패
                isPermission = false;

            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setDeniedMessage(getResources().getString(R.string.permission_1))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();

    }

}