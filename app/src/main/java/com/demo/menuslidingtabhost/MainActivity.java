package com.demo.menuslidingtabhost;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.demo.menuslidingtabhost.adapters.NavListAdapter;
import com.demo.menuslidingtabhost.fragments.ProfileFragment;
import com.demo.menuslidingtabhost.fragments.home.HomeFragment;
import com.demo.menuslidingtabhost.fragments.SettingsFragment;
import com.demo.menuslidingtabhost.models.NavItem;
import com.menuslidingtabhost.slidingmenu_tabhostviewpager.R;

public class MainActivity extends ActionBarActivity {

	DrawerLayout drawerLayout;
	RelativeLayout drawerPane;
	ListView lvNav;

	List<NavItem> listNavItems;
	List<Fragment> listFragments;

	ActionBarDrawerToggle actionBarDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerPane = (RelativeLayout) findViewById(R.id.drawer_pane);
		lvNav = (ListView) findViewById(R.id.nav_list);

		listNavItems = new ArrayList<>();
		listNavItems.add(new NavItem("Home", "HomeFragment page",
				R.drawable.ic_action_home));
		listNavItems.add(new NavItem("Settings", "Change something",
				R.drawable.ic_action_settings));
		listNavItems.add(new NavItem("About", "Author's information",
				R.drawable.ic_action_about));

		NavListAdapter navListAdapter = new NavListAdapter(
				getApplicationContext(), R.layout.item_nav_list, listNavItems);

		lvNav.setAdapter(navListAdapter);

		listFragments = new ArrayList<>();
		listFragments.add(new HomeFragment());
		listFragments.add(new SettingsFragment());
		listFragments.add(new ProfileFragment());

		// load first fragment as default:
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.main_content, listFragments.get(0)).commit();

		setTitle(listNavItems.get(0).getTitle());
		lvNav.setItemChecked(0, true);
		drawerLayout.closeDrawer(drawerPane);

		// set listener for navigation items:
		lvNav.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// replace the fragment with the selection correspondingly:
				FragmentManager fragmentManager = getSupportFragmentManager();
				fragmentManager
						.beginTransaction()
						.replace(R.id.main_content, listFragments.get(position))
						.commit();

				setTitle(listNavItems.get(position).getTitle());
				lvNav.setItemChecked(position, true);
				drawerLayout.closeDrawer(drawerPane);

			}
		});

		// create listener for drawer layout
		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.string.drawer_opened, R.string.drawer_closed) {

			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				invalidateOptionsMenu();
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				invalidateOptionsMenu();
				super.onDrawerClosed(drawerView);
			}

		};

		drawerLayout.setDrawerListener(actionBarDrawerToggle);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the HomeFragment/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		actionBarDrawerToggle.syncState();
	}
}
