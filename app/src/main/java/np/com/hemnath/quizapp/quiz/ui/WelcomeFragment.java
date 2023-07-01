package np.com.hemnath.quizapp.quiz.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import np.com.hemnath.quizapp.R;
import np.com.hemnath.quizapp.databinding.FragmentWelcomeBinding;
import np.com.hemnath.quizapp.quiz.presentation.QuizViewModel;

public class WelcomeFragment extends Fragment {
    private QuizViewModel viewModel;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    private FragmentWelcomeBinding binding;

    public static WelcomeFragment newInstance() {
        WelcomeFragment fragment = new WelcomeFragment();
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
        @NonNull FragmentWelcomeBinding bindingView = FragmentWelcomeBinding.inflate(inflater, container, false);
        binding = bindingView;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnGetStarted.setOnClickListener(view1 -> {
            viewModel.onGetStartedClicked();
        });

        viewModel.mutableLiveData.observe(getViewLifecycleOwner(), data -> {
            if (data.isLoading) {
                binding.btnGetStarted.setEnabled(false);
                binding.btnGetStarted.setText("");
                binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                binding.btnGetStarted.setEnabled(true);
                binding.btnGetStarted.setText(getString(R.string.get_started));
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }
}