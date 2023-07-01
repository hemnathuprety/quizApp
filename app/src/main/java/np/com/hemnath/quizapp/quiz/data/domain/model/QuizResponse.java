package np.com.hemnath.quizapp.quiz.data.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import np.com.hemnath.quizapp.quiz.data.local.quiz.entity.QuizEntity;

public class QuizResponse {
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("results")
    @Expose
    private List<QuizModel> results;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public List<QuizModel> getResults() {
        return results;
    }

    public void setResults(List<QuizModel> results) {
        this.results = results;
    }

    public List<QuizEntity> toQuizEntityList() {
        List<QuizEntity> quizzes = new ArrayList<>();
        if (results != null && !results.isEmpty()) {
            for (QuizModel element : results) {
                quizzes.add(element.toQuizEntity());
            }
        }
        return quizzes;
    }

    public List<QuizModel> fromQuizEntityList(List<QuizEntity> allQuizzes) {
        List<QuizModel> quizzes = new ArrayList<>();
        if (allQuizzes != null && !allQuizzes.isEmpty()) {
            for (QuizEntity element : allQuizzes) {
                quizzes.add(element.toQuizModel());
            }
        }
        return quizzes;
    }
}
