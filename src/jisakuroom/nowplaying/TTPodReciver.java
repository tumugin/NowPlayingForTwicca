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
			//音楽情報(com.sds.android.ttpod.bundle.mediaitem)
			//Bundle bundle = intent.getBundleExtra("com.sds.android.ttpod.bundle");
			Bundle bundle = intent.getBundleExtra("com.sds.android.ttpod.bundle.mediaitem");
			String artist = bundle.getString("artist");
			String album = bundle.getString("album");
			String track = bundle.getString("title");
			int trackNo = bundle.getInt("track");
			//設定に書き込み
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
			Log.e("なうぷれ for twicca","Error on OnReceive(TTPod)");
		}
	}
}
