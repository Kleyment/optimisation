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
		
		int variationEnergie=copie_solution.getEvaluation()-this.solution.getEvaluation();
		
		if (variationEnergie < 0) {
			this.solution=copie_solution;
		} else {
			System.out.println(Math.exp(-variationEnergie/temperatureDep));
		}
		
	}
	
	

}
