import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class GestorDocuments implements Serializable{
	private static final long serialVersionUID = 1L;
	public final static String TITLE_KEY = "title";
	public final static String AUTHOR_KEY = "author";
	public final static String CONTENT_KEY = "content";
	private final HashMap<String,Integer> priorityMap;
	private final HashMap<Character,Character> bracketPairs;
	private ArrayList<Document> documents;
	private HashMap<String,ArrayList<Integer>> hashAuthorTitle;
	private ArrayList<Pair<String,String>> fraseReference;
	
	public GestorDocuments() {
		this.documents = new ArrayList<>();
		this.hashAuthorTitle = new HashMap<>();
		this.priorityMap = new HashMap<>();
		priorityMap.put("(", 0);
		priorityMap.put("|", 1);
		priorityMap.put("&", 2);
		priorityMap.put("!", 3);
		this.fraseReference = new ArrayList<>();
		this.bracketPairs = new HashMap<>();
		bracketPairs.put('(', ')');
		bracketPairs.put('[', ']');
		bracketPairs.put('{', '}');
	}
	
	public String[] addDocument(String title, String author, String content) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Si l'autor ja tenia alguna obra se l'afegeix a la taula d'autors amb obres
		  *	i s'afegeix el document, en cas de que no hi sigues l'autor s'afegeix ell i el document. </Ul>
		  * 
		*/
		String lowAuthor = author.toLowerCase();
		if (hashAuthorTitle.containsKey(lowAuthor)) {
			ArrayList<Integer> listAuthor = hashAuthorTitle.get(lowAuthor);
			listAuthor.add(documents.size());
			hashAuthorTitle.replace(lowAuthor, listAuthor);
		} else {
			ArrayList<Integer> list = new ArrayList<>();
			list.add(documents.size());
			hashAuthorTitle.put(lowAuthor, list);
		}
		Document doc = new Document(title,author,content);
		documents.add(doc);
		int index = documents.indexOf(doc);
		addReferences(index, doc);
		return getContentWords(content);
	}
	
	public ArrayList<HashMap<String,String>> getDocuments() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna la llista de documents en forma de HashMap. Amb la estructura seg�ent:
		  * {
		  * 	TITLE_KEY : "titol",
		  * 	AUTHOR_KEY : "autor",
		  * 	CONTENT_KEY : "contingut"
		  * }</Ul>
		  * 
		*/
		ArrayList<HashMap<String,String>> result = new ArrayList<>();
		for (Document doc : documents) {
			HashMap<String,String> resultDoc = new HashMap<>();
			resultDoc.put(TITLE_KEY, doc.getTitle());
			resultDoc.put(AUTHOR_KEY, doc.getAuthor());
			resultDoc.put(CONTENT_KEY, doc.getContingut());
		}
		return result;
	}
	
	public HashMap<String,String> getDocument(int i) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna el document si existeix, sino retorna null. </Ul>
		  * 
		*/
		if (i < documents.size()) {
			HashMap<String,String> result = new HashMap<>();
			Document doc = documents.get(i);
			result.put(TITLE_KEY, doc.getTitle());
			result.put(AUTHOR_KEY, doc.getAuthor());
			result.put(CONTENT_KEY, doc.getContingut());
			return result;
		} else
			return null;
	}
	
	public void getAuthorTitles(String author) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Pinta per pantalla la llista de títols donat un autor. </Ul>
		  * 
		*/
		if (hashAuthorTitle.containsKey(author.toLowerCase())) {
			ArrayList<Integer> list = hashAuthorTitle.get(author.toLowerCase());
			for (Integer i : list) 
				documents.get(i).printInfo();
		} else {
			System.out.println("L'autor no te cap document");
		}
	}
	
	public String getContent(int i) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna el contingut del document i, si existeix, sino retorna un string d'error. </Ul>
		  * 
		*/
		if (i < documents.size()) {
			return documents.get(i).getContingut();
		} 
		return "El document no existeix";
	}
	
	public String getContent(String author, String title) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna el contingut del document amb titol i autor, 
		  * si existeix, sino retorna un string d'error. </Ul>
		  * 
		*/
		Document doc = getDocument(title,author);
		if (doc != null)
			return doc.getContingut();
		else 
			return "El document no existeix";
	}
	
	public String[] replaceContent(int i, String content) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Canvia el contingut del document i i retorna un array amb les paraules, 
		  * si existeix, sino retorna null. </Ul>
		  * 
		*/
		if (i < documents.size()) {
			documents.get(i).setContent(createContingut(content));
			return getContentWords(content);
		} else
			return null;
	}
	
	public String[] replaceContent(String title, String author, String content) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Canvia el contingut del document amb titol i autor i retorna un array amb les paraules, 
		  * si existeix, sino retorna null. </Ul>
		  * 
		*/
		Document doc = getDocument(title,author);
		if (doc != null) {
			int index = documents.indexOf(doc);
			doc.setContent(createContingut(content));
			documents.set(index, doc);
			return getContentWords(content);
		} else
			return null;
	}
	
	public void printDocument(int i) {
		/** <p><b>Pre:</b></p>  <Ul>i �s un document v�lid</Ul>
		  * <p><b>Post:</b></p> <Ul>Canvia el contingut del document amb titol i autor i retorna un array amb les paraules, 
		  * si existeix, sino retorna null. </Ul>
		  * 
		*/
		documents.get(i).printInfo();
	}
	
	public void printDocumentsList() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Pinta la informaci� dels documents per terminal. </Ul>
		  * 
		*/
		System.out.println("Documents:\n");
		for (Document doc : documents){ 
			System.out.print(documents.indexOf(doc)+1 + ". ");
			doc.printInfo();
		}
	}
	
	public Object[] getDocumentsList() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna el llistat de documents del programa.</Ul>
		  * 
		*/
		ArrayList<String> a = new ArrayList<>();
		for (Document doc : documents) {
			a.add(doc.getInfo());
		}
		return a.toArray();
	}
	
	public boolean deleteDocument(int i) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Si el document i es eliminat satisfactoriament retorna true, en cas contrari false. </Ul>
		  * 
		*/
		Document doc = documents.remove(i);
		if (doc != null) {
			hashAuthorTitle.get(doc.getAuthor().toLowerCase()).remove(Integer.valueOf(i));
			updateHashAuthor();
			deleteReferences(i);
		}
		return doc == null ? false : true;
	}
	
	public boolean deleteDocument(String title, String author) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Si el document amb autor i titol es eliminat satisfactoriament 
		  * retorna true, en cas contrari false. </Ul>
		  * 
		*/
		Document doc = getDocument(title,author);
		int i = documents.indexOf(doc);
		if (doc != null && documents.remove(doc)) {
			updateHashAuthor();
			deleteReferences(i);
			return true;
		} else 
			return false;
	}
	
	public int getDocumentSize() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna el nombre de documents al sistema. </Ul>
		  * 
		*/
		return documents.size();
	}
	
	public boolean existsDocument(String title, String author) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna si existeix un document donat un titol i un autor.</Ul>
		  * 
		*/
		if (getDocument(title,author) != null)
			return true;
		else
			return false;
	}
	
	public boolean existsDocument(int i) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna si existeix un document donat un numero.</Ul>
		  * 
		*/
		return i >= 0 && i < documents.size();
	}
	
	public ArrayList<String> evaluateExpresion(String e) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna la llista de documents que compleixen l'expressió booleana.</Ul>
		  * 
		*/
		if (isBalanced(e)) {
			// Construir arbre amb l'expressio passada
	        ExpresionTree expTree = new ExpresionTree(e);
	        
	        // Retorna les frases que ho compleixen
	        ArrayList<Pair<String, String>> frases = expTree.evaluate(expTree.getRoot(), fraseReference);
	        
	        ArrayList<String> rep = new ArrayList<>();
	        for (Pair<String,String> p : frases)
	        	if (!rep.contains(p.getFirst()))
	        		rep.add(p.getFirst());
	        
	        // Agafa l'id del document per tal de fer-ne un set amb tots ells
	        ArrayList<String> result = new ArrayList<>();
	        for (String n : rep) {
	        	Document doc = documents.get(Integer.valueOf(n));
	        	result.add(doc.getTitle() + " - " + doc.getAuthor());
	        }
	        return result;
		} else {
			return null;
		}
    }
	
	private boolean isBalanced(String str) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna si l'expressió booleana està ben parentitzada.</Ul>
		  * 
		*/
		Stack<Character> stack = new Stack<Character>();
		
	    char c;
	    for(int i=0; i < str.length(); i++) {
	        c = str.charAt(i);

	        if(c == '(')
	            stack.push(c);
	        else if(c == '{')
	            stack.push(c);
	        else if(c == ')')
	            if(stack.empty())
	                return false;
	            else if(stack.peek() == '(')
	                stack.pop();
	            else
	                return false;
	        else if(c == '}')
	            if(stack.empty())
	                return false;
	            else if(stack.peek() == '{')
	                stack.pop();
	            else
	                return false;
	    }
	    
	    return stack.empty();
    }
	
	private Document getDocument(String title, String author) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna el document amb titol i autor si existeix, en cas contrari retorna null. </Ul>
		  * 
		*/
		String lowAuthor = author.toLowerCase();
		String lowTitle = title.toLowerCase();
		if (hashAuthorTitle.containsKey(lowAuthor)) {
			ArrayList<Integer> authorTitles = hashAuthorTitle.get(lowAuthor);
			for (Integer i : authorTitles) {
				Document doc = documents.get(i);
				if (doc.getTitle().toLowerCase().equals(lowTitle)) 
					return doc;
			}
		}
		return null;
	}
	
	private Contingut createContingut(String content) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna un Contingut donat un string. </Ul>
		  * 
		*/
		return new Contingut(content);
	}
	
	private String[] getContentWords(String content) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna un array de totes les paraules funcionals i no funcionals sense signes de puntuaci�. </Ul>
		  * 
		*/
		return content.replaceAll("\\p{P}", "").toLowerCase().split("\\s+");
	}
	
	private void updateHashAuthor() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Modifica l'array de de referencies de la llista d'autors amb els seus
		  * titols.</Ul>
		  * 
		*/
		for (String author : hashAuthorTitle.keySet()) {
			ArrayList<Integer> list = new ArrayList<>();
			for (int i = 0; i < documents.size(); ++i) 
				if (documents.get(i).getAuthor().toLowerCase().equals(author))
					list.add(i);
			hashAuthorTitle.replace(author, list);
		}
	}
	
	private void addReferences(int index, Document doc) {
		for (Frase f : doc.getContingutF()) 
			fraseReference.add(new Pair<String,String>(String.valueOf(index), f.getString()));
	}
	
	private void deleteReferences(int i) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Elimina les frases del document eliminat.</Ul>
		  * 
		*/
		ArrayList<Pair<String,String>> copy = (ArrayList<Pair<String, String>>) fraseReference.clone();
		for (int j = 0; j < fraseReference.size(); ++j) 
			if (fraseReference.get(j).getFirst().equals(i))
				copy.remove(j);
		fraseReference = copy;
	}
	
	private void updateReferences(int i) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Fa update de les referencies amb el nou contingut d'un document.</Ul>
		  * 
		*/
		deleteReferences(i);
		addReferences(i,documents.get(i));
	}
	
}
