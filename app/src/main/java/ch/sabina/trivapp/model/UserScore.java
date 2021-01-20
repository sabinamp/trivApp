package ch.sabina.trivapp.model;

import android.util.Log;

public class UserScore {
    private int value;

    public UserScore(int value) {

        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addScore(int v){
        setValue(this.getValue() + v);
    }

    public void decreaseScore(int v){
        if (getValue() > 0) {
            setValue(this.getValue() - v);
        }
        Log.d("Score", "decreaseScore() called. Value is already 0.");
    }
}
