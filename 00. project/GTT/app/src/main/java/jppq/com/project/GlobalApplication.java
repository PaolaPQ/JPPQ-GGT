package jppq.com.project;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.orm.SugarContext;

import java.io.File;

/**
 * Created by jesika.perez on 27/02/2017.
 */
public class GlobalApplication extends Application {
    private GlobalApplication mInstance;

    public static String app_path = "";

    public void GlobalAppication() {}

    public GlobalApplication() {
        mInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    /**
     * Device has connection.
     *
     * @param context the context
     * @return the boolean
     */
    public static Boolean deviceHasConnection(Context context) {
        boolean conecction = false;

        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] networkInfos = cm.getAllNetworkInfo();

            for (NetworkInfo tempNetworkInfo : networkInfos) {
                if (tempNetworkInfo.isConnected()) {
                    conecction = true;
                    break;
                }
            }
        }

        return conecction;
    }

    /**
     * File exists.
     *
     * @param path the path
     * @return the boolean
     */
    public static boolean fileExists(String path) {
        File mediaStorageDir = new File(path);

        if (mediaStorageDir.exists()){
            return true;
        }

        return false;
    }

    /**
     * Gets bitmap from file.
     *
     * @param path the path
     * @return the bitmap from file
     */
    public static Bitmap getBitmapFromFile(String path) {
        if(GlobalApplication.fileExists(path)) {
            File file = new File(path);
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();

            return BitmapFactory.decodeFile(file.getAbsolutePath(),bmOptions);
        }

        return null;
    }
}
