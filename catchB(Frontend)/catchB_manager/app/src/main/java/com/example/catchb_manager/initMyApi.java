package com.example.catchb_manager;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface initMyApi {
    //@통신 방식("통신 API명")
    @Multipart
    @POST("api/images")
    Call<ImageResponse> imagerequest(@Part MultipartBody.Part files, @PartMap HashMap<String, RequestBody> data);

    @GET("api/images/address/{image_address}")
    Call<List<ImageResponse>> getImageResponse(@Path("image_address") String image_address);

    @Multipart
    @POST("api/store")
    Call<GifticonResponse> gifticonrequest(@Part MultipartBody.Part files, @PartMap HashMap<String, RequestBody> data);


    @GET("api/submit/all")
    Call<List<SubmitResponse>> getSubmitResponse();

    @POST("api/submit/process/{user_id}/{hashtag}")
    Call<SubmitResponse> processSubmit(@Path("user_id") String user_id,@Path("hashtag") String hashtag);

    @POST("api/users/addcredit/{image_credit}/{user_id}")
    Call<UserResponse> addCredit(@Path("image_credit") int image_credit,@Path("user_id") String user_id);

    @Headers({"accept: application/json", "content-type: application/json"})
    @POST("api/credithistory")
    Call<HistroyResponse> addHistroy(@Body HistoryRequest historyRequest);

}