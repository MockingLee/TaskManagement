package com.Picasso.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashProtection {
	
	public static String sha1(String portectData) {
        if (portectData.isEmpty()) {
            return "";
        }
        MessageDigest hash = null;
        try {
            hash = MessageDigest.getInstance("SHA1");
            byte[] bytes = hash.digest(portectData.getBytes("UTF-8"));
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
	
	public static void main(String[] args) {
		System.out.println(sha1("askdsadasdasdsadsaadasd") + " " + sha1("askdsadasdasdsadsaadasd").length());
		System.out.println(sha1("123213asdsa") + " " + sha1("123213asdsa").length());
		System.out.println(sha1("934fe5f2f3cee0da0595bddd4b12c80302aa75631231") + " " + sha1("934fe5f2f3cee0da0595bddd4b12c80302aa75631231").length());
	}

}
