package optimisation;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Configuration conf=new Configuration(4, 1,2,3,4,5,6,7);
		/*Solution s=new Solution(conf,true);
		Solution s2=new Solution(conf,true);*/
		
		AlgoGenetique algoGen=new AlgoGenetique(conf,0.1f,100);
		Solution s=algoGen.boucle(100);
		System.out.println(s);
		System.out.println(s.getEvaluation());
		/*System.out.println(s);
		System.out.println(s.evaluer());
		Solution s2 = new Solution(s);
		System.out.println("s2 : "+ s2);
		int[] tab = s.creerTableau();
		for (int i = 0; i < tab.length; i++) {
			System.out.print(tab[i]);
		}
		AlgoGenetique algoGen=new AlgoGenetique(conf,0.1f,1);
		AlgoGenetique.mutation(s);
		System.out.println(s);
		System.out.println(s.evaluer());
		System.out.println("s2 :"+ s2);
		
		ArrayList<Integer>[] tabE1 = new ArrayList[2];
		tabE1[0] = new ArrayList<Integer>();
		tabE1[0].add(5);
		tabE1[0].add(4);
		tabE1[0].add(5);
		tabE1[1] = new ArrayList<Integer>();
		tabE1[0].add(3);
		tabE1[0].add(6);
		tabE1[0].add(7);
			
		Solution sE1 = new Solution(conf,tabE1);
		
		ArrayList<Integer>[] tabE2 = new ArrayList[2];
		tabE2[0] = new ArrayList<Integer>();
		tabE2[0].add(4);
		tabE2[0].add(3);
		tabE2[0].add(5);
		tabE2[1] = new ArrayList<Integer>();
		tabE2[0].add(7);
		tabE2[0].add(6);
		tabE2[0].add(3);
			
		Solution sE2 = new Solution(conf,tabE2);
		
		System.out.println(sE1.equals(sE2));*/
	}

}
