package com.litongjava.tools.video;

public class Demo {
  public static void main(String[] args) {
    Long length1 = 7499L;
    Long length2 = 7499L;
    System.out.println(length1 == length2);
    if (length1.equals(length2) || length1 == length2 - 1 || length1 == length2 + 1) {
      System.out.println(true);
    }

  }
}
