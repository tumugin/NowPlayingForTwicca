package jisakuroom.nowplaying;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;

public class MusicReceiver extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			//âπäyèÓïÒ
			Bundle bundle = intent.getExtras();
			String artist;
			String album;
			String track;
			int trackno;
			
			if(intent.getAction().equals("com.sonyericsson.music.metachanged")){
				artist = bundle.getCharSequence("ARTIST_NAME").toString();
				album = bundle.getCharSequence("ALBUM_NAME").toString();
				track = bundle.getCharSequence("TRACK_NAME").toString();
				trackno = bundle.getInt("TRACK_NUM");
			}else {
				artist = bundle.getString("artist");
				album = bundle.getString("album");
				track = bundle.getString("track");
				trackno = bundle.getInt("tracknumber"); 
			}
			
			//ê›íËÇ…èëÇ´çûÇ›
			SharedPreferences sharedPreferences = context.getSharedPreferences("pref",android.content.Context.MODE_PRIVATE);
			Editor editor = sharedPreferences.edit();
			editor.putString("artist", artist);
			editor.putString("album", album);
			editor.putString("track", track);
			editor.putString("check", "OK");
			editor.putString("trackno", String.valueOf(trackno));
			Log.d("Nowplaying for twicca",bundle.toString());
			Log.d("Nowplaying for twicca","Recived!! Artist:" + artist + " Album:" + album + " Title:" + track + " TrackNo.:" + String.valueOf(trackno));
			editor.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
