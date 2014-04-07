package com.ospinet.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;


import android.util.Log;
import android.widget.Toast;

public class JSONParser {
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	
	/*JSONObject*/
	public JSONObject  getJSONFromUrl(String url, List<NameValuePair> params) {

		// Making HTTP request
		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			Log.d("37","test");
			HttpResponse httpResponse = httpClient.execute(httpPost);
			Log.d("40","test");
			json=inputStreamToString(httpResponse.getEntity().getContent()).toString();
			Log.d("42",json);
		} catch (UnsupportedEncodingException e) {
			Log.d("json41",e.getMessage().toString());
		} catch (ClientProtocolException e) {
			Log.d("json43",e.getMessage().toString());
		} catch (IOException e) {
			Log.d("json45",e.getMessage().toString());
		}

		/*try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
			Log.d("JSON", json);
		} catch (Exception e) {
			Log.d("Buffer Error", "Error converting result " + e.toString());
		}

		

		// return JSON String
		return jObj;*/
		// try parse the string to a JSON object
				try {
					jObj = new JSONObject(json);			
				} catch (JSONException e) {
					Log.d("JSON Parser", "Error parsing data " + e.toString());
				}
				return jObj;
	}
	 private StringBuilder inputStreamToString(InputStream is) {
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
			   Log.d("json91",e.getMessage().toString());
		   }
		   return answer;
		  }
}
