package jisakuroom.nowplaying;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;

public class PlayerProReciver extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			//���y���
			Bundle bundle = intent.getExtras();
			String artist = bundle.getString("artist");
			String album = bundle.getString("album");
			String track = bundle.getString("track");
			int trackno = bundle.getInt("id"); 
			//�ݒ�ɏ�������
			SharedPreferences sharedPreferences = context.getSharedPreferences("PlayerProNP",android.content.Context.MODE_PRIVATE);
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
			Log.e("�Ȃ��Ղ� for twicca","Error on OnReceive(PlayerPro)");
		}
	}
}
