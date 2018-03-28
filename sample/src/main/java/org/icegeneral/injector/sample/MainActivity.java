package org.icegeneral.injector.sample;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by linjianjun on 2017/6/5.
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //findViewById(R.id.content);
        //getSupportFragmentManager().beginTransaction()
        //        .replace(R.id.content, new MainFragment())
        //        .commit();

        Button button = (Button) findViewById(R.id.permission);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Click", "request exter_storage read permission");
                ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        });

        Log.d("Click", "onCreate");
    }

    @Override
    public void onClick(View v) {

    }
}
