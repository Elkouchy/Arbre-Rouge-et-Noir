import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Graphique extends JPanel implements ActionListener {
    private JButton ajout,suppr,recherche;
    private JTextField saisie;	
    private JLabel affiche;	
    Graphique(){
    	this.setBackground(Color.BLUE);
	saisie = new JTextField(5);
	ajout = new JButton("Ajout");
	suppr = new JButton("Supprimer");
	recherche= new JButton("Rechercher");
	affiche=new JLabel("");
	this.add(saisie);
	this.add(ajout);
	this.add(suppr);
	this.add(recherche);
	this.add(affiche);
	ajout.addActionListener(this);
	suppr.addActionListener(this);
	recherche.addActionListener(this);
    }
    
		public void actionPerformed(ActionEvent e)
     	{
			String Val;
            int intVal;
			Object o = e.getSource() ; 
        ArbreRN RNG= main.getArbre(); 
        affiche.setText("");
            if( o.equals(ajout) ) 
            {
            	if ( RNG.taille()<999)
            	{
	            		Val = saisie.getText();
		            	try 
		            	{
			            	intVal = Integer.parseInt(Val) ;
			            	if ((intVal>= -999)||(intVal<= 999)){
			            	if (!RNG.recherche_valeur(RNG.racine,new Noeud(intVal)))
			            			RNG.ajout(intVal);
			            	else
			             JOptionPane.showMessageDialog(null,"ce nombre existe dejà !!!","Erreur",JOptionPane.OK_OPTION);
			            	}
			            	else
			            		if (intVal<-999)
			            			JOptionPane.showMessageDialog(null,"Veuiller saisir un nombre superieur  à -1000","Erreur",JOptionPane.OK_OPTION);
			            		else
			            			JOptionPane.showMessageDialog(null,"Veuiller saisir un nombre inferieur à 1000","Erreur",JOptionPane.OK_OPTION);
			         	}
			         	catch(Exception erreur)
			         	{
			         		// cas sinon
			         		JOptionPane.showMessageDialog(null,"Erreur de saisie","Erreur",JOptionPane.OK_OPTION) ;     	
			         	}
					
				
		      	}
		      	else
		      	{
		      		JOptionPane.showMessageDialog(null,"Arbre limité a 999 valeur","Erreur",JOptionPane.OK_OPTION);
		      	}
	                	
	         	Zone.graphisme.repaint() ;
            }     
            else
            	if( o.equals(suppr) ) 
                {
                	Val = saisie.getText();
	            	try 
	            	{
	            		intVal = Integer.parseInt(Val) ;
	            		if (RNG.recherche_valeur(RNG.racine,new Noeud(intVal)))
		            	RNG.supprimer(intVal);
	            		else 
	            			JOptionPane.showMessageDialog(null,"ce nombre n'existe pas !!","Erreur",JOptionPane.OK_OPTION) ;   
		         	}
		         	catch(Exception erreur)
		         	{
		         		JOptionPane.showMessageDialog(null,"Erreur de saisie","Erreur",JOptionPane.OK_OPTION) ;     	
		         	}
		         	Zone.graphisme.repaint() ;
                } 
            	else{
            		if( o.equals(recherche) ) 
                {
            			try{
            			Val = saisie.getText();
            			intVal = Integer.parseInt(Val) ;
            			if((intVal> 999)||(intVal<-999))	JOptionPane.showMessageDialog(null,"Arbre limité dans l'intervalle de -999 à 999  !!!!","Erreur",JOptionPane.OK_OPTION);
            			else{
            			if (!RNG.recherche_valeur(RNG.racine,new Noeud(intVal)))
            				affiche.setText("ce nombre n'existe pas !!");
            			else	affiche.setText("ce nombre existe !!");}
            			}catch(Exception erreur)
    		         	{
    		         		JOptionPane.showMessageDialog(null,"Erreur de saisie","Erreur",JOptionPane.OK_OPTION) ;     	
    		         	}
            	}}
            saisie.setText(null);
            saisie.grabFocus();        
            RNG=null;            
        }
		
	
		
	}
