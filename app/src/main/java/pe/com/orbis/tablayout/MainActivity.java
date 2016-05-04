package pe.com.orbis.tablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import pe.com.orbis.tablayout.fragment.FiveFragment;
import pe.com.orbis.tablayout.fragment.FourFragment;
import pe.com.orbis.tablayout.fragment.OneFragment;
import pe.com.orbis.tablayout.fragment.ThreeFragment;
import pe.com.orbis.tablayout.fragment.TwoFragment;
import pe.com.orbis.tablayout.model.entity.DataEntity;

public class MainActivity extends AppCompatActivity{

    private Toolbar toolbar;
    private EditText txtSearch;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String data;
    private String[] titles = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        titles[0] = getString(R.string.search);
        titles[1] = getString(R.string.my_applications);
        titles[2] = getString(R.string.favorites);
        titles[3] = getString(R.string.notifications);
        titles[4] = getString(R.string.my_account);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        txtSearch = (EditText) findViewById(R.id.txtSearch);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_GO){
                    data = txtSearch.getText().toString();
                    EventBus.getDefault().post(new DataEntity(data));
                    viewPager.setCurrentItem(0);
                    return true;
                }
                return false;
            }
        });

    }

    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.icon_tab, null);
        tabOne.setText(titles[0]);
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_tab1, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.icon_tab, null);
        tabTwo.setText(titles[1]);
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_tab2, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.icon_tab, null);
        tabThree.setText(titles[2]);
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_tab3, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.icon_tab, null);
        tabFour.setText(titles[3]);
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_tab4, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);

        TextView tabFive = (TextView) LayoutInflater.from(this).inflate(R.layout.icon_tab, null);
        tabFive.setText(titles[4]);
        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_tab5, 0, 0);
        tabLayout.getTabAt(4).setCustomView(tabFive);

        viewPager.setCurrentItem(1);
        viewPager.setCurrentItem(0);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), titles[0]);
        adapter.addFragment(new TwoFragment(), titles[1]);
        adapter.addFragment(new ThreeFragment(), titles[2]);
        adapter.addFragment(new FourFragment(), titles[3]);
        adapter.addFragment(new FiveFragment(), titles[4]);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
