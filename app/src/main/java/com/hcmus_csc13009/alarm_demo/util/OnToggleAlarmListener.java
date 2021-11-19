package com.hcmus_csc13009.alarm_demo.util;

import android.view.View;

import com.hcmus_csc13009.alarm_demo.model.Alarm;

public interface OnToggleAlarmListener {
    void onToggle(Alarm alarm);

    void onDelete(Alarm alarm);

    void onItemClick(Alarm alarm, View view);
}
