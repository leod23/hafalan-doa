package uinbdg.id.doa.Model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity public class Doa{

	@Id(assignable = true)
	private long id;


	private String lapadz;


	private String latin;


	private String arti;

	private String namaDoa;

	public void setLapadz(String lapadz){
		this.lapadz = lapadz;
	}

	public String getLapadz(){
		return lapadz;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getId(){
		return id;
	}

	public void setLatin(String latin){
		this.latin = latin;
	}

	public String getLatin(){
		return latin;
	}

	public void setArti(String arti){
		this.arti = arti;
	}

	public String getArti(){
		return arti;
	}

	public void setNamaDoa(String namaDoa){
		this.namaDoa = namaDoa;
	}

	public String getNamaDoa(){
		return namaDoa;
	}

	@Override
 	public String toString(){
		return 
			"Doa{" + 
			"lapadz = '" + lapadz + '\'' + 
			",id = '" + id + '\'' + 
			",latin = '" + latin + '\'' + 
			",arti = '" + arti + '\'' + 
			",nama_doa = '" + namaDoa + '\'' + 
			"}";
		}
}