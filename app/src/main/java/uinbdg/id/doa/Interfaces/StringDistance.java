package uinbdg.id.doa.Interfaces;

import java.io.Serializable;

/**
 * Created by pragmadev on 3/5/18.
 */

public interface StringDistance extends Serializable {

    /**
     * Compute and return a measure of distance.
     * Must be >= 0.
     * @param s1
     * @param s2
     * @return
     */
    double distance(String s1, String s2);
}