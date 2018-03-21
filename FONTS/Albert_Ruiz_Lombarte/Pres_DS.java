import java.awt.Choice;
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

public class Pres_DS extends JFrame implements ActionListener, ItemListener {
	
	
	private JLabel l0;
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JTextField  t0;
	private JTextField  t1;
	private JTextField  t2;
	private Choice ch;
	private JButton btn;
	public CtrlDomini CD;
	public JInternalFrame fr;
	public JDesktopPane pane;
	
	public Pres_DS(JDesktopPane pane, JInternalFrame fr, CtrlDomini CD)
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
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		fr.add(l0,c);
		t0= new JTextField(10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;	
		fr.add(t0,c);
		
		l1 = new JLabel("Autor: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		fr.add(l1,c);
		t1= new JTextField(10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;	
		fr.add(t1,c);
		
		
		l2 = new JLabel("Quin mètode de càlcul vols usar? ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		fr.add(l2,c);
		ch=new Choice();
		ch.add("Càlcul amb mètode Cosinus");
		ch.add("Càlcul amb mètode TF-IDFJ");
		ch.addItemListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		fr.add(ch,c);
	
		l3 = new JLabel("Quin nombre de documents vols veure? ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
    	fr.add(l3,c);
    	t2= new JTextField(10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;	
		fr.add(t2,c);
		
		btn = new JButton("Cercar Documents Similars");
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
	
	private void sortir()
	{
		pane.remove(fr);
		pane.repaint();
	}
	
	private boolean existeix_Doc(String titol, String autor) {
		return this.CD.existeix_Doc(titol, autor);
	}
	
	private boolean es_enter(String n) {
		try {
			int N = Integer.parseInt(n);
			return (N >= 0);
		}
		catch (NumberFormatException e) {return false;}
	}
	
	private Object[] getDocsCos(String titol, String autor, int N) {
		return this.CD.llistarDocsSimilarsCos(titol, autor, N);
	}
	
	private Object[] getDocsIDFJ(String titol, String autor, int N) {
		return this.CD.llistarDocsSimilarsIDFJ(titol, autor, N);
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (t0.getText().equals("") || t1.getText().equals("") || t2.getText().equals("") ) {
			JOptionPane.showMessageDialog (this,  "Han quedat paràmetres sense omplir.","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			boolean b = existeix_Doc(t0.getText(),t1.getText());
			boolean t = es_enter(t2.getText());
			if (b && t) {
				if (ch.getSelectedItem().equals("Càlcul amb mètode Cosinus")) {
					Pres_Llista p = new Pres_Llista(pane,"Llista de documents més similars calculata amb mètode Cosinus:",getDocsCos(t0.getText(),t1.getText(),Integer.parseInt(t2.getText())));
				}
				else if (ch.getSelectedItem().equals("Càlcul amb mètode TF-IDFJ")) {
					Pres_Llista p = new Pres_Llista(pane,"Llista de documents més similars calculata amb mètode TF-IDFJ:",getDocsIDFJ(t0.getText(),t1.getText(),Integer.parseInt(t2.getText())));
				}
			}
			else {
				if (!b) {JOptionPane.showMessageDialog (this,  "Hi ha hagut un error:\nAquest document no existeix.\n","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE);}
				if (!t) {JOptionPane.showMessageDialog (this,  "Hi ha hagut un error:\nEl nombre de documents a mostrar ha de ser un enter més gran que 0.\n","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE);}
			}
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {

	}

}
