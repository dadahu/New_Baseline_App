package com.mcbong.utility;

import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tab2_Addons extends Fragment {
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    	 //** Inflate the layout for this fragment */
	    		View view = inflater.inflate(R.layout.tab2_addons, container, false);
	    		
	    		//** Define buttons.. */	
		        Button button_install_adw = (Button) view.findViewById(R.id.button_install_adw);
		        Button button_install_golauncher = (Button) view.findViewById(R.id.button_install_golauncher);
		        Button button_install_nova = (Button) view.findViewById(R.id.button_install_nova);
		        //** Set button image resources.. */    
		        Button button_install_adw_views= (Button)view.findViewById(R.id.button_install_adw);
		        button_install_adw_views.setBackgroundResource(R.drawable.button);
		        Button button_install_golauncher_views= (Button)view.findViewById(R.id.button_install_golauncher);
		        button_install_golauncher_views.setBackgroundResource(R.drawable.button);
		        Button button_install_nova_views= (Button)view.findViewById(R.id.button_install_nova);
		        button_install_nova_views.setBackgroundResource(R.drawable.button);
	    		
	    		
		        button_install_adw.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) {
		                Activity activity = getActivity();
		                
		                if (activity != null) {
	                	
		                	final Dialog alert_dialog = new Dialog(activity, R.style.custom_dialog);
	                    	alert_dialog.setContentView(R.layout.custom_alert_dialog);
	                    	alert_dialog.setTitle(R.string.install_adw);
	            			
	             
	            			//** set up the custom dialog components */ 
	            			TextView alert_text = (TextView) alert_dialog.findViewById(R.id.custom_alert_dialog_textview);
	            			alert_text.setText(R.string.confirm);
	            			ImageView image = (ImageView) alert_dialog.findViewById(R.id.custom_alert_dialog_image);
	            			image.setImageResource(R.drawable.adw);
	            			//* set up button image resources */
	            			Button custom_alert_dialog_ok = (Button) alert_dialog.findViewById(R.id.custom_alert_dialog_ok);
	            			custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);
	            			//** if button is clicked, execute the shell commands through root-tools */
	            			custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
	            				public void onClick(View v) {
	            					Activity activity = getActivity();
	            					//** download ADW Launcher from server .. */
	            					String url = "http://dl.dropbox.com/u/18271886/launchers/adw.apk";
	        	                	DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
	        	                	request.setDescription("ADW	Launcher Download");
	        	                	request.setTitle("ADW Launcher Download Complete");
	        	                	
	        	                	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
	        	                	    request.allowScanningByMediaScanner();
	        	                	    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
	        	                	}
	        	                	request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "ADW-Launcher.apk");

	        	                	//** get download service and enqueue file */
	        	                	DownloadManager manager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
	        	                	manager.enqueue(request);
	        	                	Toast.makeText(activity, R.string.downloading_launcher, Toast.LENGTH_SHORT).show();
	        	                	alert_dialog.dismiss();
	        	                	//Toast.makeText(activity, R.string.downloading_instructions, Toast.LENGTH_LONG).show();
	        	                  	
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
	    		 button_install_golauncher.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) {
		                Activity activity = getActivity();
		                
		                if (activity != null) {
		                	
		                	
		                	final Dialog alert_dialog = new Dialog(activity, R.style.custom_dialog);
	                    	alert_dialog.setContentView(R.layout.custom_alert_dialog);
	                    	alert_dialog.setTitle(R.string.install_golauncher);
	            			
	             
	            			//** set up the custom dialog components */ 
	            			TextView alert_text = (TextView) alert_dialog.findViewById(R.id.custom_alert_dialog_textview);
	            			alert_text.setText(R.string.confirm);
	            			ImageView image = (ImageView) alert_dialog.findViewById(R.id.custom_alert_dialog_image);
	            			image.setImageResource(R.drawable.golauncher);
	            			//* set up button image resources */
	            			Button custom_alert_dialog_ok = (Button) alert_dialog.findViewById(R.id.custom_alert_dialog_ok);
	            			custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);
	            			//** if button is clicked, execute the shell commands through root-tools */
	            			custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
	            				public void onClick(View v) {
	            					Activity activity = getActivity();
	            					//** download Go-Launcher EX from server .. */
	            					String url = "http://dl.dropbox.com/u/18271886/launchers/golauncherex.apk";
	        	                	DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
	        	                	request.setDescription("Go-Launcher EX Download");
	        	                	request.setTitle("Go-Launcher EX Download Complete");
	        	                	
	        	                	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
	        	                	    request.allowScanningByMediaScanner();
	        	                	    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
	        	                	}
	        	                	request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Go-Launcher.apk");

	        	                	//** get download service and enqueue file */
	        	                	DownloadManager manager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
	        	                	manager.enqueue(request);
	        	                	Toast.makeText(activity, R.string.downloading_launcher, Toast.LENGTH_SHORT).show();
	        	                	alert_dialog.dismiss();
	        	                	//Toast.makeText(activity, R.string.downloading_instructions, Toast.LENGTH_LONG).show();
	        	                  	
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
		        button_install_nova.setOnClickListener(new OnClickListener() {
		            public void onClick(View v) {
		                Activity activity = getActivity();
		                
		                if (activity != null) {
		                	
		                	final Dialog alert_dialog = new Dialog(activity, R.style.custom_dialog);
	                    	alert_dialog.setContentView(R.layout.custom_alert_dialog);
	                    	alert_dialog.setTitle(R.string.install_nova);
	            			
	             
	            			//** set up the custom dialog components */ 
	            			TextView alert_text = (TextView) alert_dialog.findViewById(R.id.custom_alert_dialog_textview);
	            			alert_text.setText(R.string.confirm);
	            			ImageView image = (ImageView) alert_dialog.findViewById(R.id.custom_alert_dialog_image);
	            			image.setImageResource(R.drawable.nova);
	            			//* set up button image resources */
	            			Button custom_alert_dialog_ok = (Button) alert_dialog.findViewById(R.id.custom_alert_dialog_ok);
	            			custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);
	            			//** if button is clicked, execute the shell commands through root-tools */
	            			custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
	            				public void onClick(View v) {
	            					Activity activity = getActivity();
	            					//** download Nova Launcher from server .. */
	            					String url = "http://dl.dropbox.com/u/18271886/launchers/nova.apk";
	        	                	DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
	        	                	request.setDescription("Nova Launcher EX Download");
	        	                	request.setTitle("Nova Launcher EX Download Complete");
	        	                	
	        	                	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
	        	                	    request.allowScanningByMediaScanner();
	        	                	    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
	        	                	}
	        	                	request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Nova-Launcher.apk");

	        	                	//** get download service and enqueue file */
	        	                	DownloadManager manager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
	        	                	manager.enqueue(request);
	        	                	Toast.makeText(activity, R.string.downloading_launcher, Toast.LENGTH_SHORT).show();
	        	                	alert_dialog.dismiss();
	        	                	//Toast.makeText(activity, R.string.downloading_instructions, Toast.LENGTH_LONG).show();
	        	                  	
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