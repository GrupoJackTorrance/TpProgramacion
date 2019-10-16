import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

//EN PRINCIPIO ESTA CLASE NO SE ESTÁ USANDO. LA DEJO POR LAS DUDAS.
public class PanelTablero extends JPanel{

	ArrayList <Personaje> personajes = new ArrayList<>();

	public PanelTablero(/*Personaje personaje*/) {
		personajes.add(new Personaje ("Rata", 100, 100, 10));
		personajes.add(new Personaje ("Serpiente", 200, 100, 10));
		personajes.add(new Personaje ("Tarantula", 100, 200, 10));
		personajes.add(new Personaje ("Murcielago", 200, 200, 10));
	}

	public void paintComponent(Graphics g) {
		int i;
		for (i=0; i<personajes.size(); i++) {
			g.setColor(Color.YELLOW);
			g.fillOval((int) personajes.get(i).getX(), (int) personajes.get(i).getY(), (int) personajes.get(i).getRadio(),
					(int) personajes.get(i).getRadio());
		}
	}

	public Personaje getPersonaje(String nombrePersonaje) {
		int i=0;
		
		while(i<personajes.size() && personajes.get(i).getNombre() != nombrePersonaje)
			i++;
		
		return personajes.get(i);
	}
	
	public void moverPersonaje(Personaje personaje) {
		//this.personaje = personaje;//hace un for buscando al personaje 
	}
}
