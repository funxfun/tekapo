package com.example.immadisairaj.quiz.api;

import java.util.List;

public class Problem {
    private String problem;
    private String question;
    private String Problem;
    private String Question;

    public String getProblem() {
        if (question != null)
            return question;
        if (Problem != null)
            return Problem;
        if (Question != null)
            return Question;

        return problem;
    }

    private List<String> choices;
    private List<String> options;
    private List<String> answers;
    private List<String> Choices;
    private List<String> Options;
    private List<String> Answers;

    public List<String> getChoices() {
        if (options != null)
            return options;
        if (answers != null)
            return answers;
        if (Choices != null)
            return Choices;
        if (Options != null)
            return Options;
        if (Answers != null)
            return Answers;

        return choices;
    }

    private String answer;
    private String solution;
    private String Answer;
    private String Solution;

    public String getAnswerString() {
        if (solution != null)
            return solution;
        if (Answer != null)
            return Answer;
        if (Solution != null)
            return Solution;

        return answer;
    }

    public int getAnswerIndex() {
        String answer = this.answer;
        if (solution != null)
            answer = solution;
        if (Answer != null)
            answer = Answer;
        if (Solution != null)
            answer = Solution;

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