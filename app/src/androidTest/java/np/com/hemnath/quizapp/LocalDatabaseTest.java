package np.com.hemnath.quizapp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import np.com.hemnath.quizapp.quiz.data.local.quiz.QuizDeo;
import np.com.hemnath.quizapp.quiz.data.local.token.TokenDeo;

@HiltAndroidTest
public class LocalDatabaseTest {
    @Rule
    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);

    @Before
    public void init() {
        hiltRule.inject();
    }

    @Inject
    QuizDeo quizDeo;

    @Inject
    TokenDeo tokenDeo;


    @Test
    public void testHaveQuizList() {

    }

    @Test
    public void testHaveAccessToken() {

    }

}
