

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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Pres_DelDoc extends JFrame implements ActionListener, ItemListener {
	private JLabel l0;
	private JLabel l1;
	private JTextField  t0;
	private JTextField  t1;
	private JButton btn;
	public CtrlDomini CD;
	public JInternalFrame fr;
	public JDesktopPane pane;
	
	public Pres_DelDoc(JDesktopPane pane, JInternalFrame fr, CtrlDomini CD)
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
        
        t0= new JTextField(10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;	
		fr.add(t0,c);
		
		t1= new JTextField(10);		  
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		fr.add(t1,c);
        btn = new JButton("Esborrar Document");
		btn.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		fr.add(btn,c);
        
		this.fr = fr;
		
        try { fr.setMaximum(true); } catch (Exception PropertyVetoException) { }
		pane.repaint();
	}
	
	private boolean esborrar() {
		return CD.esborrar_Doc(t0.getText(), t1.getText());
	}
	
	private void sortir()
	{
		pane.remove(fr);
		pane.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (t0.getText().equals("") || t1.getText().equals("")) {
			JOptionPane.showMessageDialog (this,  "Han quedat paràmetres sense omplir.","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			boolean b = esborrar();
			if (b) {
				JOptionPane.showMessageDialog (this,  "El document ha estat esborrat amb èxit.","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE);
				int question = JOptionPane.showConfirmDialog(this, "Vols continuar esborrant documents?","Missatge d'avís", JOptionPane.YES_NO_OPTION);
				if(question != JOptionPane.YES_OPTION) { sortir(); }
			}
			else {
				JOptionPane.showMessageDialog (this,  "Hi ha hagut un error:\nO bé l'autor no exiteix o bé no ha escrit aquesta obra.\n","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {

	}
	
	
}
