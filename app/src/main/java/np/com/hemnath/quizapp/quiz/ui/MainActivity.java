package np.com.hemnath.quizapp.quiz.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import np.com.hemnath.quizapp.databinding.ActivityMainBinding;
import np.com.hemnath.quizapp.quiz.presentation.QuizViewModel;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private QuizViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        ConstraintLayout view = binding.getRoot();
        setContentView(view);

        // Obtain an instance of the ViewModel
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        loadFragment(WelcomeFragment.newInstance());

        viewModel.getLocalToken(this);

        viewModel.mutableLiveData.observe(this, data -> {
            if (data.isTokenFetch) {
                viewModel.getQuizList(this);
            } else if (data.isError) {
                handleErrorMessage(data.errorMessage);
            } else if (data.isGetStarted) {
                loadFragment(QuestionsFragment.newInstance());
            } else if (data.isQuizCompleted) {
                loadFragment(ResultScreenFragment.newInstance());
            } else if (data.isStartAgain) {
                loadFragment(WelcomeFragment.newInstance());
                viewModel.getQuizList(this);
            }
        });
    }

    public void handleErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Load Given Fragment
     *
     * @param fragment is a Fragment
     */
    private void loadFragment(Fragment fragment) {
        // load fragment
        getSupportFragmentManager().beginTransaction().replace(binding.frameContainer.getId(), fragment).commit();
    }
}