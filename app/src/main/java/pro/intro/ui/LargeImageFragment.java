package pro.intro.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pro.intro.R;
import pro.intro.util.ImageManager;
import pro.intro.util.Intents;
import pro.intro.widget.VolleyImageView;

/**
 * Created by vsvydenko on 27.08.14.
 */
public class LargeImageFragment extends BaseFragment {

    private View returnView;
    private VolleyImageView mVolleyImageView;

    private String largeImageUrl;

    public static LargeImageFragment newInstance(Intent intent) {
        LargeImageFragment fragment = new LargeImageFragment();
        Bundle args = new Bundle(intent.getExtras());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            largeImageUrl = getArguments().getString(Intents.EXTRA_LARGE_IMAGE_URL);
        }
        // Retain this fragment across configuration changes.
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        returnView = inflater.inflate(R.layout.fragment_large_image, container, false);

        return returnView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeContent();
    }

    private void initializeContent() {

        mVolleyImageView = (VolleyImageView) returnView.findViewById(R.id.imageview_imageart);
        mVolleyImageView.setResponseObserver(new VolleyImageView.ResponseObserver() {
            @Override
            public void onError() {

            }

            @Override
            public void onSuccess() {
                hideLoadingIndicator();
            }
        });
        loadLargeImage();
    }

    private void loadLargeImage() {
        showLoadingIndicator();
        mVolleyImageView.setImageUrl(largeImageUrl, ImageManager.loader());
    }
}
