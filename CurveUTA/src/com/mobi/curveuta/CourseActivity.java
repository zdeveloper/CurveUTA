package com.mobi.curveuta;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CourseActivity extends Activity {

	ListView listView;

	ArrayList<String> arr = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course);
		listView = (ListView) findViewById(R.id.listView_courses);
		
		String departmentName = getIntent().getStringExtra("courseSelected");

		//List<ParseQuery<ParseObject>> queryList = new ArrayList<ParseQuery<ParseObject>>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Classes");
		
		ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Departments");
		query2.whereEqualTo("name", departmentName);
		
		query.include("department");
		query.whereMatchesQuery("department", query2);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> list, ParseException e) {
				for (int i = 0; i < list.size(); i++) {
						arr.add( list.get(i).getString("Name"));
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, arr);
				listView.setAdapter(adapter);
			}
		});

	}
}
