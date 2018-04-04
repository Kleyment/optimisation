package optimisation;

import java.util.Scanner;

public class Main {
	
	public static Configuration choisirConfiguration(Scanner sc) {
		Configuration conf;
		System.out.println("Une nouvelle configuration va être créé");
		System.out.println("Veuillez entrer le nombre de processeur : ");
		int nbproc=sc.nextInt();
		System.out.println("Veuillez entrer le nombre de taches : ");
		int nbtaches=sc.nextInt();
		
		int[] tableau_taches=new int[nbtaches];
		for (int i=0;i<nbtaches;i++) {
			System.out.println("Veuillez entrer la taches "+i+" : ");
			tableau_taches[i]=sc.nextInt();
		}
		return conf=new Configuration(nbproc,tableau_taches);
	}
	
	public static void choisirAlgoGen(Scanner sc, Configuration conf) {
		int taillepopulation;
		float probMut;
		System.out.println("Veuillez choisir la taille de la population : ");
		taillepopulation=sc.nextInt();
		System.out.println("Veuillez choisir la probabilité de mutation (utiliser , et pas .) : ");
		probMut=sc.nextFloat();
		
		AlgoGenetique algoGen=new AlgoGenetique(conf,probMut,taillepopulation);
		
		while(true) {
			System.out.println("");
			System.out.println("Que souhaiter vous faire ?");
			System.out.println("Effectuer un nombre d'iteration (i)");
			System.out.println("Voir un individu (v)");
			System.out.println("Voir le meilleur individu (m)");
			System.out.println("Retour (r)");
			
			char code=sc.next().charAt(0);
			switch (code) {
			case 'i':
				System.out.println("Veuillez choisir le nombre d'itérations");
				int iteration=sc.nextInt();
				algoGen.boucle(iteration);
				break;
			case 'v':
				System.out.println("Veuillez choisir quel individu voir : ");
				
				while (true) {
					int num=sc.nextInt();
					if (num < taillepopulation && num > -1) {
						Solution s=algoGen.getIndividu(num);
						System.out.println(s);
						System.out.println("evaluation max : "+s.getEvaluation());
						break;
					} else {
						System.out.println("Numéro invalide");
					}
				}
				break;
			case 'm':
				Solution s=algoGen.getMeilleurIndividu();
				System.out.println(s);
				System.out.println("evaluation max : "+s.getEvaluation());
				break;
			case 'r':
				return;
			default:
				return;
			}
		}
	}
	
	public static void choisirRecuit(Scanner sc, Configuration conf) {
		int temperatureDep;
		System.out.println("Veuillez choisir la temperature initiale : ");
		temperatureDep=sc.nextInt();
		RecuitSimule recuitSim=new RecuitSimule(conf,temperatureDep);
		
		while(true) {
			System.out.println("");
			System.out.println("Que souhaiter vous faire ?");
			System.out.println("Changer la température (t)");
			System.out.println("Effectuer un nombre d'iteration (i)");
			System.out.println("Voir la solution actuelle (s)");
			System.out.println("Retour (r)");
			
			char code=sc.next().charAt(0);
			switch (code) {
			case 't':
				System.out.println("Veuillez choisir la temperature initiale : ");
				temperatureDep=sc.nextInt();
				recuitSim=new RecuitSimule(conf,temperatureDep);
				break;
			case 'i':
				System.out.println("Veuillez choisir le nombre d'itérations");
				int iteration=sc.nextInt();
				recuitSim.boucle(iteration);
				break;
			case 's':
				Solution s=recuitSim.getSolution();
				System.out.println(s);
				System.out.println("evaluation max : "+s.getEvaluation());
				break;
			case 'r':
				return;
			default:
				return;
			}
		}
	}
	
	public static void choisirTabou(Scanner sc, Configuration conf) {
		int tailleListe;
		System.out.println("Veuillez choisir la taille de la liste tabou : ");
		tailleListe=sc.nextInt();
		RechercheTabou rechercheTab=new RechercheTabou(conf,tailleListe);
		
		while(true) {
			System.out.println("");
			System.out.println("Que souhaiter vous faire ?");
			System.out.println("Changer la taille de la liste (t)");
			System.out.println("Effectuer des mouvements (m)");
			System.out.println("Voir la solution actuelle (s)");
			System.out.println("Retour (r)");
			
			char code=sc.next().charAt(0);
			switch (code) {
			case 't':
				System.out.println("Veuillez choisir la taille de la liste tabou : ");
				tailleListe=sc.nextInt();
				rechercheTab=new RechercheTabou(conf,tailleListe);
				break;
			case 'm':
				System.out.println("Veuillez choisir le nombre d'itérations");
				int iteration=sc.nextInt();
				rechercheTab.mouvement(iteration);
				break;
			case 's':
				Solution s=rechercheTab.getSolution();
				System.out.println(s);
				System.out.println("evaluation max : "+s.getEvaluation());
				break;
			case 'r':
				return;
			default:
				return;
			}
		}
	}

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		Configuration conf;
		
		int nbproc;
		int nbtaches;
		if (args.length > 1) {
			nbproc=Integer.parseInt(args[0]);
			nbtaches=args.length-1;
			int[] tableau_taches=new int[nbtaches];
			for (int i=0;i<args.length-1;i++) {
				tableau_taches[i]=Integer.parseInt(args[i+1]);
			}
			conf=new Configuration(nbproc,tableau_taches);
		} else {
			conf=choisirConfiguration(sc);
		}
		while (true) {
			System.out.println("");
			System.out.println("Que voulez vous faire ?");
			System.out.println("Changer de configuration (c)");
			System.out.println("Faire un algo génétique sur la configuration (g)");
			System.out.println("Faire un recuit simulé sur la configuration (r)");
			System.out.println("Faire une recherche tabou sur la configuration (t)");
			System.out.println("Quitter (q)");
			
			char code=sc.next().charAt(0);
			
			switch (code) {
			case 'c':
				conf=choisirConfiguration(sc);
				break;
			case 'g':
				choisirAlgoGen(sc,conf);
				break;
			case 'r':
				choisirRecuit(sc,conf);
				break;
			case 't':
				choisirTabou(sc,conf);
				break;
			case 'q':
				return;
			default:
				return;
			}
		}
	}

}
