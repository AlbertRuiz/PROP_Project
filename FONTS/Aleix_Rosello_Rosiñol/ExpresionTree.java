import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class ExpresionTree {
	
	private final HashMap<String,Integer> priorityMap;
	private Node root;
	
	ExpresionTree() {
		this.priorityMap = new HashMap<>();
		priorityMap.put("(", 0);
		priorityMap.put("|", 1);
		priorityMap.put("&", 2);
		priorityMap.put("!", 3);
		this.root = new Node();
	}
	
	ExpresionTree(String exp) {
		this.priorityMap = new HashMap<>();
		priorityMap.put("(", 0);
		priorityMap.put("|", 1);
		priorityMap.put("&", 2);
		priorityMap.put("!", 3);
		evaluateExpression(exp);
	}
	
	public Node getRoot() {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Retorna l'arrel de l'arbre</Ul>
		  * 
		*/
		return root;
	}
    
    public ArrayList<Pair<String,String>> evaluate(Node n, ArrayList<Pair<String,String>> references) {
    	/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Evalua l'arbre creat amb l'expressio donada amb la llista de frases
		  * que composen el contingut del documents del programa. </Ul>
		  * 
		*/
        if (n.isLeaf())
            return searchValue(n.getValue(), references);
        else {
            ArrayList<Pair<String,String> > left = new ArrayList<>();
            ArrayList<Pair<String,String> > right = new ArrayList<>();
            if (n.getLeftNode() != null) {
            	left = evaluate(n.getLeftNode(), references);
            }
            if (n.getRightNode() != null) {
            	right = evaluate(n.getRightNode(), references);
            }

            ArrayList<Pair<String,String> > m = new ArrayList<>();
            if (n.getValue().equals("&")) {
                m = intersection(left, right);
            } else if (n.getValue().equals("!")) {
                if (n.getLeftNode() != null) {
                    m = not(left, references);
                } else {
                    m = not(right, references);
                }
            } else if (n.getValue().equals("|")) {
                m = union(left, right);
            }
            
            return m;
        } 
    }
    
    private Node buildTree(ArrayList<String> inorder, ArrayList<String> postorder, ArrayList<Integer> ref) {
    	/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Genera l'arbre de l'expressió</Ul>
		  * 
		*/
        return buildTreeImm(inorder, postorder, 0, inorder.size() - 1, 0, postorder.size() - 1, ref);
    }
    
    private void evaluateExpression(String e) {
    	/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Reconstrueix la expressió canviant el llenguatge natural de inordre
		  * a postordre per tal de crear l'arbre. Finalment retorna l'arrel de l'arbre generat.</Ul>
		  * 
		*/
    	String expresion = parseExpresion(e);
        
        StringTokenizer st = new StringTokenizer(expresion, "&|!()", true);
        ArrayList<String> inorder = new ArrayList<>();
        Stack<String> opst = new Stack<>();
        Stack<Integer> pos = new Stack<>();
        ArrayList<String> postorder = new ArrayList<>();
        ArrayList<Integer> ref = new ArrayList<>();
        
        // Reconstruir l'expressio amb postordre
        int k = 0;
        while (st.hasMoreTokens()) {
            String t = st.nextToken();
            if (t.equals("&") || t.equals("|") || t.equals("!")) {
                inorder.add(t);
                while (!opst.isEmpty() && (priorityMap.get(opst.peek()) >= priorityMap.get(t))) 
                    if (!opst.peek().equals("(")) {
                        postorder.add(opst.pop());
                        if (!pos.isEmpty()) 
                            ref.add(pos.pop());
                    }
                opst.push(t);
                pos.push(k);
            } else if (t.equals("(")) {
                opst.push(t);
                k--;
            } else if (t.equals(")")) {
                String aux;
                while (!opst.isEmpty() && !opst.peek().equals("(")) {
                    aux = opst.peek();
                    if (!aux.equals("(")) {
                        postorder.add(aux);
                        if (!pos.isEmpty()) 
                        	ref.add(pos.pop());
                    }
                    opst.pop();
                }
                if (opst.peek().equals("(")) 
                	opst.pop();
                k--;
            } else {
                postorder.add(t);
                inorder.add(t);
                ref.add(k);
            }
            k++;  
        }
        
        while (!opst.isEmpty()) {
            if (!opst.peek().equals("(")) {
                postorder.add((String) opst.peek());
                if (!pos.isEmpty()) ref.add(pos.pop());
            }
            opst.pop();
        }
        
        this.root = buildTree(inorder, postorder, ref);
        
    }
    
	private String parseExpresion(String exp) {
		/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Parseja l'expressió per tal de poder tractarla en la reconversió de inordre
		  * a postordre. </Ul>
		  * 
		*/
        for (int i = 0; i < exp.length(); ++i) {
            if (exp.charAt(i) == '{') {
                ++i;
                int ini = i;
                while (exp.charAt(i) != '}') ++i;
                String s = exp.substring(ini, i);
                s = s.replace(" ", " & ");
                String aux1 = exp.substring(0, ini-1);
                String aux2 = exp.substring(i+1 <= exp.length() ? i+1 : exp.length(), exp.length());
                exp = aux1 + s + aux2;
            } else if (exp.charAt(i) == '"') {
                ++i;
                while (exp.charAt(i) != '"') {
                    if (exp.charAt(i) == ' ') 
                    	exp = exp.substring(0, i) + '\0' + exp.substring(i + 1);
                    ++i;
                } 
            }
        }
        exp = exp.replace(" ", "");
        exp = exp.replace('\0', ' ');
        exp = exp.replace("\"" , "");
        return exp;
    } 
    
    private ArrayList<Pair<String,String>> intersection(ArrayList<Pair<String,String>> left, ArrayList<Pair<String,String>> right) {
    	/** <p><b>Pre:</b></p>  <Ul>Dos conjunts de frases vàlids.</Ul>
		  * <p><b>Post:</b></p> <Ul>Genera la intersecció de dos conjunts de frases.</Ul>
		  * 
		*/
    	ArrayList<Pair<String, String>> m = new ArrayList<>();
        for (Pair<String,String> p : left) 
            if (right.contains(p)) 
                m.add(p);
        
        return m;
    }
    
    private ArrayList<Pair<String,String>> union(ArrayList<Pair<String,String>> left, ArrayList<Pair<String,String>> right) {
    	/** <p><b>Pre:</b></p>  <Ul>Dos conjunts de frases vàlids.</Ul>
		  * <p><b>Post:</b></p> <Ul>Genera la unió de dos conjunts de frases.</Ul>
		  * 
		*/
    	Set<Pair<String, String> > set = new HashSet<>();
        set.addAll(left); set.addAll(right);
        ArrayList<Pair<String,String> > m = new ArrayList<Pair<String,String>>(set);
        
        return m;
    }
    
    private ArrayList<Pair<String,String>> not(ArrayList<Pair<String,String>> list, ArrayList<Pair<String,String>> references) {
    	/** <p><b>Pre:</b></p>  <Ul>Un conjunt de frases vàlid.</Ul>
		  * <p><b>Post:</b></p> <Ul>Genera la negació d'un conjunt de frases.</Ul>
		  * 
		*/
    	ArrayList<Pair<String,String> > m = new ArrayList<>();   
        for (Pair<String,String> p: references)
            if (!list.contains(p)) 
                m.add(p);
        
        return m;
    }
    
    private ArrayList<Pair<String,String>> searchValue(String value, ArrayList<Pair<String,String>> references) {
    	/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Busca el valor donat en una llista de frases i retorna les frases que
		  * el contenen.</Ul>
		  * 
		*/
    	ArrayList<Pair<String,String>> fr = (ArrayList<Pair<String,String>>) references.clone();
    	String[] splitedVal = value.split("\\b+");
    	int k = 0;
    	if (Arrays.asList(splitedVal).size() > 1) {
    		for (int i = 0; i < references.size(); ++i) 
        		if (references.get(i).getSecond() == null || !references.get(i).getSecond().toLowerCase().contains(value.toLowerCase())) {
        			fr.remove(i-k);
    				++k;
        		}
    	} else {
    		for (int i = 0; i < references.size(); ++i) 
        		if (references.get(i).getSecond() == null || !Arrays.asList(references.get(i).getSecond().toLowerCase().split("\\b+")).contains(value.toLowerCase())) { 
        			fr.remove(i-k);
        			++k;
        		}
    	}
    	
    	return fr;
    }
    
    private Node buildTreeImm(ArrayList<String> inorder, ArrayList<String> postorder, int inorder_ini, int inorder_end, int postorder_ini, int postorder_end, ArrayList<Integer> ref) {
    	/** <p><b>Pre:</b></p>  <Ul>-</Ul>
		  * <p><b>Post:</b></p> <Ul>Genera l'arbre per immersió.</Ul>
		  * 
		*/
    	if (inorder_ini > inorder_end || postorder_ini > postorder_end) 
        	return null;

        String valor_root = postorder.get(postorder_end);
        Node root = new Node(valor_root);
        if (inorder_ini == inorder_end) 
        	return root;
                
        int index_in = ref.get(postorder_end);
        root.setLeftNode(buildTreeImm(inorder, postorder, inorder_ini, index_in - 1, postorder_ini, postorder_ini + index_in - (inorder_ini + 1), ref));
        root.setRightNode(buildTreeImm(inorder, postorder, index_in + 1, inorder_end, postorder_ini + index_in - inorder_ini, postorder_end - 1, ref));
        
        return root;
    }
	
}
