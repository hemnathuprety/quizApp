package np.com.hemnath.quizapp.quiz.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import np.com.hemnath.quizapp.R;
import np.com.hemnath.quizapp.databinding.FragmentQuestionsBinding;
import np.com.hemnath.quizapp.quiz.data.domain.model.QuizModel;
import np.com.hemnath.quizapp.quiz.presentation.QuizViewModel;
import np.com.hemnath.quizapp.utils.QuizOptionsView;


public class QuestionsFragment extends Fragment {
    private QuizViewModel viewModel;

    public QuestionsFragment() {
        // Required empty public constructor
    }

    private FragmentQuestionsBinding binding;

    private int currentStep = 0;
    private int prevStep = 0;

    public static QuestionsFragment newInstance() {
        QuestionsFragment fragment = new QuestionsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        @NonNull FragmentQuestionsBinding bindingView = FragmentQuestionsBinding.inflate(inflater, container, false);
        binding = bindingView;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!viewModel.mutableQuizList.isEmpty()) {
            setQuizLists();

            binding.btnNext.setOnClickListener(view1 -> {
                nextStep();
            });

            binding.btnBack.setOnClickListener(view1 -> {
                onPreviousClick();
            });
        }

        viewModel.counter.observe(getViewLifecycleOwner(), data -> {
            if (data == 0) {
                completeSurvey();
            } else {
                float progress = data.floatValue() / 120 * 100;
                binding.time.setText(String.valueOf(data));
                binding.progressBar.setProgress((int) progress);
            }
        });
    }

    void setQuizLists() {
        addView();
        binding.seekBar.setMax(viewModel.mutableQuizList.size() - 1);
        binding.seekBar.setEnabled(false);
    }

    void addView() {
        for (QuizModel question : viewModel.mutableQuizList) {
            QuizOptionsView view = new QuizOptionsView(requireContext());
            view.init(question.getCategory(), question);

            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            view.setVisibility(View.GONE);
            binding.frameLayout.addView(view);
        }
        setVisibility();
    }

    private void setVisibility() {
        String step = String.valueOf(currentStep + 1);
        String textStep = "Question " + step + "/10";
        binding.txtQuestionCount.setText(textStep);
        if (binding.frameLayout.getChildCount() > 0) {
            if (currentStep != prevStep) {
                View child = binding.frameLayout.getChildAt(prevStep);
                child.setVisibility(View.GONE);
                prevStep = currentStep;
            }
            View child = binding.frameLayout.getChildAt(currentStep);
            child.setVisibility(View.VISIBLE);
        }
    }

    private void nextStep() {
        if (currentStep != viewModel.mutableQuizList.size() - 1) {
            currentStep++;
            binding.seekBar.setProgress(binding.seekBar.getProgress() + 1);
            setVisibility();
            if (currentStep == viewModel.mutableQuizList.size() - 1)
                binding.btnNext.setText(getString(R.string.done));
            binding.btnBack.setEnabled(true);
        } else {
            completeSurvey();
        }
    }

    private void onPreviousClick() {
        if (currentStep != 0) {
            currentStep--;
            binding.seekBar.setProgress(binding.seekBar.getProgress() - 1);
            setVisibility();
            binding.btnNext.setText(getString(R.string.next));
        }
        if (currentStep == 0) binding.btnBack.setEnabled(false);
    }

    private void completeSurvey() {
        int childCount = binding.frameLayout.getChildCount();
        int correctAnswerCount = 0;
        for (int i = 0; i < childCount; i++) {
            QuizModel questions = viewModel.mutableQuizList.get(i);
            QuizOptionsView view = (QuizOptionsView) binding.frameLayout.getChildAt(i);
            String answer = view.getAnswer();
            if (questions.getCorrectAnswer().equals(answer)) {
                correctAnswerCount++;
            }
        }
        viewModel.onQuizCompleted(correctAnswerCount);
    }

}