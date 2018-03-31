package optimisation;

import java.util.ArrayList;

public class Solution {

	private ArrayList<Integer>[] tableauListProc;
	
	public Solution(Configuration conf) {
		int nbproc=conf.getNbProcessor();
		tableauListProc=new ArrayList[nbproc];
		
		for (int i=0;i<tableauListProc.length;i++) {
			tableauListProc[i]=new ArrayList<Integer>();
		}
		
		int[]tableauTaches=conf.getTableauTaches();
		int length=tableauTaches.length;
		
		int ratio=length/nbproc; // 5/2 ratio=2
		int reste=length%nbproc;  // 5%2 reste=1

		//On essaye d'équilibrer chaque processeur
		for (int i=0;i<nbproc;i++) {
			for (int j=0;j<ratio;j++) {
				tableauListProc[i].add(tableauTaches[j+i*ratio]);
			}
		}
		
		//On met le reste dans le dernier processeur
		if (reste != 0) {
			for (int j=0;j<reste;j++) {
				tableauListProc[nbproc-1].add(tableauTaches[tableauTaches.length-(j+1)]);
			}
		}
	}
	
	public int evaluer() {
		int[] somme=new int[tableauListProc.length];
		//On initialise les sommes (une pour chaque processeur) à 0
		for (int i=0;i<tableauListProc.length;i++) {
			somme[i]=0;
		}
		
		//On calcule une somme pour chaque processeur
		for (int i=0;i<tableauListProc.length;i++) {
			for (int j=0;j<tableauListProc[i].size();j++) {
				somme[i]+=tableauListProc[i].get(j);
			}
		}
		
		//On retourne la somme maximale
		int maxSomme=0;
		for (int i=0;i<tableauListProc.length;i++) {
			if (somme[i] > maxSomme) {
				maxSomme=somme[i];
			}
		}
		return maxSomme;
		
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<tableauListProc.length;i++) {
			sb.append("Processeur "+i+"[");
			for (int j=0;j<tableauListProc[i].size();j++) {
				sb.append(tableauListProc[i].get(j));
				if (j!=tableauListProc[i].size()-1) {
					sb.append(",");
				}
			}
			sb.append("]\n");
		}
		return sb.toString();
	}
	
}
