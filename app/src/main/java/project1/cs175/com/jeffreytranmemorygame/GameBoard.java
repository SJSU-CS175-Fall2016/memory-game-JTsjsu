package project1.cs175.com.jeffreytranmemorygame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

public class GameBoard extends AppCompatActivity {

    private static final String TAG = "TESTINGCODE";
    TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20;
    ArrayList<TextView> al = new ArrayList<>();
    String pokeballpic;
    View temp, one, two;
    Integer score;
    TextView pointsText;
    TextView points;
    boolean startover;
    private Handler handy = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        startover = intent.getBooleanExtra("startover", false);
        intent.removeExtra("startover");

        t1 = (TextView) findViewById(R.id.t1);        t2 = (TextView) findViewById(R.id.t2);        t3 = (TextView) findViewById(R.id.t3);        t4 = (TextView) findViewById(R.id.t4);
        t5 = (TextView) findViewById(R.id.t5);        t6 = (TextView) findViewById(R.id.t6);        t7 = (TextView) findViewById(R.id.t7);        t8 = (TextView) findViewById(R.id.t8);
        t9 = (TextView) findViewById(R.id.t9);        t10 = (TextView) findViewById(R.id.t10);        t11 = (TextView) findViewById(R.id.t11);        t12 = (TextView) findViewById(R.id.t12);
        t13 = (TextView) findViewById(R.id.t13);        t14 = (TextView) findViewById(R.id.t14);        t15 = (TextView) findViewById(R.id.t15);        t16 = (TextView) findViewById(R.id.t16);
        t17 = (TextView) findViewById(R.id.t17);        t18 = (TextView) findViewById(R.id.t18);        t19 = (TextView) findViewById(R.id.t19);        t20 = (TextView) findViewById(R.id.t20);
        al.add(t1);        al.add(t2);        al.add(t3);        al.add(t4);        al.add(t5);        al.add(t6);        al.add(t7);        al.add(t8);
        al.add(t9);        al.add(t10);        al.add(t11);        al.add(t12);        al.add(t13);        al.add(t14);        al.add(t15);        al.add(t16);
        al.add(t17);        al.add(t18);        al.add(t19);        al.add(t20);
        pokeballpic = t1.getBackground().getConstantState().toString();
        one = new TextView(this);        two = new TextView(this);        temp = new TextView(this);
        temp.setBackgroundResource(R.drawable.pokeball);        one.setBackgroundResource(R.drawable.pokeball);        two.setBackgroundResource(R.drawable.pokeball);
        pointsText = (TextView) findViewById(R.id.pointsText);        points = (TextView) findViewById(R.id.currentScore);

        if (savedInstanceState != null) { //restore tiles and score
            points.setText(String.valueOf(savedInstanceState.getInt("score")));
            ArrayList<String> tags = savedInstanceState.getStringArrayList("tags");

            int count = 0;
            for (TextView n : al) {
                n.setTag(tags.get(count++));
                if (Integer.valueOf(n.getTag().toString()) > 10) {
                    n.setBackgroundResource(R.drawable.blank);
                }
            }
        }

        if (savedInstanceState == null) {
            score = 0;
            shuffleTiles();
        }

