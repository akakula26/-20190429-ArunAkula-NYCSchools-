package com.demo.nyschool.nysSchool;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.demo.nyschool.pojo.NysSchool;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {
    private static final String URL = "https://data.cityofnewyork.us/resource/s3k6-pzi2.json";

    private MutableLiveData<List<NysSchool>> schoolList = new MutableLiveData<>();

    public MutableLiveData<List<NysSchool>> getSchoolEvent() {
        return schoolList;
    }

    public void getSchoolAPi(AsyncHttpClient asyncClient) {

        asyncClient.get(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);
                Gson gson = new Gson();
                Type listType = new TypeToken<List<NysSchool>>(){}.getType();
                List<NysSchool> posts = gson.fromJson(response, listType);
                schoolList.setValue(posts);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
