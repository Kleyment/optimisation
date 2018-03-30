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
	
	
	
	
}
