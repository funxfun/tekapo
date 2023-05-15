package com.example.immadisairaj.quiz.api;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizQuestions {

    @SerializedName("choices")
    @Expose
    private List<Choice> choices;

    public QuizQuestions() {
    }

    public QuizQuestions(Integer responseCode, List<Choice> choices) {
        super();
        this.choices = choices;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    @SerializedName("error")
    @Expose
    private Error error;

    public Error getError() {
        return error;
    }
}

