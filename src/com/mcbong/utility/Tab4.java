package com.mcbong.utility;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.stericson.RootTools.CommandCapture;
import com.stericson.RootTools.RootTools;

public class Tab4 extends Fragment {
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	 //** Inflate the layout for this fragment */
    		View view = inflater.inflate(R.layout.tab4, container, false);
    		
    		//** Define buttons.. */
    		Button button_reboot_views= (Button)view.findViewById(R.id.button_reboot);
    		Button button_reboot_recovery_views= (Button)view.findViewById(R.id.button_reboot_recovery);
    		Button button_power_off_views= (Button)view.findViewById(R.id.button_power_off);
    		
    		//** Set button image resources.. */  
    		button_reboot_views.setBackgroundResource(R.drawable.button);
    		button_reboot_recovery_views.setBackgroundResource(R.drawable.button);
    		button_power_off_views.setBackgroundResource(R.drawable.button);
    		
    		Button button_reboot = (Button) view.findViewById(R.id.button_reboot);
    		button_reboot.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Activity activity = getActivity();
                    if (activity != null) {
                    	
 	                	final Dialog alert_dialog = new Dialog(activity, R.style.custom_dialog);
                    	alert_dialog.setContentView(R.layout.custom_alert_dialog);
                    	alert_dialog.setTitle(R.string.dialog_title_reboot_device);
            			
             
            			//** set up the custom dialog components */ 
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
                    	
 	                	final Dialog alert_dialog = new Dialog(activity, R.style.custom_dialog);
                    	alert_dialog.setContentView(R.layout.custom_alert_dialog);
                    	alert_dialog.setTitle(R.string.dialog_title_reboot_recovery);
            			
             
            			//** set up the custom dialog components */ 
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
            					CommandCapture command = new CommandCapture(0, "su", "-c", "reboot recovery");
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
                    	
 	                	final Dialog alert_dialog = new Dialog(activity, R.style.custom_dialog);
                    	alert_dialog.setContentView(R.layout.custom_alert_dialog);
                    	alert_dialog.setTitle(R.string.dialog_title_power_off_device);
            			
             
            			//** set up the custom dialog components */ 
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
            					CommandCapture command = new CommandCapture(0, "su", "-c", "reboot -p");
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
    		
    		return view;
    	}
}

    