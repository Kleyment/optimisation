package optimisation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Solution {

	private ArrayList<Integer>[] tableauListProc;
	private int evaluation;
	private Configuration conf;
	
	public Solution(Configuration conf, ArrayList<Integer>[]tableauListProc) {
		this.conf = conf;
		this.tableauListProc=tableauListProc;
		this.evaluer();
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
		
		this.evaluer();
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
		this.evaluer();
	}
	
	public int getEvaluation() {
		return evaluation;
	}

	public Configuration getConf() {
		return conf;
	}

	public ArrayList<Integer>[] getTableauListProc() {
		return tableauListProc;
	}
	
	public void setTableauListProc(ArrayList<Integer>[] tableauListProc) {
		this.tableauListProc = tableauListProc;
	}
	
	public void evaluer() {
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
		this.evaluation=maxSomme;
		
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
	
	@Override
	public boolean equals(Object o) {
		if (! (o instanceof Solution)) {
			return false;
		}
		Solution sol = (Solution) o;
		boolean equals = true;
		ArrayList<Integer> list1, list2;
		for (int i = 0; i < this.tableauListProc.length; i++) {
			list1 = new ArrayList<Integer>(tableauListProc[i]);
			Collections.sort(list1);
			list2 = new ArrayList<Integer>(sol.getTableauListProc()[i]);
			Collections.sort(list2);
			if (!(list1.size() == list2.size())) {
				equals = false;
				break;
			} else {
				for (int j = 0; j < list1.size(); j++) {	
					if (list1.get(j) != list2.get(j)) {
						equals = false;
						break;
					}
				}
			}
		}
		return equals;
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
