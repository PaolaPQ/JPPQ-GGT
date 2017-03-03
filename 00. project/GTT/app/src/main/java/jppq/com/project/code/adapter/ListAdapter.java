package jppq.com.project.code.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import jppq.com.project.R;
import jppq.com.project.model.ListItem;
import jppq.com.project.model.db.Category;

/**
 * Created by jesika.perez on 27/02/2017.
 */
public class ListAdapter extends ArrayAdapter<Category> {
    private List<Category> objects;
    private Context context;
    private int resource;

    public ListAdapter(Context context, int resource, List<Category> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(resource, parent, false);

        Category data = objects.get(position);
        TextView title = (TextView) view.findViewById(R.id.title);

        title.setText(data.getLabel());

        return view;
    }
}