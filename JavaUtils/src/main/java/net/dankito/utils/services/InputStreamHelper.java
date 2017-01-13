package net.dankito.utils.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class InputStreamHelper {

  public static final byte[] COULD_NOT_READ_FROM_INPUT_STREAM = new byte[0];

  private static final Logger log = LoggerFactory.getLogger(InputStreamHelper.class);


  public byte[] readDataFromInputStream(InputStream inputStream) {
    try {
      ByteArrayOutputStream buffer = new ByteArrayOutputStream();

      int nRead;
      byte[] data = new byte[16384];

      while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
        buffer.write(data, 0, nRead);
      }

      buffer.flush();

      return buffer.toByteArray();
    } catch(Exception e) {
      log.error("Could not read data from InputStream", e);
    }

    return COULD_NOT_READ_FROM_INPUT_STREAM;
  }
}
