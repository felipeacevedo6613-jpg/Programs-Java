// Fait par: Paula Rosero, Felipe Acevedo.
// date: 10/04/2024

import java.io.Serializable;

public class Vol implements Serializable, Comparable<Vol> {

  private static final long serialVersionUID = 2040890116313738088L;

  private int numVol;
  private String destination;
  private Date date;
  private int nombre_total_reservations;

  public static int  nombreVols = 0;

  public Vol() {
    ++nombreVols;
  }

  public Vol(int numVol, String destination, int jour,int mois, int an, int nombre_total_reservations) {
    this.numVol = numVol;
    this.destination = destination;
    this.date = new Date(jour, mois, an);
    this.nombre_total_reservations = nombre_total_reservations;
    ++nombreVols;
  }


  public void setNombre_total_reservations(int nombre_total_reservations) {
    this.nombre_total_reservations = nombre_total_reservations;
  }

  public void setDate(Date date) {
    this.date = date;
  }

	public void setNumVol(int numVol) {
		if(numVol > 0) {
			this.numVol = numVol;
		} else {
			System.out.println("Numéro de vol invalide !");
		}
	}

  public String getDestination() {
    return destination;
  }

  public Date getDate() {
    return date;
  }

  public int getNombre_total_reservations() {
    return nombre_total_reservations;
  }

  public int getNumVol() {
    return numVol;
  }

  public int compareTo(Vol unVol){
    //Par num livre
    //return (int) (this.num - unLivre.num);//En ordre croissant
    //return (int) (unLivre.num - this.num);//En ordre décroissant
    //Par titre
    return Integer.compare(this.numVol, unVol.numVol);
  }

  public boolean equals(Object obj) {
		Vol autreVol = (Vol) obj;
		if (this.numVol == autreVol.numVol) {
			return true;
		} else {
			return false;
		}
	}


    boolean repas, rvsiege, bar, divert, payants, wifi;
    public String toString() {
      String rep = (this.destination.length() >15) ? this.numVol+"\t"+this.destination+"\t"+this.date+"\t"+nombre_total_reservations+"\t": 
      this.numVol+"\t"+this.destination+"\t\t"+this.date+"\t"+nombre_total_reservations+"\t";
      return rep;
  	}

}
