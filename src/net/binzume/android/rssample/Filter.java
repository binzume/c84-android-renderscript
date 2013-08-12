package net.binzume.android.rssample;

import android.content.Context;
import android.graphics.Bitmap;

public class Filter {

	public static void filter(Context context, /*const*/ Bitmap in, Bitmap out, float centerX, float centerY) {
		final int width = in.getWidth();
		final int height = in.getHeight();
		int[] inbuf = new int[width*height];
		int[] outbuf = new int[width*height];
		in.getPixels(inbuf,0, width, 0,0, width,height);
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				double d = (x-centerX)*(x-centerX) + (y-centerY)*(y-centerY);
				double theta = Math.sqrt(d) / 1000;
				double posX = x * Math.cos(theta) + y*Math.sin(theta);
				double posY = x * -Math.sin(theta) + y*Math.cos(theta);
				if (posX > 0 && posY > 0 && posX < width && posY < height) {
					outbuf[y*width + x] = inbuf[(int)posY * width + (int)posX];
				} else {
					outbuf[y*width + x] = 0;
				}
			}
		}
		
		out.setPixels(outbuf, 0, width, 0, 0, width, height);
	}
}
