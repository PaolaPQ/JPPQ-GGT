package jppq.com.project.model;

/**
 * Created by jesika.perez on 27/02/2017.
 */
public class ListItem {
    private String key;
    private String title;

    public ListItem(String key, String title) {
        this.key = key;
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }
}
