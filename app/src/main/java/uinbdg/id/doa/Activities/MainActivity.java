package uinbdg.id.doa.Activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import uinbdg.id.doa.Fragment.SampleFragment;
import uinbdg.id.doa.R;
import uinbdg.id.doa.Util.CommonUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import uinbdg.id.doa.Util.LevenshteinDistance;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private AccountHeader headerResult = null;
    private Drawer result = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView(savedInstanceState);

    }

    private void initView(Bundle savedInstanceState) {
        CommonUtil.initToolbar(this,toolbar,getResources().getString(R.string.app_name),false);
        CommonUtil.displayFragment(MainActivity.this,R.id._container, SampleFragment.newInstance("Home","Desc"),"TAG");

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(new ColorDrawable(getResources().getColor(R.color.colorPrimary)))
                .withSelectionListEnabledForSingleProfile(false)
                .addProfiles(new ProfileDrawerItem().withName("Jack Patterson").withEmail("jack@gmail.com").withIcon(R.drawable.ic_account_circle_black_24dp).withIdentifier(100))
//                .addProfiles(new ProfileDrawerItem().withName(session.getUserName()).withEmail(session.getEmail()).withIcon(R.mipmap.ic_launcher).withIdentifier(100))
                .withSavedInstance(savedInstanceState)
                .build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withAccountHeader(headerResult)
                .addDrawerItems(new PrimaryDrawerItem().withName("Home").withIcon(R.drawable.ic_home_black_24dp).withIdentifier(1).withSelectable(false),
                        new PrimaryDrawerItem().withName("Grid Menu").withIcon(R.drawable.ic_assignment_black_24dp).withIdentifier(2).withSelectable(false),
                        new PrimaryDrawerItem().withName("Menu 2").withIcon(R.drawable.ic_assignment_black_24dp).withIdentifier(3).withSelectable(false),
                        new ExpandableDrawerItem().withName("Menu Expandable").withIcon(R.drawable.ic_assignment_black_24dp).withIdentifier(20).withSelectable(false).withSubItems(
                                new SecondaryDrawerItem().withName("Sub Menu 1").withLevel(2).withIdentifier(21),
                                new SecondaryDrawerItem().withName("Sub Menu 2").withLevel(2).withIdentifier(22)
                        ),
                        new PrimaryDrawerItem().withName("Logout").withIdentifier(100).withSelectable(false))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            if (drawerItem.getIdentifier() == 1) {
                                CommonUtil.displayFragment(MainActivity.this,R.id._container, SampleFragment.newInstance("Home","Desc"),"TAG");
                            }else if (drawerItem.getIdentifier() == 2) {
                                startActivity(new Intent(MainActivity.this,GridMenuActivity.class));
                            }else if (drawerItem.getIdentifier() == 2) {
                                CommonUtil.displayFragment(MainActivity.this,R.id._container, SampleFragment.newInstance("Menu 2","Desc"),"TAG");
                            }else if (drawerItem.getIdentifier() == 21) {
                                CommonUtil.displayFragment(MainActivity.this,R.id._container, SampleFragment.newInstance("Sub Menu 1","Desc"),"TAG");
                            }else if (drawerItem.getIdentifier() == 22) {
                                CommonUtil.displayFragment(MainActivity.this,R.id._container, SampleFragment.newInstance("Sub Menu 2","Desc"),"TAG");
                            }
                            else if (drawerItem.getIdentifier() == 100) {

                            }
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

    }


}
