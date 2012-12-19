
package com.mcbong.utility;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.stericson.RootTools.CommandCapture;
import com.stericson.RootTools.RootTools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeoutException;

public class Tab1_Backup_Restore extends Fragment {

    public final String sd = android.os.Environment.getExternalStorageDirectory().getPath();
    public final String dirUrl_c = "/.mcb/.c";
    public final String dirUrl_b = "/.mcb/.b";
    public final String dirUrl_m = "/.mcb/.m";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // ** Inflate the layout for this fragment */
        final View view = inflater.inflate(R.layout.tab1_backup_restore, container, false);
        final Activity activity = getActivity();
        // ** Define buttons and set image resource .. */
        final ImageButton imagebutton_backup_all = (ImageButton) view
                .findViewById(R.id.imagebutton_backup_all);
        final ImageButton imagebutton_restore_all = (ImageButton) view
                .findViewById(R.id.imagebutton_restore_all);
        final ImageButton button_reboot = (ImageButton) view.findViewById(R.id.button_reboot);
        final Button button_backup_contacts_and_calls = (Button) view
                .findViewById(R.id.button_backup_contacts_and_calls);
        button_backup_contacts_and_calls.setBackgroundResource(R.drawable.button);
        final Button button_backup_text_messages = (Button) view
                .findViewById(R.id.button_backup_text_messages);
        button_backup_text_messages.setBackgroundResource(R.drawable.button);
        final Button button_backup_browser_bookmarks = (Button) view
                .findViewById(R.id.button_backup_browser_bookmarks);
        button_backup_browser_bookmarks.setBackgroundResource(R.drawable.button);
        final Button button_restore_contacts_and_calls = (Button) view
                .findViewById(R.id.button_restore_contacts_and_calls);
        button_restore_contacts_and_calls.setBackgroundResource(R.drawable.button);
        final Button button_restore_text_messages = (Button) view
                .findViewById(R.id.button_restore_text_messages);
        button_restore_text_messages.setBackgroundResource(R.drawable.button);
        final Button button_restore_browser_bookmarks = (Button) view
                .findViewById(R.id.button_restore_browser_bookmarks);
        button_restore_browser_bookmarks.setBackgroundResource(R.drawable.button);

        // ** Backup functions ..

