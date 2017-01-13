package com.javierjv.angercontrol;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Start button */
    public void startAnger(View view) {
        final Context mainActivity = this;
        Intent intent = new Intent(mainActivity, Anger.class);
        startActivity(intent);
    }

}
