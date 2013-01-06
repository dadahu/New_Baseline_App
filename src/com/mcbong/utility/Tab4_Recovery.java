
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.stericson.RootTools.CommandCapture;
import com.stericson.RootTools.RootTools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

public class Tab4_Recovery extends Fragment {

    public String line = "";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // ** Inflate the layout for this fragment */
        final View view = inflater.inflate(R.layout.tab4_recovery, container, false);
        final Activity activity = getActivity();
        // ** Call check_adb_status method
        check_adb_status(view);

        // ** Define buttons.. */
        final Button button_reboot = (Button) view.findViewById(R.id.button_reboot);
        button_reboot.setBackgroundResource(R.drawable.button);
        final Button button_reboot_recovery = (Button) view
                .findViewById(R.id.button_reboot_recovery);
        button_reboot_recovery.setBackgroundResource(R.drawable.button);
        final Button button_power_off = (Button) view.findViewById(R.id.button_power_off);
        button_power_off.setBackgroundResource(R.drawable.button);
        final Button button_wireless_adb_enable = (Button) view
                .findViewById(R.id.button_wireless_adb_enable);
        button_wireless_adb_enable.setBackgroundResource(R.drawable.button);
        final Button button_wireless_adb_disable = (Button) view
                .findViewById(R.id.button_wireless_adb_disable);

