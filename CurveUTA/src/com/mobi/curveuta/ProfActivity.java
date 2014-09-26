package com.mobi.curveuta;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProfActivity extends Activity {

	private ListView list;
	private String courseName;
	private String depName;
	
	ArrayList<String> profList = new ArrayList<String>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prof);
		
		list = (ListView) findViewById(R.id.listView_prof);
		
		courseName = getIntent().getStringExtra("className");
		depName = getIntent().getStringExtra("departmentName");
		

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Classes");
		query.whereContains("Name", courseName);
		
		
		ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Departments");
		query2.whereEqualTo("name", depName);

		
		query.include("department");
		query.include("prof");
		
		query.whereMatchesQuery("department", query2);
		query.findInBackground(new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> list1, ParseException arg1) {
				for (int i = 0; i < list1.size(); i++) {
					ParseObject prof = list1.get(i).getParseObject("prof");
					profList.add( prof.getString("name") );
				}
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, profList);
				list.setAdapter(adapter);
				
			}
		});

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Intent i = new Intent(ProfActivity.this, CourseInfoActivity.class);
				i.putExtra("professorName", profList.get(position));
				i.putExtra("depName", depName);
				i.putExtra("courseName", courseName);	
				startActivity(i);
			}
		});
	}
}