        if(startover) resetEverything();
        LoadPreferences();
    }

    private void shuffleTiles() {
        //shuffle tiles
        ArrayList<Integer> asdf = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            asdf.add(i);
            asdf.add(i);
        }
        Collections.shuffle(asdf);
        int count = 0;
        for (TextView n : al) {
            n.setTag(asdf.get(count++));
//                Log.v(TAG, "SHUFFLED TAGS: " + n.getTag().toString());
        }
    }

    public void flip(View v) {
        int tag = Integer.valueOf(v.getTag().toString());

        if (tag > 10) {
            Log.v(TAG, "Tags greater than 10");
        } else {
            actuallyFlip(v);

            if (one.getBackground().getConstantState().toString().equals(pokeballpic)) {
                one = v;
            } else {
                two = v;
            }
            //if both pics are pokemons check them
            if (!one.getBackground().getConstantState().toString().equals(pokeballpic) && !two.getBackground().getConstantState().toString().equals(pokeballpic)) {
                //if both pics match
                if (one.getBackground().getConstantState().toString().equals(two.getBackground().getConstantState().toString()) && !one.equals(two)) {
                    one.setTag(Integer.valueOf(one.getTag().toString()) + 20);
                    two.setTag(Integer.valueOf(two.getTag().toString()) + 20);
                    score += 1;
                    points.setText(String.valueOf(score));
                    if (score == 10) {
                        pointsText.setText("Game Over");
                        points.setText(": You Win!");
                    }
                    for (TextView n : al) {
                        n.setEnabled(false);
                    }
                    handy.postDelayed(new Runnable() {
                        @Override
                            public void run() {
                            one.setBackgroundResource(R.drawable.blank);
                            two.setBackgroundResource(R.drawable.blank);
                            one = temp;
                            two = temp;
                            for (TextView n : al) {
                                n.setEnabled(true);
                            }
                        }
                    }, 800);

                } else {
                    for (TextView n : al) n.setEnabled(false);
                    handy.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                            one.setBackgroundResource(R.drawable.pokeball);
                            two.setBackgroundResource(R.drawable.pokeball);
                            one = temp;
                            two = temp;
                            for (TextView n : al) {
                                n.setEnabled(true);
                            }
                        }
                    }, 800);
                }
            }
        }
    }

    private void actuallyFlip(View v) {
        String t = v.getTag().toString();
        switch (t) {
            case "1":
                v.setBackgroundResource(R.drawable.blastoise);
                break;
            case "2":
                v.setBackgroundResource(R.drawable.bulbasaur);
                break;
            case "3":
                v.setBackgroundResource(R.drawable.charmander);
                break;
            case "4":
                v.setBackgroundResource(R.drawable.charizard);
                break;
            case "5":
                v.setBackgroundResource(R.drawable.ivysaur);
                break;
            case "6":
                v.setBackgroundResource(R.drawable.charmeleon);
                break;
            case "7":
                v.setBackgroundResource(R.drawable.pikachu);
                break;
            case "8":
                v.setBackgroundResource(R.drawable.squirtle);
                break;
            case "9":
                v.setBackgroundResource(R.drawable.wartortle);
                break;
            case "10":
                v.setBackgroundResource(R.drawable.venusaur);
                break;
            default:
//                v.setBackgroundResource(R.drawable.pokeball);
                break;
        } //end switch
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        SavePreferences(); //sync settings with backbutton pres
        ArrayList<String> temp = new ArrayList<>();
        for (TextView n : al) {
            temp.add(n.getTag().toString());
        }
        savedInstanceState.putBoolean("startover", false);
        savedInstanceState.putStringArrayList("tags", temp);
        savedInstanceState.putInt("score", score);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onBackPressed() {
        SavePreferences();
        super.onBackPressed();
    }

    private void SavePreferences() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("score", score);

        int temp = 1;
        for(TextView n : al) {
            String t = "t" + String.valueOf(temp++);
            editor.putString(t, n.getTag().toString());
        }
        editor.apply();
    }

    private void LoadPreferences() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        score = sharedPreferences.getInt("score", 0);

        points.setText(String.valueOf(score));

        for(int i =1; i <=20; i++) {
            String t = "t" + String.valueOf(i);
            String restore = sharedPreferences.getString(t, al.get(i-1).getTag().toString());
            al.get(i-1).setTag(restore);
            if (Integer.valueOf(al.get(i-1).getTag().toString()) > 10) {
                al.get(i-1).setBackgroundResource(R.drawable.blank);
            }
        }
    }

    public void resetBoard(View view) {
        resetEverything();
    }

    public void resetEverything() {
        score = 0;
        pointsText.setText("Points:");
        points.setText(Integer.toString(score));
        for(TextView n : al) {
            shuffleTiles();
            n.setBackgroundResource(R.drawable.pokeball);
        }
        SavePreferences();
    }
}
