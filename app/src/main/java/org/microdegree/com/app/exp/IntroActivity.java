package org.microdegree.com.app.exp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.core.content.ContextCompat;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

import org.microdegree.com.app.exp.util.Constants;


public class IntroActivity extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_intro);

//TODO : Add View model to trigger first time download
        addSlide(AppIntroFragment.newInstance("ನಮಸ್ಕಾರ !","Coding ಈಗ ಸುಲಭ ಹಾಗು ಕನ್ನಡದಲ್ಲಿ",R.mipmap.main, ContextCompat.getColor(this, R.color.darkblue)));
        addSlide(AppIntroFragment.newInstance("MicroDegree ಗುರಿ","ಕನ್ನಡಿಗರಿಗೆ Job-Ready ಮಾಡುವ ಅಭಿಯಾನ",R.mipmap.dev1, ContextCompat.getColor(this, R.color.light_sky_blue)));
        addSlide(AppIntroFragment.newInstance("ಬನ್ನಿ ಕೈಜೋಡಿಸಿ","Join 3000+ ಕನ್ನಡ Learners",R.mipmap.happy_intro2, ContextCompat.getColor(this, R.color.purple_dark_magenta)));
//        addSlide(AppIntroFragment.newInstance("Terms","By Continuing You Agree To Our Terms and Conditions http://bit.ly/dhoorbinTerms",R.mipmap.intro_image_settings2, ContextCompat.getColor(this, R.color.slate_blue)));

        showSkipButton(false);

    }

    @Override
    public void onDonePressed() {


        try {
            SharedPreferences prefs;

            prefs = PreferenceManager.getDefaultSharedPreferences(this);
            prefs.edit().putBoolean(Constants.ACCEPT_TERMS_AND_CONDITIONS, true).apply();


        } catch (Exception e) {
            e.printStackTrace();

        }finally{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }
}
