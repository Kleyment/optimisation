package optimisation;

import java.util.ArrayList;

public class Solution {

	private ArrayList<Integer>[] tableauListProc;
	private int evaluation;
	private Configuration conf;
	
	public Configuration getConf() {
		return conf;
	}

	public ArrayList<Integer>[] getTableauListProc() {
		return tableauListProc;
	}
	
	public void setTableauListProc(ArrayList<Integer>[] tableauListProc) {
		this.tableauListProc = tableauListProc;
	}
	
	public Solution(Configuration conf, ArrayList<Integer>[]tableauListProc) {
		this.conf = conf;
		this.tableauListProc=tableauListProc;
	}
	
	public Solution(Configuration conf, boolean alea) {
		
		this.conf = conf;
		int nbproc=conf.getNbProcessor();
		tableauListProc=new ArrayList[nbproc];
		
		for (int i=0;i<tableauListProc.length;i++) {
			tableauListProc[i]=new ArrayList<Integer>();
		}
		
		int[] tableauTaches=conf.getTableauTaches();
		int length=tableauTaches.length;
		
		if (alea == false) {
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
		} else {
			//On parcourt le tableau des tâches
			for (int i=0;i<length;i++) {
				int tache=tableauTaches[i];
				
				//On choisit un processeur aléatoire et on lui assigne une tâche
				int randProc=(int)(Math.random()*nbproc);
				tableauListProc[randProc].add(tache);
						
			}
		}
		
		this.evaluation=this.evaluer();
	}
	
	public Solution (Solution sol) {
		int nbProc = sol.conf.getNbProcessor();
		int[] tableauTache = new int[sol.conf.getTableauTaches().length];
		for (int i = 0; i<tableauTache.length; i++) {
			tableauTache[i] = sol.conf.getTableauTaches()[i];
		}
		Configuration config = new Configuration(nbProc, tableauTache);
		this.conf = config;
		
		ArrayList<Integer>[] tableauListProc = new ArrayList[sol.getTableauListProc().length];
		for (int j = 0; j < tableauListProc.length; j++) {
			tableauListProc[j] = new ArrayList<Integer>();
			for (int k : sol.getTableauListProc()[j]) {
				tableauListProc[j].add(k);
			}
		}		
		this.tableauListProc = tableauListProc;
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
	
	public int[] creerTableau() {
		int[] tab = new int[conf.getTableauTaches().length];
		int compteur = 0;
		for (int i = 0; i < tableauListProc.length; i++) {			
			for (int j = 0; j < tableauListProc[i].size(); j++) {
				tab[compteur + j] = tableauListProc[i].get(j);
			}
			compteur += tableauListProc[i].size();
		}
		return tab;
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
