package se.claremont.android.wallpaper;

import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidLiveWallpaperService;

public class ClaremontWallpaperService extends AndroidLiveWallpaperService {

	@Override
	public AndroidApplicationConfiguration createConfig() {
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useGL20 = true;
		return config;	
	}

	@Override
	public ApplicationListener createListener() {
		//TODO insert cool application here.
		return null;
	}

	@Override
	public void offsetChange(ApplicationListener listener, float offsetX, float offsetY,
			float offsetXStep, float offsetYStep, int offsetPixelX, int offsetPixelY) {
		Log.d(this.getClass().getSimpleName(), "Offset changed to:"+offsetPixelX+","+offsetPixelY+".");
	}

}
