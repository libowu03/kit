package libowu.utils.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 进制转换工具类
 * @author libowu
 * 2019/08/18
 */
public class HexUtils {
    /**
     * 十进制转其他进制
     * @param input
     * @param type
     * @return
     */
    public static String tenToOther(String input,int type){
        if (input.contains(".")){
            return "暂时不支持小数转换";
        }
        try{
            List<Integer> list = new ArrayList<>();
            StringBuffer result = new StringBuffer();
            if (input.contains(".")){
                return "暂时不支持小数转换";
            }else {
                int temp = Integer.parseInt(input);
                while (true){
                    if (temp/type == 0){
                        list.add(temp%type);
                        break;
                    }else {
                        list.add(temp%type);
                    }
                    temp = temp/type;
                }
            }

            for (int i=list.size()-1;i>=0;i--){
                //如果时十六进制需要单独处理
                if (type == 16){
                    if (list.get(i) == 10){
                        result.append("A");
                    }else if (list.get(i) == 11){
                        result.append("B");
                    }else if (list.get(i) == 12){
                        result.append("C");
                    }else if (list.get(i) == 13){
                        result.append("D");
                    }else if (list.get(i) == 14){
                        result.append("E");
                    }else if (list.get(i) == 15){
                        result.append("F");
                    }else {
                        result.append(list.get(i));
                    }
                }else {
                    result.append(list.get(i));
                }
            }
            return result.toString();
        }catch (NumberFormatException e){
            return "输入格式错误";
        }
    }

    /**
     * 二进制转其他进制
     * @param input
     * @param type
     * @return
     */
    public static String twoToOther(String input,int type){
        if (input.contains(".")){
            return "暂时不支持小数转换";
        }
        try{
            int length = input.length()-1;
            if (type == 10){
                return twoToTen(input);
            }else if (type == 8){
                //取三合一法,不足三位用零替代
                if (input.length()%3 != 0){
                    for (int j=0; j<input.length()%3; j++){
                        input = "0"+input;
                    }
                }
                String temp = new String();
                StringBuffer result = new StringBuffer();
                for (int i=0;i<input.length();i++){
                    if (temp.length() == 3){
                        result.append(twoToTen(temp));
                        //System.out.println(temp);
                        temp = "";
                        temp = temp + input.charAt(i);
                    }else {
                        temp = temp + input.charAt(i);
                    }
                }
                result.append(twoToTen(temp));
                return result.toString();
            }else if (type == 16){
                //取三合一法,不足三位用零替代
                if (input.length()%4 != 0){
                    for (int j=0; j<input.length()%4; j++){
                        input = "0"+input;
                    }
                }
                String temp = new String();
                StringBuffer result = new StringBuffer();
                for (int i=0;i<input.length();i++){
                    if (temp.length() == 4){
                        int tempResult = Integer.parseInt(twoToTen(temp));
                        if (tempResult == 10){
                            result.append("A");
                        }else if (tempResult == 11){
                            result.append("B");
                        }else if (tempResult == 12){
                            result.append("C");
                        }else if (tempResult == 13){
                            result.append("D");
                        }else if (tempResult == 14){
                            result.append("E");
                        }else if (tempResult == 15){
                            result.append("F");
                        }else {
                            result.append(twoToTen(temp));
                        }
                        temp = "";
                        temp = temp + input.charAt(i);
                    }else {
                        temp = temp + input.charAt(i);
                    }
                }
                result.append(twoToTen(temp));
                if (result.toString().startsWith("0")){
                    return result.toString().substring(result.toString().indexOf("0")+1);
                }else {
                    return result.toString();
                }
            }
        }catch (NumberFormatException e){
            return "输出格式有误";
        }
        return null;
    }

    /**
     * 二进制转十进制
     * @param input
     * @return
     */
    public static String twoToTen(String input){
        int result = 0;
        int length = input.length()-1;
        for (int i=0;i<input.length();i++){
            int temp = Integer.parseInt(input.charAt(i)+"");
            result = (int) (result + temp*Math.pow(2,length--));
        }
        return String.valueOf(result);
    }

