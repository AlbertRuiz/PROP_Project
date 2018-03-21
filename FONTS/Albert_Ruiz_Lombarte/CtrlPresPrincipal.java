import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;



public class CtrlPresPrincipal extends JFrame{
	
	private Action openAction = new OpenAction();
	private Action saveAction = new SaveAction();
	private Action addDocAction = new AddDocAction();
	private Action delDocAction = new DelDocAction();
	private Action modDocAction = new ModDocAction();
	private Action impDocAction = new ImpDocAction();
	private Action impPFAction = new ImpPFAction();
	private Action expDocAction = new ExpDocAction();
	private Action conDocAction = new ConDocAction();
	private Action cercaTAAction = new CercaTAAction();//Llistar Titols d'un Autor.
	private Action cercaAPAction = new CercaAPAction();//Autors per prefix.
	private Action cercaDocsAction = new CercaDocsAction();//Llistar Docs del sistema.
	private Action cercaDSAction = new CercaDSAction();//Llistar Docs Similars.
	private Action cercaEBAction = new CercaEBAction();//Llistar Docs exp booleana.
	//HISTORIAL?prova
	private Action aboutAction = new AboutAction();
	private Action manualAction = new ManualAction();
	
	public static void main(String[] args) {
		CtrlPresPrincipal P = new CtrlPresPrincipal();
		P.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		P.setVisible(true);
	}
	
	private CtrlDomini CD = new CtrlDomini();
	JDesktopPane pane;
	
	public CtrlPresPrincipal() {
		/**
		*Classe que controla el menu de l'aplicacio.
		*/
		
	 super("Projecte de PROP22_6.2");
	 
	 /*Codigo aqui*/
	 
	 setJMenuBar(createMenuBar());
	 setSize(960, 720);
	 pane = new JDesktopPane();
	 pane.setBackground(Color.GRAY);
	}
	
	protected JMenuBar createMenuBar() {
			 JMenuBar menubar = new JMenuBar();
			 JMenu file = new JMenu("Sessió");
			 JMenu edit = new JMenu("Gestió de Dades");
			 JMenu search = new JMenu("Cerca");
			 JMenu help = new JMenu("Ajuda");
			 menubar.add(file);
			 menubar.add(edit);
			 menubar.add(search);
			 menubar.add(help);
			 
			 file.setIcon(new ImageIcon("data/icons/fitxer.png"));
			 edit.setIcon(new ImageIcon("data/icons/gdades.png"));
			 search.setIcon(new ImageIcon("data/icons/cerca.png"));
			 help.setIcon(new ImageIcon("data/icons/ajuda.png"));
			 
			 
			 //FILE:
			 
			 file.add(getOpenAction());
			 file.add(getSaveAction());
			 file.add(new ExitAction());
			 
			 //DATA:
			 
			 edit.add(getAddDocAction());
			 edit.add(getDelDocAction());
			 edit.add(getModDocAction());
			 edit.add(getImpDocAction());
			 edit.add(getImpPFAction());
			 edit.add(getExpDocAction());
			 
			 //SEARCH
			 search.add(getConDocAction());
			 search.add(getCercaTAAction());
			 search.add(getCercaAPAction());
			 search.add(getCercaDocsAction());
			 search.add(getCercaDSAction());
			 search.add(getCercaEBAction());
			 
			 //HELP
			 help.add(getManualAction());
			 help.add(getAboutAction());
			 
			 return menubar;
	}

	
	protected Action getOpenAction() {return openAction; }
	protected Action getSaveAction() {return saveAction; }
	protected Action getAddDocAction() {return addDocAction;}
	protected Action getDelDocAction() {return delDocAction;}
	protected Action getModDocAction() {return modDocAction;}
	protected Action getImpDocAction() {return impDocAction;}
	protected Action getImpPFAction() {return impPFAction;}
	protected Action getExpDocAction() {return expDocAction;}
	protected Action getConDocAction() {return conDocAction;}
	protected Action getCercaTAAction() {return cercaTAAction;}
	protected Action getCercaAPAction() {return cercaAPAction;}
	protected Action getCercaDocsAction() {return cercaDocsAction;}
	protected Action getCercaDSAction() {return cercaDSAction;}
	protected Action getCercaEBAction() {return cercaEBAction;}
	protected Action getManualAction() {return manualAction;}
	protected Action getAboutAction(){return aboutAction;}
	
	
	class OpenAction extends AbstractAction {
		 public OpenAction() { super("Carregar Sessió", new ImageIcon("data/icons/obre.png")); }
		 public void actionPerformed(ActionEvent ev)
		 {
			 JFileChooser fc = new JFileChooser();
			 JOptionPane.showMessageDialog(CtrlPresPrincipal.this, "Selecciona l'arxiu de dades.", "Carregar Sessió", JOptionPane.INFORMATION_MESSAGE);
			 if (fc.showOpenDialog(CtrlPresPrincipal.this) == JFileChooser.APPROVE_OPTION) {
				 String arxiu = fc.getCurrentDirectory().toString()+"/"+fc.getName(fc.getSelectedFile());
				 /*Importar Binaris amb l'arxiu donat*/
				 Binario B = new Binario();
				 boolean b = true;
				 try {
				 CD = B.ObtenirCtrlDomini(arxiu);
				 }
				 catch(Exception ex) {b = false;}
				 if (b) {
					 JOptionPane.showMessageDialog(CtrlPresPrincipal.this,"Has carregat la sessió amb èxit.", "Missatge d'avís", JOptionPane.INFORMATION_MESSAGE);
				 }
				 else {
					 JOptionPane.showMessageDialog(CtrlPresPrincipal.this,"Hi ha hagut un problema.", "Missatge d'avís", JOptionPane.INFORMATION_MESSAGE);
				 }
			 }
		 }			 
		}
	
