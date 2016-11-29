package in.tosc.digitaloceanapp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import in.tosc.digitaloceanapp.R;
import in.tosc.digitaloceanapp.fragments.AdditionalDetailsFragment;
import in.tosc.digitaloceanapp.fragments.DataCenterFragment;
import in.tosc.digitaloceanapp.fragments.SelectImageFragment;
import in.tosc.digitaloceanapp.fragments.SelectSizeFragment;
import in.tosc.doandroidlib.objects.Droplet;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class DropletCreateActivity extends AppCompatActivity {

    static Droplet droplet;
    int count = 1;

    public static Droplet getDroplet(){
        return droplet;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        droplet = new Droplet();
        setContentView(R.layout.activity_droplet_create);
    }

    private void removeFragment(int count) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (count){
            case 4 :
                fragmentManager.popBackStack("additionalDetailsFragment" , FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.commit();
                break;
            case 3 :
                fragmentManager.popBackStack("DataCenterFragment" , FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.commit();
                break;
            case 2 :
                fragmentManager.popBackStack("selectSizeFragment" , FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.commit();
                break;
            case 1 :
                this.finish();
                break;
            default:
                count = 1;
                this.finish();
                break;
        }
    }

    private void addFragment(int count) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch(count){
            case 2 :
                SelectSizeFragment selectSizeFragment = new SelectSizeFragment();
                fragmentTransaction.replace(R.id.fragmentHolder,selectSizeFragment,"SELECT_SIZE");
                fragmentTransaction.addToBackStack("selectSizeFragment");
                fragmentTransaction.commit();
                break;
            case 3 :
                DataCenterFragment selectDataCenter = new DataCenterFragment();
                fragmentTransaction.replace(R.id.fragmentHolder,selectDataCenter,"DATA_CENTER");
                fragmentTransaction.addToBackStack("DataCenterFragment");
                fragmentTransaction.commit();
                break;
            case 4 :
                AdditionalDetailsFragment additionalDetailsFragment = new AdditionalDetailsFragment();
                fragmentTransaction.replace(R.id.fragmentHolder,additionalDetailsFragment,"ADDITIONAL_DETAILS");
                fragmentTransaction.addToBackStack("additionalDetailsFragment");
                fragmentTransaction.commit();
                break;
            case 5:
                createDroplet(droplet);
                break;
            default:
                this.finish();
                count = 1;
        }
    }

    private void createDroplet(Droplet droplet) {
        //TODO Make network call to create a droplet
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SelectImageFragment selectImageFragment = new SelectImageFragment();
        fragmentTransaction.replace(R.id.fragmentHolder,selectImageFragment,"CREATE_DROPLET");
//        fragmentTransaction.addToBackStack("additionalDetailsFragment");
        fragmentTransaction.commit();

    }

    public void previous(View view) {
        Log.e("Decreased count", String.valueOf(count));
        removeFragment(count);
        count--;
    }

    public void next(View view) {
        count++;
        addFragment(count);
        Log.e("Increased count", String.valueOf(count));
    }
}