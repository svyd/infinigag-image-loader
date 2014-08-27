package pro.intro.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pro.intro.R;
import pro.intro.entity.ImageEntity;
import pro.intro.util.ImageManager;
import pro.intro.util.Intents;
import pro.intro.widget.VolleyImageView;

/**
 * Created by vsvydenko on 27.08.14.
 */
public class ImagePagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<ImageEntity> mItems;

    public ImagePagerAdapter(Context mContext, List<ImageEntity> items) {
        this.mContext = mContext;
        this.mItems = items;
    }

    @Override
    public int getCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_image, container,
                false);

        VolleyImageView volleyImageView = (VolleyImageView) view
                .findViewById(R.id.imageview_imageart);
        TextView titleTextView = (TextView) view.findViewById(R.id.textview_title);

        final ImageEntity imageEntity = mItems.get(position);
        titleTextView.setText(imageEntity.getCaption());
        volleyImageView.setImageUrl(imageEntity.getNormalUrl(),
                ImageManager.loader());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(
                        Intents.getLargeImageIntent(mContext, imageEntity.getLargeUrl()));
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
