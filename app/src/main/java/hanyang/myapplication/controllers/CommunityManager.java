package hanyang.myapplication.controllers;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hanyang.myapplication.controllers.helpers.DataListener;
import hanyang.myapplication.models.TestData;

/**
 * Created by hanyang on 9/21/15.
 * Doing Network calls, get necessary data
 */
public class CommunityManager {
    private Context mContext;
    private ArrayList<TestData> mData = new ArrayList<TestData>();
    private DataListener mListener;

    public CommunityManager(Context context){
        mContext = context;
        mListener = (DataListener)context;
    }

    public void getTestData() {
        String url = "https://guidebook.com/service/v2/upcomingGuides/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response) {
                        mData.clear();
                        try {
                            JSONArray jsonArray = response.optJSONArray("data");
                            for(int i=0;i<jsonArray.length();i++){
                                mData.add(new TestData(jsonArray.getJSONObject(i)));
                            }
                            mListener.updateData(mData);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mListener.updateData(mData);
                            Toast.makeText(mContext,"Network issue, please try again later",Toast.LENGTH_LONG).show();
                        }
        });
        Volley.newRequestQueue(mContext).add(jsonObjectRequest);
    }

    public ArrayList<TestData> getData(){
        return mData;
    }

}
