package optimisation;

import java.util.ArrayList;

public class RechercheTabou {

	private ArrayList<Solution> listeTabou;
	private Configuration conf;
	private Solution solution;
	private int tailleListe;
	
	public RechercheTabou(Configuration conf, int tailleListe) {
		this.conf = conf;
		listeTabou = new ArrayList<Solution>();
		solution = new Solution(conf, true);
		this.tailleListe = tailleListe;
	}
	
	public void mouvement() {
		boolean equals = false;
		ArrayList<Solution> listeVoisins = new ArrayList<Solution>();
		Solution solutionActuelle = solution;
		int eval;
		while (listeTabou.size() < tailleListe) {
			Solution voisin1=new Solution(this.solution);
			AlgoGenetique.mutation(voisin1);
			listeVoisins.add(voisin1);
			Solution voisin2=new Solution(this.solution);
			AlgoGenetique.mutation(voisin2);
			listeVoisins.add(voisin2);
			Solution voisin3=new Solution(this.solution);
			AlgoGenetique.mutation(voisin3);
			listeVoisins.add(voisin3);
			Solution voisin4=new Solution(this.solution);
			AlgoGenetique.mutation(voisin4);
			listeVoisins.add(voisin4);
			eval = voisin1.getEvaluation();
			solutionActuelle = voisin1;
			for (Solution v : listeVoisins) {
				if (v.getEvaluation() < eval) {
					eval = v.getEvaluation();
					solutionActuelle = v;
				}
			}
			listeVoisins.clear();
			for (Solution s : listeTabou) {
				if (solutionActuelle.equals(s)) {
					equals = true;
					break;
				}
			}
			if (!equals) {
				listeTabou.add(solutionActuelle);
				solution = solutionActuelle;
			}
			equals = false;
		}
	}

	public Solution getSolution() {
		return solution;
	}	
	
}
