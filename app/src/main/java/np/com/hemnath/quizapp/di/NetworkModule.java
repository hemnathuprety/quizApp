package np.com.hemnath.quizapp.di;

import android.content.Context;

import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import np.com.hemnath.quizapp.application.QuizApplication;
import np.com.hemnath.quizapp.quiz.data.domain.api.QuizApiService;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Singleton
    @Provides
    public QuizApplication provideApplication(@ApplicationContext Context app) {
        return (QuizApplication) app;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl("https://opentdb.com/").client(client).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build();
    }

    private final long READ_TIMEOUT = 30L;
    private final long WRITE_TIMEOUT = 30L;
    private final long CONNECTION_TIMEOUT = 10L;
    private final long CACHE_SIZE_BYTES = 10 * 1024 * 1024L; // 10 MB

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Interceptor headerInterceptor, Cache cache) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient().newBuilder();
        okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        okHttpClientBuilder.cache(cache);
        okHttpClientBuilder.addInterceptor(headerInterceptor);
        okHttpClientBuilder.addInterceptor(interceptor);

        return okHttpClientBuilder.build();
    }


    @Provides
    @Singleton
    public Interceptor provideHeaderInterceptor() {
        return chain -> {
            Request originalRequest = chain.request();

            // Create a new request builder based on the original request
            Request.Builder requestBuilder = originalRequest.newBuilder();

            // Add headers here
            //requestBuilder.header("HeaderName", "HeaderValue");

            // Proceed with the request
            Request modifiedRequest = requestBuilder.build();
            return chain.proceed(modifiedRequest);
        };
    }


    @Provides
    @Singleton
    protected Cache provideCache(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir().getAbsolutePath(), "HttpCache");
        return new Cache(httpCacheDirectory, CACHE_SIZE_BYTES);
    }

    @Provides
    @Singleton
    public Context provideContext(QuizApplication application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    QuizApiService provideQuizApi(Retrofit retrofit) {
        return retrofit.create(QuizApiService.class);
    }
}

