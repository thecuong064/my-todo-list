package asb.mytodolist.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Method;

public class DeviceUtils {
    /**
     * Check system keyboard visibility
     * @return true if visible
     */
    public static boolean isSoftKeyboardVisible(Context context) {
        try {
            final InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            final Method windowHeightMethod = InputMethodManager.class.getMethod("getInputMethodWindowVisibleHeight");
            final int height = (int) windowHeightMethod.invoke(manager);
            return height > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean hideSoftKeyboard(Context context, View view) {
        if (isSoftKeyboardVisible(context)) {
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            return true;
        } else {
            return false;
        }
    }

    public static boolean hideSoftKeyboard(Activity activity) {
        if (isSoftKeyboardVisible(activity)) {
            InputMethodManager imm =
                    (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            View view = activity.getCurrentFocus();
            if (view == null) {
                view = new View(activity);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            return true;
        } else {
            return false;
        }

    }
}
