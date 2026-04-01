public class LivreAncien extends Livre {
    private int annee;
    private String etat;

    // Les constructeurs
    LivreAncien(){ //Par défaut
		super();
	}

    LivreAncien(int num, String titre, int pages, int categorie, int annee, String etat){ //Paramétré
		super(num, titre, pages, categorie);
		this.annee = annee;
		this.etat = etat;
	}

    // Les accesseurs (getter)
    public int getAnnee() {
        return this.annee;
    }

    public String getEtat() {
        return this.etat;
    }

    // Les mutateurs (setter)

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    // Retourner le contenu d'un objet selon un format voulu
    public String toString() {
        String rep = super.toString();
        rep += this.annee+"\t"+this.etat+"\n";
        return rep;
    }
}
