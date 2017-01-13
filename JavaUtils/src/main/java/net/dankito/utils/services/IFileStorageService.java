package net.dankito.utils.services;

/**
 * Created by ganymed on 26/11/16.
 */

public interface IFileStorageService {

  void writeObjectToFile(Object object, String filename) throws Exception;

  void writeToTextFile(String fileContent, String filename) throws Exception;


  void writeToBinaryFile(byte[] fileContent, String filename) throws Exception;

  <T> T readObjectFromFile(String filename, Class<T> objectClass) throws Exception;

  String readFromTextFile(String filename) throws Exception;


  byte[] readFromBinaryFile(String filename) throws Exception;

  void deleteFolderRecursively(String path);

}
