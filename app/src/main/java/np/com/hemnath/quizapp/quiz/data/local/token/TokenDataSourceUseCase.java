package np.com.hemnath.quizapp.quiz.data.local.token;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import np.com.hemnath.quizapp.quiz.data.domain.model.ApiToken;
import np.com.hemnath.quizapp.quiz.data.local.token.entity.TokenEntity;

public class TokenDataSourceUseCase implements TokenDataSource {

    @Inject
    public TokenDataSourceUseCase() {
    }

    @Inject
    TokenDeo tokenDeo;

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    @Override
    public void saveToken(ApiToken token) {
        executorService.execute(() -> {
            tokenDeo.insertToken(token.toTokenEntity());
        });
    }

    @Override
    public void deleteToken() {
        executorService.execute(() -> {
            tokenDeo.nukeTable();
        });
    }

    @Override
    public LiveData<List<TokenEntity>> getToken() {
        return tokenDeo.getAllToken();
    }
}
