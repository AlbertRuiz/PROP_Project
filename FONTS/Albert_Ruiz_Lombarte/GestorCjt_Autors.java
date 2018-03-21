import java.io.Serializable;
import java.util.ArrayList;

public class GestorCjt_Autors implements Serializable{

	private static final long serialVersionUID = 1L;
	public Cjt_Autors C;
	
	public GestorCjt_Autors() {
		this.C = new Cjt_Autors();
	}
	
	public boolean add_Autor_Titol(String autor, String titol) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Si l'autor existeix i ja ha escrit el titol
		  * amb nom titol no s'afegeix res i es retorna false.
		  * Si l'autor ja existeix i no ha escrit el titol amb nom titol s'afegeix titol a la
		  * llista de titols escrits per l'autor, i si l'autor no existia s'afegeix 
		  * l'autor amb nom autor i el titol amb nom titol que ha escrit</Ul>
		  * 
		*/
		if (this.C.existeix_autor(autor)) {
			if (this.C.autor_conte_titol(autor, titol)) return false;//retornem false si l'autor ja ha escrit aquest titol.
			else {
				this.C.add_autor_titol(autor, titol);//afegim un titol que ja ha escrit l'autor.
				return true;
			}
		}
		else {
			this.C.add_autor_titol(autor, titol);//afegim l'autor i el titol que ha escrit per primer cop.
			return true;
		}
	}
	
	public boolean existeixDoc(String titol, String autor) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna true si existeix un document amb titol = titol i l'ha escrit l'autor
		  * amb nom = autor, retorna false altrament.</Ul>
		  * 
		*/
		if (this.C.existeix_autor(autor)) {
			if (this.C.autor_conte_titol(autor, titol)) return true;
			else return false;
		}
		else return false;
	}
	
	public boolean existeixAutor(String autor) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna true si existeix l'autor amb nom autor en el sistema, false altrament.</Ul>
		  * 
		*/
		return this.C.existeix_autor(autor);
	}

	public boolean exiteixTitol(String titol) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna true si existeix el titol amb nom titol en el sistema, false altrament.</Ul>
		  * 
		*/
		return this.C.existeix_titol(titol);
	}
	
	public boolean delete_Autor(String autor) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Si l'autor existeix s'esborra del sistema i es retorna true,
		  * sino, no es fa res i retorna false.</Ul>
		  * 
		*/
		if (this.C.existeix_autor(autor)) {
			this.C.delete_autor(autor);
			return true;
		}
		else return false;
	}
	
	public boolean delete_Titol_dAutor(String titol, String autor) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Si l'autor existeix s'esborra del sistema i es retorna true,
		  * sino, no es fa res i retorna false.</Ul>
		  * 
		*/
		if (this.C.existeix_autor(autor)) {
			if (this.C.autor_conte_titol(autor, titol)) {
				this.C.delete_titol_dautor(autor, titol);
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public ArrayList<String> getAutors_prefix(String prefix) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Es retorna un ArrayList amb els noms dels autors que
		  * contenen el prefix = prefix.</Ul>
		  * 
		*/
		return this.C.get_autors_prefix(prefix);
	}
	
	public void printAutors_prefix(String prefix) {
			/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
			  * <p><b>Post:</b></p> <Ul>Es printen els noms dels autors que
			  * contenen el prefix = prefix, alfabeticament.</Ul>
			  * 
			*/
			ArrayList<String> A = this.C.get_autors_prefix(prefix);
			if (!A.isEmpty()) {
				for (String autor : A) {
					System.out.print(autor+"\n");
				}
			}
			else System.out.print("No hi ha cap autor que comenci per aquest prefix.\n");
	}
	
	public ArrayList<String> getTitols_autor(String autor) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Si l'autor existeix es retorna un ArrayList amb els titols
		  * dels documents que ha escrit, sino existeix es retorna un ArrayList buit.</Ul>
		  * 
		*/
		if (this.C.existeix_autor(autor)) {
			return this.C.get_titols_autor(autor);
		}
		else {
			ArrayList<String> empty = new ArrayList<>();
			return empty;
		}
	}
	
	public int nombreAutors() {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna el nombre d'autors que conte el sistema.</Ul>
		  * 
		*/
		return this.C.nombre_autors();
	}
	
	public void printAutors() {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Printa els noms de tots els autors que hi ha al sistema
		  *  per ordre alfabètic.</Ul>
		  * 
		*/
		this.C.printAutors();
	}
	
	public void printTitols_autor(String autor) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Si l'autor existeix treu per pantalla els titols que 
		  * ha escrit l'autor amb nom author, sino no treu res.</Ul>
		  * 
		*/
		this.C.printTitolsAutor(autor);
	}
	
	public Object[] getTitols(String autor) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Si l'autor existeix es retorna un Object[] amb els titols
		  * dels documents que ha escrit, sino existeix es retorna un Object[] buit.</Ul>
		  * 
		*/
		return this.C.get_titols_autor(autor).toArray();
	}
	
	public Object[] getAutorsP(String prefix) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul>Es retorna un Object[] amb els noms dels autors que
		  * contenen el prefix = prefix.</Ul>
		  * 
		*/
		return this.C.get_autors_prefix(prefix).toArray();
	}
}
