package com.dadanchi.e_meal.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dadanchi.e_meal.Home.HomeActivity;
import com.dadanchi.e_meal.auth.AuthPresenter;
import com.dadanchi.e_meal.auth.login.LoginActivity;
import com.dadanchi.e_meal.products.ProductsActivity;
import com.dadanchi.e_meal.profile.ProfileActivity;
import com.dadanchi.e_meal.repositories.AuthRepository;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by dadanchi on 11/10/2017.
 */

public abstract class BaseDrawerActivity extends DaggerAppCompatActivity implements Drawer.OnDrawerItemClickListener {

    private static final String EXTRA_IDENTIFIER = "EXTRA_IDENTIFIER";
    private Drawer mDrawer;
    private AuthPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new AuthPresenter();
        mPresenter.setRepository(new AuthRepository());
        setupDrawer();
    }

    private void setupDrawer() {
        Intent intent = getIntent();
        long currentIdentifier = intent.getLongExtra(EXTRA_IDENTIFIER, 1);

        Toolbar toolbar = getToolbar();
        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(new PrimaryDrawerItem()
                    .withIdentifier(1)
                    .withName("Home")
                    .withDescriptionTextColor(0xfff77f00))
                .addDrawerItems(new SecondaryDrawerItem()
                        .withIdentifier(2)
                        .withName("Profile")
                        .withDescriptionTextColor(0xfff77f00))
                .addDrawerItems(new SecondaryDrawerItem()
                    .withIdentifier(3)
                    .withName("Products")
                    .withDescriptionTextColor(0xfff77f00))
                .addDrawerItems(new SecondaryDrawerItem()
                    .withIdentifier(4)
                    .withName("Log out"))
                .withSelectedItem(currentIdentifier)
                .withOnDrawerItemClickListener(this)
                .build();

    }

    /**
     * Method for providing toolbar for drawer button
     *
     * @return the toolbar element of the view
     */
    protected abstract Toolbar getToolbar();

    @Override
    public boolean onItemClick(
            View view,
            int position,
            IDrawerItem drawerItem) {

        Intent intent = null;
        switch ((int) drawerItem.getIdentifier()) {
            case 1:
                intent = new Intent(this, HomeActivity.class);
                break;
            case 2:
                intent = new Intent(this, ProfileActivity.class);
                break;
            case 3:
                intent = new Intent(this, ProductsActivity.class);
                break;
            case 4:
                mPresenter.logout();
                intent = new Intent(this, LoginActivity.class);
                break;
            default:
                return false;
        }

        intent.putExtra(EXTRA_IDENTIFIER, drawerItem.getIdentifier());

        startActivity(intent);
        return true;
    }

    public Drawer getDrawer() {
        return mDrawer;
    }
}
