package net.dankito.android.util.services;


public interface PermissionRequestCallback {

  void permissionCheckDone(String permission, boolean isGranted);

}
