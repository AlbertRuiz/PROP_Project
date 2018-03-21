
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Pres_ImpPF extends JFrame implements ActionListener{
	private JLabel l0;
	private JFileChooser fc;
	public CtrlDomini CD;
	public JInternalFrame fr;
	public JDesktopPane pane;
	
	public Pres_ImpPF(JDesktopPane pane, JInternalFrame fr, CtrlDomini CD)
	{
		
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
        l0 = new JLabel("Escull l'arxiu amb les paraules funcionals que vols importar: ");
        c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		fr.add(l0,c);
		
		fc = new JFileChooser();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		fc.addActionListener(this);
		fr.add(fc,c);
		
		this.fr = fr;
        try { fr.setMaximum(true); } catch (Exception PropertyVetoException) { }
		pane.repaint();
        
	}
	
	private boolean importarPF(String arxiu) {
		return this.CD.importar_PF(arxiu);
	}
	
	private void sortir()
	{
		pane.remove(fr);
		pane.repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String act = e.getActionCommand();
		if (act.equals(JFileChooser.APPROVE_SELECTION)) {
				String arxiu = fc.getCurrentDirectory().toString()+"/"+fc.getName(fc.getSelectedFile());
				boolean b = importarPF(arxiu);
				if (b) { 
					JOptionPane.showMessageDialog (this,  "Has importat les paraules funcionals correctament.","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE); 
					int question = JOptionPane.showConfirmDialog(this, "Vols continuar importan paraules funcionals?","Missatge d'avís", JOptionPane.YES_NO_OPTION);
					if(question != JOptionPane.YES_OPTION) { sortir(); }
				}
				else { JOptionPane.showMessageDialog (this,  "Hi ha hagut algún problema.","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE); }
		}
		else if (act.equals(JFileChooser.CANCEL_SELECTION)) {sortir();}
	}
	
	
}
