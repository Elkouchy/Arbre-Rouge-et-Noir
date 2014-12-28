
import java.awt.Color;

public class ArbreRN {
	static Noeud sentinelle;
	static {
		ArbreRN.sentinelle = new Noeud(-1002, Color.WHITE, null, null, null);
	}
	Noeud racine, noeudAjoute;

	public ArbreRN() {
		racine = ArbreRN.sentinelle;
	}
	public boolean recherche_valeur(Noeud n, Noeud ok)
	{
	 	if(n==null)
    		return false;
  		else	
  			if(n.info== ok.info)  			      		      			       			
       			return true;
       		else
       		{       	
           	 if(n.info<ok.info)
           			return(recherche_valeur(n.droit,ok));
           		else 
           			return(recherche_valeur(n.gauche,ok));
			}	
 	}
	public int taille() {
		return taille(racine);
	}

	private int taille(Noeud n) {
		if (n != null)
			return taille(n.gauche) + taille(n.droit) + 1;
		else
			return 0;
	}

	public void ajout(int o) {
		racine = ajout(o, racine, null);
		reOrganiser(noeudAjoute);
	}

	public void supprimer(int o) {
		supprimer(racine, o);
	}
	
	
	public int Hauteur()
	{
		return Hauteur(racine);	
	}
	private int Hauteur(Noeud n)
	{
        if(n == null)
        	return 0;
        else
        	return  (1+(Math.max(Hauteur(n.droit),Hauteur(n.gauche))));
	}

	private Noeud supprimer(Noeud r, int o) {
		if (r.isSentinelle()) {
			return r; 
		}
		if (o==r.info) {
			r = supprimer(r);
		} else if (o<r.info ) {
			supprimer(r.gauche, o);
		} else {
			supprimer(r.droit, o);
		}
		return r;
	}

	private Noeud supprimer(Noeud z) {
		Noeud y, x;
		if (z.gauche.isSentinelle() || z.droit.isSentinelle()) {
			y = z;
		} else {
			y = arbreSuccesseur(z);
		}
		if (!y.gauche.isSentinelle()) {
			x = y.gauche;
		} else {
			x = y.droit;
		}
		x.parent = y.parent;
		if (y.parent == null) {
			racine = x;
		} else if (y == y.parent.gauche) {
			y.parent.gauche = x;
		} else {
			y.parent.droit = x;
		}
		if (y != z) {
			z.info = y.info;
		}
		if (y.couleur == Color.BLACK) {
			reOrganiserSuppression(x);
		}
		return y;
	}

	private void reOrganiserSuppression(Noeud n) {
		while (n != racine && n.couleur == Color.BLACK) {
			if (n == n.parent.gauche) {
				Noeud y = n.parent.droit;
				if (y.couleur == Color.RED) {
					y.couleur = Color.BLACK;
					n.parent.couleur = Color.RED;
					rotationGauche(n.parent);
					y = n.parent.droit;
				}
				if (y.gauche.couleur == Color.BLACK && y.droit.couleur == Color.BLACK) {
					y.couleur = Color.RED;
					n = n.parent;
				} else {
					if (y.droit.couleur == Color.BLACK) {
						y.gauche.couleur = Color.BLACK;
						y.couleur = Color.RED;
						rotationDroite(y);
						y = n.parent.droit;
					}
					y.couleur = n.parent.couleur;
					n.parent.couleur = Color.BLACK;
					y.droit.couleur = Color.BLACK;
					rotationGauche(n.parent);
					break;
				}
			} else {
				Noeud y = n.parent.gauche;
				if (y.couleur == Color.RED) {
					y.couleur = Color.BLACK;
					n.parent.couleur = Color.RED;
					rotationDroite(n.parent);
					y = n.parent.gauche;
				}
				if (y.droit.couleur == Color.BLACK && y.gauche.couleur == Color.BLACK) {
					y.couleur = Color.RED;
					n = n.parent;
				} else {
					if (y.gauche.couleur == Color.BLACK) {
						y.droit.couleur = Color.BLACK;
						y.couleur = Color.RED;
						rotationGauche(y);
						y = n.parent.gauche;
					}
					y.couleur = n.parent.couleur;
					n.parent.couleur = Color.BLACK;
					y.gauche.couleur = Color.BLACK;
					rotationDroite(n.parent);
					break;
				}
			}
		}
		n.couleur = Color.BLACK;
	}

	private Noeud arbreSuccesseur(Noeud x) {
		// le noeud successseur de x dans l'arbre,
		// sentinelle si c'est le plus grand
		if (!x.droit.isSentinelle()) {
			return arbreMinimum(x.droit);
		}
		Noeud y = x.parent;
		while (!y.isSentinelle() && x == y.droit) {
			x = y;
			y = x.parent;
		}
		return y;
	}

	private Noeud arbreMinimum(Noeud x) {
		while (!x.gauche.isSentinelle()) {
			x = x.gauche;
		}
		return x;
	}

	private Noeud ajout(int o, Noeud r, Noeud p) {
		
		if (r.isSentinelle())
			r = noeudAjoute = new Noeud(o, Color.RED, r, r, p);
		else if (o<r.info)
			r.gauche = ajout(o, r.gauche, r);
		else
			r.droit = ajout(o, r.droit, r);
		return r;
	}

	private void rotationGauche(Noeud n) {
		Noeud y = n.droit;
		n.droit = y.gauche;
		if (!y.gauche.isSentinelle())
			y.gauche.parent = n;
		y.parent = n.parent;
		if (n.parent == null)
			racine = y;
		else if (n.parent.gauche == n)
			n.parent.gauche = y;
		else
			n.parent.droit = y;
		y.gauche = n;
		n.parent = y;
	}

	private void rotationDroite(Noeud n) {
		Noeud y = n.gauche;
		n.gauche = y.droit;
		if (!y.droit.isSentinelle())
			y.droit.parent = n;
		y.parent = n.parent;
		if (n.parent == null)
			racine = y;
		else if (n.parent.droit == n)
			n.parent.droit = y;
		else
			n.parent.gauche = y;
		y.droit = n;
		n.parent = y;
	}

	private void reOrganiser(Noeud n) {
		while (n != racine && n.parent.couleur == Color.RED) {
			if (n.parent == n.parent.parent.gauche) {
				Noeud y = n.parent.parent.droit;
				if (y.couleur == Color.RED) {
					n.parent.couleur = Color.BLACK;
					y.couleur = Color.BLACK;
					n.parent.parent.couleur = Color.RED;
					n = n.parent.parent;
				} else {
					if (n == n.parent.droit) {
						n = n.parent;
						rotationGauche(n);
					}
					n.parent.couleur = Color.BLACK;
					n.parent.parent.couleur = Color.RED;
					rotationDroite(n.parent.parent);
				}
			} else {
				Noeud y = n.parent.parent.gauche;
				if (y.couleur == Color.RED) {
					n.parent.couleur = Color.BLACK;
					y.couleur = Color.BLACK;
					n.parent.parent.couleur = Color.RED;
					n = n.parent.parent;
				} else {
					if (noeudAjoute == n.parent.gauche) {
						n = n.parent;
						rotationDroite(n);
					}
					n.parent.couleur = Color.BLACK;
					n.parent.parent.couleur = Color.RED;
					rotationGauche(n.parent.parent);
				}
			}
		}
		racine.couleur = Color.BLACK; // la racine est toujours noire
	}
}