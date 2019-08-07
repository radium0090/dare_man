package com.ls.dareman.Fragments;

import com.ls.dareman.Notifications.MyResponse;
import com.ls.dareman.Notifications.Sender;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
//    @Headers(
//            {
//                    "Content-Type:application/json",
//                    "Authorization:key=ADD HERE YOUR KEY FROM FIREBASE PROJECT"
//            }
//    )
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAVvnxaDA:APA91bFq0BzwLbGOUVbb3Bv0m58xKpYIduWhoMzCT0eVQ2imFfbwtKdjqPjfH6wWnxWmWRp9QE7wryLm09u4VjLrXCD20njpiimwVWJo9LcgiVKi0bHL2s7yHo0VK_T9Gx-5ZVbPhFts"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
