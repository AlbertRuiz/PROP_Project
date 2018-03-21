import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Pres_TA extends JFrame implements ActionListener, ItemListener {

	private JLabel l0;
	private JTextField  t0;
	private JButton btn;
	public CtrlDomini CD;
	public JInternalFrame fr;
	public JDesktopPane pane;
	
	public Pres_TA(JDesktopPane pane, JInternalFrame fr, CtrlDomini CD)
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
        l0 = new JLabel("Autor: ");
        c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
        fr.add(l0,c);
        
        
		t0= new JTextField(10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;	
		fr.add(t0,c);
		
		btn = new JButton("Cercar Títols");
		btn.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		fr.add(btn,c);
		
        
		this.fr = fr;
		
        try { fr.setMaximum(true); } catch (Exception PropertyVetoException) { }
		pane.repaint();
	}

	private void sortir()
	{
		pane.remove(fr);
		pane.repaint();
	}
	
	private boolean existeix_autor(String autor) {
		return this.CD.existeixAutor(autor);
	}
	
	private Object[] getTitols(String autor) {
		return this.CD.llistarTitolsAutor(autor);
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (t0.getText().equals("")) {
			JOptionPane.showMessageDialog (this,  "Han quedat paràmetres sense omplir.","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			boolean b = existeix_autor(t0.getText());
			if (b) {
				Pres_Llista p = new Pres_Llista(pane,"Llista de títols escrits per l'autor "+t0.getText(),getTitols(t0.getText()));
				
			}
			else {
				JOptionPane.showMessageDialog (this,  "Hi ha hagut un error:\nAquest autor no existeix.\n","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {

	}
}
