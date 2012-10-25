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
        // Inflate the layout for this fragment
		 //return inflater.inflate(R.layout.tab1, container, false);
		   View view = inflater.inflate(R.layout.tab1, container, false);
	        
	        // This is how you access your layout views. Notice how we call the findViewById() method
	        // on our View directly. There is no method called findViewById() defined on Fragments like
	        // there is in an Activity.
	        Button button1 = (Button) view.findViewById(R.id.button1);
	        Button button2 = (Button) view.findViewById(R.id.button2);
	        Button button3 = (Button) view.findViewById(R.id.button3);
	        Button button4 = (Button) view.findViewById(R.id.button4);
	        
	        Button button1_views= (Button)view.findViewById(R.id.button1);
	        button1_views.setBackgroundResource(R.drawable.button);
	        Button button2_views= (Button)view.findViewById(R.id.button2);
	        button2_views.setBackgroundResource(R.drawable.button);
	        Button button3_views= (Button)view.findViewById(R.id.button3);
	        button3_views.setBackgroundResource(R.drawable.button);
	        Button button4_views= (Button)view.findViewById(R.id.button4);
	        button4_views.setBackgroundResource(R.drawable.button);
	        
	        // A simple OnClickListener for our button. You can see here how a Fragment can encapsulate
	        // logic and views to build out re-usable Activity components.
	        button1.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                Activity activity = getActivity();
	                
	                if (activity != null) {
	                    Toast.makeText(activity, R.string.testbutton1, Toast.LENGTH_LONG).show();
	                }
	            }
	            
	        });
	        button2.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                Activity activity = getActivity();
	                
	                if (activity != null) {
	                    Toast.makeText(activity, R.string.testbutton2, Toast.LENGTH_LONG).show();
	                }
	            }
	            
	        });
	        button3.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                Activity activity = getActivity();
	                
	                if (activity != null) {
	                    Toast.makeText(activity, R.string.testbutton3, Toast.LENGTH_LONG).show();
	                }
	            }
	            
	        });
	        button4.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                Activity activity = getActivity();
	                
	                if (activity != null) {
	                    Toast.makeText(activity, R.string.testbutton4, Toast.LENGTH_LONG).show();
	                }
	            }
	            
	        });
	        return view;
	      
	    }

		 
		 
		 
		// if (container == null) {
		        //...
		   //     return null;
		  //  }

		    //LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.tab1,
		     //               container, false);
//
		    // note that we're looking for a button with id="@+id/button1" in your inflated layout
		    // Naturally, this can be any View; it doesn't have to be a button
		  //  Button mButton = (Button) mLinearLayout.findViewById(R.id.button1);
		   // mButton.setOnClickListener(new OnClickListener() {
		    //    @Override
		    //    public void onClick(View v) {
		            // here you set what you want to do when user clicks your button,
		            // e.g. launch a new activity
		        //	Toast.makeText(Tab1.this, R.string.testbutton, Toast.LENGTH_LONG).show();
		        	
		      //  }
		//    });

		    // after you've done all your manipulation, return your layout to be shown
		  //  return mLinearLayout;
		
		
		 

       
	}
       
    

 


