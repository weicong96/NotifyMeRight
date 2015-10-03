package ideas.weicong.com.notifymeright;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.androidannotations.annotations.EActivity;

import ideas.weicong.com.notifymeright.adapters.DrawerItemsListAdapter;

@EActivity
public class MainActivity extends ActionBarActivity implements FragmentManager.OnBackStackChangedListener {
    private String[] mDrawerArray;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ArrayAdapter<String> mArrayAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle, mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        mDrawerTitle = mTitle = getTitle();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame,new TaskListFragment_()).commit();


        mDrawerArray = this.getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this , mDrawerLayout, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(mTitle);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        shouldDisplayHomeUp();

        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mArrayAdapter = new DrawerItemsListAdapter(this, mDrawerArray);
        mDrawerList.setAdapter(mArrayAdapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = null;
                switch(position){
                    //case 0:
                    //    fragment = new MainFragment();
                    //    break;
                    case 0 :
                        //fragment = new MapPagerFragment();
                        break;
                }
                if(fragment != null){

                    for(int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); ++i) {
                        getSupportFragmentManager().popBackStack();
                    }
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();

                    mDrawerList.setItemChecked(position, true);
                    mDrawerList.setSelection(position);
                    if(getSupportActionBar() != null){
                        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                        setTitle(mDrawerArray[position]);
                    }
                    mDrawerLayout.closeDrawer(mDrawerList);
                }
            }
        });
    }
    public void shouldDisplayHomeUp(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }
}
