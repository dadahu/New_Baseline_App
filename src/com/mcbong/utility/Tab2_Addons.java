
package com.mcbong.utility;

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

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Tab2_Addons extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // ** Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.tab2_addons, container, false);
        final Activity activity = getActivity();
        // ** Define buttons and set image resources .. */
        final Button button_install_adw = (Button) view.findViewById(R.id.button_install_adw);
        button_install_adw.setBackgroundResource(R.drawable.button);
        final Button button_install_golauncher = (Button) view
                .findViewById(R.id.button_install_golauncher);
        button_install_golauncher.setBackgroundResource(R.drawable.button);
        final Button button_install_nova = (Button) view.findViewById(R.id.button_install_nova);
        button_install_nova.setBackgroundResource(R.drawable.button);
        final Button button_install_ssdred = (Button) view.findViewById(R.id.button_install_ssdred);
        button_install_ssdred.setBackgroundResource(R.drawable.button);
        final Button button_install_popstyle = (Button) view
                .findViewById(R.id.button_install_popstyle);
        button_install_popstyle.setBackgroundResource(R.drawable.button);
        final Button button_install_redics = (Button) view.findViewById(R.id.button_install_redics);
        button_install_redics.setBackgroundResource(R.drawable.button);

        button_install_adw.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // ** call custom dialog into view and set characteristic's
                // */
                final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
                alert_dialog.getWindow();
                alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alert_dialog.setContentView(R.layout.custom_alert_dialog);
                final TextView title = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview_title);
                title.setText(R.string.install_adw);
                final TextView alert_text = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview);
                alert_text.setText(R.string.confirm);
                final ImageView image = (ImageView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_image);
                image.setImageResource(R.drawable.adw);

                // * set up button image resources */
                final Button custom_alert_dialog_ok = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_ok);
                custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                // ** if button is clicked, execute the shell commands
                // through root-tools */
                custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {

                        // ** Call download_adw method
                        download_adw(activity, alert_dialog);

                    }

                    /**
                     * @param activity
                     * @param alert_dialog
                     */
                    private void download_adw(final Activity activity, final Dialog alert_dialog) {
                        // ** Remove any old downloads of
                        // ADW-Launcher from /Download folder before
                        // downloading new version */
                        CommandCapture command = new CommandCapture(0,
                                "rm /sdcard/Download/ADW-Launcher.apk");
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

                        // ** download ADW Launcher from server ..
                        // */
                        final String url = "http://dl.dropbox.com/u/18271886/mcb/launchers/adw.apk";
                        DownloadManager.Request request = new DownloadManager.Request(Uri
                                .parse(url));
                        request.setDescription("ADW	Launcher Download");
                        request.setTitle("ADW Launcher Download Complete");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            request.allowScanningByMediaScanner();
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        }
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                                "ADW-Launcher.apk");

                        // ** get download service and enqueue file
                        // */
                        DownloadManager manager = (DownloadManager) activity
                                .getSystemService(Context.DOWNLOAD_SERVICE);
                        manager.enqueue(request);
                        alert_dialog.dismiss();
                        Toast.makeText(activity, R.string.downloading_launcher, Toast.LENGTH_SHORT)
                                .show();

                        // ** Set up broadcast receiver to detect
                        // when the download completes.. */
                        BroadcastReceiver onComplete = new BroadcastReceiver() {
                            public void onReceive(Context ctxt, Intent intent) {
                                Toast.makeText(ctxt, R.string.installing_launcher,
                                        Toast.LENGTH_SHORT).show();
                                final Intent install = new Intent(Intent.ACTION_VIEW);
                                install.setDataAndType(Uri.fromFile(new File(Environment
                                        .getExternalStorageDirectory()
                                        + "/Download/"
                                        + "ADW-Launcher.apk")),
                                        "application/vnd.android.package-archive");
                                startActivity(install);
                            }
                        };
                        activity.registerReceiver(onComplete, new IntentFilter(
                                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
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
        }

        );
        button_install_golauncher.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // ** call custom dialog into view and set characteristic's
                // */
                final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
                alert_dialog.getWindow();
                alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alert_dialog.setContentView(R.layout.custom_alert_dialog);
                final TextView title = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview_title);
                title.setText(R.string.install_golauncher);
                final TextView alert_text = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview);
                alert_text.setText(R.string.confirm);
                final ImageView image = (ImageView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_image);
                image.setImageResource(R.drawable.golauncher);

                // * set up button image resources */
                final Button custom_alert_dialog_ok = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_ok);
                custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                // ** if button is clicked, execute the shell commands
                // through root-tools */
                custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {

                        // ** Call download_golauncher method
                        download_golauncher(activity, alert_dialog);
                    }

                    /**
                     * @param activity
                     * @param alert_dialog
                     */
                    private void download_golauncher(final Activity activity,
                            final Dialog alert_dialog) {
                        // ** Remove any old downloads of
                        // Go-Launcher from /Download folder before
                        // downloading new version */
                        CommandCapture command = new CommandCapture(0,
                                "rm /sdcard/Download/Go-Launcher.apk");
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

                        // ** download Go-Launcher EX from server ..
                        // */
                        final String url = "http://dl.dropbox.com/u/18271886/mcb/launchers/golauncherex.apk";
                        DownloadManager.Request request = new DownloadManager.Request(Uri
                                .parse(url));
                        request.setDescription("Go-Launcher EX Download");
                        request.setTitle("Go-Launcher EX Download Complete");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            request.allowScanningByMediaScanner();
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        }
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                                "Go-Launcher.apk");

                        // ** get download service and enqueue file
                        // */
                        DownloadManager manager = (DownloadManager) activity
                                .getSystemService(Context.DOWNLOAD_SERVICE);
                        manager.enqueue(request);
                        alert_dialog.dismiss();
                        Toast.makeText(activity, R.string.downloading_launcher, Toast.LENGTH_SHORT)
                                .show();

                        // ** Set up broadcast receiver to detect
                        // when the download completes.. */
                        BroadcastReceiver onComplete = new BroadcastReceiver() {
                            public void onReceive(Context ctxt, Intent intent) {
                                Toast.makeText(ctxt, R.string.installing_launcher,
                                        Toast.LENGTH_SHORT).show();
                                final Intent install = new Intent(Intent.ACTION_VIEW);
                                install.setDataAndType(Uri.fromFile(new File(Environment
                                        .getExternalStorageDirectory()
                                        + "/Download/"
                                        + "Go-Launcher.apk")),
                                        "application/vnd.android.package-archive");
                                startActivity(install);
                            }
                        };
                        activity.registerReceiver(onComplete, new IntentFilter(
                                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
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
        }

        );
        button_install_nova.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // ** call custom dialog into view and set characteristic's
                // */
                final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
                alert_dialog.getWindow();
                alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alert_dialog.setContentView(R.layout.custom_alert_dialog);
                final TextView title = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview_title);
                title.setText(R.string.install_nova);
                final TextView alert_text = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview);
                alert_text.setText(R.string.confirm);
                final ImageView image = (ImageView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_image);
                image.setImageResource(R.drawable.nova);

                // * set up button image resources */
                final Button custom_alert_dialog_ok = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_ok);
                custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                // ** if button is clicked, execute the shell commands
                // through root-tools */
                custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {

                        // ** Call download_nova method
                        download_nova(activity, alert_dialog);
                    }

                    /**
                     * @param activity
                     * @param alert_dialog
                     */
                    private void download_nova(final Activity activity, final Dialog alert_dialog) {
                        // ** Remove any old downloads of
                        // Nova-Launcher from /Download folder
                        // before downloading new version */
                        CommandCapture command = new CommandCapture(0,
                                "rm /sdcard/Download/Nova-Launcher.apk");
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

                        // ** download Nova-Launcher from server ..
                        // */
                        final String url = "http://dl.dropbox.com/u/18271886/mcb/launchers/nova.apk";
                        DownloadManager.Request request = new DownloadManager.Request(Uri
                                .parse(url));
                        request.setDescription("Nova Launcher EX Download");
                        request.setTitle("Nova Launcher EX Download Complete");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            request.allowScanningByMediaScanner();
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        }
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                                "Nova-Launcher.apk");

                        // ** get download service and enqueue file
                        // */
                        DownloadManager manager = (DownloadManager) activity
                                .getSystemService(Context.DOWNLOAD_SERVICE);
                        manager.enqueue(request);
                        alert_dialog.dismiss();
                        Toast.makeText(activity, R.string.downloading_launcher, Toast.LENGTH_SHORT)
                                .show();

                        // ** Set up broadcast receiver to detect
                        // when the download completes.. */
                        BroadcastReceiver onComplete = new BroadcastReceiver() {
                            public void onReceive(Context ctxt, Intent intent) {
                                Toast.makeText(ctxt, R.string.installing_launcher,
                                        Toast.LENGTH_SHORT).show();
                                final Intent install = new Intent(Intent.ACTION_VIEW);
                                install.setDataAndType(Uri.fromFile(new File(Environment
                                        .getExternalStorageDirectory()
                                        + "/Download/"
                                        + "Nova-Launcher.apk")),
                                        "application/vnd.android.package-archive");
                                startActivity(install);
                            }
                        };
                        activity.registerReceiver(onComplete, new IntentFilter(
                                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
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
        }

        );
        button_install_ssdred.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // ** call custom dialog into view and set characteristic's
                // */
                final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
                alert_dialog.getWindow();
                alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alert_dialog.setContentView(R.layout.custom_alert_dialog);
                final TextView title = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview_title);
                title.setText(R.string.install_ssdred);
                final TextView alert_text = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview);
                alert_text.setText(R.string.confirm);
                final ImageView image = (ImageView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_image);
                image.setImageResource(R.drawable.ssdred);

                // * set up button image resources */
                final Button custom_alert_dialog_ok = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_ok);
                custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                // ** if button is clicked, execute the shell commands
                // through root-tools */
                custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {

                        // ** Call download_ssd method
                        download_ssd(activity, alert_dialog);
                    }

                    /**
                     * @param activity
                     * @param alert_dialog
                     */
                    private void download_ssd(final Activity activity, final Dialog alert_dialog) {
                        // ** Remove any old downloads of SSD-Red
                        // from /Download folder before downloading
                        // new version */
                        CommandCapture command = new CommandCapture(0,
                                "rm /sdcard/Download/ssdred.apk");
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

                        // ** download SSD-Red Theme from server ..
                        // */
                        final String url = "http://dl.dropbox.com/u/18271886/mcb/Themes/ssdred.apk";
                        DownloadManager.Request request = new DownloadManager.Request(Uri
                                .parse(url));
                        request.setDescription("SSD-Red Theme Download");
                        request.setTitle("SSD-Red Theme Download Complete");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            request.allowScanningByMediaScanner();
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        }
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                                "ssdred.apk");

                        // ** get download service and enqueue file
                        // */
                        DownloadManager manager = (DownloadManager) activity
                                .getSystemService(Context.DOWNLOAD_SERVICE);
                        manager.enqueue(request);
                        alert_dialog.dismiss();
                        Toast.makeText(activity, R.string.downloading_theme, Toast.LENGTH_SHORT)
                                .show();

                        // ** Set up broadcast receiver to detect
                        // when the download completes.. */
                        BroadcastReceiver onComplete = new BroadcastReceiver() {
                            public void onReceive(Context ctxt, Intent intent) {
                                Toast.makeText(ctxt, R.string.installing_theme, Toast.LENGTH_SHORT)
                                        .show();
                                final Intent install = new Intent(Intent.ACTION_VIEW);
                                install.setDataAndType(Uri.fromFile(new File(Environment
                                        .getExternalStorageDirectory()
                                        + "/Download/"
                                        + "ssdred.apk")), "application/vnd.android.package-archive");
                                startActivity(install);
                            }
                        };
                        activity.registerReceiver(onComplete, new IntentFilter(
                                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
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
        }

        );
        button_install_popstyle.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // ** call custom dialog into view and set characteristic's
                // */
                final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
                alert_dialog.getWindow();
                alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alert_dialog.setContentView(R.layout.custom_alert_dialog);
                final TextView title = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview_title);
                title.setText(R.string.install_popstyle);
                final TextView alert_text = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview);
                alert_text.setText(R.string.confirm);
                final ImageView image = (ImageView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_image);
                image.setImageResource(R.drawable.popstyle);

                // * set up button image resources */
                final Button custom_alert_dialog_ok = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_ok);
                custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                // ** if button is clicked, execute the shell commands
                // through root-tools */
                custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {

                        // ** Call download_popstyle method
                        download_popstyle(activity, alert_dialog);
                    }

                    /**
                     * @param activity
                     * @param alert_dialog
                     */
                    private void download_popstyle(final Activity activity,
                            final Dialog alert_dialog) {
                        // ** Remove any old downloads of PopStyle
                        // Theme from /Download folder before
                        // downloading new version */
                        CommandCapture command = new CommandCapture(0,
                                "rm /sdcard/Download/popstyle.apk");
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

                        // ** download PopStyle Theme from server ..
                        // */
                        final String url = "http://dl.dropbox.com/u/18271886/mcb/Themes/popstyle.apk";
                        DownloadManager.Request request = new DownloadManager.Request(Uri
                                .parse(url));
                        request.setDescription("PopStyle Theme Download");
                        request.setTitle("PopStyle Theme Download Complete");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            request.allowScanningByMediaScanner();
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        }
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                                "popstyle.apk");

                        // ** get download service and enqueue file
                        // */
                        DownloadManager manager = (DownloadManager) activity
                                .getSystemService(Context.DOWNLOAD_SERVICE);
                        manager.enqueue(request);
                        alert_dialog.dismiss();
                        Toast.makeText(activity, R.string.downloading_theme, Toast.LENGTH_SHORT)
                                .show();

                        // ** Set up broadcast receiver to detect
                        // when the download completes.. */
                        BroadcastReceiver onComplete = new BroadcastReceiver() {
                            public void onReceive(Context ctxt, Intent intent) {
                                Toast.makeText(ctxt, R.string.installing_theme, Toast.LENGTH_SHORT)
                                        .show();
                                final Intent install = new Intent(Intent.ACTION_VIEW);
                                install.setDataAndType(Uri.fromFile(new File(Environment
                                        .getExternalStorageDirectory()
                                        + "/Download/"
                                        + "popstyle.apk")),
                                        "application/vnd.android.package-archive");
                                startActivity(install);
                            }
                        };
                        activity.registerReceiver(onComplete, new IntentFilter(
                                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
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
        }

        );
        button_install_redics.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                // ** call custom dialog into view and set characteristic's
                // */
                final Dialog alert_dialog = new Dialog(activity, R.style.Theme_Dialog_Translucent);
                alert_dialog.getWindow();
                alert_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alert_dialog.setContentView(R.layout.custom_alert_dialog);
                final TextView title = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview_title);
                title.setText(R.string.install_redics);
                final TextView alert_text = (TextView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_textview);
                alert_text.setText(R.string.confirm);
                final ImageView image = (ImageView) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_image);
                image.setImageResource(R.drawable.redics);

                // * set up button image resources */
                final Button custom_alert_dialog_ok = (Button) alert_dialog
                        .findViewById(R.id.custom_alert_dialog_ok);
                custom_alert_dialog_ok.setBackgroundResource(R.drawable.small_button);

                // ** if button is clicked, execute the shell commands
                // through root-tools */
                custom_alert_dialog_ok.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {

                        download_redics(activity, alert_dialog);
                    }

                    /**
                     * @param activity
                     * @param alert_dialog
                     */
                    private void download_redics(final Activity activity, final Dialog alert_dialog) {
                        // ** Remove any old downloads of Red-ICS
                        // Theme from /Download folder before
                        // downloading new version */
                        CommandCapture command = new CommandCapture(0,
                                "rm /sdcard/Download/redics.apk");
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

                        // ** download Red-ICS Theme from server ..
                        // */
                        final String url = "http://dl.dropbox.com/u/18271886/mcb/Themes/redics.apk";
                        DownloadManager.Request request = new DownloadManager.Request(Uri
                                .parse(url));
                        request.setDescription("Red-ICS Theme Download");
                        request.setTitle("Red-ICS Theme Download Complete");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            request.allowScanningByMediaScanner();
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        }
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                                "redics.apk");

                        // ** get download service and enqueue file
                        // */
                        DownloadManager manager = (DownloadManager) activity
                                .getSystemService(Context.DOWNLOAD_SERVICE);
                        manager.enqueue(request);
                        alert_dialog.dismiss();
                        Toast.makeText(activity, R.string.downloading_theme, Toast.LENGTH_SHORT)
                                .show();

                        // ** Set up broadcast receiver to detect
                        // when the download completes.. */
                        BroadcastReceiver onComplete = new BroadcastReceiver() {
                            public void onReceive(Context ctxt, Intent intent) {
                                Toast.makeText(ctxt, R.string.installing_theme, Toast.LENGTH_SHORT)
                                        .show();
                                final Intent install = new Intent(Intent.ACTION_VIEW);
                                install.setDataAndType(Uri.fromFile(new File(Environment
                                        .getExternalStorageDirectory()
                                        + "/Download/"
                                        + "redics.apk")), "application/vnd.android.package-archive");
                                startActivity(install);
                            }
                        };
                        activity.registerReceiver(onComplete, new IntentFilter(
                                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
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
}
