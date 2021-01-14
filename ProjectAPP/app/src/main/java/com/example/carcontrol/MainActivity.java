package com.example.carcontrol;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.*;

import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import java.util.*;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {

    Button exit, mode;
    private BluetoothAdapter BA;
    private BluetoothLeScanner bluetoothLeScanner = BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();
    private boolean mScanning;
    private Handler handler = new Handler();
    private static final long SCAN_PERIOD = 10000;
    private ScanCallback leScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        BA = bluetoothManager.getAdapter();

        if (BA == null || !BA.isEnabled())
        {
            Intent enableItIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableItIntent, 1);
        }


        mode = (Button) findViewById(R.id.modeButton);
        exit = (Button) findViewById(R.id.ExitButton);
        
        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Mode.class);
                startActivity(intent);
            }
        });
        
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

    public void scanLeDevice()
    {
        if(!mScanning)
        {
         handler.postDelayed(new Runnable() {
             @Override
             public void run() {
                 mScanning = false;
                 bluetoothLeScanner.stopScan(leScanCallback);
             }
         }, SCAN_PERIOD);

         mScanning = true;
         bluetoothLeScanner.startScan(leScanCallback);
        }
        else {
            mScanning = false;
            bluetoothLeScanner.stopScan(leScanCallback);
        }
    }
    public void ShowGif(View view) {
        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(this).load(R.drawable.giphy).into(imageView);
    }
}
