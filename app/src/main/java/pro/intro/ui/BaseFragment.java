package pro.intro.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import pro.intro.R;

/**
 * Created by vsvydenko on 01.07.14.
 */
public class BaseFragment extends Fragment {

    protected Handler uiHandler;
    private boolean isLoadingIndicator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        uiHandler = new Handler();
    }

    public void showLoadingIndicator() {
        if (isLoadingIndicator || uiHandler == null || mShowLoadingIndicator == null) {
            return;
        }

        uiHandler.postDelayed(mShowLoadingIndicator, 350);
    }

    Runnable mShowLoadingIndicator = new Runnable() {
        public void run() {
            isLoadingIndicator = true;

            View v = getView();
            if (v == null) {
                return;
            }

            View loading = v.findViewById(R.id.loading_indicator);
            if (loading != null) {
                loading.setVisibility(View.VISIBLE);
                loading.bringToFront();
            }

            View content = v.findViewById(R.id.content);
            if (content != null) {
                content.setVisibility(View.GONE);
            }
        }
    };

    public void hideLoadingIndicator() {
        uiHandler.removeCallbacks(mShowLoadingIndicator);
        if (getView() == null) {
            return;
        }
        View v = getView().findViewById(R.id.loading_indicator);
        if (v == null || (v.getVisibility() == View.GONE && !isLoadingIndicator)) {
            return;
        }

        Runnable action = new Runnable() {
            public void run() {
                isLoadingIndicator = false;

                View v = getView();

                if (v == null) {
                    return;
                }

                View loading = v.findViewById(R.id.loading_indicator);
                if (loading != null) {
                    loading.setVisibility(View.GONE);
                }

                View content = v.findViewById(R.id.content);
                if (content != null) {
                    content.setVisibility(View.VISIBLE);
                }
            }
        };

        uiHandler.post(action);
    }

}
