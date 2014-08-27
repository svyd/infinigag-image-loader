package pro.intro.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import pro.intro.R;

/**
 * Created by vsvydenko on 27.08.14.
 */
public class LargeImageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_image);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null) {
            handleIntentExtras(getIntent());
        }
    }

    private void handleIntentExtras(Intent intent) {
        LargeImageFragment largeImageFragment = LargeImageFragment.newInstance(intent);
        getFragmentManager().beginTransaction()
                .add(R.id.container, largeImageFragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
