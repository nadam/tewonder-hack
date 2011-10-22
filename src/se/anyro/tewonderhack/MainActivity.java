package se.anyro.tewonderhack;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	
		public GameView gameView;
		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.gameView = new GameView(this);
        setContentView(this.gameView);
    }
}