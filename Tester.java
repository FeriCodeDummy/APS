package si.feri.aps.vaje;


import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Tester {


	public static void main(String[] args)  {
		int[] array = new int[]{1,2,3,4};
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
					System.out.print("Vnesi velikost seznama: ");
					int v = s.nextInt();
					array = randomArray(v);
					break;

				case 2:
					System.out.print("Vnesi velikost seznama: ");
					int f = s.nextInt();
					array = ascendingArray(f);
					break;
				case 3:
					System.out.print("Vnesi velikost seznama: ");
					int e = s.nextInt();
					array = descendingArray(e);
					break;

				case 4:
					System.out.println(Arrays.toString(array));
					break;

				case 5:
					System.out.println(IsArraySorted(array));
					break;

				case 6:
					Uredi uredi1 = new Uredi(array, "QuickSort");
					long s1 = System.nanoTime();
					array = uredi1.uredi();
					long e1 = System.nanoTime();
					System.out.println("QuickSort je trajal: " + (e1-s1));
					break;

				case 7:
					Uredi uredi2 = new Uredi(array, "QuickSortMedian");
					long s2 = System.nanoTime();
					array = uredi2.uredi();
					long e2 = System.nanoTime();
					System.out.println("QuickSort je trajal: " + (e2-s2));
					break;

				case 8:
					Uredi uredi3 = new Uredi(array, "DualPivotQuickSort");
					long s3 = System.nanoTime();
					array = uredi3.uredi();
					long e3 = System.nanoTime();
					System.out.println("DualPivotQuickSort je trajal: " + (e3-s3));
					break;


				default:
					System.out.println("Ukaz ni veljaven");
					break;
			}
		}

	}

	private static int[] ascendingArray(int velikost){
		int[] arr = randomArray(velikost);
		Arrays.sort(arr);
		return arr;
	}

	private static int[] descendingArray(int velikost){
		int[] arr = randomArray(velikost);
		Arrays.sort(arr);
		return IntStream.rangeClosed(1, arr.length).map(i -> arr[arr.length-i]).toArray();
	}

	private static int[] randomArray(int velikost){
		Random rd = new Random(); // creating Random object
		int[] arr = new int[velikost];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = rd.nextInt();
		}
		return arr;
	}

	private static void meni(){
		System.out.println("0) Izhod iz programa");
		System.out.println("1) Generiraj nakljucni seznam");
		System.out.println("2) Generiraj narascujoce zaporeje");
		System.out.println("3) Generiraj padajoce zaporedje");
		System.out.println("4) Izpis zaporedja");
		System.out.println("5) Preveri ali je zaporedje urejeno");
		System.out.println("6) Uredi s hitrim urejanjem brez mediane");
		System.out.println("7) Uredi s hitrim urejanjem z mediano");
		System.out.println("8) Uredi z drugim algoritmom za urejanje");

	}

	private static String IsArraySorted(int[] arr){
		int[] temp = Arrays.copyOf(arr, arr.length);
		Arrays.sort(temp);
		return Arrays.equals(arr, temp) ? "Seznam je urejen" : "Seznam ni urejen";
	}

}

