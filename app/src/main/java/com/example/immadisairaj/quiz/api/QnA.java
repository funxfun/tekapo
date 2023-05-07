package com.example.immadisairaj.quiz.api;

import java.util.List;

public class QnA {
    private String problem;
    private String question;

    public String getProblem() {
        if (question != null)
            return question;

        return problem;
    }

    private List<String> choices;
    private List<String> options;

    public List<String> getChoices() {
        if (options != null)
            return options;

        return choices;
    }

    private String answer;
    private String solution;

    public String getAnswer() {
        if (solution != null)
            return solution;

        return answer;
    }
}
