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
	
	public void mutation(Solution sol) {		
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
		}		
		if (!tableauListProc[indProc2].isEmpty()) {
			tableauListProc[indProc2].set(indTache2, tache1);
		} else {
			tableauListProc[indProc2].add(tache1);
			tableauListProc[indProc1].remove(indTache1);
		}
		sol.setTableauListProc(tableauListProc);
	}	
	
	public Solution croisement(Solution s1, Solution s2) {
		ArrayList<Integer>[] tableauListProcS1 = s1.getTableauListProc();
		ArrayList<Integer>[] tableauListProcS2 = s2.getTableauListProc();
		
		int[] tableauS1=s1.creerTableau();
		int[] tableauS2=s2.creerTableau();
		
		int[] tableauCroisement=new int[tableauS1.length];
		
		// [5,5,7]
		// [5X5,7] coupe à 1
		// [5,5X7] coupe à 2
		if (tableauS1.length > 1) {
			int coupe=(int)(Math.random()*(tableauS1.length-1))+1;			
			for (int i=0;i<coupe;i++) {
				tableauCroisement[i]=tableauS1[i];
			}
			
			ArrayList<Integer> restant=new ArrayList<Integer>();
			for (int i=coupe;i<tableauS1.length;i++) {
				restant.add(tableauCroisement[i]);
			}
			
			ArrayList<Integer> index_restant=new ArrayList<Integer>();
			for (int i=0;i<restant.size();i++) {
				
				for (int j=0;j<tableauS2.length;j++) {
					if (tableauS2[j] == restant.get(i)) {
						index_restant.add(j);
					}
				}
			}
			
			
			
		}
		
		
		return null;
	}

}

