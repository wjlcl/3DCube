package com.example.dcube_1;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyGLSurfaceView extends GLSurfaceView {

	public MyGLSurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		GLRender render=new GLRender();
		this.setRenderer(render);
	}

}
