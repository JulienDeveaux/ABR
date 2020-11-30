import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * <p>
 * Implantation de l'interface Collection basée sur les arbres binaires de
 * recherche. Les éléments sont ordonnés soit en utilisant l'ordre naturel (cf
 * Comparable) soit avec un Comparator fourni à la création.
 * </p>
 * 
 * <p>
 * Certaines méthodes de AbstractCollection doivent être surchargées pour plus
 * d'efficacité.
 * </p>
 * 
 * @param <E>
 *            le type des clés stockées dans l'arbre
 */
public class ABR<E> extends AbstractCollection<E> {
	private Noeud racine;
	private int taille;
	private Comparator<? super E> cmp;

	private class Noeud {
		E cle;
		Noeud gauche;
		Noeud droit;
		Noeud pere;

		Noeud(E cle) {
			this.cle = cle;
			this.droit = droit;
			this.gauche = gauche;
			this.pere = null;
		}

		/**
		 * Renvoie le noeud contenant la clé minimale du sous-arbre enraciné
		 * dans ce noeud
		 * 
		 * @return le noeud contenant la clé minimale du sous-arbre enraciné
		 *         dans ce noeud
		 */
		Noeud minimum() {
			Noeud n = new Noeud(cle);
			while (n.gauche != null) {
				n = n.gauche;
			}
			return n;
		}

		/**
		 * Renvoie le successeur de ce noeud
		 * 
		 * @return le noeud contenant la clé qui suit la clé de ce noeud dans
		 *         l'ordre des clés, null si c'es le noeud contenant la plus
		 *         grande clé
		 */
		Noeud suivant() {
			Noeud n = new Noeud(cle);
			if(n.droit != null) {
				return minimum();
			}
			Noeud o = n.pere;
			while(o != null && n == o.droit) {
				n = o;
				o = o.pere;
			}
			return o;
		}
	}

	// Consructeurs

	/**
	 * Crée un arbre vide. Les éléments sont ordonnés selon l'ordre naturel
	 */
	public ABR() {
		taille = 0;
		racine = null;
		cmp = (e1, e2) -> ((Comparable<E>)e1).compareTo(e2);
	}

	/**
	 * Crée un arbre vide. Les éléments sont comparés selon l'ordre imposé par
	 * le comparateur
	 * 
	 * @param cmp
	 *            le comparateur utilisé pour définir l'ordre des éléments
	 */
	public ABR(Comparator<? super E> cmp) {
		taille = 0;
		racine = null;
		this.cmp = cmp;
	}

	/**
	 * Constructeur par recopie. Crée un arbre qui contient les mêmes éléments
	 * que c. L'ordre des éléments est l'ordre naturel.
	 * 
	 * @param c
	 *            la collection à copier
	 */
	public ABR(Collection<? extends E> c) {
		if(c.isEmpty()) {
			taille = 0;
			racine = null;
			cmp = (e1, e2) -> ((Comparable<E>)e1).compareTo(e2);
		} else {
			for(E e : c) {
				taille ++;
			}
			E t = c.iterator().next();
			Noeud n = new Noeud(t);
			racine = n;
			cmp = (e1, e2) -> ((Comparable<E>)e1).compareTo(e2);
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new ABRIterator();
	}

	@Override
	public int size() {
		return taille;
	}

	// Quelques méthodes utiles

	/**
	 * Recherche une clé. Cette méthode peut être utilisée par
	 * {@link #contains(Object)} et {@link #remove(Object)}
	 * 
	 * @param o
	 *            la clé à chercher
	 * @return le noeud qui contient la clé ou null si la clé n'est pas trouvée.
	 */
	private Noeud rechercher(Object o) {
		Noeud res = racine;

		while(!res.cle.equals((E)o)) {
			if(cmp.compare(res.cle, (E)o) < 0) res = res.droit;
			if(cmp.compare(res.cle, (E)o) > 0) res = res.gauche;
		}

		return res;
		/*Noeud x = racine;

        while (x != null && x.cle != (E)o) {
            if (cmp.compare(x.cle, (E)o) > 0) {
                x = x.gauche;
            } else {
                x = x.droit;
            }
        }
        return x;
        }*/
	}

	/**
	 * Supprime le noeud z. Cette méthode peut être utilisée dans
	 * {@link #remove(Object)} et {@link Iterator#remove()}
	 * 
	 * @param z
	 *            le noeud à supprimer
	 * @return le noeud contenant la clé qui suit celle de z dans l'ordre des
	 *         clés. Cette valeur de retour peut être utile dans
	 *         {@link Iterator#remove()}
	 */
	private Noeud supprimer(Noeud z) {
		Noeud y = new Noeud(null);
		Noeud x = new Noeud(null);
		if (z.gauche == null || z.droit == null)
    		y = z;
  		else
    		y = z.suivant();				//a faire
  		// y est le nœud à détacher

  		if (y.gauche != null)
    		x = y.gauche;
  		else
    		x = y.droit;
  		// x est le fils unique de y ou null si y n'a pas de fils

  		if (x != null) x.pere = y.pere;

  		if (y.pere == null) { // suppression de la racine
    		racine = x;
  		} else {
    		if (y == y.pere.gauche)
      			y.pere.gauche = x;
    		else
      			y.pere.droit = x;
  		}

  		/*if (y != z) z.cle = y.cle;
  			y = null;*/

		return y;
	}

	/**
	 * Les itérateurs doivent parcourir les éléments dans l'ordre ! Ceci peut se
	 * faire facilement en utilisant {@link Noeud#minimum()} et
	 * {@link Noeud#suivant()}
	 */
	private class ABRIterator implements Iterator<E> {
		public boolean hasNext() {
			/*if(this.suivant() != null) {
				return true;
			} else {
				return false;
			}*/
			return false;
		}

		public E next() {
			// TODO
			return null;
		}

		public void remove() {
			// TODO
		}
	}

	// Pour un "joli" affichage

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		toString(racine, buf, "", maxStrLen(racine));
		return buf.toString();
	}

	private void toString(Noeud x, StringBuffer buf, String path, int len) {
		if (x == null)
			return;
		toString(x.droit, buf, path + "D", len);
		for (int i = 0; i < path.length(); i++) {
			for (int j = 0; j < len + 6; j++)
				buf.append(' ');
			char c = ' ';
			if (i == path.length() - 1)
				c = '+';
			else if (path.charAt(i) != path.charAt(i + 1))
				c = '|';
			buf.append(c);
		}
		buf.append("-- " + x.cle.toString());
		if (x.gauche != null || x.droit != null) {
			buf.append(" --");
			for (int j = x.cle.toString().length(); j < len; j++)
				buf.append('-');
			buf.append('|');
		}
		buf.append("\n");
		toString(x.gauche, buf, path + "G", len);
	}

	private int maxStrLen(Noeud x) {
		return x == null ? 0 : Math.max(x.cle.toString().length(),
				Math.max(maxStrLen(x.gauche), maxStrLen(x.droit)));
	}

	// TODO : voir quelles autres méthodes il faut surcharger
}
