package com.Tic_tac_toe_for_kids.pro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class RulesActivity extends AppCompatActivity {

    TextView rules_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        getSupportActionBar().hide();

        rules_content=(TextView)findViewById(R.id.content);
        rules_content.setText(R.string.rules_content);
    }
}