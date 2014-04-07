package com.example.ospinet;

public class KeyValuePair {
int id;
String value;
	public KeyValuePair(int ID,String VALUE)
	{
		this.id=ID;
		this.value=VALUE;
	}
	public String getValue(){ return value;}
    public int getId() {return id;   }
    public String toString() { return value;  }
}
