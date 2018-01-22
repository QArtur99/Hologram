package com.artf.holographic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout gamePanelView = findViewById(R.id.content_frame);

        String mString = getIntent().getExtras().get("m").toString();
        ViewPanel gamePanel = new ViewPanel(MainActivity.this, Integer.valueOf(mString));
        gamePanelView.addView(gamePanel);
    }
}

