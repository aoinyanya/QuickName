package com.lingjie.quicksearch;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Friend> list;

	public MyAdapter(Context context, ArrayList<Friend> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.adapter_friend, null);
		}		
		ViewHolder viewHolder = ViewHolder.getViewHolder(convertView);
		Friend friend = list.get(position);
		viewHolder.name.setText(friend.getName());
		String currentWord = friend.getPinyin().charAt(0)+"";
		if (position>0) {
			String lastWord = list.get(position-1).getPinyin().charAt(0)+"";
			if (currentWord.equals(lastWord)) {
				viewHolder.first_letter.setVisibility(View.GONE);
			}else{
				viewHolder.first_letter.setVisibility(View.VISIBLE);
				viewHolder.first_letter.setText(currentWord);
			}
		}else{
			System.out.println(currentWord);
			viewHolder.first_letter.setText(currentWord);
		}						
		return convertView;

	}

	static class ViewHolder {
		public ViewHolder(View convertView) {
			first_letter = (TextView) convertView.findViewById(R.id.first_letter);
			name = (TextView) convertView.findViewById(R.id.name);
		}

		TextView name,first_letter;
		public static ViewHolder getViewHolder(View convertView){
			ViewHolder viewHolder = (ViewHolder) convertView.getTag();
			if (viewHolder == null) {
				viewHolder = new ViewHolder(convertView);
				convertView.setTag(viewHolder);
			}
			return viewHolder;
		}
	}

}
