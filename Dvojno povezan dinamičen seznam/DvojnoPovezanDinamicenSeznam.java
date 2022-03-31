package si.feri.aps.vaje;

import java.util.ArrayList;

public class DvojnoPovezanDinamicenSeznam {

	private Polje glava;
	private Polje rep;
	private ArrayList<Polje> seznam;




	private class Polje {

		int vrednost;
		Polje prejsnje;
		Polje naslednje;

		public Polje(int value) {
			this.vrednost = value;
		}
	}

	public DvojnoPovezanDinamicenSeznam() {
		seznam = new ArrayList();
		this.glava = null;
		this.rep = null;

	}

	public void dodajZaInit(int value){
		Polje polje = new Polje(value);

		if (seznam.size() == 0){
			polje.prejsnje = null;
			polje.naslednje = this.rep;
			seznam.add(polje);
			this.glava = polje;

		}else if(seznam.size() == 1){
			polje.prejsnje = this.glava;
			polje.naslednje = null;
			seznam.add(polje);
			this.rep = polje;
			this.glava.naslednje = polje;

		}else{
			System.out.println("Ta funkcija je rezervirana za inicializacijo seznama!");
		}

	}

	public void dodajPoljeVRep(int value) {
		Polje novoPolje = new Polje(value);
		novoPolje.naslednje = null;
		this.rep.naslednje = novoPolje;
		this.rep = novoPolje;
		this.seznam.add(novoPolje);
	}

	public void dodajPoljeVGlavo(int value) {
		Polje novoPolje = new Polje(value);
		this.glava.prejsnje = novoPolje;
		novoPolje.naslednje = this.glava;
		this.glava = novoPolje;
		this.seznam.add(0, novoPolje);
	}

	public void brisiIndex(int index){

		if (index == 0){
			this.glava = this.glava.naslednje;
			this.seznam.remove(0);
			this.glava.prejsnje = null;
		} else if(index == this.seznam.size() - 1){
			this.rep = this.rep.prejsnje;
			this.rep.naslednje = null;
		} else{
			try{
				Polje tmp1 = this.seznam.get(index).naslednje;
				Polje tmp2 = this.seznam.get(index).prejsnje;
				this.seznam.get(index-1).naslednje = tmp1;
				this.seznam.get(index+1).naslednje = tmp2;
				this.seznam.remove(index);

			}catch (IndexOutOfBoundsException e){
				System.out.println("Indeks ni veljaven");
			}
		}
	}

	public static boolean elementNeObstaja(ArrayList<Polje> arr, int element){
		for (Polje p : arr){
			if (p.vrednost == element){
				return false;
			}
		}return true;
	}

	public void brisiElement(int e) {

		if (elementNeObstaja(this.seznam, e)){
			System.out.println("Element se ne nahaja v seznamu");
		} else{
			int index = 0;
			for (int i=0; i<this.seznam.size(); i++) {
				if (this.seznam.get(i).vrednost == e) {
					index = i;
				}
			}
			System.out.println(index);
			if (index == 0){
				this.glava = this.glava.naslednje;
				this.seznam.remove(0);
				this.glava.prejsnje = null;
			} else if(index == this.seznam.size() - 1){
				this.rep = this.rep.prejsnje;
				this.rep.naslednje = null;
			} else{
				Polje tmp1 = this.seznam.get(index).naslednje;
				Polje tmp2 = this.seznam.get(index).prejsnje;
				this.seznam.get(index-1).naslednje = tmp1;
				this.seznam.get(index+1).naslednje = tmp2;
				this.seznam.remove(index);
			}
		}

	}

	public void info(){

		System.out.println("Glava: " + this.glava.vrednost);
		System.out.println("Rep: " + this.rep.vrednost);
	}

	public void dodajPolje(int value, int index){

		if (index == 0){
			dodajPoljeVGlavo(value);
		}

		else if(index == this.seznam.size() -1){
			Polje novoPolje = new Polje(value);
			novoPolje.naslednje = null;
			this.rep.naslednje = novoPolje;
			this.rep = novoPolje;
			this.seznam.add(novoPolje);
		}

		else {
			Polje novoPolje = new Polje(value);
			try {
				this.seznam.add(index, novoPolje);
				this.seznam.get(index - 1).naslednje = novoPolje;
				this.seznam.get(index + 1).prejsnje = novoPolje;
				this.seznam.get(index).naslednje = this.seznam.get(index + 1);
				this.seznam.get(index).prejsnje = this.seznam.get(index - 1);

			} catch (IndexOutOfBoundsException e) {
				System.out.println("[-] Indeks ni veljaven");
			}
		}
	}

	public int size(){
		return this.seznam.size();
	}

	@Override
	public String toString() {
		StringBuilder r = new StringBuilder("[ ");
		for (Polje p : this.seznam){
			r.append(p.vrednost).append(" ");
		}
		r.append("]");

		return r.toString();
	}
}
