package com.Picasso.util;

import java.util.Random;

public class RandomString {
  public static String getRandomString(int length) {
    String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Random random = new Random();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < length; i++) {
      int number = random.nextInt(str.length());
      sb.append(str.charAt(number));
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(getRandomString(10));
  }
}