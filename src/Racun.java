import java.util.ArrayList;

public class Racun {

	private int brojRacuna;
	private String ime;
	private double iznosNaRacunu;
	public static ArrayList<Racun> racuni = new ArrayList<>();

	public Racun() {

	}

	public Racun(int brojRacuna, String ime, double iznosNaRacunu) {

		this.setBrojRacuna(brojRacuna);
		this.setIme(ime);
		this.setIznosNaRacunu(iznosNaRacunu);
		racuni.add(this);

	}

	public static ArrayList<Racun> getRacuni() {
		return racuni;
	}

	public static void setRacuni(ArrayList<Racun> racuni) {
		Racun.racuni = racuni;
	}

	public int getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(int brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public double getIznosNaRacunu() {
		return iznosNaRacunu;
	}

	public void setIznosNaRacunu(double iznosNaRacunu) {
		this.iznosNaRacunu = iznosNaRacunu;
	}

	public static void ispis() {
		for (Racun e : racuni) {
			System.out.print("\nBroj racuna:" + e.brojRacuna + "\nIme: " + e.ime + "\nIznos na racunu: "
					+ e.iznosNaRacunu + "\n\n");
		}

	}

}
