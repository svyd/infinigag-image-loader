package pro.intro;

import android.app.Application;

import pro.intro.util.RequestManager;

/**
 * Created by vsvydenko on 27.08.14.
 */
public class TestTaskApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // RequestManager initialization
        RequestManager.getInstance(getApplicationContext());
    }
}
