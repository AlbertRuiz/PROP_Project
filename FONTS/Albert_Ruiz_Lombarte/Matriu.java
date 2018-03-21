import java.io.Serializable;
import java.util.*;



public class Matriu implements Serializable{
	private static final long serialVersionUID = 1L;
	// S'enmmagatzema la matriu esparsa en aquesta estructura on nomes té valors diferents a 0.
    protected HashMap<String, HashMap<String, Integer>> M;//Matriu pel cosinus
    /*						titol, autor            paraula, aparicions*/
    protected HashMap<String, HashMap<String, Double>> MatriuTF_IDF;//Matriu per TF_IDF
    protected TreeMap<String, TreeSet<String> > IDFJ;//Matriu per en quins documents esta cada paraula
    
    
    public Matriu() {
    	/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul> S'inicialitza una matriu esparsa buida. </Ul>
		  * 
		*/
        this.M = new HashMap<>(0);
        this.MatriuTF_IDF = new HashMap<>(0);
        this.IDFJ = new TreeMap<>();
    }
    
    public boolean existeix_Par(String paraula) {
    	/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna true si algun document del sistema conte la paraula no funcional paraula, retorna false altrament.</Ul>
		  * 
		*/
    	for (String p : this.M.keySet()) {
    		HashMap<String, Integer> doc = this.M.get(p);
    		if (doc.containsKey(paraula)) return true;
    	}
    	return false;
    }
    
    public void add_Doc_buit(String titol, String autor) {
    	/** <p><b>Pre:</b></p>  <Ul>L'autor = autor no havia escrit la obra amb titol = titol.</Ul>
		 * <p><b>Post:</b></p> <Ul> S'afegeix un document nou identificat per titol i el seu
		 * autor, sense cap paraula. </Ul>
		  * 
		*/
    	HashMap<String, Integer> h = new HashMap<>();
    	HashMap<String, Double> i = new HashMap<>();
    	this.M.put(titol+", "+autor,h);
    	this.MatriuTF_IDF.put(titol+", "+autor,i);
    }
    
    
    public void add_Paraula(String titol, String autor, String paraula) {
    	/** <p><b>Pre:</b></p>  <Ul>L'obra amb Titol = titol ha d'existir i l'ha d'haver escrit
    	 * l'Autor = autor i la Paraula = paraula ha de ser funcional.</Ul>
		 * <p><b>Post:</b></p> <Ul> Si ja existia la Paraula se li afeigeix una apraició i
		 * si no existia s'afegeix amb aparicions = 1. </Ul>
		  * 
		*/
    	
    	if ((this.M.get(titol+", "+autor)).containsKey(paraula)) {
    		(this.M.get(titol+", "+autor)).replace(paraula, (this.M.get(titol+", "+autor)).get(paraula)+1);
    	}
    	else {
    		(this.M.get(titol+", "+autor)).put(paraula, 1);
    		if (this.IDFJ.containsKey(paraula)) {
    			this.IDFJ.get(paraula).add(titol+", "+autor);
    		}
    		else {
    			TreeSet<String> s = new TreeSet<>();
    			s.add(titol+", "+autor);
    			this.IDFJ.put(paraula, s);
    		}
     	}
    }
    
    public void delete_Doc(String titol, String autor) {
    	/** <p><b>Pre:</b></p>  <Ul>El document amb Titol = titol ha d'existir a la Matriu,
    	 * i ha d'haver estat escrit per l'Autor = autor.</Ul>
		  * <p><b>Post:</b></p> <Ul> El document s'esborra de la Matriu. </Ul>
		  * 
		*/
    	
    	for (String doc : this.M.keySet()) {
    		for (String par :this.M.get(doc).keySet()) {
    			if (this.IDFJ.get(par).contains(titol+", "+autor)) {
    				if (this.IDFJ.get(par).size() == 1) {
    					this.IDFJ.remove(par);
    				}
    				else {
    					this.IDFJ.get(par).remove(titol+", "+autor);
    				}
    			}
    		}
    	}
    	this.M.remove(titol+", "+autor);
    }
    
    public void delete_Par(String titol, String autor, String paraula) {
    	/** <p><b>Pre:</b></p>  <Ul>La Paraula = paraula ha d'existir en el document amb Titol = titol,
    	 * que ha escrit l'Autor = autor.</Ul>
		  * <p><b>Post:</b></p> <Ul> La paraula s'esborra si només te una aparicio i sino se li treu una aparicio </Ul>
		  * 
		*/
    	if ((this.M.get(titol+", "+autor)).get(paraula) == 1) {
    		(this.M.get(titol+", "+autor)).remove(paraula);
    		(this.IDFJ.get(paraula)).remove(titol+", "+autor);
   		}
   		else {
   			(this.M.get(titol+", "+autor)).replace(paraula, (this.M.get(titol+", "+autor)).get(paraula)-1);
   		}
    }
    
    
    public boolean existeix_Doc(String titol, String autor) {
    	/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna true si exiteix el document amb Titol = titol i Autor = autor, retorna false altrament.</Ul>
		  * 
		*/
    return this.M.containsKey(titol+", "+autor);
    }
    
    
    public boolean Doc_te_Par(String titol, String autor, String paraula) {
    	/** <p><b>Pre:</b></p>  <Ul>El document amb Titol = titol ha d'existir i ha d'haver
    	 * estat escrit per l'Autor = autor.</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna true si la paraula exiteix en el document amb titol = titol, false altrament</Ul>
		  * 
		*/
    	return (this.M.get(titol+", "+autor)).containsKey(paraula);
    }
    
