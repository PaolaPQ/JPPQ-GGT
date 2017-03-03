package jppq.com.project.view.activity.foreground;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import jppq.com.project.GlobalApplication;
import jppq.com.project.R;
import jppq.com.project.model.AppItem;
import jppq.com.project.view.activity.background.BaseActivity;

/**
 * Created by jesika.perez on 27/02/2017.
 */
public class DetailActivity extends BaseActivity {

    private AppItem app;

    private ViewGroup view;
    public TextView title;
    public TextView summary;
    public TextView category;

    public ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        view = (ViewGroup) findViewById(R.id.container);
        LoadAnimation(view, AnimationUtils.loadAnimation(bContext, R.anim.slide_down));

        if(hasExtras()) {
            image = (ImageView) findViewById(R.id.detail_img);

            title = (TextView) findViewById(R.id.detail_title);
            summary = (TextView) findViewById(R.id.detail_summary);
            category = (TextView) findViewById(R.id.detail_category);

            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath() + bContext.getResources().getString(R.string.app_images) + app.getImage();
            Bitmap bitmap = GlobalApplication.getBitmapFromFile(path);

            if(bitmap!=null) {
                image.setEnabled(false);
                image.setImageBitmap(bitmap);
            }

            title.setText(app.getTitle());
            summary.setText(app.getSummary());
            category.setText(app.getCategory());
        }
    }

    private boolean hasExtras() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            app = (AppItem) extras.getSerializable("app");
            return true;
        }

        return false;
    }
}
