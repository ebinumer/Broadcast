package com.example.broadcastbattery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;

import java.util.logging.Level;

public class MainActivity extends AppCompatActivity {
    TextView txtBattery, TXT2, TXT3, txt4, txt5,txt6,txt7;
    String charging_status,battery_status,plugtype;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            /*   Here check battery condetion   */
            int health=intent.getIntExtra(BatteryManager.EXTRA_HEALTH,0);

            switch (health){

                case 1:
                    battery_status = "Unknown";
                    break;
                case 2:
                    battery_status = "Good";
                    break;
                case  3:
                    battery_status = "Over_Heat";
                    break;
                case 4:
                    battery_status = "Not Dead";
                    break;
                case 5:
                    battery_status = "Over_voltage";
                    break;
                case 6:
                    battery_status = "Unspecifide_Failure";
                    break;
                case 7:
                    battery_status = "Cold";
                    break;

            }
            txt6.setText("Battery_Health :" + battery_status);

            /*   Here check battery level   */

            int Level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

            int Low = intent.getIntExtra(BatteryManager.EXTRA_BATTERY_LOW, 0);
            txtBattery.setText("BATTERY LEVEL  :" + Level + "%");

            /*   Here check battery Voltage   */
            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
            txt5.setText("Voltage    :" + voltage);

            /*   Here check battery temperature   */

            int temp = (intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)) / 10;
            TXT2.setText("TEMPERATURE  :" + temp + "C");

            /*   Here check battery "Battery Type   */
            String tech = intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
            txt4.setText("Battery Type :" + tech);

            /*   Here check battery is charged or not   */

           int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            Log.e("status", status + "");

            switch (status){

                case 1:
                    charging_status = "Unknown";
                    break;
                case 2:
                    charging_status = "Charging";
                    break;
                case  3:
                    charging_status = "Disconnected";
                    break;
                case 4:
                    charging_status = "Not Charging";
                    break;
                case 5:
                    charging_status = "Full Charging";
                    break;

            }
            TXT3.setText("Charging Status :" + charging_status);

            int Plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            switch (Plugged){

                case 1:
                    plugtype = "AC";
                    break;
                case 2:
                    plugtype = "USB";
                    break;
                case 4:
                    plugtype = "Wireless";
                    break;
            default:plugtype="Not Plegged";

            }
            txt7.setText("Plugged  type  :" + plugtype);

        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtBattery = findViewById(R.id.txtLvl);
        TXT2 = findViewById(R.id.txt2);
        TXT3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);
        txt6=findViewById(R.id.txt6);
        txt7=findViewById(R.id.txt7);

        this.registerReceiver(this.broadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
}
