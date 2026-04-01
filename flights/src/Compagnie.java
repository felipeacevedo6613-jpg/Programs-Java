// Fait par: Paula Rosero, Felipe Acevedo.
// date: 10/04/2024
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextArea;


public class Compagnie{
  private String nom;
  private BufferedReader tmpVolsRead;
  private BufferedWriter tmpVolsWrite;
  private final String FICHIER_VOLS_TEXTE = "src/Donnees/CieAirRelax.txt";
  static final String FICHIER_VOLS_OBJ = "src/Donnees/CieAirRelax.obj";
  private final String CHEMIN_FICHIER = "src/Donnees/";
  static ObjectInputStream tmpVolsReadObj;
	static ObjectOutputStream tmpVolsWriteObj;
	static JTextArea sortie;
  private String nomFichier;
  File fichier;
  private ArrayList<Avion> flotte = new ArrayList<Avion>();
  static Map<Integer, Vol> ListeMapVols;//= new HashMap<Integer, Vol>();

  public Compagnie() {
    flotte.add(new Avion(100, 200, "Airbus 320", "mc"));
    flotte.add(new Avion(200, 350, "Boeing 747", "lc"));
    flotte.add(new Avion(300, 250, "Airbus 380", "lc"));
    flotte.add(new Avion(400, 100, "Boeing 240", "cc"));
    flotte.add(new Avion(500, 300, "Antonov 180", "mc"));
  }



  Compagnie(String nom) throws Exception {
    this.nom = nom; // Ejemplo: Cie Air Relax
    this.ListeMapVols = new HashMap<Integer, Vol>();
    genererNomFichier();
    fichier = new File(this.nomFichier);
    if (fichier.exists()) {
      chargerVolsObj();
    } else {
      tmpVolsWrite = new BufferedWriter(new FileWriter(this.nomFichier));
      if (this.nomFichier.equals(FICHIER_VOLS_OBJ)) {
        chargerVolsTexte();
      }
    }
  }


  public  Map<Integer, Vol> getListeMapVols() {
    return this.ListeMapVols;
  }

  public void genererNomFichier() {
    nomFichier = this.nom.replace(" ", "_"); // nomFichierTxt = "Cie_Air_Relax"
    this.nomFichier = CHEMIN_FICHIER + nomFichier + ".obj"; // nomFichier = "src/donnees/Cie_Air_Relax.txt"
  }

  public void chargerVolsTexte() throws Exception {
    try {
      String ligne;
      int numVol = 0;
      String composants[] = new String[14];
      ListeMapVols = new HashMap<Integer, Vol>();
      tmpVolsRead = new BufferedReader(new FileReader(FICHIER_VOLS_TEXTE));
      ligne = tmpVolsRead.readLine(); // Lire la première ligne du fichier
      while (ligne != null) { // Si ligne == null alors ont a atteint la fin du fichier
        composants = ligne.split(";");
        numVol = Integer.parseInt(composants[1]);
        if (composants[0].equalsIgnoreCase("R")) {
          ListeMapVols.put(numVol, new VolRegulier(numVol, composants[2],
          Integer.parseInt(composants[3]),Integer.parseInt(composants[4]),
          Integer.parseInt(composants[5]),Integer.parseInt(composants[6]),
          Integer.parseInt(composants[7]),Boolean.parseBoolean(composants[8]),
          Boolean.parseBoolean(composants[9]),Boolean.parseBoolean(composants[10]),
          Boolean.parseBoolean(composants[11]),Boolean.parseBoolean(composants[12]),
          Boolean.parseBoolean(composants[13])));
        } else if (composants[0].equalsIgnoreCase("B")) {
          ListeMapVols.put(numVol, new VolBasPrix(numVol, composants[2],
          Integer.parseInt(composants[3]),Integer.parseInt(composants[4]),
          Integer.parseInt(composants[5]),Integer.parseInt(composants[6]),
          Integer.parseInt(composants[7]),Boolean.parseBoolean(composants[8]),
          Boolean.parseBoolean(composants[9]),Boolean.parseBoolean(composants[10]),
          Boolean.parseBoolean(composants[11]),Boolean.parseBoolean(composants[12]),
          Boolean.parseBoolean(composants[13])));
        } else if (composants[0].equalsIgnoreCase("C")) {
          ListeMapVols.put(numVol, new VolCharter(numVol, composants[2],
          Integer.parseInt(composants[3]),Integer.parseInt(composants[4]),
          Integer.parseInt(composants[5]),Integer.parseInt(composants[6]),
          Integer.parseInt(composants[7]),Boolean.parseBoolean(composants[8]),
          Boolean.parseBoolean(composants[9]),Boolean.parseBoolean(composants[10]),
          Boolean.parseBoolean(composants[11]),Boolean.parseBoolean(composants[12]),
          Boolean.parseBoolean(composants[13])));
        } else if (composants[0].equalsIgnoreCase("P")) {
          ListeMapVols.put(numVol, new VolPrive(numVol, composants[2],
          Integer.parseInt(composants[3]),Integer.parseInt(composants[4]),
          Integer.parseInt(composants[5]),Integer.parseInt(composants[6]),
          Integer.parseInt(composants[7]),Boolean.parseBoolean(composants[8]),
          Boolean.parseBoolean(composants[9]),Boolean.parseBoolean(composants[10]),
          Boolean.parseBoolean(composants[11]),Boolean.parseBoolean(composants[12]),
          Boolean.parseBoolean(composants[13])));
        }
        ligne = tmpVolsRead.readLine();
      }
    } catch (FileNotFoundException e) {
        System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
    } catch (IOException e) {
        System.out.println("Un problème est arrivé lors de la manipulation du fichier. Vérifiez vos données.");
    } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Un problème est arrivé lors du chargement du fichier. Contactez l'administrateur.");
    } finally {
      if (tmpVolsRead != null) {
        tmpVolsRead.close();
      }
    }
  }

  public void chargerVolsObj() {
    ObjectInputStream tmpVolsReadObj = null;
    try {
        tmpVolsReadObj = new ObjectInputStream(new FileInputStream(this.nomFichier));
        ListeMapVols = (HashMap<Integer, Vol>) tmpVolsReadObj.readObject();
    } catch (FileNotFoundException e) {
        System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
    } catch (IOException e) {
        System.out.println("Votre nouvelle entreprise n'a pas de vols.");
    } catch (ClassNotFoundException e) {
        System.out.println("Classe non trouvée lors de la lecture du fichier.");
    } finally {
        if (tmpVolsReadObj != null) {
            try {
                tmpVolsReadObj.close();
            } catch (IOException e) {
                System.out.println("Erreur lors de la fermeture du flux.");
            }
        }
    }
  }

  public void sauvegarderVols() throws IOException {
		try {
			tmpVolsWriteObj = new ObjectOutputStream(new FileOutputStream(this.nomFichier));
			tmpVolsWriteObj.writeObject(ListeMapVols);
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
		} catch (IOException e) {
			System.out.println("Un probléme est arrivé lors de la manipulation du fichier. V�rifiez vos donn�es.");
		} catch (Exception e) {
			System.out.println("Un probléme est arrivé lors du chargement du fichier. Contactez l'administrateur.");
		} finally {// Exécuté si erreur ou pas
			tmpVolsWriteObj.close();
		}
	}
}
