package com.mcbong.utility;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Tab3 extends Fragment {
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	View view = inflater.inflate(R.layout.tab3, container, false);
    	
    	// Define WebView component & load required WebSite..
    	WebView myWebView = (WebView) view.findViewById(R.id.webview);
    	myWebView.setWebViewClient(new WebViewClient());
    	//myWebView.loadUrl("http://team-scrat-xda.goodluckwith.us/");
    	myWebView.loadUrl("http://mobile.dudamobile.com/site/team-scrat-xda_goodluckwith");
    	return view;
    	//return inflater.inflate(R.layout.tab3, container, false);
    }
    
    }
