package model.gameobject.tower;

/**
 * Created by Guido on 08.05.2016.
 */
public enum BuildStatus {
    VALID("Tower is Valid"), INVALID("Tower is invalid"), BUILD("Tower is Build");

    private String status;

    BuildStatus(String status) {
        this.status = status;
    }
}
