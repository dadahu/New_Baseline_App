package com.mcbong.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.stericson.RootTools.CommandCapture;
import com.stericson.RootTools.RootTools;

public class Tab4_Recovery extends Fragment {
	String line = "";
	 @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)	{
			//** Inflate the layout for this fragment */
			final View view = inflater.inflate(R.layout.tab4_recovery, container, false);
    		    		
    		//** set up Wireless ADB check and change an image as to whether its enabled or not */ 
    		try {
    		    Process adb_check = Runtime.getRuntime().exec("getprop   service.adb.tcp.port");
    		    BufferedReader adb_check_IS = new BufferedReader(new InputStreamReader(adb_check.getInputStream()));
    		    line = adb_check_IS.readLine();
    		    	if (line.equals("5555"))
    		    	{
    		    		
    		    		ImageView image = (ImageView) view.findViewById(R.id.imageView_adb_status);
            			image.setImageResource(R.drawable.on);
    		    		
    		    	}	
        			else
        			{
        				ImageView image = (ImageView) view.findViewById(R.id.imageView_adb_status);
            			image.setImageResource(R.drawable.off);
        		}
    		} catch (java.io.IOException e) {
    		}
    		
    		
    		//** Define buttons.. */
    		Button button_reboot_views= (Button)view.findViewById(R.id.button_reboot);
    		Button button_reboot_recovery_views= (Button)view.findViewById(R.id.button_reboot_recovery);
    		Button button_power_off_views= (Button)view.findViewById(R.id.button_power_off);
    		Button button_wireless_adb_enable= (Button)view.findViewById(R.id.button_wireless_adb_enable);
    		Button button_wireless_adb_disable= (Button)view.findViewById(R.id.button_wireless_adb_disable);
    		
    		
    		//** Set button image resources.. */  
    		button_reboot_views.setBackgroundResource(R.drawable.button);
    		button_reboot_recovery_views.setBackgroundResource(R.drawable.button);
    		button_power_off_views.setBackgroundResource(R.drawable.button);
    		button_wireless_adb_enable.setBackgroundResource(R.drawable.button);
    		button_wireless_adb_disable.setBackgroundResource(R.drawable.button);
    		
    		Button button_reboot = (Button) view.findViewById(R.id.button_reboot);
    		button_reboot.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Activity activity = getActivity();
                    if (activity != null) {
                    	
                    	//** call custom dialog into view and set characteristic's */
                    	final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
	                	alert_dialog.getWindow();
	                	alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	                	alert_dialog.setContentView(R.layout.custom_alert_dialog);
						TextView title = (TextView) alert_dialog.findViewById(R.id.custom_alert_dialog_textview_title);
						title.setText(R.string.dialog_title_reboot_device);
	                	TextView alert_text = (TextView) alert_dialog.findViewById(R.id.custom_alert_dialog_textview);
            			alert_text.setText(R.string.confirm);
            			ImageView image = (ImageView) alert_dialog.findViewById(R.id.custom_alert_dialog_image);
            			image.setImageResource(R.drawable.ic_launcher);
            			
            			//* set up button image resources */
            			Button custom_alert_dialog_ok = (Button) alert_dialog.findViewById(R.id.custom_alert_dialog_ok);
            			custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);
            			
            			//** if button is clicked, execute the shell commands through root-tools */
            			custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
            				public void onClick(View v) {
            					CommandCapture command = new CommandCapture(0, "reboot");
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
            			});
            			Button custom_alert_dialog_cancel = (Button) alert_dialog.findViewById(R.id.custom_alert_dialog_cancel);
            			custom_alert_dialog_cancel.setBackgroundResource(R.drawable.small_button);
            			// if OK button is clicked, close the custom dialog */
            			custom_alert_dialog_cancel.setOnClickListener(new OnClickListener() {
            				public void onClick(View v) {
            					alert_dialog.dismiss();
            				}
            			});
            			alert_dialog.show();
                    }
               }
           });
    		Button button_reboot_recovery = (Button) view.findViewById(R.id.button_reboot_recovery);
    		button_reboot_recovery.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Activity activity = getActivity();
                    if (activity != null) {
                    	
                    	//** call custom dialog into view and set characteristic's */
                    	final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
	                	alert_dialog.getWindow();
	                	alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	                	alert_dialog.setContentView(R.layout.custom_alert_dialog);
						TextView title = (TextView) alert_dialog.findViewById(R.id.custom_alert_dialog_textview_title);
						title.setText(R.string.dialog_title_reboot_recovery);
	                	TextView alert_text = (TextView) alert_dialog.findViewById(R.id.custom_alert_dialog_textview);
            			alert_text.setText(R.string.confirm);
            			ImageView image = (ImageView) alert_dialog.findViewById(R.id.custom_alert_dialog_image);
            			image.setImageResource(R.drawable.ic_launcher);
            			
            			//* set up button image resources */
            			Button custom_alert_dialog_ok = (Button) alert_dialog.findViewById(R.id.custom_alert_dialog_ok);
            			custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);
            			
            			//** if button is clicked, execute the shell commands through root-tools */
            			custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
            				public void onClick(View v) {
            					CommandCapture command = new CommandCapture(0, "reboot recovery");
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
            			});
            			Button custom_alert_dialog_cancel = (Button) alert_dialog.findViewById(R.id.custom_alert_dialog_cancel);
            			custom_alert_dialog_cancel.setBackgroundResource(R.drawable.small_button);
            			// if OK button is clicked, close the custom dialog */
            			custom_alert_dialog_cancel.setOnClickListener(new OnClickListener() {
            				public void onClick(View v) {
            					alert_dialog.dismiss();
            				}
            			});
            			
            			alert_dialog.show();
                    }
               }
            });
    		Button dialog_title_power_off_device = (Button) view.findViewById(R.id.button_power_off);
    		dialog_title_power_off_device.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Activity activity = getActivity();
                    if (activity != null) {
                    	
                    	//** call custom dialog into view and set characteristic's */
                    	final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
	                	alert_dialog.getWindow();
	                	alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	                	alert_dialog.setContentView(R.layout.custom_alert_dialog);
						TextView title = (TextView) alert_dialog.findViewById(R.id.custom_alert_dialog_textview_title);
						title.setText(R.string.dialog_title_power_off_device);
	                	TextView alert_text = (TextView) alert_dialog.findViewById(R.id.custom_alert_dialog_textview);
            			alert_text.setText(R.string.confirm);
            			ImageView image = (ImageView) alert_dialog.findViewById(R.id.custom_alert_dialog_image);
            			image.setImageResource(R.drawable.ic_launcher);
            			
            			//* set up button image resources */
            			Button custom_alert_dialog_ok = (Button) alert_dialog.findViewById(R.id.custom_alert_dialog_ok);
            			custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);
            			
            			//** if button is clicked, execute the shell commands through root-tools */
            			custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
            				public void onClick(View v) {
            					CommandCapture command = new CommandCapture(0, "reboot -p");
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
            			});
            			Button custom_alert_dialog_cancel = (Button) alert_dialog.findViewById(R.id.custom_alert_dialog_cancel);
            			custom_alert_dialog_cancel.setBackgroundResource(R.drawable.small_button);
            			// if OK button is clicked, close the custom dialog */
            			custom_alert_dialog_cancel.setOnClickListener(new OnClickListener() {
            				public void onClick(View v) {
            					alert_dialog.dismiss();
            				}
            			});
            			
            			alert_dialog.show();
                    }
               }
           });
    		button_wireless_adb_enable.setOnClickListener(new OnClickListener() {
 	            public void onClick(View v) {
 	                Activity activity = getActivity();
 	                
 	                if (activity != null) {
 	                	
 	                	//** Enable Wireless ADBD function */
 	                	//Toast.makeText(activity, R.string.enabling, Toast.LENGTH_SHORT).show();
 	                	CommandCapture command = new CommandCapture(0, "setprop service.adb.tcp.port 5555", "stop adbd", "start adbd");
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
                       	try {
                		    Process adb_check = Runtime.getRuntime().exec("getprop   service.adb.tcp.port");
                		    BufferedReader adb_check_IS = new BufferedReader(new InputStreamReader(adb_check.getInputStream()));
                		    line = adb_check_IS.readLine();
                		    	if (line.equals("5555"))
                		    	{
                		    		ImageView image = (ImageView) view.findViewById(R.id.imageView_adb_status);
                        			image.setImageResource(R.drawable.on);
                		    		Toast.makeText(activity, R.string.wireless_adb_enabled, Toast.LENGTH_SHORT).show();
                		    	}	
                    			else
                    			{
                    				ImageView image = (ImageView) view.findViewById(R.id.imageView_adb_status);
                        			image.setImageResource(R.drawable.off);
                    				Toast.makeText(activity, R.string.wireless_adb_disabled, Toast.LENGTH_SHORT).show();
                		    }
                		} catch (java.io.IOException e) {
                		} 
    				  }
 	            }
 	        });
    		button_wireless_adb_disable.setOnClickListener(new OnClickListener() {
 	            public void onClick(View v) {
 	                Activity activity = getActivity();
 	                
 	                if (activity != null) {
 	                	
 	                	//** Disable Wireless ADBD function */
 	                	//Toast.makeText(activity, R.string.disabling, Toast.LENGTH_SHORT).show();
 	                	CommandCapture command = new CommandCapture(0, "setprop service.adb.tcp.port -1", "stop adbd", "start adbd");
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
                       	
                       	try {
                		    Process adb_check = Runtime.getRuntime().exec("getprop   service.adb.tcp.port");
                		    BufferedReader adb_check_IS = new BufferedReader(new InputStreamReader(adb_check.getInputStream()));
                		    line = adb_check_IS.readLine();
                		    	if (line.equals("5555"))
                		    	{
                		    		ImageView image = (ImageView) view.findViewById(R.id.imageView_adb_status);
                        			image.setImageResource(R.drawable.on);
                        			Toast.makeText(activity, R.string.wireless_adb_enabled, Toast.LENGTH_SHORT).show();
                		    	}	
                    			else
                    			{
                    				ImageView image = (ImageView) view.findViewById(R.id.imageView_adb_status);
                        			image.setImageResource(R.drawable.off);
                    				Toast.makeText(activity, R.string.wireless_adb_disabled, Toast.LENGTH_SHORT).show();
                		    }
                		} catch (java.io.IOException e) {
                		}
    				 }
 	            }
 	        });
    		
    		return view;
    	}
} 
