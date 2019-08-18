package libowu.utils.utils;

import java.io.UnsupportedEncodingException;

/**
 * 实现编码转换的工具类
 * @author libowu
 * 2019/08/18
 */
public class EncodedUtils {

    /**
     * 将文本转换成unicode码
     * @param string
     * @return
     */
    public static String stringToUnicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);  // 取出每一个字符
            unicode.append("\\u" +Integer.toHexString(c));// 转换为unicode
        }
        return unicode.toString();
    }

    /**
     * unicode 转字符串
     */
    public static String unicodeToString(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);// 转换出每一个代码点
            string.append((char) data);// 追加成string
        }
        return string.toString();
    }



    /**
     * 汉字 转换为对应的 UTF-8编码
     * @param s 木
     * @return E69CA8
     */
    public static String convertStringToUTF8(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        try {
            char c;
            for (int i = 0; i < s.length(); i++) {
                c = s.charAt(i);
                if (c >= 0 && c <= 255) {
                    sb.append(c);
                } else {
                    byte[] b;
                    b = Character.toString(c).getBytes("utf-8");
                    for (int j = 0; j < b.length; j++) {
                        int k = b[j];
                        //转换为unsigned integer  无符号integer
					/*if (k < 0)
						k += 256;*/
                        k = k < 0 ? k + 256 : k;
                        //返回整数参数的字符串表示形式 作为十六进制（base16）中的无符号整数
                        //该值以十六进制（base16）转换为ASCII数字的字符串
                        sb.append(Integer.toHexString(k).toUpperCase());

                        // url转置形式
                        // sb.append("%" +Integer.toHexString(k).toUpperCase());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    /**
     * UTF-8编码 转换为对应的 汉字
     *
     * @param s E69CA8
     * @return 木
     */
    public static String convertUTF8ToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        try {
            s = s.toUpperCase();
            int total = s.length() / 2;
            //标识字节长度
            int pos = 0;
            byte[] buffer = new byte[total];
            for (int i = 0; i < total; i++) {
                int start = i * 2;
                //将字符串参数解析为第二个参数指定的基数中的有符号整数。
                buffer[i] = (byte) Integer.parseInt(s.substring(start, start + 2), 16);
                pos++;
            }
            //通过使用指定的字符集解码指定的字节子阵列来构造一个新的字符串。
            //新字符串的长度是字符集的函数，因此可能不等于子数组的长度。
            return new String(buffer, 0, pos, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }
}
