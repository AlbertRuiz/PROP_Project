import java.io.Serializable;
import java.util.ArrayList;

public class GestorArchivo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Archivo arch;
	
	public GestorArchivo() {
		this.arch = new Archivo();
	}
	
	public ArrayList<String> GestorImportarPalabrasFuncionales() {
		return this.arch.ImportarPalabrasFuncionales();
	}
	
	public ArrayList<String> GestorImportarNuevasParFunc(String ruta_archivo) {
		return this.arch.ImportarNuevasParFunc(ruta_archivo);
	}
	
	public String GestorImportarTitulo(String ruta_archivo) {
		return this.arch.ImportarTitulo(ruta_archivo);
	}
	
	public String GestorImportarAutor(String ruta_archivo) {
		return this.arch.ImportarAutor(ruta_archivo);
	}
	
	public String GestorImportarContenido(String ruta_archivo){
		return this.arch.ImportarContenido(ruta_archivo);
	}
	
	public void GestorExportarArchivo(String titulo, String autor, String content, String ruta_archivo) {
		this.arch.ExportarDocumento(titulo, autor, content, ruta_archivo);
	}

}
