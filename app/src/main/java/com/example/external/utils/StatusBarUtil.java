package com.example.external.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.FloatRange;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

import com.example.external.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class StatusBarUtil {

    public static final int MIN_API = 19;
    public static int DEFAULT_COLOR = 0;
    public static float DEFAULT_ALPHA = 0;//Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 0.2f : 0.3f;

    //<editor-fold desc="">
    public static void immersive(Activity activity) {
        immersive(activity, DEFAULT_COLOR, DEFAULT_ALPHA);
    }

    public static void immersive(Activity activity, int color, @FloatRange(from = 0.0, to = 1.0) float alpha) {
        immersive(activity.getWindow(), color, alpha);
    }

    public static void immersive(Activity activity, int color) {
        immersive(activity.getWindow(), color, 1f);
    }

    public static void immersive(Window window) {
        immersive(window, DEFAULT_COLOR, DEFAULT_ALPHA);
    }

    public static void immersive(Window window, int color) {
        immersive(window, color, 1f);
    }

    public static void immersive(Window window, int color, @FloatRange(from = 0.0, to = 1.0) float alpha) {
        if (Build.VERSION.SDK_INT >= 21) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(mixtureColor(color, alpha));

            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        } else if (Build.VERSION.SDK_INT >= 19) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            setTranslucentView((ViewGroup) window.getDecorView(), color, alpha);
        } else if (Build.VERSION.SDK_INT >= MIN_API && Build.VERSION.SDK_INT > 16) {
            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        }
    }
    //</editor-fold>

    //<editor-fold desc="DarkMode">
    public static void darkMode(Activity activity, boolean dark) {
        if (isFlyme4Later()) {
            darkModeForFlyme4(activity.getWindow(), dark);
        } else if (isMIUI6Later()) {
            darkModeForMIUI6(activity.getWindow(), dark);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            darkModeForM(activity.getWindow(), dark);
        }
    }

    public static void darkMode(Activity activity) {
        darkMode(activity.getWindow(), DEFAULT_COLOR, DEFAULT_ALPHA);
    }

    public static void darkMode(Activity activity, int color, @FloatRange(from = 0.0, to = 1.0) float alpha) {
        darkMode(activity.getWindow(), color, alpha);
    }

    public static void darkMode(Window window, int color, @FloatRange(from = 0.0, to = 1.0) float alpha) {
        if (isFlyme4Later()) {
            darkModeForFlyme4(window, true);
            immersive(window, color, alpha);
        } else if (isMIUI6Later()) {
            darkModeForMIUI6(window, true);
            immersive(window, color, alpha);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            darkModeForM(window, true);
            immersive(window, color, alpha);
        } else if (Build.VERSION.SDK_INT >= 19) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            setTranslucentView((ViewGroup) window.getDecorView(), color, alpha);
        } else {
            immersive(window, color, alpha);
        }
    }

    //------------------------->

    @RequiresApi(Build.VERSION_CODES.M)
    private static void darkModeForM(Window window, boolean dark) {
        int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
        if (dark) {
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        } else {
            systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }
        window.getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    public static boolean darkModeForFlyme4(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams e = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(e);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }

                meizuFlags.setInt(e, value);
                window.setAttributes(e);
                result = true;
            } catch (Exception var8) {
                Log.e("StatusBar", "darkIcon: failed");
            }
        }

        return result;
    }

    public static boolean darkModeForMIUI6(Window window, boolean darkmode) {
        Class<? extends Window> clazz = window.getClass();
        boolean isSuccess;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            darkModeForM(window, darkmode);
            isSuccess = true;
        } else {
            try {
                int darkModeFlag = 0;
                Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                extraFlagField.invoke(window, darkmode ? darkModeFlag : 0, darkModeFlag);
                isSuccess = true;
            } catch (Exception e) {
                e.printStackTrace();
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public static boolean isFlyme4Later() {
        return Build.FINGERPRINT.contains("Flyme_OS_4")
                || Build.VERSION.INCREMENTAL.contains("Flyme_OS_4")
                || Pattern.compile("Flyme OS [4|5]", Pattern.CASE_INSENSITIVE).matcher(Build.DISPLAY).find();
    }

    public static boolean isMIUI6Later() {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method mtd = clz.getMethod("get", String.class);
            String val = (String) mtd.invoke(null, "ro.miui.ui.version.name");
            val = val.replaceAll("[vV]", "");
            int version = Integer.parseInt(val);
            return version >= 6;
        } catch (Exception e) {
            return false;
        }
    }
    //</editor-fold>

    public static void setPadding(Context context, View view) {
        if (Build.VERSION.SDK_INT >= MIN_API) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + getStatusBarHeight(context),
                    view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    public static void setPaddingSmart(Context context, View view) {
        try {
            if (Build.VERSION.SDK_INT >= MIN_API) {
                ViewGroup.LayoutParams lp = view.getLayoutParams();
                if (lp != null && lp.height > 0) {
                    lp.height += getStatusBarHeight(context);//增高
                }
                view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + getStatusBarHeight(context),
                        view.getPaddingRight(), view.getPaddingBottom());
            }
        } catch (Exception e) {
            Log.e("errorMsg", "setPaddingSmart: " + e.getMessage());
        }
    }

    public static void setHeightAndPadding(Context context, View view) {
        if (Build.VERSION.SDK_INT >= MIN_API) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            lp.height += getStatusBarHeight(context);//增高
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + getStatusBarHeight(context),
                    view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    public static void setMargin(Context context, View view) {
        if (Build.VERSION.SDK_INT >= MIN_API) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            if (lp instanceof ViewGroup.MarginLayoutParams) {
                ((ViewGroup.MarginLayoutParams) lp).topMargin += getStatusBarHeight(context);//增高
            }
            view.setLayoutParams(lp);
        }
    }

    public static void setTranslucentView(ViewGroup container, int color, @FloatRange(from = 0.0, to = 1.0) float alpha) {
        if (Build.VERSION.SDK_INT >= 19) {
            int mixtureColor = mixtureColor(color, alpha);
            View translucentView = container.findViewById(android.R.id.custom);
            if (translucentView == null && mixtureColor != 0) {
                translucentView = new View(container.getContext());
                translucentView.setId(android.R.id.custom);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(container.getContext()));
                container.addView(translucentView, lp);
            }
            if (translucentView != null) {
                translucentView.setBackgroundColor(mixtureColor);
            }
        }
    }

    public static int mixtureColor(int color, @FloatRange(from = 0.0, to = 1.0) float alpha) {
        int a = (color & 0xff000000) == 0 ? 0xff : color >>> 24;
        return (color & 0x00ffffff) | (((int) (a * alpha)) << 24);
    }

    public static int getStatusBarHeight(Context context) {
        int result = 24;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelSize(resId);
        } else {
            result = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    result, Resources.getSystem().getDisplayMetrics());
        }
        return result;
    }

    public static void setStatusBarBgWhite(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(activity.getResources().getColor(R.color.global_bg_white));
        }

    }

    public static int getStatusBarHeight() {
        return Resources.getSystem().getDimensionPixelSize(
                Resources.getSystem().getIdentifier("status_bar_height",
                        "dimen", "android"));
    }

    public static int getNavigationBarHeight() {
        Resources resources = Resources.getSystem();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    public static void setStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.immersive(activity, activity.getResources().getColor(R.color.white), 0F);
            StatusBarUtil.darkMode(activity, true);
            Window window = activity.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//此处设定状态栏文字颜色为白色
        } else {
            StatusBarUtil.immersive(activity, ResourcesCompat.getColor(activity.getResources(),
                    R.color.status_bar_color, activity.getTheme()), 1F);
        }
    }

    public static void setTextColor(Activity activity) {
            StatusBarUtil.immersive(activity, activity.getResources().getColor(R.color.white), 0F);
            StatusBarUtil.darkMode(activity, true);
            Window window = activity.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//此处设定状态栏文字颜色为白色
    }

    public static void setTransColorStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.immersive(activity, activity.getResources().getColor(R.color.white), 0F);
            StatusBarUtil.darkMode(activity, true);
        } else {
            StatusBarUtil.immersive(activity, activity.getResources().getColor(android.R.color.transparent), 0.43F);
        }
    }
}
