package com.venturetech.venture.butizon.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.venturetech.venture.butizon.Model.Employee;
import com.venturetech.venture.butizon.Model.ModelClubAppointments;
import com.venturetech.venture.butizon.Model.Model_Appointments;
import com.venturetech.venture.butizon.Model.Model_Club;
import com.venturetech.venture.butizon.Model.Model_Employee_Service;
import com.venturetech.venture.butizon.Model.Model_shedule;
import com.venturetech.venture.butizon.Model.Services;
import com.venturetech.venture.butizon.Model.Shop_Service_Details;
import com.venturetech.venture.butizon.Model.UserApp.List.FeedbackListt;
import com.venturetech.venture.butizon.Model.UserModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DBTransactionFunctions {
	private Context context;

	public DBTransactionFunctions(Context context) {
		this.context=context;
		//this.bus=bus;
		//bus.register(this);

		DatabaseUtil.init(this.context, "Butizon",1, null);
	}

	//used for section handling instead of this we can use shared preferene

	public static 	boolean insertConfigValues()
	{
		ContentValues cv;
		try {

			cv = new ContentValues();
			cv.put("configName", "user_name");
			cv.put("configValue", "");
			DBTransactionFunctions.DB_InsertRow("tb_configuration", cv);

			cv = new ContentValues();
			cv.put("configName", "name");
			cv.put("configValue", "");
			DBTransactionFunctions.DB_InsertRow("tb_configuration", cv);

			cv = new ContentValues();
			cv.put("configName", "userid");
			cv.put("configValue", "");
			DBTransactionFunctions.DB_InsertRow("tb_configuration", cv);
			cv = new ContentValues();
			cv.put("configName", "password");
			cv.put("configValue", "");
			DBTransactionFunctions.DB_InsertRow("tb_configuration", cv);
			cv = new ContentValues();
			cv.put("configName", "login_status");
			cv.put("configValue", "");
			DBTransactionFunctions.DB_InsertRow("tb_configuration", cv);
			cv = new ContentValues();
			cv.put("configName", "user_type");
			cv.put("configValue", "");
			DBTransactionFunctions.DB_InsertRow("tb_configuration", cv);
			cv = new ContentValues();
			cv.put("configName", "inserterd");
			cv.put("configValue", "");
			DBTransactionFunctions.DB_InsertRow("tb_configuration", cv);
			cv = new ContentValues();
			cv.put("configName", "gender");
			cv.put("configValue", "");
			DBTransactionFunctions.DB_InsertRow("tb_configuration", cv);

			cv = new ContentValues();
			cv.put("configName", "category");
			cv.put("configValue", "");
			DBTransactionFunctions.DB_InsertRow("tb_configuration", cv);


		}catch (Exception e){
			System.out.print(e);
		}
		return true;
	}

//Sql method to insert data to a table

	public static long DB_InsertRow(String _tableName, ContentValues _cv){
		SQLiteDatabase db = null;
		SQLiteStatement stmt = null;
		DBResponseDataTypes.InserResponse insertresponse=new DBResponseDataTypes.InserResponse();
		Cursor cr=null;
		try {
			try {
				DBResponseDataWrapper.InsertResponseData data=new DBResponseDataWrapper.InsertResponseData();
				db=DatabaseUtil.getDatabaseInstance();
				data.data=db.insert(_tableName, null, _cv);
				insertresponse.responsedata=data;
			} catch (Exception e) {
				e.printStackTrace();
			}

		} finally {
			DatabaseUtil.closeResource(db, stmt, cr);
		}
		return insertresponse.responsedata.data;
	}

	//sql method to replace data

	public static long DB_InsertorReplaceRow(String _tableName, ContentValues _cv){
		SQLiteDatabase db = null;
		SQLiteStatement stmt = null;
		DBResponseDataTypes.InserResponse insertresponse=new DBResponseDataTypes.InserResponse();
		Cursor cr=null;
		try {
			try {
				DBResponseDataWrapper.InsertResponseData data=new DBResponseDataWrapper.InsertResponseData();
				db=DatabaseUtil.getDatabaseInstance();
				data.data=db.replace(_tableName, null, _cv);
				insertresponse.responsedata=data;
			} catch (Exception e) {
				e.printStackTrace();
			}

		} finally {
			DatabaseUtil.closeResource(db, stmt, cr);
		}
		return insertresponse.responsedata.data;
	}

	//sql method to clear the table

	public static void DB_ClearTable(String _tableName){
		SQLiteDatabase db = null;
		SQLiteStatement stmt = null;
		Cursor cr=null;
		try {
			try {
				db=DatabaseUtil.getDatabaseInstance();

				db.delete(_tableName, null, null);


			} catch (Exception e) {
				e.printStackTrace();
			}

		} finally {
			DatabaseUtil.closeResource(db, stmt, cr);
		}
	}

	//sql method to delete

	public static void DB_DeleteRow(String _tableName, String _whereClause, String[] _whereArgs){
		SQLiteDatabase db = null;
		SQLiteStatement stmt = null;
		Cursor cr=null;
		try {
			try {
				db=DatabaseUtil.getDatabaseInstance();

				db.delete(_tableName, _whereClause, _whereArgs);


			} catch (Exception e) {
				// TODO Auto-generated catc6h block
				e.printStackTrace();
			}

		} finally {
			DatabaseUtil.closeResource(db, stmt, cr);
		}
	}

//sql method to update data

	public static int DB_UpdateRow(String _tableName, ContentValues _cv, String _whereClause, String[] _whereArgs){
		SQLiteDatabase db = null;
		SQLiteStatement stmt = null;
		DBResponseDataTypes.UpdateResponse updateresponse=new DBResponseDataTypes.UpdateResponse();
		Cursor cr=null;
		try {
			try {
				DBResponseDataWrapper.UpdateResponseData data=new DBResponseDataWrapper.UpdateResponseData();
				db=DatabaseUtil.getDatabaseInstance();

				data.data=db.update(_tableName, _cv, _whereClause, _whereArgs);
				updateresponse.responsedata=data;
			} catch (Exception e) {
				// TODO Auto-generated catc6h block
				e.printStackTrace();
			}

		} finally {
			DatabaseUtil.closeResource(db, stmt, cr);
		}
		return updateresponse.responsedata.data;
	}

	/** If _whereClause= null and _whereArgs=null Read all Rows in Db**/
//used to read data from table
	public static DBResponseDataTypes.ReadResponse DB_ReadRow(String _tableName,
															  String[] _SelectColumn,
															  String _whereClause,
															  String[] _whereArgs,
															  String _groupBy,
															  String _having,
															  String _orderBy)
	{
		SQLiteDatabase db = null;
		SQLiteStatement stmt = null;
		Cursor cr=null;
		DBResponseDataTypes.ReadResponse readResponse=null;
		DBResponseDataWrapper.ReadResponseData readdata=null;
		try {
			try {
				readResponse=new DBResponseDataTypes.ReadResponse();
				db=DatabaseUtil.getDatabaseInstance();

				cr=db.query(_tableName, _SelectColumn, _whereClause, _whereArgs, _groupBy,_having,_orderBy);
				readResponse.count=cr.getCount();
				if(cr.getCount()>0){
					cr.moveToFirst();

					while(cr.isAfterLast()==false){
						readdata=new DBResponseDataWrapper.ReadResponseData();
						for(int i=0;i<cr.getColumnCount();i++)
							readdata.data.put(cr.getColumnName(i), cr.getString(i)+"");

						readResponse.responsedata.add(readdata);
						cr.moveToNext();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} finally {
			DatabaseUtil.closeResource(db, stmt, cr);
		}
		return readResponse;
	}
	//read data using sql
	public static DBResponseDataTypes.ReadResponse DB_ReadRowquery(String sqlquery)
	{
		SQLiteDatabase db = null;
		SQLiteStatement stmt = null;
		Cursor cr=null;
		DBResponseDataTypes.ReadResponse readResponse=null;
		DBResponseDataWrapper.ReadResponseData readdata=null;
		try {
			try {
				readResponse=new DBResponseDataTypes.ReadResponse();
				db=DatabaseUtil.getDatabaseInstance();

				cr=db.rawQuery(sqlquery, null);
				readResponse.count=cr.getCount();
				if(cr.getCount()>0){
					cr.moveToFirst();

					while(cr.isAfterLast()==false){
						readdata=new DBResponseDataWrapper.ReadResponseData();
						for(int i=0;i<cr.getColumnCount();i++)
							readdata.data.put(cr.getColumnName(i), cr.getString(i)+"");

						readResponse.responsedata.add(readdata);
						cr.moveToNext();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} finally {
			DatabaseUtil.closeResource(db, stmt, cr);
		}
		return readResponse;
	}


	public static String getConfigvalue(String name){
		SQLiteDatabase db = null;
		Cursor cursor = null;
		String tempdata="";
		try {
			db = DatabaseUtil.getDatabaseInstance();
			cursor = db.rawQuery("SELECT * FROM tb_configuration where configName = '"+name+"'", null);
			cursor.moveToFirst();
			tempdata = String.valueOf(cursor.getString(cursor.getColumnIndex("configValue")));

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DatabaseUtil.closeResource(db, null, cursor);
		}

		return tempdata;
	}

// to update config table or section table

	public static void updateConfigvalues(String configname, String value){
		SQLiteDatabase db = null;
		try {
			db = DatabaseUtil.getDatabaseInstance();
			ContentValues cv=new ContentValues();
			cv.put("configValue",value);
			DBResponseDataWrapper.UpdateResponseData data=new DBResponseDataWrapper.UpdateResponseData();
			data.data=db.update("tb_configuration", cv, "configName=?", new String[]{configname});

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.closeResource(db, null, null);
		}
	}


	public static JSONArray getDataForSync(String table, String codition, String value){
		SQLiteDatabase db = null;
		Cursor cursor1 = null;
		JSONArray json=new JSONArray();
		try {
			db = DatabaseUtil.getDatabaseInstance();
			cursor1 = db.rawQuery("SELECT * from " + table + " where " + codition + " ="+ value+"", null);
			cursor1.moveToFirst();
			if(cursor1.getCount()>0)
			{
				while(cursor1.isAfterLast()==false)
				{
					JSONObject ja = new JSONObject();
					for(int i=0;i<cursor1.getColumnCount();i++)
						ja.put(cursor1.getColumnName(i), cursor1.getString(i));
					json.put(ja);
					cursor1.moveToNext();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}

	//to check the club data found or not

public static boolean isClubFound(String email, String password)
	{DBResponseDataTypes.ReadResponse result;
		boolean flag=false;

		try{
			String Sql="select * from tb_club where email='"+email+"' and password='"+password+"'";
			result = DB_ReadRowquery(Sql);
			if(result.count>0){
				flag=true;
			}
			else {
				flag =false;
			}

		}catch (Exception e){
			e.printStackTrace();
			flag =false;

		}
		return  flag;
	}

	//to check the user data found or not


	public static boolean isUserFound(String email, String password)
	{DBResponseDataTypes.ReadResponse result;
		boolean flag=false;

		try{
			String Sql="select * from tb_user where email='"+email+"' and password='"+password+"'";
			result = DB_ReadRowquery(Sql);
			if(result.count>0){
				flag=true;
			}
			else {
				flag =false;
			}

		}catch (Exception e){
			e.printStackTrace();
			flag =false;

		}
		return  flag;
	}

	//to get club id
	public static String getClubid(String email, String password)
	{DBResponseDataTypes.ReadResponse result;
		String name="";

		try{
			String Sql="select * from tb_club where email='"+email+"' and password='"+password+"'";
			result = DB_ReadRowquery(Sql);
			if(result.count>0){
				name= result.responsedata.get(0).data.get("id");
			}
			else {
				name ="";
			}

		}catch (Exception e){
			e.printStackTrace();
			name ="";

		}
		return  name;
	}


	//to get user id

	public static String getUserid(String email, String password)
	{DBResponseDataTypes.ReadResponse result;
		String name="";

		try{
			String Sql="select * from tb_user where email='"+email+"' and password='"+password+"'";
			result = DB_ReadRowquery(Sql);
			if(result.count>0){
				name= result.responsedata.get(0).data.get("id");
			}
			else {
				name ="";
			}

		}catch (Exception e){
			e.printStackTrace();
			name ="";

		}
		return  name;
	}

	//to get user gender

	public static String getUserGender(String email, String password)
	{DBResponseDataTypes.ReadResponse result;
		String gender="";

		try{
			String Sql="select * from tb_user where email='"+email+"' and password='"+password+"'";
			result = DB_ReadRowquery(Sql);
			if(result.count>0){
				gender= result.responsedata.get(0).data.get("gender");
			}
			else {
				gender ="";
			}

		}catch (Exception e){
			e.printStackTrace();
			gender ="";

		}
		return  gender;
	}

	//to get user name

    public static String  userName(String email)
    {DBResponseDataTypes.ReadResponse result;
       String name;

        try{
            String Sql="select * from tb_club where email='"+email+"'";
            result = DB_ReadRowquery(Sql);
            if(result.count>0){
                name= result.responsedata.get(0).data.get("club_name");

            }
            else {
                name ="";
            }

        }catch (Exception e){
            e.printStackTrace();
            name ="";

        }
        return  name;
    }

    //to get all services of current club (looginned user)

	public static ArrayList<Services> getServices() {
		ArrayList<Services> list=new ArrayList<>();
		DBResponseDataTypes.ReadResponse result;
		try{
			String Sql="select * from tb_shop_service where shopid='"+DBTransactionFunctions.getConfigvalue("userid")+"'";
		 result = DB_ReadRowquery(Sql);
			if(result.count>0){
				for(int i = 0 ;i<result.responsedata.size();i++){
					list.add(new Services(result.responsedata.get(i).data.get("id"),
							result.responsedata.get(i).data.get("shopid"),
							result.responsedata.get(i).data.get("service_name"),
							result.responsedata.get(i).data.get("rate")));
				}

			}


		}catch (Exception e){

		}
		return list;
	}

	//to get all services
public static ArrayList<Services> getServicesList() {
		ArrayList<Services> list=new ArrayList<>();
		DBResponseDataTypes.ReadResponse result;
		try{
			String Sql="select tb_shop_service.id as id,tb_shop_service.shopid as shopid,tb_shop_service.service_name as service_name,tb_shop_service.gender as gender,tb_shop_service.rate as rate,tb_shop_service.updatedtime as updatedtime from tb_shop_service JOIN tb_club ON tb_shop_service.shopid=tb_club.id where tb_club.category='"+DBTransactionFunctions.getConfigvalue("gender")+"'";
		 result = DB_ReadRowquery(Sql);
			if(result.count>0){
				for(int i = 0 ;i<result.responsedata.size();i++){
					list.add(new Services(result.responsedata.get(i).data.get("id"),
							result.responsedata.get(i).data.get("shopid"),
							result.responsedata.get(i).data.get("service_name"),
							result.responsedata.get(i).data.get("rate")));
				}

			}


		}catch (Exception e){

		}
		return list;
	}

	///to get schedule of current user

	public static ArrayList<Model_shedule> getSheduleData() {
		DBResponseDataTypes.ReadResponse result;
		ArrayList<Model_shedule>list=new ArrayList<>();

		try{
			String Sql="select * from tb_schedule where shopid='"+DBTransactionFunctions.getConfigvalue("userid")+"'";
			result = DB_ReadRowquery(Sql);

			if(result.count>0){
				for (int i = 0;i<result.responsedata.size();i++){
					list.add(new Model_shedule(result.responsedata.get(i).data.get("id"),result.responsedata.get(i).data.get("shopid"),result.responsedata.get(i).data.get("week_day"),result.responsedata.get(i).data.get("openingtime"),result.responsedata.get(i).data.get("closingtime")));
				}}


		}catch (Exception e){
			e.printStackTrace();


		}
		return list;
	}

	//to get schedule data of a specific shop and day

	public static ArrayList<Model_shedule> getSheduleDataByIdAndDay(String id,String Day) {
		DBResponseDataTypes.ReadResponse result;
		ArrayList<Model_shedule>list=new ArrayList<>();

		try{
			String Sql="select * from tb_schedule where shopid='"+id+"' and week_day='"+Day+"'";
			result = DB_ReadRowquery(Sql);

			if(result.count>0){
				for (int i = 0;i<result.responsedata.size();i++){
					list.add(new Model_shedule(result.responsedata.get(i).data.get("id"),result.responsedata.get(i).data.get("shopid"),result.responsedata.get(i).data.get("week_day"),result.responsedata.get(i).data.get("openingtime"),result.responsedata.get(i).data.get("closingtime")));
				}}


		}catch (Exception e){
			e.printStackTrace();


		}
		return list;
	}

//to get current club data

	public static ArrayList<Model_Club> getUserData() {
		DBResponseDataTypes.ReadResponse result;
		ArrayList<Model_Club>list=new ArrayList<>();

		try{
			String Sql="select * from tb_club where id='"+DBTransactionFunctions.getConfigvalue("userid")+"'";
			result = DB_ReadRowquery(Sql);

			if(result.count>0){
				for (int i = 0;i<result.responsedata.size();i++){
					list.add(new Model_Club(result.responsedata.get(i).data.get("id"),
							result.responsedata.get(i).data.get("club_name"),
							result.responsedata.get( i ).data.get( "category" ),
							result.responsedata.get(i).data.get("mobile"),
							result.responsedata.get(i).data.get("email"),
							result.responsedata.get(i).data.get("website"),
							result.responsedata.get(i).data.get("street"),
							result.responsedata.get(i).data.get("city"),
							result.responsedata.get(i).data.get("state"),
							result.responsedata.get(i).data.get("country"),
							result.responsedata.get(i).data.get("district")));
				}
			}


		}catch (Exception e){
			e.printStackTrace();


		}
		return list;
	}

	//to get employee data of current club
	public static ArrayList<Employee> getEmployee() {
		DBResponseDataTypes.ReadResponse result;
		ArrayList<Employee>list=new ArrayList<>();
		try {
			String Sql="select * from tb_employee where shopid='"+DBTransactionFunctions.getConfigvalue("userid")+"'";
			result = DB_ReadRowquery(Sql);
			if (result.count>0){
				for (int i=0;i<result.responsedata.size();i++){
					list.add(new Employee(result.responsedata.get(i).data.get("id"),result.responsedata.get(i).data.get("shopid"),result.responsedata.get(i).data.get("serviceid"),result.responsedata.get(i).data.get("emp_name"),result.responsedata.get(i).data.get("phonenumber")));
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}
	 //get employee dta using shop and service

	public static ArrayList<Employee> getEmployeeByid(String Shopid,String serviceid) {
		DBResponseDataTypes.ReadResponse result;
		ArrayList<Employee>list=new ArrayList<>();
		try {
			String Sql="select * from tb_employee where shopid='"+Shopid+"' and serviceid='"+serviceid+"'";
			result = DB_ReadRowquery(Sql);
			if (result.count>0){
				for (int i=0;i<result.responsedata.size();i++){
					list.add(new Employee(result.responsedata.get(i).data.get("id"),result.responsedata.get(i).data.get("shopid"),result.responsedata.get(i).data.get("serviceid"),result.responsedata.get(i).data.get("emp_name"),result.responsedata.get(i).data.get("phonenumber")));
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}

	//to get service name using service id

	public static String getServiceName(String service_id) {
		String service = null;
		DBResponseDataTypes.ReadResponse result;

		try {
			String Sql="select * from tb_shop_service where id='"+service_id+"'";
			result = DB_ReadRowquery(Sql);
			if (result.count>0){
				service=result.responsedata.get(0).data.get("service_name");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return service;
	}

	// to get datas from club ,service,employee table

	public static ArrayList<Shop_Service_Details> getShopServiceEmployeeData() {
		ArrayList<Shop_Service_Details> listdata = new ArrayList<Shop_Service_Details>();
		DBResponseDataTypes.ReadResponse result;

		try {
			String Sql="select tb_club.id as id,tb_club.club_name as club_name,tb_club.category as category,tb_club.mobile as mobile,tb_club.city as city,tb_club.street as street,tb_club.state as state,tb_club.email as email,tb_shop_service.id as sid,tb_shop_service.service_name as service_name,tb_shop_service.rate as rate,tb_shop_service.gender as gender,tb_employee.id as emp_id,tb_employee.emp_name as emp_name,tb_employee.phonenumber as phonenumber from tb_club INNER JOIN (tb_shop_service INNER JOIN tb_employee ON tb_employee.serviceid=tb_shop_service.id) ON tb_club.id=tb_shop_service.shopid where tb_club.category='"+DBTransactionFunctions.getConfigvalue("gender")+"'";
			result = DB_ReadRowquery(Sql);
			if (result.count>0){
				for(int i =0;i<result.responsedata.size();i++) {
					listdata.add(new Shop_Service_Details(result.responsedata.get(i).data.get("id"),
							result.responsedata.get(i).data.get("club_name"),
							result.responsedata.get( i ).data.get("category"),
							result.responsedata.get(i).data.get("mobile"),
							result.responsedata.get(i).data.get("city"),
							result.responsedata.get(i).data.get("street"),
							result.responsedata.get(i).data.get("email"),
							result.responsedata.get(i).data.get("state"),
							result.responsedata.get(i).data.get("sid"),
							result.responsedata.get(i).data.get("service_name"),
							result.responsedata.get(i).data.get("rate"),
							result.responsedata.get(i).data.get("emp_id"),
							result.responsedata.get(i).data.get("emp_name"),
							result.responsedata.get(i).data.get("phonenumber")));
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return listdata;
	}

	// to get datas from club ,service,employee table using service name
    public static ArrayList<Shop_Service_Details> getShopServiceEmployeeDataWithService(String serviceName) {
        ArrayList<Shop_Service_Details> listdata = new ArrayList<Shop_Service_Details>();
        DBResponseDataTypes.ReadResponse result;

        try {
            String Sql="select tb_club.id as id,tb_club.club_name as club_name,tb_club.category as category,tb_club.mobile as mobile,tb_club.city as city ,tb_club.street as street,tb_club.state as state,tb_club.email as email,tb_shop_service.id as sid,tb_shop_service.service_name as service_name,tb_shop_service.rate as rate,tb_shop_service.gender as gender,tb_employee.id as emp_id,tb_employee.emp_name as emp_name,tb_employee.phonenumber as phonenumber from tb_club INNER JOIN (tb_shop_service INNER JOIN tb_employee ON tb_employee.serviceid=tb_shop_service.id) ON tb_club.id=tb_shop_service.shopid where  tb_club.category='"+DBTransactionFunctions.getConfigvalue("gender")+"'  and tb_shop_service.service_name like '"+serviceName+"' order by tb_shop_service.rate desc";
            result = DB_ReadRowquery(Sql);
            if (result.count>0){
                for(int i =0;i<result.responsedata.size();i++) {
                    listdata.add(new Shop_Service_Details(result.responsedata.get(i).data.get("id"),
                            result.responsedata.get(i).data.get("club_name"),
                            result.responsedata.get( i ).data.get( "category" ),
                            result.responsedata.get(i).data.get("mobile"),
                            result.responsedata.get(i).data.get("city"),
                            result.responsedata.get(i).data.get("street"),
                            result.responsedata.get(i).data.get("email"),
                            result.responsedata.get(i).data.get("state"),
                            result.responsedata.get(i).data.get("sid"),
                            result.responsedata.get(i).data.get("service_name"),
                            result.responsedata.get(i).data.get("rate"),
                            result.responsedata.get(i).data.get("emp_id"),
                            result.responsedata.get(i).data.get("emp_name"),
                            result.responsedata.get(i).data.get("phonenumber")));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listdata;
    }

    //to get all appointments of current user

	public static ArrayList<Model_Appointments> getAppoinments() {
		ArrayList<Model_Appointments> listdata = new ArrayList<Model_Appointments>();
		DBResponseDataTypes.ReadResponse result;

		try {
			String Sql="select tb_appoinments.id as id,tb_appoinments.shopid as shopid,tb_appoinments.userid as userid,tb_appoinments.status as status,tb_appoinments.appoinmenttime as appoinmenttime,tb_shop_service.id as service_id,tb_shop_service.service_name as service_name,tb_shop_service.rate as rate,tb_employee.id as emp_id ,tb_employee.emp_name as emp_name,tb_employee.phonenumber as phonenumber ,tb_club.club_name as club_name,tb_club.mobile as mobile,tb_club.city as city,tb_club.street as street,tb_club.state as state,tb_club.email as email from tb_appoinments INNER JOIN (tb_shop_service INNER JOIN tb_employee ON tb_employee.serviceid=tb_shop_service.id) ON tb_appoinments.service_id=tb_shop_service.id join tb_club on tb_club.id=tb_appoinments.shopid where tb_appoinments.userid='"+DBTransactionFunctions.getConfigvalue("userid")+"' group by tb_appoinments.id";
			result = DB_ReadRowquery(Sql);
			if (result.count>0){
				for(int i =0;i<result.responsedata.size();i++) {
					listdata.add(new Model_Appointments(result.responsedata.get(i).data.get("shopid"),
							result.responsedata.get(i).data.get("club_name"),
							result.responsedata.get(i).data.get("mobile"),
							result.responsedata.get(i).data.get("city"),
							result.responsedata.get(i).data.get("street"),
							result.responsedata.get(i).data.get("email"),
							result.responsedata.get(i).data.get("state"),
							result.responsedata.get(i).data.get("service_id"),
							result.responsedata.get(i).data.get("service_name"),
							result.responsedata.get(i).data.get("rate"),
							result.responsedata.get(i).data.get("emp_id"),
							result.responsedata.get(i).data.get("emp_name"),
							result.responsedata.get(i).data.get("phonenumber"),
							result.responsedata.get(i).data.get("id"),
							result.responsedata.get(i).data.get("userid"),
							result.responsedata.get(i).data.get("status"),
							result.responsedata.get(i).data.get("appoinmenttime")));
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return listdata;
	}
	//to get all appointments of current club

	public static ArrayList<ModelClubAppointments> getClubAppoinment() {
		ArrayList<ModelClubAppointments> listdata = new ArrayList<ModelClubAppointments>();
		DBResponseDataTypes.ReadResponse result;

		try {
			String Sql="select tb_appoinments.id as id,tb_appoinments.shopid as shopid,tb_appoinments.userid as userid,tb_appoinments.status as status," +
                    "tb_appoinments.appoinmenttime as appoinmenttime," +
					"tb_shop_service.id as serid,tb_shop_service.service_name as service_name,tb_shop_service.rate as rate,tb_employee.id as empid," +
					"tb_employee.emp_name as emp_name,tb_employee.phonenumber as phonenumber ,tb_user.name as name,tb_user.mobile as mobile,tb_user.address as address,tb_user.email as email," +
					"tb_user.gender  as gender from tb_appoinments INNER JOIN (tb_shop_service INNER JOIN tb_employee ON tb_employee.serviceid=tb_shop_service.id) ON tb_appoinments.service_id=tb_shop_service.id join tb_user on tb_user.id=tb_appoinments.userid where tb_appoinments.shopid='"+DBTransactionFunctions.getConfigvalue("userid")+ "'group by tb_appoinments.id";
			result = DB_ReadRowquery(Sql);
			if (result.count>0){
				for(int i =0;i<result.responsedata.size();i++) {
					listdata.add(new ModelClubAppointments(result.responsedata.get(i).data.get("id"),
							result.responsedata.get(i).data.get("shopid"),
							result.responsedata.get(i).data.get("userid"),
							result.responsedata.get(i).data.get("status"),
							result.responsedata.get(i).data.get("appoinmenttime"),
							result.responsedata.get(i).data.get("serid"),
							result.responsedata.get(i).data.get("service_name"),
							result.responsedata.get(i).data.get("rate"),
							result.responsedata.get(i).data.get("empid"),
							result.responsedata.get(i).data.get("emp_name"),
							result.responsedata.get(i).data.get("phonenumber"),
							result.responsedata.get(i).data.get("name"),
							result.responsedata.get(i).data.get("mobile"),
							result.responsedata.get(i).data.get("address"),
							result.responsedata.get(i).data.get("email"),
							result.responsedata.get(i).data.get("gender")));
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return listdata;
	}

	//to get data from club,service,employee using club id and service id

	public static ArrayList<Shop_Service_Details> getShopServiceEmployeeDataByID(String shopid,String Serviceid) {
		ArrayList<Shop_Service_Details> listdata = new ArrayList<Shop_Service_Details>();
		DBResponseDataTypes.ReadResponse result;

		try {
			String Sql="select tb_club.id as id,tb_club.category as category,tb_club.club_name as club_name,tb_club.mobile as mobile,tb_club.city as city,tb_club.street as street,tb_club.state as state,tb_club.email as email,tb_shop_service.id as sid,tb_shop_service.service_name as service_name,tb_shop_service.rate as rate,tb_employee.id as empid,tb_employee.emp_name as emp_name,tb_employee.phonenumber as phonenumber from tb_club INNER JOIN (tb_shop_service INNER JOIN tb_employee ON tb_employee.serviceid=tb_shop_service.id) ON tb_club.id=tb_shop_service.shopid where tb_club.id='"+shopid+"' and tb_shop_service.id='"+Serviceid+"'";
			result = DB_ReadRowquery(Sql);
			if (result.count>0){
				for(int i =0;i<result.responsedata.size();i++) {
					listdata.add(new Shop_Service_Details(result.responsedata.get(i).data.get("id"),
							result.responsedata.get(i).data.get("club_name"),
							result.responsedata.get( i ).data.get( "category" ),
							result.responsedata.get(i).data.get("mobile"),
							result.responsedata.get(i).data.get("city"),
							result.responsedata.get(i).data.get("street"),
							result.responsedata.get(i).data.get("email"),
							result.responsedata.get(i).data.get("state"),
							result.responsedata.get(i).data.get("sid"),
							result.responsedata.get(i).data.get("service_name"),
							result.responsedata.get(i).data.get("rate"),
							result.responsedata.get(i).data.get("empid"),
							result.responsedata.get(i).data.get("emp_name"),
							result.responsedata.get(i).data.get("phonenumber")));
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return listdata;
	}

	//to get all club data
	public static ArrayList<Model_Club> getClubData() {
		DBResponseDataTypes.ReadResponse result;
		ArrayList<Model_Club>list=new ArrayList<>();

		try{
			String Sql="select * from tb_club where category='"+DBTransactionFunctions.getConfigvalue("gender")+"'";
			result = DB_ReadRowquery(Sql);

			if(result.count>0){
				for (int i = 0;i<result.responsedata.size();i++){
					list.add(new Model_Club(result.responsedata.get(i).data.get("id"),
							result.responsedata.get(i).data.get("club_name"),
							result.responsedata.get( i ).data.get( "category" ),
							result.responsedata.get(i).data.get("mobile"),
							result.responsedata.get(i).data.get("email"),
							result.responsedata.get(i).data.get("website"),
							result.responsedata.get(i).data.get("street"),
							result.responsedata.get(i).data.get("city"),
							result.responsedata.get(i).data.get("state"),
							result.responsedata.get(i).data.get("country"),
							result.responsedata.get(i).data.get("district")));
				}
			}

		}catch (Exception e){
			e.printStackTrace();


		}
		return list;
	}
	public static ArrayList<Model_Club> getNearbyClubData() {
		DBResponseDataTypes.ReadResponse result;
		ArrayList<Model_Club>list=new ArrayList<>();

		try{
			String Sql="select * from tb_club";
			result = DB_ReadRowquery(Sql);

			if(result.count>0){
				for (int i = 0;i<result.responsedata.size();i++){
					list.add(new Model_Club(result.responsedata.get(i).data.get("id"),
							result.responsedata.get(i).data.get("club_name"),
							result.responsedata.get( i ).data.get( "category" ),
							result.responsedata.get(i).data.get("mobile"),
							result.responsedata.get(i).data.get("email"),
							result.responsedata.get(i).data.get("website"),
							result.responsedata.get(i).data.get("street"),
							result.responsedata.get(i).data.get("city"),
							result.responsedata.get(i).data.get("state"),
							result.responsedata.get(i).data.get("country"),
							result.responsedata.get(i).data.get("district")));
				}
			}

		}catch (Exception e){
			e.printStackTrace();


		}
		return list;
	}
	//to get data from service and employee tables using the club id
	public static ArrayList<Model_Employee_Service> getServiceEmployeeDataById(String id) {
		DBResponseDataTypes.ReadResponse result;
		ArrayList<Model_Employee_Service>list=new ArrayList<>();

		try{
			String Sql="select tb_shop_service.id as id,tb_shop_service.rate as rate,tb_shop_service.service_name as service_name,tb_employee.id as empid,tb_employee.emp_name  as emp_name from tb_shop_service join tb_employee on tb_shop_service.shopid=tb_employee.shopid where tb_shop_service.shopid='"+id+"' group by tb_shop_service.service_name";
			result = DB_ReadRowquery(Sql);

			if(result.count>0){
				for (int i = 0;i<result.responsedata.size();i++){
					list.add(new Model_Employee_Service(result.responsedata.get(i).data.get("id"),
							result.responsedata.get(i).data.get("rate"),
							result.responsedata.get(i).data.get("service_name"),
							result.responsedata.get(i).data.get("empid"),
							result.responsedata.get(i).data.get("emp_name")));
				}
			}


		}catch (Exception e){
			e.printStackTrace();


		}
		return list;
	}

	//to get user data

	public static ArrayList<UserModel> getEditData() {
		DBResponseDataTypes.ReadResponse result;
		ArrayList<UserModel>list=new ArrayList<>();

		try {
			String Sql="select * from tb_user where id='"+DBTransactionFunctions.getConfigvalue("userid")+"'";
			result = DB_ReadRowquery(Sql);

			if(result.count>0) {
				for (int i = 0; i < result.responsedata.size(); i++) {
					list.add(new UserModel( result.responsedata.get( i ).data.get( "id" ),
							result.responsedata.get( i ).data.get( "name" ),
							result.responsedata.get( i ).data.get( "address" ),
							result.responsedata.get( i ).data.get( "name" ),
							result.responsedata.get(i).data.get("id"),
							result.responsedata.get(i).data.get("name"),
							result.responsedata.get(i).data.get("mobile"),
							result.responsedata.get(i).data.get("gender"),
							result.responsedata.get(i).data.get("p_image"),
							result.responsedata.get(i).data.get("age")
					));
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}
	//to get club data



	public static ArrayList<Model_Club> getClubEditData() {
		DBResponseDataTypes.ReadResponse result;
		ArrayList<Model_Club>list=new ArrayList<>();

		try{
			String Sql="select * from tb_club where id='"+DBTransactionFunctions.getConfigvalue("userid")+"'";
			result = DB_ReadRowquery(Sql);

			if(result.count>0){
				for (int i = 0;i<result.responsedata.size();i++){
					list.add(new Model_Club(result.responsedata.get(i).data.get("id"),
							result.responsedata.get(i).data.get("club_name"),
							result.responsedata.get( i ).data.get( "category" ),
							result.responsedata.get(i).data.get("mobile"),
							result.responsedata.get(i).data.get("email"),
							result.responsedata.get(i).data.get("website"),
							result.responsedata.get(i).data.get("street"),
							result.responsedata.get(i).data.get("city"),
							result.responsedata.get(i).data.get("state"),
							result.responsedata.get(i).data.get("country"),
							result.responsedata.get(i).data.get("district"),
							result.responsedata.get(i).data.get("p_image")));
				}
			}

		}catch (Exception e){
			e.printStackTrace();


		}
		return list;
	}

	//to get feed back using club id and service id

	public static ArrayList<FeedbackListt> getFeedback(String shopid,String serviceid) {
		DBResponseDataTypes.ReadResponse result;
		ArrayList<FeedbackListt>list=new ArrayList<>();

		try{
			String Sql="select tb_feedback.userid as userid ,tb_feedback.shopid as shopid,tb_feedback.message as message,tb_club.club_name as club_name from tb_feedback join tb_club on tb_feedback.shopid =tb_club.id where tb_feedback.shopid='"+shopid+"' and tb_feedback.serviceid='"+serviceid+"'";
			result = DB_ReadRowquery(Sql);

			if(result.count>0){
				for (int i = 0;i<result.responsedata.size();i++){
					list.add(new FeedbackListt(result.responsedata.get(i).data.get("userid"),
							result.responsedata.get(i).data.get("shopid"),
							result.responsedata.get(i).data.get("message"),
							result.responsedata.get(i).data.get("club_name")
					));
				}
			}


		}catch (Exception e){
			e.printStackTrace();


		}
		return list;
	}
	//to get feed back belongs to the club

	public static ArrayList<FeedbackListt> getFeedbackForClub() {
		DBResponseDataTypes.ReadResponse result;
		ArrayList<FeedbackListt>list=new ArrayList<>();

		try{
			String Sql="select tb_feedback.userid as userid,tb_feedback.shopid as shopid,tb_feedback.serviceid as serviceid,tb_feedback.message as message,tb_club.club_name from tb_feedback join tb_club on tb_feedback.shopid =tb_club.id where tb_club.id='"+getConfigvalue("userid")+"'";
			result = DB_ReadRowquery(Sql);

			if(result.count>0){
				for (int i = 0;i<result.responsedata.size();i++){
					list.add(new FeedbackListt(result.responsedata.get(i).data.get("userid"),
							result.responsedata.get(i).data.get("shopid"),
							result.responsedata.get(i).data.get("message"),
							result.responsedata.get(i).data.get("serviceid"),
							result.responsedata.get(i).data.get("club_name")
					));
				}
			}


		}catch (Exception e){
			e.printStackTrace();


		}
		return list;
	}

	//to get user name using user id

	public static String getUsername(String userid) {
		String name = null;
		try {

			String Sql = "select club_name from tb_club where id='" + userid + "'";
			DBResponseDataTypes.ReadResponse result = DB_ReadRowquery(Sql);
			name = result.responsedata.get(0).data.get("club_name");

		}catch (Exception e){
			e.printStackTrace();
		}
		return name;
	}
	public static boolean isEmailFound(String tablename,String  where,String email) {
		boolean name = false;
		try {

			String Sql = "select * from " + tablename + " where " + where + "='" + email + "'";
			DBResponseDataTypes.ReadResponse result = DB_ReadRowquery(Sql);
			if(result.responsedata.size()>0){
				name =false;
			}else {
				name =true;
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		return name;
	}


public  static  ArrayList<Model_Club>  getNearByShop()
{ArrayList<Model_Club> nearclubdata =new ArrayList<>(  );
 String sql="select address from tb_user where id="+getConfigvalue( "userid" );
	DBResponseDataTypes.ReadResponse result = DB_ReadRowquery(sql);
	String addres=result.responsedata.get( 0 ).data.get( "address" ).toLowerCase();
	ArrayList<Model_Club> clubdata = getClubData();

	for(int i=0;i<clubdata.size();i++)
	{
		String city=clubdata.get( i ).getCity().toLowerCase();
		String street=clubdata.get( i).getStreet().toLowerCase();
		if(addres.contains( clubdata.get( i ).getStreet().toLowerCase() )|| addres.contains( clubdata.get( i ).getCity().toLowerCase() ))
		{
			nearclubdata.add( new Model_Club( clubdata.get( i ).getId(),
					clubdata.get( i ).getName(),
					clubdata.get( i ).getCategory(),
					clubdata.get( i ).getMobile(),
					clubdata.get( i ).getEmail(),
					clubdata.get( i ).getWebsite(),
					clubdata.get( i ).getStreet(),
					clubdata.get( i ).getCity(),
					clubdata.get( i ).getState(),
					clubdata.get( i ).getCountry(),
					clubdata.get( i ).getDistrict() ) );
		}
	}

return nearclubdata;
}

	public static String getClubImage(String userid) {

		String sql="select p_image from tb_club where id='"+userid+"'";
		DBResponseDataTypes.ReadResponse result = DB_ReadRowquery(sql);
		String addres=result.responsedata.get( 0 ).data.get( "p_image" );
		return addres;
	}
}
