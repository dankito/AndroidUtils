package net.dankito.utils.web;

import net.dankito.utils.web.callbacks.RequestCallback;
import net.dankito.utils.web.model.RequestParameters;
import net.dankito.utils.web.model.WebClientResponse;

/**
 * Created by ganymed on 03/11/16.
 */

public interface IWebClient {

  WebClientResponse get(RequestParameters parameters);

  void getAsync(RequestParameters parameters, final RequestCallback callback);

  WebClientResponse post(RequestParameters parameters);

  void postAsync(RequestParameters parameters, final RequestCallback callback);

  WebClientResponse head(RequestParameters parameters);

  void headAsync(RequestParameters parameters, final RequestCallback callback);

}
