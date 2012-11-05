package com.mcbong.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tab3 extends Fragment {
	 
	 
	 
	 
    protected static final TextView text = null;
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		    //** Inflate the layout for this fragment */

    		final View view = inflater.inflate(R.layout.tab3, container, false);
    		
    		//** Define WebView component & Buttons,also load required WebSite.. */
    		final WebView myWebView = (WebView) view.findViewById(R.id.webview);
    		myWebView.setWebViewClient(new WebViewClient());
    		myWebView.loadUrl("http://team-scrat.blogspot.co.uk");
    		myWebView.setOnKeyListener(new OnKeyListener() {
    	        public boolean onKey(View v, int keyCode, KeyEvent event) {
    	            if (keyCode == KeyEvent.KEYCODE_BACK) {
    	            	if(myWebView.canGoBack())
    	            		myWebView.goBack();
    	                return true;
    	            }
    	            //** process normally */
    	            return false;
    	        }
    	    });
    		
    		//* set up button image resources */
    		Button button_check_webver = (Button) view.findViewById(R.id.button_check_webver);
    		button_check_webver.setBackgroundResource(R.drawable.button);
    
    		button_check_webver.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Activity activity = getActivity();
                if (activity != null) {
                    try {
                    	//Toast.makeText(activity, R.string.checking, Toast.LENGTH_SHORT).show();
                    	//** ..... */
                		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    	StrictMode.setThreadPolicy(policy); 
                    	
                    	//** Define a URL for the desired page */
                        URL url = new URL("http://dl.dropbox.com/u/18271886/Test_Builds/version.txt");
                    	
                    	//** Read all the text within remote file */
                        BufferedReader in = new BufferedReader(new     InputStreamReader(url.openStream()));
                        String str;
                        StringBuilder sb = new StringBuilder(100);
                        while ((str = in.readLine()) != null) {
                            sb.append(str);
                            //** str is one line of text; readLine() strips the newline character(s) */
                        }
                        in.close();
                        
                        //** call custom dialog into view */
            			final Dialog dialog = new Dialog(activity, R.style.custom_dialog);
            			dialog.setContentView(R.layout.custom_dialog);
            			dialog.setTitle(R.string.latest_version);
            			
            			//** set up the custom dialog components */ 
            			TextView text = (TextView) dialog.findViewById(R.id.custom_dialog_textview);
            			text.setText(sb.toString()+"");
            			
            			String remote_version_string = sb.toString().toString();
            			String current_version_string = getString(R.string.version).toString();
            			
            			if (current_version_string.equals(remote_version_string))
            			{
            				Toast.makeText(activity, R.string.version_uptodate, Toast.LENGTH_SHORT).show();
            			}	
            			else
            			{
            				Toast.makeText(activity, R.string.version_notuptodate, Toast.LENGTH_SHORT).show();
            				Button custom_dialog_update = (Button) dialog.findViewById(R.id.custom_dialog_update);
                			custom_dialog_update.setVisibility(View.VISIBLE);
                			Button dialogButton2 = (Button) dialog.findViewById(R.id.custom_dialog_ok2);
                			dialogButton2.setBackgroundResource(R.drawable.small_button);
                			dialogButton2.setVisibility(View.VISIBLE);
                			Button dialogButton = (Button) dialog.findViewById(R.id.custom_dialog_ok);
                			dialogButton.setVisibility(View.INVISIBLE);
                			
            			}
            			
            			ImageView image = (ImageView) dialog.findViewById(R.id.custom_dialog_image);
            			image.setImageResource(R.drawable.ic_launcher);
            			
            			//* set up button image resources */
            			Button custom_dialog_update = (Button) dialog.findViewById(R.id.custom_dialog_update);
            			custom_dialog_update.setBackgroundResource(R.drawable.small_button_update);
            			// if OK button is clicked, close the custom dialog */
            			custom_dialog_update.setOnClickListener(new OnClickListener() {
            				public void onClick(View v) {
            					Activity activity = getActivity();
            					//** Download latest version from server ... */
        	                	
        	                	String url = "http://dl.dropbox.com/u/18271886/Test_Builds/McBong-Utility.apk";
        	                	DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        	                	request.setDescription("McBong Utility Updater");
        	                	request.setTitle("McBong-Utility Download Complete");
        	                	
        	                	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        	                	    request.allowScanningByMediaScanner();
        	                	    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        	                	}
        	                	request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "McBong-Utility.apk");

        	                	//** get download service and enqueue file */
        	                	DownloadManager manager = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
        	                	manager.enqueue(request);
        	                	Toast.makeText(activity, R.string.downloading, Toast.LENGTH_SHORT).show();
        	                	Toast.makeText(activity, R.string.downloading_instructions, Toast.LENGTH_LONG).show();
        	                  
            					
            				}
            			});	
            			Button dialogButton = (Button) dialog.findViewById(R.id.custom_dialog_ok);
            			dialogButton.setBackgroundResource(R.drawable.small_button);
            			//** if OK button is clicked, close the custom dialog */
            			dialogButton.setOnClickListener(new OnClickListener() {
            				public void onClick(View v) {
            					dialog.dismiss();
            				}
            			});
            			Button dialogButton2 = (Button) dialog.findViewById(R.id.custom_dialog_ok2);
            			dialogButton2.setBackgroundResource(R.drawable.small_button);
            			//** if OK button is clicked, close the custom dialog */
            			dialogButton2.setOnClickListener(new OnClickListener() {
            				public void onClick(View v) {
            					dialog.dismiss();
            				}
            			});
            			
            		
            			dialog.show();
                     } catch (MalformedURLException e) {
                    	 Toast.makeText(activity, R.string.error_unsupported_p, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                    	Toast.makeText(activity, R.string.error_no_internet, Toast.LENGTH_SHORT).show();
                    }
                 }
            }
            
        });
    	 return view;
       }
    }