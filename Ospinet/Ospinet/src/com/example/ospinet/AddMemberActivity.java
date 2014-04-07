package com.example.ospinet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;



public class AddMemberActivity extends Activity implements OnItemSelectedListener {
	
	public Button btn_age,btn_dob,btn_unborn;
	public Spinner dob_year,dob_month,dob_day,unborn_year,unborn_month,unborn_day;
	public KeyValuePair itemsyear[],itemsmonth[],itemsdays[],unbitemsyear[],unbitemsmonth[],unbitemsdays[];
	public ArrayAdapter<KeyValuePair> dataAdapter;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.addmember);
	        /*Add Member Button*/
		     Button btn_addm=(Button)findViewById(R.id.btn_Addmember);
		     btn_addm.setOnClickListener(new View.OnClickListener() {
		    	 
		            @Override
		            public void onClick(View arg0) {
		                // Starting a new async task
		            	Intent ma = new Intent(getApplicationContext(), AddMemberActivity.class);
	                 startActivity(ma);
		            }
		        });
		     /*View Member Button*/
		     Button btn_viewm=(Button)findViewById(R.id.btn_viewallmembers);
		     btn_viewm.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View arg0) {
		                // Starting a new async task
		            	Intent ma = new Intent(getApplicationContext(), MemberActivity.class);
	                 startActivity(ma);
		            }
		        });
		     ///Table Age Group button click
		     btn_age=(Button)findViewById(R.id.btn_age);
		     btn_age.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View arg0) {
		            	btn_age.setBackgroundResource(R.drawable.button_selected);
		            	btn_dob.setBackgroundResource(R.drawable.button_custom3);
		             	btn_unborn.setBackgroundResource(R.drawable.button_custom3);
		                // Starting a new async task
		            	TableRow tblage=(TableRow)findViewById(R.id.tbl_age);
		            	tblage.setVisibility(View.VISIBLE);
		            	TableRow tbldob=(TableRow)findViewById(R.id.tbl_dob);
		            	tbldob.setVisibility(View.GONE);
		            	TableRow tblunborn=(TableRow)findViewById(R.id.tbl_unborn);
		            	tblunborn.setVisibility(View.GONE);
		            }
		        });
		     btn_dob=(Button)findViewById(R.id.btn_dob);
		     btn_dob.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View arg0) {
		            	btn_dob.setBackgroundResource(R.drawable.button_selected);
		            	btn_unborn.setBackgroundResource(R.drawable.button_custom3);
		            	btn_age.setBackgroundResource(R.drawable.button_custom3);
		                // Starting a new async task
		            	TableRow tblage=(TableRow)findViewById(R.id.tbl_age);
		            	tblage.setVisibility(View.GONE);
		            	TableRow tbldob=(TableRow)findViewById(R.id.tbl_dob);
		            	tbldob.setVisibility(View.VISIBLE);
		            	TableRow tblunborn=(TableRow)findViewById(R.id.tbl_unborn);
		            	tblunborn.setVisibility(View.GONE);
		            }
		        });
		     btn_unborn=(Button)findViewById(R.id.btn_unborn);
		     btn_unborn.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View arg0) {
		            	btn_unborn.setBackgroundResource(R.drawable.button_selected);
		            	btn_age.setBackgroundResource(R.drawable.button_custom3);
		            	btn_dob.setBackgroundResource(R.drawable.button_custom3);
		                // Starting a new async task
		            	TableRow tblage=(TableRow)findViewById(R.id.tbl_age);
		            	tblage.setVisibility(View.GONE);
		            	TableRow tbldob=(TableRow)findViewById(R.id.tbl_dob);
		            	tbldob.setVisibility(View.GONE);
		            	TableRow tblunborn=(TableRow)findViewById(R.id.tbl_unborn);
		            	tblunborn.setVisibility(View.VISIBLE);
		            }
		        });
		     /*Dob year dropdown*/
		     dob_year=(Spinner)findViewById(R.id.dobYear_sp);
		     itemsyear=new KeyValuePair[114];
		     itemsyear[0]=new KeyValuePair(0,"Year");
		     int j=1;
		     for(int i=2013;i>=1901;i--)
		     {
		    	 itemsyear[j]=new KeyValuePair(i,String.valueOf(i));
		    	 j++;
		     }
		    dataAdapter = new ArrayAdapter<KeyValuePair>(this, android.R.layout.simple_spinner_item, itemsyear);
	        dob_year.setAdapter(dataAdapter);
	        dob_year.setOnItemSelectedListener(this);
		     /*unborn year dropdown*/
		     unborn_year=(Spinner)findViewById(R.id.unbornYear_sp);
		     unbitemsyear=new KeyValuePair[6];
		     unbitemsyear[0]=new KeyValuePair(0,"Year");
		     j=2013;
		     for(int i=1;i<=5;i++)
		     {
		    	 unbitemsyear[i]=new KeyValuePair(j,String.valueOf(j));
		    	 j++;
		     }
		    dataAdapter = new ArrayAdapter<KeyValuePair>(this, android.R.layout.simple_spinner_item, unbitemsyear);
	        unborn_year.setAdapter(dataAdapter);
	        unborn_year.setOnItemSelectedListener(this);
	        
	 }
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
		switch(parent.getId()) {
		case R.id.dobYear_sp:
			if(String.valueOf(itemsyear[position])!="Year")
			{
				 itemsmonth=new KeyValuePair[13];
				 itemsmonth[0]=new KeyValuePair(0,"Month");
				 itemsmonth[1]=new KeyValuePair(1,"Jan");
				 itemsmonth[2]=new KeyValuePair(2,"Feb");
				 itemsmonth[3]=new KeyValuePair(3,"Mar");
				 itemsmonth[4]=new KeyValuePair(4,"Apr");
				 itemsmonth[5]=new KeyValuePair(5,"May");
				 itemsmonth[6]=new KeyValuePair(6,"Jun");
				 itemsmonth[7]=new KeyValuePair(7,"Jul");
				 itemsmonth[8]=new KeyValuePair(8,"Aug");
				 itemsmonth[9]=new KeyValuePair(9,"Sep");
				 itemsmonth[10]=new KeyValuePair(10,"Oct");
				 itemsmonth[11]=new KeyValuePair(11,"Nov");
				 itemsmonth[12]=new KeyValuePair(12,"Dec");
			     dob_month=(Spinner)findViewById(R.id.dobMonth_sp);
				 dataAdapter=new ArrayAdapter<KeyValuePair>(this, android.R.layout.simple_spinner_item,itemsmonth);
				 dob_month.setAdapter(dataAdapter);
				dob_month.setOnItemSelectedListener(this);
			}
		case R.id.dobMonth_sp:
			if(String.valueOf(itemsyear[position])!="Year" && itemsmonth[dob_month.getSelectedItemPosition()].getId()!=0)
			{
				int year=itemsyear[dob_year.getSelectedItemPosition()].getId();
				int month=itemsmonth[dob_month.getSelectedItemPosition()].getId();
				int days=getDaysInMonth(month,year);
				itemsdays=new KeyValuePair[days+1];
				 itemsdays[0]=new KeyValuePair(0,"Day");
			     for(int i=1;i<=days;i++)
			     {
			    	 itemsdays[i]=new KeyValuePair(i,String.valueOf(i));
			     }
			     dob_day=(Spinner)findViewById(R.id.dobDay_sp);
				 dataAdapter=new ArrayAdapter<KeyValuePair>(this, android.R.layout.simple_spinner_item,itemsdays);
				 dob_day.setAdapter(dataAdapter);
				 dob_day.setOnItemSelectedListener(this);
			}
		case R.id.unbornYear_sp:
			if(unbitemsyear[unborn_year.getSelectedItemPosition()].getValue()!="Year" && parent.getId()==R.id.unbornYear_sp)
			{
				 Calendar calendar = Calendar.getInstance();
				
				 String[] montha={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
				
				 if(calendar.get(Calendar.YEAR)==unbitemsyear[position].getId())
				 {
					 int currmont=calendar.get(Calendar.MONTH);
					 unbitemsmonth=new KeyValuePair[(12-currmont)+1];
					 unbitemsmonth[0]=new KeyValuePair(0,"Month");
					 Log.d("ml",String.valueOf(unbitemsmonth.length)+"cm"+currmont);
					 for(int i=1;i<unbitemsmonth.length;i++)
					 {
						 unbitemsmonth[i]=new KeyValuePair(currmont+1,montha[currmont]);
						 currmont++;
					 }
				 }
				 else
				 {
					 unbitemsmonth=new KeyValuePair[13];
					 unbitemsmonth[0]=new KeyValuePair(0,"Month");
					 unbitemsmonth[1]=new KeyValuePair(1,"Jan");
					 unbitemsmonth[2]=new KeyValuePair(2,"Feb");
					 unbitemsmonth[3]=new KeyValuePair(3,"Mar");
					 unbitemsmonth[4]=new KeyValuePair(4,"Apr");
					 unbitemsmonth[5]=new KeyValuePair(5,"May");
					 unbitemsmonth[6]=new KeyValuePair(6,"Jun");
					 unbitemsmonth[7]=new KeyValuePair(7,"Jul");
					 unbitemsmonth[8]=new KeyValuePair(8,"Aug");
					 unbitemsmonth[9]=new KeyValuePair(9,"Sep");
					 unbitemsmonth[10]=new KeyValuePair(10,"Oct");
					 unbitemsmonth[11]=new KeyValuePair(11,"Nov");
					 unbitemsmonth[12]=new KeyValuePair(12,"Dec");
				 }
				 unborn_month=(Spinner)findViewById(R.id.unbornMonth_sp);
				 dataAdapter=new ArrayAdapter<KeyValuePair>(this, android.R.layout.simple_spinner_item,unbitemsmonth);
				 unborn_month.setAdapter(dataAdapter);
				 unborn_month.setOnItemSelectedListener(this);
			}
		case R.id.unbornMonth_sp:
			if(parent.getId()==R.id.unbornMonth_sp && unbitemsyear[unborn_year.getSelectedItemPosition()].getValue()!="Year" && unbitemsmonth[unborn_month.getSelectedItemPosition()].getId()!=0)
			{
				 int year=unbitemsyear[unborn_year.getSelectedItemPosition()].getId();
				 int month=unbitemsmonth[unborn_month.getSelectedItemPosition()].getId();
				 int days=getDaysInMonth(month,year);
				 Calendar calendar = Calendar.getInstance();
				 int currday=calendar.get(Calendar.DAY_OF_WEEK);
				 Log.d("aa",String.valueOf(days)+"y"+year+"m"+month);
				 if(calendar.get(Calendar.MONTH)==month-1)
				 {
					unbitemsdays=new KeyValuePair[(days-currday)+2];
					unbitemsdays[0]=new KeyValuePair(0,"Day");
					int j=currday;
				     for(int i=1;i<unbitemsdays.length;i++)
				     {
				    	 unbitemsdays[i]=new KeyValuePair(j,String.valueOf(j));
				    	 j++;
				     }
				 }
				 else
				 {
					 unbitemsdays=new KeyValuePair[days+1];
					 unbitemsdays[0]=new KeyValuePair(0,"Day");
				     for(int i=1;i<=days;i++)
				     {
				    	 unbitemsdays[i]=new KeyValuePair(i,String.valueOf(i));
				     }
				 }
			     unborn_day=(Spinner)findViewById(R.id.unbornDay_sp);
				 dataAdapter=new ArrayAdapter<KeyValuePair>(this, android.R.layout.simple_spinner_item,unbitemsdays);
				 unborn_day.setAdapter(dataAdapter);
				 unborn_day.setOnItemSelectedListener(this);
			}
		default:
			break;
		}
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @param monthNumber Month Number starts with 0. For <b>January</b> it is <b>0</b> and for <b>December</b> it is <b>11</b>.
	 * @param year
	 * @return
	 */
	 public static int getDaysInMonth(int monthNumber,int year)
	 {
		 int days=0;
		 monthNumber--;
		 if(monthNumber>=0 && monthNumber<12){
			 try
			 {
				 Calendar calendar = Calendar.getInstance();
				 int date = 1;
				 calendar.set(year, monthNumber, date);
				 days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			 } catch (Exception e)
			 {
				 if(e!=null)
				 e.printStackTrace();
			 }
		 }
		 return days;
	 }
	   
}
