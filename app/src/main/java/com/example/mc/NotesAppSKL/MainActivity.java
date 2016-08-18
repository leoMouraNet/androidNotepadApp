package com.example.mc.NotesAppSKL;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    final CharSequence sortOption[] = new CharSequence[] {"Alphabetic A-Z", "Alphabetic Z-A", "Newest Saved", "Oldest Saved"};


    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0:
                    return NotesActivity.newInstance(null, null);
                case 1:
                    return MapActivity.newInstance(null, null);
                default:
                    return NotesActivity.newInstance(null, null);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //return "Tab " + (position + 1);
            switch (position) {
                case 1:
                    return "Map";
                default:
                    return "Notes";
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_newNote) {
            Intent i = new Intent(getApplicationContext(),NewnoteActivity.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.action_sort) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Sort by...");
            builder.setItems(sortOption, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // the user clicked on sortOption[which]
                    //System.out.println("Sort Option: " + sortOption[which]);

                    switch(which) {
                        case 0:
                            System.out.println("Alphabetic A-Z");
                            //Sort Here
                            break;
                        case 1:
                            System.out.println("Alphabetic Z-A");
                            break;
                        case 2:
                            System.out.println("Newest Saved");
                            break;
                        case 3:
                            System.out.println("Oldest Saved");
                            break;
                        default:
                            System.out.println("Alphabetic A-Z");
                    }

                }
            });
            builder.show();

            //Intent i = new Intent(getApplicationContext(),this.NotesActivity);
            //startActivity(i);
            System.out.println("----------------------------");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
