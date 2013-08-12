#pragma version(1)
#pragma rs java_package_name(net.binzume.android.rssample)
#include <rs_matrix.rsh>

rs_script script;
rs_allocation inBuffer;
rs_allocation outBuffer;
float2 center;

void root(uchar4 *v_out, uint32_t x, uint32_t y) {
	float2 pos = {x, y};
	float theta = length(pos - center) / 1000.0;
	rs_matrix2x2 m = {cos(theta),-sin(theta),sin(theta),cos(theta)};
	float2 p = rsMatrixMultiply(&m,pos);
	*v_out = *(uchar4*)rsGetElementAt(inBuffer, p.x, p.y); //rsPackColorTo8888(b,b,b);
}
