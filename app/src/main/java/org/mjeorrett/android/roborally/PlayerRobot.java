package org.mjeorrett.android.roborally;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

/**
 * Created by user on 27/11/2016.
 */

public class PlayerRobot extends ImageView {

    private int mRow;
    private int mColumn;

    public PlayerRobot( Context context ) {

        super( context );
        setImageResource( R.drawable.player_robot );
        setBackgroundColor( Color.argb( 150, 0, 0, 255 ) );
        mRow = 5;
        mColumn = 5;
    }

    public int getRow() {

        return mRow;
    }

    public void setRow( int row ) {

        mRow = row;
    }

    public int getColumn() {

        return mColumn;
    }

    public void setColumn( int column ) {

        mColumn = column;
    }
}