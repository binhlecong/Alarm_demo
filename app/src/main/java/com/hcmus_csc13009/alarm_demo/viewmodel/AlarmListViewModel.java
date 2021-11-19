package com.hcmus_csc13009.alarm_demo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.hcmus_csc13009.alarm_demo.data.AlarmRepository;
import com.hcmus_csc13009.alarm_demo.model.Alarm;

import java.util.List;

public class AlarmListViewModel extends AndroidViewModel {
    private AlarmRepository alarmRepository;
    private LiveData<List<Alarm>> alarmsLiveData;

    public AlarmListViewModel(@NonNull Application application) {
        super(application);

        alarmRepository = new AlarmRepository(application);
        alarmsLiveData = alarmRepository.getAlarmsLiveData();
    }

    public void update(Alarm alarm) {
        alarmRepository.update(alarm);
    }

    public LiveData<List<Alarm>> getAlarmsLiveData() {
        return alarmsLiveData;
    }

    public void delete(int alarmId) {
        alarmRepository.delete(alarmId);
    }
}