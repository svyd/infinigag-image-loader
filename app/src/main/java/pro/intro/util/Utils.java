package pro.intro.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by vsvydenko on 28.08.14.
 */
public class Utils {

    /**
     * Check if Internet connection available
     *
     * @return true is internet connected
     */
    public static boolean haveInternet(Context context) {
        if (context == null) {
            return false;
        }

        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null || !info.isConnected()) {
            return false;
        }
        if (info.isRoaming()) {
            // here is the roaming option you can change it if you want to disable
            // internet while roaming, just return false
            return true;
        }
        return true;
    }

}
