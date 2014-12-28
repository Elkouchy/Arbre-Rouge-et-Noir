import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Zone extends JFrame {
	
	private final static int HAUTEUR = 600;
	private final static int LARGEUR = 800;
	private JScrollPane zouhair;
	public static ZoneGraphique graphisme ;
	private Dimension staille ;
	public Zone()
	{
	
		setTitle("Zouhair ELKOUCHI '' GLSID2 '': Arbre Rouge et Noir ");
		staille = Toolkit.getDefaultToolkit().getScreenSize() ;
		this.setBounds((staille.width-LARGEUR)/2,(staille.height-HAUTEUR)/2,LARGEUR,HAUTEUR) ;
		this.getContentPane().add(new Graphique(),"North") ;
		graphisme = new ZoneGraphique() ;
		zouhair = new JScrollPane(graphisme) ;
		this.getContentPane().add(zouhair);		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static ZoneGraphique getGraphisme()
	{
		return graphisme; 
	}
}

