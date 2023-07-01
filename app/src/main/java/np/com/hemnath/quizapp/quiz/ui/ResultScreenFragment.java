package np.com.hemnath.quizapp.quiz.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import np.com.hemnath.quizapp.databinding.FragmentResultScreenBinding;
import np.com.hemnath.quizapp.quiz.presentation.QuizViewModel;

public class ResultScreenFragment extends Fragment {
    private QuizViewModel viewModel;

    public ResultScreenFragment() {
        // Required empty public constructor
    }

    private FragmentResultScreenBinding binding;

    public static ResultScreenFragment newInstance() {
        ResultScreenFragment fragment = new ResultScreenFragment();
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
        @NonNull FragmentResultScreenBinding bindingView = FragmentResultScreenBinding.inflate(inflater, container, false);
        binding = bindingView;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (viewModel.mutableLiveData.getValue() != null) {
            String correctAnswerCount = String.valueOf(viewModel.mutableLiveData.getValue().correctAnswerCount);
            String total = String.valueOf(viewModel.mutableQuizList.size());
            String text = correctAnswerCount + "/" + total;
            binding.count.setText(text);
        }

        binding.btnPlayAgain.setOnClickListener(view1 -> {
            viewModel.onPlayAgain();
        });
    }
}