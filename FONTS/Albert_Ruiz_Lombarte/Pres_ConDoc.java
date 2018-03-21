import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Pres_ConDoc extends JFrame implements ActionListener, ItemListener {
	private JLabel l0;
	private JLabel l1;
	private JLabel l2;
	private JTextField  t0;
	private JTextField  t1;
	private JTextArea	t2;
	private JButton btn1;
	public CtrlDomini CD;
	public JInternalFrame fr;
	public JDesktopPane pane;
	
	public Pres_ConDoc(JDesktopPane pane, JInternalFrame fr, CtrlDomini CD)
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
        
        l2 = new JLabel("Contingut del document: ");
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 5;
        fr.add(l2,c);
        
        JSeparator sep=new JSeparator();
		c.gridx = 1;
		c.gridy = 2;
		fr.add(sep, c);
        
		JSeparator sep2=new JSeparator();
		c.gridx = 1;
		c.gridy = 4;
		fr.add(sep2, c);
		
		JSeparator sep3=new JSeparator();
		c.gridx = 1;
		c.gridy = 6;
		fr.add(sep3, c);
		
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
		t2.setEditable(false);
		t2.setLineWrap(true);
		t2.setMargin(new Insets(10,10,10,10));
		c.gridx = 1;
		c.gridy = 7;
		fr.add(t2,c);
		
		this.btn1 = new JButton("Mirar Contingut");
		this.btn1.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		fr.add(this.btn1,c);
		
		this.fr = fr;
		try { fr.setMaximum(true); } catch (Exception PropertyVetoException) { }
		pane.repaint();
		
	}
	
	private String get_con(String titol, String autor) {
		return CD.getConDoc(titol, autor);
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
				String con_antic = get_con(t0.getText(),t1.getText());
				if (!con_antic.equals("")) {
					t2.setText(con_antic);
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
