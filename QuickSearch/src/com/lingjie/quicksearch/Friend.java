package com.lingjie.quicksearch;

public class Friend implements Comparable<Friend>{
	private String name;
	private String pinyin;

	//使用成员变量生成构造方法：alt+shift+s->o
	public Friend(String name) {
		super();
		this.name = name;
		setPinyin(PinYinUtil.getPinyin(name));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@Override
	public int compareTo(Friend another) {
		return getPinyin().compareTo(another.getPinyin());
	}
	
	
}
