package com.hcmus_csc13009.alarm_demo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.hcmus_csc13009.alarm_demo.data.AlarmRepository;
import com.hcmus_csc13009.alarm_demo.model.Alarm;

public class CreateAlarmViewModel extends AndroidViewModel {
    private AlarmRepository alarmRepository;

    public CreateAlarmViewModel(@NonNull Application application) {
        super(application);

        alarmRepository = new AlarmRepository(application);
    }

    public void insert(Alarm alarm) {
        alarmRepository.insert(alarm);
    }

    public void update(Alarm alarm) {
        alarmRepository.update(alarm);
    }
}