/*******************************************************************************
 * Copyright 2012 Daniel Sundberg
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package se.claremont.android.wallpaper;

import java.nio.FloatBuffer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.loaders.obj.ObjLoader;
import com.badlogic.gdx.math.Vector3;

/**
 * Wallpaper applicationlistener for rendering Claremont live wallpaper
 * 
 * @author dansun
 *
 */
public class ClaremontWallpaper implements ApplicationListener {

	//
	// Ref to meshes
	//
	private Mesh claremontText;
	private Mesh claremontOrb;
	
	//
	// 3D Perspective camera
	//
	private PerspectiveCamera perspectiveCamera;
	private Vector3 perspectiveCameraCurrentPosition = new Vector3(0f, 10f, 10f);
	private Vector3 perspectiveCameraCurrentDirection = new Vector3(0f, -10f, -10f);
	
	//
	// Lighting
	//
	private float lightPositions[] = {1.0f, 2.0f, 3,0f, 1.0f};
	private FloatBuffer lightPosition = FloatBuffer.wrap(lightPositions);
	
	@Override
	public void create() {
		
		//
		// Init camera with "realistic" perspective and clippingplanes
		//
		perspectiveCamera =  new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		perspectiveCamera.far = 100;
		perspectiveCamera.near = -0.1f;
		perspectiveCamera.update();
		
		//
		// Load models
		//
		claremontText = ObjLoader.loadObj(Gdx.files.internal("claremont_text.obj").read());
		claremontOrb = ObjLoader.loadObj(Gdx.files.internal("claremont_orb.obj").read());
	
	}

	@Override
	public void dispose() {
	
		//
		// Dispose of assets
		//
		claremontText.dispose();
		claremontOrb.dispose();

	}

	@Override
	public void pause() {}

	@Override
	public void render() {
				
		//
		// Set GL state
		//
		GL10 gl = Gdx.graphics.getGL10();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		gl.glDisable(GL10.GL_DITHER);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glEnable(GL10.GL_COLOR_MATERIAL);
		gl.glHint(GL10.GL_POLYGON_SMOOTH_HINT, GL10.GL_NICEST);
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);
		
		//
		// Update light position
		//
		lightPosition.put(0, perspectiveCamera.position.x);
		lightPosition.put(1, perspectiveCamera.position.y);
		lightPosition.put(2, perspectiveCamera.position.z);
		gl.glLightfv(GL10.GL_LIGHT0, 
				GL10.GL_POSITION, 
				lightPosition);
		
		//
		// Update camera position/direction
		//
		perspectiveCamera.position.set(perspectiveCameraCurrentPosition);
		perspectiveCamera.direction.set(perspectiveCameraCurrentDirection);
		perspectiveCamera.position.rotate(Gdx.graphics.getDeltaTime(), 0f, 1f, 0f);
		perspectiveCamera.direction.rotate(Gdx.graphics.getDeltaTime(), 0f, 1f, 0f);
		perspectiveCameraCurrentPosition = perspectiveCamera.position;
		perspectiveCameraCurrentDirection = perspectiveCamera.direction;
		perspectiveCamera.update();
		perspectiveCamera.apply(gl);
		
		//
		// Render Claremont text model
		//
		gl.glPushMatrix();
		gl.glColor4f(1f, 1f, 1f, 1f);
		gl.glRotatef(0.25f, 1f, 0f, 0f);
		claremontText.render(GL10.GL_TRIANGLES);
		gl.glPopMatrix();
		
		//
		// Render Claremont orb model
		//
		gl.glPushMatrix();
		gl.glColor4f(0.36862745f, 0.63921569f, 0.82352941f, 1f);
		gl.glRotatef(0.25f, 1f, 0f, 0f);
		claremontOrb.render(GL10.GL_TRIANGLES);
		gl.glPopMatrix();
		
	}

	@Override
	public void resize(int width, int height) {
		
		//
		// Re-init camera with new width/height
		//
		perspectiveCamera = new PerspectiveCamera(67, width, height);	
		
	}

	@Override
	public void resume() {}
	
}
