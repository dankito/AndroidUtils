package net.dankito.android.util.services;


import java.util.Map;

public interface MultiplePermissionsRequestCallback {

  void permissionsCheckDone(Map<String, Boolean> checkPermissionsResult);

}
