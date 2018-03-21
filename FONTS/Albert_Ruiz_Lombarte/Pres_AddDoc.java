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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Pres_AddDoc extends JFrame implements ActionListener, ItemListener {
	
	private JLabel l0;
	private JLabel l1;
	private JLabel l2;
	private JTextField  t0;
	private JTextField  t1;
	private JTextArea	t2;
	private JButton btn;
	public CtrlDomini CD;
	public JInternalFrame fr;
	public JDesktopPane pane;
	
	public Pres_AddDoc(JDesktopPane pane, JInternalFrame fr, CtrlDomini CD)
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
        
        JLabel le = new JLabel(" ");
		c.gridx = 0;
		c.gridy = 2;
		fr.add(le,c);
        
        JLabel l2 = new JLabel("Contingut:");
		c.gridx = 0;
		c.gridy = 3;
		fr.add(l2,c);
        
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
		
		t2 = new JTextArea(15,20);
		c.gridx = 1;
		c.gridy = 3;
		fr.add(t2,c);
		
		btn = new JButton("Afegir Document");
		btn.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		fr.add(btn,c);
        
		this.fr = fr;
		
        try { fr.setMaximum(true); } catch (Exception PropertyVetoException) { }
		pane.repaint();
	}
	
	private boolean afegir() {
		return CD.afegir_Doc(t0.getText(), t1.getText(), t2.getText());
	}
	
	private void sortir()
	{
		pane.remove(fr);
		pane.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (t0.getText().equals("") || t1.getText().equals("") || t2.getText().equals("")) {
			JOptionPane.showMessageDialog (this,  "Han quedat paràmetres sense omplir.","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			boolean b = afegir();
			if (b) {
				JOptionPane.showMessageDialog (this,  "El document ha estat afegit amb èxit.","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE);
				int question = JOptionPane.showConfirmDialog(this, "Vols continuar afegint documents?","Missatge d'avís", JOptionPane.YES_NO_OPTION);
				if(question != JOptionPane.YES_OPTION) { sortir(); }
			}
			else {
				JOptionPane.showMessageDialog (this,  "Hi ha hagut un error:\nAquest autor ja ha escrit aquesta obra.\n","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {

	}
}
