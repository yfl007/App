package nick.example.simplelauncher;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
/**
 * Author: Yefenglin on 2016/6/22  14:20
 * E-mail: ye_fenglin@163.com
 */
public class HomeScreen extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }
}
