package net.binzume.android.rssample;

import android.os.Bundle;
import android.renderscript.*;
import android.util.Log;
import android.widget.ImageView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		Bitmap in = BitmapFactory.decodeResource(getResources(), R.drawable.sample);
		Bitmap out = Bitmap.createBitmap(in.getWidth(), in.getHeight(), in.getConfig());
		
		long t = System.currentTimeMillis();
		FilterRS.filter(getApplicationContext(), in, out, 600, 400, 10);
		
		Log.d("","time: " + (System.currentTimeMillis() - t));
		
		((ImageView)findViewById(R.id.image)).setImageBitmap(out);
		
	}
	
	public void rs() {
		RenderScript rs = RenderScript.create(getApplicationContext());
		ScriptC_hello hello = new ScriptC_hello(rs, getResources(), R.raw.hello);
		Allocation result = Allocation.createSized(rs, Element.I32(rs), 1);
		hello.bind_result(result);

		hello.invoke_hello(123, 456);

		int resultArray[] = new int[1];
		result.copyTo(resultArray);
		Log.d("MainActivity","rs_result: " + resultArray[0]);
		
		rs.destroy();
	}
}
