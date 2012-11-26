package com.mcbong.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeoutException;
import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.stericson.RootTools.CommandCapture;
import com.stericson.RootTools.RootTools;

public class Tab5_Online extends Fragment {

	protected static final TextView text = null;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// ** Inflate the layout for this fragment */
		final View view = inflater.inflate(R.layout.tab5_online, container,
				false);

		// ** Define WebView component & Buttons,also load required WebSite.. */
		final WebView myWebView = (WebView) view.findViewById(R.id.webview);
		myWebView.setWebViewClient(new WebViewClient());
		myWebView.loadUrl("http://team-scrat.blogspot.co.uk");
		myWebView.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					if (myWebView.canGoBack())
						myWebView.goBack();
					return true;
				}
				// ** process normally */
				return false;
			}
		});

		// * set up button image resources */
		Button button_check_webver = (Button) view
				.findViewById(R.id.button_check_webver);
			button_check_webver
				.setBackgroundResource(R.drawable.button);

		button_check_webver
		.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Activity activity = getActivity();
				if (activity != null) {
					try {
						// ** ..... */
						StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
						StrictMode.setThreadPolicy(policy);

						// ** Define a URL for the desired page */
						URL url = new URL(
								"http://dl.dropbox.com/u/18271886/mcb/Test_Builds/version.txt");

						// ** Read all the text within remote file */
						BufferedReader in = new BufferedReader(
								new InputStreamReader(url.openStream()));
						String str;
						StringBuilder sb = new StringBuilder(100);
						while ((str = in.readLine()) != null) {
							sb.append(str);
							// ** str is one line of text; readLine() strips the
							// newline character(s) */
						}
						in.close();

						// ** call custom dialog into view and set
						// characteristic's */
						final Dialog dialog = new Dialog(activity,
								R.style.Theme_Dialog_Translucent);
						dialog.getWindow();
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.custom_dialog);
						TextView title = (TextView) dialog
								.findViewById(R.id.custom_dialog_textview_title);
						title.setText(R.string.update_checker_header);
						TextView text = (TextView) dialog
								.findViewById(R.id.custom_dialog_textview);
						text.setText(sb.toString() + "");

						// ** Get 'installed version' and 'remote version'
						// strings to compare */
						String remote_version_string = sb.toString().toString();
						String current_version_string = getString(
								R.string.version).toString();

						if (current_version_string
								.equals(remote_version_string)) {
							Button dialogButton = (Button) dialog
									.findViewById(R.id.custom_dialog_ok);
							LayoutParams params = (RelativeLayout.LayoutParams) dialogButton
									.getLayoutParams();
							params.addRule(RelativeLayout.CENTER_IN_PARENT);
							dialogButton.setLayoutParams(params); // causes
							// layout
							// update
							Toast.makeText(activity, R.string.version_uptodate,
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(activity,
									R.string.version_notuptodate,
									Toast.LENGTH_SHORT).show();
							Button custom_dialog_update = (Button) dialog
									.findViewById(R.id.custom_dialog_update);
							custom_dialog_update.setVisibility(View.VISIBLE);
							dialog.setTitle(R.string.update_available);
							Button dialogButton2 = (Button) dialog
									.findViewById(R.id.custom_dialog_ok2);
							dialogButton2
							.setBackgroundResource(R.drawable.small_button);
							dialogButton2.setVisibility(View.VISIBLE);
							Button dialogButton = (Button) dialog
									.findViewById(R.id.custom_dialog_ok);
							dialogButton.setVisibility(View.INVISIBLE);
						}

						ImageView image = (ImageView) dialog
								.findViewById(R.id.custom_dialog_image);
						image.setImageResource(R.drawable.ic_launcher);

						// * set up button image resources */
						Button custom_dialog_update = (Button) dialog
								.findViewById(R.id.custom_dialog_update);
						custom_dialog_update
						.setBackgroundResource(R.drawable.small_button_update);
						// if OK button is clicked, close the custom dialog */
						custom_dialog_update
						.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								Activity activity = getActivity();

								// ** Remove any old downloads of
								// McBong-Utility from /Download folder
								// before downloading new version */
								CommandCapture command = new CommandCapture(
										0,
										"rm /sdcard/Download/McBong-Utility.apk");
								try {
									RootTools.getShell(true)
									.add(command)
									.waitForFinish();
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

								// ** Set up the 'Download Manager' and
								// Download latest version from server
								// ... */
								String url = "http://dl.dropbox.com/u/18271886/mcb/Test_Builds/McBong-Utility.apk";
								DownloadManager.Request request = new DownloadManager.Request(
										Uri.parse(url));
								request.setDescription("McBong Utility Updater");
								request.setTitle("McBong-Utility Update");

								if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
									request.allowScanningByMediaScanner();
									request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
								}
								request.setDestinationInExternalPublicDir(
										Environment.DIRECTORY_DOWNLOADS,
										"McBong-Utility.apk");

								// ** get download service and enqueue
								// file */
								DownloadManager manager = (DownloadManager) activity
										.getSystemService(Context.DOWNLOAD_SERVICE);
								manager.enqueue(request);
								Toast.makeText(activity,
										R.string.downloading,
										Toast.LENGTH_SHORT).show();
								dialog.dismiss();

								// ** Set up broadcast receiver to
								// detect when the download completes..
								// */
								BroadcastReceiver onComplete = new BroadcastReceiver() {
									public void onReceive(Context ctxt,
											Intent intent) {
										Toast.makeText(
												ctxt,
												R.string.installing_update,
												Toast.LENGTH_SHORT)
												.show();
										Intent install = new Intent(
												Intent.ACTION_VIEW);
										install.setDataAndType(
												Uri.fromFile(new File(
														Environment
														.getExternalStorageDirectory()
														+ "/Download/"
														+ "McBong-Utility.apk")),
												"application/vnd.android.package-archive");
										startActivity(install);
									}
								};
								activity.registerReceiver(
										onComplete,
										new IntentFilter(
												DownloadManager.ACTION_DOWNLOAD_COMPLETE));
							}
						});
						Button dialogButton = (Button) dialog
								.findViewById(R.id.custom_dialog_ok);
						dialogButton
						.setBackgroundResource(R.drawable.small_button);
						// ** if OK button is clicked, close the custom dialog
						// */
						dialogButton.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								dialog.dismiss();
							}
						});
						Button dialogButton2 = (Button) dialog
								.findViewById(R.id.custom_dialog_ok2);
						dialogButton2
						.setBackgroundResource(R.drawable.small_button);
						// ** if OK button is clicked, close the custom dialog
						// */
						dialogButton2.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								dialog.dismiss();
							}
						});

						dialog.show();
					} catch (MalformedURLException e) {
						Toast.makeText(activity, R.string.error_unsupported_p,
								Toast.LENGTH_SHORT).show();
					} catch (IOException e) {
						Toast.makeText(activity, R.string.error_no_internet,
								Toast.LENGTH_SHORT).show();
					}
				}
			}

		});
		return view;
	}
}
