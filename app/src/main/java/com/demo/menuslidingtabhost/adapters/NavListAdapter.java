package com.demo.menuslidingtabhost.adapters;

import java.util.List;

import com.demo.menuslidingtabhost.models.NavItem;
import com.menuslidingtabhost.slidingmenu_tabhostviewpager.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NavListAdapter extends ArrayAdapter<NavItem>{
	
	private Context context;
	private int resLayout;
	private List<NavItem> listNavItems;

	public NavListAdapter(Context context, int resLayout, List<NavItem> listNavItems) {
		super(context, resLayout, listNavItems);
		
		this.context = context;
		this.resLayout = resLayout;
		this.listNavItems = listNavItems;
	}
	
	@NonNull
	@SuppressLint("ViewHolder") @Override
	public View getView(int position, View convertView, @NonNull ViewGroup parent) {
		
		View v = View.inflate(context, resLayout, null);
		
		TextView tvTitle  = (TextView) v.findViewById(R.id.title);
		TextView tvSubTitle = (TextView) v.findViewById(R.id.subtitle);
		ImageView navIcon =  (ImageView) v.findViewById(R.id.nav_icon);
		
		NavItem navItem = listNavItems.get(position);
		
		tvTitle.setText(navItem.getTitle());
		tvSubTitle.setText(navItem.getSubTitle());
		navIcon.setImageResource(navItem.getResIcon());
		
		return v;
	}
	

	
	
}
