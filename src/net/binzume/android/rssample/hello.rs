   #pragma version(1)
   #pragma rs java_package_name(net.binzume.android.rssample)

   rs_script script;
   int *result;

   void hello(int a, int b) {
           *result = a + b;
           rsDebug("Hello, RenderScript! ", result);
   }
