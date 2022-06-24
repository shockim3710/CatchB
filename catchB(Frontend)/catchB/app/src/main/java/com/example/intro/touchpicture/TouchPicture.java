package com.example.intro.touchpicture;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.intro.R;
import com.example.intro.mainpage.MainPage;
import com.example.intro.mainpage.PicturesResponse;
import com.example.intro.mypage.MyPage;
import com.example.intro.mypage.MypageResponse;
import com.example.intro.retrofit2.RetrofitClient;
import com.example.intro.retrofit2.initMyApi;
import com.example.intro.store.Store;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TouchPicture extends AppCompatActivity {
    private RetrofitClient retrofitClient;
    private initMyApi initMyApi;

    private static final String TAG = "catchB";
    private Boolean isPermission = true;

    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_CAMERA = 2;

    private File tempFile;
    private File selectFile;

    String p_loc;
    String p_name;
    Long p_id;
    String p_start;
    String p_end;
    String p_credit;
    String p_des;
    String touched_picture_url;
    boolean bool[] = new boolean[1000]; // index = image_id 찜 체크 여부 판단

    Long wish_num;

    @Override
    protected void onStart(){
        super.onStart();
        Log.i("onStart","onStart");
        checkJjim();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_picture);
        Intent intent = getIntent();
        String image_name = intent.getStringExtra("image_name");
        String image_loc = intent.getStringExtra("image_loc");
        Long image_id = intent.getLongExtra("image_id", 0);
        String userid = intent.getStringExtra("userId");

        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        ImageView imageView = null;

        ImageView jjimbutton = (ImageView) findViewById(R.id.jjim);
        jjimbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jjim();
            }
        });

        Call<List<PicturesResponse>> call = initMyApi.getPicturesNameResponse(image_name);
        call.enqueue(new Callback<List<PicturesResponse>>() {
            @Override
            public void onResponse(Call<List<PicturesResponse>> call, Response<List<PicturesResponse>> response) {
                List<PicturesResponse> pictures = response.body();
                for(PicturesResponse picturesResponse : pictures){
                    p_loc = picturesResponse.pictures_loc;
                    p_credit = picturesResponse.pictures_credit;
                    p_des = picturesResponse.pictures_des;
                    p_end = picturesResponse.pictures_end;
                    p_start = picturesResponse.pictures_start;
                    p_id = picturesResponse.pictures_id;
                    p_name = picturesResponse.pictures_name;
                    touched_picture_url = "http://211.108.193.21:8080/api/images/view?image_address=" + picturesResponse.pictures_loc +
                            "&image_name=" + picturesResponse.pictures_name;

                    ImageView image = (ImageView) findViewById(R.id.touched_image);
                    Glide.with(getApplicationContext()).load(touched_picture_url)
                            .override(550,300)
                            .centerCrop()
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .into(image);
                    //Title_position
                    TextView ploc = (TextView) findViewById(R.id.Title_position);
                    ploc.setText(p_loc + "    ");
                    //Credit
                    TextView pcredit = (TextView) findViewById(R.id.Credit);
                    pcredit.setText(" " +p_credit);
                    //comment_text
                    TextView pstart_end = (TextView) findViewById(R.id.comment_text);
                    pstart_end.setText(p_start + "~" + p_end);
                    //text_hashtag
                    TextView pdes = (TextView) findViewById(R.id.text_hashtag);
                    pdes.setText(p_des);
                }
            }
            @Override
            public void onFailure(Call<List<PicturesResponse>> call, Throwable t) {

            }
        });

        tedPermission();
        findViewById(R.id.imageuplode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 권한 허용에 동의하지 않았을 경우 토스트를 띄웁니다.
                if(isPermission) {
                    goToAlbum();
                }
                else Toast.makeText(view.getContext(), getResources().getString(R.string.permission_2), Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.camerauplode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 권한 허용에 동의하지 않았을 경우 토스트를 띄웁니다.
                if(isPermission) {
                    takePhoto();
                }
                else Toast.makeText(view.getContext(), getResources().getString(R.string.permission_2), Toast.LENGTH_LONG).show();
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
        } else if (requestCode == PICK_FROM_CAMERA) {
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
     *  카메라에서 이미지 가져오기
     */
    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            tempFile = createImageFile();
            selectFile = tempFile;
        }   catch (Exception e) {
            Toast.makeText(this, "이미지 처리 오류", Toast.LENGTH_SHORT).show();
            finish();
            e.printStackTrace();
        }
        if (tempFile != null) {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                Uri photoUri = FileProvider.getUriForFile(this,
                        "com.example.intro.provider", tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, PICK_FROM_CAMERA);

            } else {
                Uri photoUri = Uri.fromFile(tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, PICK_FROM_CAMERA);

            }
        }
    }

    /**
     *  폴더 및 파일 만들기
     */
    private File createImageFile() throws IOException {

        // 이미지 파일 이름 ( blackJin_{시간}_ )
        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String imageFileName = "catchB_" + timeStamp + "_";

        // 이미지가 저장될 폴더 이름 (  /mnt/sdcard/Pictures )
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (!storageDir.exists()) {       // 원하는 경로에 폴더가 있는지 확인
            storageDir.mkdirs();    // 하위폴더를 포함한 폴더를 전부 생성
        }

        // 파일 생성
        File image = File.createTempFile("JPEG_", ".jpg", storageDir);
        Log.d(TAG, "createImageFile : " + image.getAbsolutePath());

        return image;
    }
    /**
     *  서버로 사진전송
     */
    private void setImage() {

        String filepath = selectFile.getAbsolutePath();
        upload(filepath);

        Toast.makeText(TouchPicture.this, "사진이 성공적으로 등록되었습니다.", Toast.LENGTH_LONG).show();

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
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();

    }

    public void upload(String path){
        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");
        String image_loc = intent.getStringExtra("image_loc");
        String image_name = intent.getStringExtra("image_name");

        TextView credittext = (TextView) findViewById(R.id.Credit);
        String temp_credit = credittext.getText().toString();
        String credit = temp_credit.replaceAll("[^0-9]", "");
        TextView hashtagtext = (TextView) findViewById(R.id.text_hashtag);
        String hashtag = hashtagtext.getText().toString();


        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        HashMap<String, RequestBody> map = new HashMap<>();

        RequestBody userID = RequestBody.create(MediaType.parse("text/plain"),user_id);
        RequestBody imageLoc = RequestBody.create(MediaType.parse("text/plain"),image_loc);
        RequestBody imageName = RequestBody.create(MediaType.parse("text/plain"),image_name);
        RequestBody imagecredit = RequestBody.create(MediaType.parse("text/plain"),credit);
        RequestBody hash = RequestBody.create(MediaType.parse("text/plain"),hashtag);

        map.put("user_id", userID);
        map.put("submit_address", imageLoc);
        map.put("file_name", imageName);
        map.put("submit_credit",imagecredit);
        map.put("hashtag",hash);

        File file = new File(path);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part uploadFile = MultipartBody.Part.createFormData("files", file.getName(), requestFile);
        Call <UserPictureUplodeResponse> call = initMyApi.userpicturerequest(uploadFile,map);

        call.enqueue(new Callback<UserPictureUplodeResponse>() {
            @Override
            public void onResponse(Call<UserPictureUplodeResponse> call, Response<UserPictureUplodeResponse> response) {
                if(response.isSuccessful()){
                }else{
                }
            }

            @Override
            public void onFailure(Call<UserPictureUplodeResponse> call, Throwable t) {
                Log.i("test", "onFailure: "+t.getMessage());
            }
        });
    }

    public void LogoClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(TouchPicture.this, MainPage.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        TouchPicture.this.finish();
    }

    public void MypageClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(TouchPicture.this, MyPage.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        TouchPicture.this.finish();
    }
    public void StoreClick(View view) {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(TouchPicture.this, Store.class);
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        TouchPicture.this.finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        String userid = intent.getStringExtra("user_id");

        Intent intent2 = new Intent(TouchPicture.this, MainPage.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        intent2.putExtra("user_id", userid);
        startActivity(intent2);
        finish();   //현재 액티비티 종료
    }
    public void sharebykakao(View view){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label","공유하기 기능 준비중");
        clipboard.setPrimaryClip(clip);

    }

    public void comment(View view){
        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");
        Long image_id = intent.getLongExtra("image_id",0);
        Intent intent2 = new Intent(getApplicationContext(), Comment_activity.class);
        intent2.putExtra("user_id", user_id);
        intent2.putExtra("image_id", image_id);

        startActivity(intent2);
        TouchPicture.this.onStop();
    }

    public void jjim(){
        ImageView imageView = null;
        Intent intent = getIntent();
        Long image_id = intent.getLongExtra("image_id",0);
        String user_id = intent.getStringExtra("user_id");
        int intImageId = image_id.intValue();

        JjimRequest jjimRequest = new JjimRequest(user_id,image_id);
        ImageView checkimage = (ImageView) findViewById(R.id.jjim);
        if(bool[intImageId] == false) { // 찜버튼 클릭 되어있지 않을 때
            initMyApi.pushWishListResponse(jjimRequest).enqueue(new Callback<JjimResponse>() {
                @Override
                public void onResponse(Call<JjimResponse> call, Response<JjimResponse> response) {
                    Log.d("retrofit", "로그인 한 유저가 해당 게시물 찜!");

                }

                @Override
                public void onFailure(Call<JjimResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });
            imageView = (ImageView) findViewById(R.id.jjim);
            imageView.setImageResource(R.drawable.touched_jjimicon);
            bool[intImageId] = true;
        }
        else{ // 찜버튼 클릭 되어있을 때
            initMyApi.deletewishResponse(user_id,image_id).enqueue(new Callback<JjimResponse>() {
                @Override
                public void onResponse(Call<JjimResponse> call, Response<JjimResponse> response) {
                    Log.d("retrofit", "찜 제거");
                }

                @Override
                public void onFailure(Call<JjimResponse> call, Throwable t) {

                }
            });
            imageView = (ImageView) findViewById(R.id.jjim);
            imageView.setImageResource(R.drawable.jjim);
            bool[intImageId] = false;
        }
    }

    public void hint(View v){
        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");
        Long image_id = intent.getLongExtra("image_id",0);
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        Call<MypageResponse> call = initMyApi.getMypageResponse(user_id);

        call.enqueue(new Callback<MypageResponse>() {
            @Override
            public void onResponse(Call<MypageResponse> call, Response<MypageResponse> response) {
                if (response.isSuccessful()) {
                    MypageResponse mypageResponse = response.body();
                    int user_credit = mypageResponse.getUser_credit();

                    if (user_credit < 20)
                        Toast.makeText(getBaseContext(), "보유한 크레딧이 부족합니다.", Toast.LENGTH_SHORT).show();

                    else {
                        Intent intent2 = new Intent(TouchPicture.this, PopupActivity.class);
                        intent2.putExtra("user_id",user_id);
                        intent2.putExtra("image_id",image_id);
                        startActivity(intent2);
                    }
                }
            }
            @Override
            public void onFailure(Call<MypageResponse> call, Throwable t) {
                Log.d("불러오기", "실패");
            }
        });


    }

    public void checkJjim(){
        Intent intent = getIntent();
        Long image_id = intent.getLongExtra("image_id", 0);
        String user_id = intent.getStringExtra("user_id");
        int intImageId = image_id.intValue();

        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        Call<List<JjimResponse>> call = initMyApi.getwishlistResponse(user_id,image_id);

        call.enqueue(new Callback<List<JjimResponse>>() {
            @Override
            public void onResponse(Call<List<JjimResponse>> call, Response<List<JjimResponse>> response) {
                List<JjimResponse> wishes = response.body();
                for(JjimResponse jjimResponse : wishes){
                    wish_num = jjimResponse.wish_num;
                }

                ImageView jjimbutton;
                if(wish_num == null){
                    jjimbutton = (ImageView) findViewById(R.id.jjim);
                    jjimbutton.setImageResource(R.drawable.jjim);
                    bool[intImageId] = false;
                }
                else{
                    jjimbutton = (ImageView) findViewById(R.id.jjim);
                    jjimbutton.setImageResource(R.drawable.touched_jjimicon);
                    bool[intImageId] = true;
                }
            }
            @Override
            public void onFailure(Call<List<JjimResponse>> call, Throwable t) {

            }
        });

    }

    public void advertisingClick(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/search?q=%EC%B0%8D%EC%96%B4%EB%A8%B9%EC%9E%90&c=apps"));
        startActivity(intent);
    }
}