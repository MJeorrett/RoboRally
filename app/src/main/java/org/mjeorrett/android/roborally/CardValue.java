package org.mjeorrett.android.roborally;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by user on 27/11/2016.
 */

public enum CardValue {

    ROTATE_LEFT( "Rotate", "Left" ),
    ROTATE_RIGHT( "Rotate", "Right" ),
    U_TURN( "Rotate", "U-turn"),
    STRAIGHT_ON_1( "Move", "One"),
    STRAIGHT_ON_2( "Move", "Two"),
    STRAIGHT_ON_3( "Move", "Three"),
    BACK_UP( "Move", "Back");


    private static final List<CardValue> VALUES = Collections.unmodifiableList(
            Arrays.asList( values() )
    );
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    private String mDirection;
    private String mQuantity;

    CardValue(String direction, String amount ) {

        mDirection = direction;
        mQuantity = amount;
    }

    public String getDirection() {

        return mDirection;
    }

    public String getQuantity() {

        return mQuantity;
    }

    public static CardValue randomCardValue()  {

        return VALUES.get( RANDOM.nextInt( SIZE ) );
    }
}
