package com.julienarzul.basemvp.sample.ui.eventDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.julienarzul.basemvp.sample.R;

/**
 * Copyright @ Julien Arzul 2017
 */

public class EventDetailsActivity extends AppCompatActivity {

    private static final String EVENT_ID_EXTRA_KEY = "com.julienarzul.basemvp.sample.ui.eventDetails.EventDetailsActivity.eventId";

    public static Intent getStartingIntent(Context context, int eventId) {
        Intent intent = new Intent(context, EventDetailsActivity.class);

        intent.putExtra(EVENT_ID_EXTRA_KEY, eventId);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_details);

        if (savedInstanceState == null) {
            if (!this.getIntent().hasExtra(EVENT_ID_EXTRA_KEY)) {
                throw new IllegalArgumentException("Impossible to create EventDetailsActivity without an event id");
            }

            this.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.event_details_content_container,
                            EventDetailsFragment.newInstance(this.getIntent().getIntExtra(EVENT_ID_EXTRA_KEY, -1)))
                    .commit();
        }
    }
}
