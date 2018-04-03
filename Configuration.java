package optimisation;

public class Configuration {

	private int nbProcessor;
	private int[] tableauTaches;
	private int evaluationMinimale;
	
	public Configuration(int nbProcessors, int... tableauTaches) {
		this.nbProcessor=nbProcessors;
		this.tableauTaches=tableauTaches;
		this.evaluationMinimale();
	}

	public int getEvaluationMinimale() {
		return evaluationMinimale;
	}

	public int getNbProcessor() {
		return nbProcessor;
	}

	public int[] getTableauTaches() {
		return tableauTaches;
	}
	
	//L'évaluation minimale possible (pas toujours atteignable)
	public void evaluationMinimale() {
		int somme=0;
		for (int i=0;i<tableauTaches.length;i++) {
			somme+=tableauTaches[i];
		}
		this.evaluationMinimale=(somme/nbProcessor);
	}
	
	
	
	
}
