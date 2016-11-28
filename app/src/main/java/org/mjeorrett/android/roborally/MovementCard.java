package org.mjeorrett.android.roborally;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/**
 * Created by user on 28/11/2016.
 */

public class MovementCard {

    private View mView;
    private TextView mDirectionTextView;
    private TextView mQuantityTextView;
    private MovementCardValue mCardValue;

    public MovementCard( View view, @Nullable MovementCardValue cardValue ) {

        mView = view;
        mDirectionTextView = (TextView) mView.findViewById( R.id.view_movement_card_direction_text_view );
        mQuantityTextView = (TextView) mView.findViewById( R.id.view_movement_card_quantity_text_view );

        if ( cardValue != null ) this.setCardValue( cardValue );
    }

    public MovementCard( View view ) {

        this( view, null );
    }

    public void setCardValue( MovementCardValue cardValue ) {

        mCardValue = cardValue;
        mDirectionTextView.setText( mCardValue.getDirection() );
        mQuantityTextView.setText( mCardValue.getQuantity() );
    }

}
