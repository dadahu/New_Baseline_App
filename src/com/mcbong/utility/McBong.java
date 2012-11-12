package com.mcbong.utility;


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.stericson.RootTools.RootTools;

public class McBong extends Activity {
	public static Context appContext;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	
    	//** Grant app full root access via roottools interactive shell */
    	if (RootTools.isAccessGiven()) {
    	}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        appContext = getApplicationContext();
              
        //** Define ActionBar and Tabs.. */
        ActionBar actionbar = getActionBar();
        
        //actionbar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Red)));
        //actionbar.setStackedBackgroundDrawable(getResources().getDrawable(R.drawable.tabbar));
        
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        ActionBar.Tab ToolsTab = actionbar.newTab().setText("| Tool's ..|..");
        ActionBar.Tab AddonsTab = actionbar.newTab().setText("| Add-on's ..|..");
        ActionBar.Tab TweaksTab = actionbar.newTab().setText("| Tweak's ..|..");
        ActionBar.Tab RecoveryTab = actionbar.newTab().setText("| Recovery..|");
		ActionBar.Tab OnlineTab = actionbar.newTab().setText("| Online..|..");
	   
        Fragment ToolsFragment = new Tab1_Tools();
        Fragment AddonsFragment = new Tab2_Addons();
        Fragment TweaksFragment = new Tab3_Tweaks();
        Fragment RecoveryFragment = new Tab4_Recovery();
        Fragment OnlineFragment = new Tab5_Online();

        ToolsTab.setTabListener(new MyTabsListener(ToolsFragment));
        AddonsTab.setTabListener(new MyTabsListener(AddonsFragment));
        TweaksTab.setTabListener(new MyTabsListener(TweaksFragment));
        OnlineTab.setTabListener(new MyTabsListener(OnlineFragment));
        RecoveryTab.setTabListener(new MyTabsListener(RecoveryFragment));

        actionbar.addTab(ToolsTab);
        actionbar.addTab(AddonsTab);
        actionbar.addTab(TweaksTab);
		actionbar.addTab(RecoveryTab);
        actionbar.addTab(OnlineTab);
        
   }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    
    	//** Set Hardware Back & Menu Key to do Nothing if pressed, as to use on-screen touch options instead.. */
  	@Override
  	public void onBackPressed() {

		return;
  	}
  	public void onMenuPressed() {

  	   return;
  	}
  
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.menuitem_version:
		
				//** call custom dialog into view and set characteristic's */
				final Dialog dialog = new Dialog(this, R.style.Theme_Dialog_Translucent);
                dialog.getWindow();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_dialog);
                Button dialogButton = (Button) dialog.findViewById(R.id.custom_dialog_ok);
                //** Center OK button on dialog */
                LayoutParams params = (RelativeLayout.LayoutParams)dialogButton.getLayoutParams();
                params.addRule(RelativeLayout.CENTER_IN_PARENT);
                dialogButton.setLayoutParams(params); //causes layout update
                TextView title = (TextView) dialog.findViewById(R.id.custom_dialog_textview_title);
                title.setText(R.string.installed_version);
    			    			
    			//** set up the custom dialog components */ 
    			TextView text = (TextView) dialog.findViewById(R.id.custom_dialog_textview);
    			text.setText(R.string.version);
    			ImageView image = (ImageView) dialog.findViewById(R.id.custom_dialog_image);
    			image.setImageResource(R.drawable.ic_launcher);
    			//* set up button image resources */
    			dialogButton.setBackgroundResource(R.drawable.small_button);
    			//** if OK button is clicked, close the custom dialog */
    			dialogButton.setOnClickListener(new OnClickListener() {
    				public void onClick(View v) {
    					dialog.dismiss();
    				}
    			});
    			
    			dialog.show();
				return true;
			case R.id.menuitem_quit:
				android.os.Process.killProcess(android.os.Process.myPid());
				return true;
		}
		return false;
	}
	
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
    }
    
}



class MyTabsListener implements ActionBar.TabListener {
	public Fragment fragment;
	
	public MyTabsListener(Fragment fragment) {
		this.fragment = fragment;
	}
	
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		//** Add Function for reselected tab here.. */ 
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.replace(R.id.fragment_container, fragment);
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(fragment);
	}
	
		
	
}
