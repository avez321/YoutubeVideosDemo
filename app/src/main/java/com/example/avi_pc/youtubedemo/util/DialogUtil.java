package com.example.avi_pc.youtubedemo.util;

import android.app.Dialog;
import android.content.Context;

import android.widget.Toast;

import com.example.avi_pc.youtubedemo.R;


public class DialogUtil {
    public static void showPleaseWaitDialog(Dialog dialog) {
        dialog.setContentView(R.layout.layout_progress_dialog);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void hideDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) dialog.dismiss();
    }

    public void showToast(String message, int duration, Context context) {
        Toast.makeText(context, message, duration).show();
    }
}
