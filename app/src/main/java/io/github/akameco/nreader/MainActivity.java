package io.github.akameco.nreader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import io.github.akameco.nreader.fragments.ListFragment;

public class MainActivity extends AppCompatActivity {
	private FragmentManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manager = getSupportFragmentManager();
		initFragment();
	}

	private void initFragment() {
		Fragment fragment;
		fragment = ListFragment.newInstance();
		manager.beginTransaction().add(R.id.main_container, fragment).commit();
	}
}
