import java.awt.*;

import javax.swing.*;

/* s'il n'y a pas de probleme ..!!
 Merci de bien changer les noms des classes  !!
Merci  :)
*/
public class ZoneGraphique extends JPanel {
	private static int Xini, Yini, hauteurArbre, largeurArbre ;
    private static Dimension taille ;
    private static Color  couleur_racine=Color.BLACK , couleur_branche=Color.BLACK, couleur_Text=Color.WHITE;  
    private static int largeur_text ;
    private final static int rayon=12 ;   
    private final static int V_ecart_default = 40 ;
    private final static int V_ecart_min = rayon*2 ;
    private final static int H_ecart_default = 24 ;
    private final static int H_ecart_min = rayon*2 ;  
    private static int hauteur, largeur, pasX ; 
    
	public ZoneGraphique()
	{
		setPreferredSize(new Dimension(780,450));
		setBackground(Color.white);		
	}	
	private static int puissance( int a, int b)
	{
		if (b==0)
			return 1;
		else
			return a*puissance(a,b-1);
	}
	
	public void paint(Graphics g)
	{
		hauteurArbre = (main.getArbre().Hauteur()-1)*V_ecart_default+2*rayon + 30;		
        if ((main.getArbre().Hauteur()-1)>=0)
        	largeurArbre = ZoneGraphique.puissance(2, main.getArbre().Hauteur()-1)*H_ecart_default+2*rayon;
        else
        	largeurArbre = ZoneGraphique.puissance(2, 0)*H_ecart_default+2*rayon;
		largeur=largeurArbre;
		if(largeur<=780) largeur=780;
		hauteur=hauteurArbre;		
        if(hauteur<=450) hauteur=450;
		taille = new Dimension(largeur,hauteur);		
		setPreferredSize(taille) ;
		setBackground(Color.white) ;
		super.paintComponent(g) ;  
        super.revalidate() ;
        Xini = largeur/2;      
        Yini = 30;
        pasX=largeurArbre/2 ;            
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON) ;  
        dessiner(g2, main.getArbre().racine, Xini, Yini, pasX) ;  
    }
   
	
    private static void dessiner(Graphics2D g, Noeud n, int x, int y, int pasX)
    {   
        if(n!=null)
        {   
            if(n.gauche!=null)
            {
                g.setColor(couleur_branche); 
                g.drawLine(x,y,x-pasX/2, y+V_ecart_default) ;
                dessiner(g, n.gauche, x-pasX/2, y+V_ecart_default, pasX/2);                
            }
            if(n.droit!=null)
            {
                g.setColor(couleur_branche);
                g.drawLine(x,y,x+pasX/2, y+V_ecart_default);
                dessiner(g, n.droit, x+pasX/2, y+V_ecart_default, pasX/2);                
            }
            faire_noeud(g, n, x, y);            	
        }
    }
    
    private static void faire_noeud(Graphics2D g, Noeud n, int x, int y)   
    {
    
    		if((n.info> 999)||(n.info<-999)){
    	   g.setColor(Color.BLACK);       
           g.fillOval(x-rayon , y-rayon, 2*rayon, 2*rayon);
           g.setColor(Color.BLACK);
           g.drawOval(x-rayon , y-rayon, 2*rayon, 2*rayon);
    	}
    		else{
        g.setColor(n.couleur);        
        g.fillOval(x-rayon , y-rayon, 2*rayon, 2*rayon);
        g.setColor(Color.black);
        g.drawOval(x-rayon , y-rayon, 2*rayon, 2*rayon); 
        largeur_text = g.getFontMetrics().stringWidth(""+n.info+"");
        g.setColor(couleur_Text);
        g.drawString(""+n.info+"",x-largeur_text/2,y+5);       
        }
    }
}   	
