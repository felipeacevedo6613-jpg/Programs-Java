public class LivreElectronique extends Livre {
    // Attributs d'instances
    private boolean telechargeable;
    private boolean audio;
    

    // Les constructeurs
    LivreElectronique(){ //Par défaut
		super();
	}

    LivreElectronique(int num, String titre, int pages, int categorie, boolean telechargeable, boolean audio){ //Paramétré
		super(num, titre, pages, categorie);
		this.telechargeable = telechargeable;
		this.audio = audio;
	}

    // Les accesseurs (getter)
    public boolean getTelechargeable() {
        return this.telechargeable;
    }

    public boolean getAudio() {
        return this.audio;
    }

    
    // Les mutateurs (setter)

    public void setTelechargeable(boolean telechargeable) {
        this.telechargeable = telechargeable;
    }

    public void setAudio(boolean audio) {
        this.audio = audio;
    }

    // Retourner le contenu d'un objet selon un format voulu
    public String toString() {
        String rep = super.toString();        
        rep += this.telechargeable?"Oui\t\t":"Non\t\t";
        rep += this.audio ? "Oui\n" : "Non\n";
        return rep;
    }
}
