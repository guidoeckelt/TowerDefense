package model.map;

/**
 * Created by Guido on 22.05.2016.
 */
public enum AvailableMaps {
    FirstMap("First Map");

    private String name;

    AvailableMaps(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
