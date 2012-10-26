package com.mcbong.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class Tab3 extends Fragment {
	 
	 
	 
	 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	//** Inflate the layout for this fragment */
    		View view = inflater.inflate(R.layout.tab3, container, false);
    		
    	final TextView tv = (TextView) view.findViewById(R.id.textView1);
    	
    	//** Define WebView component & Button,also load required WebSite.. */
    		WebView myWebView = (WebView) view.findViewById(R.id.webview);
    		myWebView.setWebViewClient(new WebViewClient());
    		myWebView.loadUrl("http://team-scrat.blogspot.co.uk");
    		//myWebView.loadUrl("http://mobile.dudamobile.com/site/team-scrat-xda_goodluckwith");
    		Button buttontext = (Button) view.findViewById(R.id.buttontext);
    	
    
        buttontext.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Activity activity = getActivity();
                
                if (activity != null) {
                    //Toast.makeText(activity, R.string.testbutton1, Toast.LENGTH_LONG).show();
         
                	try {
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
                        tv.setText(sb.toString());
                    } catch (MalformedURLException e) {
                        tv.setText(R.string.error_unsupported_p);
                    } catch (IOException e) {
                        tv.setText(R.string.error_no_internet);
                    }
                	
    	
    
                }
            }
            
        });
    	
    	
        return view;
       }
    
    
    }

    	 
    
    

