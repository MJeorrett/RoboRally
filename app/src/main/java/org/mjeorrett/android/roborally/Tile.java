package org.mjeorrett.android.roborally;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

/**
 * Created by user on 27/11/2016.
 */

public class Tile extends ImageView {

    public Tile( Context context, int drawableId ) {

        super( context );
        setImageResource( drawableId );
        setBackgroundColor( Color.BLUE );
    }
}
