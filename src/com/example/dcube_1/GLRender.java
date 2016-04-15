package com.example.dcube_1;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class GLRender implements Renderer {
	private float anglez=0;
	private float angley=0;
	private float anglex=0;
	private FloatBuffer vertexesBuffer;
	private FloatBuffer colorBuffer;
	private ByteBuffer triangleBuffer;
	private float[] data_vertexes = new float[] {  
			0.5f,0.5f,0.5f,
			0.5f,-0.5f,0.5f,
			-0.5f,-0.5f,0.5f,
			-0.5f,0.5f,0.5f,
			
			0.5f,0.5f,-0.5f,
			0.5f,-0.5f,-0.5f,
			-0.5f,-0.5f,-0.5f,
			-0.5f,0.5f,-0.5f
//			1,1,1,
//			1,-1,1,
//			-1,-1,1,
//			-1,1,1,
//			1,1,-1,
//			1,-1,-1,
//			-1,-1,1,
//			-1,1,1,
//			

	};
	private float[] data_corlor = new float[] {
			1,1,0,1,
			1,0,1,1, 
			0,1,1,1,
			1,0,0,1,
			0,0,0,1,
			0,0,1,1,
			0,1,0,1,
			1,1,1,1
	};
	private byte[] data_triangle = new byte[] {
			0, 1, 2,
			0, 2, 3,
			0, 3, 7, 
			0,7, 4,
			0, 4, 5,
			0, 5, 1,
			6, 5, 4,
			6, 4, 7, 
			6, 7, 3,
			6, 3, 2, 
			6, 2,1,
			6, 1, 5
			
	};

	public GLRender() {
		vertexesBuffer = this.getfloatbuffer(data_vertexes);
		colorBuffer = this.getfloatbuffer(data_corlor);
		triangleBuffer = this.getbytebuffer(data_triangle);

	}

	public FloatBuffer getfloatbuffer(float[] vertexe) {
		FloatBuffer buffer = null;

		ByteBuffer qbb = ByteBuffer.allocateDirect(vertexe.length * 4);
		qbb.order(ByteOrder.nativeOrder());

		buffer = qbb.asFloatBuffer();
		buffer.put(vertexe);
		buffer.position(0);
		return buffer;

	}

	public ByteBuffer getbytebuffer(byte indice[]) {
		ByteBuffer buffer = ByteBuffer.allocateDirect(indice.length);
		buffer.put(indice);
		buffer.position(0);
		return buffer;

	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub

		gl.glDisable(GL10.GL_DITHER);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glClearDepthf(1f);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		float aspect = (float)width/(float)(height==0?1:height);
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();

		GLU.gluPerspective(gl, 45.0f, aspect, 0.1f, 200.0f);
		GLU.gluLookAt(gl, 5f, 5f, 5f, 0f, 0f, 0f, 0, 1, 0);

	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION,
				new float[] { 5, 5, 5, 1 }, 0);

		gl.glRotatef(anglez, 0, 0, 1);
		gl.glRotatef(angley, 0, 1, 0);
		gl.glRotatef(anglex, 1, 0, 0);
		anglez += 0.1;
		angley += 0.2;
		anglex += 0.3;

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexesBuffer);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLES, triangleBuffer.remaining(),
				GL10.GL_UNSIGNED_BYTE, triangleBuffer);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glFinish();

	}

}
