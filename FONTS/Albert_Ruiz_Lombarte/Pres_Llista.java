import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Pres_Llista extends JFrame{
	
	public JDesktopPane pane;
	private JList l;

	public Pres_Llista(JDesktopPane pane, String assumpte, Object[] array) {
		/** <p><b>Pre:</b></p>  <Ul>Cert.</Ul>
		  * <p><b>Post:</b></p> <Ul> Crea un menu interactiu. </Ul>
		  * @param pane			Pane del Swing.
		  * @param assumpte		Assumpte de la llista.
		  * @param array		Array amb els Strings a mostrar en llista.
		  * */
		this.pane = pane;
		JInternalFrame fr = new JInternalFrame(assumpte,false,true,false,false);
		fr.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        l = new JList(array);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		
		JScrollPane listScroller = new JScrollPane(l);
		listScroller.setPreferredSize(new Dimension(300, 300));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		fr.add(listScroller, c);
		
		try { fr.setMaximum(true); } catch (Exception PropertyVetoException) { }
		
		fr.setSize(pane.getWidth(), pane.getHeight());
		fr.setVisible(true);
		fr.setLocation(0,0);
		pane.add(fr);
		fr.moveToFront();
		this.pane = pane;
	}
}
