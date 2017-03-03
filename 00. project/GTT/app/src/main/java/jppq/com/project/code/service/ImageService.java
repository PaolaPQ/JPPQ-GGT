package jppq.com.project.code.service;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import cz.msebera.android.httpclient.Header;
import jppq.com.project.GlobalApplication;
import jppq.com.project.R;
import jppq.com.project.code.provider.WebApi;
import jppq.com.project.view.activity.background.BaseActivity;

/**
 * Created by jesika.perez on 27/02/2017.
 */
public class ImageService extends AsyncTask<Void, Void, Void> {
    private Context context;
    private String url;
    private String name;

    public ImageService(BaseActivity activity, String url, String name) {
        this.url = url;
        this.name = name;
        this.context = activity.bContext;
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected Void doInBackground(Void... params) {
        if(GlobalApplication.deviceHasConnection(context)) {
            downloadImage();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {}

    private void downloadImage() {
        SyncHttpClient client = new SyncHttpClient();
        client.setTimeout(WebApi.Timeuot);

        client.get(url, new BinaryHttpResponseHandler() {
            @Override public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {
                File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath() + context.getResources().getString(R.string.app_images));
                File file = new File(path, name);

                try {
                    if(!path.exists()) {
                        path.mkdirs();
                    }

                    OutputStream os = new FileOutputStream(file);
                    os.write(binaryData);
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
                error.printStackTrace();
            }
        });
    }
}