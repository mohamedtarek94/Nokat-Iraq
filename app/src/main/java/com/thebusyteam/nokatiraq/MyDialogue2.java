package com.thebusyteam.nokatiraq;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MyDialogue2 extends Activity {

    LinearLayout lay_img,lay_txt;
    ProgressBar pb;

    @Override
    protected void onResume() {
        super.onResume();
        pb.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dialogue);
        lay_img = (LinearLayout) findViewById(R.id.lay_img);
        lay_txt = (LinearLayout) findViewById(R.id.lay_txt);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(ProgressBar.INVISIBLE);
        lay_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(ProgressBar.VISIBLE);
                Intent in = new Intent();
                in.setAction(Intent.ACTION_SEND);
                in.setType("image/jpeg");
                in.putExtra(Intent.EXTRA_STREAM, Uri.parse(Text2.save(getContentResolver())));
                startActivity(Intent.createChooser(in, "Share"));
                finish();
            }
        });

        lay_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setAction(Intent.ACTION_SEND);
                in.setType("text/plain");
                in.putExtra(Intent.EXTRA_TEXT, Text2.adapter.getItem(Text2.start2-1).toString());
                startActivity(Intent.createChooser(in, "Share"));
                finish();
            }
        });


    }

}
