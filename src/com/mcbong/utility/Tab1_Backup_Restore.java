package com.mcbong.utility;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.stericson.RootTools.CommandCapture;
import com.stericson.RootTools.RootTools;

public class Tab1_Backup_Restore extends Fragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// ** Inflate the layout for this fragment */
		View view = inflater.inflate(R.layout.tab1_backup_restore, container,
				false);

		// ** Define buttons and set image resource .. */
		Button button_backup_contacts_and_calls = (Button) view
				.findViewById(R.id.button_backup_contacts_and_calls);
		button_backup_contacts_and_calls
		.setBackgroundResource(R.drawable.button);
		Button button_backup_text_messages = (Button) view
				.findViewById(R.id.button_backup_text_messages);
		button_backup_text_messages.setBackgroundResource(R.drawable.button);
		Button button_backup_browser_bookmarks = (Button) view
				.findViewById(R.id.button_backup_browser_bookmarks);
		button_backup_browser_bookmarks
		.setBackgroundResource(R.drawable.button);
		Button button_restore_contacts_and_calls = (Button) view
				.findViewById(R.id.button_restore_contacts_and_calls);
		button_restore_contacts_and_calls
		.setBackgroundResource(R.drawable.button);
		Button button_restore_text_messages = (Button) view
				.findViewById(R.id.button_restore_text_messages);
		button_restore_text_messages.setBackgroundResource(R.drawable.button);
		Button button_restore_browser_bookmarks = (Button) view
				.findViewById(R.id.button_restore_browser_bookmarks);
		button_restore_browser_bookmarks
		.setBackgroundResource(R.drawable.button);

		button_backup_contacts_and_calls
		.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Activity activity = getActivity();

				if (activity != null) {

					// ** call custom dialog into view and set
					// characteristic's */
					final Dialog alert_dialog = new Dialog(activity,
							R.style.Theme_Dialog_Translucent);
					alert_dialog.getWindow();
					alert_dialog
					.requestWindowFeature(Window.FEATURE_NO_TITLE);
					alert_dialog
					.setContentView(R.layout.custom_alert_dialog);
					TextView title = (TextView) alert_dialog
							.findViewById(R.id.custom_alert_dialog_textview_title);
					title.setText(R.string.backup_contacts_and_calls);
					TextView alert_text = (TextView) alert_dialog
							.findViewById(R.id.custom_alert_dialog_textview);
					alert_text.setText(R.string.confirm);
					ImageView image = (ImageView) alert_dialog
							.findViewById(R.id.custom_alert_dialog_image);
					image.setImageResource(R.drawable.icon_contacts_and_calls);

					// * set up custom dialog button image resources */
					Button custom_alert_dialog_ok = (Button) alert_dialog
							.findViewById(R.id.custom_alert_dialog_ok);
					custom_alert_dialog_ok
					.setBackgroundResource(R.drawable.small_button);

					// ** if custom dialog OK button is clicked, execute
					// the shell commands through root-tools */
					custom_alert_dialog_ok
					.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							Activity activity = getActivity();

							alert_dialog.dismiss();

							Toast.makeText(
									activity,
									R.string.backing_up_contacts_and_calls,
									Toast.LENGTH_SHORT).show();

							// ** Remove old contacts and
							// call-log backups before making
							// the
							// new backups */
							CommandCapture command = new CommandCapture(
									0,
									"mkdir /sdcard/.mcb/",
									"mkdir /sdcard/.mcb/.cb",
									"rm /sdcard/.mcb/.cb/c",
									"rm /sdcard/.mcb/.cb/cj",
									"cp -p /data/data/com.android.providers.contacts/databases/contacts2.db /sdcard/.mcb/.cb/c",
									"cp -p /data/data/com.android.providers.contacts/databases/contacts2.db-journal /sdcard/.mcb/.cb/cj");
							try {
								RootTools.getShell(true)
								.add(command)
								.waitForFinish();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch
								// block
								Toast.makeText(activity,
										R.string.backup_failed,
										Toast.LENGTH_SHORT)
										.show();
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch
								// block
								Toast.makeText(activity,
										R.string.backup_failed,
										Toast.LENGTH_SHORT)
										.show();
								e.printStackTrace();
							} catch (TimeoutException e) {
								// TODO Auto-generated catch
								// block
								Toast.makeText(activity,
										R.string.backup_failed,
										Toast.LENGTH_SHORT)
										.show();
								e.printStackTrace();
							}
							// alert_dialog.dismiss();

							// ** Check to see if database
							// backup was ACTUALLY made .. */
							String fileUrl = "/.mcb/.cb/c";
							String file = android.os.Environment
									.getExternalStorageDirectory()
									.getPath()
									+ fileUrl;
							File contacts = new File(file);

							if (contacts.exists()) {
								Toast.makeText(
										activity,
										R.string.backup_complete,
										Toast.LENGTH_SHORT)
										.show();
							} else {
								Toast.makeText(activity,
										R.string.backup_failed,
										Toast.LENGTH_SHORT)
										.show();
							}
						}
					});
					Button custom_alert_dialog_cancel = (Button) alert_dialog
							.findViewById(R.id.custom_alert_dialog_cancel);
					custom_alert_dialog_cancel
					.setBackgroundResource(R.drawable.small_button);
					// if custom dialog cancel button is clicked, close
					// the custom dialog */
					custom_alert_dialog_cancel
					.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							alert_dialog.dismiss();
						}
					});
					alert_dialog.show();
				}
			}
		});
		button_backup_text_messages.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Activity activity = getActivity();

				if (activity != null) {

					// ** call custom dialog into view and set characteristic's
					// */
					final Dialog alert_dialog = new Dialog(activity,
							R.style.Theme_Dialog_Translucent);
					alert_dialog.getWindow();
					alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					alert_dialog.setContentView(R.layout.custom_alert_dialog);
					TextView title = (TextView) alert_dialog
							.findViewById(R.id.custom_alert_dialog_textview_title);
					title.setText(R.string.backup_text_messages);
					TextView alert_text = (TextView) alert_dialog
							.findViewById(R.id.custom_alert_dialog_textview);
					alert_text.setText(R.string.confirm);
					ImageView image = (ImageView) alert_dialog
							.findViewById(R.id.custom_alert_dialog_image);
					image.setImageResource(R.drawable.icon_messages);

					// * set up button image resources */
					Button custom_alert_dialog_ok = (Button) alert_dialog
							.findViewById(R.id.custom_alert_dialog_ok);
					custom_alert_dialog_ok
					.setBackgroundResource(R.drawable.small_button);

					// ** if custom dialog OK button is clicked, execute the
					// shell commands through root-tools */
					custom_alert_dialog_ok
					.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							Activity activity = getActivity();

							alert_dialog.dismiss();

							Toast.makeText(activity,
									R.string.backing_up_text_messages,
									Toast.LENGTH_SHORT).show();

							// /** Remove old messages backups before
							// making the new
							// backups */
							CommandCapture command = new CommandCapture(
									0,
									"mkdir /sdcard/.mcb/",
									"mkdir /sdcard/.mcb/.mb",
									"rm /sdcard/mcb/mb/m",
									"rm /sdcard/.mcb/.mb/mj",
									"cp /data/data/com.android.providers.telephony/databases/mmssms.db /sdcard/.mcb/.mb/m",
									"cp /data/data/com.android.providers.telephony/databases/mmssms.db-journal /sdcard/.mcb/.mb/mj");
							try {
								RootTools.getShell(true).add(command)
								.waitForFinish();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								Toast.makeText(activity,
										R.string.backup_failed,
										Toast.LENGTH_SHORT).show();
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								Toast.makeText(activity,
										R.string.backup_failed,
										Toast.LENGTH_SHORT).show();
								e.printStackTrace();
							} catch (TimeoutException e) {
								// TODO Auto-generated catch block
								Toast.makeText(activity,
										R.string.backup_failed,
										Toast.LENGTH_SHORT).show();
								e.printStackTrace();
							}
							// alert_dialog.dismiss();

							// ** Check to see if database backup was
							// ACTUALLY made .. */
							String fileUrl = "/.mcb/.mb/m";
							String file = android.os.Environment
									.getExternalStorageDirectory()
									.getPath()
									+ fileUrl;
							File messages = new File(file);

							if (messages.exists()) {
								Toast.makeText(activity,
										R.string.backup_complete,
										Toast.LENGTH_SHORT).show();
							} else {
								Toast.makeText(activity,
										R.string.backup_failed,
										Toast.LENGTH_SHORT).show();
							}
						}
					});
					Button custom_alert_dialog_cancel = (Button) alert_dialog
							.findViewById(R.id.custom_alert_dialog_cancel);
					custom_alert_dialog_cancel
					.setBackgroundResource(R.drawable.small_button);
					// if cancel button is clicked, close the custom dialog */
					custom_alert_dialog_cancel
					.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							alert_dialog.dismiss();
						}
					});
					alert_dialog.show();
				}
			}
		});
		button_backup_browser_bookmarks
		.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Activity activity = getActivity();

				if (activity != null) {

					// ** call custom dialog into view and set
					// characteristic's
					// */
					final Dialog alert_dialog = new Dialog(activity,
							R.style.Theme_Dialog_Translucent);
					alert_dialog.getWindow();
					alert_dialog
					.requestWindowFeature(Window.FEATURE_NO_TITLE);
					alert_dialog
					.setContentView(R.layout.custom_alert_dialog);
					TextView title = (TextView) alert_dialog
							.findViewById(R.id.custom_alert_dialog_textview_title);
					title.setText(R.string.backup_browser_bookmarks);
					TextView alert_text = (TextView) alert_dialog
							.findViewById(R.id.custom_alert_dialog_textview);
					alert_text.setText(R.string.confirm);
					ImageView image = (ImageView) alert_dialog
							.findViewById(R.id.custom_alert_dialog_image);
					image.setImageResource(R.drawable.icon_browser);

					// * set up button image resources */
					Button custom_alert_dialog_ok = (Button) alert_dialog
							.findViewById(R.id.custom_alert_dialog_ok);
					custom_alert_dialog_ok
					.setBackgroundResource(R.drawable.small_button);

					// ** if custom dialog OK button is clicked, execute
					// the
					// shell commands through root-tools */
					custom_alert_dialog_ok
					.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							Activity activity = getActivity();

							alert_dialog.dismiss();

							Toast.makeText(
									activity,
									R.string.backing_up_browser_bookmarks,
									Toast.LENGTH_SHORT).show();

							// /** Remove old messages backups
							// before making the
							// new
							// backups */
							CommandCapture command = new CommandCapture(
									0, "mkdir /sdcard/.mcb/",
									"mkdir /sdcard/.mcb/.bb",
									"rm /sdcard/mcb/bb/b",
									"cp /data/data/com.android.browser/databases/browser2.db /sdcard/.mcb/.bb/b",
									"cp /data/data/com.android.browser/databases/browser2.db-shm /sdcard/.mcb/.bb/b-shm",
									"cp /data/data/com.android.browser/databases/browser2.db-wal /sdcard/.mcb/.bb/b-wal",
									"cp /data/data/com.android.browser/databases/webview.db /sdcard/.mcb/.bb/b-wv",
									"cp /data/data/com.android.browser/databases/webview.db-jounal /sdcard/.mcb/.bb/b-wvj");
							try {
								RootTools.getShell(true)
								.add(command)
								.waitForFinish();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch
								// block
								Toast.makeText(activity,
										R.string.backup_failed,
										Toast.LENGTH_SHORT)
										.show();
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch
								// block
								Toast.makeText(activity,
										R.string.backup_failed,
										Toast.LENGTH_SHORT)
										.show();
								e.printStackTrace();
							} catch (TimeoutException e) {
								// TODO Auto-generated catch
								// block
								Toast.makeText(activity,
										R.string.backup_failed,
										Toast.LENGTH_SHORT)
										.show();
								e.printStackTrace();
							}
							// alert_dialog.dismiss();

							// ** Check to see if database
							// backup was
							// ACTUALLY made .. */
							String fileUrl = "/.mcb/.bb/b";
							String file = android.os.Environment
									.getExternalStorageDirectory()
									.getPath()
									+ fileUrl;
							File messages = new File(file);

							if (messages.exists()) {
								Toast.makeText(
										activity,
										R.string.backup_complete,
										Toast.LENGTH_SHORT)
										.show();
							} else {
								Toast.makeText(activity,
										R.string.backup_failed,
										Toast.LENGTH_SHORT)
										.show();
							}
						}
					});
					Button custom_alert_dialog_cancel = (Button) alert_dialog
							.findViewById(R.id.custom_alert_dialog_cancel);
					custom_alert_dialog_cancel
					.setBackgroundResource(R.drawable.small_button);
					// if cancel button is clicked, close the custom
					// dialog */
					custom_alert_dialog_cancel
					.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							alert_dialog.dismiss();
						}
					});
					alert_dialog.show();
				}
			}
		});
		button_restore_contacts_and_calls
		.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Activity activity = getActivity();

				if (activity != null) {

					// ** Check to see if database backups are ACTUALLY
					// there to be restored from before continuing .. */
					String fileUrl = "/.mcb/.cb/c";
					String file = android.os.Environment
							.getExternalStorageDirectory().getPath()
							+ fileUrl;
					File contacts_r = new File(file);

					if (contacts_r.exists()) {

						// ** call custom dialog into view and set
						// characteristic's */
						final Dialog alert_dialog = new Dialog(
								activity,
								R.style.Theme_Dialog_Translucent);
						alert_dialog.getWindow();
						alert_dialog
						.requestWindowFeature(Window.FEATURE_NO_TITLE);
						alert_dialog
						.setContentView(R.layout.custom_alert_dialog);
						TextView title = (TextView) alert_dialog
								.findViewById(R.id.custom_alert_dialog_textview_title);
						title.setText(R.string.restore_contacts_and_calls);
						TextView alert_text = (TextView) alert_dialog
								.findViewById(R.id.custom_alert_dialog_textview);
						alert_text.setText(R.string.confirm);
						ImageView image = (ImageView) alert_dialog
								.findViewById(R.id.custom_alert_dialog_image);
						image.setImageResource(R.drawable.icon_contacts_and_calls);

						// * set up button image resources */
						Button custom_alert_dialog_ok = (Button) alert_dialog
								.findViewById(R.id.custom_alert_dialog_ok);
						custom_alert_dialog_ok
						.setBackgroundResource(R.drawable.small_button);

						// ** if custom dialog OK button is clicked,
						// execute the shell commands through root-tools
						// */
						custom_alert_dialog_ok
						.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								Activity activity = getActivity();

								alert_dialog.dismiss();

								Toast.makeText(
										activity,
										R.string.restoring_contacts_and_calls,
										Toast.LENGTH_SHORT)
										.show();

								// /** Remove current contacts
								// and call-logs databases
								// before
								// restoring the created backups
								// */
								CommandCapture command = new CommandCapture(
										0,
										"cp -p /sdcard/.mcb/.cb/c /data/data/com.android.providers.contacts/databases/contacts2.db",
										"cp -p /sdcard/.mcb/.cb/cj /data/data/com.android.providers.contacts/databases/contacts2.db-journal");
								try {
									RootTools.getShell(true)
									.add(command)
									.waitForFinish();
									Toast.makeText(
											activity,
											R.string.restore_complete,
											Toast.LENGTH_SHORT)
											.show();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch
									// block
									Toast.makeText(
											activity,
											R.string.restore_failed,
											Toast.LENGTH_SHORT)
											.show();
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch
									// block
									Toast.makeText(
											activity,
											R.string.restore_failed,
											Toast.LENGTH_SHORT)
											.show();
									e.printStackTrace();
								} catch (TimeoutException e) {
									// TODO Auto-generated catch
									// block
									Toast.makeText(
											activity,
											R.string.restore_failed,
											Toast.LENGTH_SHORT)
											.show();
									e.printStackTrace();
								}
								// alert_dialog.dismiss();
							}
						});
						Button custom_alert_dialog_cancel = (Button) alert_dialog
								.findViewById(R.id.custom_alert_dialog_cancel);
						custom_alert_dialog_cancel
						.setBackgroundResource(R.drawable.small_button);
						// if cancel button is clicked, close the custom
						// dialog */
						custom_alert_dialog_cancel
						.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								alert_dialog.dismiss();
							}
						});
						alert_dialog.show();

						// ** If NO database backups are found then warn
						// the user and stop the restore process .. */
					} else {
						Toast.makeText(activity,
								R.string.no_backup_found,
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		button_restore_text_messages.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Activity activity = getActivity();

				if (activity != null) {

					// ** Check to see if database backups are ACTUALLY there to
					// be restored from before continuing .. */
					String fileUrl = "/.mcb/.mb/m";
					String file = android.os.Environment
							.getExternalStorageDirectory().getPath() + fileUrl;
					File messages_r = new File(file);

					if (messages_r.exists()) {

						// ** call custom dialog into view and set
						// characteristic's */
						final Dialog alert_dialog = new Dialog(activity,
								R.style.Theme_Dialog_Translucent);
						alert_dialog.getWindow();
						alert_dialog
						.requestWindowFeature(Window.FEATURE_NO_TITLE);
						alert_dialog
						.setContentView(R.layout.custom_alert_dialog);
						TextView title = (TextView) alert_dialog
								.findViewById(R.id.custom_alert_dialog_textview_title);
						title.setText(R.string.restore_text_messages);
						TextView alert_text = (TextView) alert_dialog
								.findViewById(R.id.custom_alert_dialog_textview);
						alert_text.setText(R.string.confirm);
						ImageView image = (ImageView) alert_dialog
								.findViewById(R.id.custom_alert_dialog_image);
						image.setImageResource(R.drawable.icon_messages);

						// * set up button image resources */
						Button custom_alert_dialog_ok = (Button) alert_dialog
								.findViewById(R.id.custom_alert_dialog_ok);
						custom_alert_dialog_ok
						.setBackgroundResource(R.drawable.small_button);

						// ** if custom dialog OK button is clicked, execute the
						// shell commands through root-tools */
						custom_alert_dialog_ok
						.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								Activity activity = getActivity();

								alert_dialog.dismiss();

								Toast.makeText(
										activity,
										R.string.restoring_text_messages,
										Toast.LENGTH_SHORT).show();

								// /** Remove current messages databases
								// from /data/data/... before restoring
								// the created backups */
								CommandCapture command = new CommandCapture(
										0,
										"cp -p /sdcard/.mcb/.mb/m /data/data/com.android.providers.telephony/databases/mmssms.db",
										"cp -p /sdcard/.mcb/.mb/mj /data/data/com.android.providers.telephony/databases/mmssms.db-journal");
								try {
									RootTools.getShell(true)
									.add(command)
									.waitForFinish();
									Toast.makeText(activity,
											R.string.restore_complete,
											Toast.LENGTH_SHORT).show();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									Toast.makeText(activity,
											R.string.restore_failed,
											Toast.LENGTH_SHORT).show();
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									Toast.makeText(activity,
											R.string.restore_failed,
											Toast.LENGTH_SHORT).show();
									e.printStackTrace();
								} catch (TimeoutException e) {
									// TODO Auto-generated catch block
									Toast.makeText(activity,
											R.string.restore_failed,
											Toast.LENGTH_SHORT).show();
									e.printStackTrace();
								}
								// alert_dialog.dismiss();
							}
						});
						Button custom_alert_dialog_cancel = (Button) alert_dialog
								.findViewById(R.id.custom_alert_dialog_cancel);
						custom_alert_dialog_cancel
						.setBackgroundResource(R.drawable.small_button);
						// if cancel button is clicked, close the custom dialog
						// */
						custom_alert_dialog_cancel
						.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								alert_dialog.dismiss();
							}
						});
						alert_dialog.show();

						// ** If NO database backups are found then warn the
						// user and stop the restore process .. */
					} else {
						Toast.makeText(activity, R.string.no_backup_found,
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		button_restore_browser_bookmarks
		.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Activity activity = getActivity();

				if (activity != null) {

					// ** Check to see if database backups are ACTUALLY
					// there to
					// be restored from before continuing .. */
					String fileUrl = "/.mcb/.bb/b";
					String file = android.os.Environment
							.getExternalStorageDirectory().getPath()
							+ fileUrl;
					File messages_r = new File(file);

					if (messages_r.exists()) {

						// ** call custom dialog into view and set
						// characteristic's */
						final Dialog alert_dialog = new Dialog(
								activity,
								R.style.Theme_Dialog_Translucent);
						alert_dialog.getWindow();
						alert_dialog
						.requestWindowFeature(Window.FEATURE_NO_TITLE);
						alert_dialog
						.setContentView(R.layout.custom_alert_dialog);
						TextView title = (TextView) alert_dialog
								.findViewById(R.id.custom_alert_dialog_textview_title);
						title.setText(R.string.restore_browser_bookmarks);
						TextView alert_text = (TextView) alert_dialog
								.findViewById(R.id.custom_alert_dialog_textview);
						alert_text.setText(R.string.confirm);
						ImageView image = (ImageView) alert_dialog
								.findViewById(R.id.custom_alert_dialog_image);
						image.setImageResource(R.drawable.icon_browser);

						// * set up button image resources */
						Button custom_alert_dialog_ok = (Button) alert_dialog
								.findViewById(R.id.custom_alert_dialog_ok);
						custom_alert_dialog_ok
						.setBackgroundResource(R.drawable.small_button);

						// ** if custom dialog OK button is clicked,
						// execute the
						// shell commands through root-tools */
						custom_alert_dialog_ok
						.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								Activity activity = getActivity();

								alert_dialog.dismiss();

								Toast.makeText(
										activity,
										R.string.restoring__browser_bookmarks,
										Toast.LENGTH_SHORT)
										.show();

								// /** Remove current messages
								// databases
								// from /data/data/... before
								// restoring
								// the created backups */
								CommandCapture command = new CommandCapture(
										0,
										"cp -p /sdcard/.mcb/.bb/b /data/data/com.android.browser/databases/browser2.db",
										"cp -p /sdcard/.mcb/.bb/b-shm /data/data/com.android.browser/databases/browser2.db-shm",
										"cp -p /sdcard/.mcb/.bb/b-wal /data/data/com.android.browser/databases/browser2.db-wal",
										"cp -p /sdcard/.mcb/.bb/b-wv /data/data/com.android.browser/databases/webview.db",
										"cp -p /sdcard/.mcb/.bb/b-wvj /data/data/com.android.browser/databases/webview.db-jounal");
								try {
									RootTools.getShell(true)
									.add(command)
									.waitForFinish();
									Toast.makeText(
											activity,
											R.string.restore_complete,
											Toast.LENGTH_SHORT)
											.show();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch
									// block
									Toast.makeText(
											activity,
											R.string.restore_failed,
											Toast.LENGTH_SHORT)
											.show();
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch
									// block
									Toast.makeText(
											activity,
											R.string.restore_failed,
											Toast.LENGTH_SHORT)
											.show();
									e.printStackTrace();
								} catch (TimeoutException e) {
									// TODO Auto-generated catch
									// block
									Toast.makeText(
											activity,
											R.string.restore_failed,
											Toast.LENGTH_SHORT)
											.show();
									e.printStackTrace();
								}
								// alert_dialog.dismiss();
							}
						});
						Button custom_alert_dialog_cancel = (Button) alert_dialog
								.findViewById(R.id.custom_alert_dialog_cancel);
						custom_alert_dialog_cancel
						.setBackgroundResource(R.drawable.small_button);
						// if cancel button is clicked, close the custom
						// dialog
						// */
						custom_alert_dialog_cancel
						.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								alert_dialog.dismiss();
							}
						});
						alert_dialog.show();

						// ** If NO database backups are found then warn
						// the
						// user and stop the restore process .. */
					} else {
						Toast.makeText(activity,
								R.string.no_backup_found,
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		return view;
	}
}
