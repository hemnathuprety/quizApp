package np.com.hemnath.quizapp.quiz.data.domain;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;
import javax.inject.Singleton;

import np.com.hemnath.quizapp.quiz.data.domain.api.QuizApiService;
import np.com.hemnath.quizapp.quiz.data.domain.model.ApiToken;
import np.com.hemnath.quizapp.quiz.data.domain.model.QuizResponse;
import np.com.hemnath.quizapp.utils.NetworkUtils;

@Singleton
public class QuizUseCase {
    @Inject
    public QuizUseCase() {
    }

    @Inject
    QuizApiService apiService;

    @Inject
    Context context;

    public MutableLiveData<Resource<QuizResponse>> getQuizzes(String token) {
        if (NetworkUtils.isConnected(context)) {
            NetworkCall<QuizResponse> call = new NetworkCall<>();
            return call.makeCall(apiService.getQuizzes(10, token));
        } else {
            MutableLiveData<Resource<QuizResponse>> result = new MutableLiveData<>();
            result.setValue(Resource.error(""));
            return result;
        }
    }

    public MutableLiveData<Resource<ApiToken>> getToken() {
        if (NetworkUtils.isConnected(context)) {
            NetworkCall<ApiToken> call = new NetworkCall<>();
            return call.makeCall(apiService.getToken("request"));
        } else {
            MutableLiveData<Resource<ApiToken>> result = new MutableLiveData<>();
            result.setValue(Resource.error("Request failed, Please try again"));
            return result;
        }
    }

}
