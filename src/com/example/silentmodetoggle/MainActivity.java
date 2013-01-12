package com.example.silentmodetoggle;

import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.MediaStore.Audio;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	private AudioManager mAudioManager;
	private boolean mPhoneIsSilent;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);

        checkIfPhoneIsSilent();
        
        setButtonClickListener();
    }

    private void setButtonClickListener() {
        Button toggleButton = (Button)findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mPhoneIsSilent) {
					mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
					mPhoneIsSilent = false;
				} else {
					mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
					mPhoneIsSilent = true;
				}
				
				toggleUi();
			}
		});
    	
    }
    
    private void checkIfPhoneIsSilent() {
    	int ringerMode = mAudioManager.getRingerMode();
    	if(ringerMode == AudioManager.RINGER_MODE_SILENT) {
    		mPhoneIsSilent = true;
    	} else {
    		mPhoneIsSilent = false;
    	}
    }
    
    private void toggleUi() {
    	ImageView imageView = (ImageView)findViewById(R.id.phone_icon);
    	Drawable newPhoneImage;
    	
    	if(mPhoneIsSilent) {
    		newPhoneImage = getResources().getDrawable(R.drawable.phone_silent);
    	} else {
    		newPhoneImage = getResources().getDrawable(R.drawable.phone_on);
    	}
    	
    	imageView.setImageDrawable(newPhoneImage);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	checkIfPhoneIsSilent();
    	toggleUi();
    }
    
}
