package com.mcbong.utility;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.stericson.RootTools.CommandCapture;
import com.stericson.RootTools.RootTools;

public class Tab4 extends Fragment {
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	 //** Inflate the layout for this fragment */
    		View view = inflater.inflate(R.layout.tab4, container, false);
    		
    		Button button6_views= (Button)view.findViewById(R.id.button6);
	        button6_views.setBackgroundResource(R.drawable.button);
	        
    		Button button6 = (Button) view.findViewById(R.id.button6);
    		button6.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Activity activity = getActivity();
                    if (activity != null) {
                     //** create function here.. */
                    	 //Toast.makeText(activity, R.string.testbutton1, Toast.LENGTH_LONG).show();
                    
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
                    	
                    }}
                
                
                    });
    		return view;
    	}
}

    