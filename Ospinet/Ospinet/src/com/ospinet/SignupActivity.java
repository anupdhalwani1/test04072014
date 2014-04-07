package com.ospinet;
import com.example.ospinet.R;
import com.ospinet.library.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity  extends MenuActivity {

	Button btnregister;
	EditText txtfname;
	EditText txtlname;
	EditText txtemail;
	EditText txtreemail;
	EditText txtpassword;
	EditText txtrepassword;
	TextView txtmessage;
	//Validate Class
	Validate validate;
	private String jsonResult;
	String fname,lname,email,password;
	
	ProgressDialog dialog ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		
		btnregister= (Button) findViewById(R.id.btn_register);
		txtfname=(EditText)findViewById(R.id.txt_firstname);
		txtfname.requestFocus();
		txtlname=(EditText)findViewById(R.id.txt_lastname);
		txtemail=(EditText)findViewById(R.id.txt_email);
		txtreemail=(EditText)findViewById(R.id.txt_reemail);
		txtpassword=(EditText)findViewById(R.id.txt_pasword);
		txtrepassword=(EditText)findViewById(R.id.txt_repassword);
		txtmessage=(TextView)findViewById(R.id.tv_message);
		validate=new Validate(getApplicationContext());
		dialog= new ProgressDialog(this);
		View myLayout=findViewById(R.id.appheader);
	    ImageButton btn_home =(ImageButton)myLayout.findViewById(R.id.imgBtn_logo_home);
	    
	    btn_home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("ll","btnlogoclick");
				// TODO Auto-generated method stub
				  Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                  startActivity(login);
                  finish();
			}
		});
	    
		//Register Button Click Listner
		btnregister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(valid())
				{
					fname=txtfname.getText().toString();
					lname=txtlname.getText().toString();
					email=txtemail.getText().toString();
					password=txtpassword.getText().toString();
					pDialog = new ProgressDialog(SignupActivity.this);
		            pDialog.setMessage("Loading. Please wait...");
		            pDialog.show();
					UserFunctions userFunction = new UserFunctions();
					JSONObject json = userFunction.signup(fname, lname, email, password);
					Log.d("sigup",json.toString());
					insert_result(json.toString());
				}
			}
		});
		
	}

	
	
	public boolean valid()
	{
		boolean val=true;
		val=validate.check_required(txtfname, "First Name Required", "First Name");
		val=validate.check_required(txtlname, "Last Name Required", "Last Name");
		val=validate.Email_Check(txtemail, "Email Address is not valid", "Email");
		val=validate.Email_Check(txtreemail, "Re-enter Email Address is not valid", "Re-enter Email");
		val=validate.check_two_input(txtemail, txtreemail, "Email Address is not match");
		val=validate.check_required(txtpassword, "Password Required", "Password");
		val=validate.check_two_input(txtpassword, txtrepassword, "Password is not match");
		return val;
	}
	public void insert_result(String jsonResult)
		{
		 	pDialog.dismiss();
			JSONObject jsonResponse;
			try {
				jsonResponse = new JSONObject(jsonResult);
				JSONArray jsonMainNode = jsonResponse.optJSONArray("signupmsg");
				 for (int i = 0; i < jsonMainNode.length(); i++) {
					    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
						String msg = jsonChildNode.optString("signupstatus");
						txtmessage.setText(msg);						
					   }
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}