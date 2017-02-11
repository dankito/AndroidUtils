package net.dankito.utils.services;


public enum HashAlgorithm {

  MD5("MD5"),
  SHA1("SHA1"),
  SHA224("SHA-224"),
  SHA256("SHA-256"),
  SHA384("SHA-384"),
  SHA512("SHA-512");


  private String algorithmName;

  HashAlgorithm(String algorithmName) {
    this.algorithmName = algorithmName;
  }


  public String getAlgorithmName() {
    return algorithmName;
  }


  @Override
  public String toString() {
    return getAlgorithmName();
  }

}
