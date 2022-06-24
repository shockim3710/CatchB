package com.example.intro.retrofit2;

import com.example.intro.mainpage.PicturesResponse;
import com.example.intro.login.LoginResponse;
import com.example.intro.mainpage.PicturesResponse2;
import com.example.intro.mypage.MypageResponse;
import com.example.intro.mypage.WishListResponse;
import com.example.intro.signup.IdSameResponse;
import com.example.intro.signup.SignUpRequest;
import com.example.intro.signup.SignUpResponse;
import com.example.intro.store.StoreResponse;
import com.example.intro.store.storageResponse;
import com.example.intro.touchpicture.CommentRequest;
import com.example.intro.touchpicture.CommentResponse;
import com.example.intro.touchpicture.HintResponse;
import com.example.intro.touchpicture.HistoryRequest;
import com.example.intro.touchpicture.HistoryResponse;
import com.example.intro.touchpicture.JjimRequest;
import com.example.intro.touchpicture.JjimResponse;
import com.example.intro.touchpicture.UserPictureUplodeResponse;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface initMyApi {
    @Headers({"accept: application/json", "content-type: application/json"})
    //@통신 방식("통신 API명")
    //회원가입
    @POST("/api/users/")
    Call<SignUpResponse> getSignUpResponse(@Body SignUpRequest signupRequest);


    @GET("api/users/test")
    Call<LoginResponse> getLoginResponse(@Query("user_id") String user_id, @Query("user_pw") String user_pw);

    // 유저 아이디로 중복체크
    @GET("api/users/test2")
    Call<IdSameResponse> getUserResponse(@Query("user_id") String user_id);

    //사진의 지역이름으로 조회
    @GET("api/images/address/{image_address}")
    Call<List<PicturesResponse>> getPicturesResponse(@Path("image_address") String pictures_loc);

    //사진 이름으로 조회
    @GET("api/images/name/{image_name}")
    Call<List<PicturesResponse>> getPicturesNameResponse(@Path("image_name") String pictures_name);

    //사진 아이디로 조회
    @GET("api/images/id/{image_id}")
    Call<List<PicturesResponse2>> getPicturesNameResponse2(@Path("image_id") Long image_id);



    //user infomation search
    @GET("api/users/userinfo")
    Call<MypageResponse> getMypageResponse(@Query("user_id") String user_id);

    //image_id로 찜목록 조회
    @GET("api/wishlist/{user_id}/{image_id}")
    Call<List<JjimResponse>> getwishlistResponse(@Path("user_id") String user_id, @Path("image_id") Long image_id);

    //찜목록 등록
    @Headers({"accept: application/json", "content-type: application/json"})
    @POST("api/wishlist/")
    Call<JjimResponse> pushWishListResponse(@Body JjimRequest jjimRequest);

    //찜 목록 삭제
    @DELETE("api/wishlist/delete/{user_id}/{image_id}")
    Call<JjimResponse> deletewishResponse(@Path("user_id") String user_id, @Path("image_id") Long image_id);

    //user_id로 찜목록 조회
    @GET("api/wishlist/{user_id}")
    Call<List<WishListResponse>> getWishListAllResponse(@Path("user_id") String user_id);



    //댓글 등록
    @Headers({"accept: application/json", "content-type: application/json"})
    @POST("api/comment/")
    Call<CommentResponse> writeCommentResponse(@Body CommentRequest commentRequest);

    //댓글 전체 조회
    @GET("api/comment/search/{image_id}")
    Call<List<CommentResponse>> searchCommentResponse(@Path("image_id")Long image_id);

    //댓글 삭제
    @DELETE("api/comment/delete/{user_id}/{comment_des}")
    Call<CommentResponse> deleteCommentResponse(@Path("user_id") String user_id, @Path("comment_des") String comment_des);



    //유저사진전송
    @Multipart
    @POST("api/submit/")
    Call<UserPictureUplodeResponse> userpicturerequest(@Part MultipartBody.Part files, @PartMap HashMap<String, RequestBody> data);



    //사용자 힌트 사용
    @POST("api/users/usehint/{user_id}")
    Call<HintResponse> useHintResponse(@Path("user_id")String user_id);

    //크레딧 히스토리 관련
    @Headers({"accept: application/json", "content-type: application/json"})
    @POST("api/credithistory")
    Call<HistoryResponse> addHistroy(@Body HistoryRequest historyRequest);

    @GET("api/credithistory/{user_id}")
    Call<List<HistoryResponse>> getHistory(@Path("user_id") String user_id);



    //스토어 관련
    @POST("api/store/use/{user_id}/{item_name}")
    Call<StoreResponse> usegift(@Path("user_id")String user_id, @Path("item_name")String item_name);

    @POST("api/users/purchase/{giftcredit}/{user_id}")
    Call<MypageResponse> purchasegift(@Path("giftcredit")int giftcredit, @Path("user_id")String user_id);

    @GET("api/store/item_credit/{item_name}")
    Call<StoreResponse> searchCredit (@Path("item_name")String item_name);

    @GET("api/store/count/{item_name}")
    Call<StoreResponse> checkStock(@Path("item_name")String item_name);

    @GET("api/store/item_credit/{user_id}")
    Call<List<storageResponse>> getStorage(@Path("user_id") String user_id);
}
