package np.com.hemnath.quizapp.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import np.com.hemnath.quizapp.R;
import np.com.hemnath.quizapp.quiz.data.domain.model.QuizModel;
import np.com.hemnath.quizapp.quiz.presentation.model.OptionModel;
import np.com.hemnath.quizapp.quiz.ui.adapter.OptionsRecyclerAdapter;

public class QuizOptionsView extends LinearLayout {
    private List<OptionModel> optionsList = new ArrayList<>();
    private int previousSelected = 0;
    private RecyclerView rvOptions;

    public QuizOptionsView(Context context) {
        super(context);
    }

    public QuizOptionsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QuizOptionsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(String type, QuizModel model) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_custom_options, this, true);
        rvOptions = view.findViewById(R.id.rvOptions);
        TextView txtQuestion = view.findViewById(R.id.txtQuestion);
        txtQuestion.setText(model.getQuestion());

        optionsList.add(new OptionModel(model.getCorrectAnswer(), false));

        for (String answers : model.getIncorrectAnswers()) {
            optionsList.add(new OptionModel(answers, false));
        }

        Collections.shuffle(optionsList);

        OptionsRecyclerAdapter optionsAdapter = new OptionsRecyclerAdapter(optionsList, position -> {
            updateSelected(previousSelected, false);
            updateSelected(position, true);
            previousSelected = position;
        });


        rvOptions.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOptions.setAdapter(optionsAdapter);
    }

    private void updateSelected(int position, boolean value) {
        OptionModel item = optionsList.get(position);
        item.setSelected(value);
        optionsList.remove(position);
        optionsList.add(position, item);
        if (rvOptions.getAdapter() != null) rvOptions.getAdapter().notifyItemChanged(position);
    }

    public String getAnswer() {
        String answer = "";
        for (OptionModel option : optionsList) {
            if (option.getSelected()) {
                answer = option.getAnswers();
            }
        }
        return answer;
    }

}