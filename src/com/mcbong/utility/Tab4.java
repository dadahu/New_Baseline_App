package com.mcbong.utility;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
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
                    	//AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
 	                	//builder.setTitle(R.string.dialog_title_reboot_device);
 	                	//** add ok and cancel buttons */
 	                //builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
 	                          // public void onClick(DialogInterface dialog, int id) {
 	                               //** User clicked OK button */
 	                        	  //** Send shell command string to Superuser via 'roottools' .. */
                    	final Dialog alert_dialog = new Dialog(activity, R.style.custom_dialog);
                    	alert_dialog.setContentView(R.layout.custom_alert_dialog);
                    	alert_dialog.setTitle(R.string.dialog_title_reboot_device);
            			
             
            			//** set up the custom dialog components */ 
            			TextView alert_text = (TextView) alert_dialog.findViewById(R.id.custom_alert_dialog_textview);
            			alert_text.setText(R.string.confirm);
            			ImageView image = (ImageView) alert_dialog.findViewById(R.id.custom_alert_dialog_image);
            			image.setImageResource(R.drawable.ic_launcher);
             
            			Button custom_alert_dialog_ok = (Button) alert_dialog.findViewById(R.id.custom_alert_dialog_ok);
            			// if button is clicked, close the custom dialog
            			custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
            				public void onClick(View v) {
            					//dialog.dismiss();
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
            			// if button is clicked, close the custom dialog
            			custom_alert_dialog_cancel.setOnClickListener(new OnClickListener() {
            				public void onClick(View v) {
            					alert_dialog.dismiss();
            				}
            			});
            			
            			alert_dialog.show();
                    	
                    	
                    	
                    	
 	                          	
 	                          // }
 	                       //}
                //);
 	               // builder.setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
 	                        //   public void onClick(DialogInterface dialog, int id) {
 	                               // User cancelled the dialog
 	                       //    }
 	                     //  });
 	                //** Set other dialog properties if needed */
 	               //** ... */

 	                //** Create the AlertDialog */
 	               // AlertDialog dialog = builder.create();
 	               // dialog.show();
                   
                    	
                    }
               }
                
           });
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		Button button_reboot_recovery = (Button) view.findViewById(R.id.button_reboot_recovery);
    		button_reboot_recovery.setOnClickListener(new OnClickListener() {
 	            public void onClick(View v) {
 	                Activity activity = getActivity();
 	                if (activity != null) {
 	                	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
 	                	builder.setTitle(R.string.dialog_title_reboot_recovery);
 	                	//** add ok and cancel buttons */
 	                builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
 	                           public void onClick(DialogInterface dialog, int id) {
 	                               //** User clicked OK button */
 	                        		//** Send shell command string to Superuser via 'roottools' .. */
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
 	      						//	// TODO Auto-generated catch block
 	      							e.printStackTrace();
 	      						}
 	                           }
 	                       });
 	                builder.setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
 	                           public void onClick(DialogInterface dialog, int id) {
 	                               // User cancelled the dialog
 	                           }
 	                       });
 	                //** Set other dialog properties if needed */
 	               //** ... */

 	                //** Create the AlertDialog */
 	                AlertDialog dialog = builder.create();
 	                dialog.show();
 	                
 	                
 	                }
 	            }
 	            
 	        });
    		Button button_power_off = (Button) view.findViewById(R.id.button_power_off);
    		button_power_off.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Activity activity = getActivity();
                    if (activity != null) {
                    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
 	                	builder.setTitle(R.string.dialog_title_power_off_device);
 	                	//** add ok and cancel buttons */
 	                builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
 	                           public void onClick(DialogInterface dialog, int id) {
 	                               //**	User clicked OK button */
 	                        	  //** Send shell command string to Superuser via 'roottools' .. */
 	                          	CommandCapture command = new CommandCapture(0,"su", "-c", "reboot -p");
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
 	                builder.setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
 	                           public void onClick(DialogInterface dialog, int id) {
 	                               // User cancelled the dialog
 	                           }
 	                       });
 	                // Set other dialog properties if needed */
 	               //** ... */

 	                //** Create the AlertDialog */
 	                AlertDialog dialog = builder.create();
 	                dialog.show();
                   
                    	
                    }
               }
                
           });
    		return view;
    	}
}

    