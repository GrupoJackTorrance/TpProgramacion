import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class PanelVentanaTablero extends JPanel{
	Tablero tablero;
	List<Jugador>jugadores;
	Graphics2D g2;
	JButton btnArriba = new JButton("Arriba");
	JButton btnAbajo = new JButton("Abajo");
	JButton btnDerecha = new JButton("Derecha");
	JButton btnIzquierda = new JButton("Izquierda");
	PixelJugador [] jugadoresGraficos;
	Jugador jugador;
	double anchoAlturaCasilla=50;
	double ubicacionX=40;
	double ubicacionY=60;
	private JLabel turnoDe=new JLabel("Turno de:");
	private JLabel dado=new JLabel("Salio el:");
	private int altura=477;
	private JLabel turnoJugador=new JLabel("");
	
	public PanelVentanaTablero(Tablero tablero) {
		btnAbajo.setLocation(200, 200);
		btnIzquierda.setLocation(150, 200);
		btnDerecha.setLocation(100, 200);
		btnArriba.setLocation(2500, 200);
		
		btnArriba.setVisible(false);
		btnAbajo.setVisible(false);
		btnIzquierda.setVisible(false);
		btnDerecha.setVisible(false);
		Botones botonesListener=new Botones();
		btnDerecha.addActionListener(botonesListener);
		btnIzquierda.addActionListener(botonesListener);
		btnAbajo.addActionListener(botonesListener);
		btnArriba.addActionListener(botonesListener);
		add(btnAbajo);
		add(btnArriba);
		add(btnDerecha);
		add(btnIzquierda);
		add(turnoDe);
		
		//add(dado);
		add(turnoJugador);
		
		this.tablero=tablero;
		this.jugadores=tablero.getJugadores();
		jugadoresGraficos=new PixelJugador[this.jugadores.size()];
		for(int i=0 ; i<jugadoresGraficos.length;i++) {
			jugadoresGraficos[i]=new PixelJugador(this.jugadores.get(i));
		}
	}
		
public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g2= (Graphics2D)g;
	
	setBackground(Color.lightGray);
	//dado.setLocation(550,487);
	
	turnoDe.setLocation(500,500);
	turnoJugador.setLocation(570,500);
	Rectangle2D rectangulo=new Rectangle2D.Double(120,100,anchoAlturaCasilla,anchoAlturaCasilla);
	int filasMapa=tablero.mapa.length;
	int columnasMapa=tablero.mapa[0].length;
	
	//dibujo el tablero
	for(int i=0;i<filasMapa;i++) {
		for(int j=0;j<columnasMapa;j++) {
			rectangulo.setFrame(ubicacionX+(j*anchoAlturaCasilla),ubicacionY+(i*anchoAlturaCasilla), anchoAlturaCasilla, anchoAlturaCasilla);
			if(tablero.mapa[i][j]!=null) {
			g2.setPaint(tablero.mapa[i][j].getColor());
			g2.fill(rectangulo);
			g2.setPaint(Color.BLACK);
			g2.draw(rectangulo);
			}
			
			//dibujo a los jugadores
			Rectangle2D figJugador=new Rectangle2D.Double(120,100,anchoAlturaCasilla/2,anchoAlturaCasilla/2);
			int h=0;
			for (PixelJugador jugador : jugadoresGraficos) {
				figJugador.setFrame(jugador.pixelX,jugador.pixelY, anchoAlturaCasilla/2, anchoAlturaCasilla/2);
				if(h==0) {
					g2.setPaint(Color.BLUE);
					h++;
				}else if(h==1) {
					g2.setPaint(Color.ORANGE);
					h++;
				}else if(h==2) {
					g2.setPaint(Color.PINK);
					h++;
				}else {
					g2.setPaint(Color.WHITE);
				}
				g2.fill(figJugador);		
			}
			
			int v=0;
			h=0;
			Rectangle2D figJugadorP=new Rectangle2D.Double(120,100,anchoAlturaCasilla/2,anchoAlturaCasilla/2);
			for (PixelJugador jugador : jugadoresGraficos) {
				figJugadorP.setFrame(215,490+v, anchoAlturaCasilla/4, anchoAlturaCasilla/4);
				if(h==0) {
					g2.setPaint(Color.BLUE);
					h++;
				}else if(h==1) {
					g2.setPaint(Color.ORANGE);
					h++;
				}else if(h==2) {
					g2.setPaint(Color.PINK);
					h++;
				}else {
					g2.setPaint(Color.WHITE);
				}
				g2.fill(figJugadorP);		
				v+=30;
			
			}
			g2.setColor(Color.DARK_GRAY);
			g2.setFont(new Font("Dialog", Font.BOLD, 14));
			
			for (int k = 0, y=0; k < jugadores.size(); k++,y+=30) {
				g2.drawString(jugadores.get(k).getNombre()+"         Score   " + 
				jugadores.get(k).getPuntos() , 250, 500+y);
			}
			
			
			
		
		}
	
		
		
	}

	
	
}

