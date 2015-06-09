package com.example.android.smsalert4;

/**
 * Created by Sibe Jan on 8-6-2015.
 */

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.telephony.TelephonyManager;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
        import android.widget.ToggleButton;

        import java.util.ArrayList;

public class ActivityLogin extends Activity {

    private EditText name, group, alarm_code;
    private String part1, part2, part3;
    private String telnr1, telnr2, telnr3;
    private Button btnLogin, btnsave;
    private Context context;

    private String state;
    private String command;
    ArrayList<String> status;

    private SharedPreferences preferenceSettings;
    private SharedPreferences.Editor preferencesEditor;
    private static final int PREFERENCES_PRIVATE = 0;
    public static final String PREFS_NAME = "SMS_ALARM_PREFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = (EditText) findViewById(R.id.name);
        group = (EditText) findViewById(R.id.group);
        alarm_code = (EditText) findViewById(R.id.alarm_code);

        context = getApplicationContext();
        btnsave = (Button) findViewById(R.id.btnsave);
        btnLogin = (Button) findViewById(R.id.btn_login);

        status = getIntent().getStringArrayListExtra("status");
        state = status.get(0);
        command = status.get(1);

        preferenceSettings = getSharedPreferences(PREFS_NAME,context.MODE_PRIVATE);
        name.setText(preferenceSettings.getString("name",""));
        group.setText(preferenceSettings.getString("group", ""));
        alarm_code.setText(preferenceSettings.getString("alarm_code",""));
        part1 = preferenceSettings.getString("part1","");
        telnr1 =preferenceSettings.getString("telnr1", "");
        part2 = preferenceSettings.getString("part2","");
        telnr2 = preferenceSettings.getString("telnr2","");
        part3 = preferenceSettings.getString("part3","");
        telnr3 = preferenceSettings.getString("telnr3", "");

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferenceSettings = getSharedPreferences(PREFS_NAME,context.MODE_PRIVATE);
                preferencesEditor = preferenceSettings.edit();
                preferencesEditor.putString("name", name.getText().toString());
                preferencesEditor.putString("group", group.getText().toString());
                preferencesEditor.putString("alarm_code", alarm_code.getText().toString());
                preferencesEditor.putString("part1", part1);
                preferencesEditor.putString("telnr1", telnr1);
                preferencesEditor.putString("part2", part2);
                preferencesEditor.putString("telnr2", telnr2);
                preferencesEditor.putString("part3", part3);
                preferencesEditor.putString("telnr3", telnr3);
                preferencesEditor.commit();
                Toast msg = Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_LONG);
                msg.show();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String command = "register";
                status =new ArrayList<String>();
                status.add(0, state);
                status.add(1,command);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("status",status);
                startActivity(intent);
            }
        });
    }
}