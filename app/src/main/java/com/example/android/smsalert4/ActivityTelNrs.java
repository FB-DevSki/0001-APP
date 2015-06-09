package com.example.android.smsalert4;

        import android.app.Activity;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import java.util.ArrayList;

/**
 * Created by Sibe Jan on 8-6-2015.
 */

public class ActivityTelNrs extends Activity {
    private EditText part1, part2, part3;
    private EditText telnr1, telnr2, telnr3;
    private String name, group, alarm_code;
    private Button telNrSave;

    private SharedPreferences preferenceSettings;
    private SharedPreferences.Editor preferencesEditor;
    private static final int PREFERENCES_PRIVATE = 0;
    public static final String PREFS_NAME = "SMS_ALARM_PREFS";

    ArrayList<String> participants;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tel_nrs);

        part1 = (EditText)findViewById(R.id.part1);
        part2 = (EditText)findViewById(R.id.part2);
        part3 = (EditText)findViewById(R.id.part3);
        telnr1 = (EditText) findViewById(R.id.tel1);
        telnr2 = (EditText) findViewById(R.id.tel2);
        telnr3 = (EditText) findViewById(R.id.tel3);

        telNrSave = (Button) findViewById(R.id.tel_nr_save);

        preferenceSettings = getSharedPreferences(PREFS_NAME, getApplicationContext().MODE_PRIVATE);
        name = preferenceSettings.getString("name", "");
        group = preferenceSettings.getString("group", "");
        alarm_code = preferenceSettings.getString("alarm_code", "");
        part1.setText(preferenceSettings.getString("part1", ""));
        telnr1.setText(preferenceSettings.getString("telnr1", ""));
        part2.setText(preferenceSettings.getString("part2", ""));
        telnr2.setText(preferenceSettings.getString("telnr2", ""));
        part3.setText(preferenceSettings.getString("part3", ""));
        telnr3.setText(preferenceSettings.getString("telnr3", ""));

        telNrSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferenceSettings = getSharedPreferences(PREFS_NAME,getApplicationContext().MODE_PRIVATE);
                preferencesEditor = preferenceSettings.edit();
                preferencesEditor.putString("name", name);
                preferencesEditor.putString("group", group);
                preferencesEditor.putString("alarm_code", alarm_code);
                preferencesEditor.putString("part1", part1.getText().toString());
                preferencesEditor.putString("telnr1", telnr1.getText().toString());
                preferencesEditor.putString("part2", part2.getText().toString());
                preferencesEditor.putString("telnr2", telnr2.getText().toString());
                preferencesEditor.putString("part3", part3.getText().toString());
                preferencesEditor.putString("telnr3", telnr3.getText().toString());
                preferencesEditor.commit();
                Toast msg = Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_LONG);
                msg.show();
            }
        });
    }
}
