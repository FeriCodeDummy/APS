package si.feri.aps.vaje;

import java.util.Arrays;
import java.util.Scanner;

public class TestBT {


	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int koren = s.nextInt();
		BinarnoDrevo bd = new BinarnoDrevo(koren);

		int vrednost;

		commands:
		while (true){
			System.out.print("Enter command: ");
			int command = s.nextInt();
			switch(command){
				case 0: // Exit program
					break commands;

				case 1: // Add node
					System.out.print("Enter value: ");
					vrednost = s.nextInt();

					bd.dodaj(vrednost);
					break;

				case 2: // Remove node
					System.out.print("Enter value: ");
					vrednost = s.nextInt();
					if (!bd.vsebujeVozlisce(vrednost)){
						System.out.println("To vozlisce ne obstaja!");
						break;
					}
					bd.brisi(vrednost);
					break;

				case 3: // Previous node
					System.out.print("Enter value: ");
					vrednost = s.nextInt();
					if (!bd.vsebujeVozlisce(vrednost)){
						System.out.println("To vozlisce ne obstaja!");
						break;
					}
					try{
						int[] drevo = bd.urediDrevo();
						System.out.println("Predhodnik je " + drevo[java.util.Arrays.binarySearch(drevo, vrednost) - 1]);
					}catch(IndexOutOfBoundsException e){
						System.out.println("Element nima predhodnika");
					}
					break;

				case 4: // Next node
					System.out.print("Enter value: ");
					vrednost = s.nextInt();
					if (!bd.vsebujeVozlisce(vrednost)){
						System.out.println("To vozlisce ne obstaja!");
						break;
					}
					try{
						int[] drevo = bd.urediDrevo();
						System.out.println("Naslednik je " + drevo[java.util.Arrays.binarySearch(drevo, vrednost) + 1]);
					}catch(IndexOutOfBoundsException e){
						System.out.println("Element nima naslednika");
					}
					break;

				case 5: // Sorted tree
					System.out.println(Arrays.toString(bd.urediDrevo()));
					break;

				case 6: // Highest element
					System.out.println("Najvisji element je " + bd.najvecjeVozlisce(bd.koren));
					break;


				case 7: // Lowest element
					System.out.println("Najmanjsi element je " + bd.najmanjseVozlisce(bd.koren));
					break;

				case 8: // Element exist
					System.out.print("Enter value: ");
					vrednost = s.nextInt();
					if (bd.vsebujeVozlisce(vrednost)){
						System.out.println("Drevo ima element");
					}else{
						System.out.println("Drevo nima elementa");
					}

			}
		}


	}
}

