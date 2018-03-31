package optimisation;

import java.util.ArrayList;

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
		int indProc2=(int)(Math.random()*tableauListProc.length);
		
		if (tableauListProc.length != 1) {
			while (indProc2 == indProc1) {
				indProc2=(int)(Math.random()*tableauListProc.length);
			}			
		}
		
		int indTache1=(int)(Math.random()*tableauListProc[indProc1].size());
		int indTache2=(int)(Math.random()*tableauListProc[indProc2].size());
		
		int tache1 = tableauListProc[indProc1].get(indTache1);
		int tache2 = tableauListProc[indProc2].get(indTache2);
		
		tableauListProc[indProc1].set(indTache1, tache2);
		tableauListProc[indProc2].set(indTache2, tache1);
		
		sol.setTableauListProc(tableauListProc);
	}
	

}

