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

import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidLiveWallpaperService;

/**
 * Service class for Claremont live wallpaper
 * 
 * @author dansun
 *
 */
public class ClaremontWallpaperService extends AndroidLiveWallpaperService {

	@Override
	public AndroidApplicationConfiguration createConfig() {
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useGL20 = false;
		return config;	
	}

	@Override
	public ApplicationListener createListener() {
		return new ClaremontWallpaper();
	}

	@Override
	public void offsetChange(ApplicationListener listener, float offsetX, float offsetY,
			float offsetXStep, float offsetYStep, int offsetPixelX, int offsetPixelY) {
		Log.d(this.getClass().getSimpleName(), "Offset changed to:"+offsetPixelX+","+offsetPixelY+".");
	}

}
