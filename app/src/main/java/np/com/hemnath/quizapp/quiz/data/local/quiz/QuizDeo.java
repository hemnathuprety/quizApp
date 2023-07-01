package np.com.hemnath.quizapp.quiz.data.local.quiz;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import np.com.hemnath.quizapp.quiz.data.local.quiz.entity.QuizEntity;

@Dao
public interface QuizDeo {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuiz(QuizEntity quiz);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllQuizzes(List<QuizEntity> Quizzes);

    @Query("SELECT * FROM quizzes ORDER BY RANDOM() LIMIT 10")
    LiveData<List<QuizEntity>> getAllQuizzes();

    @Query("DELETE FROM quizzes")
    void nukeTable();
}
