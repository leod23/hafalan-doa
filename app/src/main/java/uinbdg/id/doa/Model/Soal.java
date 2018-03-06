package uinbdg.id.doa.Model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Soal {
    @Id(assignable = true)
    private long id;
    private String jawaban;
    private String pertanyaanLanjutan;
    private String pertanyaan;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setPertanyaanLanjutan(String pertanyaanLanjutan) {
        this.pertanyaanLanjutan = pertanyaanLanjutan;
    }

    public String getPertanyaanLanjutan() {
        return pertanyaanLanjutan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    @Override
    public String toString() {
        return
                "Soal{" +
                        "id = '" + id + '\'' +
                        ",jawaban = '" + jawaban + '\'' +
                        ",pertanyaan_lanjutan = '" + pertanyaanLanjutan + '\'' +
                        ",pertanyaan = '" + pertanyaan + '\'' +
                        "}";
    }
}
