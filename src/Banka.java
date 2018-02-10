import java.util.Scanner;

public class Banka {

	static Scanner unos = new Scanner(System.in);
	private static Racun racun;

	public static void main(String[] args) {

		System.out.println("Izaberite opciju: ");
		System.out.println("\n1. Kreiranje ra�una \n2. Prebacivanje novca \n3. Ispisivanje ra�una ");
		int izbor = unos.nextInt();

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
		}

	}

	private static void kreiranjeRacuna() {

		System.out.println("Unesite broj ra�una: ");
		int brojRacuna = unos.nextInt();

		while (provjeraRacuna(brojRacuna)) {
			System.out.println("Broj ra�una vec postoji ili je negativan broj, poku�ajte sa drugim brojem: ");
			brojRacuna = unos.nextInt();
		}

		System.out.println("Unesite ime korisnika: ");
		String ime = unos.next();

		System.out.println("Unesite iznos na ra�unu: ");
		double iznosNaRacunu = unos.nextInt();

		while (provjeraIznosa(iznosNaRacunu)) {
			System.out.println("Unijeli ste negativan iznos, unesite ponovno:");
			iznosNaRacunu = unos.nextDouble();
		}

		racun = new Racun(brojRacuna, ime, iznosNaRacunu);
		System.out.println("\nUspje�no kreiran ra�un.\n");
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

		System.out.println("Unesite broj ra�una sa kojeg prebacujete novac: ");
		int sourceAcc = unos.nextInt();

		while (!provjeraBrojaRacuna(sourceAcc)) {
			System.out.println("Une�eni ra�un ne postoji, provjerite ra�un i poku�ajte ponovno: ");
			sourceAcc = unos.nextInt();
		}

		System.out.println("Unesite broj ra�una na koji prebacujete novac: ");
		int targetAcc = unos.nextInt();

		while (!provjeraBrojaRacuna(targetAcc)) {
			System.out.println("Une�eni ra�un ne postoji, provjerite ra�un i poku�ajte ponovno: ");
			sourceAcc = unos.nextInt();
		}

		System.out.println("Unesite iznos novca koji �elite prebaciti: ");
		double iznos = unos.nextDouble();

		int indexS = indexSource(sourceAcc);
		int indexT = indexTarget(targetAcc);

		double stanjeSource = Racun.getRacuni().get(indexS).getIznosNaRacunu();
		double stanjeTarget = Racun.getRacuni().get(indexT).getIznosNaRacunu();

		if (stanjeSource < iznos) {
			System.out.println("Nema dovoljno novca za transakciju, poku�ajte sa manjim iznosom: ");
			iznos = unos.nextDouble();
		}

		Racun.getRacuni().get(indexS).setIznosNaRacunu(stanjeSource - iznos);
		Racun.getRacuni().get(indexT).setIznosNaRacunu(stanjeTarget + iznos);

		System.out.println("Transakcija uspije�no zavr�ena.");
	}

	public static Racun getRacun() {
		return racun;
	}

	public static void setRacun(Racun racun) {
		Banka.racun = racun;
	}
}