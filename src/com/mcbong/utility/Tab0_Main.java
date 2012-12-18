
package com.mcbong.utility;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Tab0_Main extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        // ** Inflate the layout for this fragment */
        final View view = inflater.inflate(R.layout.tab0_main, container, false);

        final Activity activity = getActivity();

        // ** Set up Dummy on-click handlers for buttons */
        // TODO - set up each button to initialise the required fragment and
        // change the action-bar tab to corresponding tab //

        Button button_mainpage_link_backup_restore = (Button) view
                .findViewById(R.id.button_mainpage_link_backup_restore);
        button_mainpage_link_backup_restore.setBackgroundResource(R.drawable.button);
        button_mainpage_link_backup_restore.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(activity, R.string.dummy, Toast.LENGTH_SHORT).show();

            }
        });

        Button button_mainpage_link_addons = (Button) view
                .findViewById(R.id.button_mainpage_link_addons);
        button_mainpage_link_addons.setBackgroundResource(R.drawable.button);
        button_mainpage_link_addons.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(activity, R.string.dummy, Toast.LENGTH_SHORT).show();

            }
        });

        Button button_mainpage_link_tweaks = (Button) view
                .findViewById(R.id.button_mainpage_link_tweaks);
        button_mainpage_link_tweaks.setBackgroundResource(R.drawable.button);
        button_mainpage_link_tweaks.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(activity, R.string.dummy, Toast.LENGTH_SHORT).show();

            }
        });

        Button button_mainpage_link_recovery = (Button) view
                .findViewById(R.id.button_mainpage_link_recovery);
        button_mainpage_link_recovery.setBackgroundResource(R.drawable.button);
        button_mainpage_link_recovery.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(activity, R.string.dummy, Toast.LENGTH_SHORT).show();

            }
        });

        Button button_mainpage_link_online = (Button) view
                .findViewById(R.id.button_mainpage_link_online);
        button_mainpage_link_online.setBackgroundResource(R.drawable.button);
        button_mainpage_link_online.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                // Create new fragment and transaction
                // Fragment newFragment = new Tab5_Online();
                // FragmentTransaction transaction =
                // getFragmentManager().beginTransaction();
                //
                // // Replace whatever is in the fragment_container view with
                // this
                // // fragment,
                // // and add the transaction to the back stack
                // transaction.replace(R.id.pager, newFragment);
                // // transaction.addToBackStack(null);
                //
                // // Commit the transaction
                // transaction.commit();
                //
                // // Fragment tabFiveFragment = new Tab5_Online();
                // //
                // // FragmentTransaction ft =
                // // getFragmentManager().beginTransaction();
                // // ft.replace(android.R.id.content, tabFiveFragment);
                // //
                // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                // // ft.commit();
                //
                // // Toast.makeText(activity, R.string.dummy,
                // // Toast.LENGTH_SHORT).show();
                //
            }
        });

        return view;

    }
}
