package com.javasoso.pass.util;

/**
 * 二进制 十六进制 互转
 *
 * @author jasonzhu
 * @date 2019-06-09
 */
public class HexUtil {
    private static final int BASE_LENGTH = 128;
    private static final int LOOKUP_LENGTH = 16;
    private static final byte[] HEX_NUMBER_TABLE = new byte[128];
    private static final char[] UPPER_CHARS = new char[16];
    private static final char[] LOWER_CHARS = new char[16];

    public static String encode(byte[] bytes) {
        return encode(bytes, true);
    }

    public static String encode(byte[] bytes, boolean upperCase) {
        if (bytes == null) {
            return null;
        } else {
            char[] chars = upperCase ? UPPER_CHARS : LOWER_CHARS;
            char[] hex = new char[bytes.length * 2];

            for(int i = 0; i < bytes.length; ++i) {
                int b = bytes[i] & 255;
                hex[i * 2] = chars[b >> 4];
                hex[i * 2 + 1] = chars[b & 15];
            }

            return new String(hex);
        }
    }

    public static byte[] decode(String encoded) {
        if (encoded == null) {
            return null;
        } else {
            int lengthData = encoded.length();
            if (lengthData % 2 != 0) {
                return null;
            } else {
                char[] binaryData = encoded.toCharArray();
                int lengthDecode = lengthData / 2;
                byte[] decodedData = new byte[lengthDecode];

                for(int i = 0; i < lengthDecode; ++i) {
                    char tempChar = binaryData[i * 2];
                    byte temp1 = tempChar < 128 ? HEX_NUMBER_TABLE[tempChar] : -1;
                    if (temp1 == -1) {
                        return null;
                    }

                    tempChar = binaryData[i * 2 + 1];
                    byte temp2 = tempChar < 128 ? HEX_NUMBER_TABLE[tempChar] : -1;
                    if (temp2 == -1) {
                        return null;
                    }

                    decodedData[i] = (byte)(temp1 << 4 | temp2);
                }

                return decodedData;
            }
        }
    }

    static {
        int i;
        for(i = 0; i < 128; ++i) {
            HEX_NUMBER_TABLE[i] = -1;
        }

        for(i = 57; i >= 48; --i) {
            HEX_NUMBER_TABLE[i] = (byte)(i - 48);
        }

        for(i = 70; i >= 65; --i) {
            HEX_NUMBER_TABLE[i] = (byte)(i - 65 + 10);
        }

        for(i = 102; i >= 97; --i) {
            HEX_NUMBER_TABLE[i] = (byte)(i - 97 + 10);
        }

        for(i = 0; i < 10; ++i) {
            UPPER_CHARS[i] = (char)(48 + i);
            LOWER_CHARS[i] = (char)(48 + i);
        }

        for(i = 10; i <= 15; ++i) {
            UPPER_CHARS[i] = (char)(65 + i - 10);
            LOWER_CHARS[i] = (char)(97 + i - 10);
        }

    }
}
