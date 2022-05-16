package si.feri.aps.vaje;

import java.util.ArrayList;

public class Vozlisce {
public int dolzina;
public ArrayList<Vozlisce> sosedi;
public int indeks;
public Status status;
public String ime;
public int predhodnjik;
public int cena;

public Vozlisce(String ime, int indeks) {
		this.sosedi = new ArrayList<>();

		this.ime = ime;
		this.indeks = indeks;
		this.status = Status.NEPREGLEDAN;
		}
	}
