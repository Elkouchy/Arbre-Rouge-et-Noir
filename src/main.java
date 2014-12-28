import javax.swing.JFrame;

public class main {

    private static ArbreRN  RN = new ArbreRN();
	
	public static ArbreRN getArbre()
	{
		return RN;
	}
    
	public static void main(String[] args) {
		// s'il est possible de faire un peu de changement .. !! Merci bien  Zouhair ELKOUCHI
        JFrame.setDefaultLookAndFeelDecorated(true);
        new Zone();   

	}

}
