package com.example.intro.signup;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.intro.login.Login;
import com.example.intro.R;
import com.example.intro.retrofit2.RetrofitClient;

import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    private RetrofitClient retrofitClient;
    private com.example.intro.retrofit2.initMyApi initMyApi;
    private EditText user_id, user_phone, user_pw, user_nickname;
    private Button btn_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        user_id = findViewById(R.id.inputid);
        user_phone = findViewById(R.id.inputphone);
        user_pw = findViewById(R.id.inputpwd);
        user_nickname = findViewById(R.id.inputnickname);

        //회원가입 버튼 이벤트
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                SignUpResponse();
            }
        });

        //회원가입 페이지에서 로그인 페이지로 빠지는 버튼
        ImageView loginbtn1 = (ImageView) findViewById(R.id.loginbtn1);
        loginbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                SignUp.this.finish();
            }
        });
    }

    public void SignUpResponse() {
        String userID = user_id.getText().toString().trim();
        String userPassword = user_pw.getText().toString().trim();
        String userPhone = user_phone.getText().toString().trim();
        String userNickname = user_nickname.getText().toString().trim();

        //SignUpRequest에 사용자가 입력한 id와 pw를 저장
        SignUpRequest signUpRequest = new SignUpRequest(userID, userPassword, userPhone, userNickname);

        //retrofit 생성
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();


        Call<IdSameResponse> call = initMyApi.getUserResponse(userID);
        call.enqueue(new Callback<IdSameResponse>() {
            @Override
            public void onResponse(Call<IdSameResponse> call, Response<IdSameResponse> response) {
                Log.d("회원가입", "성공");
                IdSameResponse idSameResponse = response.body();
                if (idSameResponse.resultCode.equals("0")){
                    retrofitClient = RetrofitClient.getInstance();
                    initMyApi = RetrofitClient.getRetrofitInterface();

                    //SignUpRequest에 저장된 데이터와 함께 init에서 정의한 getSignUpResponse 함수를 실행한 후 응답을 받음
                    initMyApi.getSignUpResponse(signUpRequest).enqueue(new Callback<SignUpResponse>() {
                        @Override
                        public void onResponse(Call<SignUpResponse> call2, Response<SignUpResponse> response) {
                            Log.d("retrofit", "Data fetch success");
                            //통신 성공
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(SignUp.this, Login.class);
                                startActivity(intent);
                                Toast.makeText(SignUp.this, "회원가입이 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                SignUp.this.finish();
                                //response.body()를 result에 저장
                            }
                        }
                        //통신실패
                        @Override
                        public void onFailure(Call<SignUpResponse> call2, Throwable t) {
                            t.printStackTrace();
                        }
                    });

                }else if(idSameResponse.resultCode.equals("1")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                    builder.setTitle("알림")
                            .setMessage("이미 가입된 아이디가 있습니다.\n아이디를 다시 작성해주세요.")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                    builder.setTitle("알림")
                            .setMessage("예기치 못한 오류가 발생하였습니다.")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                }
            }
            @Override
            public void onFailure(Call<IdSameResponse> call, Throwable t) {
                Log.d("회원가입", "실패");
                AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                builder.setTitle("알림")
                        .setMessage("예기치 못한 오류가 발생.")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
            }
        });
    }

    //키보드 숨기기
    private void hideKeyboard()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(user_id.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(user_pw.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(user_phone.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(user_nickname.getWindowToken(), 0);
    }

//    화면 터치 시 키보드 내려감
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
}
