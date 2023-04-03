import java.util.ArrayList;
import java.util.List;

public class Arbre {

	char caractere = 0;
	int occurence;
	Arbre filsDroit = null;
	Arbre filsGauche = null;
	
	public Arbre(char ecaractere, int eoccurence) {		//constructeur pour les feuilles
		caractere = ecaractere;
		occurence = eoccurence;
	}
	public Arbre(int eoccurence, Arbre fg, Arbre fd) {		//constructeur pour les sous-arbres
		occurence = eoccurence;
		filsGauche = fg;
		filsDroit = fd;
	}
	
	public boolean isLeaf() {
		if (filsGauche == null & filsDroit == null) {
			return(true);
		} else {
			return(false);
		}
	}
	
	public List<String> codage() {
		List<String> codes = new ArrayList<String>();
		this.filsGauche.sousCodage("1", codes);
		this.filsDroit.sousCodage("0", codes);
		return(codes);
	}
	
	public void sousCodage(String code, List<String> codes) {
		if (this.isLeaf()) {
			codes.add(code);
		} else {
			this.filsGauche.sousCodage(code + "1", codes);
			this.filsDroit.sousCodage(code + "0", codes);
		}
	}
	
	public List<Character> ordrecodes() {
		List<Character> ordrecodes = new ArrayList<Character>();
		this.filsGauche.sousordrecodes(ordrecodes);
		this.filsDroit.sousordrecodes(ordrecodes);
		return(ordrecodes);
	}
	
	public void sousordrecodes(List<Character> ordre) {
		if (this.isLeaf()) {
			ordre.add(this.caractere);
		} else {
			this.filsGauche.sousordrecodes(ordre);
			this.filsDroit.sousordrecodes(ordre);
		}
	}
	
}
