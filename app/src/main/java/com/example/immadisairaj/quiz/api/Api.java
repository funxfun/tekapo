package com.example.immadisairaj.quiz.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "https://api.openai.com/";

    @POST("v1/chat/completions")
    Call<QuizQuestions> getQuizQuestions(
            @Header("authorization") String token,
            @Header("content-type") String contentType,
            @Body Request request);
}