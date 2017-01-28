package net.dankito.android.util.controls;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import net.dankito.android.R;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class DateTimePicker {

  public void pickDateTime(Context context, Calendar initialValue, final DateTimePickerCallback callback) {
    final View dialogView = View.inflate(context, R.layout.date_time_picker, null);
    final DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);

    final TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.time_picker);
    timePicker.setIs24HourView(DateFormat.is24HourFormat(context));
    setInitialDateTime(initialValue, datePicker, timePicker);

    final AlertDialog alertDialog = new AlertDialog.Builder(context).create();

    dialogView.findViewById(R.id.btnDateTimeSelected).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        alertDialog.dismiss();

        dateTimeSelected(datePicker, timePicker, callback);
      }});

    alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
      @Override
      public void onDismiss(DialogInterface dialog) {
        alertDialog.dismiss();

        callback.selectingDateTimeFinished(false, null);
      }
    });

    alertDialog.setView(dialogView);
    alertDialog.show();
  }

  protected void setInitialDateTime(Calendar initialValue, DatePicker datePicker, TimePicker timePicker) {
    if(initialValue != null) {
      datePicker.init(initialValue.get(Calendar.YEAR), initialValue.get(Calendar.MONTH), initialValue.get(Calendar.DAY_OF_MONTH), null);

      timePicker.setCurrentHour(initialValue.get(Calendar.HOUR_OF_DAY));
      timePicker.setCurrentMinute(initialValue.get(Calendar.MINUTE));
    }
  }

  protected void dateTimeSelected(DatePicker datePicker, TimePicker timePicker, DateTimePickerCallback callback) {
    Calendar calendar = new GregorianCalendar(datePicker.getYear(),
        datePicker.getMonth(),
        datePicker.getDayOfMonth(),
        timePicker.getCurrentHour(),
        timePicker.getCurrentMinute());

    callback.selectingDateTimeFinished(true, calendar);
  }

}
