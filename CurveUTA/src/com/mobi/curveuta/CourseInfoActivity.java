package com.mobi.curveuta;

import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class CourseInfoActivity extends Activity {

	private String courseName;
	private String depName;
	private String professorName;
	private TextView professorText, courseNameText, departmentText, descriptionText;
	private ImageView img;
	
	ParseObject obj1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_info);
		
		professorText = (TextView) findViewById(R.id.professorName);
		courseNameText = (TextView) findViewById(R.id.courseName);
		departmentText = (TextView) findViewById(R.id.department);
		descriptionText = (TextView) findViewById(R.id.description);
		img = (ImageView) findViewById(R.id.imageView_class_img);
		
		courseName = getIntent().getStringExtra("courseName");
		depName = getIntent().getStringExtra("depName");
		professorName = getIntent().getStringExtra("professorName");
		
		professorText.setText(professorName);
		departmentText.setText(depName);
		courseNameText.setText(courseName);
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Classes");
		query.whereEqualTo("Name", courseName);
		
		query.getFirstInBackground(new GetCallback<ParseObject>() {
			@Override
			public void done(ParseObject obj, ParseException e) {
				descriptionText.setText( obj.get("Description").toString() );
				
				obj.getParseFile("img").getDataInBackground(new GetDataCallback() {
					@Override
					public void done(byte[] b, ParseException e) {
						Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
						img.setImageBitmap(bitmap);
						courseNameText.setText("CRASH");
					}
				});
			}
		});
		
	}
}
