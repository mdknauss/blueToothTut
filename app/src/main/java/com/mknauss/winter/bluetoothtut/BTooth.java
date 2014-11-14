package com.mknauss.winter.bluetoothtut;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static android.bluetooth.BluetoothAdapter.getDefaultAdapter;


public class BTooth extends Activity {

    public TextView statusUpdate;
    public Button connect;
    public Button disconnect;
    public ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btooth);
        setupUI();
    }

    private void setupUI() {
            //get references
            final TextView statusUpdate =(TextView) findViewById(R.id.result);
            final Button connect = (Button)findViewById(R.id.connect_button);
            final Button disconnect = (Button)findViewById(R.id.disconnect_button);
            final ImageView logo = (ImageView)findViewById(R.id.logo);
            disconnect.setVisibility(View.GONE);
            logo.setVisibility(View.GONE);

            btAdapter = getDefaultAdapter();
                if (btAdapter.isEnabled()) {
                    String address = btAdapter.getAddress();
                    String name = btAdapter.getName();
                    String statusText = name + " : " + address;
                    statusUpdate.setText(statusText);
                }
                else {
                    statusUpdate.setText("Bluetooth is NOT on");
                }
            connect.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    String actionStateChanged = BluetoothAdapter.ACTION_STATE_CHANGED;
                    String actionRequestEnable = BluetoothAdapter.ACTION_REQUEST_ENABLE;
                    IntentFilter filter = new IntentFilter(actionStateChanged);
                    //registerReceiver(bluetoothState, filter);
                    startActivityForResult(new Intent(actionRequestEnable), 0);
                }
            }); //end connect onClickListener

        disConnect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }); //end connect onClickListener

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.btooth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
