package com.exp.sqliteexample;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class DBController extends SQLiteOpenHelper {
	private static final String LOGCAT = null;
	public DBController(Context applicationcontext) {
		super(applicationcontext, "Employee_Manager.db", null, 1);
		Log.d(LOGCAT,"Created");
	}
	@Override
	public void onCreate(SQLiteDatabase database) {
		String query;
		query = "CREATE TABLE employees ( id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , firstname TEXT, lastname TEXT, birthday TEXT, idcard TEXT, email TEXT)";
		database.execSQL(query);
		Log.d(LOGCAT,"Employees Created");
	}
	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
		String query;
		query = "DROP TABLE IF EXISTS employees";
		database.execSQL(query);
		onCreate(database);
	}
	public void insertEmployee(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("firstname", queryValues.get("txtFirstName"));
		values.put("lastname", queryValues.get("txtLastName"));
		values.put("birthday", queryValues.get("txtBirthday"));
		values.put("idcard", queryValues.get("txtIDCard"));
		values.put("email", queryValues.get("txtEmail"));
		database.insert("employees", null, values);
		database.close();
	}
	public int updateEmployee(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("firstname", queryValues.get("txtFirstName"));
		values.put("lastname", queryValues.get("txtLastName"));
		values.put("birthday", queryValues.get("txtBirthday"));
		values.put("idcard", queryValues.get("txtIDCard"));
		values.put("email", queryValues.get("txtEmail"));
		return database.update("employees", values, "id" + " = ?", new String[] { queryValues.get("txtID") });
	}
	public void deleteEmployee(int id) {
		SQLiteDatabase database = this.getWritableDatabase();
		String deleteQuery = "DELETE FROM  employees where id="+ id;
		database.execSQL(deleteQuery);
	}
	public boolean deleteEmployeeFinal(int id)
	{
		SQLiteDatabase database = this.getWritableDatabase();
		return database.delete("employees", "id" +"=" + id, null) > 0;
	}
	public ArrayList<HashMap<String, String>> getAllEmployees() {
		ArrayList<HashMap<String, String>> wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT id, firstname, lastname FROM employees";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", cursor.getString(0));
				map.put("firstname", cursor.getString(1));
				map.put("lastname", cursor.getString(2));
				wordList.add(map);
			} while (cursor.moveToNext());
		}
		return wordList;
	}
	public HashMap<String, String> getEmployeeInfo(String id) {
		HashMap<String, String> wordList = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM employees where id='"+id+"'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				wordList.put("id", cursor.getString(0));
				wordList.put("firstname", cursor.getString(1));
				wordList.put("lastname", cursor.getString(2));
				wordList.put("birthday", cursor.getString(3));
				wordList.put("idcard", cursor.getString(4));
				wordList.put("email", cursor.getString(5));
			} while (cursor.moveToNext());
		} 
		return wordList;
	} 
}
