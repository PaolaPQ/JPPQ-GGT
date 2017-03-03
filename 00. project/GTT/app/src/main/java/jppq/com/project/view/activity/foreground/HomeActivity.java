package jppq.com.project.view.activity.foreground;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import jppq.com.project.R;
import jppq.com.project.code.adapter.GridAdapter;
import jppq.com.project.code.adapter.RecycleAdapter;
import jppq.com.project.code.holder.AppHolder;
import jppq.com.project.code.manager.ORMManager;
import jppq.com.project.model.AppItem;
import jppq.com.project.view.activity.background.BaseActivity;

/**
 * Created by jesika.perez on 27/02/2017.
 */
public class HomeActivity extends BaseActivity {

    private ViewGroup view;
    private ImageView btnSearch;
    private RecyclerView recycler;
    private GridView grid;

    private List<AppItem> apps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        view = (ViewGroup) findViewById(R.id.container);
        LoadAnimation(view, AnimationUtils.loadAnimation(bContext, R.anim.fade_in));

        apps = ORMManager.getAppsDTO();

        if(apps.size() > 0) {

            if(bContext.getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                recycler = (RecyclerView) findViewById(R.id.home_recycle);

                recycler.setHasFixedSize(true);
                recycler.setLayoutManager(layoutManager);
                recycler.setAdapter(new RecycleAdapter(bContext, R.layout.layout_item_app, apps, new AppHolder.OnItemClickListener() {
                    @Override public void onItemClick(AppItem item, int position) {
                        showDetail(item);
                    }
                }));

            } else {
                grid = (GridView) findViewById(R.id.home_grid);
                grid.setAdapter( new GridAdapter(bContext, R.layout.layout_item_app, apps));
                grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override public void onItemClick(AdapterView<?> adapter, View view, int position, long l) {
                        showDetail((AppItem) adapter.getItemAtPosition(position));
                    }
                });
            }

            btnSearch = (ImageView) findViewById(R.id.home_btn_menu);
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    startAppActivity(true, MenuActivity.class, null);
                }
            });
        }
    }

    private void showDetail(AppItem app) {
        Intent intent = new Intent(bContext, DetailActivity.class);
        intent.putExtra("app", app);
        startActivity(intent);
    }
}
