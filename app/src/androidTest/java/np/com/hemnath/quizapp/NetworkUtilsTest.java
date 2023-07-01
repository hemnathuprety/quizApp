package np.com.hemnath.quizapp;

import static org.junit.Assert.assertEquals;
import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Test;

import np.com.hemnath.quizapp.utils.NetworkUtils;

public class NetworkUtilsTest {
    @Test
    public void network_isConnected() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Boolean isConnected = NetworkUtils.isConnected(appContext);
        assertEquals(true, isConnected);
    }
}
