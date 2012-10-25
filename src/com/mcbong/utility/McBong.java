package com.mcbong.utility;


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class McBong extends Activity {
	public static Context appContext;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        appContext = getApplicationContext();
        setRequestedOrientation(1); 

      
       //ActionBar
        ActionBar actionbar = getActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        ActionBar.Tab TweakTab = actionbar.newTab().setText("| Tool's ..|");
        ActionBar.Tab AddonTab = actionbar.newTab().setText("| Tweak's ..|");
        ActionBar.Tab OnlineTab = actionbar.newTab().setText("| Online..|");
        ActionBar.Tab RecoveryTab = actionbar.newTab().setText("| Recovery..|");
        
        Fragment TweakFragment = new Tab1();
        Fragment AddonFragment = new Tab2();
        Fragment OnlineFragment = new Tab3();
        Fragment RecoveryFragment = new Tab4();

        TweakTab.setTabListener(new MyTabsListener(TweakFragment));
        AddonTab.setTabListener(new MyTabsListener(AddonFragment));
        OnlineTab.setTabListener(new MyTabsListener(OnlineFragment));
        RecoveryTab.setTabListener(new MyTabsListener(RecoveryFragment));

        actionbar.addTab(TweakTab);
        actionbar.addTab(AddonTab);
        actionbar.addTab(OnlineTab);
        actionbar.addTab(RecoveryTab);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

  //Set Hardware Back Key to do Nothing .....
  	@Override
  	public void onBackPressed() {

  	   return;
  	}
  //.... /Set Hardware Menu Key to do Nothing .....
  	
  public void onMenuPressed() {

  	   return;
  	}
  
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			//case R.id.menuitem_about:
			//	Toast.makeText(appContext, "'McBong-Utility' } Internal test build -v1.0", Toast.LENGTH_SHORT).show();
			//	return true;
			case R.id.menuitem_quit:
				//Toast.makeText(appContext, "quit", Toast.LENGTH_SHORT).show();
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
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		//Toast.makeText(StartActivity.appContext, "This Tab is already in view", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.replace(R.id.fragment_container, fragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(fragment);
	}
	
		
	
}
