package optimisation;

import java.util.ArrayList;

public class RechercheTabou {

	private ArrayList<Solution> listeTabou;
	private Configuration conf;
	private Solution solution;
	
	public RechercheTabou(Configuration conf, int tailleListe) {
		this.conf = conf;
		listeTabou = new ArrayList<Solution>(tailleListe);
		solution = new Solution(conf, true);
		
	}
	
	public void mouvement() {
		boolean equals = false;
		while (!listeTabou.isEmpty()) {
			Solution solution_actuelle=new Solution(this.solution);
			AlgoGenetique.mutation(solution_actuelle);
			for (Solution s : listeTabou) {
				if (solution_actuelle.equals(s)) {
					equals = true;
				}
			}
			if (!equals) {
				listeTabou.add(solution_actuelle);
				solution = solution_actuelle;
			}
		}
	}
	
}
