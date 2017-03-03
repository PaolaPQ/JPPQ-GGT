package jppq.com.project.code.manager;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import jppq.com.project.model.AppItem;
import jppq.com.project.model.db.App;
import jppq.com.project.model.db.Category;

/**
 * Created by jesika.perez on 28/02/2017.
 */
public class ORMManager {

    /**
     * Delete apps int.
     *
     * @return the int
     */
    public static int deleteApps() {
        return App.deleteAll(App.class);
    }

    /**
     * Delete categories int.
     *
     * @return the int
     */
    public static int deleteCategories() {
        return Category.deleteAll(Category.class);
    }

    /**
     * Gets category.
     *
     * @param key the key
     * @return the category
     */
    public static Category getCategory(Long key) {
        String[] args = {Long.toString(key), Integer.toString(1)};
        List<Category> categories = Category.find(Category.class, "key=? limit ?", args);

        if (categories.size() > 0) {
            return categories.get(0);
        }

        return null;
    }

    /**
     * Gets app.
     *
     * @param key the key
     * @return the app
     */
    public static App getApp(Long key) {
        String[] args = {Long.toString(key), Integer.toString(1)};
        List<App> apps = App.find(App.class, "key=? limit ?", args);

        if (apps.size() > 0) {
            return apps.get(0);
        }

        return null;
    }

    /**
     * Gets apps - dto.
     *
     * @return the apps dto
     */
    public static List<AppItem> getAppsDTO() {
        List<App> apps = Lists.newArrayList(App.findAll(App.class));
        List<AppItem> appItems = new ArrayList<>();

        if (apps.size() > 0) {
            for (App app:apps) {
                Category category = getCategory(app.getCategory());

                if(category!=null) {
                    appItems.add(new AppItem(app.getKey(), app.getTitle(), app.getBundle(), app.getSummary(), category.getLabel(), app.getImage(), app.getLink()));
                }
            }
        }

        return appItems;
    }

    /**
     * Gets categories.
     *
     * @return the categories
     */
    public static List<Category> getCategories() {
        return Lists.newArrayList(Category.findAll(Category.class));
    }

    /**
     * Save category long.
     *
     * @param category the category
     * @return the long
     */
    public static long saveCategory(Category category) {
        return Category.save(category);
    }

    /**
     * Save app long.
     *
     * @param app the app
     * @return the long
     */
    public static long saveApp(App app) {
        return App.save(app);
    }
}