    public static String eightToOther(String input , int type){
        if (input.contains(".")){
            return "暂时不支持小数转换";
        }
       try{
           if (type == 16){
               //转换成16进制
               StringBuffer result = new StringBuffer();
               String tempResult = new String();
               for (int i=0;i<input.length();i++){
                   String temp = tenToOther(input.charAt(i)+"",2);
                   for (int j = 0; j<temp.length()%3;j++){
                       temp = "0"+temp;
                   }
                   //System.out.println(tempResult);
                   tempResult = tempResult + temp;
               }
               result.append(twoToOther(tempResult,16));
               return result.toString();
           }else if (type == 2){
               //转换成二进制
               String tempResult = new String();
               for (int i=0;i<input.length();i++){
                   String temp = tenToOther(input.charAt(i)+"",2);
                   for (int j = 0; j<temp.length()%3;j++){
                       temp = "0"+temp;
                   }
                   //System.out.println(tempResult);
                   tempResult = tempResult + temp;
               }
               if (tempResult.startsWith("0")){
                   return tempResult.substring(tempResult.indexOf("0")+1);
               }else {
                   return tempResult;
               }
           }else if (type == 10){
               //转换成十进制
               int length = input.length() - 1;
               int result = 0;
               for (int i=0;i<input.length();i++){
                   result = (int) (result + Integer.parseInt(input.charAt(i)+"")*Math.pow(8,length--));
               }
               return String.valueOf(result);
           }
           return null;
       }catch (NumberFormatException e){
           return "输出格式有误";
       }
    }

    /**
     * 十六进制转其他进制
     * @param input
     * @param type
     * @return
     */
    public static String sixTeenToOther(String input,int type){
        if (input.contains(".")){
            return "暂时不支持小数转换";
        }
        try{
            if (type == 2){
                return sixTeenToTwo(input);
            }else if (type == 8){
                String sixTeenTwo = sixTeenToTwo(input);
                String sexTeenEight = twoToOther(sixTeenTwo,8);
                return sexTeenEight;
            }else if (type == 10){
                int index = input.length()-1;
                int result = 0;
                for (int i=0;i<input.length();i++){
                    int tempNumber = 0;
                    if (String.valueOf(input.charAt(i)).equalsIgnoreCase("a")){
                        tempNumber = 10;
                    }else if (String.valueOf(input.charAt(i)).equalsIgnoreCase("b")){
                        tempNumber = 11;
                    }else if (String.valueOf(input.charAt(i)).equalsIgnoreCase("c")){
                        tempNumber = 12;
                    }else if (String.valueOf(input.charAt(i)).equalsIgnoreCase("d")){
                        tempNumber = 13;
                    }else if (String.valueOf(input.charAt(i)).equalsIgnoreCase("e")){
                        tempNumber = 14;
                    }else if (String.valueOf(input.charAt(i)).equalsIgnoreCase("f")){
                        tempNumber = 15;
                    }else {
                        tempNumber = Integer.parseInt(input.charAt(i)+"");
                    }
                    result = (int) (result + tempNumber*Math.pow(16,index--));
                }
                return String.valueOf(result);
            }
        }catch (NumberFormatException e){
            return "输出格式有误";
        }
        return null;
    }

    /**
     * 十六进制转二进制
     * @param input
     * @return
     */
    public static String sixTeenToTwo(String input){
        String changeTemp = "";
        for (int i=0;i<input.length();i++){
            String temp;
            if (String.valueOf(input.charAt(i)).equalsIgnoreCase("a")){
                temp = tenToOther("10",2);
            }else if (String.valueOf(input.charAt(i)).equalsIgnoreCase("b")){
                temp = tenToOther("11",2);
            }else if (String.valueOf(input.charAt(i)).equalsIgnoreCase("c")){
                temp = tenToOther("12",2);
            }else if (String.valueOf(input.charAt(i)).equalsIgnoreCase("d")){
                temp = tenToOther("13",2);
            }else if (String.valueOf(input.charAt(i)).equalsIgnoreCase("e")){
                temp = tenToOther("14",2);
            }else if (String.valueOf(input.charAt(i)).equalsIgnoreCase("f")){
                temp = tenToOther("15",2);
            }else {
                temp = tenToOther(String.valueOf(input.charAt(i)),2);
            }
            if (temp.equals("输入格式错误")){
                return temp;
            }
            if (temp.length() < 4 && i != 0){
                for (int j=0; j<temp.length()%4; j++){
                    temp = "0" + temp;
                }
            }
            changeTemp = changeTemp + temp;
        }
        return changeTemp;
    }
}
