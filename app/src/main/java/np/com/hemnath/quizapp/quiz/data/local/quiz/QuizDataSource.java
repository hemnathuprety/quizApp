package np.com.hemnath.quizapp.quiz.data.local.quiz;

import androidx.lifecycle.LiveData;

import java.util.List;

import np.com.hemnath.quizapp.quiz.data.local.quiz.entity.QuizEntity;

public interface QuizDataSource {
    void saveQuizData(List<QuizEntity> Quizzes);

    void deleteQuizData();

    LiveData<List<QuizEntity>> getQuizData();
}
