package com.advanz101.quickaction.filter;

/**
 * Created by advanz101 on 8/11/17.
 */

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.PopupWindow;

public class PopupHelper {

    public static PopupWindow newBasicPopupWindow(Context context) {
        final PopupWindow window = new PopupWindow(context);

        // when a touch even happens outside of the window
        // make the window go away
        window.setTouchInterceptor(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    window.dismiss();
                    return true;
                }
                return false;
            }
        });

        window.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        window.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        window.setTouchable(true);
        window.setFocusable(true);
        window.setOutsideTouchable(true);

        window.setBackgroundDrawable(new BitmapDrawable());

        return window;
    }



}
