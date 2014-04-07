package com.ospinet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

import com.example.ospinet.R;

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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.ospinet.library.*;
public class MemberActivity extends MenuActivity implements OnClickListener {

	public String jsonResult;
	//Session  class
	SessionManagement session;
	public ProgressDialog pDialog;
	ListView memberlv;
	ImageButton btnPrev;
	ImageButton btnNext;
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MembersCnt="memberCnt";
	ArrayList<HashMap<String, String>> membersList;
	// Flag for current page
   
    int current_page = 0;
	int pageCount=0;
	int offset=0;
	public int no_of_rec=10;
	public JSONObject jsonResponse;
	public UserFunctions userFunction;
	public JSONObject json;
	public HashMap<String, String> user;
	public MemberActivity()
	{
		  
	       userFunction = new UserFunctions();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.member);
		 session = new SessionManagement(getApplicationContext());
	     user = session.getUserDetails();
		
		memberlv=(ListView)findViewById(R.id.memberList);
		btnPrev=(ImageButton)findViewById(R.id.imgBtnPrev);
		btnNext=(ImageButton)findViewById(R.id.imgBtnNext);
		
		
		//Get Total Members Count
		   json = userFunction.members_count(user.get(session.KEY_USERID));
		   try {
			pageCount=json.getInt(TAG_MembersCnt);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.d("logmemberscnt",e.getMessage().toString());
			}		
		   members_list(offset);
		   if(no_of_rec >= pageCount)
	       {
			   btnPrev.setEnabled(false);
	           btnNext.setEnabled(false);
	       }
		
	    /**
         * Listening to Load More button click event
         * */
	    btnPrev.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                // Starting a new async task
            	current_page--;
            	offset=no_of_rec*current_page;
            	members_list(offset);
                CheckEnable();
            }
        });
	    btnNext.setOnClickListener(new View.OnClickListener() {
	    	 
            @Override
            public void onClick(View arg0) {
                // Starting a new async task
            	current_page++;
            	offset=no_of_rec*current_page;
            	members_list(offset);
                CheckEnable();
            }
        });
	    /*Add Member Button*/
	     Button btn_addm=(Button)findViewById(R.id.btn_Addmember);
	     btn_addm.setOnClickListener(new View.OnClickListener() {
	    	 
	            @Override
	            public void onClick(View arg0) {
	            	pDialog = new ProgressDialog(MemberActivity.this);
	                pDialog.setMessage("Loading. Please wait...");
	                pDialog.show();
	                // Starting a new async task
	            	Intent ma = new Intent(getApplicationContext(), AddMemberActivity.class);
	            	 pDialog.dismiss();
                 startActivity(ma);
                
	            }
	        });
	     /*View Member Button*/
	     Button btn_viewm=(Button)findViewById(R.id.btn_viewallmembers);
	     btn_viewm.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View arg0) {
	            	pDialog = new ProgressDialog(MemberActivity.this);
	                pDialog.setMessage("Loading. Please wait...");
	                pDialog.show();
	                // Starting a new async task
	            	Intent ma = new Intent(getApplicationContext(), MemberActivity.class);
	            	pDialog.dismiss();
                 startActivity(ma);
	            }
	        });
	}
	/**
    * Method for enabling and disabling Buttons
    */
   private void CheckEnable()
   {
       if(((current_page+1)*no_of_rec) >= pageCount)
       {
    	   btnPrev.setEnabled(true);
           btnNext.setEnabled(false);
       }
       else if(current_page == 0)
       {
    	   btnNext.setEnabled(true);
           btnPrev.setEnabled(false);
       }
       else
       {
    	   btnPrev.setEnabled(true);
    	   btnNext.setEnabled(true);
       }
   }
	@Override
	public void onClick(View v)
	{}
	
	
	 public void members_list(int offset1)
		{
			pDialog = new ProgressDialog(MemberActivity.this);
            pDialog.setMessage("Loading. Please wait...");
            pDialog.show();
		 json = userFunction.members(user.get(session.KEY_USERID),String.valueOf(offset1),String.valueOf(no_of_rec));
			try {
				jsonResponse = new JSONObject(json.toString());
				
				int s=jsonResponse.getInt(TAG_SUCCESS);
				
				if(s==1)
				{
					JSONArray members = jsonResponse.optJSONArray("members");
					/*Create A Members List Array
					 */
					// looping through All Products
					//Log.d("mc", String.valueOf(members.length()));
					membersList= new ArrayList<HashMap<String, String>>();
                    for (int i = 0; i < members.length(); i++) {
                        JSONObject c = members.getJSONObject(i);
 
                        // Storing each json item in variable
                        String id = c.getString("id");
                        String name = c.getString("name");
                        String gender = c.getString("gender");
                        String age = c.getString("age");
 
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put("id", id);
                        map.put("name", name);
                        map.put("gender", gender);
                        map.put("age", age);
 
                        // adding HashList to ArrayList
                       
                        membersList.add(map);
                     // get listview current position - used to maintain scroll position
                        int currentPosition = memberlv.getFirstVisiblePosition();
                          ListAdapter adapter=new SimpleAdapter(MemberActivity.this,membersList,R.layout.list_item, new String[]{"id","name","gender","age"},new int[]{R.id.mid,R.id.name,R.id.gender,R.id.age});
                          memberlv.setAdapter(adapter);
                          // Setting new scroll position
                          memberlv.setSelectionFromTop(currentPosition + 1, 0);
                          pDialog.dismiss();
                    }
                } else {
                    // no products found
                    // Launch Add New product Activity
                  //  Log.d("message","NO Members Found");
                }
				/*End*/
		 } catch (JSONException e) {
				// TODO Auto-generated catch block
			//	Log.d("ml",e.getMessage());
			}
		}
}
