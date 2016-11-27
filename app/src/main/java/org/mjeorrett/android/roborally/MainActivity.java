package org.mjeorrett.android.roborally;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.main_activity_fragment );

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment boardFragment = fragmentManager.findFragmentById( R.id.board_fragment_container );

        if ( boardFragment == null ) {

            boardFragment = new BoardFragment();
            fragmentManager.beginTransaction()
                    .add( R.id.board_fragment_container, boardFragment )
                    .commit();
        }

        Fragment cardHandFragment = fragmentManager.findFragmentById( R.id.card_hand_fragment_container );

        if ( cardHandFragment == null ) {

            cardHandFragment = new CardHandFragment();
            fragmentManager.beginTransaction()
                    .add( R.id.card_hand_fragment_container, cardHandFragment )
                    .commit();
        }
    }
}
