package com.example.immadisairaj.quiz.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error {
    @SerializedName("message")
    @Expose
    private String message;

    public Error() {
    }

    public Error(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
