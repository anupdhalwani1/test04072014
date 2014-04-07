package com.example.ospinet.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.util.Log;

import com.example.ospinet.SessionManagement;
import com.example.ospinet.library.JSONParser;

public class UserFunctions {
	
private JSONParser jsonParser;
	
	public String siteurl="http://www.ospinet.com/andriod_app_fun/";
	//public String siteurl="http://10.0.2.2/Ospinet-Phase-II/public_html/andriod_app_fun/";
	
	String login_url = "login";
	String signup_url = "signup";
	String memberscnt_url = "get_countmembers";
	String members_url = "get_members";
	//Session  class
		SessionManagement session;
	// constructor
	public UserFunctions(){
		jsonParser = new JSONParser();
	}
	/**
	 * function make Login Request
	 * @param email
	 * @param password
	 * */
	public JSONObject loginUser(String email, String password){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		JSONObject json = jsonParser.getJSONFromUrl(siteurl+login_url, params);
		// return json
	//	 Log.d("UserFunctionJSON40", json.toString());
		return json;
	}
	/**
	 * function make Registration Of User after registration mail goes on his email id when he click on confirm he can login in app
	 * @param fname
	 * @param lname
	 * @param email
	 * @param password
	 * */
	public JSONObject signup(String fname, String lname,String email, String password){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("fname", fname));
		params.add(new BasicNameValuePair("lname", lname));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("passwd", password));
		JSONObject json = jsonParser.getJSONFromUrl(siteurl+signup_url, params);
		// return json
		// Log.d("UserFunctionJSON40", json.toString());
		return json;
	}
	/**
	 * function make to get Count of members of login user
	 * @param fname
	 * @param lname
	 * @param email
	 * @param password
	 * */
	public JSONObject members_count(String user_id){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user_id", user_id));
		JSONObject json = jsonParser.getJSONFromUrl(siteurl+memberscnt_url, params);
		// return json
		// Log.d("UserFunctionJSON40", json.toString());
		return json;
	}
	/**
	 * function make to get login user members list
	 * @param fname
	 * @param lname
	 * @param email
	 * @param password
	 * */
	public JSONObject members(String user_id,String offset,String no_of_rec){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user_id", user_id));
		params.add(new BasicNameValuePair("offset",offset));
		params.add(new BasicNameValuePair("no_of_rec",no_of_rec));
		JSONObject json = jsonParser.getJSONFromUrl(siteurl+members_url, params);
		// return json
	//	 Log.d("userfunoffset",offset);
		return json;
	}
}