    public PriorityQueue<Pair <String, Double> > Docs_Similars_Cos(String titol, String autor) {
    	/** <p><b>Pre:</b></p>  <Ul>El paràmetre titol ha de ser el Titol d'algun dels documents de la matriu,
    	 * i ha d'haver estat escrit per l'Autor = autor.</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna una cua de prioritats amb el titols i els autors dels documents més semblants al document
		  * amb Titol = titol i Autor = autor, ordenats de més semblants a menys.</Ul>
		  * 
		*/
    	Comparator<Pair<String, Double> > comparator = new PairComparator();
    	PriorityQueue<Pair<String, Double> > Q = new PriorityQueue<>(comparator);//Cua de prioritats on insertarem els elements.
    	HashMap<String, Integer> par_cons = this.M.get(titol+", "+autor);//Paraules del document que es vol comparar amb els altres
    	int p_escalar, SumQuad_cons, SumQuad_comp;
    	p_escalar = SumQuad_cons = SumQuad_comp = 0;
    	for (String par : par_cons.keySet()) {
			SumQuad_cons += Math.pow(par_cons.get(par), 2);
    	}
    	double modul_cons = Math.sqrt(SumQuad_cons);
    	for (String P : this.M.keySet()) {//for per cada document de la Matriu
    		if (!P.matches(titol+", "+autor)) {
    			p_escalar = 0;
    			SumQuad_comp = 0;
    			HashMap<String, Integer> par_comp = this.M.get(P);//Document de la Matriu amb el que compararem el document consultat
    			for (String paraula : par_comp.keySet()) {//for per cada paraula del document consultat
    				if (par_cons.containsKey(paraula)) {//si el document que comparem te alguna paraula del consultat
    					p_escalar += par_cons.get(paraula)*par_comp.get(paraula);
    				}
    				SumQuad_comp += Math.pow(par_comp.get(paraula), 2);
    			}
    			if (p_escalar != 0) {
    				double modul_comp = Math.sqrt(SumQuad_comp);
    				double cosinus = p_escalar/(modul_cons*modul_comp);
    				Pair<String,Double> result = new Pair<String,Double>(P,cosinus);
    				Q.add(result);
    			}
    		}
    	}
    	return Q;
    }
    
    public PriorityQueue<Pair <String, Double> > Docs_Similars_IDFJ(String titol, String autor) {
    	calculaIDFJ(titol+", "+autor);
    	Comparator<Pair<String, Double> > comparator = new PairComparator();
    	PriorityQueue<Pair<String, Double> > Q = new PriorityQueue<>(comparator);
    	HashMap<String, Double> par_cons = this.MatriuTF_IDF.get(titol+", "+autor);
    	double p_escalar;
    	for (String doc : this.MatriuTF_IDF.keySet()) {
    		if (!doc.matches(titol+", "+autor)) {
    			p_escalar = 0.0;
    			HashMap<String, Double> par_comp = this.MatriuTF_IDF.get(doc);
    			for (String par : par_comp.keySet()) {
    				if (par_cons.containsKey(par)) {
    					p_escalar += par_cons.get(par)*par_comp.get(par);
    				}
    			}
    			Pair<String,Double> result = new Pair<String,Double>(doc,p_escalar);
    			Q.add(result);
    		}
    	}
    	return Q;
    }
    
    private void calculaIDFJ(String document) {
    	for (String doc : this.M.keySet()) {
    		HashMap<String,Double> idfj = new HashMap<>();
    		for (String par : this.M.get(doc).keySet()) {
    			if (this.IDFJ.get(par).contains(document)) { 
    				double r = Math.log10(((double)(this.M.size()-1))/((double)(this.IDFJ.get(par).size()-1)));
    				idfj.put(par,r);
    				this.MatriuTF_IDF.put(doc, idfj);
    			}
    			else {
    				double r = Math.log10(((double)(this.M.size()))/((double)(this.IDFJ.get(par).size())));
    				idfj.put(par,r);
    				this.MatriuTF_IDF.put(doc, idfj);
    			}
    		}
    	}
    }
    
    
    
    public void printIDFJ() {
    	//calculaIDFJ();
    	for (String doc : this.MatriuTF_IDF.keySet()) {
    		System.out.print(doc+" :");
    		for (String par : this.MatriuTF_IDF.get(doc).keySet()) {
    			System.out.print(" "+par+"("+this.MatriuTF_IDF.get(doc).get(par)+")");
    		}
    		System.out.print("\n");

    	}
    }
}