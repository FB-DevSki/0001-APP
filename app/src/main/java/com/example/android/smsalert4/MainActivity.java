package com.example.android.smsalert4;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.provider.Telephony;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    IncomingSms mReceiver = new IncomingSms();

    private SharedPreferences preferenceSettings;
    private SharedPreferences.Editor preferencesEditor;
    private static final int PREFERENCES_PRIVATE = 0;
    public static final String PREFS_NAME = "SMS_ALARM_PREFS";

    private String name, group, alarm_code;
    private String part1, telnr1, part2, telnr2, part3, telnr3;
    private Button btnOpt1, btnOpt2, btnOpt3, btnOpt4;
    private String state, command;
    private ArrayList<String> status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);

        btnOpt1 = (Button) findViewById(R.id.btn1);
        btnOpt2 = (Button) findViewById(R.id.btn2);
        btnOpt3 = (Button) findViewById(R.id.btn3);
        btnOpt4 = (Button) findViewById(R.id.btn4);

        btnOpt2.setEnabled(false);
        btnOpt3.setEnabled(false);

        status = new ArrayList<>();
        state ="";
        command = "";
        status.add(0,state);
        status.add(1,command);

        Intent intent = getIntent();
        if (intent.hasExtra("status")) {
            status = intent.getStringArrayListExtra("status");
            state = status.get(0);
            command = status.get(1);
            if (command.equals("register")) {
                this.registerReceiver(mReceiver, intentFilter);
                btnOpt2.setEnabled(true);
                btnOpt3.setEnabled(true);
                btnOpt1.setEnabled(false);
                state = "registered";
            }
            else if (command.equals("close")){
                if (state.equals("registered")) {
                    unregisterReceiver(mReceiver);
                    state="unregistered";
                }
                btnOpt1.setEnabled(true);
                btnOpt2.setEnabled(false);
                btnOpt3.setEnabled(false);
            }
        }

        preferenceSettings = getPreferences(PREFERENCES_PRIVATE);
        preferencesEditor = preferenceSettings.edit();
        name = preferenceSettings.getString("name", "");
        group = preferenceSettings.getString("group", "");
        alarm_code = preferenceSettings.getString("alarm_code", "");
        part1 = preferenceSettings.getString("part1", "");
        telnr1 = preferenceSettings.getString("telNr1", "");
        part2 = preferenceSettings.getString("part2", "");
        telnr2 = preferenceSettings.getString("telNr2", "");
        part3 = preferenceSettings.getString("part3", "");
        telnr3 = preferenceSettings.getString("telNr3", "");
        preferencesEditor.commit();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBtnOpt1Clicked (View view){
        Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
        intent.putExtra("status", status);
        startActivity(intent);
    }
    public void onBtnOpt2Clicked (View view) {
        Intent intent = new Intent(getApplicationContext(), ActivityAlarm.class);
        intent.putExtra("status", status);
        startActivity(intent);
    }
    public void onBtnOpt3Clicked (View view) {
        Intent intent = new Intent(getApplicationContext(), ActivityLogout.class);
        intent.putExtra("status", status);
        startActivity(intent);
    }
    public void onBtnOpt4Clicked (View view) {
        Intent intent = new Intent(getApplicationContext(), ActivityTelNrs.class);
        intent.putExtra("status", status);
        startActivity(intent);
    }
}
