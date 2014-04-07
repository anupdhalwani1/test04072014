package com.ospinet;
import com.example.ospinet.R;
import com.ospinet.library.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceActivity.Header;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
public class LoginActivity extends MenuActivity {
	Button btnLogin;
	Button btnsignup;
	
	EditText inputEmail;
	EditText inputPassword;
	TextView errormsg;
	 AlertDialog.Builder dialog;
	 String email;
	 public boolean st=true;
	 final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
	 private String jsonResult;
	 
	 //Session Managment class
	 SessionManagement session;
	 Validate validate;
	
	
	
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
		
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.login);
	        
	    
	        inputEmail=(EditText) findViewById(R.id.fname_txt);
	        inputEmail.requestFocus();
	      
	        inputPassword=(EditText) findViewById(R.id.lname_txt);
	        btnLogin=(Button) findViewById(R.id.memberSave);
	        btnsignup=(Button)findViewById(R.id.freesignup_btn);
	        errormsg=(TextView) findViewById(R.id.errorMsg);
	        email=inputEmail.getText().toString();
	        session = new SessionManagement(getApplicationContext());
	        validate=new Validate(getApplicationContext());
	        //Login Button Click Listener
	        btnLogin.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 email=inputEmail.getText().toString();
					 String password=inputPassword.getText().toString();
					 boolean val=Validate();
					 pDialog = new ProgressDialog(LoginActivity.this);
			         pDialog.setMessage("Loading. Please wait...");
			         pDialog.show();
					 if(val==true)
					 {
					 	
						UserFunctions userFunction = new UserFunctions();
						JSONObject json = userFunction.loginUser(email, password);
						login_status(json.toString());
					 }
					 else
					 {
						 errormsg.setText(String.valueOf(val));
					 }
				}
				
			});
	        
	        //Free Sign Up Button
	        btnsignup.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// Staring Free Sign Up Acitivity
                    Intent signup = new Intent(getApplicationContext(), SignupActivity.class);
                    startActivity(signup);
                    finish();
					
				}
			});
	        
	 }
	
	 public boolean Validate()
	 {
		 	boolean val=true;
			val=validate.check_required(inputEmail, "Email Required", "Username");
			val=validate.Email_Check(inputEmail, "Email Address is not valid", "Username");
			val=validate.check_required(inputPassword, "Password Required", "Password");
			return val;
	 }
	 
	
	 public void login_status(String jsonResult)
		{
		
			JSONObject jsonResponse;
			try {
				jsonResponse = new JSONObject(jsonResult);
				JSONArray jsonMainNode = jsonResponse.optJSONArray("loginmsg");
				 for (int i = 0; i < jsonMainNode.length(); i++) {
					    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
						String msg = jsonChildNode.optString("loginstatus");
						String fname=jsonChildNode.optString("fname");
						String lname=jsonChildNode.optString("lname");
						String email=jsonChildNode.optString("email");
						String userid=jsonChildNode.optString("userid");
						String roleid=jsonChildNode.optString("roleid");
						String loginmsg="";
						//Log.d("msg",msg);
						if(msg.equals("1"))
						{
							loginmsg="Please enter a registered email address. Click below to register";
						}
						else if(msg.equals("2"))
						{
							//loginmsg="Success";
							// Creating user login session
                            // Use user real data
							 session.createLoginSession(fname, lname, email, userid, roleid);
							// get user data from session
						        HashMap<String, String> user = session.getUserDetails();
						         
						        // name
						        String name = user.get(SessionManagement.KEY_FNAME);
						     //  Log.d("track", name);
							// Staring MainActivity
						        
	                        Intent ma = new Intent(getApplicationContext(), MemberActivity.class);
	                        startActivity(ma);
	                        finish();
	                        pDialog.dismiss();
						}
						else if(msg.equals("3"))
						{
							loginmsg="Incorrect username or Password. Please click forgot password link to regenerate password.";
						}
						else if(msg.equals("4"))
						{
							loginmsg="Please click on the link sent to the registered email address and complete registration before login.";
						}
						else if(msg.equals("5"))
						{
							loginmsg="Set Password";
						}
						
						errormsg.setText(loginmsg);						
					   }
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	 
	
}
