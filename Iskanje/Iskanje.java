package si.feri.aps.vaje;

import java.io.File;
import java.util.*;

public class Iskanje {

	public void izpis(ArrayList<Vozlisce> path, int s, int v) {

		if (v == s){
			System.out.println("Pot zaključena");
		}else{
			if(path.get(v).predhodnjik == -1){
				System.out.println("Med " + path.get(s).ime + " in " + path.get(v).ime + " ni poti!");
			}else{
				izpis(path, s, path.get(v).predhodnjik);
				System.out.println(path.get(v).ime);
			}
		}
	}

	static class Povezava{
		public int cena;
		public int v1;
		public int v2;

		public Povezava(int v1, int v2, int cena){
			this.v1 = v1;
			this.v2 = v2;
			this.cena = cena;
		}
	}



	public ArrayList<Vozlisce> IskanjeVGlobino(ArrayList<Vozlisce> G, int s, int d) {
		Stack<Vozlisce> sklad = new Stack<>();

		for (Vozlisce v : G) {
			if (v.indeks != s) {
				v.dolzina = Integer.MAX_VALUE;
			}
			v.predhodnjik = -1;
		}
		G.get(s).status = Status.TRENUTEN;
		G.get(s).dolzina = 0;

		sklad.push(G.get(s));
		while (!sklad.isEmpty()) {
			Vozlisce v = sklad.pop();
			if (v.indeks == d) {
				return G;
			}
			if (!v.status.equals(Status.NEPREGLEDAN)) {
				//System.out.println(v.ime);
				for (Vozlisce u : v.sosedi) {
					if (u.status.equals(Status.NEPREGLEDAN)) {
						u.status = Status.TRENUTEN;
						u.dolzina = v.dolzina + 1;
						u.predhodnjik = v.indeks;
						sklad.push(u);
						}

				}
				v.status = Status.PREGLEDAN;
			}
		}
		return G;

	}

	public ArrayList<Vozlisce> IskanjeVSirino(ArrayList<Vozlisce> G, int s, int v){
		LinkedList<Vozlisce> vrsta = new LinkedList<>();
		for (Vozlisce V : G) {
			if (V.indeks != s) {
				V.dolzina = Integer.MAX_VALUE;
			}
			V.predhodnjik = -1;
		}
		G.get(s).status = Status.TRENUTEN;
		G.get(s).dolzina = 0;

		vrsta.add(G.get(s));
		while (vrsta.size()!=0){
			Vozlisce t = vrsta.poll();
			if(t.indeks == v){
				return G;
			}
			if(!t.status.equals(Status.NEPREGLEDAN)){
				for (Vozlisce u : t.sosedi){
					u.status = Status.TRENUTEN;
					u.dolzina = t.dolzina+1;
					u.predhodnjik = t.indeks;
					vrsta.add(u);
				}
			}

			t.status = Status.PREGLEDAN;


		}
		return G;
	}


	public ArrayList<Vozlisce> parseGraph(String f) {
		try {
			File file = new File(f);
			Scanner s = new Scanner(file);
			int v = Integer.parseInt(s.nextLine());
			int p = Integer.parseInt(s.nextLine());
			ArrayList<Vozlisce> graf = new ArrayList<>();
			for (int i = 0; i < v; i++) {
				graf.add(new Vozlisce(" Vozlisce " + i, i));
			}
			while (s.hasNextLine()) {
				String[] arr = s.nextLine().split(" ");
				System.out.println(Arrays.toString(arr));
				int v1 = Integer.parseInt(arr[0]) - 1;
				int v2 = Integer.parseInt(arr[1]) - 1;
				graf.get(v1).sosedi.add(getVozlisce(graf, v2));
				graf.get(v2).sosedi.add(getVozlisce(graf, v1));
			}
			return graf;

		} catch (Exception e) {
			System.out.println("Error loading graph: " + e);
			return null;
		}

	}

	public Vozlisce getVozlisce(ArrayList<Vozlisce> graf, int index) {
		for (Vozlisce v : graf) {
			if (v.indeks == index) {
				return v;
			}
		}
		return null;
	}


	private ArrayList<Vozlisce> path(Stack<Vozlisce> s) {
		ArrayList<Vozlisce> t = new ArrayList<>();
		while (!s.isEmpty()) {
			t.add(0, s.pop());
		}
		return t;
	}
}
