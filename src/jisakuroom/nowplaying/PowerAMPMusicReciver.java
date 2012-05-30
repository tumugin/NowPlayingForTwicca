package jisakuroom.nowplaying;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;

import com.maxmpz.audioplayer.player.PowerAMPiAPI;

public class PowerAMPMusicReciver extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			//âπäyèÓïÒ
			Bundle bundle = intent.getBundleExtra(PowerAMPiAPI.TRACK);
			String artist = bundle.getString(PowerAMPiAPI.Track.ARTIST);
			String album = bundle.getString(PowerAMPiAPI.Track.ALBUM);
			String track = bundle.getString(PowerAMPiAPI.Track.TITLE);
			int trackno = bundle.getInt(PowerAMPiAPI.Track.POS_IN_LIST) + 1;
			//ê›íËÇ…èëÇ´çûÇ›
			SharedPreferences sharedPreferences = context.getSharedPreferences("PowerAMPNP",android.content.Context.MODE_PRIVATE);
			Editor editor = sharedPreferences.edit();
			editor.putString("artist", artist);
			editor.putString("album", album);
			editor.putString("track", track);
			editor.putString("check", "OK");
			editor.putString("trackno", String.valueOf(trackno));
			editor.commit();
			Log.d("Nowplaying for twicca",bundle.toString());
			Log.d("Nowplaying for twicca","Recived!! Artist:" + artist + " Album:" + album + " Title:" + track + " TrackNo.:" + String.valueOf(trackno));
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Ç»Ç§Ç’ÇÍ for twicca","Error on OnReceive(PowerAMP)");
		}
	}
}
