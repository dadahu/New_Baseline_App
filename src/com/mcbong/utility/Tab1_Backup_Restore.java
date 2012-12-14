
package com.mcbong.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    public String sd = android.os.Environment.getExternalStorageDirectory().getPath();
    public String dirUrl_c = "/.mcb/.c";
    public String dirUrl_b = "/.mcb/.b";
    public String dirUrl_m = "/.mcb/.m";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // ** Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.tab1_backup_restore, container, false);
        final Activity activity = getActivity();
        // ** Define buttons and set image resource .. */
        Button button_backup_contacts_and_calls = (Button) view
                .findViewById(R.id.button_backup_contacts_and_calls);
        button_backup_contacts_and_calls.setBackgroundResource(R.drawable.button);
        Button button_backup_text_messages = (Button) view
                .findViewById(R.id.button_backup_text_messages);
        button_backup_text_messages.setBackgroundResource(R.drawable.button);
        Button button_backup_browser_bookmarks = (Button) view
                .findViewById(R.id.button_backup_browser_bookmarks);
        button_backup_browser_bookmarks.setBackgroundResource(R.drawable.button);
        Button button_restore_contacts_and_calls = (Button) view
                .findViewById(R.id.button_restore_contacts_and_calls);
        button_restore_contacts_and_calls.setBackgroundResource(R.drawable.button);
        Button button_restore_text_messages = (Button) view
                .findViewById(R.id.button_restore_text_messages);
        button_restore_text_messages.setBackgroundResource(R.drawable.button);
        Button button_restore_browser_bookmarks = (Button) view
                .findViewById(R.id.button_restore_browser_bookmarks);
        button_restore_browser_bookmarks.setBackgroundResource(R.drawable.button);

        // ** Backup functions ..

        button_backup_contacts_and_calls.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                if (activity != null) {

                    // ** call custom dialog into view and set
                    // characteristic's */
                    final Dialog alert_dialog = new Dialog(activity,
                            R.style.Theme_Dialog_Translucent);
                    alert_dialog.getWindow();
                    alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    alert_dialog.setContentView(R.layout.custom_alert_dialog);
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
                    custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                    custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {

                            alert_dialog.dismiss();

                            Toast.makeText(activity, R.string.backing_up_contacts_and_calls,
                                    Toast.LENGTH_SHORT).show();

                            InputStream myInput;

                            try {

                                myInput = new FileInputStream(
                                        "/data/data/com.android.providers.contacts/databases/contacts2.db");

                                // Set the output folder on the
                                // SDcard
                                String file = sd + dirUrl_c;
                                File directory = new File(file);
                                // Create the folder if it
                                // doesn't exist:
                                if (!directory.exists()) {
                                    directory.mkdirs();
                                }
                                // Set the output file stream
                                // up:

                                OutputStream myOutput = new FileOutputStream(directory.getPath()
                                        + "/c.mcbong");

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
                                String file = sd + dirUrl_c;
                                File directory = new File(file);
                                // Create the folder if it
                                // doesn't exist:
                                if (!directory.exists()) {
                                    directory.mkdirs();
                                }
                                // Set the output file stream
                                // up:

                                OutputStream myOutput = new FileOutputStream(directory.getPath()
                                        + "/c2.mcbong");

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
                            String fileUrl_contacts_database = "/.mcb/.c/c.mcbong";
                            String file = sd + fileUrl_contacts_database;
                            File contacts_database = new File(file);

                            if (contacts_database.exists()) {
                                Toast.makeText(activity, R.string.verify_database,
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(activity, R.string.backup_failed, Toast.LENGTH_LONG)
                                .show();
                            }
                            String fileUrl_contacts_database_journal = "/.mcb/.c/c2.mcbong";
                            String file1 = sd + fileUrl_contacts_database_journal;
                            File contacts_database_journal = new File(file1);

                            if (contacts_database_journal.exists()) {
                                Toast.makeText(activity, R.string.verify_database_journal,
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(activity, R.string.backup_failed, Toast.LENGTH_LONG)
                                .show();

                            }
                        }

                    });
                    Button custom_alert_dialog_cancel = (Button) alert_dialog
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
            }
        });
        button_backup_text_messages.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

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
                    custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                    // ** if custom dialog OK button is clicked, execute the
                    // shell commands through root-tools */
                    custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {

                            alert_dialog.dismiss();

                            Toast.makeText(activity, R.string.backing_up_text_messages,
                                    Toast.LENGTH_SHORT).show();

                            InputStream myInput;

                            try {

                                myInput = new FileInputStream(
                                        "/data/data/com.android.providers.telephony/databases/mmssms.db");

                                // Set the output folder on the
                                // SDcard
                                String file = sd + dirUrl_m;
                                File directory = new File(file);
                                // Create the folder if it
                                // doesn't exist:
                                if (!directory.exists()) {
                                    directory.mkdirs();
                                }
                                // Set the output file stream
                                // up:

                                OutputStream myOutput = new FileOutputStream(directory.getPath()
                                        + "/m.mcbong");

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
                                String file = sd + dirUrl_m;
                                File directory = new File(file);
                                // Create the folder if it
                                // doesn't exist:
                                if (!directory.exists()) {
                                    directory.mkdirs();
                                }
                                // Set the output file stream
                                // up:

                                OutputStream myOutput = new FileOutputStream(directory.getPath()
                                        + "/m2.mcbong");

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
                            String fileUrl_messages_database = "/.mcb/.m/m.mcbong";
                            String file = sd + fileUrl_messages_database;
                            File messages_database = new File(file);

                            if (messages_database.exists()) {
                                Toast.makeText(activity, R.string.verify_database,
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(activity, R.string.backup_failed, Toast.LENGTH_LONG)
                                .show();
                            }
                            String fileUrl_messages_database_journal = "/.mcb/.m/m2.mcbong";
                            String file1 = sd + fileUrl_messages_database_journal;
                            File messages_database_journal = new File(file1);

                            if (messages_database_journal.exists()) {
                                Toast.makeText(activity, R.string.verify_database_journal,
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(activity, R.string.backup_failed, Toast.LENGTH_LONG)
                                .show();

                            }

                        }
                    });
                    Button custom_alert_dialog_cancel = (Button) alert_dialog
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
            }
        });
        button_backup_browser_bookmarks.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                if (activity != null) {

                    // ** call custom dialog into view and set
                    // characteristic's
                    // */
                    final Dialog alert_dialog = new Dialog(activity,
                            R.style.Theme_Dialog_Translucent);
                    alert_dialog.getWindow();
                    alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    alert_dialog.setContentView(R.layout.custom_alert_dialog);
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
                    custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                    // ** if custom dialog OK button is clicked, execute
                    // the
                    // shell commands through root-tools */
                    custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {

                            alert_dialog.dismiss();

                            Toast.makeText(activity, R.string.backing_up_browser_bookmarks,
                                    Toast.LENGTH_SHORT).show();

                            InputStream myInput;

                            try {

                                myInput = new FileInputStream(
                                        "/data/data/com.android.browser/databases/browser2.db");

                                // Set the output folder on the
                                // SDcard
                                String file = sd + dirUrl_b;
                                File directory = new File(file);
                                // Create the folder if it
                                // doesn't exist:
                                if (!directory.exists()) {
                                    directory.mkdirs();
                                }
                                // Set the output file stream
                                // up:

                                OutputStream myOutput = new FileOutputStream(directory.getPath()
                                        + "/b.mcbong");

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
                                String file = sd + dirUrl_b;
                                File directory = new File(file);
                                // Create the folder if it
                                // doesn't exist:
                                if (!directory.exists()) {
                                    directory.mkdirs();
                                }
                                // Set the output file stream
                                // up:

                                OutputStream myOutput = new FileOutputStream(directory.getPath()
                                        + "/b2.mcbong");

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
                            String fileUrl_browser_database = "/.mcb/.b/b.mcbong";
                            String file = sd + fileUrl_browser_database;
                            File browser_database = new File(file);

                            if (browser_database.exists()) {
                                Toast.makeText(activity, R.string.verify_database,
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(activity, R.string.backup_failed, Toast.LENGTH_LONG)
                                .show();
                            }
                            String fileUrl_browser_database_journal = "/.mcb/.b/b2.mcbong";
                            String file1 = sd + fileUrl_browser_database_journal;
                            File browser_database_journal = new File(file1);

                            if (browser_database_journal.exists()) {
                                Toast.makeText(activity, R.string.verify_database_journal,
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(activity, R.string.backup_failed, Toast.LENGTH_LONG)
                                .show();

                            }

                        }
                    });
                    Button custom_alert_dialog_cancel = (Button) alert_dialog
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
            }
        });

        // ** Restore functions ..

        button_restore_contacts_and_calls.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                if (activity != null) {

                    // ** Check to see if database backups are ACTUALLY
                    // there to be restored from before continuing .. */
                    String fileUrl_c = "/.mcb/.c/c.mcbong";
                    String file = sd + fileUrl_c;
                    File contacts_r = new File(file);

                    if (contacts_r.exists()) {

                        // ** call custom dialog into view and set
                        // characteristic's */
                        final Dialog alert_dialog = new Dialog(activity,
                                R.style.Theme_Dialog_Translucent);
                        alert_dialog.getWindow();
                        alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        alert_dialog.setContentView(R.layout.custom_alert_dialog);
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
                        custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                        // ** if custom dialog OK button is clicked,
                        // execute the shell commands through root-tools
                        // */
                        custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                            public void onClick(View v) {

                                alert_dialog.dismiss();

                                Toast.makeText(activity, R.string.restoring_contacts_and_calls,
                                        Toast.LENGTH_SHORT).show();

                                // /** Remove current contacts
                                // and call-logs databases
                                // before
                                // restoring the created backups
                                // */
                                CommandCapture command = new CommandCapture(
                                        0,
                                        "cp -p /sdcard/.mcb/.c/c.mcbong /data/data/com.android.providers.contacts/databases/contacts2.db",
                                        "cp -p /sdcard/.mcb/.c/c2.mcbong /data/data/com.android.providers.contacts/databases/contacts2.db-journal");
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
                                // alert_dialog.dismiss();
                            }
                        });
                        Button custom_alert_dialog_cancel = (Button) alert_dialog
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
                        Toast.makeText(activity, R.string.no_backup_found, Toast.LENGTH_SHORT)
                        .show();
                    }
                }
            }
        });
        button_restore_text_messages.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                if (activity != null) {

                    // ** Check to see if database backups are ACTUALLY there to
                    // be restored from before continuing .. */
                    String fileUrl_m = "/.mcb/.m/m.mcbong";
                    String file = sd + fileUrl_m;
                    File messages_r = new File(file);

                    if (messages_r.exists()) {

                        // ** call custom dialog into view and set
                        // characteristic's */
                        final Dialog alert_dialog = new Dialog(activity,
                                R.style.Theme_Dialog_Translucent);
                        alert_dialog.getWindow();
                        alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        alert_dialog.setContentView(R.layout.custom_alert_dialog);
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
                        custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                        // ** if custom dialog OK button is clicked, execute the
                        // shell commands through root-tools */
                        custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                            public void onClick(View v) {

                                alert_dialog.dismiss();

                                Toast.makeText(activity, R.string.restoring_text_messages,
                                        Toast.LENGTH_SHORT).show();

                                // /** Remove current messages databases
                                // from /data/data/... before restoring
                                // the created backups */
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
                                // alert_dialog.dismiss();
                            }
                        });
                        Button custom_alert_dialog_cancel = (Button) alert_dialog
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
                        Toast.makeText(activity, R.string.no_backup_found, Toast.LENGTH_SHORT)
                        .show();
                    }
                }
            }
        });
        button_restore_browser_bookmarks.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                if (activity != null) {

                    // ** Check to see if database backups are ACTUALLY
                    // there to
                    // be restored from before continuing .. */
                    String fileUrl_b = "/.mcb/.b/b.mcbong";
                    String file = sd + fileUrl_b;
                    File messages_r = new File(file);

                    if (messages_r.exists()) {

                        // ** call custom dialog into view and set
                        // characteristic's */
                        final Dialog alert_dialog = new Dialog(activity,
                                R.style.Theme_Dialog_Translucent);
                        alert_dialog.getWindow();
                        alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        alert_dialog.setContentView(R.layout.custom_alert_dialog);
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
                        custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                        // ** if custom dialog OK button is clicked,
                        // execute the
                        // shell commands through root-tools */
                        custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                            public void onClick(View v) {

                                alert_dialog.dismiss();

                                Toast.makeText(activity, R.string.restoring__browser_bookmarks,
                                        Toast.LENGTH_SHORT).show();

                                // /** Remove current messages
                                // databases
                                // from /data/data/... before
                                // restoring
                                // the created backups */
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
                                // alert_dialog.dismiss();
                            }
                        });
                        Button custom_alert_dialog_cancel = (Button) alert_dialog
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
                        Toast.makeText(activity, R.string.no_backup_found, Toast.LENGTH_SHORT)
                        .show();
                    }
                }
            }
        });
        return view;
    }
}

