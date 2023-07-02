package np.com.hemnath.quizapp.quiz.data.domain.api;

import np.com.hemnath.quizapp.quiz.data.domain.model.ApiToken;
import np.com.hemnath.quizapp.quiz.data.domain.model.QuizResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuizApiService {
    @GET("api.php")
    Call<QuizResponse> getQuizzes(@Query("amount") int amount, @Query("token") String token);

    @GET("api_token.php")
    Call<ApiToken> getToken(@Query("command") String command);

    @GET("api_token.php")
    Call<ApiToken> getResetToken(@Query("command") String command, @Query("token") String token);
}