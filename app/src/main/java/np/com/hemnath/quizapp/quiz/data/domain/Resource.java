package np.com.hemnath.quizapp.quiz.data.domain;

public class Resource<T> {
    Status status;
    T data;
    String apiError;

    public Resource(Status status, T data, String apiError) {
        this.status = status;
        this.data = data;
        this.apiError = apiError;
    }

    public Resource() {

    }

    public enum Status {
        SUCCESS, ERROR, LOADING
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getApiError() {
        return apiError;
    }

    public void setApiError(String apiError) {
        this.apiError = apiError;
    }

    public static <T> Resource<T> success(T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String apiError) {
        return new Resource<>(Status.ERROR, null, apiError);
    }

    public static <T> Resource<T> loading(T data) {
        return new Resource<>(Status.LOADING, data, null);
    }
}
