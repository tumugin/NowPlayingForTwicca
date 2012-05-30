package jisakuroom.nowplaying;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;

public class TTPodReciver extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			//âπäyèÓïÒ(com.sds.android.ttpod.bundle.mediaitem)
			//Bundle bundle = intent.getBundleExtra("com.sds.android.ttpod.bundle");
			Bundle bundle = intent.getBundleExtra("com.sds.android.ttpod.bundle.mediaitem");
			String artist = bundle.getString("artist");
			String album = bundle.getString("album");
			String track = bundle.getString("title");
			int trackNo = bundle.getInt("track");
			//ê›íËÇ…èëÇ´çûÇ›
			SharedPreferences sharedPreferences = context.getSharedPreferences("TTPodNP",android.content.Context.MODE_PRIVATE);
			Editor editor = sharedPreferences.edit();
			editor.putString("artist", artist);
			editor.putString("album", album);
			editor.putString("track", track);
			editor.putString("check", "OK");
			editor.putString("trackno", String.valueOf(trackNo));
			editor.apply();
			Log.d("Nowplaying for twicca",bundle.toString());
			Log.d("Nowplaying for twicca","Recived!! Artist:" + artist + " Album:" + album + " Title:" + track + " TrackNo.:" + String.valueOf(trackNo));
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Ç»Ç§Ç’ÇÍ for twicca","Error on OnReceive(TTPod)");
		}
	}
}
