package uinbdg.id.doa.Model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by pragmadev on 3/6/18.
 */

@Entity
public class Skor {
    @Id(assignable = true)
    private long id;

    int nilai;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }
}
