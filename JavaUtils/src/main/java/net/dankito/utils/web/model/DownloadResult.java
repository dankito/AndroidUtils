package net.dankito.utils.web.model;


/**
 * Created by ganymed on 18/11/16.
 */
public class DownloadResult extends ResponseBase {

  protected DownloadInfo downloadInfo;

  protected boolean isUserCancelled;


  public DownloadResult(DownloadInfo downloadInfo, boolean successful) {
    super(successful);
    this.downloadInfo = downloadInfo;
  }

  public DownloadResult(DownloadInfo downloadInfo, boolean isSuccessful, boolean isUserCancelled) {
    super(isSuccessful);
    this.downloadInfo = downloadInfo;
    this.isUserCancelled = isUserCancelled;
  }

  public DownloadResult(DownloadInfo downloadInfo, String error) {
    super(error);
    this.downloadInfo = downloadInfo;
  }


  public DownloadInfo getDownloadInfo() {
    return downloadInfo;
  }

  public boolean isUserCancelled() {
    return isUserCancelled;
  }

}
