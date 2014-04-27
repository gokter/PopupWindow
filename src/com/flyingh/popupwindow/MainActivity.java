package com.flyingh.popupwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

public class MainActivity extends ActionBarActivity {
	private static final String LABEL = "label";
	private static final String ICON = "icon";
	private static final int[] icons = { R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5,
			R.drawable.i6, R.drawable.i7, R.drawable.i8 };
	private static final String[] labels = { "A", "B", "C", "D", "E", "F", "G", "H" };

	private LinearLayout linearLayout;
	private PopupWindow popupWindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		linearLayout = (LinearLayout) findViewById(R.id.container);
		View contentView = getLayoutInflater().inflate(R.layout.popup_window, null);
		popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		GridView gridView = (GridView) contentView.findViewById(R.id.gridView);
		List<Map<String, Object>> data = new ArrayList<>();
		for (int i = 0; i < icons.length; i++) {
			HashMap<String, Object> map = new HashMap<>();
			map.put(ICON, icons[i]);
			map.put(LABEL, labels[i]);
			data.add(map);
		}
		gridView.setAdapter(new SimpleAdapter(this, data, R.layout.grid_item, new String[] { ICON, LABEL }, new int[] {
				R.id.imageView, R.id.textView }));

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				popupWindow.dismiss();
			}
		});
		popupWindow.setAnimationStyle(R.style.PopupWindow);
		popupWindow.setFocusable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
	}

	public void open(View view) {
		popupWindow.showAtLocation(linearLayout, Gravity.BOTTOM, 0, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
