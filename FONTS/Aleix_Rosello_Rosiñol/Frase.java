import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Aleix on 30/09/2016.
 */
public class Frase implements Serializable{

	private static final long serialVersionUID = 1L;
	ArrayList<String> paraules;
	
	public Frase() {
		this.paraules = new ArrayList<>();
	}
	
	public Frase(String text) {
		String[] splited = text.split("\\s+");
		this.paraules = new ArrayList<>(Arrays.asList(splited));
	}
	
	public String getString() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna la frase en forma de string. </Ul>
		  * 
		*/
		if (paraules.size() > 0) {
			String result = paraules.get(0);
			for (int i = 1; i < paraules.size(); ++i) 
				result += " " + paraules.get(i);
			return result;
		} else 
			return null;
	}
	
	public void printInfo() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Pinta per terminal la frase. </Ul>
		  * 
		*/
		if (paraules.size() > 0) {
			System.out.printf(paraules.get(0));
			for (int i = 1; i < paraules.size(); i++) {
				String word = paraules.get(i);
				System.out.printf(!word.equals(',') || !word.equals(';') ? " " + word : word);
			}
		}			
	}

	
}
