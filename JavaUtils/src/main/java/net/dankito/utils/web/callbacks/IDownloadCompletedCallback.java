package net.dankito.utils.web.callbacks;

import net.dankito.utils.web.model.DownloadResult;

/**
 * Created by ganymed on 18/11/16.
 */

public interface IDownloadCompletedCallback {

  void completed(DownloadResult result);

}
