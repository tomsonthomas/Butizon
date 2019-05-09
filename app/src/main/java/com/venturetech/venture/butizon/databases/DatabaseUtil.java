package com.venturetech.venture.butizon.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Statement;

/**
 * Create or open the database and Provides access to a database. after creating the database it
 * executes statements from the file database.sql which is in the asset folder
 * 
 */
public class DatabaseUtil {

	private static DatabaseHelper mOpenHelper = null;
	private static Context context;

	private static final String fileName ="database.sql";

	/**
	 * Initialize the DatabaseUtil instance for the app
	 * 
	 * @param context {@link Context}
	 * @param String databasename
	 */
	public static void init(Context mcontext, String databasename, int version, DatabaseConfig config) {

	
			if (mOpenHelper == null) {
				context = mcontext; 
				// =====called directly DatabaseHelper class=============
				mOpenHelper = new DatabaseHelper(mcontext, databasename, version, config);

				try { 
					mOpenHelper.getWritableDatabase().close(); 
				} catch (Exception e) {
					e.printStackTrace();
				} 

			}
		
	}

	/**
	 * this method is used to get the current database instance
	 * 
	 * @return SQLiteDatabase instance to access current database
	 * @throws DbException
	 */
	public static SQLiteDatabase getDatabaseInstance() throws DbException {

		if (mOpenHelper == null)
			throw new DbException("DatabaseUtil.init() is not called.");

		return mOpenHelper.getWritableDatabase();

	}

	/**
	 * Use this static method to release database related resource. Pass <code>null</code> if not
	 * particular argument is not applicable.
	 * 
	 * @param db {@link SQLiteDatabase}
	 * @param stmt {@link Statement}
	 * @param cursor {@link Cursor}
	 */
	public static void closeResource(SQLiteDatabase db, SQLiteStatement stmt, Cursor cursor) {

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}

		if (cursor != null) {
			try {
				cursor.close();
			} catch (Exception e) {
			}
		}

		if (db != null) {
			try {
				db.close();
			} catch (Exception de) {
			}

		}
	}

	/**
	 * This class helps open, create, and upgrade the database file.
	 */
	public static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseConfig config = null;

		public DatabaseHelper(Context context, String datbaseName, int version, DatabaseConfig config) {
			super(context, datbaseName, null, version);
			this.config = config;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			execteStatement(db);
			if (config != null) {
				try {
					config.onCreate(db);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		// ======if any changes occurs==========
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


			if (config != null) {
				try {
					config.onUpgrade(db);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

			onCreate(db);
	}

	/**
	 * executes the statements from the file which is in the asset folder if line in the file starts
	 * with # then it consider it as the comments
	 * 
	 * @param db
	 * @param fileName name of the file in the asset folder
	 */
	// =====read d file line by line & execute============
	// ======file is in asset folder database sql==========
		
		private static void execteStatement(SQLiteDatabase db) {
			String statement="";
			try {
				InputStream inputStream = context.getAssets().open(fileName);
				if (inputStream != null) {

					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
					
					while ((statement = reader.readLine()) != null) {
						if (statement.trim().length() > 0 && !statement.startsWith("#")&& !statement.startsWith("BEGIN")
								&& !statement.startsWith("COMMIT")) {
							System.out.println(statement);
							db.execSQL(statement);
						}
					}

				}
			} catch (IOException e) {
				
				System.out.println("Statement"+statement);
				e.printStackTrace();
			}

		}

		
	}
}
