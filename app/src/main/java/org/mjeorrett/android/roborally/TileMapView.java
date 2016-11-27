package org.mjeorrett.android.roborally;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 27/11/2016.
 */

public class TileMapView extends ViewGroup {

    private static final int INVALID_POINTER_ID = -1;
    private static final int TILE_COUNT = 12; // number of tiles wide / high
    private static final float MAX_ZOOM_SCALE = 5.0f;

    private int mActivePointerId = INVALID_POINTER_ID;
    private ScaleGestureDetector mScaleDetector;
    private GestureDetector mGestureDetector;

    private int mTileSize;
    private float mPosX;
    private float mPosY;
    private float mScaleFactor = 1.f;

    private float mLastTouchX;
    private float mLastTouchY;

    public TileMapView( Context context, AttributeSet attributeSet ) {

        super( context, attributeSet );

        mScaleDetector = new ScaleGestureDetector( context, new ScaleListener() );
        mGestureDetector = new GestureDetector( context, new GestureListener() );

        setBackgroundColor( Color.DKGRAY );

        for ( int iy = 0; iy < TILE_COUNT; iy++ ) {

            for ( int ix = 0; ix < TILE_COUNT; ix++ ) {

                final Tile newTile = new Tile( getContext(), R.drawable.tile_blank );
                addView( newTile );
            }
        }
    }

    @Override
    public boolean onTouchEvent( MotionEvent event ) {

        mScaleDetector.onTouchEvent( event );
        mGestureDetector.onTouchEvent( event );

        final int action = event.getAction();

        switch ( action & MotionEvent.ACTION_MASK ) {

            case MotionEvent.ACTION_DOWN: {

                final float x = event.getX();
                final float y = event.getY();

                mLastTouchX = x;
                mLastTouchY = y;
                mActivePointerId = event.getPointerId( 0 );

                break;
            }

            case MotionEvent.ACTION_MOVE: {

                final int pointerIndex = event.findPointerIndex( mActivePointerId );

                final float x = event.getX( pointerIndex );
                final float y = event.getY( pointerIndex );

                if ( !mScaleDetector.isInProgress() ) {

                    final float dx = x - mLastTouchX;
                    final float dy = y - mLastTouchY;

                    mPosX += dx;
                    mPosY += dy;

                    updateChildTranslation();
                }

                mLastTouchX = x;
                mLastTouchY = y;

                break;
            }

            case MotionEvent.ACTION_UP: {

                mActivePointerId = INVALID_POINTER_ID;

                break;
            }

            case MotionEvent.ACTION_CANCEL: {

                mActivePointerId = INVALID_POINTER_ID;

                break;
            }

            case MotionEvent.ACTION_POINTER_UP: {

                final int pointerIndex = (action & MotionEvent.ACTION_POINTER_INDEX_MASK)
                        >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                final int pointerId = event.getPointerId( pointerIndex );

                if ( pointerId == mActivePointerId ) {

                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastTouchX = event.getX( newPointerIndex );
                    mLastTouchY = event.getY( newPointerIndex );
                    mActivePointerId = event.getPointerId( newPointerIndex );
                }

                break;
            }
        }

        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int widthMode = MeasureSpec.getMode( widthMeasureSpec );

        if ( widthMode == MeasureSpec.EXACTLY ) {

            final int width = MeasureSpec.getSize( widthMeasureSpec );
            setMeasuredDimension( width, width );

        } else {

            final int height = MeasureSpec.getSize( heightMeasureSpec );
            setMeasuredDimension( height, height );
        }
    }

    @Override
    protected void onLayout( boolean changed, int left, int top, int right, int bottom ) {

        final int minDimension = Math.min( getWidth(), getHeight() );
        mTileSize = minDimension / TILE_COUNT;

        final int posX = (int) mPosX;
        final int posY = (int) mPosY;

        for ( int iy = 0; iy < TILE_COUNT; iy++ ) {

            for ( int ix = 0; ix < TILE_COUNT; ix++ ) {

                final View child = getChildAt( ( iy * TILE_COUNT ) + ix );
                int childLeft = ix * mTileSize;
                int childRight = ( ix * mTileSize) + mTileSize;
                int childTop = iy * mTileSize;
                int childBottom = ( iy * mTileSize) + mTileSize;
                child.layout( childLeft + posX, childTop + posY, childRight + posX, childBottom + posY );
            }
        }
    }

    private void updateChildTranslation() {

        final float sizeIncrease = mTileSize * ( mScaleFactor - 1 );

        for ( int iy = 0; iy < TILE_COUNT; iy++ ) {

            for ( int ix = 0; ix < TILE_COUNT; ix++ ) {

                final View child = getChildAt( (iy * TILE_COUNT) + ix );
                child.setTranslationX( mPosX + (ix * sizeIncrease) );
                child.setTranslationY( mPosY + (iy * sizeIncrease) );
                child.setScaleX( mScaleFactor );
                child.setScaleY( mScaleFactor );
            }
        }
    }

    private void resetTranslations() {

        mPosX = 0;
        mPosY = 0;
        mScaleFactor = 1.0f;
        updateChildTranslation();
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale( ScaleGestureDetector detector ) {

            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max( 1.f, Math.min( mScaleFactor, MAX_ZOOM_SCALE ) );
            updateChildTranslation();
            return true;
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDoubleTap(MotionEvent e) {

            resetTranslations();
            return true;
        }
    }
}
