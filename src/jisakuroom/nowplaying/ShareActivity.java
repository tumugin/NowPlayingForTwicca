package jisakuroom.nowplaying;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ShareActivity extends Activity{
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
			Intent intent = new Intent();
			intent.setAction("jisakuroom.nowplaying.share");
			intent.putExtra(Intent.EXTRA_TEXT, "");
			startActivity(intent);
		} catch (Exception e) {
			Toast.makeText(this, getString(R.string.CantOpenShare), Toast.LENGTH_LONG).show();
		}
		finish();
    }
}
