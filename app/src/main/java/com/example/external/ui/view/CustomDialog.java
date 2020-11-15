package com.example.external.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


/**
 * Create by hao on 2019-06-18
 */
public class CustomDialog extends Dialog {
    private Context context;
    private int maxHeight, height, width, gravity;
    private boolean cancelTouchout;
    private View view;

    private CustomDialog(Builder builder) {
        super(builder.context);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        gravity = builder.gravity;
        maxHeight = builder.maxHeight;
        cancelTouchout = builder.cancelTouchout;
        view = builder.view;
    }


    private CustomDialog(Builder builder, int resStyle) {
        super(builder.context, resStyle);
        context = builder.context;
        height = builder.height;
        maxHeight = builder.maxHeight;
        width = builder.width;
        gravity = builder.gravity;
        cancelTouchout = builder.cancelTouchout;
        view = builder.view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(view);
        setCanceledOnTouchOutside(cancelTouchout);
        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = gravity;
        lp.height = height;
        lp.width = width;
        win.setAttributes(lp);

    }

    public static final class Builder {

        private Context context;
        private int maxHeight, height, width, gravity;
        private boolean cancelTouchout;
        private View view;
        private int resStyle = -1;


        public Builder(Context context) {
            this.context = context;

        }


        public Builder view(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }

        public View getView() {
            return view;
        }

        public Builder heightDimenRes(int dimenRes) {
            if (dimenRes == ViewGroup.LayoutParams.MATCH_PARENT) {
                height = ViewGroup.LayoutParams.MATCH_PARENT;
            } else if (dimenRes == ViewGroup.LayoutParams.WRAP_CONTENT) {
                height = ViewGroup.LayoutParams.WRAP_CONTENT;
            } else {
                height = context.getResources().getDimensionPixelOffset(dimenRes);
            }
            return this;
        }

        public Builder widthDimenRes(int dimenRes) {
            if (dimenRes == ViewGroup.LayoutParams.MATCH_PARENT) {
                width = ViewGroup.LayoutParams.MATCH_PARENT;
            } else if (dimenRes == ViewGroup.LayoutParams.WRAP_CONTENT) {
                width = ViewGroup.LayoutParams.WRAP_CONTENT;
            } else {
                width = context.getResources().getDimensionPixelOffset(dimenRes);
            }
            return this;
        }

        public Builder setGravity(int gravityRes) {
            gravity = gravityRes;
            return this;
        }

        public Builder style(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Builder cancelTouchout(boolean val) {
            cancelTouchout = val;
            return this;
        }

        public Builder addViewOnclick(int viewRes, View.OnClickListener listener) {
            view.findViewById(viewRes).setOnClickListener(listener);
            return this;
        }


        public CustomDialog build() {
            if (resStyle != -1) {
                return new CustomDialog(this, resStyle);
            } else {
                return new CustomDialog(this);
            }
        }
    }
}