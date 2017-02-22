package com.julienarzul.basemvp.sample.ui.main;

import android.os.Bundle;
import android.view.View;

import com.julienarzul.basemvp.MvpActivity;
import com.julienarzul.basemvp.sample.R;

public class MainActivity extends MvpActivity<MainContract.Presenter> implements MainContract.View {

    @Override
    protected MainContract.Presenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.main_activity_based_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onActivityBasedButtonClicked();
            }
        });
        findViewById(R.id.main_fragment_based_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onFragmentBasedButtonClicked();
            }
        });
    }

    @Override
    public void launchActivityBasedScreen() {
        // TODO
    }

    @Override
    public void launchFragmentBasedScreen() {
        // TODO
    }
}
