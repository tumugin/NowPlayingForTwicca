package jisakuroom.nowplaying;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class Edit extends Activity{
	public static String Tweet;
	public static int Cursor;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			Intent receivedIntent = getIntent();
			Tweet = receivedIntent.getStringExtra(Intent.EXTRA_TEXT);
			Cursor = receivedIntent.getIntExtra("cursor", 0);
			
			SharedPreferences sharedPreferences = getSharedPreferences("pref",MODE_PRIVATE);
			String template = sharedPreferences.getString("text", "NowPlaying $title - $artist #NowPlaying");
			//一応チェック
			if(sharedPreferences.getString("check", null) == null){
				Toast.makeText(Edit.this, getString(R.string.NoMusicPlaying), Toast.LENGTH_LONG).show();
				finish();
				return;
			}
			//ツイート関連
			String tweetString = Edit.Tweet;
			int tweetCursor = Edit.Cursor;
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
			//テキスト代入
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(tweetString);
			stringBuilder.insert(tweetCursor, template);
			//Shareの場合とtwiccaの場合
			if(receivedIntent.getAction().toString().matches("jisakuroom.nowplaying.share") == true){
				Intent intentShare = new Intent(Intent.ACTION_SEND);
				intentShare.setType("text/plain");
				intentShare.putExtra(Intent.EXTRA_TEXT, new String(stringBuilder));
				startActivity(intentShare);
				finish();
			}else {
				Intent intent_backIntent = new Intent();
				intent_backIntent.putExtra(Intent.EXTRA_TEXT, new String(stringBuilder));
				intent_backIntent.putExtra("cursor", tweetCursor);
				setResult(RESULT_OK, intent_backIntent);
				finish();
			}
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(Edit.this, getString(R.string.MusicGetError), Toast.LENGTH_LONG).show();
			finish();
		}
	}
}
