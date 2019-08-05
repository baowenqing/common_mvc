package com.juziwl.commonlibrary.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月24日
 * @description 汉字转拼音
 */
public class PingYinUtil {
    /**
     * 将字符串中的中文转化为拼音,其他字符不变
     *
     * @param inputString
     * @return
     */
    @SuppressLint("DefaultLocale")
    public static String getPingYin(String inputString) {
        if (TextUtils.isEmpty(inputString))
            return "";
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] input = inputString.trim().toCharArray();
        StringBuilder output = new StringBuilder(100);

        try {
            for (char anInput : input) {
                if (Character.toString(anInput).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(
                            anInput, format);
                    output.append(temp[0]);
                } else
                    output.append(Character.toString(anInput).toUpperCase());
            }
        } catch (NoSuchMethodError | Exception error) {
            error.printStackTrace();
        }
        return output.toString();
    }

    /**
     * 汉字转换位汉语拼音首字母，英文字符不变
     *
     * @param chines 汉字
     * @return 拼音
     */
    @SuppressLint("DefaultLocale")
    public static String converterToFirstSpell(String chines) {
        StringBuilder pinyinName = new StringBuilder(100);
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char aNameChar : nameChar) {
            if (aNameChar > 128) {
                try {
                    if (PinyinHelper.toHanyuPinyinStringArray(aNameChar,
                            defaultFormat) != null) {
                        pinyinName.append(PinyinHelper.toHanyuPinyinStringArray(
                                aNameChar, defaultFormat)[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName.append(aNameChar);
            }
        }
        String upoutputnew = pinyinName.toString().toUpperCase();
        return upoutputnew;
    }

    private static String getAlphabet(String str) {
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 输出拼音全部小写
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // 不带声调
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String pinyin = null;
        try {
            pinyin = PinyinHelper.toHanyuPinyinStringArray(str.charAt(0),
                    defaultFormat)[0];
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return pinyin != null ? pinyin.substring(0, 1) : null;
    }
}
