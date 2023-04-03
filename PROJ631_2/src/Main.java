import java.util.List;

public class Main {

	public static void main(String[] arg) {
		
		Methodes.codageHuffman("textesimple");
        Methodes.codageHuffman("extraitalice");
        Methodes.codageHuffman("alice");
        
		
        //Ci-dessous est un texte défini pour tester le bon fonctionnement du tri des listes.
        
		String testtxt = "a bb ccc gggg ffff eeee dddd";
		Frequence testfrq = new Frequence(testtxt);
		
        System.out.println(testfrq.alphabet);
        System.out.println(testfrq.occurences);
        //System.out.println(testfrq.ascii);
        Arbre testarbre = testfrq.constructArbre();
        List<String> testcodage = testarbre.codage();
        System.out.println(testcodage);
        List<Character> testordre = testarbre.ordrecodes();
        System.out.println(testordre);
        
        
        //Ci-dessous sont les instructions permettant de tester le programme avant la définition de la méthode codageHuffman.
        
        /*List<String> gotlines = Methodes.getFileLines("alice");
        System.out.println(gotlines);
        String sampletext = Methodes.concatLines(gotlines);
        System.out.println(sampletext);
        Frequence samplefrq = new Frequence(sampletext);
        System.out.println(samplefrq.alphabet);
        System.out.println(samplefrq.occurences);
        //System.out.println(samplefrq.ascii);
        Arbre sampleArbre = samplefrq.constructArbre();
        List<String> samplecodage = sampleArbre.codage();
        System.out.println(samplecodage);
        List<Character> sampleordre = sampleArbre.ordrecodes();
        System.out.println(sampleordre);
        List<String> samplecoded = Methodes.codeLines(gotlines, sampleordre, samplecodage);
        System.out.println(samplecoded);
        Methodes.writeCodedText("alice", samplecoded);
        samplefrq.writeFreqFile("alice");*/
		}
	}
