package com.example.java2.animations;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "bla";
    TextView text;
    Integer check = 360;
    private Button withA;
    private Button woA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        animateStartEnd();
        linkUi();
        setLis();

    }

    @SuppressLint("NewApi")
    private void setLis() {
        withA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
//                 ActivityOptions options=ActivityOptions.makeScaleUpAnimation(v,0,0,v.getWidth(),v.getHeight());
//                startActivity(intent,options.toBundle());
//                move();
//                AnimatorSet anim = new AnimatorSet();
//                anim.play(rotate()).with(blur());
//                anim.start();
                zoom();
            }
        });
    }

    public void blink() {

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animaie);
        woA.startAnimation(animation);
    }
    public void zoom() {

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        woA.startAnimation(animation);
    }

    private ObjectAnimator rotate() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(woA, "rotation", check);
        objectAnimator.setDuration(3000);
        if (check == 360) {
            check = 0;
        } else {
            check = 360;
        }

//        objectAnimator.start();
        return objectAnimator;

    }

    private void move() {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(woA, "alpha", 0f);
        fadeOut.setDuration(2000);
        ObjectAnimator mover = ObjectAnimator.ofFloat(woA, "translationX", -500f, 0f);
        mover.setDuration(2000);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(woA, "alpha", 0f, 1f);
        fadeIn.setDuration(2000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(mover).with(fadeIn).after(fadeOut);
        animatorSet.start();
    }

    private void linkUi() {
        withA = (Button) findViewById(R.id.wAnim);
        woA = (Button) findViewById(R.id.woAnim);
    }

    private ObjectAnimator blur() {
        int dest = 1;
        if (woA.getAlpha() > 0) {
            dest = 0;
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(woA, "alpha", dest);
        animator.setDuration(3000);
//        animator.start();
        return animator;
    }

    @SuppressLint("NewApi")
    private void animate() {
        text.animate().translationX(400).withLayer();
    }

    @SuppressLint("NewApi")
    private void animateStartEnd() {
        text.animate().translationX(400).withStartAction(new Runnable() {
            @Override
            public void run() {
                text.setTranslationX(400 - text.getWidth());
            }
        });
        text.animate().alpha(0).withStartAction(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "end");
            }
        });

    }
}
