package com.example.hp.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

/*** Created by GBY9C3HL on 12/08/2015.*/
public class Tabs extends Activity implements View.OnClickListener{

    TabHost tabHost;
    TextView showResults;
    long start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);

        tabHost = (TabHost) findViewById(R.id.tabHost);

        Button newTab = (Button) findViewById(R.id.bAddTab);
        Button bStart = (Button) findViewById(R.id.bStartWatch);
        Button bStop = (Button) findViewById(R.id.bStopWatch);
        showResults = (TextView) findViewById(R.id.tvShowResults);

        newTab.setOnClickListener(this);
        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);

        tabHost.setup();

        TabSpec tabSpecs = tabHost.newTabSpec("tag1");
        tabSpecs.setContent(R.id.tab1);
        tabSpecs.setIndicator("Stop Watch");
        tabHost.addTab(tabSpecs);

        tabSpecs = tabHost.newTabSpec("tag2");
        tabSpecs.setContent(R.id.tab2);
        tabSpecs.setIndicator("Tab 2");
        tabHost.addTab(tabSpecs);

        tabSpecs = tabHost.newTabSpec("tag3");
        tabSpecs.setContent(R.id.tab3);
        tabSpecs.setIndicator("Add a Tab");
        tabHost.addTab(tabSpecs);

        start = 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.bAddTab:

                TabSpec theSpec = tabHost.newTabSpec("tag1");
                theSpec.setContent(new TabHost.TabContentFactory() {

                    @Override
                    public View createTabContent(String tag) {
                        TextView text = new TextView(Tabs.this);
                        text.setText("You have created a new Tab!");
                        return (text);
                    }
                });
                theSpec.setIndicator("New");
                tabHost.addTab(theSpec);

                break;

            case R.id.bStartWatch:

                start = System.currentTimeMillis();

                break;

            case R.id.bStopWatch:

                stop = System.currentTimeMillis();

                if (start != 0) {
                    long result = stop - start;

                    int milliseconds = (int) result;
                    int seconds = (int) result / 1000;
                    int minutes = (int) seconds / 60;

                    milliseconds = milliseconds % 100;
                    seconds = seconds % 60;

                    showResults.setText(String.format("%d:%02d:%02d", minutes, seconds, milliseconds));
                }

                break;
        }
    }
}
