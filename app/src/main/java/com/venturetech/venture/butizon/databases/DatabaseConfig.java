package com.venturetech.venture.butizon.databases;

import android.database.sqlite.SQLiteDatabase;

public abstract class DatabaseConfig {

	/**
	 * Override this method and execute the table creation queries using
	 * <CODE>DatabaseHelper.executeSQL()</CODE> method.
	 * 
	 * @param SQLiteDatabase
	 * @author Amit Tank
	 */
	public abstract void onCreate(SQLiteDatabase db) throws Exception;

	/**
	 * Override this method and execute the upgrade queries using
	 * <CODE>DatabaseHelper.executeSQL()</CODE> method.
	 * 
	 * @param db
	 * @throws Exception
	 * @author Amit
	 */
	public abstract void onUpgrade(SQLiteDatabase db) throws Exception;

}
