package net.dankito.android.util.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.TimePicker;


/**
 * A simple extension of {@link TimePicker} which while being used avoids scrolling of parent ScrollView.<br/>
 * So much thanks to https://stackoverflow.com/a/14440893.
 */
public class TimePickerInScrollView extends TimePicker {

  public TimePickerInScrollView(Context context) {
    super(context);
  }

  public TimePickerInScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public TimePickerInScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
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
