package net.dankito.utils.web;

import net.dankito.utils.web.callbacks.IDownloadCompletedCallback;
import net.dankito.utils.web.model.DownloadInfo;

/**
 * Created by ganymed on 05/11/16.
 */

public interface IDownloadManager {

  void downloadUrlAsync(DownloadInfo downloadInfo, IDownloadCompletedCallback callback);

}
