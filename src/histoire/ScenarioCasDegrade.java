package histoire;
import personnages.*;
import villagegaulois.*;


public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		Etal etal = new Etal();
		etal.libererEtal();
		System.out.println("Fin du test 1");
		Gaulois bonemine = new Gaulois("Bonemine", 3);
		etal.acheterProduit(2, null);
		System.out.println("Fin du test 2");
		etal.acheterProduit(-1, bonemine);
		System.out.println("Fin du test 3");
		etal.acheterProduit(3, bonemine);
		System.out.println("Fin du test 4");
		}

}