private class PixelJugador{
	double pixelX;
	double pixelY;
	public PixelJugador(Jugador jugador) {
		this.pixelX=ubicacionX+(anchoAlturaCasilla/4)+jugador.getLugarTableroY()*(anchoAlturaCasilla);
		this.pixelY=ubicacionY+(anchoAlturaCasilla/4)+jugador.getLugarTableroX()*(anchoAlturaCasilla);
	}
	public void setearUbicaciones(double pixelX,double pixelY) {
		this.pixelX=pixelX;
		this.pixelY=pixelY;
	}
	
	
}
public void movimientoJugador(Jugador jugador,String direccion) throws InterruptedException {
	
	int indexJugador= jugadores.indexOf(jugador);
	long millis=(25);
	double inicio;
	double fin;
	//muevo al jugador pixel por pixel desde el inicio hasta el fin
	if(direccion.equals("arriba")) {
		inicio=jugadoresGraficos[indexJugador].pixelY;
		fin=jugadoresGraficos[indexJugador].pixelY-anchoAlturaCasilla;
		for(double j=inicio;j>=fin;j--) {
			jugadoresGraficos[indexJugador].setearUbicaciones(jugadoresGraficos[indexJugador].pixelX,j);
			repaint();
			Thread.sleep(millis);
		}
	}
	else if(direccion.equals("abajo")) {
		inicio=jugadoresGraficos[indexJugador].pixelY;
		fin=jugadoresGraficos[indexJugador].pixelY+anchoAlturaCasilla;
		for(double j=inicio;j<=fin;j++) {
			jugadoresGraficos[indexJugador].setearUbicaciones(jugadoresGraficos[indexJugador].pixelX,j);
			repaint();
			Thread.sleep(millis);
		}
	}
	else if(direccion.equals("izquierda")) {
		inicio=jugadoresGraficos[indexJugador].pixelX;
		fin = jugadoresGraficos[indexJugador].pixelX-anchoAlturaCasilla;
		for(double j=inicio;j>=fin;j--) {
			jugadoresGraficos[indexJugador].setearUbicaciones(j,jugadoresGraficos[indexJugador].pixelY);
			repaint();
			Thread.sleep(millis);
		}
	}
	else if(direccion.equals("derecha")) {
		inicio=jugadoresGraficos[indexJugador].pixelX;
		fin=jugadoresGraficos[indexJugador].pixelX+anchoAlturaCasilla;
		for(double j=inicio;j<=fin;j++) {
			jugadoresGraficos[indexJugador].setearUbicaciones(j,jugadoresGraficos[indexJugador].pixelY);
			repaint();
			Thread.sleep(millis);
		}
	}
				
}

public void mostrarOpciones(String[] direcciones, Jugador jugador) throws InterruptedException {
	int i=1;
	
	//--------------C R E A C I O N     DE      B O T O N E S-----------------------
	this.jugador=jugador;		
		
		for (String string : direcciones) {
			if(i%3==0 && string != null) {
				if(string.equals("arriba")) {
					btnArriba.setVisible(true);
					btnArriba.setBounds(250, 300, 89, 23);
					add(btnArriba);
				}
				if(string.equals("abajo")) {
					btnAbajo.setVisible(true);
					btnAbajo.setBounds(280, 300, 89, 23);
					add(btnAbajo);
				}
					
				if(string.equals("izquierda")) {
					btnIzquierda.setVisible(true);
					btnIzquierda.setBounds(150, 300, 89, 23);	
					add(btnIzquierda);
				}
					
				if(string.equals("derecha")) {
					btnAbajo.setBounds(280, 300, 89, 23);
					btnDerecha.setVisible(true);
				}
					
			}	
			
			i++;
		}
		Thread.sleep(5000);
		btnAbajo.setVisible(false);
		btnArriba.setVisible(false);
		btnDerecha.setVisible(false);
		btnIzquierda.setVisible(false);
		
	}
class Botones implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnArriba) {
			jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
			jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
			jugador.setLugarTableroX(jugador.getLugarTableroX() - 1);
			try {
				movimientoJugador(jugador, "arriba");
			} catch (InterruptedException e1) {
				// TODO Bloque catch generado automáticamente
				e1.printStackTrace();
			}
			
			btnArriba.setVisible(false);
			btnAbajo.setVisible(false);
			btnDerecha.setVisible(false);
			btnIzquierda.setVisible(false);
			
		}
		if(e.getSource()==btnAbajo) {
			jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
			jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
			jugador.setLugarTableroX(jugador.getLugarTableroX() +1);
			try {
				movimientoJugador(jugador, "abajo");
			} catch (InterruptedException e1) {
				// TODO Bloque catch generado automáticamente
				e1.printStackTrace();
			}
			
			btnArriba.setVisible(false);
			btnAbajo.setVisible(false);
			btnDerecha.setVisible(false);
			btnIzquierda.setVisible(false);
			
		}
		 if(e.getSource()==btnDerecha) {
			 jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
				jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
				jugador.setLugarTableroY(jugador.getLugarTableroY() + 1);
				try {
					movimientoJugador(jugador, "derecha");
				} catch (InterruptedException e1) {
					// TODO Bloque catch generado automáticamente
					e1.printStackTrace();
				}
				
				btnArriba.setVisible(false);
				btnAbajo.setVisible(false);
				btnDerecha.setVisible(false);
				btnIzquierda.setVisible(false);
		
		}
		if(e.getSource()==btnIzquierda) {
			jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
			jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
			jugador.setLugarTableroY(jugador.getLugarTableroY() - 1);
			try {
				movimientoJugador(jugador, "izquierda");
			} catch (InterruptedException e1) {
				// TODO Bloque catch generado automáticamente
				e1.printStackTrace();
			}
			
			btnArriba.setVisible(false);
			btnAbajo.setVisible(false);
			btnDerecha.setVisible(false);
			btnIzquierda.setVisible(false);
			
		}
		
	}
	
	
}

	public void setearTurnoJugador(Jugador jugador) {
		turnoDe.setVisible(true);
		turnoJugador.setText(jugador.getNombre());
		turnoJugador.setVisible(true);
	}

}