        button_backup_contacts_and_calls.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // ** call custom dialog into view and set
                // characteristic's */
                final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
                alert_dialog.getWindow();
                alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alert_dialog.setContentView(R.layout.custom_alert_dialog);
                final TextView title = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview_title);
                title.setText(R.string.backup_contacts_and_calls);
                final TextView alert_text = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview);
                alert_text.setText(R.string.confirm);
                final ImageView image = (ImageView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_image);
                image.setImageResource(R.drawable.icon_contacts_and_calls);

                // * set up custom dialog button image resources */
                final Button custom_alert_dialog_ok = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_ok);
                custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {

                        alert_dialog.dismiss();

                        Toast.makeText(activity, R.string.backing_up_contacts_and_calls,
                                Toast.LENGTH_LONG).show();

                        // ** Call backup_contacts method to do the backup work
                        backup_contacts(activity);
                    }

                });
                final Button custom_alert_dialog_cancel = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_cancel);
                custom_alert_dialog_cancel.setBackgroundResource(R.drawable.small_button);
                // if custom dialog cancel button is clicked, close
                // the custom dialog */
                custom_alert_dialog_cancel.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        alert_dialog.dismiss();
                    }
                });
                alert_dialog.show();
            }
        });
        button_backup_text_messages.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // ** call custom dialog into view and set characteristic's
                // */
                final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
                alert_dialog.getWindow();
                alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alert_dialog.setContentView(R.layout.custom_alert_dialog);
                final TextView title = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview_title);
                title.setText(R.string.backup_text_messages);
                final TextView alert_text = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview);
                alert_text.setText(R.string.confirm);
                final ImageView image = (ImageView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_image);
                image.setImageResource(R.drawable.icon_messages);

                // * set up button image resources */
                final Button custom_alert_dialog_ok = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_ok);
                custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                // ** if custom dialog OK button is clicked, execute the
                // shell commands through root-tools */
                custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {

                        alert_dialog.dismiss();

                        Toast.makeText(activity, R.string.backing_up_text_messages,
                                Toast.LENGTH_LONG).show();

                        // ** Call backup_messages method to do the backup work
                        backup_messages(activity);
                    }
                });
                final Button custom_alert_dialog_cancel = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_cancel);
                custom_alert_dialog_cancel.setBackgroundResource(R.drawable.small_button);
                // if cancel button is clicked, close the custom dialog */
                custom_alert_dialog_cancel.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        alert_dialog.dismiss();
                    }
                });
                alert_dialog.show();
            }
        });
        button_backup_browser_bookmarks.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // ** call custom dialog into view and set
                // characteristic's
                // */
                final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
                alert_dialog.getWindow();
                alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alert_dialog.setContentView(R.layout.custom_alert_dialog);
                final TextView title = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview_title);
                title.setText(R.string.backup_browser_bookmarks);
                final TextView alert_text = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview);
                alert_text.setText(R.string.confirm);
                final ImageView image = (ImageView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_image);
                image.setImageResource(R.drawable.icon_browser);

                // * set up button image resources */
                final Button custom_alert_dialog_ok = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_ok);
                custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                // ** if custom dialog OK button is clicked, execute
                // the
                // shell commands through root-tools */
                custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {

                        alert_dialog.dismiss();

                        Toast.makeText(activity, R.string.backing_up_browser_bookmarks,
                                Toast.LENGTH_LONG).show();
                        // ** Call backup_browser method to do the backup work
                        backup_browser(activity);
                    }
                });
                final Button custom_alert_dialog_cancel = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_cancel);
                custom_alert_dialog_cancel.setBackgroundResource(R.drawable.small_button);
                // if cancel button is clicked, close the custom
                // dialog */
                custom_alert_dialog_cancel.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        alert_dialog.dismiss();
                    }
                });
                alert_dialog.show();
            }
        });

        imagebutton_backup_all.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // ** call custom dialog into view and set
                // characteristic's */
                // ** call custom dialog into view and set characteristic's
                // */
                final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
                alert_dialog.getWindow();
                alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alert_dialog.setContentView(R.layout.custom_alert_dialog);
                final TextView title = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview_title);
                title.setText(R.string.backup_all);
                final TextView alert_text = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview);
                alert_text.setText(R.string.confirm);
                final ImageView image = (ImageView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_image);
                image.setImageResource(R.drawable.on);

                // * set up button image resources */
                final Button custom_alert_dialog_ok = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_ok);
                custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                // ** if custom dialog OK button is clicked, execute the
                // shell commands through root-tools */
                custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {

                        alert_dialog.dismiss();

                        Toast.makeText(activity, R.string.backing_up_all, Toast.LENGTH_LONG).show();

                        // ** Call all three backup methods with try/catch

                        try {
                            backup_contacts(activity);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            Toast.makeText(activity, R.string.backup_contacts_failed,
                                    Toast.LENGTH_LONG).show();
                        }
                        try {
                            backup_messages(activity);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            Toast.makeText(activity, R.string.backup_messages_failed,
                                    Toast.LENGTH_LONG).show();
                        }
                        try {
                            backup_browser(activity);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            Toast.makeText(activity, R.string.backup_browser_failed,
                                    Toast.LENGTH_LONG).show();
                        }

                        Toast.makeText(activity, R.string.backup_all_complete, Toast.LENGTH_LONG)
                                .show();
                    }
                });
                final Button custom_alert_dialog_cancel = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_cancel);
                custom_alert_dialog_cancel.setBackgroundResource(R.drawable.small_button);
                // if cancel button is clicked, close the custom dialog */
                custom_alert_dialog_cancel.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        alert_dialog.dismiss();
                    }
                });
                alert_dialog.show();

            }
        });
        // ** Restore functions ..

        button_restore_contacts_and_calls.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // if (activity != null) {

                // ** Check to see if database backups are ACTUALLY
                // there to be restored from before continuing .. */
                final String fileUrl_c = "/.mcb/.c/c.mcbong";
                final String file = sd + fileUrl_c;
                final File contacts_r = new File(file);
                final String fileUrl_c2 = "/.mcb/.c/c2.mcbong";
                final String file2 = sd + fileUrl_c2;
                final File contacts_r2 = new File(file2);

                if (contacts_r.exists() & contacts_r2.exists()) {

                    // ** call custom dialog into view and set
                    // characteristic's */
                    final Dialog alert_dialog = new Dialog(activity,
                            R.style.Theme_Dialog_Translucent);
                    alert_dialog.getWindow();
                    alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    alert_dialog.setContentView(R.layout.custom_alert_dialog);
                    final TextView title = (TextView) alert_dialog
                            .findViewById(R.id.custom_alert_dialog_textview_title);
                    title.setText(R.string.restore_contacts_and_calls);
                    final TextView alert_text = (TextView) alert_dialog
                            .findViewById(R.id.custom_alert_dialog_textview);
                    alert_text.setText(R.string.confirm);
                    final ImageView image = (ImageView) alert_dialog
                            .findViewById(R.id.custom_alert_dialog_image);
                    image.setImageResource(R.drawable.icon_contacts_and_calls);

                    // * set up button image resources */
                    final Button custom_alert_dialog_ok = (Button) alert_dialog
                            .findViewById(R.id.custom_alert_dialog_ok);
                    custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                    // ** if custom dialog OK button is clicked,
                    // execute the shell commands through root-tools
                    // */
                    custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {

                            alert_dialog.dismiss();

                            Toast.makeText(activity, R.string.restoring_contacts_and_calls,
                                    Toast.LENGTH_SHORT).show();

                            // restoring the created backups
                            // */
                            restore_contacts(activity);
                            // alert_dialog.dismiss();
                        }
                    });
                    final Button custom_alert_dialog_cancel = (Button) alert_dialog
                            .findViewById(R.id.custom_alert_dialog_cancel);
                    custom_alert_dialog_cancel.setBackgroundResource(R.drawable.small_button);
                    // if cancel button is clicked, close the custom
                    // dialog */
                    custom_alert_dialog_cancel.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            alert_dialog.dismiss();
                        }
                    });
                    alert_dialog.show();

                    // ** If NO database backups are found then warn
                    // the user and stop the restore process .. */
                } else {
                    Toast.makeText(activity, R.string.no_backup_found, Toast.LENGTH_SHORT).show();
                }
            }
        });
        button_restore_text_messages.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // if (activity != null) {

                // ** Check to see if database backups are ACTUALLY there to
                // be restored from before continuing .. */
                final String fileUrl_m = "/.mcb/.m/m.mcbong";
                final String file = sd + fileUrl_m;
                final File messages_r = new File(file);
                final String fileUrl_m2 = "/.mcb/.m/m2.mcbong";
                final String file2 = sd + fileUrl_m2;
                final File messages_r2 = new File(file2);

                if (messages_r.exists() & messages_r2.exists()) {

                    // ** call custom dialog into view and set
                    // characteristic's */
                    final Dialog alert_dialog = new Dialog(activity,
                            R.style.Theme_Dialog_Translucent);
                    alert_dialog.getWindow();
                    alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    alert_dialog.setContentView(R.layout.custom_alert_dialog);
                    final TextView title = (TextView) alert_dialog
                            .findViewById(R.id.custom_alert_dialog_textview_title);
                    title.setText(R.string.restore_text_messages);
                    final TextView alert_text = (TextView) alert_dialog
                            .findViewById(R.id.custom_alert_dialog_textview);
                    alert_text.setText(R.string.confirm);
                    final ImageView image = (ImageView) alert_dialog
                            .findViewById(R.id.custom_alert_dialog_image);
                    image.setImageResource(R.drawable.icon_messages);

                    // * set up button image resources */
                    final Button custom_alert_dialog_ok = (Button) alert_dialog
                            .findViewById(R.id.custom_alert_dialog_ok);
                    custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                    // ** if custom dialog OK button is clicked, execute the
                    // shell commands through root-tools */
                    custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {

                            alert_dialog.dismiss();

                            Toast.makeText(activity, R.string.restoring_text_messages,
                                    Toast.LENGTH_SHORT).show();

                            // restoring
                            // the created backups */
                            restore_messages(activity);
                            // alert_dialog.dismiss();
                        }
                    });
                    final Button custom_alert_dialog_cancel = (Button) alert_dialog
                            .findViewById(R.id.custom_alert_dialog_cancel);
                    custom_alert_dialog_cancel.setBackgroundResource(R.drawable.small_button);
                    // if cancel button is clicked, close the custom dialog
                    // */
                    custom_alert_dialog_cancel.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            alert_dialog.dismiss();
                        }
                    });
                    alert_dialog.show();

                    // ** If NO database backups are found then warn the
                    // user and stop the restore process .. */
                } else {
                    Toast.makeText(activity, R.string.no_backup_found, Toast.LENGTH_SHORT).show();
                }
            }
        });
        button_restore_browser_bookmarks.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // if (activity != null) {

                // ** Check to see if database backups are ACTUALLY
                // there to
                // be restored from before continuing .. */
                final String fileUrl_b = "/.mcb/.b/b.mcbong";
                final String file = sd + fileUrl_b;
                final File browser_r = new File(file);
                final String fileUrl_b2 = "/.mcb/.b/b2.mcbong";
                final String file2 = sd + fileUrl_b2;
                final File browser_r2 = new File(file2);

                if (browser_r.exists() & browser_r2.exists()) {

                    // ** call custom dialog into view and set
                    // characteristic's */
                    final Dialog alert_dialog = new Dialog(activity,
                            R.style.Theme_Dialog_Translucent);
                    alert_dialog.getWindow();
                    alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    alert_dialog.setContentView(R.layout.custom_alert_dialog);
                    final TextView title = (TextView) alert_dialog
                            .findViewById(R.id.custom_alert_dialog_textview_title);
                    title.setText(R.string.restore_browser_bookmarks);
                    final TextView alert_text = (TextView) alert_dialog
                            .findViewById(R.id.custom_alert_dialog_textview);
                    alert_text.setText(R.string.confirm);
                    final ImageView image = (ImageView) alert_dialog
                            .findViewById(R.id.custom_alert_dialog_image);
                    image.setImageResource(R.drawable.icon_browser);

                    // * set up button image resources */
                    final Button custom_alert_dialog_ok = (Button) alert_dialog
                            .findViewById(R.id.custom_alert_dialog_ok);
                    custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                    // ** if custom dialog OK button is clicked,
                    // execute the
                    // shell commands through root-tools */
                    custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {

                            alert_dialog.dismiss();

                            Toast.makeText(activity, R.string.restoring__browser_bookmarks,
                                    Toast.LENGTH_SHORT).show();

                            // restoring
                            // the created backups */
                            restore_browser(activity);
                            // alert_dialog.dismiss();
                        }
                    });
                    final Button custom_alert_dialog_cancel = (Button) alert_dialog
                            .findViewById(R.id.custom_alert_dialog_cancel);
                    custom_alert_dialog_cancel.setBackgroundResource(R.drawable.small_button);
                    // if cancel button is clicked, close the custom
                    // dialog
                    // */
                    custom_alert_dialog_cancel.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            alert_dialog.dismiss();
                        }
                    });
                    alert_dialog.show();

                    // ** If NO database backups are found then warn
                    // the
                    // user and stop the restore process .. */
                } else {
                    Toast.makeText(activity, R.string.no_backup_found, Toast.LENGTH_SHORT).show();
                }
            }
        });

        imagebutton_restore_all.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // ** call custom dialog into view and set
                // characteristic's */
                // ** call custom dialog into view and set characteristic's
                // */
                final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
                alert_dialog.getWindow();
                alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alert_dialog.setContentView(R.layout.custom_alert_dialog);
                final TextView title = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview_title);
                title.setText(R.string.restore_all);
                final TextView alert_text = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview);
                alert_text.setText(R.string.confirm);
                final ImageView image = (ImageView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_image);
                image.setImageResource(R.drawable.on);

                // * set up button image resources */
                final Button custom_alert_dialog_ok = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_ok);
                custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                // ** if custom dialog OK button is clicked, execute the
                // shell commands through root-tools */
                custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {

                        alert_dialog.dismiss();

                        Toast.makeText(activity, R.string.restoring_all, Toast.LENGTH_LONG).show();

                        // ** Call all three backup methods with try/catch

                        try {
                            restore_contacts(activity);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            Toast.makeText(activity, R.string.restore_contacts_failed,
                                    Toast.LENGTH_LONG).show();
                        }
                        try {
                            restore_messages(activity);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            Toast.makeText(activity, R.string.restore_messages_failed,
                                    Toast.LENGTH_LONG).show();
                        }
                        try {
                            restore_browser(activity);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            Toast.makeText(activity, R.string.restore_browser_failed,
                                    Toast.LENGTH_LONG).show();
                        }

                        final ImageButton button_reboot = (ImageButton) view
                                .findViewById(R.id.button_reboot);
                        button_reboot.setVisibility(View.VISIBLE);
                        Toast.makeText(activity, R.string.restore_all_complete, Toast.LENGTH_LONG)
                                .show();
                    }
                });
                final Button custom_alert_dialog_cancel = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_cancel);
                custom_alert_dialog_cancel.setBackgroundResource(R.drawable.small_button);
                // if cancel button is clicked, close the custom dialog */
                custom_alert_dialog_cancel.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        alert_dialog.dismiss();
                    }
                });
                alert_dialog.show();

            }
        });
        button_reboot.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // ** call custom dialog into view and set characteristic's
                // */
                final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
                alert_dialog.getWindow();
                alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alert_dialog.setContentView(R.layout.custom_alert_dialog);
                final TextView title = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview_title);
                title.setText(R.string.dialog_title_reboot_device);
                final TextView alert_text = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview);
                alert_text.setText(R.string.confirm);
                final ImageView image = (ImageView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_image);
                image.setImageResource(R.drawable.reboot);

                // * set up button image resources */
                final Button custom_alert_dialog_ok = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_ok);
                custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                // ** if button is clicked, execute the shell commands
                // through root-tools */
                custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        // ** Call rootshell_reboot method
                        rootshell_reboot();
                    }

                    /**
                         * 
                         */
                    public void rootshell_reboot() {
                        CommandCapture command = new CommandCapture(0, "reboot");
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
                    }
                });
                final Button custom_alert_dialog_cancel = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_cancel);
                custom_alert_dialog_cancel.setBackgroundResource(R.drawable.small_button);
                // if OK button is clicked, close the custom dialog */
                custom_alert_dialog_cancel.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        alert_dialog.dismiss();
                    }
                });
                alert_dialog.show();
            }
        });

        return view;
    }

    /**
     * @param activity
     */
    public void backup_contacts(final Activity activity) {
        InputStream myInput;

        try {

            myInput = new FileInputStream(
                    "/data/data/com.android.providers.contacts/databases/contacts2.db");

            // Set the output folder on the
            // SDcard
            final String file = sd + dirUrl_c;
            final File directory = new File(file);
            // Create the folder if it
            // doesn't exist:
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // Set the output file stream
            // up:

            final OutputStream myOutput = new FileOutputStream(directory.getPath() + "/c.mcbong");

            // Transfer bytes from the input
            // file to the output file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close and clear the streams

            myOutput.flush();

            myOutput.close();

            myInput.close();

        } catch (FileNotFoundException e) {

            // TODO Auto-generated catch
            // block
            e.printStackTrace();
        } catch (IOException e) {

            // TODO Auto-generated catch
            // block
            e.printStackTrace();
        }

        try {

            myInput = new FileInputStream(
                    "/data/data/com.android.providers.contacts/databases/contacts2.db-journal");

            // Set the output folder on the
            // SDcard
            final String file = sd + dirUrl_c;
            final File directory = new File(file);
            // Create the folder if it
            // doesn't exist:
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // Set the output file stream
            // up:

            OutputStream myOutput = new FileOutputStream(directory.getPath() + "/c2.mcbong");

            // Transfer bytes from the input
            // file to the output file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close and clear the streams

            myOutput.flush();

            myOutput.close();

            myInput.close();

        } catch (FileNotFoundException e) {

            // TODO Auto-generated catch
            // block
            e.printStackTrace();
        } catch (IOException e) {

            // TODO Auto-generated catch
            // block
            e.printStackTrace();
        }

        // ** Check to see if database's
        // were ACTUALLY backed up .. */
        final String fileUrl_contacts_database = "/.mcb/.c/c.mcbong";
        final String file = sd + fileUrl_contacts_database;
        final File contacts_database = new File(file);
        final String fileUrl_contacts_database_journal = "/.mcb/.c/c2.mcbong";
        final String file1 = sd + fileUrl_contacts_database_journal;
        final File contacts_database_journal = new File(file1);

        if (contacts_database.exists()) {
            Toast.makeText(activity, R.string.verify_database_contacts, Toast.LENGTH_LONG).show();
        }
        if (contacts_database_journal.exists()) {
            Toast.makeText(activity, R.string.verify_database_contacts_journal, Toast.LENGTH_LONG)
                    .show();

            Toast.makeText(activity, R.string.backup_contacts_complete, Toast.LENGTH_LONG).show();

        } else {

            Toast.makeText(activity, R.string.backup_contacts_failed, Toast.LENGTH_LONG).show();

        }
    }

    /**
     * @param activity
     */
    public void backup_messages(final Activity activity) {
        InputStream myInput;

        try {

            myInput = new FileInputStream(
                    "/data/data/com.android.providers.telephony/databases/mmssms.db");

            // Set the output folder on the
            // SDcard
            final String file = sd + dirUrl_m;
            final File directory = new File(file);
            // Create the folder if it
            // doesn't exist:
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // Set the output file stream
            // up:

            final OutputStream myOutput = new FileOutputStream(directory.getPath() + "/m.mcbong");

            // Transfer bytes from the input
            // file to the output file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close and clear the streams

            myOutput.flush();

            myOutput.close();

            myInput.close();

        } catch (FileNotFoundException e) {

            // TODO Auto-generated catch
            // block
            e.printStackTrace();
        } catch (IOException e) {

            // TODO Auto-generated catch
            // block
            e.printStackTrace();
        }

        try {

            myInput = new FileInputStream(
                    "/data/data/com.android.providers.telephony/databases/mmssms.db-journal");

            // Set the output folder on the
            // SDcard
            final String file = sd + dirUrl_m;
            final File directory = new File(file);
            // Create the folder if it
            // doesn't exist:
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // Set the output file stream
            // up:

            final OutputStream myOutput = new FileOutputStream(directory.getPath() + "/m2.mcbong");

            // Transfer bytes from the input
            // file to the output file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close and clear the streams

            myOutput.flush();

            myOutput.close();

            myInput.close();

        } catch (FileNotFoundException e) {

            // TODO Auto-generated catch
            // block
            e.printStackTrace();
        } catch (IOException e) {

            // TODO Auto-generated catch
            // block
            e.printStackTrace();
        }

        // ** Check to see if database's
        // were ACTUALLY backed up .. */
        final String fileUrl_messages_database = "/.mcb/.m/m.mcbong";
        final String file = sd + fileUrl_messages_database;
        final File messages_database = new File(file);
        final String fileUrl_messages_database_journal = "/.mcb/.m/m2.mcbong";
        final String file1 = sd + fileUrl_messages_database_journal;
        final File messages_database_journal = new File(file1);

        if (messages_database.exists()) {
            Toast.makeText(activity, R.string.verify_database_messages, Toast.LENGTH_LONG).show();
        }
        if (messages_database_journal.exists()) {
            Toast.makeText(activity, R.string.verify_database_messages_journal, Toast.LENGTH_LONG)
                    .show();
            Toast.makeText(activity, R.string.backup_messages_complete, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(activity, R.string.backup_messages_failed, Toast.LENGTH_LONG).show();

        }
    }

    /**
     * @param activity
     */
    public void backup_browser(final Activity activity) {
        InputStream myInput;

        try {

            myInput = new FileInputStream("/data/data/com.android.browser/databases/browser2.db");

            // Set the output folder on the
            // SDcard
            final String file = sd + dirUrl_b;
            final File directory = new File(file);
            // Create the folder if it
            // doesn't exist:
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // Set the output file stream
            // up:

            final OutputStream myOutput = new FileOutputStream(directory.getPath() + "/b.mcbong");

            // Transfer bytes from the input
            // file to the output file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close and clear the streams

            myOutput.flush();

            myOutput.close();

            myInput.close();

        } catch (FileNotFoundException e) {

            // TODO Auto-generated catch
            // block
            e.printStackTrace();
        } catch (IOException e) {

            // TODO Auto-generated catch
            // block
            e.printStackTrace();
        }

        try {

            myInput = new FileInputStream(
                    "/data/data/com.android.browser/databases/browser2.db-wal");

            // Set the output folder on the
            // SDcard
            final String file = sd + dirUrl_b;
            final File directory = new File(file);
            // Create the folder if it
            // doesn't exist:
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // Set the output file stream
            // up:

            final OutputStream myOutput = new FileOutputStream(directory.getPath() + "/b2.mcbong");

            // Transfer bytes from the input
            // file to the output file
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close and clear the streams

            myOutput.flush();

            myOutput.close();

            myInput.close();

        } catch (FileNotFoundException e) {

            // TODO Auto-generated catch
            // block
            e.printStackTrace();
        } catch (IOException e) {

            // TODO Auto-generated catch
            // block
            e.printStackTrace();
        }

        // ** Check to see if database's
        // were ACTUALLY backed up .. */
        final String fileUrl_browser_database = "/.mcb/.b/b.mcbong";
        final String file = sd + fileUrl_browser_database;
        final File browser_database = new File(file);
        final String fileUrl_browser_database_journal = "/.mcb/.b/b2.mcbong";
        final String file1 = sd + fileUrl_browser_database_journal;
        final File browser_database_journal = new File(file1);

        if (browser_database.exists()) {
            Toast.makeText(activity, R.string.verify_database_browser, Toast.LENGTH_LONG).show();
        }
        if (browser_database_journal.exists()) {
            Toast.makeText(activity, R.string.verify_database_browser_journal, Toast.LENGTH_LONG)
                    .show();
            Toast.makeText(activity, R.string.backup_browser_complete, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(activity, R.string.backup_browser_failed, Toast.LENGTH_LONG).show();

        }
    }

    /**
     * @param activity
     */
    public void restore_contacts(final Activity activity) {
        CommandCapture command = new CommandCapture(
                0,
                "cp -p /sdcard/.mcb/.c/c.mcbong /data/data/com.android.providers.contacts/databases/contacts2.db",
                "cp -p /sdcard/.mcb/.c/c2.mcbong /data/data/com.android.providers.contacts/databases/contacts2.db-journal");
        try {
            RootTools.getShell(true).add(command).waitForFinish();
            Toast.makeText(activity, R.string.restore_complete, Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch
            // block
            Toast.makeText(activity, R.string.restore_failed, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch
            // block
            Toast.makeText(activity, R.string.restore_failed, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (TimeoutException e) {
            // TODO Auto-generated catch
            // block
            Toast.makeText(activity, R.string.restore_failed, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * @param activity
     */
    public void restore_messages(final Activity activity) {
        CommandCapture command = new CommandCapture(
                0,
                "cp -p /sdcard/.mcb/.m/m.mcbong /data/data/com.android.providers.telephony/databases/mmssms.db",
                "cp -p /sdcard/.mcb/.m/m2.mcbong /data/data/com.android.providers.telephony/databases/mmssms.db-journal");
        try {
            RootTools.getShell(true).add(command).waitForFinish();
            Toast.makeText(activity, R.string.restore_complete,
                    Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            Toast.makeText(activity, R.string.restore_failed,
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Toast.makeText(activity, R.string.restore_failed,
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            Toast.makeText(activity, R.string.restore_failed,
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * @param activity
     */
    public void restore_browser(final Activity activity) {
        CommandCapture command = new CommandCapture(
                0,
                "cp -p /sdcard/.mcb/.b/b.mcbong /data/data/com.android.browser/databases/browser2.db",
                "cp -p /sdcard/.mcb/.b/b2.mcbong /data/data/com.android.browser/databases/browser2.db-wal");
        try {
            RootTools.getShell(true).add(command).waitForFinish();
            Toast.makeText(activity, R.string.restore_complete,
                    Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch
            // block
            Toast.makeText(activity, R.string.restore_failed,
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch
            // block
            Toast.makeText(activity, R.string.restore_failed,
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (TimeoutException e) {
            // TODO Auto-generated catch
            // block
            Toast.makeText(activity, R.string.restore_failed,
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
