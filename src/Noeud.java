import java.awt.Color;

public class Noeud {
	java.awt.Color couleur;
	int info;
	Noeud gauche, droit, parent;

	Noeud(int o) {
		couleur = Color.BLACK;
		info = o;
		gauche = droit = parent = null;
	}

	Noeud(int o, Color c, Noeud g, Noeud d, Noeud p) {
		couleur = c;
		info = o;
		gauche = g;
		droit = d;
		parent = p;
	}

	boolean isSentinelle() {
		return this == ArbreRN.sentinelle;
	}

	int getHauteur() {
		if (isSentinelle())
			return 0;
		else
			return 1 + Math.max(gauche.getHauteur(), droit.getHauteur());
	}
}