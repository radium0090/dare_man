package com.ls.dareman.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageButton;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.ls.dareman.R;

public class DialogFragmentHelper {
    private static final String DIALOG_POSITIVE = "OK";
    private static final String DIALOG_NEGATIVE = "Cancel";
    private static final String TAG = "DialogFragmentHelper";

    /**
     * dialog theme
     */
    private static final int DIALOG_THEME = R.style.Base_AlertDialog;
    private static final int BANNER_THEME = R.style.Banner_Dialog_Theme;

    /**
     * text dialog
     */
    private static final String TIPS_TAG = TAG + ":text";
    public static void textDialog(FragmentManager fragmentManager, String message){
        textDialog(fragmentManager, message, true, null);
    }

    public static void textDialog(FragmentManager fragmentManager, String message, boolean cancelable){
        textDialog(fragmentManager, message, cancelable, null);
    }

    public static void textDialog(FragmentManager fragmentManager, final String message, boolean cancelable, DialogFragmentUtils.OnDialogCancelListener cancelListener) {
        DialogFragmentUtils dialogFragment = DialogFragmentUtils.newInstance(new DialogFragmentUtils.OnCallDialog() {
            @Override
            public Dialog getDialog(Context context) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context, DIALOG_THEME);
                builder.setMessage(message);
                return builder.create();
            }
        }, cancelable, cancelListener);
        dialogFragment.show(fragmentManager, TIPS_TAG);
    }

    /**
     * event banner dialog
     */
    private static final String IMG_TAG = TAG + ":image";
    public static DialogFragment eventBannerDialog(final FragmentManager fragmentManager, boolean cancelable) {
        final DialogFragmentUtils dialogFragmentUtils = DialogFragmentUtils.newInstance(new DialogFragmentUtils.OnCallDialog() {
            @Override
            public Dialog getDialog(final Context context) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context, BANNER_THEME);
                final View eventView = View.inflate(context, R.layout.layout_dialog, null);
                ImageButton closeBtn = (ImageButton) eventView.findViewById(R.id.close_banner);
                builder.setView(eventView);
                final AlertDialog dialog = builder.create();
                closeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("button clicked");
                        dialog.dismiss();
                    }
                });
                return dialog;
            }
        }, cancelable, null);
        dialogFragmentUtils.show(fragmentManager, IMG_TAG);
        return null;
    }

    /**
     * list item dialog
     */
    private static final String LIST_TAG = TAG + ":list";
    public static DialogFragment listDialog(FragmentManager fragmentManager, final String title, final String[] items, final DialogResultListener<Integer> resultListener, boolean cancelable ){
        DialogFragmentUtils dialogFragment = DialogFragmentUtils.newInstance(context -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, DIALOG_THEME);
            builder.setTitle(title);
            builder.setItems(items, (dialog, which) -> {
                if(resultListener != null){
                    resultListener.onDataResult(which);
                }
            });
            return builder.create();
        }, cancelable, null);
        dialogFragment.show(fragmentManager, LIST_TAG);
        return null;
    }

    /**
     * confirm dialog
     */
    private static final String CONFIRM_TAG = TAG + ":confirm";
    public static void confirmDialog(FragmentManager manager, String title, String msg, DialogResultListener<Integer> dListener) {
        confirmDialog(manager, title, msg, dListener, true, null);
    }

    public static void confirmDialog(FragmentManager manager, final String title, final String msg, final DialogResultListener<Integer> dListener, boolean cancelable, DialogFragmentUtils.OnDialogCancelListener cListener) {
        DialogFragmentUtils dialog = DialogFragmentUtils.newInstance(new DialogFragmentUtils.OnCallDialog() {
            @Override
            public Dialog getDialog(final Context context) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context, DIALOG_THEME);
                builder.setTitle(title);
                builder.setMessage(msg);
                builder.setPositiveButton(DIALOG_POSITIVE, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dListener != null) {
                            dListener.onDataResult(which);
                        }
                    }
                });
                builder.setNegativeButton(DIALOG_NEGATIVE, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dListener != null) {
                            dListener.onDataResult(which);
                        }
                    }
                });
                return builder.create();
            }
        }, cancelable, cListener);
        dialog.show(manager, CONFIRM_TAG);
    }
}
