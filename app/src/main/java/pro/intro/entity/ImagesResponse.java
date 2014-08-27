package pro.intro.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vsvydenko on 27.08.14.
 */
public class ImagesResponse {

    private List<ImageEntity> images = new ArrayList<ImageEntity>();
    private String nextPage;

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public List<ImageEntity> getImages() {
        return images;
    }

    public ImagesResponse() {
    }

    public static ImagesResponse fromJson(JSONObject jsonObject) {
        ImagesResponse imagesResponse = new ImagesResponse();

        try {
            JSONArray data = jsonObject.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {

                JSONObject image = data.getJSONObject(i);
                imagesResponse.getImages().add(ImageEntity.fromJson(image));
            }
            imagesResponse.setNextPage(jsonObject.getJSONObject("paging").optString("next"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return imagesResponse;
    }

}
