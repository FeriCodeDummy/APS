package si.feri.aps.vaje;

import java.util.*;

public class BinarnoDrevoFilmi {

	public Vozlisce koren;
	public int predecessor, successor;
	private ArrayList<Integer> elementi;




	private class Vozlisce{
		public int vrednost;
		ArrayList<String> naslov;
		Vozlisce leviOtrok;
		Vozlisce desniOtrok;
		Vozlisce oce;


		public Vozlisce(int vrednost, String naslov){
			this.naslov = new ArrayList<>();
			this.naslov.add(naslov);
			this.vrednost = vrednost;
			this.desniOtrok = null;
			this.leviOtrok = null;
			this.oce = null;

		}

		public void dodajVKljuc(){}
	}

	public BinarnoDrevoFilmi(){
		this.koren = null;
		this.elementi = new ArrayList<>(); //TODO sprement to

	}

	private Vozlisce dodajRekurzivno(Vozlisce trenutno, int vrednost, String naslov){
		if(trenutno == null){
			return new Vozlisce(vrednost, naslov);
		}
		if (vrednost < trenutno.vrednost){
			trenutno.leviOtrok = dodajRekurzivno(trenutno.leviOtrok, vrednost, naslov);
		}else if(vrednost > trenutno.vrednost){
			trenutno.desniOtrok = dodajRekurzivno(trenutno.desniOtrok, vrednost, naslov);
		}else{
			return trenutno;
		}

		return trenutno;
	}

	public void dodaj(int vrednost, String naslov){
		Vozlisce x = this.koren;
		Vozlisce y = null;

		if(!vsebujeVozlisce(vrednost)){
			this.koren = dodajRekurzivno(this.koren, vrednost, naslov);
		}else {
			while (x != null) {
				y = x;
				if (vrednost < x.vrednost) {
					x = x.leviOtrok;
				} else if (vrednost > x.vrednost) {
					x = x.desniOtrok;
				} else {
					x.naslov.add(naslov);
					return;
				}
			}

			Vozlisce z = new Vozlisce(vrednost, naslov);
			z.oce = y;
			if (y == null) {
				this.koren = z;
			} else {
				if (z.vrednost < y.vrednost) {
					y.leviOtrok = z;
				} else {
					y.desniOtrok = z;
				}
			}
		}


	}

	public void successorPredecessor(Vozlisce root, int val) {
		if (root != null) {
			if (root.vrednost == val) {

				if (root.leviOtrok != null) {
					Vozlisce t = root.desniOtrok;
					while (t.desniOtrok != null) {
						t = t.desniOtrok;
					}
					this.predecessor = t.vrednost;
				}
				if (root.leviOtrok != null) {
					Vozlisce t = root.desniOtrok;
					while (t.leviOtrok != null) {
						t = t.leviOtrok;
					}
					this.successor = t.vrednost;
				}
			} else if (root.vrednost > val) {
				this.successor = root.vrednost;
				successorPredecessor(root.leviOtrok, val);
			} else if (root.vrednost < val) {
				this.predecessor = root.vrednost;
				successorPredecessor(root.desniOtrok, val);
			}
		}
	}


	public void UrejenIzpis(Vozlisce koren){
		if(koren != null){
			UrejenIzpis(koren.leviOtrok);
			System.out.println(koren.vrednost + ArrayListNaslov(koren.naslov));
			UrejenIzpis(koren.desniOtrok);
		}
	}

	private String ArrayListNaslov(ArrayList<String> al){
		StringBuilder s = new StringBuilder("{ ");
		for (String x : al){
			s.append(x).append(", ");
		}
		s.append("}");
		return s.toString();
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


	public boolean filmObstaja(Vozlisce trenutno, int vrednost, String film) {
		if (trenutno == null) {
			return false;
		}
		if (vrednost == trenutno.vrednost) {
			return trenutno.naslov.contains(film);
		}
		if (vrednost < trenutno.vrednost){
			return filmObstaja(trenutno.leviOtrok, vrednost, film);}
		else{
			return filmObstaja(trenutno.desniOtrok, vrednost, film);}
	}


	private String isciFilmeRekurzivno(Vozlisce trenutno, int vrednost) {
		if (trenutno == null) {
			return null;
		}
		if (vrednost == trenutno.vrednost) {
			return ArrayListNaslov(trenutno.naslov);
		}
		if (vrednost < trenutno.vrednost){
			return isciFilmeRekurzivno(trenutno.leviOtrok, vrednost);}
		else{
			return isciFilmeRekurzivno(trenutno.desniOtrok, vrednost);}
	}

	public void brisiFilmeRekurzivno(Vozlisce trenutno, int vrednost, String film){
		if (trenutno == null) {
			return;
		}
		if (vrednost == trenutno.vrednost) {
			trenutno.naslov.remove(film);
		}
		if (vrednost < trenutno.vrednost){
			brisiFilmeRekurzivno(trenutno.leviOtrok, vrednost, film);}
		else{
			brisiFilmeRekurzivno(trenutno.desniOtrok, vrednost, film);}

	}

	public void isciFilme(int leto){
		if (!vsebujeVozlisce(leto)){
			System.out.println("V drevesu ni filmov s to letnico");
		}else{
			String filmi = isciFilmeRekurzivno(this.koren, leto);
			System.out.println(filmi);
			}
		}


	public int findSuccessor(Vozlisce root, Vozlisce succ, int key)
	{
		// base case
		if (root == null) {
			return succ.vrednost;
		}

		// if a node with the desired value is found, the successor is the minimum
		// value node in its right subtree (if any)
		if (root.vrednost == key)
		{
			if (root.desniOtrok != null) {
				return najmanjseVozlisce(root.desniOtrok);
			}
		}

		// if the given key is less than the root node, recur for the left subtree
		else if (key < root.vrednost)
		{
			// update successor to the current node before recursing in the
			// left subtree
			succ = root;
			return findSuccessor(root.leviOtrok, succ, key);
		}

		// if the given key is more than the root node, recur for the right subtree
		else {
			return findSuccessor(root.desniOtrok, succ, key);
		}

		return succ.vrednost;
	}



	public int findPredecessor(Vozlisce root, Vozlisce prec, int key)
	{
		// base case
		if (root == null) {
			return prec.vrednost;
		}

		// if a node with the desired value is found, the predecessor is the maximum
		// value node in its left subtree (if any)
		if (root.vrednost == key)
		{
			if (root.leviOtrok != null) {
				return najvecjeVozlisce(root.leviOtrok);
			}
		}

		// if the given key is less than the root node, recur for the left subtree
		else if (key < root.vrednost) {
			return findPredecessor(root.leviOtrok, prec, key);
		}

		// if the given key is more than the root node, recur for the right subtree
		else {
			// update predecessor to the current node before recursing
			// in the right subtree
			prec = root;
			return findPredecessor(root.desniOtrok, prec, key);
		}
		return prec.vrednost;
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
		//this.elementi.remove(getElementIndex(ArrayListtoArray(this.elementi), vrednost));
		this.koren = brisiRekurzivno(this.koren, vrednost);
	}

	public int najvecjeVozlisce(Vozlisce koren){
		return koren.desniOtrok == null ? koren.vrednost : najvecjeVozlisce(koren.desniOtrok);
	}

	public void naj(Vozlisce vozlisce){
		int min = najmanjseVozlisce(vozlisce);
		int max = najvecjeVozlisce(vozlisce);
		System.out.println("Najnovejsi filmi so " + isciFilmeRekurzivno(vozlisce, max));
		System.out.println("Najstarejsi filmi so " + isciFilmeRekurzivno(vozlisce, min));
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
