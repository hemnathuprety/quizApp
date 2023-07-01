package np.com.hemnath.quizapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

public class NetworkUtils {
    public static Boolean isConnected(Context context) {
        boolean result;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Network networkCapabilities = connectivityManager.getActiveNetwork();
            NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(networkCapabilities);

            if (actNw == null) {
                return false;
            } else {
                if (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    result = true;
                } else if (actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    result = true;
                } else result = actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
            }
        } else {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            result = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }

        return result;
    }

}