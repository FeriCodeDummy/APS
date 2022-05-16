package si.feri.aps.vaje;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Tester {


	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Iskanje iskanje = new Iskanje();
		ArrayList<Vozlisce> graf = new ArrayList<>();

		commands:
		while(true){
			meni();
			System.out.print("Enter command: ");
			int command = s.nextInt();
			switch(command){
				case 0:
					break commands;

				case 1:
					graf = iskanje.parseGraph("graf.txt");
					break;
				case 2:
					System.out.print("Začetno vozlisce: ");
					int v1 = s.nextInt();
					System.out.print("Končno vozlišče: ");
					int v2 = s.nextInt();
					ArrayList<Vozlisce> path = iskanje.IskanjeVGlobino(graf, v1, v2);
					iskanje.izpis(path,v1, v2);
					break;
				case 3:
					System.out.print("Začetno vozlisce: ");
					int v3 = s.nextInt();
					System.out.print("Končno vozlišče: ");
					int v4 = s.nextInt();
					ArrayList<Vozlisce> path2 = iskanje.IskanjeVSirino(graf, v3, v4);
					iskanje.izpis(path2,v3, v4);
			}
		}
	}

	private static void meni(){
		System.out.println("0) Izhod iz programa");
		System.out.println("1) Generiraj graf");
		System.out.println("2) Iskanje v globino");
		System.out.println("3) Iskanje v širino");
	}


}

