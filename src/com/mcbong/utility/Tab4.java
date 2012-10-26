package com.mcbong.utility;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.stericson.RootTools.CommandCapture;
import com.stericson.RootTools.RootTools;

public class Tab4 extends Fragment {
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	 //** Inflate the layout for this fragment */
    		View view = inflater.inflate(R.layout.tab4, container, false);
    		
    		//** Define buttons.. */
    		Button button_reboot_views= (Button)view.findViewById(R.id.button_reboot);
    		Button button_reboot_ui_views= (Button)view.findViewById(R.id.button_reboot_ui);
    		
    		//** Set button image resources.. */  
    		button_reboot_views.setBackgroundResource(R.drawable.button);
    		button_reboot_ui_views.setBackgroundResource(R.drawable.button);
    		
    			        
    		Button button_reboot = (Button) view.findViewById(R.id.button_reboot);
    		button_reboot.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Activity activity = getActivity();
                    if (activity != null) {
                     //** Send shell command string to Superuser via 'roottools' .. */
                    	CommandCapture command = new CommandCapture(0, "su", "-c", "reboot");
                    	try {
							RootTools.getShell(true).add(command).waitForFinish();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (TimeoutException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    	
                    }
               }
                
           });
    		Button button_reboot_ui = (Button) view.findViewById(R.id.button_reboot_ui);
    		button_reboot_ui.setOnClickListener(new OnClickListener() {
 	            public void onClick(View v) {
 	                Activity activity = getActivity();
 	                if (activity != null) {
 	                	Toast.makeText(activity, R.string.test_button_reboot_ui, Toast.LENGTH_SHORT).show();
 	                //** Send shell command string to Superuser via 'roottools' .. */
 	                	//CommandCapture command = new CommandCapture(0, "su", "-c", "busybox killall system_server");
                    	//try {
						//	RootTools.getShell(true).add(command).waitForFinish();
						//} catch (InterruptedException e) {
							// TODO Auto-generated catch block
						//	e.printStackTrace();
					//	} catch (IOException e) {
							// TODO Auto-generated catch block
						//	e.printStackTrace();
						//} catch (TimeoutException e) {
						//	// TODO Auto-generated catch block
						//	e.printStackTrace();
						//}
 	                }
 	            }
 	            
 	        });
    		return view;
    	}
}

    