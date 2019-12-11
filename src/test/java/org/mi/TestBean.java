package org.mi;

/**
 * @author yfwang2012@gmail.com
 * @description
 * @since 2019/12/10 21:59
 */
public class TestBean {

  public String publicStr;

  private String privateStr;

  private String method1(String parm){
    return null;
  }

  private String method2(String parm1, String parm2){
    return null;
  }

  public String getPublicStr() {
    return publicStr;
  }

  public void setPublicStr(String publicStr) {
    this.publicStr = publicStr;
  }

  public String getPrivateStr() {
    return privateStr;
  }

  public void setPrivateStr(String privateStr) {
    this.privateStr = privateStr;
  }
}
