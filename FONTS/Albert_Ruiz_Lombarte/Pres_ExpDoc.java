import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Pres_ExpDoc extends JFrame implements ActionListener{
	private JLabel l0;
	private JLabel l1;
	private JLabel l2;
	private JTextField  t0;
	private JTextField  t1;
	private JFileChooser fc;
	public CtrlDomini CD;
	public JInternalFrame fr;
	public JDesktopPane pane;
	
	public Pres_ExpDoc(JDesktopPane pane, JInternalFrame fr, CtrlDomini CD) {
		
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul> Crea un menu interactiu. </Ul>
		  * @param pane			Pane del Swing.
		  * @param fr			InternalFrame del Swing.
		  * @param CD			Controlador del Domini.
		*/
		
		this.CD = CD;
		this.pane = pane;
		fr.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        l0 = new JLabel("Títol: ");
        c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		fr.add(l0,c);
		l1 = new JLabel("Autor: ");
        c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
        fr.add(l1,c);
        l2 = new JLabel("Escull la ruta del fitxer on s'exportarà el contingut del document:");
		c.gridx = 1;
		c.gridy = 3;
		fr.add(l2,c);
        
		t0 = new JTextField(10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;	
		fr.add(t0,c);
		
		t1 = new JTextField(10);		  
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		fr.add(t1,c);
		
		fc = new JFileChooser();
		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		fc.addActionListener(this);
		fr.add(fc,c);
		
		this.fr = fr;
        try { fr.setMaximum(true); } catch (Exception PropertyVetoException) { }
		pane.repaint();
        
	}

	private void sortir()
	{
		pane.remove(fr);
		pane.repaint();
	}
	
	private boolean existeixDoc(String titol, String autor) {
		return this.CD.existeix_Doc(titol, autor);
	}
	
	private void exportar(String titol, String autor, String ruta) {
		this.CD.exportar_Doc(titol,autor,ruta);
	}
	
	public void actionPerformed(ActionEvent e) {
		String act = e.getActionCommand();
		if (act.equals(JFileChooser.APPROVE_SELECTION)) {
			if (t0.getText().equals("") || t1.getText().equals("")) {
				JOptionPane.showMessageDialog (this,  "Han quedat paràmetres sense omplir.","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				if (existeixDoc(t0.getText(), t1.getText())) {
					String arxiu = fc.getCurrentDirectory().toString()+"/"+fc.getName(fc.getSelectedFile());
					File f;
					boolean b;
					try { f = new File(arxiu); b = f.createNewFile(); }
					catch (Exception ex) { b = false; }
					if (b) {
						exportar(t0.getText(),t1.getText(),arxiu);
						JOptionPane.showMessageDialog (this,  "Has exportat el document correctament.","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE); 
						int question = JOptionPane.showConfirmDialog(this, "Vols continuar exportan documents?","Missatge d'avís", JOptionPane.YES_NO_OPTION);
						if(question != JOptionPane.YES_OPTION) { sortir(); }
					}
					else {
						JOptionPane.showMessageDialog (this,  "Hi ha hagut algun problema.","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {JOptionPane.showMessageDialog (this,  "O bé aquest autor no exiteix o bé no ha escrit aqueta obra.","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE);}
			}
		}
		else if (act.equals(JFileChooser.CANCEL_SELECTION)) { sortir(); }
	}
}
