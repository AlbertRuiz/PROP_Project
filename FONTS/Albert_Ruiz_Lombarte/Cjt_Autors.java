import java.io.Serializable;
import java.util.*;




public class Cjt_Autors implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<String> cjt_autors;
	public HashMap<String,ArrayList<String> > A_titol;
	
	public Cjt_Autors() {
		this.cjt_autors = new ArrayList<String>();
		this.A_titol = new HashMap<>();
	}
	
	
	public void add_autor_titol(String autor, String titol) {
		/** <p><b>Pre:</b></p>  <Ul>titol no es un dels titols que ja escrit l'autor = autor</Ul>
		  * <p><b>Post:</b></p> <Ul>Si l'autor no exiteix s'afegeix l'autor i el titol del document que ha escrit
		  * si ja existeix l'autor s'afegeix a la seva llista de titols que ha escrit,
		  * i si l'autor ja havia escrit aquest document amb titol = titol no s'afegeix res.</Ul>
		  * 
		*/
		if (this.A_titol.containsKey(autor)){//Si l'autor ja hi es al conjunt
			int i = Collections.binarySearch((this.A_titol).get(autor), titol);
	        if (i < 0) i = ~i;
	        if (i < (this.A_titol).get(autor).size()) {
	        	if (!this.A_titol.get(autor).get(i).matches(titol)) {
	        		(this.A_titol.get(autor)).add(i,titol);
	        	}
	        }
	        else {
	        	(this.A_titol).get(autor).add(titol);
	        }
		}
		else {//Si l'autor no hi es al conjunt s'afegeix amb el titol que ha escrit (parametre)
			ArrayList<String> a = new ArrayList<>();
			a.add(titol);
			(this.A_titol).put(autor,a);
			int index = Collections.binarySearch(this.cjt_autors, autor);
	        if (index < 0) index = ~index;
	        if (index < (this.cjt_autors).size()) (this.cjt_autors).add(index, autor);
	        else (this.cjt_autors).add(autor);
		}
	}
	
	public void delete_titol_dautor(String autor, String titol) {
		/** <p><b>Pre:</b></p>  <Ul>L'autor amb nom = autor i el document amb titol = titol han d'existir</Ul>
		  * <p><b>Post:</b></p> <Ul>El titol del autor es esborrat, si a l'autor només li quedava un titol l'autor es esborrat del conjunt.</Ul>
		  * 
		*/
		if (this.A_titol.containsKey(autor)) {
			if (this.A_titol.get(autor).size() == 1) {
				(this.A_titol).remove(autor);
				int i = Collections.binarySearch(this.cjt_autors, autor);
		        if (i < 0) i = ~i;
				(this.cjt_autors).remove(i);
			}
			else {
				int index = Collections.binarySearch((this.A_titol).get(autor), titol);
				if (index < 0) index = ~index;
				(this.A_titol).get(autor).remove(index);
			}
		}
	}
	
	public boolean existeix_titol(String titol) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna true si algun autor te un titol amb nom = titol, 
		  * retorna false altrament.</Ul>
		  * 
		*/
		for (String a : (this.A_titol).keySet()) {
			int index = Collections.binarySearch((this.A_titol).get(a), titol);
			if (index < 0) index = ~index;
			if (index < (this.A_titol).get(a).size()) {
				if ((this.A_titol).get(a).get(index).matches(titol)) return true;
			}
		}
		return false;
	}
	
	public boolean existeix_autor(String autor) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna true si l'autor amb nom = autor exiteix al conjunt,
		  * retorna false altrament.</Ul>
		  * 
		*/
		return this.A_titol.containsKey(autor);
	}
	
	public boolean autor_conte_titol(String autor, String titol) {
		/** <p><b>Pre:</b></p>  <Ul>L'autor amb nom = autor ha d'existir.</Ul>
		  * <p><b>Post:</b></p> <Ul>retorna true si l'autor amb nom = autor conté el titol = titol, retorna false altrament.</Ul>
		  * 
		*/
		int index = Collections.binarySearch((this.A_titol).get(autor), titol);
		if (index < 0) index = ~index;
		if (index < (this.A_titol).get(autor).size()) {
			if ((this.A_titol).get(autor).get(index).matches(titol)) return true;
			else return false;
		}
		else return false;
	}
	
	public void delete_autor(String autor) {
		/** <p><b>Pre:</b></p>  <Ul>L'autor amb nom = autor ha d'existir</Ul>
		  * <p><b>Post:</b></p> <Ul>L'autor amb nom = autor es esborrat del conjunt amb totes les seves obres.</Ul>
		  * 
		*/
		if ((this.A_titol).containsKey(autor)) {
			(this.A_titol).remove(autor);
			int i = Collections.binarySearch(this.cjt_autors, autor);
	        if (i < 0) i = ~i;
			(this.cjt_autors).remove(i);
		}
	}
	
	public ArrayList<String> get_autors_prefix(String prefix){
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna un ArrayList amb tots els autors que tenen com a prefix l'String passat com a parametre.</Ul>
		  * 
		*/
		ArrayList<String> R = new ArrayList<>();
		int i = Collections.binarySearch(this.cjt_autors, prefix);
		if (i < 0) i = ~i;
		while (i < this.cjt_autors.size() && prefix.compareTo((this.cjt_autors).get(i)) <= 0) {
			if ((this.cjt_autors.get(i)).startsWith(prefix)) {
				R.add(this.cjt_autors.get(i));
			}
			++i;
		}
		return R;
	}
	
	public int nombre_autors() {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna el nombre d'autors que te el conjunt.</Ul>
		  * 
		*/
		return this.cjt_autors.size();
	}
	
	public ArrayList<String> get_titols_autor(String autor) {
		/** <p><b>Pre:</b></p>  <Ul>L'autor amb nom = autor ha d'existir.</Ul>
		  * <p><b>Post:</b></p> <Ul>Es retorna un ArrayList amb els titols que ha escrit l'autor amb nom = autor,
		  * en ordre alfabètic.</Ul>
		  * 
		*/
		return (this.A_titol).get(autor);
	}
	
	public void printAutors() {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Treu per pantalla la llista de tots els autors del conjunt en ordre alfabètic.</Ul>
		  * 
		*/
		for (int i=0; i<this.cjt_autors.size(); i++) {
			System.out.println(this.cjt_autors.get(i)+"\n");
		}
	}
	
	public void printTitolsAutor(String autor) {
		/** <p><b>Pre:</b></p>  <Ul>L'autor amb nom = autor exiteix.</Ul>
		  * <p><b>Post:</b></p> <Ul>Treu per pantalla els titols de l'autor amb nom = autor.</Ul>
		  * 
		*/
		if ((this.A_titol).containsKey(autor)) {
			for (int i=0; i<(this.A_titol).get(autor).size(); i++) {
				System.out.println((this.A_titol).get(autor).get(i));
			}
		}
	}
	
}
