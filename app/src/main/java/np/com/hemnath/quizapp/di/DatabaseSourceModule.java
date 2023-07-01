package np.com.hemnath.quizapp.di;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import np.com.hemnath.quizapp.quiz.data.local.quiz.QuizDataSource;
import np.com.hemnath.quizapp.quiz.data.local.quiz.QuizDataSourceUseCase;
import np.com.hemnath.quizapp.quiz.data.local.token.TokenDataSource;
import np.com.hemnath.quizapp.quiz.data.local.token.TokenDataSourceUseCase;

@InstallIn(SingletonComponent.class)
@Module
public abstract class DatabaseSourceModule {
    @Singleton
    @Binds
    abstract QuizDataSource bindQuizDataSource(QuizDataSourceUseCase useCase);

    @Singleton
    @Binds
    abstract TokenDataSource bindTokenDataSource(TokenDataSourceUseCase useCase);
}
