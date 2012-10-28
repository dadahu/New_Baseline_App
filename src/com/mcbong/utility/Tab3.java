package com.mcbong.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class Tab3 extends Fragment {
	 
	 
	 
	 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	//** Inflate the layout for this fragment */
    		View view = inflater.inflate(R.layout.tab3, container, false);
    		
    		//** Define WebView component & Buttons,also load required WebSite.. */
    		WebView myWebView = (WebView) view.findViewById(R.id.webview);
    		myWebView.setWebViewClient(new WebViewClient());
    		myWebView.loadUrl("http://team-scrat.blogspot.co.uk");
    		Button button_check_webver = (Button) view.findViewById(R.id.button_check_webver);
    		button_check_webver.setBackgroundResource(R.drawable.button);
    
    		button_check_webver.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Activity activity = getActivity();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                 if (activity != null) {
                    try {
                    	
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
                        builder.setTitle(R.string.latest_version);
                        builder.setMessage(Html.fromHtml("<p align='center'>"+"<b>"+"<font color='green'>"+sb.toString()+"</font>"+"</b>"+"</center>"+"</p align>"+"<br>"));
                    } catch (MalformedURLException e) {
                        builder.setMessage(R.string.error_unsupported_p);
                    } catch (IOException e) {
                        builder.setMessage(R.string.error_no_internet);
                    }
                	
                	// Create the AlertDialog
 	                AlertDialog dialog = builder.create();
 	                dialog.show();
    
                }
            }
            
        });
    	
    	
        return view;
       }
    
    
    }

    	 
    
    

