package com.cmsv6demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.babelstar.gviewer.NetClient;
import com.babelstar.gviewer.VideoView;

public class MainActivity extends Activity {
	private UpdateViewThread mUpdateViewThread = null;	//视频界面更新线程
	private VideoView mVideoImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mVideoImage = (VideoView) findViewById(R.id.imageView1);
		
		NetClient.Initialize();
		NetClient.SetDirSvr("52.8.85.226", "52.8.85.226", 6605, 0);
		mVideoImage.setViewInfo("91510970", "91510970", 0, "CH1");
		mVideoImage.StartAV();
		mUpdateViewThread = new UpdateViewThread();
		mUpdateViewThread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mUpdateViewThread.setExit(true);
		mUpdateViewThread = null;
		mVideoImage.StopAV();
		NetClient.UnInitialize();
		super.onDestroy();
	}
    
    /*
	 * 播放状态检测线程
	 */
	private class UpdateViewThread extends Thread{
		private boolean isExit = false;
		private boolean isPause = false;
		
		public void setExit(boolean isExit) {
			this.isExit = isExit;
		}
		
		public void setPause(boolean isPause) {
			this.isPause = isPause;
		}
		
		public void run()   {  
			while (!isExit) {
				try {
					if (!isPause) {
						mVideoImage.updateView();
						Thread.sleep(20);
					} else {
						Thread.sleep(100);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
			this.isExit = true;
	    }
	}
}
