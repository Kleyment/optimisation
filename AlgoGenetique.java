package optimisation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class AlgoGenetique {
	
	private float probMutation;
	private int taillePopulation;
	private Configuration config;
	private ArrayList<Solution> population;
	
	public AlgoGenetique(Configuration config, float probMutation, int taillePopulation) {
		this.config=config;
		this.probMutation = probMutation;
		this.taillePopulation = taillePopulation;
	}
	
	public static void mutation(Solution sol) {		
		ArrayList<Integer>[] tableauListProc = sol.getTableauListProc();
		int indProc1=(int)(Math.random()*tableauListProc.length);
		
		while (tableauListProc[indProc1].isEmpty()) {
			indProc1=(int)(Math.random()*tableauListProc.length);
		}
		
		int indProc2=(int)(Math.random()*tableauListProc.length);
		
		if (tableauListProc.length != 1) {
			while (indProc2 == indProc1) {
				indProc2=(int)(Math.random()*tableauListProc.length);
			}			
		}
		
		int indTache1=(int)(Math.random()*tableauListProc[indProc1].size());
		int indTache2=(int)(Math.random()*tableauListProc[indProc2].size());
		
		int tache1 = tableauListProc[indProc1].get(indTache1);
		int tache2;
		if (!tableauListProc[indProc2].isEmpty()) {
			tache2 = tableauListProc[indProc2].get(indTache2);
			tableauListProc[indProc1].set(indTache1, tache2);
			tableauListProc[indProc2].set(indTache2, tache1);
		} else {
			tableauListProc[indProc2].add(tache1);
			tableauListProc[indProc1].remove(indTache1);
		}
		sol.setTableauListProc(tableauListProc);
	}	
	
	public static Solution croisement(Solution s1, Solution s2) {
		ArrayList<Integer>[] tableauListProcS1 = s1.getTableauListProc();
		ArrayList<Integer>[] tableauListProcS2 = s2.getTableauListProc();
		
		int[] tableauS1=s1.creerTableau();
		int[] tableauS2=s2.creerTableau();
		
		System.out.print("S1[");
		for (int i=0;i<tableauS1.length;i++) {
			System.out.print(tableauS1[i]+",");
		}
		System.out.println("]\n");
		
		System.out.print("S2[");
		for (int i=0;i<tableauS1.length;i++) {
			System.out.print(tableauS2[i]+",");
		}
		System.out.println("]\n");
		
		int[] tableauCroisement=new int[tableauS1.length];
		
		// [5,5,7]
		// [5X5,7] coupe à 1
		// [5,5X7] coupe à 2
		if (tableauS1.length > 1) {
			
			int coupe=(int)(Math.random()*(tableauS1.length-1))+1;
			System.out.println("coupe : "+coupe);
			
			for (int i=0;i<coupe;i++) {
				tableauCroisement[i]=tableauS1[i];
			}
			
			ArrayList<Integer> restant=new ArrayList<Integer>();
			for (int i=coupe;i<tableauS1.length;i++) {
				restant.add(tableauS1[i]);
				System.out.println("restant.add("+tableauS1[i]+");");
			}
			
			ArrayList<Integer> index_restant=new ArrayList<Integer>();
			for (int i=0;i<restant.size();i++) {
				for (int j=0;j<tableauS2.length;j++) {
					if (tableauS2[j] == restant.get(i) && !(index_restant.contains(j))) {
						index_restant.add(j);
						System.out.println("index_restant.add("+j+");");
						break;
					}
				}
			}
			
			ArrayList<Integer> restant_ordonne=new ArrayList<Integer>();

			while (!restant.isEmpty()) {
				//La valeur dans index_restant
				int indexmin=index_restant.get(0);
				//La clé dans index_restant
				int key_in_index_restant=0;
				
				//Recherche de la valeur min
				for (int i=0;i<index_restant.size();i++) {
					if (index_restant.get(i)<indexmin) {
						indexmin=index_restant.get(i);
						key_in_index_restant=i;
					}
				}
				restant_ordonne.add(restant.get(key_in_index_restant));
				restant.remove(key_in_index_restant);
				index_restant.remove(key_in_index_restant);
			}
			
			// 0 1 2 3 4 5 6 length=7
			// 0 1 2X3 4 5 6 coupe=3
			int j=0;
			for (int i=coupe;i<tableauS1.length;i++) {
				tableauCroisement[i]=restant_ordonne.get(j);
				j++;
			}
						
			System.out.print("S3[");
			for (int i=0;i<tableauS1.length;i++) {
				System.out.print(tableauCroisement[i]+",");
			}
			System.out.println("]\n");
			
			ArrayList<Integer>[] tableauListProc = new ArrayList[s1.getTableauListProc().length];
			Configuration conf=s1.getConf();
			
			int k=0;
			for (j = 0; j < tableauListProc.length; j++) {
				tableauListProc[j] = new ArrayList<Integer>();
				for (int i=0;i<s1.getTableauListProc()[j].size();i++) {
					tableauListProc[j].add(tableauCroisement[k]);
					k++;
				}
			}

			return new Solution(conf,tableauListProc);
		}
		
		
		return null;
	}

}

