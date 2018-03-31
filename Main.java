package optimisation;

public class Main {

	public static void main(String[] args) {
		Configuration conf=new Configuration(2, 5,4,5,3,6,7);
		Solution s=new Solution(conf,true);
		System.out.println(s);
		System.out.println(s.evaluer());
		AlgoGenetique algoGen=new AlgoGenetique(conf,0.1f,1);
		algoGen.mutation(s);
		System.out.println(s);
		System.out.println(s.evaluer());
	}

}
