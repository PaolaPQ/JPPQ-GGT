package jppq.com.project.code.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jppq.com.project.GlobalApplication;
import jppq.com.project.R;
import jppq.com.project.code.holder.AppHolder;
import jppq.com.project.model.AppItem;

/**
 * Created by jesika.perez on 27/02/2017.
 */
public class RecycleAdapter extends RecyclerView.Adapter<AppHolder> {
    private List<AppItem> objects  = new ArrayList<>();
    private AppHolder.OnItemClickListener listener;
    private Context context;
    private int resource;

    public RecycleAdapter(Context context, int resource, List<AppItem> objects, AppHolder.OnItemClickListener listener) {
        this.objects = objects;
        this.listener = listener;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public AppHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        AppHolder appHolder = new AppHolder(v);

        return appHolder;
    }

    @Override
    public void onBindViewHolder(AppHolder appHolder, int position) {
        AppItem object = objects.get(position);

        appHolder.title.setText(object.getTitle());

        if(!object.getImage().isEmpty()) {
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath() + context.getResources().getString(R.string.app_images) + object.getImage();
            Bitmap bitmap = GlobalApplication.getBitmapFromFile(path);

            if(bitmap!=null) {
                appHolder.image.setEnabled(false);
                appHolder.image.setImageBitmap(bitmap);
            }
        }

        appHolder.setOnItemClickListener(objects.get(position), listener);
    }
}