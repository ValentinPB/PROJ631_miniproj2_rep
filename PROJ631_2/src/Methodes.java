import java.util.ArrayList;
import java.util.List;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class Methodes {

	public static List<String> getFileLines(String title) {
		List<String> lines = new ArrayList<>();
		String path = "C:\\Users\\Valentin\\Documents\\Annecy\\Projets\\PROJ631\\miniproj2\\";
		File text = new File(path + title + ".txt");
	    try {
			Scanner reader = new Scanner(text);
		    while (reader.hasNextLine()) {
		    	String line = reader.nextLine();
			    lines.add(line);
			    }
		    reader.close();
	    	} catch (FileNotFoundException e) {
	    	    System.out.println("Fichier non trouvé.");
	    	    e.printStackTrace();
	    	    }
		return(lines);
		}
	
	public static String concatLines(List<String> lines) {
		String res = "";
		for (int i = 0; i < lines.size(); i = i + 1) {
			res = res + lines.get(i) + " ";
		}
		return(res);
	}
	
	public static List<String> codeLines(List<String> lines, List<Character> ordres, List<String> codes) {
		List<String> codedlines = new ArrayList<String>();
		String construct;
		for (int l = 0; l < lines.size(); l = l + 1) {
			construct = "";
			for (int ch = 0; ch < lines.get(l).length(); ch = ch + 1) {
				for (int co = codes.size() - 1; co >= 0; co = co - 1) {
					if (lines.get(l).charAt(ch) == ordres.get(co)) {
						construct = construct + codes.get(co);
						break;
					}
				}
			}
			codedlines.add(construct);
		}
		return(codedlines);
	}
	
	public static void writeCodedText(String name, List<String> lines) {
		File text = new File(name + "_comp.bin");
		try {
		    if (text.createNewFile()) {
		    	System.out.println("Bien créé");
		    	FileWriter writer = new FileWriter(text);
		    	for (int i = 0; i < lines.size(); i = i + 1) {
		    		writer.write(lines.get(i) + "\n");
		    	}
		    	writer.close();
		    } else {
		    	System.out.println("Fichier existe déjà");
		    }
		} catch (IOException e) {
			System.out.println("Erreur");
			e.printStackTrace();
		}
	}
	
	public static void codageHuffman(String filename) {
		List<String> lines = Methodes.getFileLines(filename);
        String huffText = Methodes.concatLines(lines);
        Frequence huffFrq = new Frequence(huffText);
        Arbre arb = huffFrq.constructArbre();
        List<String> huffCodage = arb.codage();
        List<Character> huffOrdre = arb.ordrecodes();
        List<String> huffCoded = Methodes.codeLines(lines, huffOrdre, huffCodage);
        Methodes.writeCodedText(filename, huffCoded);
        huffFrq.writeFreqFile(filename);
        float taux = Methodes.tauxCompression(filename);
        float moyennebits = Methodes.bitsMoyens(huffCodage);
        System.out.println("Le taux de compression est : " + Float.toString(taux));
        System.out.println("Le nombre moyens de bits de stockage est : " + Float.toString(moyennebits));
	}
	
	public static int volumeLines(List<String> lines) {
		int vol = 0;
		for (int i = 0; i < lines.size(); i = i + 1) {
			vol = vol + lines.get(i).length();
		}
		return(vol);
	}
	
	public static float tauxCompression(String filename) {
		List<String> lines = Methodes.getFileLines(filename);
		List<String> complines = new ArrayList<>();
		File text = new File(filename + "_comp.bin");
	    try {
			Scanner reader = new Scanner(text);
		    while (reader.hasNextLine()) {
		    	String compline = reader.nextLine();
			    complines.add(compline);
			    }
		    reader.close();
	    	} catch (FileNotFoundException e) {
	    	    System.out.println("Fichier compressé non trouvé.");
	    	    e.printStackTrace();
	    	    }
	    float filevol = Methodes.volumeLines(lines);
	    float compvol = Methodes.volumeLines(complines);
	    return(1 - (compvol / filevol));
	}
	
	public static float bitsMoyens(List<String> codes) {
		float somme = 0;
		for (int i = 0; i < codes.size(); i = i + 1) {
			somme = somme + codes.get(i).length();
		}
		return(somme / (float) codes.size());
	}
	
}