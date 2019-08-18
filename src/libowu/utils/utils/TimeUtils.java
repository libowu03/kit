package libowu.utils.utils;

/**
 * 时间转换工具帮助类
 * @author libowu
 * 2019/08/18
 */
public class TimeUtils {
    public static final int MILLSECOND = -1;
    public static final int SECOND = 0;
    public static final int MINUES = 1;
    public static final int HOUSE = 2;
    public static final int DAYS = 3;
    public static final int MONTH = 4;
    public static final int YEAR = 5;
    private static final double MINTH_DAYS = 30.416667;


    /**
     * 毫秒转秒
     * @param time 毫秒数
     * @return
     */
    public static String haomiaoTomiao(String time){
        try{
            double changeTime = Double.parseDouble(time);
            String result = String.valueOf((changeTime/1000.0));
            return result;
        }catch (NumberFormatException e){
            return "输入格式有误";
        }
    }

    /**
     * 毫秒转分钟
     * @param time 毫秒数
     * @return
     */
    public static String haomiaoTomin(String time){
        try{
            String second = haomiaoTomiao(time);
            String minue = String.valueOf(Double.parseDouble(second)/60);
            return minue;
        }catch (NumberFormatException e){
            return "输入格式有误";
        }
    }

    /**
     * 毫秒转小时
     * @param time 毫秒数
     * @return
     */
    public static String haomiaoToHouse(String time){
        try{
            String minue = haomiaoTomin(time);
            String house = String.valueOf(Double.parseDouble(minue)/60);
            return house;
        }catch (NumberFormatException e){
            return "输入格式有误";
        }
    }

    /**
     * 毫秒转天
     * @param time 毫秒数
     * @return
     */
    public static String haomiaoToDay(String time){
        try{
            String house = haomiaoToHouse(time);
            String result = String.valueOf(Double.parseDouble(house)/24);
            return result;
        }catch (NumberFormatException e){
            return "输入格式有误";
        }
    }

    /**
     * 毫秒转月
     * @param month 毫秒数
     * @return
     */
    public static String haomiaoToMonth(String month){
        try{
            String day = haomiaoToDay(month);
            String result = String.valueOf(Double.parseDouble(day)/MINTH_DAYS);
            return result;
        }catch (NumberFormatException e){
            return "输入格式有误";
        }
    }

    /**
     * 毫秒转年
     * @param time 毫秒数
     * @return
     */
    public static String haomiaoToYear(String time){
        try{
            String month = haomiaoToMonth(time);
            String result = String.valueOf(Double.parseDouble(month)/12);
            return result;
        }catch (NumberFormatException e){
            return "输入格式有误";
        }
    }

    /**
     * 秒转其他
     * @param time 秒
     * @return
     */
    public static String secondToOther(String time,int type){
        try {
            double result = Double.parseDouble(time) * 1000;
            if (type == MILLSECOND){
                return String.valueOf(result);
            }else if (type == MINUES){
                return haomiaoTomin(String.valueOf(result));
            }else if (type == HOUSE){
                return haomiaoToHouse(String.valueOf(result));
            }else if (type == DAYS){
                return haomiaoToDay(String.valueOf(result));
            }else if (type == MONTH){
                return haomiaoToMonth(String.valueOf(result));
            }else if (type == YEAR){
                return haomiaoToYear(String.valueOf(result));
            }
            return haomiaoToMonth(String.valueOf(result));
        }catch (NumberFormatException e){
            return "输出格式有误";
        }
    }

    public static String minToOther(String time,int type){
        try{
            double second = Double.parseDouble(time)*60;
            double result = Double.parseDouble(secondToOther(String.valueOf(Double.parseDouble(time)*60),MILLSECOND));
            if (type == SECOND){
                return String.valueOf(second);
            }else if (type == MILLSECOND){
                return String.valueOf(result);
            }else {
                return secondToOther(String.valueOf(second),type);
            }
        }catch (NumberFormatException e){
            return "输出格式有误";
        }
    }

    /**
     * 小时转其他
     * @param time
     * @param type
     * @return
     */
    public static final String houseToOther(String time,int type){
        try {
            double min = Double.parseDouble(time)*60;
            if (type == MINUES){
                return String.valueOf(min);
            }else {
                return minToOther(String.valueOf(min),type);
            }
        }catch (NumberFormatException e){
            return "输出格式有误";
        }
    }


    /**
     * 日转其他
     * @param time
     * @param type
     * @return
     */
    public static final String dayToOther(String time,int type){
        try {
            double house = Double.parseDouble(time)*24;
            if (type == HOUSE){
                return String.valueOf(house);
            }else {
                return houseToOther(String.valueOf(house),type);
            }
        }catch (NumberFormatException e){
            return "输出格式有误";
        }
    }


    /**
     * 日转其他
     * @param time
     * @param type
     * @return
     */
    public static final String monthToOther(String time,int type){
        try {
            double day = Double.parseDouble(time)*MINTH_DAYS;
            if (type == DAYS){
                return String.valueOf(day);
            }else {
                return dayToOther(String.valueOf(day),type);
            }
        }catch (NumberFormatException e){
            return "输出格式有误";
        }
    }

    /**
     * 日转其他
     * @param time
     * @param type
     * @return
     */
    public static final String yearToOther(String time,int type){
        try {
            double month = Double.parseDouble(time)*12;
            if (type == MONTH){
                return String.valueOf(month);
            }else {
                return monthToOther(String.valueOf(month),type);
            }
        }catch (NumberFormatException e){
            return "输出格式有误";
        }
    }

}
