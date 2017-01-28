package net.dankito.android.util.controls;


import java.util.Calendar;

public interface DateTimePickerCallback {

  void selectingDateTimeFinished(boolean hasAValueBeenSelected, Calendar selectedValue);

}
