package com.venturetech.venture.butizon.databases;



import java.util.ArrayList;

public class DBResponseDataTypes {

	public static class Response
	{ 
		public int count;
	}
	
	public static class InserResponse
	{
		public DBResponseDataWrapper.InsertResponseData responsedata;
	}

	public static class UpdateResponse
	{
		public DBResponseDataWrapper.UpdateResponseData responsedata;
	}

	public static class ReadResponse extends Response
	{
		public ArrayList<DBResponseDataWrapper.ReadResponseData> responsedata=new ArrayList<>();
	}

}
