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

public class Pres_EB extends JFrame implements ActionListener, ItemListener {

		private JLabel l0;
		private JTextField  t0;
		private JButton btn;
		public CtrlDomini CD;
		public JInternalFrame fr;
		public JDesktopPane pane;
		
		public Pres_EB(JDesktopPane pane, JInternalFrame fr, CtrlDomini CD)
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
        l0 = new JLabel("Expressió Booleana: ");
        c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
        fr.add(l0,c);
        
        
		t0= new JTextField(10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;	
		fr.add(t0,c);
		
		btn = new JButton("Cerca Documents per Expressió Booleana");
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
		
		private Object[] getDocs(String eb) {
			return CD.llistarDocsEB(eb);
		}
		
		@Override
		public void actionPerformed(ActionEvent ev) {
				if (t0.getText().equals("")) {
					JOptionPane.showMessageDialog (this,  "Han quedat paràmetres sense omplir.","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE);
				}	
				else {
					Object[] o = getDocs(t0.getText());
					if (o == null) {JOptionPane.showMessageDialog (this,  "L'Expressió Booleana no esta ben parentitzada.","Missatge d'avís",JOptionPane.INFORMATION_MESSAGE);}
					else {
						Pres_Llista p = new Pres_Llista(pane,"Llista de Documents que compleixen l'expressió: "+t0.getText(),o);
					}
				}
		}
		
		@Override
		public void itemStateChanged(ItemEvent arg0) {

		}
		
		
}
