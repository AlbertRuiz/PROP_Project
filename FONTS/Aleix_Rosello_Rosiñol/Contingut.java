/**
 * Created by Aleix on 30/09/2016.
 */
import java.io.Serializable;
import java.util.ArrayList;

public class Contingut implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Frase> text;
	private String stringText;

	public Contingut() {

	}

	public Contingut(ArrayList<Frase> text) {
		this.text = text;
	}
	
	public Contingut(String text) {
		this.text = new ArrayList<>();
		this.stringText = text;
		String[] phrases = text.split("\\.");
		for (String phrase : phrases) 
			this.text.add(new Frase(phrase));
	}

	public ArrayList<Frase> getText() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna un array de les Frase del contingut. </Ul>
		  * 
		*/
		return text;
	}

	public void setText(ArrayList<Frase> text) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Canvia les Frase del contingut. </Ul>
		  * 
		*/
		this.text = text;
	}
	
	public String getString() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna el contingut en format String. </Ul>
		  * 
		*/
		return stringText;
	}
	
	public void printInfo() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Pinta per terminal les frases del contingut. </Ul>
		  * 
		*/
		for (Frase frase : text) 
			frase.printInfo();
	}

}
