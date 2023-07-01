package np.com.hemnath.quizapp.application;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import np.com.hemnath.quizapp.quiz.data.local.quiz.QuizDeo;
import np.com.hemnath.quizapp.quiz.data.local.quiz.entity.QuizEntity;
import np.com.hemnath.quizapp.quiz.data.local.token.TokenDeo;
import np.com.hemnath.quizapp.quiz.data.local.token.entity.TokenEntity;

@Database(entities = {QuizEntity.class, TokenEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuizDeo quizDao();
    public abstract TokenDeo tokenDao();
}
