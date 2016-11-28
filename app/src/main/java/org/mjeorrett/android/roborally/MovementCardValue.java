package org.mjeorrett.android.roborally;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.mjeorrett.android.roborally.MovementCardRotationValue.*;

/**
 * Created by user on 27/11/2016.
 */

public enum MovementCardValue {

    ROTATE_LEFT( ANTI_CLOCKWISE_90, 0 ),
    ROTATE_RIGHT( CLOCKWISE_90, 0 ),
    TURN_AROUND( U_TURN, 0 ),
    STRAIGHT_ON_1( null, 1 ),
    STRAIGHT_ON_2( null, 2 ),
    STRAIGHT_ON_3( null, 3 ),
    BACK_UP( null, -1 );


    private static final List<MovementCardValue> VALUES = Collections.unmodifiableList(
            Arrays.asList( values() )
    );

    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    private MovementCardRotationValue mRotationValue;
    private int mMovementQuantity;

    MovementCardValue( MovementCardRotationValue rotationValue, int movementQuantity ) {

        mRotationValue = rotationValue;
        mMovementQuantity = movementQuantity;
    }

    public String getMovementQuantityString() {

        if ( mMovementQuantity == 0 ) return "";

        return "Move " + mMovementQuantity;
    }

    public String getRotationString() {

        if ( mRotationValue == null ) return "";

        return mRotationValue.toString();
    }

    public static MovementCardValue randomCardValue()  {

        return VALUES.get( RANDOM.nextInt( SIZE ) );
    }
}
