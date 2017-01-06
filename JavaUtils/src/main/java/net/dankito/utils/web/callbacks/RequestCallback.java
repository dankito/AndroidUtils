package net.dankito.utils.web.callbacks;

import net.dankito.utils.web.model.WebClientResponse;

/**
 * Created by ganymed on 03/11/16.
 */

public interface RequestCallback {

  void completed(WebClientResponse response);

}
