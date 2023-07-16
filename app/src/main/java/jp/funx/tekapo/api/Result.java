package jp.funx.tekapo.api;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Serializable {

    @SerializedName("content")
    @Expose
    private String content;

    public Result() {
    }

    public Result(String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}