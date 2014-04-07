package com.ospinet;

import com.example.ospinet.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TableRow;
import android.widget.Toast;

public class MenuActivity extends Activity {
	
	public AlertDialog pDialog;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
	
	@Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	 
	  switch (item.getItemId()) {
	   
	  case R.id.logout:
	   SessionManagement sm_logout=new SessionManagement(getApplicationContext());
	   sm_logout.logoutUser();
	   return true;
	  default:
	   return super.onOptionsItemSelected(item);
	  }
	 
	 }
	 

}
