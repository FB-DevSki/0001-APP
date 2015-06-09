package com.example.android.smsalert4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Sibe Jan on 9-6-2015.
 */
public class ActivityLogout extends Activity {
    Button btnLogout;

    private String state;
    private String command;
    ArrayList<String> status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        btnLogout = (Button) findViewById(R.id.btnLogout);

        status = getIntent().getStringArrayListExtra("status");
        state = status.get(0);
        command = status.get(1);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                command = "close";
                status =new ArrayList<String>();
                status.add(0,state);
                status.add(1,command);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("status", status);
                startActivity(intent);
            };
        });
    }
}
