package jp.funx.tekapo.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("role")
    @Expose
    private String role;

    @SerializedName("content")
    @Expose
    private String content;

    public Message() {
    }

    public Message(String role, String content) {
        super();
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }
}
