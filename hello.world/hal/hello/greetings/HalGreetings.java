package hello.greetings;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.IOException;

public class HalGreetings {
	
	static public void greetings(PrintStream ps, String[] args) {
		echoGreetings(ps,args);
	}
	
	public static void main(String[] args) throws IOException {
		String[] fullname;
		if (args == null || args.length == 0) {
			System.out.println("Hello, I am Hal, and you are?");
			fullname = readFullName(System.in);
		} else {
			System.out.println("Hello, I am Hal.");	
			fullname = args;
		}
		echoGreetings(System.out, fullname);
	}

	private static String[] readFullName(InputStream in) throws IOException {
		
		
//          NOTRE CODE 		
		
		
//		byte[] inputBuffer = new byte[100]; //Initialize un buffer de taille 100
//		int nbr_lu = 0; // 
//		
//		try {
//			nbr_lu = in.read(inputBuffer); 
//			// stocke le nombre de caractère dans le buffer (la longueur de la chaine)
//		}catch(Exception e) {}
//		// crée un string qui contiendra le mot entré au clavier
//		String fullName = new String(inputBuffer, 0, nbr_lu).trim();
//		// .trim() permet de supprimer les espaces en trop
//		// permet de découoer le string par des espaces (pour se faire une idée : fullname[0] = "Emilio"
//		return fullName.split(" ");
		
	   InputStreamReader r = new InputStreamReader(in);
	   BufferedReader br = new BufferedReader(r);
	   String line = br.readLine();
	   String[] names;
	   int compteur = 0; //variable qui stockera le nbr d'espaces
	   for (int i = 0 ; i < line.length(); i++) {
		   if(line.charAt(i) == ' ') {
			   compteur++; 
			   //si un espace est détécté, on ajoute le nbr despace à 1
		   }
	   }
	   
	   int[] index = new int[compteur];
	   int k = 0; // on initialise le compteur qui va croître
	   
	   for(int i = 0 ; i<line.length();i++) {
		   if(line.charAt(i) == ' ') {
			   index[k]=i;
			   k++;
		   }
	   }
	   
	   int index_avant = 0;
	   names = new String[compteur+1];

	   for(int i =0 ; i<compteur ; i++) {
		   names[i] = line.substring(index_avant,index[i]);
		   index_avant = index[i] +1;
	   }
	   names[compteur] =line.substring(index_avant);
	   return names;
	 }
	

	private static void echoGreetings(PrintStream ps, String[] names) {
		
		ps.printf("Greetings ");
		for (int i = 0; i < names.length; i++) {
			ps.printf("%s ", names[i]);
		}
		ps.printf("!");
	}
}