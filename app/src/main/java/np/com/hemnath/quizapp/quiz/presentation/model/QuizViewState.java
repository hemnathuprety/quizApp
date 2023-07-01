package np.com.hemnath.quizapp.quiz.presentation.model;

public class QuizViewState {
    public Boolean isLoading;
    public Boolean isTokenFetch;
    public Boolean isError;
    public String errorMessage;
    public Boolean isQuizCompleted;
    public int correctAnswerCount;
    public Boolean isGetStarted;
    public Boolean isStartAgain;

    public QuizViewState() {
        this.isLoading = false;
        this.isTokenFetch = false;
        this.isQuizCompleted = false;
        this.isGetStarted = false;
        this.isStartAgain = false;
        this.correctAnswerCount = 0;
        this.isError = false;
        this.errorMessage = "";
    }

    public Boolean getLoading() {
        return isLoading;
    }

    public void setLoading(Boolean loading) {
        isLoading = loading;
    }

    public Boolean getError() {
        return isError;
    }

    public void setError(Boolean error) {
        isError = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getQuizCompleted() {
        return isQuizCompleted;
    }

    public void setQuizCompleted(Boolean quizCompleted) {
        isQuizCompleted = quizCompleted;
    }

    public Boolean getTokenFetch() {
        return isTokenFetch;
    }

    public void setTokenFetch(Boolean tokenFetch) {
        isTokenFetch = tokenFetch;
    }

    public Boolean getGetStarted() {
        return isGetStarted;
    }

    public void setGetStarted(Boolean getStarted) {
        isGetStarted = getStarted;
    }

    public int getCorrectAnswerCount() {
        return correctAnswerCount;
    }

    public void setCorrectAnswerCount(int correctAnswerCount) {
        this.correctAnswerCount = correctAnswerCount;
    }

    public Boolean getStartAgain() {
        return isStartAgain;
    }

    public void setStartAgain(Boolean startAgain) {
        isStartAgain = startAgain;
    }
}
