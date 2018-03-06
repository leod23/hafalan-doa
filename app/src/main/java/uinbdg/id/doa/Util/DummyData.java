package uinbdg.id.doa.Util;

import java.util.ArrayList;
import java.util.List;

import uinbdg.id.doa.Model.GridMenu;
import uinbdg.id.doa.R;

/**
 * Created by Comp on 2/11/2018.
 */

public class DummyData {
    public static List<GridMenu> listMenu(){
        List<GridMenu> gridMenus = new ArrayList<>();
        gridMenus.add(new GridMenu(R.drawable.ic_account_circle_black_24dp,"MENU1"));
        gridMenus.add(new GridMenu(R.drawable.ic_assignment_black_24dp,"MENU2"));
        gridMenus.add(new GridMenu(R.drawable.ic_accessible_black_24dp,"MENU3"));
        gridMenus.add(new GridMenu(R.drawable.ic_card_membership_black_24dp,"MENU4"));
        gridMenus.add(new GridMenu(R.drawable.ic_home_black_24dp,"MENU5"));
        gridMenus.add(new GridMenu(R.drawable.ic_account_balance_black_24dp,"MENU6"));
        gridMenus.add(new GridMenu(R.drawable.ic_alarm_black_24dp,"MENU7"));
        gridMenus.add(new GridMenu(R.drawable.ic_work_black_24dp,"MENU8"));
        gridMenus.add(new GridMenu(R.drawable.ic_youtube_searched_for_black_24dp,"MENU10"));
        gridMenus.add(new GridMenu(R.drawable.ic_announcement_black_24dp,"MENU11"));
        gridMenus.add(new GridMenu(R.drawable.ic_assignment_ind_black_24dp,"MENU12"));
        return gridMenus;
    }
}
