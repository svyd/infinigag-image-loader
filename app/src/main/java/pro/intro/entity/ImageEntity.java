package pro.intro.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vsvydenko on 27.08.14.
 */
public class ImageEntity {

    String mCaption;
    String mNormalUrl;
    String mLargeUrl;

    public ImageEntity() {
    }

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public String getNormalUrl() {
        return mNormalUrl;
    }

    public void setNormalUrl(String normalUrl) {
        mNormalUrl = normalUrl;
    }

    public String getLargeUrl() {
        return mLargeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        mLargeUrl = largeUrl;
    }

    public static ImageEntity fromJson(JSONObject jsonObject) {
        ImageEntity imageEntity = new ImageEntity();

        try {
            imageEntity.setCaption(jsonObject.optString("caption"));
            imageEntity.setNormalUrl(jsonObject.getJSONObject("images").
                    optString("normal"));
            imageEntity.setLargeUrl(jsonObject.getJSONObject("images").
                    optString("large"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return imageEntity;

    }

}
