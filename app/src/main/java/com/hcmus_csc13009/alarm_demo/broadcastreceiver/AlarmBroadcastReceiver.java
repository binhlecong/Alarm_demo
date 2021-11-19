package com.hcmus_csc13009.alarm_demo.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.hcmus_csc13009.alarm_demo.R;
import com.hcmus_csc13009.alarm_demo.model.Alarm;
import com.hcmus_csc13009.alarm_demo.service.AlarmService;
import com.hcmus_csc13009.alarm_demo.service.RescheduleAlarmsService;

import java.util.Calendar;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    Alarm alarm;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            String toastText = "Alarm Reboot";
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
            startRescheduleAlarmsService(context);
        } else {
            Bundle bundle = intent.getBundleExtra(context.getString(R.string.bundle_alarm_obj));

            if (bundle != null)
                alarm = (Alarm) bundle.getSerializable(context.getString(R.string.arg_alarm_obj));
            String toastText = "Alarm Received";
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();

            if (alarm != null) {
                if (!alarm.isRecurring()) {
                    startAlarmService(context, alarm);
                } else {
                    if (isAlarmToday(alarm)) {
                        startAlarmService(context, alarm);
                    }
                }
            }
        }
    }

    private boolean isAlarmToday(Alarm alarm1) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int today = calendar.get(Calendar.DAY_OF_WEEK);

        switch (today) {
            case Calendar.MONDAY:
                return alarm1.isMonday();
            case Calendar.TUESDAY:
                return alarm1.isTuesday();
            case Calendar.WEDNESDAY:
                return alarm1.isWednesday();
            case Calendar.THURSDAY:
                return alarm1.isThursday();
            case Calendar.FRIDAY:
                return alarm1.isFriday();
            case Calendar.SATURDAY:
                return alarm1.isSaturday();
            case Calendar.SUNDAY:
                return alarm1.isSunday();
        }
        return false;
    }

    private void startAlarmService(Context context, Alarm alarm1) {
        Intent intentService = new Intent(context, AlarmService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(context.getString(R.string.arg_alarm_obj), alarm1);
        intentService.putExtra(context.getString(R.string.bundle_alarm_obj), bundle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }
    }

    private void startRescheduleAlarmsService(Context context) {
        Intent intentService = new Intent(context, RescheduleAlarmsService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }
    }
}
