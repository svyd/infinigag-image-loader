package pro.intro.util;

import android.content.Context;
import android.content.Intent;

import pro.intro.ui.LargeImageActivity;

/**
 * Created by vsvydenko on 27.08.14.
 */
public class Intents {

    public static final String EXTRA_LARGE_IMAGE_URL = "extra_large_image_url";

    public static Intent getLargeImageIntent(Context context, String largeImageUrl) {
        Intent intent = new Intent(context, LargeImageActivity.class);
        intent.putExtra(EXTRA_LARGE_IMAGE_URL, largeImageUrl);

        return intent;
    }

}
