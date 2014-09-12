package com.mobi.curveuta;

import com.parse.ParseQueryAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class DepartmentActivity extends Activity {

	ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_department);

		final ParseQueryAdapter adapter2 = new ParseQueryAdapter(this, "Departments");
		adapter2.setTextKey("name");
		adapter2.setImageKey("img");
		
		list = (ListView) findViewById(R.id.listView_department);
		list.setAdapter(adapter2);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View parent, int position,
					long arg3) {
				Toast.makeText(getApplicationContext(), "You clicked on # " + position, Toast.LENGTH_SHORT).show();
				
				String courseSelected = adapter2.getItem(position).get("name").toString();
				
				Intent i = new Intent(DepartmentActivity.this , CourseActivity.class);
				i.putExtra("courseSelected", courseSelected);
				
				startActivity(i);
				
				
			}
		});
		
		
		
	}
}
