public class VolPrive extends Vol{
  private int IdAvion;
  private boolean repas, rvsiege, bar, divert, payants, wifi;

  public VolPrive(){
    super();
  }

  public VolPrive(int numVol,String destination, int jour, int mois, int an, int nombre_total_reservations, int IdAvion, boolean repas, boolean rvsiege, boolean bar, boolean divert, boolean payants, boolean wifi){
    super(numVol, destination, jour, mois, an, nombre_total_reservations);
    this.IdAvion = IdAvion;
    this.repas = repas;
    this.rvsiege = rvsiege;
    this.bar = bar;
    this.divert = divert;
    this.payants = payants;
    this.wifi = wifi;
  }

  public int getIdAvion(){
    return this.IdAvion;
  }

  public boolean getRepas(){
    return this.repas;
  }

  public boolean getRvsiege(){
    return this.rvsiege;
  }

  public boolean getBar(){
    return this.bar;
  }

  public boolean getDivert(){
    return this.divert;
  }

  public boolean getPayants(){
    return this.payants;
  }

  public boolean getWifi(){
    return this.wifi;
  }

  public void setIdAvion(int IdAvion){
    this.IdAvion = IdAvion;
  }

  public void setRepas(boolean repas){
    this.repas = repas;
  }

  public void setRvsiege(boolean rvsiege){
    this.rvsiege = rvsiege;
  }

  public void setBar(boolean bar){
    this.bar = bar;
  }

  public void setDivert(boolean divert){
    this.divert = divert;
  }

  public void setPayants(boolean payants){
    this.payants = payants;
  }

  public void setWifi(boolean wifi){
    this.wifi = wifi;
  }

  public String toString(){
    String rep = super.toString();
    rep += this.IdAvion + "\t";
    rep += this.repas?"Oui\t":"Non\t";
    rep += this.rvsiege?"Oui\t":"Non\t";
    rep += this.bar ? "Oui\t" : "Non\t";
    rep += this.divert?"Oui\t":"Non\t";
    rep += this.payants?"Oui\t":"Non\t";
    rep += this.wifi?"Oui\t":"Non\n";
    // rep += this.IdAvion + "\t" + this.repas + "\t" + this.rvsiege + "\t" + this.bar + "\t" + this.divert + "\t" + this.payants + "\t" + this.wifi + "\n";
   return rep;
 }
}

