package com.demo.nyschool.detailschool;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.demo.nyschool.pojo.NysDetail;
import com.demo.nyschool.pojo.NysSchool;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class SchoolDetailViewModel extends ViewModel {

    private static final String TAG = "SchoolDetailViewModel";

    private  String URL = "https://data.cityofnewyork.us/resource/f9bf-2cp4.json";
    private MutableLiveData<List<NysDetail>> schoolList = new MutableLiveData<>();

    public MutableLiveData<List<NysDetail>> getSchoolEvent() {
        return schoolList;
    }



    public void getSchoolDetalAPi(AsyncHttpClient asyncClient, String schoolName, Context context) {

        URL = URL+"?school_name="+schoolName.toUpperCase();
        Log.e(TAG, "getSchoolDetalAPi: "+URL );
        asyncClient.get( URL,  new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {


                    String response = new String(responseBody);
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<NysDetail>>(){}.getType();
                    List<NysDetail> posts = gson.fromJson(response, listType);
                    schoolList.setValue(posts);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                schoolList.setValue(null);

            }
        });

    }
}
