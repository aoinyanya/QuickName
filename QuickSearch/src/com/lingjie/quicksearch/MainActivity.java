package com.lingjie.quicksearch;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lingjie.quicksearch.QuickIndex.onTouchLetterListener;

public class MainActivity extends Activity {

	private QuickIndex qu;
	private ListView lv;
	private ArrayList<Friend> friends = new ArrayList<Friend>();
	private TextView currentWord;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		qu = (QuickIndex) findViewById(R.id.qu);
		lv = (ListView) findViewById(R.id.lv);
		currentWord = (TextView) findViewById(R.id.currentWord);
		fillList();
		Collections.sort(friends);
		lv.setAdapter(new MyAdapter(this, friends));
		qu.setOnTouchLetterListener(new onTouchLetterListener() {

			@Override
			public void onTouchListener(String Letter) {
				for (int i = 0; i < friends.size(); i++) {
					String firstWord = friends.get(i).getPinyin().charAt(0)+"";
					if (Letter.equals(firstWord)) {
						lv.setSelection(i);
						break;
					}
				}
				showCurrentWord(Letter);
			}
		});

	}
	private Handler handler = new Handler();
	protected void showCurrentWord(String Letter) {
		currentWord.setVisibility(View.VISIBLE);
		currentWord.setText(Letter);
		handler.removeCallbacksAndMessages(null);
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				currentWord.setVisibility(View.GONE);
			}
		},1500);
	}
	private void fillList() {
		// 虚拟数据
		friends.add(new Friend("李伟"));
		friends.add(new Friend("张三"));
		friends.add(new Friend("阿三"));
		friends.add(new Friend("阿四"));
		friends.add(new Friend("段誉"));
		friends.add(new Friend("段正淳"));
		friends.add(new Friend("张三丰"));
		friends.add(new Friend("陈坤"));
		friends.add(new Friend("林俊杰1"));
		friends.add(new Friend("陈坤2"));
		friends.add(new Friend("王二a"));
		friends.add(new Friend("林俊杰a"));
		friends.add(new Friend("张四"));
		friends.add(new Friend("林俊杰"));
		friends.add(new Friend("王二"));
		friends.add(new Friend("王二b"));
		friends.add(new Friend("赵四"));
		friends.add(new Friend("杨坤"));
		friends.add(new Friend("赵子龙"));
		friends.add(new Friend("杨坤1"));
		friends.add(new Friend("李伟1"));
		friends.add(new Friend("宋江"));
		friends.add(new Friend("宋江1"));
		friends.add(new Friend("李伟3"));
	}
}
