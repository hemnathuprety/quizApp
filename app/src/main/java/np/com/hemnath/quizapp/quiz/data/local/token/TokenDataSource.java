package np.com.hemnath.quizapp.quiz.data.local.token;

import androidx.lifecycle.LiveData;

import java.util.List;

import np.com.hemnath.quizapp.quiz.data.domain.model.ApiToken;
import np.com.hemnath.quizapp.quiz.data.local.token.entity.TokenEntity;

public interface TokenDataSource {
    void saveToken(ApiToken token);

    void deleteToken();

    LiveData<List<TokenEntity>> getToken();
}
