package jppq.com.project.model;

import java.io.Serializable;

/**
 * Created by jesika.perez on 28/02/2017.
 */
public class AppItem implements Serializable {
    private long key;
    private String title;
    private String bundle;
    private String summary;
    private String category;
    private String image;
    private String link;

    public AppItem() {}

    public AppItem(long key, String title, String bundle, String summary, String category, String image, String link) {
        this.key = key;
        this.title = title;
        this.bundle = bundle;
        this.summary = summary;
        this.category = category;
        this.image = image;
        this.link = link;
    }

    public long getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getBundle() {
        return bundle;
    }

    public String getSummary() {
        return summary;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }
}
