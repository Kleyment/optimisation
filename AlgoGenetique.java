package optimisation;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Random;
=======
>>>>>>> 203d6a20cccff5398c47e3932da68f033711ecb6

public class AlgoGenetique {
	
	private float probMutation;
	private int taillePopulation;
	private ArrayList<Solution> population;
	
	public AlgoGenetique(float probMutation, int taillePopulation) {
		this.probMutation = probMutation;
		this.taillePopulation = taillePopulation;
	}
	
	public void mutation(Solution sol) {		
		ArrayList<Integer>[] tableauListProc = sol.getTableauListProc();
		Random rand = new Random();
		int indProc1 = rand.nextInt(tableauListProc.length - 0 + 1) + 0;
		int indProc2 = rand.nextInt(tableauListProc.length - 0 + 1) + 0;
		if (indProc2 == indProc1) {
			indProc2 = rand.nextInt(tableauListProc.length - 0 + 1) + 0;
		}
		int indTache1 = rand.nextInt(tableauListProc[indProc1].size() - 0 + 1) + 0;
		int indTache2 = rand.nextInt(tableauListProc[indProc2].size() - 0 + 1) + 0;
		int tache1 = tableauListProc[indProc1].get(indTache1);
		int tache2 = tableauListProc[indProc2].get(indTache2);
		tableauListProc[indProc1].set(indTache1, tache2);
		tableauListProc[indProc2].set(indTache2, tache1);		
	}
	

}

