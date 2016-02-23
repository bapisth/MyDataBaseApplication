package com.esspl.hemendra;

import android.content.Intent;
import android.os.Bundle;

import com.esspl.hemendra.mydatabaseapplication.MainActivity;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by BAPI1 on 09-01-2016.
 */
public class MyAppIntro extends AppIntro {
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(AppIntroFragment.newInstance("Test", "AppIntro Test description", android.R.drawable.sym_call_incoming, com.github.paolorotolo.appintro.R.color.abc_color_highlight_material));
        addSlide(AppIntroFragment.newInstance("Test", "AppIntro Test description", android.R.drawable.sym_call_incoming, com.github.paolorotolo.appintro.R.color.accent_material_dark));
        addSlide(AppIntroFragment.newInstance("Test", "AppIntro Test description", android.R.drawable.sym_call_incoming, com.github.paolorotolo.appintro.R.color.foreground_material_light));
        addSlide(AppIntroFragment.newInstance("Test", "AppIntro Test description", android.R.drawable.sym_call_incoming, com.github.paolorotolo.appintro.R.color.abc_color_highlight_material));
        showSkipButton(true);
        setProgressButtonEnabled(true);
    }

    @Override
    public void onSkipPressed() {

    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {
        Intent intent = new Intent(MyAppIntro.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSlideChanged() {

    }
}
