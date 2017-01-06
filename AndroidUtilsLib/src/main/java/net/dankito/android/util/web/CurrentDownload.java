package net.dankito.android.util.web;

import net.dankito.utils.web.callbacks.IDownloadCompletedCallback;
import net.dankito.utils.web.model.DownloadInfo;

/**
 * Created by ganymed on 18/11/16.
 */

public class CurrentDownload {

  protected DownloadInfo downloadInfo;

  protected IDownloadCompletedCallback callback;


  public CurrentDownload(DownloadInfo downloadInfo, IDownloadCompletedCallback callback) {
    this.downloadInfo = downloadInfo;
    this.callback = callback;
  }


  public DownloadInfo getDownloadInfo() {
    return downloadInfo;
  }

  public IDownloadCompletedCallback getCallback() {
    return callback;
  }


  @Override
  public String toString() {
    return "" + downloadInfo;
  }

}
