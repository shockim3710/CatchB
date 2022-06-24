package com.example.intro.login;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.intro.mainpage.MainPage;
import com.example.intro.R;
import com.example.intro.retrofit2.RetrofitClient;
import com.example.intro.signup.SignUp;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private Boolean isPermission = true;
    private RetrofitClient retrofitClient;
    private com.example.intro.retrofit2.initMyApi initMyApi;
    private EditText user_id, user_pw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        user_id = findViewById(R.id.inputid);
        user_pw = findViewById(R.id.inputpwd);

        //로그인 페이지에서 회원가입 페이지로 가는 화면 맨 아래 버튼 이벤트
        ImageView signbtn1 = (ImageView) findViewById(R.id.signbtn1);
        signbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                Login.this.finish();
            }
        });

        //로그인버튼
        Button loginok1 = (Button) findViewById(R.id.loginok1);
        loginok1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = user_id.getText().toString();
                String pw = user_pw.getText().toString();
                if (id.trim().length() == 0 || pw.trim().length() == 0 || id == null || pw == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setTitle("알림")
                            .setMessage("로그인 정보를 입력바랍니다.")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    //로그인 통신
                    LoginResponse();
                }
            }
        });
    }
    public void LoginResponse() {
        String userID = user_id.getText().toString().trim();
        String userPassword = user_pw.getText().toString().trim();

        //retrofit 생성
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        Call<LoginResponse> call = initMyApi.getLoginResponse(userID,userPassword);
        //loginRequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("로그인", "성공");
                LoginResponse loginResponse = response.body();
                if (loginResponse.resultCode.equals("1")){
                    String userID = user_id.getText().toString();
                        Toast.makeText(Login.this, userID + "님 환영합니다.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this, MainPage.class);
                        intent.putExtra("user_id", userID);
                        startActivity(intent);
                        Login.this.finish();

                }else if(loginResponse.resultCode.equals("0")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                        builder.setTitle("알림")
                                .setMessage("아이디 혹은 비밀번호가 일치하지 않습니다.")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();

                }else if(loginResponse.resultCode.equals("2")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                        builder.setTitle("알림")
                                .setMessage("아이디 혹은 비밀번호가 일치하지 않습니다.")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();
                }else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                        builder.setTitle("알림")
                                .setMessage("예기치 못한 오류가 발생하였습니다.")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();
                    }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("로그인", "실패");
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setTitle("알림")
                        .setMessage("예기치 못한 오류가 발생.")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
            }
        });
    }

    //화면 터치시 키보드 다운
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    //권한설정
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
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }
}
