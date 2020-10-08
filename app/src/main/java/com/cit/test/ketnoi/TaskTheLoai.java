package com.cit.test.ketnoi;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TaskTheLoai extends AsyncTask<String,String,String> {

    String URL;
    private NetworkResponseListener networkResponseListener;
    public TaskTheLoai(NetworkResponseListener networkResponseListener, String URL){
        this.URL=URL;
        this.networkResponseListener=networkResponseListener;
    }

    @Override
    protected String doInBackground(String... strings) {

        OkHttpClient client=new OkHttpClient();
        client.retryOnConnectionFailure();
        client.newBuilder().connectTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(500,TimeUnit.SECONDS)
                .readTimeout(3000,TimeUnit.SECONDS)
                .build();

        //Calling Demo Data APi
        Request request=new  Request.Builder().url(URL).build();
        Response response=null;

        try {
            response=client.newCall(request).execute();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        if(response!=null && response.isSuccessful()){
            try{
                if(response.body()!=null){
                    return response.body().string();
                }
                else{
                    return  null;
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s!=null){
            networkResponseListener.SuccessData(s);
        }
        else{
            networkResponseListener.FailedData();
        }
    }

}
