package com.example.carcontrol;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class Utils {

    public static boolean checkBluetooth(BluetoothAdapter bluetoothAdapter)
    {
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled())
        {
            return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void requestUserBluetooth(Activity activity)
    {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(enableBtIntent, MainActivity.REQUEST_ENABLE_BT);
    }
    public static void toast(Context activityContext, String string) {
        Toast toast = Toast.makeText(activityContext, string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0,0);
        toast.show();
    }
}
