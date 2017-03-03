package jppq.com.project.view.activity.background;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by jesika.perez on 27/02/2017.
 */
public class BaseActivity extends AppCompatActivity implements Animation.AnimationListener {

    /**
     * The base attribute - context.
     */
    public Context bContext;
    /**
     * The base attribute - context.
     */
    public BaseActivity bActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bActivity = this;
        bContext = getApplicationContext();

        if (isTablet(bContext)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {}

    @Override
    public void onAnimationEnd(Animation animation) {}

    @Override
    public void onAnimationRepeat(Animation animation) {}

    /**
     * Is tablet boolean.
     *
     * @param context the context
     * @return the boolean
     */
    public boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * Load animation.
     *
     * @param view the view
     */
    public void LoadAnimation(ViewGroup view, Animation animation){
        if(animation!=null) {
            animation.setAnimationListener(this);
            view.startAnimation(animation);
        }
    }

    /**
     * Show toast.
     *
     * @param message   the message
     * @param showShort the show short
     */
    public void showToast(final String message, final Boolean showShort) {
        if(showShort) {
            Toast.makeText(bContext, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(bContext, message, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Start app activity.
     *
     * @param withHistory the with history
     * @param activity    the activity
     * @param extras      the extras
     */
    public void startAppActivity(Boolean withHistory, Class activity, HashMap<String, String> extras) {
        Intent newIntent = new Intent(bContext, activity);

        if(!withHistory) {
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }

        if(extras!=null && extras.size()>0) {
            for (String key:extras.keySet()) {
                newIntent.putExtra(key, extras.get(key));
            }
        }

        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        bContext.startActivity(newIntent);
    }
}
