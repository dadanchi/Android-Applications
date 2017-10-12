package com.dadanchi.e_meal.dagger;

import com.dadanchi.e_meal.Home.HomeActivity;
import com.dadanchi.e_meal.Home.HomeModule;
import com.dadanchi.e_meal.RecipeDetails.RecipeDetailsActivity;
import com.dadanchi.e_meal.RecipeDetails.RecipeDetailsContracts;
import com.dadanchi.e_meal.RecipeDetails.RecipeDetailsModule;
import com.dadanchi.e_meal.auth.AuthModule;
import com.dadanchi.e_meal.auth.login.LoginActivity;
import com.dadanchi.e_meal.auth.register.RegisterActivity;
import com.dadanchi.e_meal.products.ProductsActivity;
import com.dadanchi.e_meal.products.ProductsModule;
import com.dadanchi.e_meal.profile.ProfileActivity;
import com.dadanchi.e_meal.profile.ProfileModule;
import com.dadanchi.e_meal.recipes.RecipeActivity;
import com.dadanchi.e_meal.recipes.RecipeModule;
import com.dadanchi.e_meal.repositories.AuthRepository;
import com.dadanchi.e_meal.repositories.RepositoriesModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by dadanchi on 08/10/2017.
 */

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity homeActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = ProductsModule.class)
    abstract ProductsActivity productsActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = RecipeModule.class)
    abstract RecipeActivity recipeActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = RecipeDetailsModule.class)
    abstract RecipeDetailsActivity recipeDetailsActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = ProfileModule.class)
    abstract ProfileActivity profileActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = AuthModule.class)
    abstract RegisterActivity registerActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = AuthModule.class)
    abstract LoginActivity loginActivity();
}
