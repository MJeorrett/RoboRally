package org.mjeorrett.android.roborally;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by user on 27/11/2016.
 */

public class CardHandFragment extends Fragment {

    private View[] mCardViews;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState ) {

        View view = inflater.inflate( R.layout.fragment_card_hand,container, false );

        mCardViews = new View[9];

        mCardViews[0] = view.findViewById( R.id.fragment_card_hand_card_1 );
        mCardViews[1] = view.findViewById( R.id.fragment_card_hand_card_2 );
        mCardViews[2] = view.findViewById( R.id.fragment_card_hand_card_3 );
        mCardViews[3] = view.findViewById( R.id.fragment_card_hand_card_4 );
        mCardViews[4] = view.findViewById( R.id.fragment_card_hand_card_5 );
        mCardViews[5] = view.findViewById( R.id.fragment_card_hand_card_6 );
        mCardViews[6] = view.findViewById( R.id.fragment_card_hand_card_7 );
        mCardViews[7] = view.findViewById( R.id.fragment_card_hand_card_8 );
        mCardViews[8] = view.findViewById( R.id.fragment_card_hand_card_9 );

        for ( int i = 0; i < 9; i++ ) {

            final View card = mCardViews[i];
            final TextView directionTextView = (TextView) card.findViewById( R.id.view_movement_card_direction_text_view );
            final TextView quantityTextView = (TextView) card.findViewById( R.id.view_movement_card_quantity_text_view );
            final CardValue cardValue = CardValue.randomCardValue();

            directionTextView.setText( cardValue.getDirection() );
            quantityTextView.setText( cardValue.getQuantity() );
        }

        return view;
    }
}
