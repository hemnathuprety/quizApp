package np.com.hemnath.quizapp.quiz.data.local.quiz;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import np.com.hemnath.quizapp.quiz.data.local.quiz.entity.QuizEntity;

public class QuizDataSourceUseCase implements QuizDataSource {

    @Inject
    public QuizDataSourceUseCase() {
    }

    @Inject
    QuizDeo quizDeo;

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    @Override
    public void saveQuizData(List<QuizEntity> quizzes) {
        executorService.execute(() -> {
            quizDeo.insertAllQuizzes(quizzes);
        });
    }

    @Override
    public void deleteQuizData() {
        executorService.execute(() -> {
            quizDeo.nukeTable();
        });
    }

    @Override
    public LiveData<List<QuizEntity>> getQuizData() {
        return quizDeo.getAllQuizzes();
    }
}
