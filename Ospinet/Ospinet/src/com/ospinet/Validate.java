package com.ospinet;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;



public class Validate {
	
	AlertDialog.Builder dialog;
	//Context
    Context _context;
    final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
	public  Validate(Context context)
    {
		this._context=context;
    }
	public boolean check_required(EditText input,String msg,String inputprevtext)
	{
		if( input.getText().toString().length()==0 || input.getText().toString().equals(inputprevtext))
		{
			input.setError(msg);
			return false;
		}
		return true;
	}
	public boolean Email_Check(EditText input,String msg,String inputprevtext)
	{
		if( input.getText().toString().length()==0 || !input.getText().toString().matches(emailPattern))
		{
			input.setError(msg);
			return false;
		}
		return true;
	}
	public boolean check_two_input(EditText input1,EditText input2,String msg)
	{
		if(!input1.getText().toString().equals(input2.getText().toString()))
		{
			input2.setError(msg);
			return false;
		}
		return true;
	}
}
