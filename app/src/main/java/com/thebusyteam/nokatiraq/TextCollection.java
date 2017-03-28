package com.thebusyteam.nokatiraq;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;



public class  TextCollection extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    int img_arr[];
    ImageView img_random;
    static ImageButton bl;
    static ImageButton br;
    static ImageButton bc;
    static AdView adView;
    static SharedPreferences sp;
    static int start3,count,mesgnum,id3,id_arr,prev,rand;
    static ArrayAdapter adapter;
    String[] arr;
    static TextView t1;
    static RelativeLayout relativeLayout;
    static LinearLayout lay_btn;
    static TableRow img_play;
    static ScrollView scrollView;
    static LinearLayout.LayoutParams params;
    Boolean flag_next,flag_prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {


            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

                loadInterstitial();


            }

            @Override
            public void onAdClosed() {
                loadInterstitial();
                // Proceed to the next level.

            }
        });

        count =0;
        Resources res = this.getResources();
        id_arr = R.array.nokat3;
        arr = res.getStringArray(id_arr);
        adapter = new ArrayAdapter(this, R.layout.activity_main,arr);
        mesgnum = adapter.getCount();
        img_random = (ImageView) findViewById(R.id.img_random);
        img_arr = new int[]{R.drawable.im1,R.drawable.im2,R.drawable.im3
                ,R.drawable.im4,R.drawable.im5,R.drawable.im6,R.drawable.im7,R.drawable.im8
                ,R.drawable.im9,R.drawable.im10,R.drawable.im11
        };        t1 = (TextView) findViewById(R.id.textView);
        bc = (ImageButton) findViewById(R.id.imageButton);
        bl = (ImageButton) findViewById(R.id.imageButton2);
        br = (ImageButton) findViewById(R.id.imageButton3);
        scrollView = (ScrollView) findViewById(R.id.scrollView3);
        img_play = (TableRow) findViewById(R.id.table);
        lay_btn = (LinearLayout) findViewById(R.id.lay_btn);
        relativeLayout = (RelativeLayout) findViewById(R.id.lay_relative);
        sp = getSharedPreferences("sp", MODE_PRIVATE);
        start3 = sp.getInt("start3", 0);
        flag_next = flag_prev = false;

        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent in = new Intent();
//                in.setAction(Intent.ACTION_SEND);
//                in.setType("image/jpeg");
//                in.putExtra(Intent.EXTRA_STREAM, Uri.parse(save()));
//                startActivity(Intent.createChooser(in, "Share"));
                Intent intent = new Intent(getApplicationContext(),MyDialogue3.class);
                startActivity(intent);

            }


        });

        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag_next = true;
                count++;
                if(start3 < mesgnum-1){
                    if (flag_prev){ start3 += 2; flag_prev=false;}
                    t1.setText(adapter.getItem(start3).toString());
                    start3++;
                }
                else if(start3 == mesgnum-1)start3 = 0;

                if (!mInterstitialAd.isLoaded()){  try {
                    loadInterstitial();
                } catch (Exception e) {
                    e.printStackTrace();
                }}
                if (count%5 == 0){
                    showInterstitial();
                }
                prev = rand;
                rand= (int) (Math.random() * 11);
                while (rand == prev)rand =(int) (Math.random() * 11);
                img_random.setImageResource(img_arr[rand]);


            }
        });

        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag_prev = true;
                count++;
                if (start3 > 0) {
                    if (flag_next){ start3 -= 2; flag_next=false;}
                    t1.setText(adapter.getItem(start3).toString());
                    start3--;
                }
                else if(start3 == 0)start3 = mesgnum-1;

                if (!mInterstitialAd.isLoaded()){  try {
                    loadInterstitial();
                } catch (Exception e) {
                    e.printStackTrace();
                }}
                if (count%5==0){
                    showInterstitial();
                }

                prev = rand;
                rand= (int) (Math.random() * 11);
                while (rand == prev)rand =(int) (Math.random() * 11);
                img_random.setImageResource(img_arr[rand]);

            }
        });



        // Load an ad into the AdMob banner view.
        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);


    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }

    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.



        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }

    public static Bitmap capture(RelativeLayout r_lay){
        float t = t1.getTranslationY();
        if(t1.getHeight() > scrollView.getHeight()) {
            scrollView.setVerticalScrollBarEnabled(false);
            scrollView.scrollTo(0, View.FOCUS_UP);
            t1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            t1.setTranslationY(t - 110);

        }
        bc.setVisibility(View.INVISIBLE);
        bl.setVisibility(View.INVISIBLE);
        br.setVisibility(View.INVISIBLE);
//        scrollView.refreshDrawableState();
        img_play.setVisibility(View.VISIBLE);
        lay_btn.setBackgroundColor(Color.parseColor("#004d40"));
        adView.setVisibility(View.INVISIBLE);
//        float y = img_random.getY();
//        img_random.setTranslationY(200f);

        Bitmap bitmap = Bitmap.createBitmap(r_lay.getWidth(), r_lay.getHeight(),
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        r_lay.draw(canvas);

//        img_random.setY(y);
        t1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        t1.setTranslationY(t);
        scrollView.setVerticalScrollBarEnabled(true);
        img_play.setVisibility(View.INVISIBLE);
        lay_btn.setBackgroundColor(Color.parseColor("#00695c"));
        bc.setVisibility(View.VISIBLE);
        bl.setVisibility(View.VISIBLE);
        br.setVisibility(View.VISIBLE);

//        scrollView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                        scrollView.setLayoutParams(params);
//            }
//        }, 500);
        adView.setVisibility(View.VISIBLE);

        return bitmap;
    }
    public static String save(ContentResolver cr){
        StringBuilder title = new StringBuilder("nokat3").append(id3++);
        String uri = MediaStore.Images.Media.insertImage(cr,capture(relativeLayout),
                title.toString(), "nokat iraq");
        sp.edit().putInt("id3",id3).apply();
        return uri;
    }

    @Override
    protected void onStop() {
        super.onStop();
        sp.edit().putInt("start3",start3).apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_text, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
