package optimisation;

public class Main {

	public static void main(String[] args) {
		Configuration conf=new Configuration(2, 5,4,5,3,6,7);
		Solution s=new Solution(conf);
		System.out.println(s);
		System.out.println(s.evaluer());
	}

}
