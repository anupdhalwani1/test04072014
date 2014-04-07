package com.ospinet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
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

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class Members_Oper extends AsyncTask<String, Integer, Double>{
	
	public ProgressDialog pDialog;
	public String jsonResult;
	public MemberActivity ma;
	 // constructor
    public Members_Oper() {
 
    }
		@Override
		protected Double doInBackground(String... params) {
			// TODO Auto-generated method stub
			members(params[0]);
			return null;
		}
		 public void members(String user_id)
		 {
			// Create a new HttpClient and Post Header
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost("http://www.ospinet.com/andriod_app_fun/get_members");
				//HttpPost httppost = new HttpPost("http://localhost/Ospinet-Phase-II/andriod_app_fun/login");
				try {
					// Add your data
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					// get user data from session
					 // Session class instance
					nameValuePairs.add(new BasicNameValuePair("user_id",user_id ));
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					// Execute HTTP Post Request
					HttpResponse response = httpclient.execute(httppost);
					jsonResult = inputStreamToString(response.getEntity().getContent()).toString();
					Log.d("rssult", jsonResult);
			
	 			} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
	 				Log.d("c", e.getMessage());
				} catch (IOException e) {
					Log.d("ioc", e.getMessage());
					// TODO Auto-generated catch block
				}
		 }
		 
		  @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            ma=new MemberActivity();
	            pDialog = new ProgressDialog( ma.getApplicationContext());
	            pDialog.setMessage("Loading members. Please wait...");
	            pDialog.show();
	        }
		  protected void onPostExecute(Double result){
			  pDialog.dismiss();
				members_list();
			}
			protected void onProgressUpdate(Integer... progress){
				
			}
	
	 public void members_list()
		{
			JSONObject jsonResponse;
			try {
				jsonResponse = new JSONObject(jsonResult);
				JSONArray jsonMainNode = jsonResponse.optJSONArray("success");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.d("ml",e.getMessage());
			}
		}
	 public StringBuilder inputStreamToString(InputStream is) {
		   String rLine = "";
		   StringBuilder answer = new StringBuilder();
		   BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		 
		   try {
		    while ((rLine = rd.readLine()) != null) {
		     answer.append(rLine);
		    }
		   }
		 
		   catch (IOException e) {
		    // e.printStackTrace();
		    Toast.makeText(ma.getApplicationContext(),
		      "Error..." + e.toString(), Toast.LENGTH_LONG).show();
		   }
		   return answer;
		  }
}
