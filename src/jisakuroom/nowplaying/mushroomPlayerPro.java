package jisakuroom.nowplaying;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class mushroomPlayerPro extends Activity{
	public static String Tweet;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			Intent receivedIntent = getIntent();
			Tweet = receivedIntent.getStringExtra(Intent.EXTRA_TEXT);
			
			SharedPreferences sharedPreferences = getSharedPreferences("PlayerProNP",MODE_PRIVATE);
			SharedPreferences sharedPreferences2 = getSharedPreferences("pref",MODE_PRIVATE);
			String template = sharedPreferences2.getString("text", "NowPlaying $title - $artist #NowPlaying");
			//一応チェック
			if(sharedPreferences.getString("check", null) == null){
				Toast.makeText(this, getString(R.string.NoMusicPlaying), Toast.LENGTH_LONG).show();
				finish();
				return;
			}
			//音楽情報
			String artist = sharedPreferences.getString("artist", "");
			String album = sharedPreferences.getString("album", "");
			String track = sharedPreferences.getString("track", "");
			String trackno = sharedPreferences.getString("trackno", "");
			//置き換え
			template = template.replace("$title", track.toString());
			template = template.replace("$artist", artist.toString());
			template = template.replace("$album", album.toString());
			template = template.replace("$trackno", trackno);
			//終了
			Intent intent_backIntent = new Intent();
			intent_backIntent.putExtra("replace_key", template);
			setResult(RESULT_OK, intent_backIntent);
			finish();
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(this, getString(R.string.MusicGetError), Toast.LENGTH_LONG).show();
			finish();
		}
	}
}
