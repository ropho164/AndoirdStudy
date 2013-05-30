package com.exp.sqliteexample;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Edit extends Activity{
	DBController controller = new DBController(this);
	EditText txtFirstName,txtLastName,txtBirthday,txtIDCard,txtEmail;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		txtFirstName = (EditText) findViewById(R.id.txtFirstName);
		txtLastName = (EditText) findViewById(R.id.txtLastName);
		txtBirthday = (EditText) findViewById(R.id.txtBirthday);
		txtIDCard = (EditText) findViewById(R.id.txtIDCard);
		txtEmail = (EditText) findViewById(R.id.txtEmail);
		Intent objIntent = getIntent();
		String id = objIntent.getStringExtra("idPara");
		HashMap<String, String> empList = controller.getEmployeeInfo(id);
		if(empList.size()!=0) {
			txtFirstName.setText(empList.get("firstname"));
			txtLastName.setText(empList.get("lastname"));
			txtBirthday.setText(empList.get("birthday"));
			txtIDCard.setText(empList.get("idcard"));
			txtEmail.setText(empList.get("email"));
		}
	}
	public void editEmployee(View view) {
		HashMap<String, String> queryValues =  new  HashMap<String, String>();
		txtFirstName = (EditText) findViewById(R.id.txtFirstName);
		Intent objIntent = getIntent();
		String txtID = objIntent.getStringExtra("idPara");
		queryValues.put("txtID", txtID);
		queryValues.put("txtFirstName", txtFirstName.getText().toString());
		queryValues.put("txtLastName", txtLastName.getText().toString());
		queryValues.put("txtBirthday", txtBirthday.getText().toString());
		queryValues.put("txtIDCard", txtIDCard.getText().toString());
		queryValues.put("txtEmail", txtEmail.getText().toString());
		controller.updateEmployee(queryValues);
		this.callHomeActivity(view);
	}
	public void removeEmployee(View view) {
		Intent objIntent = getIntent();
		String txtID = objIntent.getStringExtra("idPara");
		controller.deleteEmployeeFinal(Integer.parseInt(txtID.toString()));
		this.callHomeActivity(view);
	}
	public void callHomeActivity(View view) {
		Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(objIntent);
	}
}