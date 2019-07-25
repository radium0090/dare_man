package com.ls.dareman;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class DialogFragmentUtils extends DialogFragment {
    private OnDialogCancelListener mCancelListener;
    private OnCallDialog mCallDialog;

    /**
     * callback interface
     */
    public interface OnDialogCancelListener {
        void onCancel();
    }

    public interface OnCallDialog {
        Dialog getDialog(Context context);
    }

    /**
     * constructor
     * @param callDialog
     * @param cancelable
     * @return
     */
    public static DialogFragmentUtils newInstance(OnCallDialog callDialog, boolean cancelable) {
        return newInstance(callDialog, cancelable, null);
    }

    /**
     * Encapsulate constructor
     * @param callDialog
     * @param cancelable
     * @param cancelListener
     * @return
     */
    public static DialogFragmentUtils newInstance(OnCallDialog callDialog, boolean cancelable, OnDialogCancelListener cancelListener) {
        DialogFragmentUtils instance = new DialogFragmentUtils();
        instance.setCancelable(cancelable);
        instance.mCancelListener = cancelListener;
        instance.mCallDialog = callDialog;
        return instance;
    }

    /**
     * return dialog from callback, nonnull expected.
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return mCallDialog.getDialog(getActivity());
    }

    /**
     * before 5.0, keep dialog background with transparent
     * set window appearance for dialog
     */
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }

            Window window = getDialog().getWindow();
            WindowManager.LayoutParams windowParams = window.getAttributes();
            windowParams.dimAmount = 0.0f;
            window.setAttributes(windowParams);
        }
    }

    /**
     * cancel process
     * implement to call OnCancelListener
     * @param dialogInterface
     */
    @Override
    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        if (mCancelListener != null) {
            mCancelListener.onCancel();
        }
    }
}
