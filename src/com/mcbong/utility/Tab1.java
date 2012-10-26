package com.mcbong.utility;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Tab1 extends Fragment {
	
   

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //** Inflate the layout for this fragment */
			View view = inflater.inflate(R.layout.tab1, container, false);
	        
		//** Define buttons.. */	
	        Button button1 = (Button) view.findViewById(R.id.button1);
	        Button button2 = (Button) view.findViewById(R.id.button2);
	        Button button3 = (Button) view.findViewById(R.id.button3);
	        Button button4 = (Button) view.findViewById(R.id.button4);
	        Button button5 = (Button) view.findViewById(R.id.button5);
	       
	    //** Set button image resources.. */    
	        Button button1_views= (Button)view.findViewById(R.id.button1);
	        button1_views.setBackgroundResource(R.drawable.button);
	        Button button2_views= (Button)view.findViewById(R.id.button2);
	        button2_views.setBackgroundResource(R.drawable.button);
	        Button button3_views= (Button)view.findViewById(R.id.button3);
	        button3_views.setBackgroundResource(R.drawable.button);
	        Button button4_views= (Button)view.findViewById(R.id.button4);
	        button4_views.setBackgroundResource(R.drawable.button);
	        Button button5_views= (Button)view.findViewById(R.id.button5);
	        button5_views.setBackgroundResource(R.drawable.button);
	        
	  
	      //** Define OnClick methods for buttons.. */
	        
	        button1.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                Activity activity = getActivity();
	                
	                if (activity != null) {
	                    Toast.makeText(activity, R.string.testbutton1, Toast.LENGTH_LONG).show();
	                }
	            }
	            
	        });
	        button2.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                Activity activity = getActivity();
	                
	                if (activity != null) {
	                    Toast.makeText(activity, R.string.testbutton2, Toast.LENGTH_LONG).show();
	                }
	            }
	            
	        });
	        button3.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                Activity activity = getActivity();
	                
	                if (activity != null) {
	                    Toast.makeText(activity, R.string.testbutton3, Toast.LENGTH_LONG).show();
	                }
	            }
	            
	        });
	        button4.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                Activity activity = getActivity();
	                
	                if (activity != null) {
	                    Toast.makeText(activity, R.string.testbutton4, Toast.LENGTH_LONG).show();
	                }
	            }
	            
	        });
	        button5.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                Activity activity = getActivity();
	                
	                if (activity != null) {
	                    Toast.makeText(activity, R.string.testbutton5, Toast.LENGTH_LONG).show();
	                }
	            }
	            
	        });
	        return view;
	      
	    }
	
	}