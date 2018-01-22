package com.artf.holographic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {


    @BindView(R.id.editText) EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.button)
    public void onListItemClick() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("m", editText.getText().toString());
        startActivity(intent);
    }

}