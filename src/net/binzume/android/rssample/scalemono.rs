
// 1/N scaling

#pragma version(1)
#pragma rs java_package_name(net.binzume.android.rssample)

rs_allocation inBuffer;
rs_allocation outBuffer;
rs_script script;

static uint scale_N;
static float scale;

void root(uchar4 *v_out, uint32_t x, uint32_t y) {
	int c = 0;
	for (int dy = 0; dy < scale; dy++) {
		uchar sh = 7-(x*scale_N & 7);
		ushort dd = *(uchar*)rsGetElementAt(inBuffer, x*scale_N /8, y*scale_N + dy);
		if (sh < scale) {
			dd = (dd << 8) | *(uchar*)rsGetElementAt(inBuffer, x*scale_N /8 + 1, y*scale_N + dy);
			sh+=8;
		}
		for (int i = 0; i < scale ; i++) {
			c += ( dd >> sh-i )&1^1;
		}
	}
	float b = c*scale*scale;
	*v_out = rsPackColorTo8888(b,b,b);
}

void do_scale(int _scale) {
	scale_N = _scale;
	scale = 1.0 / _scale;
	rsForEach(script, outBuffer, outBuffer);
	return;
}
