package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	
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
			boolean found=false;
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

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
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
	
	public static void main(String[] args) {
		Village village = new Village("VillageTest", 10);
		Marche marche = new Marche(5);
		Gaulois assurancetourix=new Gaulois("Assurancetourix", 3);
		Gaulois asterix=new Gaulois("Astérix", 15);
		System.out.println(marche.trouverEtalLibre());
		marche.utiliserEtal(0, assurancetourix, "Poisson", 12);
		System.out.println(marche.trouverEtalLibre());
		Etal[] poisson = marche.trouverEtals("Poisson");
		System.out.println(poisson[0].afficherEtal());
		Etal etal_tr= marche.trouverVendeur(assurancetourix);
		System.out.println(etal_tr.afficherEtal());
		System.out.println("Affichage\n");
		marche.utiliserEtal(1, asterix, "Sanglier", 9);
		System.out.println(marche.afficherMarche());
	}
}