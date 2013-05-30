package com.exp.sqliteexample;

import java.util.HashMap;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class AddNew extends Activity{	
	EditText txtFirstName,txtLastName,txtBirthday,txtIDCard,txtEmail;
	DBController controller = new DBController(this);
	static final int DATE_DIALOG_ID = 999;
	private int year;
	private int month;
	private int day;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		txtFirstName = (EditText) findViewById(R.id.txtFirstName);
		txtLastName = (EditText) findViewById(R.id.txtLastName);
		txtBirthday = (EditText) findViewById(R.id.txtBirthday);
		txtIDCard = (EditText) findViewById(R.id.txtIDCard);
		txtEmail = (EditText) findViewById(R.id.txtEmail);
	}
	public void addNewEmployee(View view) {
		HashMap<String, String> queryValues =  new  HashMap<String, String>();
		queryValues.put("txtFirstName", txtFirstName.getText().toString());
		queryValues.put("txtLastName", txtLastName.getText().toString());
		queryValues.put("txtBirthday", txtBirthday.getText().toString());
		queryValues.put("txtIDCard", txtIDCard.getText().toString());
		queryValues.put("txtEmail", txtEmail.getText().toString());
		controller.insertEmployee(queryValues);
		this.callHomeActivity(view);
	}
	public void callHomeActivity(View view) {
		Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(objIntent);
	} 
	@SuppressWarnings("deprecation")
	public void viewDatePicker(View view) {
		showDialog(DATE_DIALOG_ID);
	} 
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, datePickerListener, year, month,day);
		}
		return null;
	}
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
			txtBirthday.setText(new StringBuilder().append(month + 1).append("-").append(day).append("-").append(year).append(" "));
		}
	};
}