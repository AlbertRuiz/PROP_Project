import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Aleix on 30/09/2016.
 */
public class Document implements Serializable{
	private static final long serialVersionUID = 1L;
	Contingut contingut;
	Frase title;
	Frase author;
	
	public Document() {
		this.contingut = new Contingut();
		this.title = new Frase();
		this.author = new Frase();
	}
	
	public Document(String title, String author, String content) {
		this.contingut = new Contingut(content);
		this.title = new Frase(title);
		this.author = new Frase(author);
	}
	
	public String getContingut() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna el contingut del document en forma de String. </Ul>
		  * 
		*/
		return contingut.getString();
	}
	
	public ArrayList<Frase> getContingutF() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna un array de les Frase del contingut. </Ul>
		  * 
		*/
		return contingut.getText();
	}
	
	public String getTitle() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna el titol del document en forma de String. </Ul>
		  * 
		*/
		return title.getString();
	}
	
	public String getAuthor() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna l'autor del document en forma de String. </Ul>
		  * 
		*/
		return author.getString();
	}
	
	public void setContent(Contingut contingut) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Modifica el contingut del document. </Ul>
		  * 
		*/
		this.contingut = contingut;
	}
	
	public void printInfo() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Pinta per pantalla el titol i l'autor del document en el format seg�ent:
		  * Titol - Autor </Ul>
		  * 
		*/
		title.printInfo();
		System.out.printf(" - ");
		author.printInfo();
		System.out.printf("\n");
	}
	
	public String getInfo() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna titol i l'autor del document en el format seg�ent:
		  * Titol - Autor </Ul>
		  * 
		*/
		String doc = title.getString();
		doc = doc+" - ";
		doc = doc+author.getString();
		return doc;	
	}
	
}
