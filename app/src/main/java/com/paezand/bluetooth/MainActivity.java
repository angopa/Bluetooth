package com.paezand.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (bluetoothAdapter.isEnabled()) {
            Toast.makeText(this, "Bluetooth is on", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(intent);

            if (bluetoothAdapter.isEnabled()) {
                Toast.makeText(this, "Bluetooth turned on", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.turn_bluetooth_off)
    protected void onTurnBluetoothOffTaped() {
        bluetoothAdapter.disable();
        if (bluetoothAdapter.isEnabled()) {
            Toast.makeText(this, "Bluetooth cannot be disabled", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Bluetooth turned off", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.find_devices)
    protected void onFindDevicesTapped() {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(intent);
    }

    @OnClick(R.id.paired_devices)
    protected void onPairedDevicesTapped() {
        Set<BluetoothDevice> pairedDevicesSet = bluetoothAdapter.getBondedDevices();

        ListView pairedDevices = (ListView) findViewById(R.id.paired_devices_container);
        ArrayList<String> pairedDevicesArrayList = new ArrayList();

        for (BluetoothDevice device : pairedDevicesSet) {
            pairedDevicesArrayList.add(device.getName());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pairedDevicesArrayList);

        pairedDevices.setAdapter(adapter);

    }
}
