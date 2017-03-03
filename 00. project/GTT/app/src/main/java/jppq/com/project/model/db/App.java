package jppq.com.project.model.db;

import com.orm.SugarRecord;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jesika.perez on 27/02/2017.
 */
public class App extends SugarRecord {
    private String title;
    private String summary;
    private String link;
    private long category;
    private String image;

    private long key;
    private String bundle;

    public App() {}

    public App(String title, String summary, String link, long category, String image, long key, String bundle) {
        this.key = key;
        this.bundle = bundle;

        this.title = title;
        this.summary = summary;
        this.link = link;
        this.category = category;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }
}
