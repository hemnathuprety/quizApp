package np.com.hemnath.quizapp.quiz.presentation;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import np.com.hemnath.quizapp.quiz.data.domain.QuizUseCase;
import np.com.hemnath.quizapp.quiz.data.domain.model.ApiToken;
import np.com.hemnath.quizapp.quiz.data.domain.model.QuizModel;
import np.com.hemnath.quizapp.quiz.data.domain.model.QuizResponse;
import np.com.hemnath.quizapp.quiz.data.local.quiz.QuizDataSource;
import np.com.hemnath.quizapp.quiz.data.local.token.TokenDataSource;
import np.com.hemnath.quizapp.quiz.presentation.model.QuizViewState;

@HiltViewModel
public class QuizViewModel extends ViewModel {

    @Inject
    public QuizViewModel() {
    }

    @Inject
    QuizUseCase useCase;

    @Inject
    QuizDataSource quizDataSource;

    @Inject
    TokenDataSource tokenDataSource;

    public List<QuizModel> mutableQuizList = new ArrayList<>();
    private String apiToken = "";

    public MutableLiveData<QuizViewState> mutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> counter = new MutableLiveData<>(120);

    private Timer timer = null;
    private final Handler handler = new Handler(Looper.getMainLooper());

    public void getQuizList(LifecycleOwner owner) {
        useCase.getQuizzes(apiToken).observe(owner, data -> {
            QuizViewState newState = new QuizViewState();
            switch (data.getStatus()) {
                case SUCCESS:
                    QuizResponse quizData = data.getData();
                    if (quizData != null) {
                        quizDataSource.saveQuizData(quizData.toQuizEntityList());
                        mutableQuizList = quizData.getResults();
                        mutableLiveData.setValue(newState);
                    }

                    break;
                case ERROR:
                    getLocalQuizData(owner);
                    break;
                case LOADING:
                    newState.setLoading(true);
                    mutableLiveData.setValue(newState);
                    break;
            }
        });
    }

    public void getLocalQuizData(LifecycleOwner owner) {
        quizDataSource.getQuizData().observe(owner, data -> {
            QuizViewState newState = new QuizViewState();
            if (data != null && !data.isEmpty()) {
                QuizResponse response = new QuizResponse();
                mutableQuizList = response.fromQuizEntityList(data);
            } else {
                String errorMessage = "Request failed, Please try again";
                newState.setErrorMessage(errorMessage);
            }
            mutableLiveData.setValue(newState);
        });
    }

    public void getLocalToken(LifecycleOwner owner) {
        tokenDataSource.getToken().observe(owner, data -> {
            if (data != null && !data.isEmpty()) {
                ApiToken token = data.get(0).toTokenModel();
                apiToken = token.getToken();
                Date date = token.getDate();
                QuizViewState newState = new QuizViewState();
                if (date != null) {
                    Date now = new Date();
                    long secondsInMilli = 1000;
                    long minutesInMilli = secondsInMilli * 60;
                    long hoursInMilli = minutesInMilli * 60;
                    long different = now.getTime() - date.getTime();
                    long elapsedHours = different / hoursInMilli;
                    if (elapsedHours > 6) {
                        newState.setNeedTokenReset(true);
                    } else {
                        newState.setTokenFetch(true);
                    }
                } else {
                    newState.setTokenFetch(true);
                }
                mutableLiveData.setValue(newState);

                Log.d("Token", apiToken);
            } else {
                getToken(owner);
            }
        });
    }

    public void getToken(LifecycleOwner owner) {
        useCase.getToken().observe(owner, data -> {
            QuizViewState newState = new QuizViewState();
            switch (data.getStatus()) {
                case SUCCESS:
                    ApiToken quizData = data.getData();
                    apiToken = quizData.getToken();
                    tokenDataSource.saveToken(quizData);
                    newState.setTokenFetch(true);
                    mutableLiveData.setValue(newState);
                    Log.d("Token", apiToken);
                    break;
                case ERROR:
                    String errorMessage = data.getApiError();
                    newState.setErrorMessage(errorMessage);
                    mutableLiveData.setValue(newState);
                    break;
                case LOADING:
                    newState.setLoading(true);
                    mutableLiveData.setValue(newState);
                    break;
            }
        });
    }

    public void getResetToken(LifecycleOwner owner) {
        useCase.getResetToken(apiToken).observe(owner, data -> {
            QuizViewState newState = new QuizViewState();
            switch (data.getStatus()) {
                case SUCCESS:
                    newState.setTokenFetch(true);
                    mutableLiveData.setValue(newState);
                    Log.d("Token", apiToken);
                    break;
                case ERROR:
                    String errorMessage = data.getApiError();
                    newState.setErrorMessage(errorMessage);
                    mutableLiveData.setValue(newState);
                    break;
                case LOADING:
                    newState.setLoading(true);
                    mutableLiveData.setValue(newState);
                    break;
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void onGetStartedClicked() {
        if (!mutableQuizList.isEmpty()) {
            QuizViewState newState = new QuizViewState();
            newState.setGetStarted(true);
            mutableLiveData.setValue(newState);
            startTimer();
        }
    }

    public void onQuizCompleted(int correctAnswerCount) {
        QuizViewState newState = new QuizViewState();
        newState.setQuizCompleted(true);
        newState.setCorrectAnswerCount(correctAnswerCount);
        mutableLiveData.setValue(newState);
    }

    public void onPlayAgain() {
        QuizViewState newState = new QuizViewState();
        newState.setStartAgain(true);
        mutableLiveData.setValue(newState);
    }

    public void startTimer() {
        counter.setValue(120);
        timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                if (counter.getValue() != null) {
                    int count = counter.getValue();
                    count--;
                    if (count == 0) {
                        cancelTimer();
                    }
                    int finalCount = count;
                    handler.post(() -> counter.setValue(finalCount));
                }
            }
        };

        // Scheduling the task to run every 1 second (1000 milliseconds)
        timer.schedule(task, 0, 1000);
    }

    public void cancelTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
