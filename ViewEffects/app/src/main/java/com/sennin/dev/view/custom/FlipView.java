package com.sennin.dev.view.custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FlipView extends androidx.appcompat.widget.AppCompatImageView {


    private GestureDetector gestureDetector;

    private int currentImage = 0;
    private Bitmap frontImage;
    private Bitmap backImage;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({Direction.NONE, Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN})
    public @interface Direction {
        int NONE = 0;
        int LEFT = 1;
        int RIGHT = 2;
        int UP = 3;
        int DOWN = 4;
    }

    public FlipView(Context context) {
        super(context);
        init(context);
    }

    public FlipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
    }


    public void setViews(Bitmap frontImage, Bitmap backImage) {
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.setImageBitmap(frontImage);
    }


    private void animateFlip(@Direction int direction) {
        Bitmap fromBitmap = currentImage == 0 ? frontImage : backImage;
        int directionCounter = (direction == Direction.LEFT) ? 1 : -1;
        currentImage = currentImage + directionCounter;
        if (currentImage < 0) {
            currentImage = 1;
        } else if (currentImage >= 2) {
            currentImage = 0;
        }
        Bitmap toBitmap = currentImage == 0 ? frontImage : backImage;

        switch (direction) {
            case Direction.LEFT:
                ObjectAnimator flipRight = ObjectAnimator.ofFloat(this, "rotationY", 180f, 90f);
                ObjectAnimator flipRight2 = ObjectAnimator.ofFloat(this, "rotationY", 90f, 0f);
                flipRight.setInterpolator(new DecelerateInterpolator());
                flipRight2.setInterpolator(new AccelerateDecelerateInterpolator());
                flipRight.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        FlipView.this.setImageBitmap(toBitmap);
                        flipRight2.start();
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        FlipView.this.setImageBitmap(fromBitmap);
                    }
                });
                flipRight.start();

                break;
            case Direction.RIGHT:
                ObjectAnimator flipLeft = ObjectAnimator.ofFloat(this, "rotationY", 0f, 90f);
                ObjectAnimator flipLeft2 = ObjectAnimator.ofFloat(this, "rotationY", 90f, 180f);
                flipLeft.setInterpolator(new DecelerateInterpolator());
                flipLeft2.setInterpolator(new AccelerateDecelerateInterpolator());
                flipLeft.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        FlipView.this.setImageBitmap(toBitmap);
                        flipLeft2.start();
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        FlipView.this.setImageBitmap(fromBitmap);
                    }

                });
                flipLeft.start();
                break;
            default:
                break;
        }


    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 200;
        private static final int SWIPE_VELOCITY_THRESHOLD = 0;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }


    public void onSwipeRight() {
        animateFlip(Direction.RIGHT);
    }

    public void onSwipeLeft() {
        animateFlip(Direction.LEFT);
    }


}
