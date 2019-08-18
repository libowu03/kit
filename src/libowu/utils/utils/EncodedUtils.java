package libowu.utils.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

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
     * string转成url编码
     * @param str
     * @return
     */
    public static String strToUrl(String str,String code){
        try {
            String result = URLEncoder.encode(str,code);
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "解析错误,原因："+e.getLocalizedMessage();
        }
    }

    public static String urlTostr(String url,String code){
        try {
            String result = URLDecoder.decode(url,code);
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "解析错误,原因："+e.getLocalizedMessage();
        }
    }
}
