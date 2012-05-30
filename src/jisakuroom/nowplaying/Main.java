package jisakuroom.nowplaying;

import android.content.pm.PackageManager;
import android.app.Activity;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class Main extends Activity {
	Button Button1;
	Button Button2;
	EditText EditText1;
	TextView TextView5;
	//チェックボックス
	CheckBox checkBox1;
	CheckBox checkBox2;
	CheckBox checkBox3;
	CheckBox checkBox4;
	CheckBox checkBox5;
	PackageManager packageManager;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //パッケージマネージャ初期化
        packageManager = getPackageManager();
        //ビューの初期化
        TextView5 = (TextView) findViewById(R.id.textView5);
        Button1 = (Button) findViewById(R.id.button1);
        Button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Button1OnClick();
			}
		});
        Button2 = (Button) findViewById(R.id.button2);
        Button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Button2OnClick();
			}
		});
        EditText1 = (EditText)findViewById(R.id.editText1);
        EditText1.addTextChangedListener(new UITextWatcher());
        //チェックボックス関連
        checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox)findViewById(R.id.checkBox4);
        checkBox5 = (CheckBox)findViewById(R.id.checkBox5);
        //設定を取得
        SharedPreferences sharedPreferences = getSharedPreferences("pref",MODE_PRIVATE);
        String setting = sharedPreferences.getString("text", null);
        if(setting != null){
        	EditText1.setText(setting);
        }else {
			EditText1.setText("NowPlaying $title - $artist #NowPlaying");
		}
        //Disable menu用の設定を取得
        if (packageManager.getComponentEnabledSetting(new ComponentName(this, Edit.class)) == android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_DISABLED){
        	checkBox1.setChecked(true);
        }
        if (packageManager.getComponentEnabledSetting(new ComponentName(this, EditPowerAMP.class)) == android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_DISABLED){
        	checkBox2.setChecked(true);
        }
        if (packageManager.getComponentEnabledSetting(new ComponentName(this, EditTTPod.class)) == android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_DISABLED){
        	checkBox3.setChecked(true);
        }
        if (packageManager.getComponentEnabledSetting(new ComponentName(this, EditPlayerPro.class)) == android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_DISABLED){
        	checkBox4.setChecked(true);
        }
        //ランチャーの表示を無効化用の設定を取得
        if (packageManager.getComponentEnabledSetting(new ComponentName(this, ShareActivity.class)) == android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_DISABLED){
        	checkBox5.setChecked(true);
        }
    }
    /* EditText用のOverride */
    public class UITextWatcher implements TextWatcher {
      public void afterTextChanged(Editable arg) {
    	  
      }
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    	  
      }
      public void onTextChanged(CharSequence s, int start, int before, int count) {
    	  String text = s.toString();
    	  text = text.replace("$title", "Title");
    	  text = text.replace("$artist", "Artist");
    	  text = text.replace("$album", "Album");
    	  text = text.replace("$trackno", "Track number");
    	  TextView5.setText(text);
      }
    }
    
    public void Button1OnClick(){
    	SharedPreferences sharedPreferences = getSharedPreferences("pref",MODE_PRIVATE);
    	Editor editor = sharedPreferences.edit();
    	editor.putString("text", EditText1.getText().toString());
    	editor.commit();
    	//Disable menu用
    	if(checkBox1.isChecked() == true){
    		packageManager.setComponentEnabledSetting(new ComponentName(this, Edit.class), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    		packageManager.setComponentEnabledSetting(new ComponentName(this, mushroom.class), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    	}else {
    		packageManager.setComponentEnabledSetting(new ComponentName(this, Edit.class), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    		packageManager.setComponentEnabledSetting(new ComponentName(this, mushroom.class), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
		}
    	if(checkBox2.isChecked() == true){
    		packageManager.setComponentEnabledSetting(new ComponentName(this, EditPowerAMP.class), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    		packageManager.setComponentEnabledSetting(new ComponentName(this, mushroomPowerAMP.class), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    	}else {
    		packageManager.setComponentEnabledSetting(new ComponentName(this, EditPowerAMP.class), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    		packageManager.setComponentEnabledSetting(new ComponentName(this, mushroomPowerAMP.class), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
		}
    	if(checkBox3.isChecked() == true){
    		packageManager.setComponentEnabledSetting(new ComponentName(this, EditTTPod.class), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    		packageManager.setComponentEnabledSetting(new ComponentName(this, mushroomTTPod.class), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    	}else {
    		packageManager.setComponentEnabledSetting(new ComponentName(this, EditTTPod.class), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    		packageManager.setComponentEnabledSetting(new ComponentName(this, mushroomTTPod.class), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
		}
    	if(checkBox4.isChecked() == true){
    		packageManager.setComponentEnabledSetting(new ComponentName(this, EditPlayerPro.class), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    		packageManager.setComponentEnabledSetting(new ComponentName(this, mushroomPlayerPro.class), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    	}else {
    		packageManager.setComponentEnabledSetting(new ComponentName(this, EditPlayerPro.class), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    		packageManager.setComponentEnabledSetting(new ComponentName(this, mushroomPlayerPro.class), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
		}
    	//ランチャーの表示を無効化用
    	if(checkBox5.isChecked() == true){
    		packageManager.setComponentEnabledSetting(new ComponentName(this, ShareActivity.class), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    	}else {
    		packageManager.setComponentEnabledSetting(new ComponentName(this, ShareActivity.class), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
		}
    	//終了
    	finish();
    }
    public void Button2OnClick(){
    	finish();
    }
}