package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	
	private static class Marche {
		private Etal[] etals;
		
		private Marche(int nbEtals) {
			this.etals=new Etal[nbEtals];
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			Etal nv_etal = new Etal();
			nv_etal.occuperEtal(vendeur, produit, nbProduit);
			etals[indiceEtal]=nv_etal;
			}
		
		private int trouverEtalLibre() {
			int numEtal=-1;
			int i=0;
			boolean found=false;
			while (!found && i<etals.length) {
				if (etals[i]==null) {
					found=true;
					numEtal=i;
				}
				i+=1;
			}
			return numEtal;
		}
		
		private Etal[] trouverEtals(String produit) {
			int total=0;
			
			for (int i=0;i<etals.length;i++) {
				if (etals[i]!=null && etals[i].contientProduit(produit)) {
					total+=1;
				}
			}
			
			Etal[] etalProduit= new Etal[total];
			int j=0;
			int i=0;
			while(j<total) {
				if (etals[i]!=null && etals[i].contientProduit(produit)) {
					etalProduit[j]=etals[i];
					j++;
				}
				i++;
			}
			return etalProduit;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			int i=0;
			while (i<etals.length) {
				if (etals[i]!=null && etals[i].getVendeur()==gaulois) {
					return etals[i];
				}
				i++;
			}
			return null;
		}
		
		private String afficherMarche() {
			int i=0;
			int nbEtalVide=0;
			String affichage="";
			while(i<etals.length) {
				if(etals[i]==null) nbEtalVide++;
				else affichage+=etals[i].afficherEtal()+"\n";
				i++;
			}
			if (nbEtalVide==0) affichage+= "Il reste " + nbEtalVide + 
					" étals non utilisés dans le marché.\n";
			return affichage;
		}
		
	}

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		int etalNum = this.marche.trouverEtalLibre();
		if (etalNum==-1)
			return vendeur.getNom() + " ne peut pas s'installer au marché.\n";
		this.marche.utiliserEtal(etalNum, vendeur, produit, nbProduit);
		return vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n"
				+ "Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n°" + (etalNum+1) + ".\n";
	}
	
	public String rechercherVendeursProduit(String produit) {
		Etal[] etal = this.marche.trouverEtals(produit);
		int nbEtals=etal.length;
		String finPhrase = "propose des " + produit + " au marché.\n";
		if(nbEtals==0)
			return "Il n'y a pas de vendeur qui " + finPhrase;
		else if(nbEtals==1) {
			Gaulois vendeur = etal[0].getVendeur();
			return "Seul le vendeur " + vendeur.getNom() + " " + finPhrase;
		}
		String texteEtals = "Les vendeurs qui proposent des " + produit + " sont :\n";
		for(int i=0;i<nbEtals;i++) {
			Gaulois vendeur = etal[i].getVendeur();
			texteEtals+= "- " + vendeur.getNom() + "\n";
		}
		return texteEtals;
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return this.marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		return rechercherEtal(vendeur).libererEtal();
	}
	
	public static void main(String[] args) {
//		Village village = new Village("VillageTest", 10,5);
//		Gaulois assurancetourix=new Gaulois("Assurancetourix", 3);
//		Gaulois asterix=new Gaulois("Astérix", 15);
//		System.out.println(marche.trouverEtalLibre());
//		marche.utiliserEtal(0, assurancetourix, "Poisson", 12);
//		System.out.println(marche.trouverEtalLibre());
//		Etal[] poisson = marche.trouverEtals("Poisson");
//		System.out.println(poisson[0].afficherEtal());
//		Etal etal_tr= marche.trouverVendeur(assurancetourix);
//		System.out.println(etal_tr.afficherEtal());
//		System.out.println("Affichage\n");
//		marche.utiliserEtal(1, asterix, "Sanglier", 9);
//		System.out.println(marche.afficherMarche());
		Village village = new Village("Test",10,5);
		Gaulois bonemine = new Gaulois("Bonemine", 5);
		Gaulois falbala = new Gaulois("Falbala", 7);
		System.out.println(village.installerVendeur(bonemine, "fleurs", 20));
		System.out.println(village.installerVendeur(falbala, "fleurs", 15));
		System.out.println(village.rechercherVendeursProduit("fleurs"));
		System.out.println(village.rechercherEtal(falbala).afficherEtal());
		System.out.println(village.partirVendeur(falbala));
	}
}