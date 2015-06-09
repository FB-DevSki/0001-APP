package com.example.android.smsalert4;

/**
 * Created by Sibe Jan on 8-6-2015.
 */

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.telephony.SmsManager;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.Toast;

        import java.util.ArrayList;

/**
 * Created by Sibe Jan on 31-5-2015.
 */
public class ActivityAlarm extends Activity{
    String alarmMessage;
    String resetMessage;

    private String name, group, alarm_code;
    private String part1, part2, part3;
    private String telnr1, telnr2, telnr3;

    private SharedPreferences preferenceSettings;
    private SharedPreferences.Editor preferencesEditor;
    private static final int PREFERENCES_PRIVATE = 0;
    public static final String PREFS_NAME = "SMS_ALARM_PREFS";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_screen);
        Context context = getApplicationContext();

        preferenceSettings = getSharedPreferences(PREFS_NAME, context.MODE_PRIVATE);
        preferencesEditor = preferenceSettings.edit();
        name =preferenceSettings.getString("name", "");
        group =preferenceSettings.getString("group", "");
        alarm_code =preferenceSettings.getString("alarm_code","");
        part1 =preferenceSettings.getString("part1","");
        telnr1 =preferenceSettings.getString("telnr1", "");
        part2 = preferenceSettings.getString("part2","");
        telnr2 =preferenceSettings.getString("telnr2","");
        part3 =preferenceSettings.getString("part3","");
        telnr3 =preferenceSettings.getString("telnr3", "");

        alarmMessage = "SMSALARM " + name +", groep " + group + ", code " + alarm_code;
        resetMessage = "ALARM RESET " + name +", groep " + group + ", code " + alarm_code;
    }

    public void onBtnAlarmClicked(View view) {
        ImageButton alarmbutton = (ImageButton)findViewById(R.id.btnAlarm);
        alarmbutton.setImageResource(R.drawable.btn_alarm_act);
        ImageButton resetbutton = (ImageButton)findViewById(R.id.btnReset);
        resetbutton.setVisibility(View.VISIBLE);

        Toast msg = Toast.makeText(getBaseContext(),alarmMessage, Toast.LENGTH_LONG);
        msg.show();

        if (telnr1.length()==10)sendSMS(telnr1,alarmMessage);
        if (telnr2.length()==10)sendSMS(telnr2,alarmMessage);
        if (telnr3.length()==10)sendSMS(telnr3,alarmMessage);
    }

    public void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    public void onBtnResetClicked(View view) {
        ImageButton alarmbutton = (ImageButton)findViewById(R.id.btnAlarm);
        alarmbutton.setImageResource(R.drawable.btn_alarm_pass);
        ImageButton resetbutton = (ImageButton)findViewById(R.id.btnReset);
        resetbutton.setVisibility(View.INVISIBLE);
        Toast msg = Toast.makeText(getBaseContext(),resetMessage, Toast.LENGTH_LONG);
        msg.show();

        if (telnr1.length()==10)sendSMS(telnr1,resetMessage);
        if (telnr2.length()==10)sendSMS(telnr2,resetMessage);
        if (telnr3.length()==10)sendSMS(telnr3,resetMessage);
    }
}

