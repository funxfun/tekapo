package jp.funx.tekapo.solution;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jp.funx.tekapo.R;
import jp.funx.tekapo.SolutionActivity;
import jp.funx.tekapo.question.Question;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AnswersFragment extends Fragment {

    @BindView(R.id.rv_answers)
    RecyclerView solutions;

    Question q;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answers, container, false);

        ButterKnife.bind(this, view);

        ArrayList<Integer> Answers = SolutionActivity.getAnswer();

        AnswerAdapter answerAdapter = new AnswerAdapter(Answers,
                SolutionActivity.getAnswers(),
                SolutionActivity.getOptA(),
                SolutionActivity.getOptB(),
                SolutionActivity.getOptC(),
                SolutionActivity.getOptD());
        solutions.setAdapter(answerAdapter);

        return view;
    }
}