// button_restore_contacts_and_calls.setOnClickListener(new OnClickListener() {
// public void onClick(View v) {
//
// if (activity != null) {
//
// // ** Check to see if database backups are ACTUALLY
// // there to be restored from before continuing .. */
// String fileUrl = "/.mcb/.c/c.mcbong";
// String file = sd
// + fileUrl;
// File contacts_r = new File(file);
//
// if (contacts_r.exists()) {
//
// // ** call custom dialog into view and set
// // characteristic's */
// final Dialog alert_dialog = new Dialog(activity,
// R.style.Theme_Dialog_Translucent);
// alert_dialog.getWindow();
// alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
// alert_dialog.setContentView(R.layout.custom_alert_dialog);
// TextView title = (TextView) alert_dialog
// .findViewById(R.id.custom_alert_dialog_textview_title);
// title.setText(R.string.restore_contacts_and_calls);
// TextView alert_text = (TextView) alert_dialog
// .findViewById(R.id.custom_alert_dialog_textview);
// alert_text.setText(R.string.confirm);
// ImageView image = (ImageView) alert_dialog
// .findViewById(R.id.custom_alert_dialog_image);
// image.setImageResource(R.drawable.icon_contacts_and_calls);
//
// // * set up button image resources */
// Button custom_alert_dialog_ok = (Button) alert_dialog
// .findViewById(R.id.custom_alert_dialog_ok);
// custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);
//
// // ** if custom dialog OK button is clicked,
// // execute the shell commands through root-tools
// // */
// custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
// public void onClick(View v) {
//
// alert_dialog.dismiss();
//
// Toast.makeText(activity, R.string.restoring_contacts_and_calls,
// Toast.LENGTH_SHORT).show();
//
// OutputStream myOutput;
//
// try {
//
// myOutput = new FileOutputStream(
// "/data/data/com.android.providers.contacts/databases/contacts2.db");
//
// // Set the output folder on
// // the
// // SDcard
// String fileUrl = "/.mcb/.c";
// String file = sd + fileUrl;
// File directory = new File(file);
//
// // Set the input file stream
// // up:
//
// InputStream myInputs = new FileInputStream(directory.getPath()
// + "/c.mcbong");
//
// // Transfer bytes from the
// // input file to the output
// // file
// byte[] buffer = new byte[1024];
// int length;
// while ((length = myInputs.read(buffer)) > 0) {
// myOutput.write(buffer, 0, length);
// }
//
// // Close and clear the
// // streams
// myOutput.flush();
//
// myOutput.close();
//
// myInputs.close();
//
// } catch (FileNotFoundException e) {
// Toast.makeText(activity, R.string.restore_failed,
// Toast.LENGTH_LONG).show();
//
// // TODO Auto-generated catch
// // block
// e.printStackTrace();
// } catch (IOException e) {
// Toast.makeText(activity, R.string.restore_failed,
// Toast.LENGTH_LONG).show();
//
// // TODO Auto-generated catch
// // block
// e.printStackTrace();
// }
// Toast.makeText(activity, R.string.restoring_database,
// Toast.LENGTH_LONG).show();
//
// try {
//
// myOutput = new FileOutputStream(
// "/data/data/com.android.providers.contacts/databases/contacts2.db-journal");
//
// // Set the output folder on
// // the
// // SDcard
// String fileUrl = "/.mcb/.c";
// String file = sd + fileUrl;
// File directory = new File(file);
//
// // Set the input file stream
// // up:
//
// InputStream myInputs = new FileInputStream(directory.getPath()
// + "/c2.mcbong");
//
// // Transfer bytes from the
// // input file to the output
// // file
// byte[] buffer = new byte[1024];
// int length;
// while ((length = myInputs.read(buffer)) > 0) {
// myOutput.write(buffer, 0, length);
// }
//
// // Close and clear the
// // streams
// myOutput.flush();
//
// myOutput.close();
//
// myInputs.close();
//
// } catch (FileNotFoundException e) {
// Toast.makeText(activity, R.string.restore_failed,
// Toast.LENGTH_LONG).show();
//
// // TODO Auto-generated catch
// // block
// e.printStackTrace();
// } catch (IOException e) {
// Toast.makeText(activity, R.string.restore_failed,
// Toast.LENGTH_LONG).show();
//
// // TODO Auto-generated catch
// // block
// e.printStackTrace();
// }
// Toast.makeText(activity, R.string.restore_complete,
// Toast.LENGTH_LONG).show();
// }
//
// });
// Button custom_alert_dialog_cancel = (Button) alert_dialog
// .findViewById(R.id.custom_alert_dialog_cancel);
// custom_alert_dialog_cancel.setBackgroundResource(R.drawable.small_button);
// // if cancel button is clicked, close the custom
// // dialog */
// custom_alert_dialog_cancel.setOnClickListener(new OnClickListener() {
// public void onClick(View v) {
// alert_dialog.dismiss();
// }
// });
// alert_dialog.show();
//
// // ** If NO database backups are found then warn
// // the user and stop the restore process .. */
// } else {
// Toast.makeText(activity, R.string.no_backup_found, Toast.LENGTH_SHORT)
// .show();
// }
// }
// }
// });
// button_restore_text_messages.setOnClickListener(new OnClickListener() {
// public void onClick(View v) {
//
// if (activity != null) {
//
// // ** Check to see if database backups are ACTUALLY there to
// // be restored from before continuing .. */
// String fileUrl = "/.mcb/.m/m.mcbong";
// String file = sd
// + fileUrl;
// File messages_r = new File(file);
//
// if (messages_r.exists()) {
//
// // ** call custom dialog into view and set
// // characteristic's */
// final Dialog alert_dialog = new Dialog(activity,
// R.style.Theme_Dialog_Translucent);
// alert_dialog.getWindow();
// alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
// alert_dialog.setContentView(R.layout.custom_alert_dialog);
// TextView title = (TextView) alert_dialog
// .findViewById(R.id.custom_alert_dialog_textview_title);
// title.setText(R.string.restore_text_messages);
// TextView alert_text = (TextView) alert_dialog
// .findViewById(R.id.custom_alert_dialog_textview);
// alert_text.setText(R.string.confirm);
// ImageView image = (ImageView) alert_dialog
// .findViewById(R.id.custom_alert_dialog_image);
// image.setImageResource(R.drawable.icon_messages);
//
// // * set up button image resources */
// Button custom_alert_dialog_ok = (Button) alert_dialog
// .findViewById(R.id.custom_alert_dialog_ok);
// custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);
//
// // ** if custom dialog OK button is clicked, execute the
// // shell commands through root-tools */
// custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
// public void onClick(View v) {
//
// alert_dialog.dismiss();
//
// Toast.makeText(activity, R.string.restoring_text_messages,
// Toast.LENGTH_SHORT).show();
//
// OutputStream myOutput;
//
// try {
//
// myOutput = new FileOutputStream(
// "/data/data/com.android.providers.telephony/databases/mmssms.db");
//
// // Set the output folder on
// // the
// // SDcard
// String fileUrl = "/.mcb/.m";
// String file = sd + fileUrl;
// File directory = new File(file);
//
// // Set the input file stream
// // up:
//
// InputStream myInputs = new FileInputStream(directory.getPath()
// + "/m.mcbong");
//
// // Transfer bytes from the
// // input file to the output
// // file
// byte[] buffer = new byte[1024];
// int length;
// while ((length = myInputs.read(buffer)) > 0) {
// myOutput.write(buffer, 0, length);
// }
//
// // Close and clear the
// // streams
// myOutput.flush();
//
// myOutput.close();
//
// myInputs.close();
//
// } catch (FileNotFoundException e) {
// Toast.makeText(activity, R.string.restore_failed,
// Toast.LENGTH_LONG).show();
//
// // TODO Auto-generated catch
// // block
// e.printStackTrace();
// } catch (IOException e) {
// Toast.makeText(activity, R.string.restore_failed,
// Toast.LENGTH_LONG).show();
//
// // TODO Auto-generated catch
// // block
// e.printStackTrace();
// }
// Toast.makeText(activity, R.string.restoring_database,
// Toast.LENGTH_LONG).show();
//
// try {
//
// myOutput = new FileOutputStream(
// "/data/data/com.android.providers.telephony/databases/mmssms.db-journal");
//
// // Set the output folder on
// // the
// // SDcard
// String fileUrl = "/.mcb/.m";
// String file = sd + fileUrl;
// File directory = new File(file);
//
// // Set the input file stream
// // up:
//
// InputStream myInputs = new FileInputStream(directory.getPath()
// + "/m2.mcbong");
//
// // Transfer bytes from the
// // input file to the output
// // file
// byte[] buffer = new byte[1024];
// int length;
// while ((length = myInputs.read(buffer)) > 0) {
// myOutput.write(buffer, 0, length);
// }
//
// // Close and clear the
// // streams
// myOutput.flush();
//
// myOutput.close();
//
// myInputs.close();
//
// } catch (FileNotFoundException e) {
// Toast.makeText(activity, R.string.restore_failed,
// Toast.LENGTH_LONG).show();
//
// // TODO Auto-generated catch
// // block
// e.printStackTrace();
// } catch (IOException e) {
// Toast.makeText(activity, R.string.restore_failed,
// Toast.LENGTH_LONG).show();
//
// // TODO Auto-generated catch
// // block
// e.printStackTrace();
// }
// Toast.makeText(activity, R.string.restore_complete,
// Toast.LENGTH_LONG).show();
// }
//
// });
// Button custom_alert_dialog_cancel = (Button) alert_dialog
// .findViewById(R.id.custom_alert_dialog_cancel);
// custom_alert_dialog_cancel.setBackgroundResource(R.drawable.small_button);
// // if cancel button is clicked, close the custom dialog
// // */
// custom_alert_dialog_cancel.setOnClickListener(new OnClickListener() {
// public void onClick(View v) {
// alert_dialog.dismiss();
// }
// });
// alert_dialog.show();
//
// // ** If NO database backups are found then warn the
// // user and stop the restore process .. */
// } else {
// Toast.makeText(activity, R.string.no_backup_found, Toast.LENGTH_SHORT)
// .show();
// }
// }
// }
// });
// button_restore_browser_bookmarks.setOnClickListener(new OnClickListener() {
// public void onClick(View v) {
//
// if (activity != null) {
//
// // ** Check to see if database backups are ACTUALLY
// // there to
// // be restored from before continuing .. */
// String fileUrl = "/.mcb/.b/b.mcbong";
// String file = sd
// + fileUrl;
// File messages_r = new File(file);
//
// if (messages_r.exists()) {
//
// // ** call custom dialog into view and set
// // characteristic's */
// final Dialog alert_dialog = new Dialog(activity,
// R.style.Theme_Dialog_Translucent);
// alert_dialog.getWindow();
// alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
// alert_dialog.setContentView(R.layout.custom_alert_dialog);
// TextView title = (TextView) alert_dialog
// .findViewById(R.id.custom_alert_dialog_textview_title);
// title.setText(R.string.restore_browser_bookmarks);
// TextView alert_text = (TextView) alert_dialog
// .findViewById(R.id.custom_alert_dialog_textview);
// alert_text.setText(R.string.confirm);
// ImageView image = (ImageView) alert_dialog
// .findViewById(R.id.custom_alert_dialog_image);
// image.setImageResource(R.drawable.icon_browser);
//
// // * set up button image resources */
// Button custom_alert_dialog_ok = (Button) alert_dialog
// .findViewById(R.id.custom_alert_dialog_ok);
// custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);
//
// // ** if custom dialog OK button is clicked,
// // execute the
// // shell commands through root-tools */
// custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
// public void onClick(View v) {
//
// alert_dialog.dismiss();
//
// Toast.makeText(activity, R.string.restoring__browser_bookmarks,
// Toast.LENGTH_SHORT).show();
//
// // ** for reference to check if browser bug is
// // fixed ... */
// //
// //"cp -p /sdcard/.mcb/.bb/b /data/data/com.android.browser/databases/browser2.db",
// //
// "cp -p /sdcard/.mcb/.bb/b-shm /data/data/com.android.browser/databases/browser2.db-shm",
// //
// "cp -p /sdcard/.mcb/.bb/b-wal /data/data/com.android.browser/databases/browser2.db-wal",
// //
// "cp -p /sdcard/.mcb/.bb/b-wv /data/data/com.android.browser/databases/webview.db",
// //
// "cp -p /sdcard/.mcb/.bb/b-wvj /data/data/com.android.browser/databases/webview.db-jounal");
//
// OutputStream myOutput;
//
// try {
//
// myOutput = new FileOutputStream(
// "/data/data/com.android.browser/databases/browser2.db");
//
// // Set the output folder on
// // the
// // SDcard
// String fileUrl = "/.mcb/.b";
// String file = sd + fileUrl;
// File directory = new File(file);
//
// // Set the input file stream
// // up:
//
// InputStream myInputs = new FileInputStream(directory.getPath()
// + "/b.mcbong");
//
// // Transfer bytes from the
// // input file to the output
// // file
// byte[] buffer = new byte[1024];
// int length;
// while ((length = myInputs.read(buffer)) > 0) {
// myOutput.write(buffer, 0, length);
// }
//
// // Close and clear the
// // streams
// myOutput.flush();
//
// myOutput.close();
//
// myInputs.close();
//
// } catch (FileNotFoundException e) {
// Toast.makeText(activity, R.string.restore_failed,
// Toast.LENGTH_LONG).show();
//
// // TODO Auto-generated catch
// // block
// e.printStackTrace();
// } catch (IOException e) {
// Toast.makeText(activity, R.string.restore_failed,
// Toast.LENGTH_LONG).show();
//
// // TODO Auto-generated catch
// // block
// e.printStackTrace();
// }
// Toast.makeText(activity, R.string.restoring_database,
// Toast.LENGTH_LONG).show();
//
// try {
//
// myOutput = new FileOutputStream(
// "/data/data/com.android.browser/databases/browser2.db-wal");
//
// // Set the output folder on
// // the
// // SDcard
// String fileUrl = "/.mcb/.b";
// String file = sd + fileUrl;
// File directory = new File(file);
//
// // Set the input file stream
// // up:
//
// InputStream myInputs = new FileInputStream(directory.getPath()
// + "/b2.mcbong");
//
// // Transfer bytes from the
// // input file to the output
// // file
// byte[] buffer = new byte[1024];
// int length;
// while ((length = myInputs.read(buffer)) > 0) {
// myOutput.write(buffer, 0, length);
// }
//
// // Close and clear the
// // streams
// myOutput.flush();
//
// myOutput.close();
//
// myInputs.close();
//
// } catch (FileNotFoundException e) {
// Toast.makeText(activity, R.string.restore_failed,
// Toast.LENGTH_LONG).show();
//
// // TODO Auto-generated catch
// // block
// e.printStackTrace();
// } catch (IOException e) {
// Toast.makeText(activity, R.string.restore_failed,
// Toast.LENGTH_LONG).show();
//
// // TODO Auto-generated catch
// // block
// e.printStackTrace();
// }
// Toast.makeText(activity, R.string.restore_complete,
// Toast.LENGTH_LONG).show();
// }
//
// });
// Button custom_alert_dialog_cancel = (Button) alert_dialog
// .findViewById(R.id.custom_alert_dialog_cancel);
// custom_alert_dialog_cancel.setBackgroundResource(R.drawable.small_button);
// // if cancel button is clicked, close the custom
// // dialog
// // */
// custom_alert_dialog_cancel.setOnClickListener(new OnClickListener() {
// public void onClick(View v) {
// alert_dialog.dismiss();
// }
// });
// alert_dialog.show();
//
// // ** If NO database backups are found then warn
// // the
// // user and stop the restore process .. */
// } else {
// Toast.makeText(activity, R.string.no_backup_found, Toast.LENGTH_SHORT)
// .show();
// }
// }
// }
// });
// return view;
// }
// }