	class SaveAction extends AbstractAction {
		 public SaveAction() { super("Guardar Sessió", new ImageIcon("data/icons/guardar.png")); }
		 public void actionPerformed(ActionEvent ev) {
			 JFileChooser fc = new JFileChooser();
			 /*Importar Binaris*/
			 JOptionPane.showMessageDialog(CtrlPresPrincipal.this, "Selecciona l'arxiu de dades.", "Guardar Sessió", JOptionPane.INFORMATION_MESSAGE);
			 if (fc.showSaveDialog(CtrlPresPrincipal.this) == JFileChooser.APPROVE_OPTION) {
				 String arxiu = fc.getCurrentDirectory().toString() + "/" + fc.getName(fc.getSelectedFile());
				 Binario B = new Binario();
				 boolean b = true;
				 try {B.GuardarCtrlDomini(CD, arxiu);}
				 catch(Exception ex) {b = false;}
				 if (b) {JOptionPane.showMessageDialog(CtrlPresPrincipal.this,"Has desat la sessió amb èxit.", "Missatge d'avís", JOptionPane.INFORMATION_MESSAGE);}
				 else {JOptionPane.showMessageDialog(CtrlPresPrincipal.this,"Hi ha hagut un problema.", "Missatge d'avís", JOptionPane.INFORMATION_MESSAGE);}
			 }
		 }
	}
	
	public class ExitAction extends AbstractAction {
		 public ExitAction() { super("Sortir", new ImageIcon("data/icons/tanca.png"));  }
		 public void actionPerformed(ActionEvent ev) { System.exit(0); }
	}
	
	public class AddDocAction extends AbstractAction {
		 public AddDocAction() { super("Afegir Document", new ImageIcon("data/icons/afegir_doc.png")); }
		 public void actionPerformed(ActionEvent ev) { show_content("Afegir Document"); }
	}
	
	public class DelDocAction extends AbstractAction {
		 public DelDocAction() { super("Esborrar Document", new ImageIcon("data/icons/esborrar_doc.png")); }
		 public void actionPerformed(ActionEvent ev) { show_content("Esborrar Document"); }
	}
	
	public class ModDocAction extends AbstractAction {
		public ModDocAction() {super("Modificar Document", new ImageIcon("data/icons/modificar_doc.png"));}
		public void actionPerformed(ActionEvent ev) {show_content("Modificar Document");}
	}	
	
	public class ImpDocAction extends AbstractAction {
		public ImpDocAction() {super ("Importar Document", new ImageIcon("data/icons/importar.png"));}
		public void actionPerformed(ActionEvent ev) {show_content("Importar Document");}		
	}
	
	public class ImpPFAction extends AbstractAction {
		public ImpPFAction() {super ("Importar Paraules Funcionals", new ImageIcon("data/icons/importarPF.png"));}
		public void actionPerformed(ActionEvent ev) {show_content("Importar Paraules Funcionals");}		
	}
	
	public class ExpDocAction extends AbstractAction {
		public ExpDocAction() {super ("Exportar Document", new ImageIcon("data/icons/exportar.png"));}
		public void actionPerformed(ActionEvent ev) {show_content("Exportar Document");}		
	}
	
	public class ConDocAction extends AbstractAction {
		public ConDocAction() {super("Consultar Contingut", new ImageIcon ("data/icons/contingut.png"));}
		public void actionPerformed(ActionEvent ev) {show_content("Consultar Contingut");}
	}
	
	public class CercaTAAction extends AbstractAction {
		public CercaTAAction() {super ("Llistar títols d'un autor", new ImageIcon("data/icons/cercaTA.png"));}
		public void actionPerformed(ActionEvent ev) {show_content("Llistar títols d'un autor");}
	}
	
