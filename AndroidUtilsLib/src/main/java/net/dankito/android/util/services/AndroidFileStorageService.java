package net.dankito.android.util.services;

import android.content.Context;

import net.dankito.utils.services.JavaFileStorageService;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ganymed on 26/11/16.
 */

public class AndroidFileStorageService extends JavaFileStorageService {

  protected Context context;


  public AndroidFileStorageService(Context context) {
    this.context = context;
  }


  @Override
  protected OutputStream createFileOutputStream(String filename) throws FileNotFoundException {
    return context.openFileOutput(filename, Context.MODE_PRIVATE);
  }

  @Override
  protected InputStream createFileInputStream(String filename) throws FileNotFoundException {
    return context.openFileInput(filename);
  }

}
