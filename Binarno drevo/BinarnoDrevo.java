package si.feri.aps.vaje;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BinarnoDrevo {

	public Vozlisce koren;
	private ArrayList<Integer> elementi;

	private class Vozlisce{
		int vrednost;
		Vozlisce leviOtrok;
		Vozlisce desniOtrok;

		public Vozlisce(int vrednost){
			this.vrednost = vrednost;
			this.desniOtrok = null;
			this.leviOtrok = null;
		}
	}

	public BinarnoDrevo(int vrednost){
		this.koren = new Vozlisce(vrednost);
		this.elementi = new ArrayList<>();
		this.elementi.add(vrednost);

	}

	private Vozlisce dodajRekurzivno(Vozlisce trenutno, int vrednost){
		if(trenutno == null){
			return new Vozlisce(vrednost);
		}
		if (vrednost < trenutno.vrednost){
			trenutno.leviOtrok = dodajRekurzivno(trenutno.leviOtrok, vrednost);
		}else if(vrednost > trenutno.vrednost){
			trenutno.desniOtrok = dodajRekurzivno(trenutno.desniOtrok, vrednost);
		}else{
			return trenutno;
		}

		return trenutno;
	}

	public void dodaj(int vrednost){
		this.elementi.add(vrednost);
		this.koren = dodajRekurzivno(this.koren, vrednost);
	}

	private boolean  vsebujeVozlisceRekurzivno(Vozlisce trenutno, int vrednost) {
		if (trenutno == null) {
			return false;
		}
		if (vrednost == trenutno.vrednost) {
			return true;
		}
		if (vrednost < trenutno.vrednost){
			 return vsebujeVozlisceRekurzivno(trenutno.leviOtrok, vrednost);}
		else{
			 return vsebujeVozlisceRekurzivno(trenutno.desniOtrok, vrednost);}
	}

	public boolean vsebujeVozlisce(int vrednost) {
		return vsebujeVozlisceRekurzivno(this.koren, vrednost);
	}

	private Vozlisce brisiRekurzivno(Vozlisce trenutno, int vrednost) {
		if (trenutno == null) {
			return null;
		}

		if (vrednost == trenutno.vrednost) {
			if (trenutno.leviOtrok == null && trenutno.desniOtrok == null) {
				return null;
			}

			if (trenutno.desniOtrok == null) {
				return trenutno.leviOtrok;
			}

			if (trenutno.leviOtrok == null) {
				return trenutno.desniOtrok;
			}

			int smallestValue = najmanjseVozlisce(trenutno.desniOtrok);
			trenutno.vrednost = smallestValue;
			trenutno.desniOtrok = brisiRekurzivno(trenutno.desniOtrok, smallestValue);
			return trenutno;
		}
		if (vrednost < trenutno.vrednost) {
			trenutno.leviOtrok = brisiRekurzivno(trenutno.leviOtrok, vrednost);
			return trenutno;
		}
		trenutno.desniOtrok = brisiRekurzivno(trenutno.desniOtrok, vrednost);
		return trenutno;
	}


	public int najmanjseVozlisce(Vozlisce koren) {
		return koren.leviOtrok == null ? koren.vrednost : najmanjseVozlisce(koren.leviOtrok);
	}

	public void brisi(int vrednost) {
		this.elementi.remove(getElementIndex(ArrayListtoArray(this.elementi), vrednost));
		this.koren = brisiRekurzivno(this.koren, vrednost);
	}

	public int najvecjeVozlisce(Vozlisce koren){
		return koren.desniOtrok == null ? koren.vrednost : najvecjeVozlisce(koren.desniOtrok);
	}


	private int[] ArrayListtoArray(ArrayList<Integer> arraylist){
		int[] arr = new int[arraylist.size()];
		for (int i = 0; i < arraylist.size(); i++){
			arr[i] = this.elementi.get(i);
		}
		return arr;
	}

	public int[] urediDrevo(){
		int[] arr = ArrayListtoArray(this.elementi);
		Arrays.sort(arr);
		return arr;
	}

	private int getElementIndex(int[] array, int element){
		Arrays.sort(array);
		return java.util.Arrays.binarySearch(array, element);
	}

}
