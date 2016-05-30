package model.level;

import model.wave.Wave;

import java.util.LinkedList;

/**
 * Created by Guido on 22.05.2016.
 */
public abstract class Level {

    protected LinkedList<Wave> waves = new LinkedList<>();
    protected long lastminioncreation = -1;
    protected long miniontimeout;

    public Level() {
        loadWaves();
    }

    protected void loadNextWave(){

    }

    protected abstract void loadWaves();

    public long getMiniontimeout() {
        return miniontimeout;
    }

    public long getLastminioncreation() {
        return lastminioncreation;
    }

    public void setLastminioncreation(long lastminioncreation) {
        this.lastminioncreation = lastminioncreation;
    }
}
