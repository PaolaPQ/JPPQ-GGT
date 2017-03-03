package jppq.com.project.code.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import jppq.com.project.GlobalApplication;
import jppq.com.project.R;
import jppq.com.project.code.holder.AppHolder;
import jppq.com.project.model.AppItem;

/**
 * Created by jesika.perez on 1/03/2017.
 */
public class GridAdapter extends ArrayAdapter<AppItem> {

    private List<AppItem> objects;
    private Context context;
    private int resource;

    /**
     * Instantiates a new Grid adapter.
     *
     * @param context  the context
     * @param resource the resource
     * @param objects  the objects
     */
    public GridAdapter(Context context, int resource, List<AppItem> objects) {
        super(context, resource, objects);

        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    /**
     * Gets custom view.
     *
     * @param position    the position
     * @param convertView the convert view
     * @param parent      the parent
     * @return the custom view
     */
    public View getCustomView(int position, View convertView, ViewGroup parent) {
        AppHolder appHolder;

        if(convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
            appHolder = new AppHolder(convertView);
            convertView.setTag(appHolder);

        }else{
            appHolder = (AppHolder) convertView.getTag();
        }

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

        return convertView;
    }
}