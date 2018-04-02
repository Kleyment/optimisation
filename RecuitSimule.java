package optimisation;

public class RecuitSimule {
	
	private int temperatureDep;
	private Configuration conf;
	private Solution solution;
	
	public RecuitSimule(int temperatureDep, Configuration conf) {
		this.temperatureDep = temperatureDep;
		this.conf = conf;
		this.solution = new Solution(conf,true);
	}
	
	

}
