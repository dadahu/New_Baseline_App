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

public class Tab1_Tools extends Fragment {
	
   

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //** Inflate the layout for this fragment */
			View view = inflater.inflate(R.layout.tab1_tools, container, false);
	        
		//** Define buttons.. */	
	        Button button_test1 = (Button) view.findViewById(R.id.button_test1);
	        Button button_test2 = (Button) view.findViewById(R.id.button_test2);
	        Button button_test3 = (Button) view.findViewById(R.id.button_test3);
	        Button button_test4 = (Button) view.findViewById(R.id.button_test4);
	        Button button_test5 = (Button) view.findViewById(R.id.button_test5);
	       
	    //** Set button image resources.. */    
	        Button button_test1_views= (Button)view.findViewById(R.id.button_test1);
	        button_test1_views.setBackgroundResource(R.drawable.button);
	        Button button_test2_views= (Button)view.findViewById(R.id.button_test2);
	        button_test2_views.setBackgroundResource(R.drawable.button);
	        Button button_test3_views= (Button)view.findViewById(R.id.button_test3);
	        button_test3_views.setBackgroundResource(R.drawable.button);
	        Button button_test4_views= (Button)view.findViewById(R.id.button_test4);
	        button_test4_views.setBackgroundResource(R.drawable.button);
	        Button button_test5_views= (Button)view.findViewById(R.id.button_test5);
	        button_test5_views.setBackgroundResource(R.drawable.button);
	        
	  
	      //** Define OnClick methods for buttons.. */
	        
	        button_test1.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                Activity activity = getActivity();
	                
	                if (activity != null) {
	                	//* add code here for 'Tweak's' section.. */ 
	                    Toast.makeText(activity, R.string.testbutton1, Toast.LENGTH_SHORT).show();
	                }
	            }
	            
	        });
	        button_test2.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                Activity activity = getActivity();
	                
	                if (activity != null) {
	                	//* add code here for 'Tweak's' section.. */ 
	                    Toast.makeText(activity, R.string.testbutton2, Toast.LENGTH_SHORT).show();
	                }
	            }
	            
	        });
	        button_test3.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                Activity activity = getActivity();
	                
	                if (activity != null) {
	                	//* add code here for 'Tweak's' section.. */ 
	                    Toast.makeText(activity, R.string.testbutton3, Toast.LENGTH_SHORT).show();
	                }
	            }
	            
	        });
	        button_test4.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                Activity activity = getActivity();
	                
	                if (activity != null) {
	                	//* add code here for 'Tweak's' section.. */ 
	                    Toast.makeText(activity, R.string.testbutton4, Toast.LENGTH_SHORT).show();
	                }
	            }
	            
	        });
	        button_test5.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                Activity activity = getActivity();
	                
	                if (activity != null) {
	                	//* add code here for 'Tweak's' section.. */ 
	                    Toast.makeText(activity, R.string.testbutton5, Toast.LENGTH_SHORT).show();
	                }
	            }
	            
	        });
	        return view;
	      
	    }
	
	}