package model.core.medicine;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marcin on 2016-01-20.
 */
public enum ECategory {
    DRESSING (1),
    PAINKILLER (2),
    OTHERS (3); //i wiele innych...

    private static final Map<Integer,ECategory> lookup = new HashMap<>();
    static {
        for (ECategory c : EnumSet.allOf(ECategory.class)) {
            lookup.put(c.getId(), c);
        }
    }

    private int id;
    ECategory(int id) {
        this.id = id;
    }

    public int getId() { return id; }

    public static ECategory get(int id) {
        return lookup.get(id);
    }

}
