package optimisation;

public class RecuitSimule {
	
	private int temperatureDep;
	private Configuration conf;
	private Solution solution;
	
	public RecuitSimule(Configuration conf, int temperatureDep) {
		this.temperatureDep = temperatureDep;
		this.conf = conf;
		this.solution = new Solution(conf,true);
	}
	
	public void variationEnergie() {
		Solution copie_solution=new Solution(this.solution);
		AlgoGenetique.mutation(copie_solution);
		
		float variationEnergie=copie_solution.getEvaluation()-this.solution.getEvaluation();
		
		if (variationEnergie <= 0) {
			this.solution=copie_solution;
		} else {
			double proba=Math.exp(-variationEnergie/temperatureDep);
			double random=Math.random();
			
			if (random < proba) {
				this.solution=copie_solution;
			}
		}
	}
	
	public Solution boucle(int iteration) {
		for (int i=0;i<iteration;i++) {
			this.variationEnergie();
			if ((this.solution.getEvaluation() == conf.getEvaluationMinimale())) {
				break;
			}
		}
		return this.solution;
	}

	public Solution getSolution() {
		return solution;
	}
	

}
