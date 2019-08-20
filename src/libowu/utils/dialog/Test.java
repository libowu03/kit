package libowu.utils.dialog;

import com.google.gson.Gson;
import libowu.utils.utils.EncodedUtils;
import libowu.utils.utils.HexUtils;
import libowu.utils.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String test = "niha?noin?fdkf";
        String[] info = test.split("\\?");
        for (String a:info) {
            System.out.println(a);
        }
        System.out.print(info);
    }
}
