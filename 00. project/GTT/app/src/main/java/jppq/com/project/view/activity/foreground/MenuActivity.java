package jppq.com.project.view.activity.foreground;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import jppq.com.project.R;
import jppq.com.project.code.adapter.ListAdapter;
import jppq.com.project.code.manager.ORMManager;
import jppq.com.project.model.db.Category;
import jppq.com.project.view.activity.background.BaseActivity;

/**
 * Created by jesika.perez on 27/02/2017.
 */
public class MenuActivity extends BaseActivity {

    private ViewGroup view;

    private ListView list;
    private List<Category> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        view = (ViewGroup) findViewById(R.id.container);
        LoadAnimation(view, AnimationUtils.loadAnimation(bContext, R.anim.bounce));

        categories = ORMManager.getCategories();

        if(categories.size() > 0) {
            list = (ListView) findViewById(R.id.menu_list);
            list.setAdapter( new ListAdapter(bContext, R.layout.layout_item_category, categories));
        }
    }
}
