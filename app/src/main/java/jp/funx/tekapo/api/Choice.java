package jp.funx.tekapo.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Choice {
    @SerializedName("message")
    @Expose
    private Message message;

    public Choice() {
    }

    public Choice(Message message) {
        super();
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }
}
