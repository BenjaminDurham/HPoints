package com.aboundmediasolutions.services.hpoints.utilities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.aboundmediasolutions.services.hpoints.R;

public class Strategos {

    protected Fragment fragment;
    protected String transaction, tag;
    protected Integer options;

    /*public void buildTransaction(FragmentActivity activity, Fragment fragment, String transaction, Integer options) {
        this.fragment = fragment;
        this.transaction = transaction;
        this.options = options;

        transaction(activity, fragment, transaction, options);
    } */

    public void buildTransaction(FragmentActivity activity, Fragment fragment, String transaction, String tag, Integer options) {
        this.fragment = fragment;
        this.transaction = transaction;
        this.options = options;
        this.tag = tag;

        transaction(activity);
    }

    /*public void transaction(FragmentActivity activity, Fragment fragment, String transaction, Integer options) {
        switch(transaction) {
            case "add" :
                addFragment(activity, fragment, options);
                break;
            case "replace" :
                replaceFragment(activity, options);
                break;
            case "remove" :

                break;
        }
    } */

    private void transaction(FragmentActivity activity) {
        switch(transaction) {
            case "add" :
                addFragment(activity);
                break;
            case "replace" :
                replaceFragment(activity);
                break;
            case "remove" :
                removeFragment(activity);
                break;
        }
    }

    private void addFragment(FragmentActivity activity) {
        FragmentManager manager = activity.getSupportFragmentManager();
        if(options==1) {
            manager.beginTransaction().add(R.id.fragmentholder, fragment, tag).addToBackStack(null).commit();
        } else {
            manager.beginTransaction().add(R.id.fragmentholder, fragment, tag).commit();
        }
    }


  /* public void addFragment(FragmentActivity activity, Integer options) {
        FragmentManager manager = activity.getSupportFragmentManager();
        if(options==1) {
            manager.beginTransaction().add(R.id.fragmentholder, fragment).addToBackStack(null).commit();
        } else {
            manager.beginTransaction().add(R.id.fragmentholder, fragment).commit();
        }
    } */

   /* public void addFragment(FragmentActivity activity, Fragment fragment, Integer options) {
        FragmentManager manager = activity.getSupportFragmentManager();
        if(options==1) {
            manager.beginTransaction().add(R.id.fragmentholder, fragment).addToBackStack(null).commit();
        } else {
            manager.beginTransaction().add(R.id.fragmentholder, fragment).commit();
        }
    } */

  /*  public void addFragment(FragmentActivity activity, Fragment fragment, String tag, Integer options) {
        FragmentManager manager = activity.getSupportFragmentManager();
        if(options==1) {
            manager.beginTransaction().add(R.id.fragmentholder, fragment, tag).addToBackStack(null).commit();
        } else {
            manager.beginTransaction().add(R.id.fragmentholder, fragment, tag).commit();
        }
    } */

    private void replaceFragment(FragmentActivity activity) {
        FragmentManager manager = activity.getSupportFragmentManager();
        if(options==1){
            manager.beginTransaction().replace(R.id.fragmentholder, fragment, tag).addToBackStack(null).commit();
        } else {
            manager.beginTransaction().replace(R.id.fragmentholder, fragment, tag).commit();
        }
    }


   /* public void replaceFragment(FragmentActivity activity, Integer options) {
        FragmentManager manager = activity.getSupportFragmentManager();
        if(options==1){
            manager.beginTransaction().replace(R.id.fragmentholder, fragment).addToBackStack(null).commit();
        } else {
            manager.beginTransaction().replace(R.id.fragmentholder, fragment).commit();
        }
    } */

   /* public void replaceFragment(FragmentActivity activity, String tag, Integer options) {
        FragmentManager manager = activity.getSupportFragmentManager();
        if(options==1) {
            manager.beginTransaction().replace(R.id.fragmentholder, fragment, tag).addToBackStack(null).commit();
        } else {
            manager.beginTransaction().replace(R.id.fragmentholder, fragment, tag).commit();
        }
    } */

    private void removeFragment(FragmentActivity activity) {
        FragmentManager manager = activity.getSupportFragmentManager();
        if (options == 1) {
            manager.beginTransaction().remove(fragment).addToBackStack(null).commit();
        } else {
            manager.beginTransaction().remove(fragment).commit();
        }
    }

    public interface Communicator {
        public void passToStrategos(String fragmentName, String transaction, String tag, Integer options);
    }


}