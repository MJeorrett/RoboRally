package org.mjeorrett.android.roborally;

import android.view.View;
import android.widget.TextView;

/**
 * Created by user on 28/11/2016.
 */

public class MovementCard {

    private int mPlayOrder;
    private TextView mPlayOrderTextView;
    private TextView mDirectionTextView;
    private TextView mQuantityTextView;

    public MovementCard( View view ) {

        mPlayOrder = 0;
        mPlayOrderTextView = (TextView) view.findViewById( R.id.view_movement_card_play_order_text_view );
        mDirectionTextView = (TextView) view.findViewById( R.id.view_movement_card_direction_text_view );
        mQuantityTextView = (TextView) view.findViewById( R.id.view_movement_card_quantity_text_view );

        view.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick( View view ) {

                mPlayOrder += 1;
                mPlayOrderTextView.setText( String.valueOf( mPlayOrder ) );
            }
        });
    }

    public void setCardValue( MovementCardValue cardValue ) {

        mDirectionTextView.setText( cardValue.getMovementQuantityString() );
        mQuantityTextView.setText( cardValue.getRotationString() );
    }

}