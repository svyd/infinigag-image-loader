package pro.intro.ui;


import android.app.Activity;
import android.os.Bundle;

import pro.intro.R;


public class ListImagesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_images);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ListImageFragment())
                    .commit();
        }
    }
}
