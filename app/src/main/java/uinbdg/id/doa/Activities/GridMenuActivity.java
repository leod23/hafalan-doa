package uinbdg.id.doa.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import uinbdg.id.doa.Adapter.AdapterGridMenu;
import uinbdg.id.doa.R;
import uinbdg.id.doa.Util.CommonUtil;
import uinbdg.id.doa.Util.DummyData;
import uinbdg.id.doa.Util.Tools;
import uinbdg.id.doa.Widget.SpacingItemDecoration;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GridMenuActivity extends AppCompatActivity {
    private View parent_view;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private AdapterGridMenu adapterGridMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_menu);
        ButterKnife.bind(this);
        parent_view = findViewById(android.R.id.content);

        initView();
    }

    private void initView() {
        CommonUtil.initToolbar(this, toolbar, getResources().getString(R.string.app_name), true);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new SpacingItemDecoration(1, Tools.dpToPx(this, 1), true));
        recyclerView.setHasFixedSize(true);

        adapterGridMenu = new AdapterGridMenu(this, DummyData.listMenu());
        recyclerView.setAdapter(adapterGridMenu);
        adapterGridMenu.setOnItemClickListener(new AdapterGridMenu.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String obj, int position) {
                Snackbar.make(parent_view, "Item " + position + " clicked", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
