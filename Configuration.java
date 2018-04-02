package optimisation;

public class Configuration {

	private int nbProcessor;
	private int[] tableauTaches;
	
	public Configuration(int nbProcessors, int... tableauTaches) {
		this.nbProcessor=nbProcessors;
		this.tableauTaches=tableauTaches;
	}

	public int getNbProcessor() {
		return nbProcessor;
	}

	public int[] getTableauTaches() {
		return tableauTaches;
	}
	
	//L'évaluation minimale possible (pas toujours atteignable)
	public int evaluationMinimale() {
		int somme=0;
		for (int i=0;i<tableauTaches.length;i++) {
			somme+=tableauTaches[i];
		}
		return (somme/nbProcessor);
	}
	
	
	
	
}
