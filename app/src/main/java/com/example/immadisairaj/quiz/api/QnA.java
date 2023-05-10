package com.example.immadisairaj.quiz.api;

import java.util.List;
import java.util.Locale;

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

    public String getAnswerString() {
        if (solution != null)
            return solution;

        return answer;
    }

    public int getAnswerIndex() {
        String answer = this.answer;
        if (solution != null)
            answer = solution;

        if (getChoices().size() == 1 && answer.toLowerCase().equals(getChoices().get(0).toLowerCase()))
            return 0;
        if (getChoices().size() <= 2 && answer.toLowerCase().equals(getChoices().get(1).toLowerCase()))
            return 1;
        if (getChoices().size() <= 3 && answer.toLowerCase().equals(getChoices().get(2).toLowerCase()))
            return 2;
        if (getChoices().size() <= 4 && answer.toLowerCase().equals(getChoices().get(3).toLowerCase()))
            return 3;

        if ("a".equals(answer.toLowerCase().charAt(0)))
            return 0;
        if ("b".equals(answer.toLowerCase().charAt(0)))
            return 1;
        if ("c".equals(answer.toLowerCase().charAt(0)))
            return 2;
        if ("d".equals(answer.toLowerCase().charAt(0)))
            return 3;

        if ("1".equals(answer.toLowerCase().charAt(0)))
            return 0;
        if ("2".equals(answer.toLowerCase().charAt(0)))
            return 1;
        if ("3".equals(answer.toLowerCase().charAt(0)))
            return 2;
        if ("4".equals(answer.toLowerCase().charAt(0)))
            return 3;

        return -1;
    }
}
