import java.util.ArrayList;
import java.util.List;
import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors

public class Frequence {

	List<Character> alphabet = new ArrayList<Character>();
    List<Integer> occurences = new ArrayList<Integer>();
    int nbocc;
    
	public Frequence(String text) {

        char tempchar;

        for (int i = 0; text.length() != 0; i = i + 1) {
            nbocc = 0;
            tempchar = text.charAt(0);
            for (int j = 0; j < text.length(); j = j + 1) {
            	//System.out.println(tempchar + " / " + String.valueOf(testtxt.charAt(j)));
                if (tempchar == text.charAt(j)) {
                    nbocc = nbocc + 1;
                    //System.out.println("occ+1");
                    }
                }
            //System.out.println(alphabet);
            alphabet.add(tempchar);
            occurences.add(nbocc);
            text = text.replace(Character.toString(tempchar), "");
            //System.out.println(testtxt);
            }
        
        //triage des listes :
        alphabet.add(0, 'a');
        occurences.add(0, 0);
        for (int i = 1; i < occurences.size(); i = i + 1) {
        	int tempocc = occurences.get(i);
        	char tempabc = alphabet.get(i);
        	int j = i;
        	while ((j > 0) & (occurences.get(j - 1) > tempocc)) {
        		//System.out.println(j > 0);
        		//System.out.println(occurences.get(j - 1) > tempocc);
        		occurences.set(j, occurences.get(j - 1));
        		alphabet.set(j, alphabet.get(j - 1));
        		j = j - 1;
        		if (j == 0) {
        			break;
        		}
        		//System.out.println(Integer.toString(i) + " | " + Integer.toString(j));
        	occurences.set(j, tempocc);
        	alphabet.set(j, tempabc);
        	}
        }
        alphabet.remove(0);
        occurences.remove(0);
        
        //tri par ordre ascii
        int x = occurences.get(0);
        for (int i = 1; i < occurences.size(); i = i + 1) {		//parcours des listes
        	x = occurences.get(i);
        	for (int l = i; x == occurences.get(l) & l < (occurences.size() - 1); l = l + 1) {		//tri par groupes de mêmes occurences
        			//System.out.println(l);
        			//System.out.println(occurences);
        			//System.out.println(alphabet);
                	int tempocc = occurences.get(l);
                	char tempabc = alphabet.get(l);
                	int j = l;
                	while (x == occurences.get(j - 1) & (alphabet.get(j - 1) > tempabc)) {
            			//System.out.println(j);
            			//System.out.println(occurences);
            			//System.out.println(alphabet);
                		
                		//System.out.println(j > 0);
                		//System.out.println(occurences.get(j - 1) > tempocc);
                		occurences.set(j, occurences.get(j - 1));
                		alphabet.set(j, alphabet.get(j - 1));
                		j = j - 1;
                		if (j == 0) {
                			break;
                		}
                		//System.out.println(Integer.toString(i) + " | " + Integer.toString(j));
                	occurences.set(j, tempocc);
                	alphabet.set(j, tempabc);
                	}
        	}
        }
        
	}
	
	public Arbre constructArbre() {
		List<Arbre> arbres = new ArrayList<>();
		Arbre current;
		int currentInd = -1;
		for (int i = 0; i < alphabet.size(); i = i + 1) {
			arbres.add(new Arbre(alphabet.get(i), occurences.get(i)));		//définition des feuilles
		}
		//System.out.println(arbres);		//pour voir la liste des feuilles
		while (arbres.size() > 1) {
			current = new Arbre(arbres.get(0).occurence + arbres.get(1).occurence, arbres.get(0), arbres.get(1));
			arbres.remove(0);
			arbres.remove(0);
			for (int j = 0; j < arbres.size(); j = j + 1) {
				if (current.occurence <= arbres.get(j).occurence) {
					currentInd = j;
					break;
				}
				//System.out.println(currentInd);
				//System.out.println(arbres);
			}
			if (currentInd == -1) {
				arbres.add(current);
			} else {
				arbres.add(currentInd, current);
			}
			//System.out.println(arbres);	//pour voir la liste à chaque tour
			currentInd = -1;
		}
		return(arbres.get(0));
	}
	
	public void writeFreqFile(String name) {
		File freqtext = new File(name + "_freq.txt");
		try {
		    if (freqtext.createNewFile()) {
		    	System.out.println("Bien créé freq");
		    	FileWriter writer = new FileWriter(freqtext);
		    	writer.write(Integer.toString(alphabet.size()) +  "\n");
		    	for (int i = 0; i < alphabet.size(); i = i + 1) {
		    		writer.write(alphabet.get(i) + " " + occurences.get(i) + "\n");
		    	}
		    	writer.close();
		    } else {
		    	System.out.println("Fichier freq existe déjà");
		    }
		} catch (IOException e) {
			System.out.println("Erreur freq");
			e.printStackTrace();
		}
	}
}
