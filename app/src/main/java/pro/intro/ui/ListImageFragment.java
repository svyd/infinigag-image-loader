package pro.intro.ui;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pro.intro.R;
import pro.intro.entity.ImageEntity;
import pro.intro.entity.ImagesResponse;
import pro.intro.util.RequestManager;
import pro.intro.util.Utils;

/**
 * Created by vsvydenko on 27.08.14.
 */
public class ListImageFragment extends BaseFragment {


    private View returnView;

    private boolean restore;
    private ImagePagerAdapter mImagesAdapter;
    private String page = "0";
    private List<ImageEntity> mImageEntityList;
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retain this fragment across configuration changes.
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        returnView = inflater.inflate(R.layout.fragment_list_images, container, false);

        return returnView;
    }

    @Override
    public void onPause() {
        restore = true;
        super.onPause();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeContent();
    }

    private void initializeContent() {

        mViewPager = (ViewPager) returnView.findViewById(R.id.view_pager);

        if (restore && mImageEntityList != null && mImageEntityList.size() > 0) {
            updateUI();
            restore = false;
        } else {
            loadImages();
        }
        returnView.findViewById(R.id.empty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImages(page);
            }
        });
    }

    private void loadImages() {
        showLoadingIndicator();
        loadImages("0");
    }

    private void loadImages(String page) {
        if (!Utils.haveInternet(getActivity())) {
            showEmptyView();
            return;
        }
        jsonTestRequest(page);
    }

    private void jsonTestRequest(String page) {
        RequestManager.getInstance().doRequest().trendingImages(page, listener, errorListener);
    }

    Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(final JSONObject jsonObject) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (mImageEntityList == null || page.equals("0")) {
                        mImageEntityList = new ArrayList<ImageEntity>();
                    }
                    ImagesResponse mImageResponse = ImagesResponse.fromJson(jsonObject);
                    final List<ImageEntity> response = mImageResponse.getImages();
                    page = mImageResponse.getNextPage();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mImageEntityList.addAll(response);
                            updateUI();
                        }
                    });
                }
            }).start();

        }
    };

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            showEmptyView();
        }
    };

    private void updateUI() {

        if (mImagesAdapter == null) {
            mImagesAdapter = new ImagePagerAdapter(getActivity(), mImageEntityList);
        }

        hideLoadingIndicator();
        if (mViewPager.getAdapter() == null) {
            mViewPager.setAdapter(mImagesAdapter);
        } else {
            mImagesAdapter.notifyDataSetChanged();
        }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                    int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (canLoadMore() && position < 10 && 10 - position <= 3) {
                    loadImages(page);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private boolean canLoadMore() {
        return mImageEntityList.size() >= 20 ? false : true;
    }

}
