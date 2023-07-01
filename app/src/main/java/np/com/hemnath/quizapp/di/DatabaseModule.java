package np.com.hemnath.quizapp.di;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import np.com.hemnath.quizapp.application.AppDatabase;
import np.com.hemnath.quizapp.application.QuizApplication;
import np.com.hemnath.quizapp.quiz.data.local.quiz.QuizDeo;
import np.com.hemnath.quizapp.quiz.data.local.token.TokenDeo;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {
    @Provides
    @Singleton
    AppDatabase provideDatabase(QuizApplication application) {
        return Room.databaseBuilder(application, AppDatabase.class, "quizDB").build();
    }

    @Provides
    TokenDeo provideTokenDao(AppDatabase appDatabase) {
        return appDatabase.tokenDao();
    }

    @Provides
    QuizDeo provideQuizDao(AppDatabase appDatabase) {
        return appDatabase.quizDao();
    }
}
