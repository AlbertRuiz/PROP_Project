import java.io.Serializable;
import java.util.*;

public class GestorMatriu implements Serializable{
	private static final long serialVersionUID = 1L;
	Matriu M;
	ArrayList<String> funcionals;
	
	public GestorMatriu(ArrayList<String> paraules_func) {
		this.M = new Matriu();
		this.funcionals = paraules_func;
	}
	
	public void add_pFuncionals(ArrayList<String> paraules_func) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>S'afegeix la llista de paraules_func.</Ul>
		  * 
		*/
		this.funcionals.addAll(paraules_func);
		Collections.sort(this.funcionals);
	}
	
	public boolean addDocument_buit(String titol, String autor) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna false i no afegeix res si el document identificat per
		  * titol i autor ja existeix, altrament afegeix el document i retorna true.</Ul>
		  * 
		*/
		if (!this.M.existeix_Doc(titol, autor)) {
			this.M.add_Doc_buit(titol,autor);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean addParaula(String titol, String autor, String paraula) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Si el titol no existeix no es fa res i es retorna false,
		  * si el titol existeix s'afeigeix simplement una aparicio
		  * de paraulas.
		  * Si paraula es una paraula no funcional no s'afegeix res.
		  * Es retorna true en ambdos condicions anteriors.
		  * </Ul>
		  * 
		*/
		
		
		if (this.M.existeix_Doc(titol, autor)) {
			int i = Collections.binarySearch(this.funcionals, paraula);
	        if (i < 0) i = ~i;
	        if (i >= this.funcionals.size() || !this.funcionals.get(i).matches(paraula)) {//miro si es funcional, sino ho es la puc afegir.
	    			this.M.add_Paraula(titol, autor, paraula);
	        }
	        return true;			
		}
		else return false;
	}
	
	public void addContingut(String titol, String autor, String[] contingut) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Si el titol no existeix, s'afeigeix un nou titol
		  * amb les aparicions de paraules funcionals de contingut, si el titol existeix
		  * s'afeigeixen les aparicions de les paraules funcionals de continugut.</Ul>
		  * 
		*/
		if (this.M.existeix_Doc(titol,autor)) {
			for (String paraula : contingut) {
				int i = Collections.binarySearch(this.funcionals, paraula);
		        if (i < 0) i = ~i;
		        if (i >= this.funcionals.size() || !this.funcionals.get(i).matches(paraula)) {
		        	this.M.add_Paraula(titol, autor, paraula);
		        }
			}
		}
		else {
			this.M.add_Doc_buit(titol,autor);
			for (String paraula : contingut) {
				int i = Collections.binarySearch(this.funcionals, paraula);
		        if (i < 0) i = ~i;
		        if (i >= this.funcionals.size() || !this.funcionals.get(i).matches(paraula)) {
		        	this.M.add_Paraula(titol, autor, paraula);
		        }
			}
		} 
	}
	
	public void replaceContingut(String titol, String autor, String[] contingut) {
		/** <p><b>Pre:</b></p>  <Ul>Ha d'existir el document amb titol = titol i el seu autor ha de ser = autor.</Ul>
		  * <p><b>Post:</b></p> <Ul>Reemplaça el contingut que tenia el document per el contingut passat com a parametre.</Ul>
		  * 
		*/
		if (this.M.existeix_Doc(titol,autor)) {
			this.M.delete_Doc(titol, autor);
			this.M.add_Doc_buit(titol, autor);
			for (String paraula : contingut) {
				int i = Collections.binarySearch(this.funcionals, paraula);
		        if (i < 0) i = ~i;
		        if (i >= this.funcionals.size() || !this.funcionals.get(i).matches(paraula)) {
		        	this.M.add_Paraula(titol, autor, paraula);
		        }
			}
			
		}
	}
	
	public boolean existeixDocument(String titol, String autor) {
    	/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna true si exiteix el document amb Titol = titol i Autor = autor, retorna false altrament.</Ul>
		  * 
		*/
    return this.M.existeix_Doc(titol, autor);
    }
	
	//public void addFrase(String titol, String autor, Frase frase) {
	//NO IMPLEMENTADA JA QUE AL MODIFICAR ÉS REEMPLAÇA TOT EL CONTINGUT
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Si el titol no existeix, s'afeigeix un nou titol
		  * amb les aparicions de paraules funcionals de frase, si el titol existeix
		  * s'afeigeixen les aparicions de les paraules funcionals de frase.</Ul>
		  * 
		*/
		/*if (this.M.existeix_Doc(titol, autor)) {
			for (String par : frase.paraules) {

				
			}
		}
		else {
			this.M.add_Doc_buit(titol,autor);
			for (String par : frase.paraules) {
				
			}
		}
	}*/
	
	public boolean deleteDoc(String titol, String autor) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Si exiteix el document amb Titol = titol s'elimina i es
		  * retorna true, sino no es fa res i es retorna false.</Ul>
		  * 
		*/
		if (this.M.existeix_Doc(titol, autor)) {
			this.M.delete_Doc(titol, autor);
			return true;
		}
		else return false;
	}
	
	//public boolean deleteFrase(String titol, String autor, Frase frase) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Si existeix el document amb Titol = titol s'eliminen
		  * les aparicions de les paraules funcionals de frase i es retorna true, sino existeix
		  * no es fa res i es retorna false. </Ul>
		  * 
		*/
		/*if (this.M.existeix_Doc(titol,autor)) {
			for (String par : frase.paraules) {
				if (this.M.Doc_te_Par(titol, autor, par)) {
					this.M.delete_Par(titol, autor, par);
				}
			}
			return true;
		}
		else return false;
	}*/
	
	
	public boolean deleteParaula(String titol, String autor, String paraula) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Si exiteix el titol que conté la paraula = paraula s'elimina
		  * i es retorna true, sino no es fa res i es retorna false.</Ul>
		  * 
		*/
		if (this.M.existeix_Doc(titol, autor)) {
			if (this.M.Doc_te_Par(titol, autor, paraula)) {
				this.M.delete_Par(titol, autor, paraula);
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public PriorityQueue<Pair<String, Double> > getDocs_similars_Cos(String titol_consultat, String autor) {
		/*					TITOL,AUTOR  SEMBLANÇA*/
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna una cua de prioritats amb el titols dels documents més semblants al document
		  * amb titol = titol_consulta ordenats de més semblants a menys, tots ells calculats amb el metode de Cosinus. Si titol_consultat
		  * no exiteix es retorna una Cua de prioritats buida.</Ul>
		  * 
		*/
		if (this.M.existeix_Doc(titol_consultat,autor)) {
			PriorityQueue<Pair<String, Double> > C = this.M.Docs_Similars_Cos(titol_consultat, autor);
			return C;
		}
		else {
			PriorityQueue<Pair<String, Double> > empty = new PriorityQueue<>();
			return empty;
		}
	}
	
	public PriorityQueue<Pair<String, Double> > getDocs_similars_IDFJ(String titol_consultat, String autor) {
		/*					TITOL,AUTOR  SEMBLANÇA*/
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna una cua de prioritats amb el titols dels documents més semblants al document
		  * amb titol = titol_consulta ordenats de més semblants a menys, tots ells calculats amb el metode de TF-IDFJ. Si titol_consultat
		  * no exiteix es retorna una Cua de prioritats buida.</Ul>
		  * 
		*/
		if (this.M.existeix_Doc(titol_consultat,autor)) {
			PriorityQueue<Pair<String, Double> > C = this.M.Docs_Similars_IDFJ(titol_consultat, autor);
			return C;
		}
		else {
			PriorityQueue<Pair<String, Double> > empty = new PriorityQueue<>();
			return empty;
		}
	}
	
	public boolean printDocs_similars_Cos(String titol_consultat, String autor, int N) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Printa el titols dels documents més semblants amb calcul de COSINUS
		  * a un document amb titol = titol_consulta ordenats de més semblants a menys. Si titol_consultat
		  * no exiteix es printa que no existeix. Retorna un boolea indicant si l'operacio s'ha fet
		  * correctament amb true o fals si malament total o parcialment.</Ul>
		  * 
		*/
		if (this.M.existeix_Doc(titol_consultat, autor)) {
			PriorityQueue<Pair<String, Double> > C = this.M.Docs_Similars_Cos(titol_consultat, autor);
			if (C.isEmpty()) {
				System.out.print("No hi ha cap obra amb un grau de similitud més gran que 0.0\n"
						+"per a l'obra amb titol "+titol_consultat+" de l'autor "+autor+".\n");
				return false;
			}
			else {
				int i = 1;
				while(!C.isEmpty() && i<=N) {
					System.out.print(i+". "+(C.peek()).getFirst()+" : ");
					System.out.printf("%.16f\n",(C.peek()).getSecond());
					C.poll();
					++i;
				}
				if (i<=N) {
					i = i-1;
					System.out.print("No s'han pogut mostrar "+N+" documents donat que només hi ha "+i+"\n");
					System.out.print("Que tinguin una similitud diferent de 0 .\n");
					return false;

				}
				else return true;
			}	
		}
		else {
			System.out.print("Aquesta obra no exiteix o no ha estat escrita per aquest autor.\n");
			return false;
		}
	}
	
	public boolean printDocs_similars_IDJF(String titol_consultat, String autor, int N) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Printa el titols dels documents més semblants amb calcul de TF-IDJF
		  * a un document amb titol = titol_consulta ordenats de més semblants a menys. Si titol_consultat
		  * no exiteix es printa que no existeix. Retorna un boolea indicant si l'operacio s'ha fet
		  * correctament amb true o fals si malament total o parcialment.</Ul>
		  * 
		*/
		if (this.M.existeix_Doc(titol_consultat, autor)) {
			PriorityQueue<Pair<String, Double> > C = this.M.Docs_Similars_IDFJ(titol_consultat, autor);
			if (C.isEmpty()) {
				System.out.print("No hi ha cap obra amb un grau de similitud més gran que 0.0\n"
						+"per a l'obra amb titol "+titol_consultat+" de l'autor "+autor+".\n");
				return false;
			}
			else {
				int i = 1;
				while(!C.isEmpty() && i<=N) {
					System.out.print(i+". "+(C.peek()).getFirst()+" : ");
					System.out.printf("%.16f\n",(C.peek()).getSecond());
					C.poll();
					++i;
				}
				if (i<=N) {
					i = i-1;
					System.out.print("No s'han pogut mostrar "+N+" documents donat que només hi ha "+i+"\n");
					System.out.print("Que tinguin una similitud diferent de 0 .\n");
					return false;

				}
				else return true;
			}	
		}
		else {
			System.out.print("Aquesta obra no exiteix o no ha estat escrita per aquest autor.\n");
			return false;
		}
	}
	
	public Object[] getDocsSimilars_Cosinus(String titol_consultat, String autor, int N) {
		ArrayList<String> S = new ArrayList<>();
		if (this.M.existeix_Doc(titol_consultat, autor)) {
			PriorityQueue<Pair<String, Double> > C = this.M.Docs_Similars_Cos(titol_consultat, autor);
				int i = 1;
				while(!C.isEmpty() && i<=N) {
					String s = "";
					s = s+Integer.toString(i);
					s = s+". ";
					s = s+C.peek().getFirst();
					s = s+" : ";
					s = s+Double.toString(C.peek().getSecond());
					S.add(s);
					C.poll();
					++i;
				}	
		}
		return S.toArray();
	}
	
	public Object[] getDocsSimilars_IDFJ(String titol_consultat, String autor, int N) {
		ArrayList<String> S = new ArrayList<>();
		if (this.M.existeix_Doc(titol_consultat, autor)) {
			PriorityQueue<Pair<String, Double> > C = this.M.Docs_Similars_IDFJ(titol_consultat, autor);
			int i = 1;
			while(!C.isEmpty() && i<=N) {
					String s = "";
					s = s+Integer.toString(i);
					s = s+". ";
					s = s+C.peek().getFirst();
					s = s+" : ";
					s = s+Double.toString(C.peek().getSecond());
					S.add(s);
					C.poll();
					++i;
			}	
		}
		return S.toArray();
	}
}
