
package com.mcbong.utility;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.stericson.RootTools.RootTools;

import java.util.ArrayList;

public class McBong extends FragmentActivity {

    FragmentTransaction transaction;
    static ViewPager mViewPager;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // ** Grant This Application full root access via root-tools interactive
        // shell */
        if (RootTools.isAccessGiven()) {
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // ** Create Fragment's .. */
        final Fragment tabZeroFragment = new Tab0_Main();
        final Fragment tabOneFragment = new Tab1_Backup_Restore();
        final Fragment tabTwoFragment = new Tab2_Addons();
        final Fragment tabThreeFragment = new Tab3_Tweaks();
        final Fragment tabFourFragment = new Tab4_Recovery();
        final Fragment tabFiveFragment = new Tab5_Online();

        // ** Add Fragment's .. */
        PagerAdapter mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(tabZeroFragment);
        mPagerAdapter.addFragment(tabOneFragment);
        mPagerAdapter.addFragment(tabTwoFragment);
        mPagerAdapter.addFragment(tabThreeFragment);
        mPagerAdapter.addFragment(tabFourFragment);
        mPagerAdapter.addFragment(tabFiveFragment);

        // ** Set up pager adapter .. */
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(6);
        mViewPager.setCurrentItem(0);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // ** When swiping between pages select the correct Tab
                // */
                getActionBar().setSelectedNavigationItem(position);
            }
        });

        // ** Set up ActionBar .. */
        final ActionBar ab = getActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        Tab tab0 = ab.newTab().setText("| Main Page Selector ..|..")
                .setTabListener(new TabListener<Tab0_Main>(this, "tabzero", Tab0_Main.class));

        Tab tab1 = ab
                .newTab()
                .setText("| Backup & Restore ..|..")
                .setTabListener(
                        new TabListener<Tab1_Backup_Restore>(this, "tabone",
                                Tab1_Backup_Restore.class));

        Tab tab2 = ab.newTab().setText("| Add-on's ..|..")
                .setTabListener(new TabListener<Tab2_Addons>(this, "tabtwo", Tab2_Addons.class));

        Tab tab3 = ab.newTab().setText("| Tweak's ..|..")
                .setTabListener(new TabListener<Tab3_Tweaks>(this, "tabthree", Tab3_Tweaks.class));

        Tab tab4 = ab
                .newTab()
                .setText("| Recovery ..|..")
                .setTabListener(
                        new TabListener<Tab4_Recovery>(this, "tabfour", Tab4_Recovery.class));

        Tab tab5 = ab.newTab().setText("| Online ..|")
                .setTabListener(new TabListener<Tab5_Online>(this, "tabfive", Tab5_Online.class));

        ab.addTab(tab0);
        ab.addTab(tab1);
        ab.addTab(tab2);
        ab.addTab(tab3);
        ab.addTab(tab4);
        ab.addTab(tab5);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuitem_device_info:

                // ** call custom dialog into view and set characteristic's */
                final Dialog dialog = new Dialog(this, R.style.Theme_Dialog_Translucent);
                dialog.getWindow();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_info_dialog);
                Button dialogButton = (Button) dialog.findViewById(R.id.custom_about_dialog_ok);

                // ** Centre OK button on dialog */
                LayoutParams params = (RelativeLayout.LayoutParams) dialogButton.getLayoutParams();
                params.addRule(RelativeLayout.CENTER_IN_PARENT);
                dialogButton.setLayoutParams(params); // causes layout update

                // ** set dialog title .. */
                TextView title = (TextView) dialog
                        .findViewById(R.id.custom_about_dialog_textview_title);
                title.setText(R.string.menu_device_info);

                // ** reference dialog device info textview's.. */
                final TextView textView_device_row2 = (TextView) dialog
                        .findViewById(R.id.textView_device_row2);
                final TextView textView_device_row4 = (TextView) dialog
                        .findViewById(R.id.textView_device_row4);
                final TextView textView_device_row6 = (TextView) dialog
                        .findViewById(R.id.textView_device_row6);
                final TextView textView_device_row8 = (TextView) dialog
                        .findViewById(R.id.textView_device_row8);

                // ** reference dialog application info textview's.. */
                final TextView textView_os_row2 = (TextView) dialog
                        .findViewById(R.id.textView_os_row2);
                // final TextView textView_application_row4 = (TextView) dialog
                // .findViewById(R.id.textView_application_row4); - Removed for
                // now while look into different information to collect.
                final TextView textView_os_row6 = (TextView) dialog
                        .findViewById(R.id.textView_os_row6);
                final TextView textView_os_row8 = (TextView) dialog
                        .findViewById(R.id.textView_os_row8);

                // ** Define device and application string's .. */
                final String model = new String(android.os.Build.MODEL);
                final String device = new String(android.os.Build.DEVICE);
                final String product = new String(android.os.Build.PRODUCT);
                final String board = new String(android.os.Build.BOARD);

                final String version = new String(android.os.Build.VERSION.RELEASE);
                // final String build = new String(android.os.Build.DISPLAY); -
                // Removed for now while look into different information to
                // collect.
                final String build_id = new String(android.os.Build.ID);
                final String user = new String(android.os.Build.USER);

                // ** Set the correct strings to the correct textview's.. */
                textView_device_row2.setText(model);
                textView_device_row4.setText(device);
                textView_device_row6.setText(product);
                textView_device_row8.setText(board);

                textView_os_row2.setText(version);
                // textView_application_row4.setText(build); - Removed for now
                // while look into different information to collect.
                textView_os_row6.setText(build_id);
                textView_os_row8.setText(user);

                // * set up button image resources */
                dialogButton.setBackgroundResource(R.drawable.small_button);
                // ** if OK button is clicked, close the custom dialog */
                dialogButton.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                return true;

            case R.id.menuitem_appinfo:

                // ** call custom dialog into view and set characteristic's */
                final Dialog dialog1 = new Dialog(this, R.style.Theme_Dialog_Translucent);
                dialog1.getWindow();
                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog1.setContentView(R.layout.custom_about_dialog);
                Button dialogButton1 = (Button) dialog1.findViewById(R.id.custom_about_dialog_ok);

                final String app_info_title = new String(
                        "=========="
                                + "\n"
                                + "McBong-Utility"
                                + "\n"
                                + "=========="
                                + "\n"
                                + "\n"
                                + "An App designed for users who want all the useful feature's ALL in one place..");

                final String app_info = new String(
                        "*This Project was started as a mean's to easily set up a device after an initial ROM Flash,"
                                + "and has had small feature's added here and there to make post-ROM-Flash even easier.\n"
                                + "\n"
                                + "This was an idea I just had to get done and so here we are.\n"
                                + "\n" + ". . .\n" + "\n" + "{" + "." + getString(R.string.version)
                                + "." + "}" + "\n");

                // ** Center OK button on dialog */
                LayoutParams params1 = (RelativeLayout.LayoutParams) dialogButton1
                        .getLayoutParams();
                params1.addRule(RelativeLayout.CENTER_IN_PARENT);
                dialogButton1.setLayoutParams(params1); // causes layout update
                TextView title1 = (TextView) dialog1
                        .findViewById(R.id.custom_about_dialog_textview_title);
                title1.setText(app_info_title);

                // ** set up the custom dialog components */
                TextView text1 = (TextView) dialog1.findViewById(R.id.custom_about_dialog_textview);
                text1.setText(app_info);

                // * set up button image resources */
                dialogButton1.setBackgroundResource(R.drawable.small_button);
                // ** if OK button is clicked, close the custom dialog */
                dialogButton1.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });

                dialog1.show();
                return true;
            case R.id.menuitem_quit:
                android.os.Process.killProcess(android.os.Process.myPid());
                return true;
        }
        return false;
    }

    // ** Set Hardware Back & Menu Key to do Nothing if pressed, as to use
    // on-screen touch options instead.. */
    @Override
    public void onBackPressed() {

        return;
    }

    public void onMenuPressed() {

        return;
    }

    public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
        private Fragment mFragment;
        private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;

        /**
         * Constructor used each time a new tab is created.
         * 
         * @param activity The host Activity, used to instantiate the fragment
         * @param tag The identifier tag for the fragment
         * @param clz The fragment's Class, used to instantiate the fragment
         */
        public TabListener(Activity activity, String tag, Class<T> clz) {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
        }

        // ** The following are each of the ActionBar.TabListener call-backs */
        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            // ** Check if the fragment is already initialised */
            if (mFragment == null) {
                // If not, instantiate and add it to the activity
                mFragment = Fragment.instantiate(mActivity, mClass.getName());
                ft.add(android.R.id.content, mFragment, mTag);
            } else {
                // ** If it exists, simply attach it in order to show it */
                ft.attach(mFragment);
            }
        }

        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            if (mFragment != null) {
                // ** Detach the fragment as the next Tab is being attached */
                ft.detach(mFragment);
            }
        }

        public void onTabReselected(Tab tab, FragmentTransaction ft) {

        }

        public void onTabReselected(Tab arg0, android.app.FragmentTransaction arg1) {
            // TODO Auto-generated method stub

        }

        public void onTabSelected(Tab arg0, android.app.FragmentTransaction arg1) {
            // TODO Auto-generated method stub
            mViewPager.setCurrentItem(arg0.getPosition());
        }

        public void onTabUnselected(Tab arg0, android.app.FragmentTransaction arg1) {
            // TODO Auto-generated method stub

        }
    }

    public class PagerAdapter extends FragmentPagerAdapter {

        private final ArrayList<Fragment> mFragments = new ArrayList<Fragment>();

        public PagerAdapter(FragmentManager manager) {
            super(manager);
        }

        public void addFragment(Fragment fragment) {
            mFragments.add(fragment);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
