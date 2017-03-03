package jppq.com.project.code.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import jppq.com.project.R;
import jppq.com.project.model.AppItem;

/**
 * Created by jesika.perez on 28/02/2017.
 */
public class AppHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public ImageView image;

    public AppHolder(View v) {
        super(v);

        title = (TextView)v.findViewById(R.id.item_app_title);
        image = (ImageView)v.findViewById(R.id.item_app_image);
    }

    public void setOnItemClickListener(final AppItem item, final OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                int position = getAdapterPosition();
                listener.onItemClick(item, position);
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(AppItem item, int position);
    }
}