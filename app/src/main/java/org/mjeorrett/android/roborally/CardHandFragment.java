package org.mjeorrett.android.roborally;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by user on 27/11/2016.
 */

public class CardHandFragment extends Fragment implements View.OnClickListener {

    private MovementCard[] mCards;
    private Button mDealButton;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState ) {

        View view = inflater.inflate( R.layout.fragment_card_hand,container, false );

        mCards = new MovementCard[9];

        mCards[0] = new MovementCard( view.findViewById( R.id.fragment_card_hand_card_1 ) );
        mCards[1] = new MovementCard( view.findViewById( R.id.fragment_card_hand_card_2 ) );
        mCards[2] = new MovementCard( view.findViewById( R.id.fragment_card_hand_card_3 ) );
        mCards[3] = new MovementCard( view.findViewById( R.id.fragment_card_hand_card_4 ) );
        mCards[4] = new MovementCard( view.findViewById( R.id.fragment_card_hand_card_5 ) );
        mCards[5] = new MovementCard( view.findViewById( R.id.fragment_card_hand_card_6 ) );
        mCards[6] = new MovementCard( view.findViewById( R.id.fragment_card_hand_card_7 ) );
        mCards[7] = new MovementCard( view.findViewById( R.id.fragment_card_hand_card_8 ) );
        mCards[8] = new MovementCard( view.findViewById( R.id.fragment_card_hand_card_9 ) );

        mDealButton = (Button) view.findViewById( R.id.fragment_card_hand_deal_button );
        mDealButton.setOnClickListener( this );

        dealCards();

        return view;
    }

    @Override
    public void onClick(View view) {

        if ( view.getId() == mDealButton.getId() ) {

            dealCards();
        }
    }

    private void dealCards() {

        for ( int i = 0; i < 9; i++ ) {

            final MovementCard card = mCards[i];
            final MovementCardValue cardValue = MovementCardValue.randomCardValue();
            card.setCardValue( cardValue );
        }
    }
}
