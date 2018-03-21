import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Binario {

	public Binario() {
		// TODO Auto-generated constructor stub
	}
	
	public void GuardarCtrlDomini(CtrlDomini CD, String ruta) {
		try {
			FileOutputStream f = new FileOutputStream(ruta);
			ObjectOutputStream exp_cont = new ObjectOutputStream(f);
			exp_cont.writeObject(CD);
			exp_cont.close();
		}catch(Exception e) {
			System.out.println("No s'ha pogut serialitzar l'objecte");
		}
	}
	
	public CtrlDomini ObtenirCtrlDomini(String ruta) {
		CtrlDomini CD = new CtrlDomini();
		try{
			FileInputStream f = new FileInputStream(ruta);
			ObjectInputStream imp_cont = new ObjectInputStream(f);
			CD = (CtrlDomini)imp_cont.readObject();
			imp_cont.close();
		}catch(Exception e){
			System.out.println("No s'ha pogut deserialitzar l'objecte");
		}
		return CD;
	}
	
	public void GuardarGCjt_Autors(GestorCjt_Autors gestCjtA, String ruta) {
		try {
			FileOutputStream f = new FileOutputStream(ruta);
			ObjectOutputStream exp_cont = new ObjectOutputStream(f);
			exp_cont.writeObject(gestCjtA);
			exp_cont.close();
		}catch(Exception e) {
			System.out.println("No s'ha pogut serialitzar l'objecte");
		}
	}
	
	public GestorCjt_Autors ObtenirGCjt_Autors(String ruta) {
		GestorCjt_Autors gestCjtA = new GestorCjt_Autors();
		try{
			FileInputStream f = new FileInputStream(ruta);
			ObjectInputStream imp_cont = new ObjectInputStream(f);
			gestCjtA = (GestorCjt_Autors)imp_cont.readObject();
			imp_cont.close();
		}catch(Exception e){
			System.out.println("No s'ha pogut deserialitzar l'objecte");
		}
		return gestCjtA;
	}
	
	public void GuardarGDocuments(GestorDocuments gestDocs, String ruta) {
		try {
			FileOutputStream f = new FileOutputStream(ruta);
			ObjectOutputStream exp_cont = new ObjectOutputStream(f);
			exp_cont.writeObject(gestDocs);
			exp_cont.close();
		}catch(Exception e) {
			System.out.println("No s'ha pogut serialitzar l'objecte");
		}
	}
	
	public GestorDocuments ObtenirGDocuments(String ruta) {
		GestorDocuments gestDocs = new GestorDocuments();
		try{
			FileInputStream f = new FileInputStream(ruta);
			ObjectInputStream imp_cont = new ObjectInputStream(f);
			gestDocs = (GestorDocuments)imp_cont.readObject();
			imp_cont.close();
		}catch(Exception e){
			System.out.println("No s'ha pogut deserialitzar l'objecte");
		}
		return gestDocs;
	}
	
	public void GuardarGMatriu(GestorDocuments gestMat, String ruta) {
		try {
			FileOutputStream f = new FileOutputStream(ruta);
			ObjectOutputStream exp_cont = new ObjectOutputStream(f);
			exp_cont.writeObject(gestMat);
			exp_cont.close();
		}catch(Exception e) {
			System.out.println("No s'ha pogut serialitzar l'objecte");
		}
	}
	
	public GestorMatriu ObtenirGMatriu(String ruta, ArrayList<String> pf) {
		GestorMatriu gestMat = new GestorMatriu(pf);
		try{
			FileInputStream f = new FileInputStream(ruta);
			ObjectInputStream imp_cont = new ObjectInputStream(f);
			gestMat = (GestorMatriu)imp_cont.readObject();
			imp_cont.close();
		}catch(Exception e){
			System.out.println("No s'ha pogut deserialitzar l'objecte");
		}
		return gestMat;
	}
}