package np.com.hemnath.quizapp.quiz.data.domain;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCall<T> {
    Call<T> call;

    MutableLiveData<Resource<T>> makeCall(Call<T> call) {
        this.call = call;
        CallBackKt<T> callBackKt = new CallBackKt<T>();
        callBackKt.result.setValue(Resource.loading(null));
        this.call.clone().enqueue(callBackKt);
        return callBackKt.result;
    }

    static class CallBackKt<T> implements Callback<T> {
        MutableLiveData<Resource<T>> result = new MutableLiveData<>();

        @Override
        public void onResponse(@NonNull Call<T> call, Response<T> response) {
            if (response.isSuccessful())
                result.setValue(Resource.success(response.body()));
            else {
                result.setValue(Resource.error("Request failed, Please try again"));
            }
        }

        @Override
        public void onFailure(@NonNull Call<T> call, Throwable t) {
            result.setValue(Resource.error("Request failed, Please try again"));
            t.printStackTrace();
        }
    }

    void cancel() {
        call.cancel();
    }
}
