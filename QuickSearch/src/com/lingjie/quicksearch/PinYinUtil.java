package com.lingjie.quicksearch;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.text.TextUtils;

public class PinYinUtil {
	public static String getPinyin(String chinese){
		if (TextUtils.isEmpty(chinese)) return null;
		//用来设置转化的拼音的大小写或者声调
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);//设置转化的拼音是大写字母
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		char[] charArray = chinese.toCharArray();
		String pinyin = "";
		for (int i = 0; i < charArray.length; i++) {
			if (Character.isWhitespace(charArray[i])) continue;
			if (charArray[i]>127) {
				try {
					//判断多音字
					String[] pinyinArrs = PinyinHelper.toHanyuPinyinStringArray(charArray[i], format);
					if (pinyinArrs!=null) {
						pinyin += pinyinArrs[0];//此处有多音字,那么只能取第一个拼音
					}else{
						//
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			}else{
				pinyin += charArray[i];
			}
		}
		return pinyin;
	}
}
