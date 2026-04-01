// Fait par: Paula Rosero, Felipe Acevedo et Mozhdeh Rostami.
// date: 22/03/2024
import java.io.Serializable;
public class Date implements Serializable {
  private int jour, mois, an;

  public Date() {
  }

  public Date(int jour, int mois, int an) {
    this.jour = jour;
    this.mois = mois;
    this.an = an;
  }


  public int getJour() {
    return jour;
  }

  public int getMois() {
    return mois;
  }

  public int getAn() {
    return an;
  }

  @Override
  public String toString() {
    return String.format("%02d",jour) + "/" + String.format("%02d",mois) + "/" + an;
  }
}
