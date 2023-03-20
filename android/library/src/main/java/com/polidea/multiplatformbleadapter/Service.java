package com.polidea.multiplatformbleadapter;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Service {
    private static final String TAG = "ADV_TEST";

    final private int id;
    final private String deviceID;
    final private BluetoothGattService btGattService;

    public Service(int id, String deviceID, BluetoothGattService btGattService) {
        this.id = id;
        this.deviceID = deviceID;
        this.btGattService = btGattService;
    }

    public int getId() {
        return this.id;
    }

    public UUID getUuid() {
        return this.btGattService.getUuid();
    }

    public String getDeviceID() {
        return this.deviceID;
    }

    public boolean isPrimary() {
        return btGattService.getType() == BluetoothGattService.SERVICE_TYPE_PRIMARY;
    }

    @Nullable
    public Characteristic getCharacteristicByUUID(@NonNull UUID uuid) {
        BluetoothGattCharacteristic characteristic = this.btGattService.getCharacteristic(uuid);
        if (characteristic != null) {
            Log.v(TAG, "char" + uuid);
            Log.v(TAG, "BluetoothGattCharacteristic" + characteristic);
            Log.v(TAG, "btGattService" + this.btGattService);
        }

        if (characteristic == null) return null;

        return new Characteristic(this, characteristic);
    }

    public List<Characteristic> getCharacteristics() {
        ArrayList<Characteristic> characteristics = new ArrayList<>(this.btGattService.getCharacteristics().size());
        for (BluetoothGattCharacteristic gattCharacteristic : this.btGattService.getCharacteristics()) {
            characteristics.add(new Characteristic(this, gattCharacteristic));
        }
        return characteristics;
    }
}
