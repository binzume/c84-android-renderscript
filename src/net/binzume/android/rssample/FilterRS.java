package net.binzume.android.rssample;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.Float2;
import android.renderscript.RenderScript;
import android.renderscript.Type;

public class FilterRS {

	public static void filter(Context context,/*const*/ Bitmap in, Bitmap out, float centerX, float centerY, int count) {
		final int width = in.getWidth();
		final int height = in.getHeight();
		
		RenderScript rs = RenderScript.create(context);
		ScriptC_sample filter = new ScriptC_sample(rs, context.getResources(), R.raw.sample);
		Allocation inAllocation = Allocation.createFromBitmap(rs, in);
		Allocation outAllocation = Allocation.createTyped(rs, new Type.Builder(rs, Element.RGBA_8888(rs)).setX(width).setY(height).create());
		
		for (int i= 0; i<count; i++) {
			filter.set_inBuffer(inAllocation);
			filter.set_center(new Float2(centerX, centerY));
			filter.forEach_root(outAllocation);
		}

		outAllocation.copyTo(out);
		
		rs.destroy();
	}
}
