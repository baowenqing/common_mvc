package com.juziwl.commonlibrary.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 创建日期：2017/12/16
 * 描述:
 *
 * @author: zhaoh
 */
public class FloatUtil {

    /**
     * 小数点保留两位
     *
     * @return
     */
    public static String dealwithDouble(String str) {
        BigDecimal bd = new BigDecimal(Float.parseFloat(str));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        String need = String.valueOf(bd);
        return need;
    }

    public static String dealwithDown(String str) {
        BigDecimal bd = new BigDecimal(Float.parseFloat(str));
        bd = bd.setScale(2, RoundingMode.HALF_DOWN);
        String need = String.valueOf(bd);
        return need;
    }

    public static String dealwithDown(String str, int number) {
        BigDecimal bd = new BigDecimal(Float.parseFloat(str));
        bd = bd.setScale(number, RoundingMode.HALF_DOWN);
        String need = String.valueOf(bd);
        return need;
    }


    public static int floatToInt(float value) {
        BigDecimal bd = new BigDecimal(value);
        return bd.intValue();
    }

    public static String dealwithDouble(double str) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(str);
    }

    public static String dealwithString(String str) {
        if (!StringUtils.isEmpty(str)) {
            return dealwithDouble(Double.parseDouble(str));
        }
        return "金额错误";
    }

    /**
     * 账户支付金额显示处理
     *
     * @param str
     * @return
     */
    public static String dealwithAccountMoney(String str) {
        if (!StringUtils.isEmpty(str)) {
            DecimalFormat df = new DecimalFormat("0.00");
            float f = Float.parseFloat(str);
            return df.format(f);
        } else {
            return "0.00";
        }
    }
    //string 转整数

    public int dealwithFloatToInt(String str) {
        int result;
        float j = Float.parseFloat(str);
        result = (int) (j + 0.5);
        return result;
    }

    public static String dealwithDouble(String str, int number) {
        BigDecimal bd = new BigDecimal(Float.parseFloat(str));
        bd = bd.setScale(number, RoundingMode.HALF_UP);
        String need = String.valueOf(bd);
        return need;
    }

    /**
     * （1）四舍五入把double转化int整型，0.5进一，小于0.5不进一
     *
     * @param number
     * @return
     */
    public static int getInt(double number) {
        BigDecimal bd = new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP);
        return Integer.parseInt(bd.toString());
    }


    /**
     * （2）四舍五入把double转化为int类型整数,0.5也舍去,0.51进一
     *
     * @param dou
     * @return
     */
    public static int DoubleFormatInt(Double dou) {
        DecimalFormat df = new DecimalFormat("######0"); //四色五入转换成整数
        return Integer.parseInt(df.format(dou));
    }


    /**
     * （3）去掉小数凑整:不管小数是多少，都进一
     *
     * @param number
     * @return
     */
    public static int ceilInt(double number) {
        return (int) Math.ceil(number);
    }


    /**
     * 处理数字金额问题 保留两位小数的问题
     *
     * @param a 被除数
     * @param b 除数
     * @return 字符串类型的
     */
    public static String dealWithMoney(String a, String b) {
        if (!StringUtils.isEmpty(a) && !StringUtils.isEmpty(b)) {
            int x = Integer.parseInt(a);
            int y = Integer.parseInt(b);
            //格式化小数
            DecimalFormat df = new DecimalFormat("0.00");
            String num = df.format((float) x / y);
            return num;
        } else {
            return "0.00";
        }
    }

    /**
     * 处理数字金额问题能
     *
     * @param a 被除数
     * @param b 除数
     * @return 字符串类型的
     */
    public static String dealWithMoneyInt(String a, String b) {
        if (!StringUtils.isEmpty(a) && !StringUtils.isEmpty(b)) {
            double value = new BigDecimal(Double.parseDouble(a) / Double.parseDouble(b)).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
//            int x = Integer.parseInt(a);
//            int y = Integer.parseInt(b);
//            DecimalFormat df = new DecimalFormat("0.00");
//            //格式化小数
//            if(x%y==0){
//                String num = df.format((float) x / y);
//                double d = Double.parseDouble(num);
//                String needMoney = String.format("%d", ceilInt(d));
//                return needMoney;
//            }else{
//                float f = (float) x/y;
//                String  needS = String.valueOf(f);
//                String[] str =  needS.split("\\.");
//               if(str[1].length()>3){
//                   String str1 = df.format(f);
//                   return str1;
//               }else{
//                   return needS;
//               }
//            }
            return value + "";
        } else {
            return "0";
        }
    }

    /**
     * 处理金额的优惠打折后  保留一个整数位
     *
     * @param sum        第一个参数是总的金额
     * @param favourable 第二个参数是折扣率
     * @param isSum      显示折扣后的价格还是优惠了多少
     * @return
     */
    public static String dealWihtIntFavourable(String sum, String favourable, boolean isSum) {
        if (!StringUtils.isEmpty(sum) && !StringUtils.isEmpty(favourable)) {
            int vlaue = Integer.parseInt(sum);
            float parseFloat = Float.parseFloat(favourable);
            DecimalFormat df = new DecimalFormat("0.00");
            if (isSum) {
                String num = df.format((float) vlaue * parseFloat);
                Double d = Double.parseDouble(num);
                int zheKou = Integer.parseInt(dealWihtIntFavourable(sum, favourable, false));
                String nSum = String.valueOf((vlaue - zheKou));
                if (vlaue - zheKou == 0) {
                    return "0";
                } else {
                    return nSum;
                }
            } else {
                String favourableNumber = df.format((float) vlaue * (1 - parseFloat));
                Double d = Double.parseDouble(favourableNumber);
                String nFavourableNumber = String.format("%d", ceilInt(d));
                return nFavourableNumber;
            }
        } else {
            return "0";
        }
    }


    public static String dealWithPer(float per) {
        DecimalFormat df = new DecimalFormat("0%");
        return df.format(per);
    }

}
