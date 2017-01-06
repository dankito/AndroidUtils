package net.dankito.utils.web.model;

/**
 * Created by ganymed on 18/11/16.
 */

public class DownloadInfo {


  protected String url;

  protected String fileSize;

  protected String downloadLocationUri = null;

  protected String downloadLocationPath = null;


  public DownloadInfo() {

  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getFileSize() {
    return fileSize;
  }

  public void setFileSize(String fileSize) {
    this.fileSize = fileSize;
  }

  public String getDownloadLocationUri() {
    return downloadLocationUri;
  }

  public void setDownloadLocationUri(String downloadLocationUri) {
    this.downloadLocationUri = downloadLocationUri;
  }

  public String getDownloadLocationPath() {
    return downloadLocationPath;
  }

  public void setDownloadLocationPath(String downloadLocationPath) {
    this.downloadLocationPath = downloadLocationPath;
  }


  @Override
  public String toString() {
    return url;
  }

}
