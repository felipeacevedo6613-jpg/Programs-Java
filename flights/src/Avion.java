class Avion{
  protected static int IdAvion;
  private int nbPlaces;
  private String categorie;
  private String raction;

  public Avion(int IdAvion, int nbPlaces, String categorie, String raction){
    this.IdAvion = IdAvion;
    this.nbPlaces = nbPlaces;
    this.categorie = categorie;
    this.raction = raction;
  }

  public int getIdAvion(){
    return this.IdAvion;
  }

  public int getNbPlaces(){
    return this.nbPlaces;
  }

  public String getCategorie(){
    return this.categorie;
  }

  public String getRaction(){
    return this.raction;
  }

  public void setIdAvion(int IdAvion){
    this.IdAvion = IdAvion;
  }

  public void setNbPlaces(int nbPlaces){
    this.nbPlaces = nbPlaces;
  }

  public void setCategorie(String categorie){
    this.categorie = categorie;
  }

  public void setRaction(String raction){
    this.raction = raction;
  }

  public String toString(){
    return "IdAvion: " + this.IdAvion + ", nbPlaces: " + this.nbPlaces + ", categorie: " + this.categorie + ", raction: " + this.raction;
  }
}
