package com.venturetech.venture.butizon.databases;

import java.util.HashMap;

public class DBResponseDataWrapper {

	public static class ReadResponseData
	{
		public HashMap<String,String> data=new HashMap<>();
	}
	
	public static class InsertResponseData
	{
		public long data;
	}
	public static class UpdateResponseData
	{
		public int data;
	}
}