        button_reboot.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // ** call custom dialog into view and set characteristic's
                // */
                final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
                alert_dialog.getWindow();
                alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alert_dialog.setContentView(R.layout.custom_alert_dialog);
                TextView title = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview_title);
                title.setText(R.string.dialog_title_reboot_device);
                TextView alert_text = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview);
                alert_text.setText(R.string.confirm);
                ImageView image = (ImageView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_image);
                image.setImageResource(R.drawable.reboot);

                // * set up button image resources */
                Button custom_alert_dialog_ok = (Button) alert_dialog
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
                    private void rootshell_reboot() {
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
                Button custom_alert_dialog_cancel = (Button) alert_dialog
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
        button_reboot_recovery.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // ** call custom dialog into view and set characteristic's
                // */
                final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
                alert_dialog.getWindow();
                alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alert_dialog.setContentView(R.layout.custom_alert_dialog);
                TextView title = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview_title);
                title.setText(R.string.dialog_title_reboot_recovery);
                TextView alert_text = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview);
                alert_text.setText(R.string.confirm);
                ImageView image = (ImageView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_image);
                image.setImageResource(R.drawable.reboot_recovery);

                // * set up button image resources */
                Button custom_alert_dialog_ok = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_ok);
                custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                // ** if button is clicked, execute the shell commands
                // through root-tools */
                custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        // ** Call rootshell_reboot_recovery method
                        rootshell_reboot_recovery();
                    }

                    /**
                         * 
                         */
                    private void rootshell_reboot_recovery() {
                        CommandCapture command = new CommandCapture(0, "reboot recovery");
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
                Button custom_alert_dialog_cancel = (Button) alert_dialog
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
        button_power_off.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // ** call custom dialog into view and set characteristic's
                // */
                final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
                alert_dialog.getWindow();
                alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alert_dialog.setContentView(R.layout.custom_alert_dialog);
                TextView title = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview_title);
                title.setText(R.string.dialog_title_power_off_device);
                TextView alert_text = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview);
                alert_text.setText(R.string.confirm);
                ImageView image = (ImageView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_image);
                image.setImageResource(R.drawable.power_off);

                // * set up button image resources */
                Button custom_alert_dialog_ok = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_ok);
                custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                // ** if button is clicked, execute the shell commands
                // through root-tools */
                custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        // ** Call rootshell_shutdown method
                        rootshell_shutdown();
                    }

                    /**
                     * 
                     */
                    private void rootshell_shutdown() {
                        CommandCapture command = new CommandCapture(0, "reboot -p");
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
                Button custom_alert_dialog_cancel = (Button) alert_dialog
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
        button_wireless_adb_enable.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // ** Call rootshell_enable_wireless_adb method
                rootshell_enable_wireless_adb(view, activity);
            }

            /**
             * @param view
             * @param activity
             */
            private void rootshell_enable_wireless_adb(final View view, final Activity activity) {
                // ** Enable Wireless ADBD function */
                CommandCapture command = new CommandCapture(0, "setprop service.adb.tcp.port 5555",
                        "stop adbd", "start adbd");
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

                // ** Make checks to see if Wireless ADB has ACTUALLY been
                // Enabled .. */
                // ** .. And Set The TextView Accordingly */
                try {
                    Process adb_check = Runtime.getRuntime().exec("getprop   service.adb.tcp.port");
                    BufferedReader adb_check_IS = new BufferedReader(new InputStreamReader(
                            adb_check.getInputStream()));
                    line = adb_check_IS.readLine();
                    if (line.equals("5555")) {
                        final ImageView image = (ImageView) view
                                .findViewById(R.id.imageView_adb_status);
                        image.setImageResource(R.drawable.on);
                        final TextView ip = (TextView) view.findViewById(R.id.textView_ip);
                        // ** Get wireless ADB IP address to show when connected
                        // .. */
                        Process ip_check = Runtime.getRuntime().exec("ifconfig wlan0");
                        BufferedReader ip_check_IS = new BufferedReader(new InputStreamReader(
                                ip_check.getInputStream()));
                        line = ip_check_IS.readLine().substring(0, 22);
                        // final String output = new String(R.string.connect_to
                        // + line
                        // + R.string.connect_to_port);
                        final String output = new String("Connect To.." + line + ":5555");
                        ip.setText(output);
                        ip.setTextColor(getResources().getColor(R.color.Green));
                        Toast.makeText(activity, R.string.wireless_adb_enabled, Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        final ImageView image = (ImageView) view
                                .findViewById(R.id.imageView_adb_status);
                        image.setImageResource(R.drawable.off);
                        final TextView ip = (TextView) view.findViewById(R.id.textView_ip);
                        ip.setText(R.string.wireless_adb_disabled_ip);
                        ip.setTextColor(getResources().getColor(R.color.DarkRed));
                        Toast.makeText(activity, R.string.wireless_adb_disabled, Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (java.io.IOException e) {
                }
            }
        });
        button_wireless_adb_disable.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // ** Call rootshell_disable_wireless_adb method
                rootshell_disable_wireless_adb(view, activity);
            }

            /**
             * @param view
             * @param activity
             */
            private void rootshell_disable_wireless_adb(final View view, final Activity activity) {
                // ** Disable Wireless ADBD function */
                CommandCapture command = new CommandCapture(0, "setprop service.adb.tcp.port -1",
                        "stop adbd", "start adbd");
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

                // ** Make checks to see if Wireless ADB has ACTUALLY been
                // Disabled .. */
                // ** .. And Set The TextView Accordingly */
                try {
                    Process adb_check = Runtime.getRuntime().exec("getprop   service.adb.tcp.port");
                    BufferedReader adb_check_IS = new BufferedReader(new InputStreamReader(
                            adb_check.getInputStream()));
                    line = adb_check_IS.readLine();
                    if (line.equals("5555")) {
                        final ImageView image = (ImageView) view
                                .findViewById(R.id.imageView_adb_status);
                        image.setImageResource(R.drawable.on);
                        final TextView ip = (TextView) view.findViewById(R.id.textView_ip);
                        // ** Get wireless ADB IP address to show when connected
                        // .. */
                        Process ip_check = Runtime.getRuntime().exec("ifconfig wlan0");
                        BufferedReader ip_check_IS = new BufferedReader(new InputStreamReader(
                                ip_check.getInputStream()));
                        line = ip_check_IS.readLine().substring(0, 22);
                        // final String output = new String(R.string.connect_to
                        // + line
                        // + R.string.connect_to_port);
                        final String output = new String("Connect To.." + line + ":5555");
                        ip.setText(output);
                        ip.setTextColor(getResources().getColor(R.color.Green));
                        Toast.makeText(activity, R.string.wireless_adb_enabled, Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        final ImageView image = (ImageView) view
                                .findViewById(R.id.imageView_adb_status);
                        image.setImageResource(R.drawable.off);
                        final TextView ip = (TextView) view.findViewById(R.id.textView_ip);
                        ip.setText(R.string.wireless_adb_disabled_ip);
                        ip.setTextColor(getResources().getColor(R.color.DarkRed));
                        Toast.makeText(activity, R.string.wireless_adb_disabled, Toast.LENGTH_SHORT)
                                .show();
                    }
                } catch (java.io.IOException e) {
                }
            }

        });

        return view;
    }

    /**
     * @param view
     */
    private void check_adb_status(final View view) {
        // ** Set up Wireless ADB check and change an ImageView and TextView as
        // to whether its
        // enabled or not */
        try {
            Process adb_check = Runtime.getRuntime().exec("getprop   service.adb.tcp.port");
            BufferedReader adb_check_IS = new BufferedReader(new InputStreamReader(
                    adb_check.getInputStream()));
            line = adb_check_IS.readLine();
            if (line.equals("5555")) {
                final ImageView image = (ImageView) view.findViewById(R.id.imageView_adb_status);
                image.setImageResource(R.drawable.on);
                final TextView ip = (TextView) view.findViewById(R.id.textView_ip);
                // ** Get wireless ADB IP address to show when connected .. */
                Process ip_check = Runtime.getRuntime().exec("ifconfig wlan0");
                BufferedReader ip_check_IS = new BufferedReader(new InputStreamReader(
                        ip_check.getInputStream()));
                line = ip_check_IS.readLine().substring(0, 22);
                // final String output = new String(R.string.connect_to + line
                // + R.string.connect_to_port);
                final String output = new String("Connect To.." + line + ":5555");
                ip.setText(output);
                ip.setTextColor(getResources().getColor(R.color.Green));
            } else {
                final ImageView image = (ImageView) view.findViewById(R.id.imageView_adb_status);
                image.setImageResource(R.drawable.off);
                final TextView ip = (TextView) view.findViewById(R.id.textView_ip);
                ip.setText(R.string.wireless_adb_disabled_ip);
                ip.setTextColor(getResources().getColor(R.color.DarkRed));
            }
        } catch (java.io.IOException e) {
        }
    }
}
