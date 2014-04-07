package com.ospinet;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManagement {
	// Shared Preferences
    SharedPreferences pref;
    
    //Editor For Shared Preferences
    Editor editor;
    
    //Context
    Context _context;
    
    //Shared Pref Mode
    int PRIVATE_MODE=0;
    
    //Shared Pref file name
    private static final String PREF_NAME="OspinetMedicalPref";
    
    //All Shared Preference Keys
    private static final String IS_LOGIN="ospinet_loggedin";
    
    public static final String KEY_FNAME="fname";
    public static final String KEY_LNAME="lname";
    public static final String KEY_EMAIL="Email";
    public static final String KEY_USERID="ID";
    public static final String KEY_ROLEID="RoleID";
    //Constructor
	public SessionManagement(Context context)
    {
    	this._context=context;
    	pref=_context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
    	editor=pref.edit();
    }
    public void test()
    {
    	Log.d("sesstest", "correct");
    }
    /**
     * Create login session
     */
    public void createLoginSession(String fname,String lname,String email,String userid,String roleid)
    {
    	//Storing login value as TRUE
    	editor.putBoolean(IS_LOGIN, true);
    	//Storing User Details
    	editor.putString(KEY_FNAME, fname);
    	editor.putString(KEY_LNAME, lname);
    	editor.putString(KEY_EMAIL, email);
    	editor.putString(KEY_USERID, userid);
    	editor.putString(KEY_ROLEID, roleid);
    	//Commit Changes
    	editor.commit();
    }
    /**
     * Check login method will check user login status
     * If false it will redirect to user login page
     * Else won't do anything
     */
    public void checklogin() {
    	//Check login Status
    	if(!this.isLoggedIn())
    	{
    		// user is not logged in redirect him to Login Activity
    		Intent i=new Intent(_context,LoginActivity.class);
    		//Closing All Activity
    		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		//Add new flag to start new Activity
    		_context.startActivity(i);
    	}
		
	}
    /**
     * Get stored session data
     * 
    */
     public HashMap<String,String> getUserDetails()
     {
    	 HashMap<String, String> user = new HashMap<String,String>();
         // user name
         user.put(KEY_FNAME, pref.getString(KEY_FNAME,null));
         user.put(KEY_LNAME, pref.getString(KEY_LNAME, null));
         user.put(KEY_USERID, pref.getString(KEY_USERID, null));
         user.put(KEY_ROLEID, pref.getString(KEY_ROLEID, null));
         // user email id
         user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
          
         // return user
         return user;
     }
     /**
      * Clear session details
      * */
     public void logoutUser(){
         // Clearing all data from Shared Preferences
         editor.clear();
         editor.commit();
          
         // After logout redirect user to Loing Activity
         Intent i = new Intent(_context, LoginActivity.class);
         // Closing all the Activities
         i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          
         // Add new Flag to start new Activity
         i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          
         // Staring Login Activity
         _context.startActivity(i);
     }
     /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
