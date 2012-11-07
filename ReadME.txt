------------------------------------------------------------------------------------------------------
                        #######   New_Baseline_App [McBong-Utility] []   #######
------------------------------------------------------------------------------------------------------

		           This App is a MAJOR 'Work-In-Progress' ! 
 	[and will be updated and reconfigured hundreds more times before any kind of release]

------------------------------------------------------------------------------------------------------


Update 7/11/2012 ... Update no.7 ...

		> integrated update downloader into the update checker, so now its an 
		  all-in-one check and download,
		  changed URL's for test builds and test version.txt's to new remote location.
		  added roottools library to source for easy compilation from any device,
		  Created new Tab and renamed and reordered all Tabs,
		  also added options to download latest ADW launcher, NOVA launcher and
		  Go-launcher apk's from remote server.
		  changed the way custom_alert_dialog and custom_dialog behave when
		  'update' / 'download' is called......  alert_dialog.dismiss(); is called
		  when download/update starts.
		  edited the way downloads & updates are processed,
		  added initial deletion of requested download/update from SDcard/Download
		  if file exists, to stop multiple files being created.
		  added broadcast receiver to get Download_Complete call once the file
		  download is done
		  added instant call for download/update's package to be installed
		  directly after download completes....  alot less hassle being an all in
		  one function.
		  added downloads for Launchers and Themes held remotely,
		  all downloads will be notified when finished and will call the package
		  manager to install them automatically,
		  more layout changes, much nicer clean ui now,.
		  added more icons,
		  strings cleanup,
		  removed 'test buttons' from tab1_tools,
		  re-arranged ALL layouts, cleaned code etc ...
		  
-

Update 3/11/2012 ... Update no.6 ...

		> added update downloader, added proper online version checker,added 
		  compare both remote and local strings function and return if..
		  update is available for download,
		  added OnCreate getprop wireless adb value, cleaned up layouts, added …
		  more icons. added tablelayout for tab4. Changed versioning value.
		  added simple wireless adb on/off option, updated version.txt to reflect
		  build date,
		  
-

Update 30/10/2012 ...  Update no.5 ...

		> added complete functions for all active buttons, added confirmation dialogs for 	
		  reboot actions, 
		  minor layout and function changes,	
		  added new custom_dialog and new custom_alert_dialog layouts,
		  created second buttons xml to define button_pressed etc,
		  added shutdown option, reboot device option and reboot to recovery option,
		  fixed layout again, 
		  added current version dialog,
		  added some other graphics,
		  both 'custom_dialog' and 'custom_alert_dialog' have been created and
		  relevant resources configured.
		  all alert dialogs and builders have now all been replaced with dialog-
		  calling new layouts and styles. 
		  small buttons been added,
		  remote and local version strings changed, 
		  some other minor edits,
		  general cleanup and format.


-

Update 26/10/2012 ...  Update no.4 ...

		> Added Stericson's 'roottools' library to project for easy one time SU-
		  init and shell command pass throughs.
		  added some more test strings, implimented first 'recovery' fragment tab
		  option : 'Reboot'.
		  fixed Landscape tab fragment issue
		  added new strings, changed 'online' webview url, major code cleanup.

-


Update 25/10/2012 ...  [Part 2] Update no.3 ...

		> properly configured fragment tabs, changed version string again to fit,
		  added scrollview,  added webview for direct website access via tab,
		  configured mobile version of website and added URL to code, enabled
		  webview to intercept and handle any URL's called from within the webview
		  fragment tab,  another minor code clean as well ...

-


Update 25/10/2012 ...  [Part 1] Update no.3 ...
		
		> added proper 'onclick' method for buttons, created new button.png and
		  button_pressed.png, added button.xml for defining 'normal' and 'pressed'
		  images, renamed activity properly, changed version code to have date of
		  compile with version number,  small code clean.

-


Update 24/10/2012 ...  Update no.2 ...

		> Properly removed hardware 'Menu' and 'Back' button activities, added test buttons, 
		  added new button.png with shadow,other minor changes here and there

-


Update 18/10/2012 ...  Update no.1 ... [Initial Git commit]

		> Layout Partially Configured, configured App for Android 4.1+, 
		  removed hardware 'menu' key activity, added direct quit button, added short 'about' toast message,
		  configured initial app icon, configured initial manifest.	
	
-

				
					\\\.\\\\. End Of ReadMe File .///.////
