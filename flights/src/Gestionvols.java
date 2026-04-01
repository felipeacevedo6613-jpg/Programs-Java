// Fait par: Paula Rosero, Felipe Acevedo.
// date: 10/04/2024
import java.awt.Font;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Gestionvols {
  static BufferedReader bufLire;
  static BufferedWriter bufEcrire;
  private static boolean donneesChangees = false;
  static JTextArea sortie = new JTextArea();
  static final String FICHIER_VOLS_OBJ_CIEAIRRELAX = "src/Donnees/CieAirRelax.obj";
  static Map<Integer, Vol> ListeMapVols = new HashMap<Integer, Vol>();
  static final int MAX_PLACES = 340;

  public static int menu(){
    int choix;
    String menu =   "1. Liste des vols \n" +
                    "2. Ajout de un vol\n" +
                    "3. Retrait d'un vol \n" +
                    "4. Modification de la date de départ\n" +
                    "5. Reservation d'un vol \n" +
                    "0. Terminer\n\n" +
                    "Faites votre choix : ";
    choix = Integer.parseInt(JOptionPane.showInputDialog(null, menu, "GESTION VOLS", JOptionPane.PLAIN_MESSAGE));
    return choix;
  }

  public static int menuListerVols() {
		String contenu = "1-Tous\n2-Vols a Bas Prix\n3-Vols Reguliers\n4-Vols Prives\n5-Vols Charter\n6-Terminer\n\n";
		contenu += "Entrez votre choix parmis[1-6] : ";
		return Integer.parseInt(JOptionPane.showInputDialog(null, contenu, "MENU CHOIX DE VOLS", JOptionPane.PLAIN_MESSAGE));
	}

  public static int menuAjouter() {
		String contenu = "Quel type de vol voulez vous ajouter?\n1-Bas Prix\n2-Regulier\n3-Prive\n4-Charter\n5-Terminer\n\n";
		contenu += "Entrez votre choix parmis[1-5] : ";
		return Integer.parseInt(JOptionPane.showInputDialog(null, contenu, "MENU AJOUTER UN VOL", JOptionPane.PLAIN_MESSAGE));
	}

  public static void afficherEntete(String suiteEntete) {
		sortie.setFont(new Font("monospace", Font.PLAIN, 12));
		sortie.append("\n\n\t\t\t\t\tLISTE DES VOLS\n\n");
		sortie.append("NUMÉRO\tDESTINATION\t\tDATE\tNo RES\tIDAVION\t");
		sortie.append(suiteEntete);
	}

  public static void listerVolBasPrix() {
    int nbVols=0;
    Vol unVol;
    afficherEntete("REPAS\tR_SIEGE\tDIVER..\tBAR\t S_PAYANT \tWIFI \n\n\n");
    for (Integer numVol : ListeMapVols.keySet()) {
        unVol = ListeMapVols.get(numVol);
        VolBasPrix unVolBasPrix;
        if (unVol instanceof VolBasPrix) {
            unVolBasPrix = (VolBasPrix) unVol;
            sortie.append(unVolBasPrix.toString());
            nbVols++;
        }
    }
    sortie.append("\nNombre de Vols a Bas Prix = " + nbVols);
  }

  public static void listerVolRegulier() {
		int nbVols=0;
		Vol unVol;
		afficherEntete("REPAS\tR_SIEGE\tDIVER..\tBAR\t S_PAYANT \tWIFI \n\n\n");
		for (Integer numVol : ListeMapVols.keySet()) {
			unVol = ListeMapVols.get(numVol);
			VolRegulier unVolRegulier;
			if (unVol instanceof VolRegulier) {
				unVolRegulier = (VolRegulier) unVol;
				sortie.append(unVolRegulier.toString());
				nbVols++;
			}
		}
		sortie.append("\nNombre de Vols Reguliers= " + nbVols);
	}
  public static void listerVolPrive() {
		int nbVols=0;
		Vol unVol;
		afficherEntete("REPAS\tR_SIEGE\tDIVER..\tBAR\t S_PAYANT \tWIFI \n\n\n");
		for (Integer numVol : ListeMapVols.keySet()) {
			unVol = ListeMapVols.get(numVol);
			VolPrive unVolPrive;
			if (unVol instanceof VolPrive) {
				unVolPrive = (VolPrive) unVol;
				sortie.append(unVolPrive.toString());
				nbVols++;
			}
		}
		sortie.append("\nNombre de Vols Prives = " + nbVols);
	}
  public static void listerVolCharter() {
		int nbVols=0;
		Vol unVol;
		afficherEntete("REPAS\tR_SIEGE\tDIVER..\tBAR\t S_PAYANT \tWIFI \n\n\n");
		for (Integer numVol : ListeMapVols.keySet()) {
			unVol = ListeMapVols.get(numVol);
			VolCharter unVolCharter;
			if (unVol instanceof VolCharter) {
				unVolCharter = (VolCharter) unVol;
				sortie.append(unVolCharter.toString());
				nbVols++;
			}
		}
		sortie.append("\nNombre de Vols Charters = " + nbVols);
	}

	public static void listerTout() {
		listerVolBasPrix();
    listerVolRegulier();
    listerVolPrive();
    listerVolCharter();
	}

  public static void lister() {
    int choix;
    do {
      choix = menuListerVols();
      switch (choix) {
        case 1:
          sortie.setText("");
          listerTout();
          JOptionPane.showMessageDialog(null, sortie, "LISTE DES VOLS", JOptionPane.PLAIN_MESSAGE);
        break;
        case 2:
          sortie.setText("");
          listerVolBasPrix();
          JOptionPane.showMessageDialog(null, sortie, "LISTE DES VOLS", JOptionPane.PLAIN_MESSAGE);
        break;
        case 3:
          sortie.setText("");
          listerVolRegulier();
          JOptionPane.showMessageDialog(null, sortie, "LISTE DES VOLS", JOptionPane.PLAIN_MESSAGE);
        break;
        case 4:
          sortie.setText("");
          listerVolRegulier();
          JOptionPane.showMessageDialog(null, sortie, "LISTE DES VOLS", JOptionPane.PLAIN_MESSAGE);
        break;
        case 5:
          sortie.setText("");
          listerVolCharter();
          JOptionPane.showMessageDialog(null, sortie, "LISTE DES VOLS", JOptionPane.PLAIN_MESSAGE);
        break;
        case 6:
        break;
        default:
        JOptionPane.showMessageDialog(null, "Choix invalide", "MESSAGES", JOptionPane.PLAIN_MESSAGE);
      } 
    } while (choix != 6);
  }


  public static Vol rechercherVol(int num) {
		return ListeMapVols.get(num);
	}

  public static int obtenirNumeroVolValide(){
		boolean numVolExiste;
		int num;
		do {
			num = Integer
					.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro", "AJOUTER UN VOL",
							JOptionPane.PLAIN_MESSAGE));
			numVolExiste = ListeMapVols.containsKey(num);
			if (numVolExiste) {
      JOptionPane.showMessageDialog(null, "Le numéro"+ num+ "existe déjà", "MESSAGES", JOptionPane.PLAIN_MESSAGE);
			}
		} while (numVolExiste);
		return num;
	}
  public static void insererVol() {
    int jour, mois, an, nombre_total_reservations, choix=0, id, option;
    int numVol;
    String destination;
    do {
      option = menuAjouter();
      if (option > 5) {
          JOptionPane.showMessageDialog(null, "Choix invalide", "MESSAGES", JOptionPane.PLAIN_MESSAGE);
          continue;
      }
      if(option == 5) {
          break;
      }
      numVol = obtenirNumeroVolValide();        
      destination = JOptionPane.showInputDialog(null, "Destination du Vol :", "AJOUT D'UN VOL", JOptionPane.PLAIN_MESSAGE);
      jour = Integer.parseInt(JOptionPane.showInputDialog(null, "Le jour :", "AJOUT D'UN VOL", JOptionPane.PLAIN_MESSAGE));
      mois = Integer.parseInt(JOptionPane.showInputDialog(null, "Le mois :", "AJOUT D'UN VOL", JOptionPane.PLAIN_MESSAGE));
      an = Integer.parseInt(JOptionPane.showInputDialog(null, "L'an :", "AJOUT D'UN VOL", JOptionPane.PLAIN_MESSAGE));
      nombre_total_reservations = Integer.parseInt(JOptionPane.showInputDialog(null, "nombre de reservations :", "AJOUT D'UN VOL", JOptionPane.PLAIN_MESSAGE));
      id = Integer.parseInt(JOptionPane.showInputDialog(null, "ID de l'avion :", "AJOUT D'UN VOL", JOptionPane.PLAIN_MESSAGE));
      switch (option) {
        case 1:
          ListeMapVols.put(numVol, new VolBasPrix(numVol, destination, jour, mois, an, nombre_total_reservations, id, false, false, false, false, false, false));
          break;
        case 2:
          ListeMapVols.put(numVol, new VolRegulier(numVol, destination, jour, mois, an, nombre_total_reservations, id, true, false, false, false, false, true));
          break;
        case 3:
          ListeMapVols.put(numVol, new VolPrive(numVol, destination, jour, mois, an, nombre_total_reservations, id, true, true, true, true, true, true));
          break;
        case 4:
          ListeMapVols.put(numVol, new VolCharter(numVol, destination, jour, mois, an, nombre_total_reservations, id, true, false, false, false, false, true));
          break;
      default:
      JOptionPane.showMessageDialog(null, "Choix invalide", "MESSAGES", JOptionPane.PLAIN_MESSAGE);
      }
      JOptionPane.showMessageDialog(null, "Le vol a été ajouté avec succès.", "AJOUT D'UN VOL", JOptionPane.INFORMATION_MESSAGE);
      donneesChangees = true;
      choix = JOptionPane.showConfirmDialog(null, "Voulez-vous ajouter un autre vol? ", "CONFIRMATION", JOptionPane.YES_NO_OPTION);
    } while (choix == JOptionPane.YES_OPTION);
    if(option == 5) {
      menu();
    }
  }

  public static void modifierDate() {
    int numVol, jour, mois, an, choix;

    do {
      numVol = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro du Vol:",
      "MODIFICATION DATE DE DÉPART", JOptionPane.PLAIN_MESSAGE));
      Vol pos= rechercherVol(numVol);

      if (pos == null) {
        JOptionPane.showMessageDialog(null, "Numero de Vol " + pos + "n'exist pas", "ERREUR", JOptionPane.ERROR_MESSAGE);
        return;
      }
      Vol volAModifier = ListeMapVols.get(numVol);
      String messageM = "Numero du Vol: " + volAModifier.getNumVol() + "\n" +
              "Destination: " + volAModifier.getDestination() + "\n" +
              "Date: " + volAModifier.getDate() + "\n" +
              "Désirez-vous vraiment modifier la date de départ de ce vol?";
              choix = JOptionPane.showConfirmDialog(null, messageM, "Confirmation", JOptionPane.YES_NO_OPTION);
      if (choix == JOptionPane.YES_OPTION) {
        jour = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le jour :",
        "MODIFICATION DATE DE DÉPART)", JOptionPane.PLAIN_MESSAGE));
        mois = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le mois :",
        "MODIFICATION DATE DE DÉPART)", JOptionPane.PLAIN_MESSAGE));
        an = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez l' an :",
        "MODIFICATION DATE DE DÉPART)", JOptionPane.PLAIN_MESSAGE));
        Date date= new Date(jour, mois ,an);
        ListeMapVols.get(numVol).setDate(date);
        donneesChangees = true;
        JOptionPane.showMessageDialog(null, "La date a été modifie avec succès.", "MODIFICATION DATE DÉPART",
        JOptionPane.INFORMATION_MESSAGE);
      }

      choix = JOptionPane.showConfirmDialog(null, "Voulez-vous modifier une autre date? ", "CONFIRMATION",
      JOptionPane.YES_NO_OPTION);
      
    } while (choix == JOptionPane.YES_OPTION);
  }

  public static void supprimerVol() {
    int choix;
    do {
        int numVol = Integer.parseInt(JOptionPane.showInputDialog("Entrez le numéro du Vol a supprimer: "));
        Vol volARemover = ListeMapVols.get(numVol);
        if (volARemover == null) {
          JOptionPane.showMessageDialog(null, "Numero de Vol " + volARemover + "n'exist pas", "ERREUR", JOptionPane.ERROR_MESSAGE);
          return;
        }
        String messageM = "Numero du Vol: " + volARemover.getNumVol() + "\n" +
        "Destination: " + volARemover.getDestination() + "\n" +
        "Date: " + volARemover.getDate() + "\n" +
        "Désirez-vous vraiment effacer ce vol?";
        choix = JOptionPane.showConfirmDialog(null, messageM, "Confirmation", JOptionPane.YES_NO_OPTION);
        if (choix == JOptionPane.YES_OPTION) {
            ListeMapVols.remove(numVol);
            JOptionPane.showMessageDialog(null, "Le vol a été supprimé avec succès.", "Retrait de Vol", JOptionPane.INFORMATION_MESSAGE);
            donneesChangees = true;
            Vol.nombreVols--;
        }
        choix = JOptionPane.showConfirmDialog(null, "Voulez-vous effacer un autre Vol? ", "CONFIRMATION",
        JOptionPane.YES_NO_OPTION);
    } while (choix == JOptionPane.YES_OPTION);
}

  public static void reserverVol () {
    int numVol, choix, nombrePlaces = 0 ;
    do {
      numVol = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro du Vol:",
      "RESERVATIÓN D'UN VOL", JOptionPane.PLAIN_MESSAGE));
      Vol pos= rechercherVol(numVol);
      if (pos == null) {
        JOptionPane.showMessageDialog(null, "Numero de Vol " + pos + "n'exist pas", "ERREUR", JOptionPane.ERROR_MESSAGE);
        return;
      }
      Vol volAModifier = ListeMapVols.get(numVol);
      String messageM = "Numero du Vol: " + volAModifier.getNumVol() + "\n" +
              "Destination: " + volAModifier.getDestination() + "\n" +
              "Date: " + volAModifier.getDate() + "\n" +
              "Désirez-vous vraiment faire une reservation pour ce ce vol?";
              choix = JOptionPane.showConfirmDialog(null, messageM, "Confirmation", JOptionPane.YES_NO_OPTION);
      nombrePlaces = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le nombre de places à reserver  :",
      "RÉSERVATION D'UN VOL", JOptionPane.PLAIN_MESSAGE));
      if (ListeMapVols.get(numVol).getNombre_total_reservations() + nombrePlaces > MAX_PLACES ){
        JOptionPane.showMessageDialog(null, "Il n'a pas assez des places, il reste juste " + (MAX_PLACES - ListeMapVols.get(numVol).getNombre_total_reservations()), "ERREUR", JOptionPane.ERROR_MESSAGE);
      } else{
        ListeMapVols.get(numVol).setNombre_total_reservations(ListeMapVols.get(numVol).getNombre_total_reservations() + nombrePlaces);
        donneesChangees = true;
        JOptionPane.showMessageDialog(null, "La reservation a ete fait avec success", "MODIFICATION DATE DÉPART",
        JOptionPane.INFORMATION_MESSAGE);
      }
      choix = JOptionPane.showConfirmDialog(null, "Voulez-vous faire une autre reservation? ", "CONFIRMATION",
      JOptionPane.YES_NO_OPTION);
    } while (choix == JOptionPane.YES_OPTION);
  }


  public static void main(String[] args) throws Exception {
    String nomCompagnie = JOptionPane.showInputDialog(null, "Entrez le nom de la compagnie", "AJOUTER UNE COMPAGNIE",
    JOptionPane.PLAIN_MESSAGE);
    Compagnie cp = new Compagnie(nomCompagnie);
    File f = new File(FICHIER_VOLS_OBJ_CIEAIRRELAX);
    if (f.exists()) {
      cp.chargerVolsObj();
    }else {
      cp.chargerVolsTexte();
    }
    ListeMapVols = cp.getListeMapVols();
    for (Vol unVol : ListeMapVols.values()) {
      System.out.println(unVol);
    }
    int choix;
    boolean continuer = true;
    do {
      choix = menu();
      switch (choix) {
        case 1:
          lister();
          break;
        case 2:
          insererVol();
          break;
        case 3:
        // int numVol = 0;
        // numVol = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro du Vol a supprimer: ",
        // "RETRAITE D'UN VOL", JOptionPane.PLAIN_MESSAGE));
        // supprimerVol(numVol);
        supprimerVol();
          break;
        case 4:
          modifierDate();
          break;
        case 5:
        reserverVol ();
          break;
        case 0:
          if (donneesChangees = true) {
            cp.sauvegarderVols();
          }
          JOptionPane.showMessageDialog(null, "Merci d'avoir utilisé notre logiciel", "FIN DU PROGRAMME",
          JOptionPane.PLAIN_MESSAGE);
          continuer = false;
          break;
        default:
        JOptionPane.showMessageDialog(null, "Choix invalide", "MESSAGES", JOptionPane.PLAIN_MESSAGE);
      }
    } while (continuer);
    System.exit(0);
  }
}
