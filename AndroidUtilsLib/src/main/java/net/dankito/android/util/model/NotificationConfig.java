package net.dankito.android.util.model;

import android.app.Activity;

/**
 * Created by ganymed on 01/12/16.
 */

public class NotificationConfig {

  protected Class<Activity> activityToShowOnTap;

  protected String title;

  protected String text;

  protected boolean isMultiLineText;

  protected int iconId;

  protected boolean letLedBlink;

  protected int ledArgb;

  protected int ledOnMillis;

  protected int ledOffMillis;


  public NotificationConfig() {

  }

  public NotificationConfig(Class<Activity> activityToShowOnTap, String title, String text, int iconId) {
    this.activityToShowOnTap = activityToShowOnTap;
    this.title = title;
    this.text = text;
    this.iconId = iconId;
  }

  public NotificationConfig(Class<Activity> activityToShowOnTap, String title, String text, int iconId, boolean isMultiLineText) {
    this(activityToShowOnTap, title, text, iconId);
    this.isMultiLineText = isMultiLineText;
  }

  public NotificationConfig(Class<Activity> activityToShowOnTap, int ledArgb, int ledOnMillis, int ledOffMillis) {
    this.activityToShowOnTap = activityToShowOnTap;
    this.letLedBlink = true;
    this.ledArgb = ledArgb;
    this.ledOnMillis = ledOnMillis;
    this.ledOffMillis = ledOffMillis;
  }


  public Class<Activity> getActivityToShowOnTap() {
    return activityToShowOnTap;
  }

  public void setActivityToShowOnTap(Class<Activity> activityToShowOnTap) {
    this.activityToShowOnTap = activityToShowOnTap;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public boolean isMultiLineText() {
    return isMultiLineText;
  }

  public void setMultiLineText(boolean multiLineText) {
    isMultiLineText = multiLineText;
  }

  public int getIconId() {
    return iconId;
  }

  public void setIconId(int iconId) {
    this.iconId = iconId;
  }

  public boolean letLedBlink() {
    return letLedBlink;
  }

  public void setLetLedBlink(boolean letLedBlink) {
    this.letLedBlink = letLedBlink;
  }

  public void setLetLedBlink(int ledArgb, int ledOnMillis, int ledOffMillis) {
    setLetLedBlink(true);
    setLedArgb(ledArgb);
    setLedOnMillis(ledOnMillis);
    setLedOffMillis(ledOffMillis);
  }

  public int getLedArgb() {
    return ledArgb;
  }

  public void setLedArgb(int ledArgb) {
    this.ledArgb = ledArgb;
  }

  public int getLedOnMillis() {
    return ledOnMillis;
  }

  public void setLedOnMillis(int ledOnMillis) {
    this.ledOnMillis = ledOnMillis;
  }

  public int getLedOffMillis() {
    return ledOffMillis;
  }

  public void setLedOffMillis(int ledOffMillis) {
    this.ledOffMillis = ledOffMillis;
  }

}
