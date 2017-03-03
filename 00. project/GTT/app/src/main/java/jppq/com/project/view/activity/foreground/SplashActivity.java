package jppq.com.project.view.activity.foreground;

import android.os.Handler;
import android.os.Bundle;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import jppq.com.project.GlobalApplication;
import jppq.com.project.R;
import jppq.com.project.code.manager.ORMManager;
import jppq.com.project.code.provider.WebApi;
import jppq.com.project.code.service.ImageService;
import jppq.com.project.model.db.App;
import jppq.com.project.model.db.Category;
import jppq.com.project.view.activity.background.BaseActivity;

/**
 * Created by jesika.perez on 27/02/2017.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        startApplication(3000);
    }

    private void startApplication(int time) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(GlobalApplication.deviceHasConnection(bContext)) {
                    showToast("Dispositivo conectado", false);
                    getAppsInformation();
                } else {
                    showToast("Dispositivo desconectado", false);
                    startApplication();
                }
            }
        }, time);
    }

    private void getAppsInformation() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(WebApi.Timeuot);

        client.get(bContext, WebApi.Url,new JsonHttpResponseHandler() {
            @Override public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    if(response.has("feed")) {
                        JSONObject feed = response.getJSONObject("feed");

                        if (feed.has("entry")) {
                            JSONArray entry = feed.getJSONArray("entry");
                            int count = entry.length();

                            for(int i=0; i<count; i++) {
                                JSONObject data = entry.getJSONObject(i);

                                Category categoryObj = getCategory(data.getJSONObject("category").getJSONObject("attributes"));
                                JSONObject appJson = data.getJSONObject("id").getJSONObject("attributes");
                                JSONArray image = data.getJSONArray("im:image");

                                App appObj = ORMManager.getApp(appJson.getLong("im:id"));

                                if(appObj==null) {
                                    appObj = new App();
                                    appObj.setKey(appJson.getLong("im:id"));
                                }

                                if(image.length() == 3) {
                                    JSONObject imgData = image.getJSONObject(2);
                                    String name = generateImageName(data.getJSONObject("im:name").getString("label"), imgData.getString("label"));

                                    if(name!=null) {
                                        ImageService service = new ImageService(bActivity, imgData.getString("label"), name);

                                        appObj.setImage(name);
                                        service.execute();
                                    }
                                }

                                appObj.setLink(data.getJSONObject("link").getJSONObject("attributes").getString("href"));
                                appObj.setTitle(data.getJSONObject("title").getString("label"));
                                appObj.setSummary(data.getJSONObject("summary").getString("label"));
                                appObj.setBundle(appJson.getString("im:bundleId"));
                                appObj.setCategory(categoryObj.getKey());

                                ORMManager.saveApp(appObj);
                            }
                        }
                    }

                    startApplication();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private Category getCategory(JSONObject json) throws JSONException {
        Category categoryObj = null;

        if(json!=null){
            categoryObj = ORMManager.getCategory(json.getLong("im:id"));

            if(categoryObj==null) {
                categoryObj = new Category(json.getLong("im:id"), json.getString("label"));
                ORMManager.saveCategory(categoryObj);
            }
        }

        return categoryObj;
    }

    private String generateImageName(String original, String extension){
        String name = null;

        if(!original.isEmpty()) {
            name = original.replaceAll("\\s", "").replaceAll("\\W", "").toLowerCase();

            if(extension.toLowerCase().endsWith(".png"))
            {
                name += ".png";
            } else {
                name += ".jpg";
            }
        }

        return name;
    }

    private void startApplication() {
        startAppActivity(true, HomeActivity.class, null);
        finish();
    }
}
