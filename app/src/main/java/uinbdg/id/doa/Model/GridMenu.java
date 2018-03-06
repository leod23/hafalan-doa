package uinbdg.id.doa.Model;

/**
 * Created by Comp on 2/11/2018.
 */

public class GridMenu {
    int icon;
    String title;

    public GridMenu(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
