package com.example.android.stagstreet;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class sidemenu extends AppCompatActivity {
    private ResideMenu resideMenu;
    private Context mContext;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemNewsFeed;
    private ResideMenuItem itemExplore;
    private ResideMenuItem itemEvents;
    private ResideMenuItem itemTimeline;
    private ResideMenuItem itemLogout;

    // declaring for the left side
    private ResideMenuItem itemStories;
    private ResideMenuItem itemSavedMedia;
    private ResideMenuItem itemAcitivityLog;
    private ResideMenuItem itemLocation;
    private ResideMenuItem itemSettings ;
    private ResideMenuItem itemInviteFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidemenu);
        mContext = this;
        setUpMenu();
        if( savedInstanceState == null )
            return ;
          //changeFragment(new HomeFragment());

    }
    private void setUpMenu(){
        resideMenu = new ResideMenu(this);
        resideMenu.attachToActivity(this);
        resideMenu.setBackground(R.drawable.profile_pic);
        // declaring the intents in the left side
        itemHome     = new ResideMenuItem(this, R.drawable.profile_pic,     "Profile");
        itemNewsFeed = new ResideMenuItem(this,R.drawable.profile_pic,"The NewsFeed");
        itemExplore   = new ResideMenuItem(this,R.drawable.profile_pic,"Explore");
        itemEvents    = new ResideMenuItem(this,R.drawable.profile_pic,"Events");
        itemTimeline  = new ResideMenuItem(this,R.drawable.profile_pic,"Timeline");
        itemLogout  = new ResideMenuItem(this,R.drawable.profile_pic,"Logout");
        //declaring the intents in the right side
        itemStories = new ResideMenuItem(this,R.drawable.profile_pic,"Stories");
        itemSavedMedia = new ResideMenuItem(this,R.drawable.profile_pic,"Saved Media");
        itemAcitivityLog = new ResideMenuItem(this,R.drawable.profile_pic,"Acitivity log");
        itemLocation = new ResideMenuItem(this,R.drawable.profile_pic,"Location");
        itemSettings = new ResideMenuItem(this,R.drawable.profile_pic,"Settings");
        itemInviteFriends = new ResideMenuItem(this,R.drawable.profile_pic,"Invite Friends");




        //setting the intents to the left side
        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemNewsFeed,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemExplore,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemEvents,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemTimeline,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemLogout,ResideMenu.DIRECTION_LEFT);
        // setting the intents to the right side of the sideMenu
        resideMenu.addMenuItem(itemStories,ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemSavedMedia,ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemAcitivityLog,ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemLocation,ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemSettings,ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemInviteFriends,ResideMenu.DIRECTION_RIGHT);


        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
