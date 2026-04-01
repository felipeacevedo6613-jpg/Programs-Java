import java.awt.Font;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class GestionLivres {
	static final String FICHIER_LIVRES_TEXTE = "src/donnees/livres.txt";
	static final String FICHIER_LIVRES_OBJ = "src/donnees/livres.obj";
	static Map<Integer, Livre> biblio;
	static List<Livre> listeLivres;
	static BufferedReader tmpLivresRead;
	static BufferedWriter tmpLivresWrite;
	static ObjectInputStream tmpLivresReadObj;
	static ObjectOutputStream tmpLivresWriteObj;
	static JTextArea sortie;
    static int nbLivres;

	public static int menuGeneral() {
		String contenu = "1-Lister\n2-Ajouter un livre\n3-Enlever un livre\n4-Lister par catégorie\n5-Modifier un livre\n6-Terminer\n\n";
		contenu += "Entrez votre choix parmis[1-6] : ";
		return Integer
				.parseInt(JOptionPane.showInputDialog(null, contenu, "MENU GESTION LIVRES", JOptionPane.PLAIN_MESSAGE));
	}

	public static int menuChoixListe() {
		String contenu = "1-Tous\n2-Anciens\n3-Électroniques\n4-Terminer\n\n";
		contenu += "Entrez votre choix parmis[1-4] : ";
		return Integer
				.parseInt(JOptionPane.showInputDialog(null, contenu, "MENU CHOIX DE LIVRES", JOptionPane.PLAIN_MESSAGE));
	}

	public static int menuChoixLivreAjouter() {
		String contenu = "1-Ancien\n2-Électronique\n3-Terminer\n\n";
		contenu += "Entrez votre choix parmis[1-3] : ";
		return Integer
				.parseInt(JOptionPane.showInputDialog(null, contenu, "MENU CHOIX DE LIVRES", JOptionPane.PLAIN_MESSAGE));
	}

	public static void chargerLivresObj() throws Exception {
		try {
			tmpLivresReadObj = new ObjectInputStream (new FileInputStream (FICHIER_LIVRES_OBJ));
			biblio = (HashMap<Integer, Livre>) tmpLivresReadObj.readObject();
		}catch(FileNotFoundException e)
		{
			System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
		}catch(IOException e)
		{
			System.out.println("Un probléme est arrivé lors de la manipulation du fichier. V�rifiez vos donn�es.");
		}catch(Exception e)
		{
			System.out.println("Un probléme est arrivé lors du chargement du fichier. Contactez l'administrateur.");
		}finally
		{// Exécuté si erreur ou pas
			tmpLivresReadObj.close();
		}
	}

	public static void chargerLivresTexte() throws Exception {
		try {
			String ligne;
			int numLivre;
			String elems[] = new String[7];
			biblio = new HashMap<Integer, Livre>();
			tmpLivresRead = new BufferedReader(new FileReader(FICHIER_LIVRES_TEXTE));
			ligne = tmpLivresRead.readLine();// Lire la premiére ligne du fichier
			while (ligne != null) {// Si ligne == null alors ont a atteint la fin du fichier
				elems = ligne.split(";");// elems[0] contient le num�ro du livre;elems[1] le titre et elems[2] les pages
				// A;200;Un excellent livre;850;5;1640;Potable
				numLivre = Integer.parseInt(elems[1]);
				if(elems[0].equalsIgnoreCase("A")){
					biblio.put(numLivre, new LivreAncien(numLivre, elems[2], Integer.parseInt(elems[3]),
							Integer.parseInt(elems[4]), Integer.parseInt(elems[5]), elems[6]));
				} else if(elems[0].equalsIgnoreCase("E")){
					// E;888;UN LIVRE;300;0;true;true
					biblio.put(numLivre, new LivreElectronique(numLivre, elems[2], Integer.parseInt(elems[3]),
							Integer.parseInt(elems[4]), Boolean.parseBoolean(elems[5]), 
							Boolean.parseBoolean(elems[6])));
				}
				ligne = tmpLivresRead.readLine();
			} // fin while

		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
		} catch (IOException e) {
			System.out.println("Un probléme est arrivé lors de la manipulation du fichier. Vérifiez vos donn�es.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Un probléme est arrivé lors du chargement du fichier. Contactez l'administrateur.");
		} finally {// Exécuté si erreur ou pas
			tmpLivresRead.close();
		}
	}

	public static void afficherEntete(String suiteEntete) {
		sortie.setFont(new Font("monospace", Font.PLAIN, 12));
		sortie.append("\n\n\t\t\tLISTE DES LIVRES\n\n");
		sortie.append("NUMÉRO\tTITRE\t\tPAGES\tCATÉGORIE\t");
		sortie.append(suiteEntete);
	}
 	
	public static void listerAnciens() {
		nbLivres=0;
		Livre unLivre;
		afficherEntete("ANNÉE\tÉTAT\n\n");
		for (Integer numLivre : biblio.keySet()) {
			unLivre = biblio.get(numLivre);
			LivreAncien unLivreAncien;
			if (unLivre instanceof LivreAncien) {
				unLivreAncien = (LivreAncien) unLivre; // unLivre a une représentation Object (Livre)
				sortie.append(unLivreAncien.toString());
				nbLivres++;
			}
		}
		sortie.append("Nombre de livres = " + nbLivres);
	}

	public static void listerElectroniques() {
		nbLivres=0;
		Livre unLivre;
		afficherEntete("TÉLÉCHARGEABLE\tAUDIO\n\n");
		for (Integer numLivre : biblio.keySet()) {
			unLivre = biblio.get(numLivre);
			LivreElectronique unLivreElectronique;
			if (unLivre instanceof LivreElectronique) {
				unLivreElectronique = (LivreElectronique) unLivre;
				sortie.append(unLivreElectronique.toString());
				nbLivres++;
			}
		};
		sortie.append("Nombre de livres = " + nbLivres);
	}

	public static void listerTout() {
		listerAnciens();
		listerElectroniques();
	}
 

	public static void afficherMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "MESSAGES", JOptionPane.PLAIN_MESSAGE);
	}

	public static int obtenirNumeroLivreValide(){
		boolean numLivreExiste;
		int num;
		do {
			num = Integer
					.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro", "AJOUTER UN LIVRE",
							JOptionPane.PLAIN_MESSAGE));
			numLivreExiste = biblio.containsKey(num);
			if (numLivreExiste) {
				afficherMessage("Le livre de numéro " + num + " existe déjà !");

			}
		} while (numLivreExiste);
		return num;
	}

	public static void ajouterUnLivreAncien() {
		
		int num = obtenirNumeroLivreValide();

		String titre = JOptionPane.showInputDialog(null, "Entrez le titre", "AJOUTER UN LIVRE",
				JOptionPane.PLAIN_MESSAGE);
		int pages = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez le nombre de pages", "AJOUTER UN LIVRE",
						JOptionPane.PLAIN_MESSAGE));
		int categorie = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez la catégorie", "AJOUTER UN LIVRE",
						JOptionPane.PLAIN_MESSAGE));
		int annee = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez l'année'", "AJOUTER UN LIVRE",
						JOptionPane.PLAIN_MESSAGE));
		String etat = JOptionPane.showInputDialog(null, "Entrez l'état'", "AJOUTER UN LIVRE",
				JOptionPane.PLAIN_MESSAGE);
		biblio.put(num, new LivreAncien(num, titre, pages, categorie, annee, etat));
	}

	public static void ajouterUnLivreElectronique() {
		
		int num = obtenirNumeroLivreValide();
		
		String titre = JOptionPane.showInputDialog(null, "Entrez le titre", "AJOUTER UN LIVRE",
				JOptionPane.PLAIN_MESSAGE);
		int pages = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez le nombre de pages", "AJOUTER UN LIVRE",
						JOptionPane.PLAIN_MESSAGE));
		int categorie = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez la catégorie", "AJOUTER UN LIVRE",
						JOptionPane.PLAIN_MESSAGE));
		String choix = JOptionPane.showInputDialog(null, "Téléchargeable ?  O-Oui N-Non", "AJOUTER UN LIVRE",
				JOptionPane.PLAIN_MESSAGE);
		choix = (choix.equalsIgnoreCase("O"))?"true":"false";
		boolean telechargeable = Boolean.parseBoolean(choix);
		choix = JOptionPane.showInputDialog(null, "Audio ?  O-Oui N-Non", "AJOUTER UN LIVRE",
				JOptionPane.PLAIN_MESSAGE);
		choix = (choix.equalsIgnoreCase("O"))?"true":"false";
		boolean audio = Boolean.parseBoolean(choix);
		biblio.put(num, new LivreElectronique(num, titre, pages, categorie, telechargeable, audio));
	}

	public static void enleverUnLivre() {
		Livre livreEfface;
		do {
			int num = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro", "ENLEVER UN LIVRE",
						JOptionPane.PLAIN_MESSAGE));
			livreEfface = biblio.remove(num);
			if (livreEfface == null) {
					afficherMessage("Le livre de numéro " + num + " n'existe pas !");
			}
		}while(livreEfface == null);
	}


	public static void listerLivresParCategorie() {
		sortie = new JTextArea(20, 70);
		int categorie = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez la catégorie", "LISTER DES LIVRES PAR CATÉGORIE",
						JOptionPane.PLAIN_MESSAGE));
		afficherEntete("\n\n");
		 int cptCategs = 0;
		for (Livre unLivre : biblio.values()) {
			if (unLivre.getCategorie() == categorie) {
				sortie.append(unLivre.toString());
				++cptCategs;
			}
		}
		sortie.append("Nombre de livres = " + cptCategs);
		JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE);
	}

	public static Livre rechercherLivre(int num) {
		return biblio.get(num);
	}

	public static int menuModifier(){
		String contenu = "1-Modifier le titre\n2-Modifier le nombre de pages\n3-Modifier la catégorie\n4-Terminer\n\n";
		contenu += "Entrez votre choix parmis[1-4] : ";
		return Integer
				.parseInt(JOptionPane.showInputDialog(null, contenu, "MENU CHOIX DE LIVRES", JOptionPane.PLAIN_MESSAGE));
	}

	public static void modifierUnLivre() {
		int choix;
		int num = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro", "MODIFIER UN LIVRE",
						JOptionPane.PLAIN_MESSAGE));
		Livre livreTrouve = rechercherLivre(num);
		if (livreTrouve == null) {
			afficherMessage("Le livre de numéro " + num + " n'existe pas !");
			return;
		}
		do {

			choix = 1; 
			switch (choix) {
				case 1:
					String titre = JOptionPane.showInputDialog(null, "Entrez le nouveau titre", "AJOUTER UN LIVRE",
							JOptionPane.PLAIN_MESSAGE);
					livreTrouve.setTitre(titre);
					break;
				case 2:
					int pages = Integer
							.parseInt(JOptionPane.showInputDialog(null, "Entrez le nouveau nombre de pages",
									"MODIFIER UN LIVRE", JOptionPane.PLAIN_MESSAGE));
					livreTrouve.setPages(pages);
					break;
				case 3:
					int categorie = Integer
							.parseInt(JOptionPane.showInputDialog(null, "Entrez la nouvelle catégorie",
									"MODIFIER UN LIVRE", JOptionPane.PLAIN_MESSAGE));
					livreTrouve.setCategorie(categorie);
					break;
				case 4:
					break;
				default:
					afficherMessage("Choix invalide. Les option sont [1-4] !");
					break;

			}
		} while (choix != 4);
	}

	static String ligne = "";

	public static void sauvegarderLivres() throws IOException {
		try {
			tmpLivresWriteObj = new ObjectOutputStream(new FileOutputStream(FICHIER_LIVRES_OBJ));
			tmpLivresWriteObj.writeObject(biblio);
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
		} catch (IOException e) {
			System.out.println("Un probléme est arrivé lors de la manipulation du fichier. V�rifiez vos donn�es.");
		} catch (Exception e) {
			System.out.println("Un probléme est arrivé lors du chargement du fichier. Contactez l'administrateur.");
		} finally {// Exécuté si erreur ou pas
			tmpLivresWriteObj.close();
		}
	}

	
	public static void trier() {
		// TreeMap<Integer, Livre> biblioTriee = new TreeMap<Integer, Livre>();
		// biblioTriee.putAll(biblio);
		// biblio = biblioTriee;

		listeLivres = new ArrayList<Livre>(biblio.values());
		Collections.sort(listeLivres);
		// for (Livre unLivre : listeLivres) {
		//     System.out.println(unLivre);
		// }
		
	}

	public static void main(String[] args) throws Exception {
		int choix, choixListe, choixLivre;
		File f = new File(FICHIER_LIVRES_OBJ);
		if (f.exists()) {
			chargerLivresObj();
		}else {
			chargerLivresTexte();
		}
		System.out.println("Avant le tri");
		for (Livre unLivre : biblio.values()) {
			System.out.println(unLivre);
		}
		trier();
		System.out.println("Après le tri");
		for (Livre unLivre : listeLivres) {
			System.out.println(unLivre);
		}

		// do {
		// 	choix = menuGeneral();
		// 	switch (choix) {
		// 		case 1:
		// 			do {
		// 					choixListe = menuChoixListe();
		// 					switch(choixListe){
		// 						case 1 : 
		// 							sortie = new JTextArea(20,70);
		// 							listerTout();
		// 							JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE);
		// 						break;
		// 						case 2 :
		// 							sortie = new JTextArea(20, 70); 
		// 							listerAnciens();
		// 							JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE);

		// 						break;
		// 						case 3 : 
		// 							sortie = new JTextArea(20, 70);
		// 							listerElectroniques();
		// 							JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE);
		// 						break;
		// 						case 4:
		// 						break;
		// 						default : afficherMessage("Choix invalide. Les option sont [1-4] !");
		// 						break;
		// 					}
		// 				} while (choixListe != 4);
					
		// 			break;
		// 		case 2:
		// 			do {
		// 					choixLivre = menuChoixLivreAjouter();
		// 					switch(choixLivre){
		// 						case 1 : 
		// 							ajouterUnLivreAncien();
		// 						break;
		// 						case 2 : 
		// 							ajouterUnLivreElectronique();
		// 						break;
		// 						case 3 :
		// 						break;
		// 						default:
		// 							afficherMessage("Choix invalide. Les option sont [1-3] !");
		// 						break;
		// 				}
		// 			} while (choixLivre != 3);
		// 			break;
		// 		case 3:
		// 			enleverUnLivre();
		// 			break;
		// 		case 4:
		// 			listerLivresParCategorie();
		// 			break;
		// 		case 5:
		// 			modifierUnLivre();
		// 			break;
		// 		case 6:
		// 			sauvegarderLivres();
		// 			afficherMessage("Merci d'avoir utilisé notre système");
		// 			break;
		// 		default:
		// 			afficherMessage("Choix invalide. Les option sont [1-6] !");
		// 			break;
		// 	}
		// } while (choix != 6);
	}
}
