import java.io.Serializable;
import java.util.ArrayList;

public class CtrlDomini implements Serializable{
	private static final long serialVersionUID = 1L;
	private GestorDocuments gestorDocuments;
	private GestorCjt_Autors gestorCjtAutors;
	private GestorMatriu gestorMatriu;
	private GestorArchivo gestorFitxers;
	private ArrayList<String> pFuncionals;
	
	public CtrlDomini() {
		gestorDocuments = new GestorDocuments();
		gestorCjtAutors = new GestorCjt_Autors();
		gestorFitxers = new GestorArchivo();
		pFuncionals = gestorFitxers.GestorImportarPalabrasFuncionales();
		gestorMatriu = new GestorMatriu(pFuncionals);
	}
	
	public boolean afegir_Doc(String titol, String autor, String contingut) {
		if (gestorCjtAutors.add_Autor_Titol(autor, titol)) {
			gestorMatriu.addDocument_buit(titol, autor);
			gestorMatriu.addContingut(titol, autor, gestorDocuments.addDocument(titol, autor, contingut));
			return true;
		}
		else return false;
	}
	
	public boolean esborrar_Doc(String titol, String autor) {
		if (gestorCjtAutors.delete_Titol_dAutor(titol, autor)) {
			gestorMatriu.deleteDoc(titol, autor);
			gestorDocuments.deleteDocument(titol, autor);
			return true;
		}
		else return false;
	}
	
	public String getConDoc(String titol, String autor) {
		if (gestorCjtAutors.existeixDoc(titol, autor)) {
			return gestorDocuments.getContent(autor, titol);
		}
		else return "";
	}
	
	public void modificar_Doc(String titol, String autor, String nou_con) {
		gestorMatriu.replaceContingut(titol, autor, gestorDocuments.replaceContent(titol, autor, nou_con));
	}
	
	public boolean importar_Doc(String titol, String autor, String arxiu) {
		String content = gestorFitxers.GestorImportarContenido(arxiu);
		if (gestorCjtAutors.add_Autor_Titol(autor, titol)) {
			gestorMatriu.addDocument_buit(titol, autor);
			gestorMatriu.addContingut(titol, autor, gestorDocuments.addDocument(titol, autor, content));
			return true;
		}
		else return false;
	}
	
	public boolean importar_PF(String ruta) {
		try{
		gestorMatriu.add_pFuncionals(gestorFitxers.GestorImportarNuevasParFunc(ruta));
		return true;
		}
		catch(Exception e) {return false;}
	}
	
	public boolean existeix_Doc(String titol, String autor) {
		return gestorCjtAutors.existeixDoc(titol, autor);
	}
	
	public void exportar_Doc(String titol, String autor, String ruta) {
		gestorFitxers.GestorExportarArchivo(titol, autor, gestorDocuments.getContent(autor, titol), ruta);
	}
	
	public boolean existeixAutor(String autor) {
		return gestorCjtAutors.existeixAutor(autor);
	}
	
	public Object[] llistarTitolsAutor(String autor) {
		return gestorCjtAutors.getTitols(autor);
	}
	
	public Object[] llistarAutorsPrefix(String prefix) {
		return gestorCjtAutors.getAutorsP(prefix);
	}
	
	public Object[] llistarDocs() {
		return gestorDocuments.getDocumentsList();
	}
	
	public Object[] llistarDocsSimilarsCos(String titol, String autor, int N) {
		return gestorMatriu.getDocsSimilars_Cosinus(titol, autor, N);
	}
	
	public Object[] llistarDocsSimilarsIDFJ(String titol, String autor, int N) {
		return gestorMatriu.getDocsSimilars_IDFJ(titol, autor, N);
	}
	

	public Object[] llistarDocsEB(String exp) {
		ArrayList<String> a = gestorDocuments.evaluateExpresion(exp);
		if (a == null) {return null;}
		else {return a.toArray();}
	}
}
