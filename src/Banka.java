import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Banka {

	static Scanner unos = new Scanner(System.in);
	private static Racun racun;
	static int izbor, brojRacuna, sourceAcc, targetAcc;
	static int brojac = 0;
	static double iznosNaRacunu, iznos;


	public static void main(String[] args) throws IOException {

		if (brojac == 0) {
			citaj();
			brojac++;
		}

		boolean nastavak = true;

		do {
			try {

				System.out.println("Izaberite opciju: ");
				System.out.println(
						"\n1. Kreiranje raèuna \n2. Prebacivanje novca \n3. Ispisivanje raèuna \n4. Exit&Save ");
				izbor = unos.nextInt();

				if (izbor < 1 || izbor > 4) {
					nastavak = true;
					System.err.println("Pogresan unos, unesite brojeve izmedju 1 - 4!");
					continue;
				}

				nastavak = false;

			} catch (InputMismatchException e) {
				System.err.println("Pogresan unos, unesite brojeve izmedju 1 - 4!");
				unos.nextLine();
			}
		} while (nastavak);

		switch (izbor) {

		case 1:

			kreiranjeRacuna();
			Banka.main(args);

			break;

		case 2:

			transakcija();
			Banka.main(args);

			break;

		case 3:

			Racun.ispis();
			Banka.main(args);

			break;
		case 4:
			upisi();
			System.exit(0);
		}

	}

	private static void kreiranjeRacuna() throws IOException {

		boolean nastavak = true;
		do {
			try {
				System.out.println("Unesite broj raèuna: ");
				brojRacuna = unos.nextInt();
				nastavak = false;
			} catch (InputMismatchException e) {
				System.err.print("Pogresan unos!");
				unos.nextLine();
			}
		} while (nastavak);
		while (provjeraRacuna(brojRacuna)) {
			System.err.println("Broj raèuna vec postoji ili je negativan broj, pokušajte sa drugim brojem: ");
			brojRacuna = unos.nextInt();
		}

		System.out.println("Unesite ime korisnika: ");
		String ime = unos.next();

		nastavak = true;

		do {
			try {
				System.out.println("Unesite iznos na raèunu: ");
				iznosNaRacunu = unos.nextDouble();
				nastavak = false;
			} catch (InputMismatchException e) {
				System.err.print("Pogresan unos!");
				unos.nextLine();
			}
		} while (nastavak);

		while (provjeraIznosa(iznosNaRacunu)) {
			System.err.println("Unijeli ste negativan iznos, unesite ponovno:");
			iznosNaRacunu = unos.nextDouble();
		}

		racun = new Racun(brojRacuna, ime, iznosNaRacunu);
		System.out.println("\nUspješno kreiran raèun.\n");
	}

	private static boolean provjeraRacuna(int brojRacuna) {
		for (Racun racun : Racun.racuni) {
			if (brojRacuna == racun.getBrojRacuna() || brojRacuna < 0) {
				return true;
			}
		}
		return false;

	}

	private static boolean provjeraIznosa(double iznosNaRacunu) {

		if (iznosNaRacunu < 0) {
			return true;
		}

		return false;

	}

	private static boolean provjeraBrojaRacuna(int brojRacuna) {
		for (Racun e : Racun.racuni) {
			if (brojRacuna == e.getBrojRacuna()) {
				return true;
			}
		}
		return false;
	}

	private static int indexSource(int sourceAcc) {
		for (Racun e : Racun.getRacuni()) {
			if (sourceAcc == e.getBrojRacuna()) {
				int indexS = Racun.getRacuni().indexOf(e);
				return indexS;
			}
		}
		return sourceAcc;
	}

	private static int indexTarget(int targetAcc) {
		for (Racun e : Racun.getRacuni()) {
			if (targetAcc == e.getBrojRacuna()) {
				int indexT = Racun.getRacuni().indexOf(e);
				return indexT;
			}
		}
		return targetAcc;
	}

	private static void transakcija() {

		boolean nastavak = true;

		do {
			try {
				System.out.println("Unesite broj raèuna sa kojeg prebacujete novac: ");
				sourceAcc = unos.nextInt();
				nastavak = false;
			} catch (InputMismatchException e) {
				System.err.print("Pogresan unos!");
				unos.nextLine();
			}
		} while (nastavak);

		while (!provjeraBrojaRacuna(sourceAcc)) {
			System.out.println("Unešeni raèun ne postoji, provjerite raèun i pokušajte ponovno: ");
			sourceAcc = unos.nextInt();
		}

		nastavak = true;

		do {
			try {
				System.out.println("Unesite broj raèuna na koji prebacujete novac: ");
				targetAcc = unos.nextInt();
				nastavak = false; 
			} catch (InputMismatchException e) {
				System.err.print("Pogresan unos!");
				unos.nextLine();
			}
		} while (nastavak);

		while (!provjeraBrojaRacuna(targetAcc)) {
			System.out.println("Unešeni raèun ne postoji, provjerite raèun i pokušajte ponovno: ");
			sourceAcc = unos.nextInt();
		}

		nastavak = true;

		do {
			try {
				System.out.println("Unesite iznos novca koji želite prebaciti: ");
				iznos = unos.nextDouble();
				nastavak = false;
			} catch (InputMismatchException e) {
				System.err.print("Pogresan unos!");
				unos.nextLine();
			}
		} while (nastavak);

		int indexS = indexSource(sourceAcc);
		int indexT = indexTarget(targetAcc);

		double stanjeSource = Racun.getRacuni().get(indexS).getIznosNaRacunu();
		double stanjeTarget = Racun.getRacuni().get(indexT).getIznosNaRacunu();

		if (stanjeSource < iznos) {
			System.out.println("Nema dovoljno novca za transakciju, pokušajte sa manjim iznosom: ");
			iznos = unos.nextDouble();
		}

		Racun.getRacuni().get(indexS).setIznosNaRacunu(stanjeSource - iznos);
		Racun.getRacuni().get(indexT).setIznosNaRacunu(stanjeTarget + iznos);

		System.out.println("Transakcija uspiješno završena.");
	}

	public static Racun getRacun() {
		return racun;
	}

	public static void setRacun(Racun racun) {
		Banka.racun = racun;
	}

	public static void upisi() throws IOException {

		Path path = Paths.get("Racuni.txt");
		BufferedWriter writer = Files.newBufferedWriter(path);

		for (Racun e : Racun.getRacuni()) {
			writer.write(e.getBrojRacuna() + " ");
			writer.write(e.getIme() + " ");
			writer.write(String.valueOf(e.getIznosNaRacunu()));
			writer.newLine();

		}
		writer.close();

	}

	public static void citaj() throws IOException {

		Path path = Paths.get("Racuni.txt");
		BufferedReader reader = Files.newBufferedReader(path);
		String line;

		while ((line = reader.readLine()) != null) {
			String[] var = line.split(" ");

			racun = new Racun(Integer.parseInt(var[0]), var[1], Double.parseDouble(var[2]));

		}
		reader.close();
	}
}