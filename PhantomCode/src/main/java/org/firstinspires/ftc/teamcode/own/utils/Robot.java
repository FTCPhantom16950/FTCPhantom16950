package org.firstinspires.ftc.teamcode.own.utils;


import com.qualcomm.robotcore.hardware.HardwareDevice;


import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.psilynx.psikit.core.wpi.WPISerializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public enum Robot {

    INSTANCE;
    private static final Logger log = LoggerFactory.getLogger(Robot.class);
    public final Map<String, Integer> sounds = new ConcurrentHashMap<>();
    public final List<String> queueCurrent = new CopyOnWriteArrayList<>();
    /// Map for saving {@link HardwareDevice}
    private final Map<String, HardwareDevice> robotDeviceMap = new ConcurrentHashMap<>();

    public Map<String, Object> getTelemetryMap() {
        return telemetryMap;
    }

    /// Map for telemetry data
    private final Map<String, Object> telemetryMap = new ConcurrentHashMap<>();
    private final Map<String,org.psilynx.psikit.core.wpi.WPISerializable> loggerDataMap = new ConcurrentHashMap<>();
    /// Map for inner data of robot
    private final Map<String, Object> dataMap = new ConcurrentHashMap<>();
    /// Set of {@link Mechanism}
    private final Set<Mechanism> mechanismSet = ConcurrentHashMap.newKeySet();
    /// Action is added to robot by user. See {@link Action}
    private Action action = null;

    public void clearRobotDevices() {
        robotDeviceMap.clear();
    }

    public void clearTelemetry() {
        telemetryMap.clear();
    }

    public void clearData() {
        dataMap.clear();
    }

    public void clearMechanisms() {
        mechanismSet.clear();
    }

    public void clearAction() {
        action = null;
    }

    public void addRobotDevice(String name, HardwareDevice device) {
        robotDeviceMap.put(name, device);
    }

    public void addTelemetryData(String name, Object data) throws InterruptedException {
        telemetryMap.put(name, data);
    }
    public void addLoggerData(String name, WPISerializable obj){loggerDataMap.put(name,obj);}
    public void addData(String name, Object data) {
        dataMap.put(name, data);
    }

    public void addMechanism(Mechanism mechanism) {
        mechanismSet.add(mechanism);
    }

    public void setStartAction(Action action) {
        this.action = action;
    }

    public <T> T getRobotDevice(String name, Class<T> classType) throws InterruptedException {
        HardwareDevice device = robotDeviceMap.get(name);
        if (device == null){
            throw new InterruptedException("Device not found");
        }
        else{
            return classType.cast(device);
        }
    }
    public WPISerializable getLoggerData(String name)throws InterruptedException{
        WPISerializable data = loggerDataMap.get(name);
        try {
            return data;
        } catch (RuntimeException e){
            throw new InterruptedException("Data not found " + name);
        }
    }
    public Object getTelemetryData(String name) throws InterruptedException {
        Object data = telemetryMap.get(name);
        try {
            return data;
        } catch (RuntimeException e){
            throw new InterruptedException("Data not found " + name);
        }
    }

    public <T> T getRobotData(String name, Class<T> classType) throws InterruptedException {
        Object data = dataMap.get(name);
        if (data == null) {
            throw new InterruptedException("Data not found " + name);
        } else if (!classType.isInstance(data)) {
            throw new InterruptedException("Data type doesn't match");
        } else {
            return classType.cast(data);
        }

    }

    public Set<Mechanism> getMechanisms() throws InterruptedException {
        if (mechanismSet.isEmpty()){
            throw new InterruptedException("Mechanisms not found");
        }
        else{
            return mechanismSet;
        }
    }

    public Action getAction() throws InterruptedException {
        if (action == null) {
            throw new RuntimeException("Action not found");
        }
        else{
            return action;
        }
    }
}
