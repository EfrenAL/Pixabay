package com.efren.pixabay.view;


import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import com.efren.pixabay.R;
import com.efren.pixabay.base.Constants;
import com.efren.pixabay.view.details.DetailsFragment;
import com.efren.pixabay.view.images.ImageFragment;
import javax.inject.Inject;
import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector, ImageFragment.DataPassListener {


    @Inject
    public DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    private ImageFragment imageFragment;
    private DetailsFragment detailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidInjection.inject(this); //configureDagger

        if (savedInstanceState == null) {
            imageFragment = new ImageFragment();
            detailsFragment = new DetailsFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fr_images, imageFragment, "image")
                    .commit();
        } else {
            imageFragment = (ImageFragment) getSupportFragmentManager().findFragmentByTag("image");
            detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentByTag("details");
            if (detailsFragment == null) detailsFragment = new DetailsFragment();
        }
    }

    private void showDetailsFragment(Integer pos) {

        Bundle bundle = new Bundle();
        bundle.putInt(Constants.POSITION, pos);

        detailsFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_images, detailsFragment, "details")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                imageFragment.requestImages(query);

                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                searchItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public void passData(Integer pos) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.details))
                .setMessage(getString(R.string.message))
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showDetailsFragment(pos);
                    }
                })
                .setNegativeButton(getString(R.string.cancel), null)
                .show();
    }
}
