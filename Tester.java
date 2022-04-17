package si.feri.aps.vaje;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Tester {


	public static void main(String[] args) throws IOException {

		Scanner s = new Scanner(System.in);
		BinarnoDrevoFilmi bdf = new BinarnoDrevoFilmi();
		commands:
		while (true) {
			meni();
			System.out.print("Enter command: ");
			int command = s.nextInt();
			switch (command) {
				case 0: // Exit program
					break commands;

				case 1:
					System.out.print("Vnesi datum: ");
					int df = s.nextInt();
					s.nextLine();
					System.out.print("Vnesi film");
					String naslov = s.nextLine();
					bdf.dodaj(df, naslov);
					break;
				case 2:
					bdf.UrejenIzpis(bdf.koren);
					break;
				case 3:
					System.out.print("Vnesi datum: ");
					int izdaja = s.nextInt();

					long sT = System.nanoTime();
					bdf.isciFilme(izdaja);
					long eT = System.nanoTime();
					long dr = eT-sT;

					System.out.println("Iskanje datuma " + izdaja + " je potrebovalo " + dr/1000000 + "ns");
					break;

				case 4:
					bdf.naj(bdf.koren);
					break;
				case 5:
					System.out.print("Vnesi datum: ");
					int nd = s.nextInt();
					if(!bdf.vsebujeVozlisce(nd)){
						System.out.println("Datuma ni v drevesu");
						break;
					}

					System.out.println("Predhodnik: " + bdf.findPredecessor(bdf.koren, null, nd) + "\nNaslednik: " + bdf.findSuccessor(bdf.koren, null, nd));
					break;
				case 6:
					System.out.print("Datum: ");
					int d = s.nextInt();
					if(!bdf.vsebujeVozlisce(d)){
						System.out.println("Datuma ni v drevesu");
					}
					bdf.brisi(d);
					break;

				case 7:
					System.out.print("Datum: ");
					int f = s.nextInt();
					if(!bdf.vsebujeVozlisce(f)){
						System.out.println("Datuma ni v drevesu");
						break;
					}
					s.nextLine();
					System.out.print("Naslov filma: ");
					String F = s.nextLine();
					if(!bdf.filmObstaja(bdf.koren,f,F)){
						System.out.println("Filma ni v vozliscu " + f);
						break;
					}
					bdf.brisiFilmeRekurzivno(bdf.koren, f, F);
					break;
				case 8:

					bdf.koren = null; // Reset bdf

					meniDatotek();
					System.out.print("Izberi datoteko: ");
					String filename = "";
					int fl = s.nextInt();
					switch (fl) {
						case 1 -> filename = "IMBD_date_name_full.list";
						case 2 -> filename = "IMDB_date_name_full_sorted.list";
						case 3 -> filename = "IMDB_date_name_mini.list";
						case 4 -> filename = "IMDB_date_name_mini_sorted.list";
					}

					long startTime = System.nanoTime();
					File file = new File(filename);
					BufferedReader b = new BufferedReader(new FileReader(file));
					String readLine = "";

					readLine = b.readLine();
					int N = Integer.parseInt(readLine);

					for (int i = 0; i < N; i++) {
						readLine = b.readLine();
						if (readLine.isBlank())
							continue;

						String datumString = readLine.substring(0, 8);
						String ime = readLine.substring(9);
						int datum = Integer.parseInt(datumString);
						//System.out.println(datum + " : " + ime);
						bdf.dodaj(datum, ime);
					}
					long endTime = System.nanoTime();

					long duration = (endTime - startTime);

					System.out.println(N +" filov se je nalozilo v " + duration/1000000 + "ms");


					break;

				default:
					System.out.println("Ukaz ni veljaven");
					break;
			}
		}

	}

	private static void meni(){
		System.out.println("0) Izhod iz programa");
		System.out.println("1) Vnos filma na datum izdaje");
		System.out.println("2) Urejen izpis vseh filmov");
		System.out.println("3) Izpis filmov na datum izdaje");
		System.out.println("4) Izpisi najstarejse in najnovejse filme");
		System.out.println("5) Poisci sosednja datuma");
		System.out.println("6) Brisi datum");
		System.out.println("7) Brisi film na izbran datum");
		System.out.println("8) Nalaganje filmov iz datoteke");
	}

	private static void meniDatotek(){
		System.out.println("1) IMDB_date_name_full.list");
		System.out.println("2) IMDB_date_name_full_sorted.list");
		System.out.println("3) IMDB_date_name_mini.list");
		System.out.println("4) IMDB_date_name_mini_sorted.list");
	}
}

