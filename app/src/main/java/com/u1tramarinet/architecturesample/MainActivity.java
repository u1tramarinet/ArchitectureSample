package com.u1tramarinet.architecturesample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.u1tramarinet.architecturesample.login.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragmentWithTitle(LoginFragment.newInstance(), "Login Sample");
    }

    private void addFragmentWithTitle(@NonNull Fragment fragment, @NonNull String title) {
        addFragment(fragment);
        updateTitle(title);
    }

    private void addFragment(@NonNull Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.commit();
    }

    private void updateTitle(@NonNull String title) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);
    }
}