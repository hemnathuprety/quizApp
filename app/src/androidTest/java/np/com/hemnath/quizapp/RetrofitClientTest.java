package np.com.hemnath.quizapp;

import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import np.com.hemnath.quizapp.quiz.data.domain.api.QuizApiService;

@HiltAndroidTest
public class RetrofitClientTest {
    @Rule
    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);

    @Before
    public void init() {
        hiltRule.inject();
    }

    @Inject
    QuizApiService apiService;

    @Test
    public void apiServiceNullTest() {
        assertNull(apiService);
    }
}
