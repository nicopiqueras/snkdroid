/**
 * Anova IT Consulting 2011
 *
 * This file is licensed under the GPL version 3.
 * Please refer to the URL http://www.gnu.org/licenses/gpl-3.0.html for details.
 */

package es.anovagroup.moviles.modulo2.snkdroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class SnakeActivity extends Activity {

    private static final String TAG = "snkdroid";
    
    private SharedPreferences mPrefs;
    public int saved_score;
    public String scoreboard_title;
    
    /**
     * Called when the activity is first created.
     * 
     * @param savedState
     *            If the activity is being re-initialized after previously being
     *            shut down then this Bundle contains the data it most recently
     *            supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it
     *            is null.</b>
     */
    @Override
    public void onCreate(final Bundle savedState) {
        super.onCreate(savedState);
        
        this.scoreboard_title = getString(R.string.score_title);
        mPrefs = getSharedPreferences("snake.prefs", MODE_PRIVATE);
        this.saved_score = mPrefs.getInt("score", 0);
        
        Log.i(TAG, "onCreate");
        setContentView(new SnakeView(this));
    }
    
    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor ed = mPrefs.edit();
        ed.putInt("score", this.saved_score);
        ed.commit();
    }
    
    public void update_score(boolean is_game_over){
        if(is_game_over){
            this.saved_score = 0;
        }else{
            this.saved_score += 10;
        }
    }
}