	public class CercaAPAction extends AbstractAction {
		public CercaAPAction() {super ("Llistar autors per prefix", new ImageIcon("data/icons/autors_pre.png"));}
		public void actionPerformed(ActionEvent ev) {show_content("Llistar autors per prefix");}
	}
	
	public class CercaDocsAction extends AbstractAction {
		public CercaDocsAction() {super ("Llistar documents", new ImageIcon("data/icons/cercadocs.png"));}
		public void actionPerformed(ActionEvent ev) {show_content("Llistar documents");}
	}
	
	public class CercaDSAction extends AbstractAction {
		public CercaDSAction() {super ("Llistar documents similars", new ImageIcon("data/icons/cercaS.png"));}
		public void actionPerformed(ActionEvent ev) {show_content("Llistar documents similars");}
	}
	
	public class CercaEBAction extends AbstractAction {
		public CercaEBAction() {super ("Llistar documents per expressió booleana", new ImageIcon("data/icons/cercaEB.png"));}
		public void actionPerformed(ActionEvent ev) {show_content("Llistar documents per expressió booleana");}
	}
	
	class ManualAction extends AbstractAction {
		 public ManualAction() { super("Manual d'Usuari", new ImageIcon("data/icons/manualusuari.png")); }
		 public void actionPerformed(ActionEvent ev) { show_manual(); }
	 }
	
	class AboutAction extends AbstractAction {
		 public AboutAction() { super("Sobre el programa", new ImageIcon("data/icons/about.png")); }
		 public void actionPerformed(ActionEvent ev) { show_credits(); }
	}
	
	public void show_content(String s)
	{
		
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul> Mostra el contingut de la seccio "s". </Ul>
		  * @param s			Nom de la seccio que volem mostrar-
		*/
		
		JInternalFrame fr = new JInternalFrame(s,false,true,false,false);
		try { fr.setMaximum(true); } catch (Exception PropertyVetoException) { }
		fr.setSize(pane.getWidth(), pane.getHeight());
		fr.setVisible(true);
		fr.setLocation(0,0);
		pane.add(fr);
		fr.moveToFront();
		add(pane);
		
		if (s.equals("Afegir Document")) {Pres_AddDoc p = new Pres_AddDoc(pane,fr,CD); }
		else if (s.equals("Esborrar Document")) {Pres_DelDoc p = new Pres_DelDoc(pane,fr,CD); }
		else if (s.equals("Modificar Document")) {Pres_ModDoc p = new Pres_ModDoc(pane,fr,CD); }
		else if (s.equals("Importar Document")) {Pres_ImpDoc p = new Pres_ImpDoc(pane,fr,CD); }
		else if (s.equals("Importar Paraules Funcionals")) {Pres_ImpPF p = new Pres_ImpPF(pane,fr,CD);}
		else if (s.equals("Exportar Document")) {Pres_ExpDoc p = new Pres_ExpDoc(pane,fr,CD); }
		else if (s.equals("Consultar Contingut")) {Pres_ConDoc p = new Pres_ConDoc(pane,fr,CD);}
		else if (s.equals("Llistar títols d'un autor")){Pres_TA p = new Pres_TA(pane,fr,CD);}
		else if (s.equals("Llistar autors per prefix")){Pres_AP p = new Pres_AP(pane,fr,CD);}
		else if (s.equals("Llistar documents")){Pres_Llista p = new Pres_Llista(pane,"Llista de tots els documents del sistema",CD.llistarDocs());}
		else if (s.equals("Llistar documents similars")){Pres_DS p = new Pres_DS(pane,fr,CD);}
		else if (s.equals("Llistar documents per expressió booleana")){Pres_EB p = new Pres_EB(pane,fr,CD);}		
	}
	
	public void show_credits()
	{
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul> Mostra un missatge amb els credits del programa. </Ul>
		*/
		JOptionPane.showMessageDialog (this,  "Projecte de PROP22_6.2\nRealitzat per:\n-"
				+ "Albert Ruiz Lombarte\n-Aleix Roselló Rosiñol\n-John Bryan Game Gilces","Sobre el Programa",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void show_manual()
	{
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul> Obre el manual en PDF del programa. </Ul>
		*/
		try { Desktop.getDesktop().open(new File("data/manual.pdf")); }
		catch (IOException e) { JOptionPane.showMessageDialog (this,  "No s'ha pogut obrir el manual.","Sobre el Programa",JOptionPane.INFORMATION_MESSAGE); }
		catch (NullPointerException e) { JOptionPane.showMessageDialog (this,  "No s'ha pogut obrir el manual.","Sobre el Programa",JOptionPane.INFORMATION_MESSAGE); }
		catch (IllegalArgumentException e) { JOptionPane.showMessageDialog (this,  "No s'ha pogut obrir el manual.","Sobre el Programa",JOptionPane.INFORMATION_MESSAGE); }
	}
	
	
}
