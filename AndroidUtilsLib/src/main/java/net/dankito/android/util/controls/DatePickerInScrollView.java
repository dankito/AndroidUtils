package net.dankito.android.util.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.DatePicker;


/**
 * A simple extension of {@link DatePicker} which while being used avoids scrolling of parent ScrollView.<br/>
 * So much thanks to https://stackoverflow.com/a/34189814.
 */
public class DatePickerInScrollView extends DatePicker {

  public DatePickerInScrollView(Context context) {
    super(context);
  }

  public DatePickerInScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public DatePickerInScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }


  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    // Stop ScrollView from getting involved once you interact with the View
    if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
      ViewParent p = getParent();
      if (p != null)
        p.requestDisallowInterceptTouchEvent(true);
    }
    return false;
  }

}
