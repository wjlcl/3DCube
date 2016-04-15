package com.example.dcube_1;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class CubeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		设置窗体为全屏、无标题模式！！！！！
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		MyGLSurfaceView mglv=new MyGLSurfaceView(this);
		mglv.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
		setContentView(mglv);
		 
	} 
}
