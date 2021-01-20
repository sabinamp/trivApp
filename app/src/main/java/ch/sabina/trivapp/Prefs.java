package ch.sabina.trivapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private SharedPreferences preferences;

    public Prefs(Activity activity) {
        this.preferences = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public void saveLastScore(int score) {
        int lastScore = preferences.getInt("last_score", 0);

            //we have a new highest and we save it!
            preferences.edit().putInt("last_score", score).apply();

    }

    public int getSavedScore() {
        return preferences.getInt("last_score", 0);
    }

    public  void setIndex(int index) {
        preferences.edit().putInt("index_state", index).apply();
    }

    public int getIndex() {
        return preferences.getInt("index_state", 0);
    }
}
