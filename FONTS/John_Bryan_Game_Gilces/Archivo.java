
import java.io.*;
import java.util.*;



public class Archivo implements Serializable {

	private static final long serialVersionUID = 1L;

	public Archivo() {

	}
	
	
	public ArrayList<String> ImportarPalabrasFuncionales () {
		ArrayList<String> pf = new ArrayList<String>();
		try{
			File fcat = new File("data/empty.cat");
			BufferedReader brcat = new BufferedReader(new InputStreamReader(
					new FileInputStream(fcat),"ISO-8859-1"));
			String cadena;
			while ((cadena = brcat.readLine()) != null){
				pf.add(cadena);
			}
			brcat.close();
			File feng = new File("data/empty.eng");
			BufferedReader breng = new BufferedReader(new InputStreamReader(
					new FileInputStream(feng),"ISO-8859-1"));
			while ((cadena = breng.readLine()) != null){
				pf.add(cadena);
			}
			breng.close();
			File fsp = new File("data/empty.sp");
			BufferedReader brsp = new BufferedReader(new InputStreamReader(
					new FileInputStream(fsp),"ISO-8859-1"));
			while ((cadena = brsp.readLine()) != null){
				pf.add(cadena);
			}
			brsp.close();			
			Collections.sort(pf);
			
		}
		catch(FileNotFoundException e) { System.out.println("No s'han pogut obrir els arxius de paraules funcionals.\n"); }
        catch(IOException e) { System.out.println("Error al intentar llegir els arxius de paraules funcionals.\n");}
        catch(Exception e) {System.out.println("No s'han trobat els arxius de paraules funcionals.\n");}
		return pf;
	}
	
	public ArrayList<String> ImportarNuevasParFunc(String ruta_archivo) {
		ArrayList<String> pf = new ArrayList<String>();
		try{
			File f = new File(ruta_archivo);
			BufferedReader brpar = new BufferedReader(new InputStreamReader(
					new FileInputStream(f),"ISO-8859-1"));
			String cadena;
			while ((cadena = brpar.readLine()) != null){
				pf.add(cadena);
			}
			brpar.close();			
		}
		catch(FileNotFoundException e) { System.out.println("No s'han pogut obrir els arxius de paraules funcionals.\n"); }
        catch(IOException e) { System.out.println("Error al intentar llegir els arxius de paraules funcionals.\n");}
        catch(Exception e) {System.out.println("No s'han trobat els arxius de paraules funcionals.\n");}
		return pf;
	}
	
	public String ImportarTitulo(String nombrearchivo) {
		String titulo = new String();
		try{
			File f = new File(nombrearchivo);
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					new FileInputStream(f),"UTF-8"));
			titulo = bf.readLine();
			bf.close();
		}
		catch(FileNotFoundException e) { System.out.println("No s'han pogut obrir l'arxiu "+nombrearchivo+".\n"); }
        catch(IOException e) { System.out.println("Error al intentar llegir l'arxiu "+nombrearchivo+".\n");}
        catch(Exception e) {System.out.println("No s'ha trobat l'arxius "+nombrearchivo+".\n");}
		return titulo;
	}
	
	public String ImportarAutor(String nombrearchivo) {
		String autor = new String();
		try{
			File f = new File(nombrearchivo);
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					new FileInputStream(f),"UTF-8"));
			autor = bf.readLine();
			autor = bf.readLine();
			bf.close();
			
		}
		catch(FileNotFoundException e) { System.out.println("No s'han pogut obrir l'arxiu "+nombrearchivo+".\n"); }
        catch(IOException e) { System.out.println("Error al intentar llegir l'arxiu "+nombrearchivo+".\n");}
        catch(Exception e) {System.out.println("No s'ha trobat l'arxius "+nombrearchivo+".\n");}
		return autor;
	}
	
	public String ImportarContenido(String nombrearchivo) {
		String contenido = new String();
		try{
			File f = new File(nombrearchivo);
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					new FileInputStream(f),"UTF-8"));
			String cadena;
			while ((cadena = bf.readLine()) != null) {
				contenido = contenido.concat(cadena+"\n");
			}
			System.out.print(contenido);
			bf.close();
		}
		catch(FileNotFoundException e) { System.out.println("No s'han pogut obrir l'arxiu "+nombrearchivo+".\n"); }
        catch(IOException e) { System.out.println("Error al intentar llegir l'arxiu "+nombrearchivo+".\n");}
        catch(Exception e) {System.out.println("No s'ha trobat l'arxius "+nombrearchivo+".\n");}
		return contenido;
	}
	
	public void ExportarDocumento(String titulo, String autor, String content, String ruta_archivo) {
		try
		{
		PrintWriter writer = new PrintWriter(ruta_archivo, "UTF-8");
			//writer.println(titulo);
			//writer.println(autor);
			writer.println(content);
			writer.close();
		}
		catch(FileNotFoundException e) { System.out.println("No s'ha pogut en la ruta '"+ruta_archivo+"'.");}
		catch(UnsupportedEncodingException e) { System.out.println("Format incorrecte.");}
		catch(Exception e){System.out.println("Error a l'exportar el fitxer.");}
	}
	
}