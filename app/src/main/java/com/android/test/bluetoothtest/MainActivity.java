package com.android.test.bluetoothtest;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	public final static int PERMISSIONS_CODE_BLUETOOTH = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i(MainActivity.class.getSimpleName(), "**Activity Started** <-- Note time before and after.");

		findViewById(R.id.activity_main_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(MainActivity.class.getSimpleName(), "Button Clicked");
				if(checkSelfPermission(Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
					Log.i(MainActivity.class.getSimpleName(), "Requesting permissions");
					MainActivity.this.requestPermissions(new String[]{Manifest.permission.BLUETOOTH}, PERMISSIONS_CODE_BLUETOOTH);
				}else{
					Log.i(MainActivity.class.getSimpleName(), "Dialog");
					AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
					builder.setMessage(R.string.dialog_permissions_fine)
							.setTitle(R.string.dialog_permissions_title)
							.setPositiveButton(R.string.dialog_permissions_ok, new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									dialog.dismiss();
								}
							});
					// Create the AlertDialog object and return it
					builder.show();
				}
			}
		});
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		Log.i(MainActivity.class.getSimpleName(), "onRequestPermissionsResult");
		switch (requestCode) {
			case PERMISSIONS_CODE_BLUETOOTH:{
				if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					Log.i(MainActivity.class.getSimpleName(), "Bluetooth Permission Granted");
				}else{
					Log.i(MainActivity.class.getSimpleName(), "Bluetooth Permission Denied");
				}
				return;
			}
		}
	}
}