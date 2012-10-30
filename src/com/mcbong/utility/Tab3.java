package com.mcbong.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
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
                    	Toast.makeText(activity, R.string.checking, Toast.LENGTH_SHORT).show();
                    	//** ..... */
                		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    	StrictMode.setThreadPolicy(policy); 
                    	
                    	//** Define a URL for the desired page */
                        URL url = new URL("http://dl.dropbox.com/u/18271886/version.txt");

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
            			ImageView image = (ImageView) dialog.findViewById(R.id.custom_dialog_image);
            			image.setImageResource(R.drawable.ic_launcher);
            			//* set up button image resources */
            			Button dialogButton = (Button) dialog.findViewById(R.id.custom_dialog_ok);
            			dialogButton.setBackgroundResource(R.drawable.small_button);
            			//** if OK button is clicked, close the custom dialog */
            			dialogButton.setOnClickListener(new OnClickListener() {
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

    	 
    
    

