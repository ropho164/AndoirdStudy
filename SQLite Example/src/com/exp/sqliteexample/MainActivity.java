package com.exp.sqliteexample;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener{
	Intent intent;
	TextView txtID;
	ListView lv;
	String[] columTable = new String[] {"id", "firstname", "lastname"};
    int[] textView = new int[] { R.id.txtID, R.id.txtFName, R.id.txtLName};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.listEmp); 
		DBController controller = new DBController(this);
		ArrayList<HashMap<String, String>> employeeList =  controller.getAllEmployees();
		if(employeeList.size()!=0) {
			lv.setOnItemClickListener(this);
			SimpleAdapter adapter = new SimpleAdapter( this, employeeList, R.layout.listview_simple, columTable, textView); 
			lv.setAdapter(adapter);
		}
	}
	public void showAddForm(View view) {
		Intent objIntent = new Intent(getApplicationContext(), AddNew.class);
		startActivity(objIntent);
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		String  uid = ((TextView) v.findViewById(R.id.txtID)).getText().toString();
		Toast.makeText(getApplicationContext(),"id = " + uid, Toast.LENGTH_SHORT).show();
		 Intent  objIndent = new Intent(getApplicationContext(),Edit.class);
		 objIndent.putExtra("idPara", uid); 
		 startActivity(objIndent); 
	}
}
