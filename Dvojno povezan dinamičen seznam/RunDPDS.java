package si.feri.aps.vaje;

import java.util.Scanner;

public class RunDPDS{

	public static void main(String[] args) {

		DvojnoPovezanDinamicenSeznam dps = new DvojnoPovezanDinamicenSeznam();

		Scanner scanner = new Scanner(System.in);

		commands:
		while (true){
			System.out.print("Enter command: ");
			int command = scanner.nextInt();

			switch(command){
				case 0:
					break commands;
				case 1: // Vstavi v glavo
					if (dps.size() < 1){
						System.out.println("Seznam je prazen, polja ni bilo mozno dodati!");
						break;
					}
					System.out.print("Vnesi vrednost: ");
					int i = scanner.nextInt();
					dps.dodajPoljeVGlavo(i);
					break;

				case 2: // Vstavi v rep
					if (dps.size() < 1){
						System.out.println("Seznam je prazen, polja ni bilo mozno dodati!");
						break;
					}
					System.out.print("Vnesi vrednost: ");
					int j = scanner.nextInt();
					dps.dodajPoljeVRep(j);
					break;

				case 3:// vstavi na indeks
					if (dps.size() < 1){
						System.out.println("Seznam je prazen, polja ni bilo mozno dodati!");
						break;
					}
					System.out.print("Vnesi vrednost: ");
					int k = scanner.nextInt();
					System.out.print("Vnesi indeks: ");
					int index = scanner.nextInt();
					dps.dodajPolje(k, index);
					break;
				case 4:
					System.out.println(dps);
					break;

				case 5: // Izbrisi na indeksu
					System.out.print("Vnesi indeks: ");
					int q = scanner.nextInt();
					dps.brisiIndex(q);
					break;

				case 6: // IzbrisiElement
					System.out.print("Vnesi vrednost: ");
					int e = scanner.nextInt();
					dps.brisiElement(e);
					break;

				case 8:
					System.out.println(dps);
					dps.info();
					break;

				case 9:
					System.out.print("Vnesi vrednost: ");
					int v = scanner.nextInt();
					dps.dodajZaInit(v);
					break;


			}
		}


	}
}

