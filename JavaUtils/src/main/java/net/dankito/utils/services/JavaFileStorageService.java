package net.dankito.utils.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.inject.Named;


@Named
public class JavaFileStorageService implements IFileStorageService {

  protected ObjectMapper mapper = new ObjectMapper();


  public JavaFileStorageService() {
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }


  public void writeObjectToFile(Object object, String filename) throws Exception {
    String json = mapper.writeValueAsString(object);
    writeToTextFile(json, filename);
  }

  public void writeToTextFile(String fileContent, String filename) throws Exception {
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(createFileOutputStream(filename));
    outputStreamWriter.write(fileContent);
    outputStreamWriter.close();
  }

  @Override
  public void writeToBinaryFile(byte[] fileContent, String filename) throws Exception {
    OutputStream outputStream = createFileOutputStream(filename);
    outputStream.write(fileContent);
    outputStream.close();
  }

  protected OutputStream createFileOutputStream(String filename) throws FileNotFoundException {
    return new FileOutputStream(filename);
  }


  public <T> T readObjectFromFile(String filename, Class<T> objectClass) throws Exception {
    String json = readFromTextFile(filename);

    return mapper.readValue(json, objectClass);
  }

  public String readFromTextFile(String filename) throws Exception {
    String fileContent = "";

    InputStream inputStream = createFileInputStream(filename);

    if(inputStream != null) {
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      String receiveString = "";
      StringBuilder stringBuilder = new StringBuilder();

      while ( (receiveString = bufferedReader.readLine()) != null ) {
        stringBuilder.append(receiveString);
      }

      inputStream.close();
      fileContent = stringBuilder.toString();
    }

    return fileContent;
  }

  @Override
  public byte[] readFromBinaryFile(String filename) throws Exception {
    InputStream inputStream = createFileInputStream(filename);

    if(inputStream != null) {
      return new InputStreamHelper().readDataFromInputStream(inputStream);
    }

    return null;
  }

  protected InputStream createFileInputStream(String filename) throws FileNotFoundException {
    return new FileInputStream(filename);
  }


  @Override
  public void deleteFolderRecursively(String path) {
    deleteRecursively(new File(path));
  }

  protected void deleteRecursively(File file) {
    if(file.isDirectory()) {
      for(File containingFile : file.listFiles()) {
        deleteRecursively(containingFile);
      }
    }

    file.delete();
  }


}
