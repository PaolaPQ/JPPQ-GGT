package jppq.com.project.model.db;

import com.orm.SugarRecord;

/**
 * Created by jesika.perez on 27/02/2017.
 */
public class Category extends SugarRecord {
    private long key;
    private String label;

    public Category() {}

    public Category(long key, String label) {
        this.key = key;
        this.label = label;
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
