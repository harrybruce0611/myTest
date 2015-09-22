package hanyang.myapplication.models;

import org.json.JSONObject;

/**
 * Created by hanyang on 9/21/15.
 * Data model
 */
public class TestData {
    private String mImageUrl;
    private String mText;

    public TestData(JSONObject _data) {
        mImageUrl = _data.optString("icon");
        mText = _data.optString("name");
    }

    public String getText() {
        return mText;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

}
