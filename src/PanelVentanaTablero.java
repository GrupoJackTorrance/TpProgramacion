import java.awt.Color;
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
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelVentanaTablero extends JPanel{
	Tablero tablero;
	List<Jugador>jugadores;
	Graphics2D g2;
	PixelJugador [] jugadoresGraficos;
	double anchoAlturaCasilla=50;
	double ubicacionX=40;
	double ubicacionY=60;
	public PanelVentanaTablero(Tablero tablero) {
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
			for (PixelJugador jugador : jugadoresGraficos) {
				figJugador.setFrame(jugador.pixelX,jugador.pixelY, anchoAlturaCasilla/2, anchoAlturaCasilla/2);
				g2.setPaint(Color.BLACK);
				g2.fill(figJugador);		
				
			
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
		for(double j=inicio;j>fin;j--) {
			jugadoresGraficos[indexJugador].setearUbicaciones(jugadoresGraficos[indexJugador].pixelX,j);
			repaint();
			Thread.sleep(millis);
		}
	}
	else if(direccion.equals("abajo")) {
		inicio=jugadoresGraficos[indexJugador].pixelY;
		fin=jugadoresGraficos[indexJugador].pixelY+anchoAlturaCasilla;
		for(double j=inicio;j<fin;j++) {
			jugadoresGraficos[indexJugador].setearUbicaciones(jugadoresGraficos[indexJugador].pixelX,j);
			repaint();
			Thread.sleep(millis);
		}
	}
	else if(direccion.equals("izquierda")) {
		inicio=jugadoresGraficos[indexJugador].pixelX;
		fin = jugadoresGraficos[indexJugador].pixelX-anchoAlturaCasilla;
		for(double j=inicio;j>fin;j--) {
			jugadoresGraficos[indexJugador].setearUbicaciones(j,jugadoresGraficos[indexJugador].pixelY);
			repaint();
			Thread.sleep(millis);
		}
	}
	else if(direccion.equals("derecha")) {
		inicio=jugadoresGraficos[indexJugador].pixelX;
		fin=jugadoresGraficos[indexJugador].pixelX+anchoAlturaCasilla;
		for(double j=inicio;j<fin;j++) {
			jugadoresGraficos[indexJugador].setearUbicaciones(j,jugadoresGraficos[indexJugador].pixelY);
			repaint();
			Thread.sleep(millis);
		}
	}
				
}

public void mostrarOpciones(String[] direcciones, Jugador jugador) throws InterruptedException {
	int i=1;
	
	//--------------C R E A C I O N     DE      B O T O N E S-----------------------
		JButton btnArriba = new JButton("Arriba");
		JButton btnAbajo = new JButton("Abajo");
		JButton btnDerecha = new JButton("Derecha");
		JButton btnIzquierda = new JButton("Izquierda");
		
		btnArriba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
					jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
					jugador.setLugarTableroX(jugador.getLugarTableroX() - 1);
					movimientoJugador(jugador, "arriba");
					
//					btnArriba.setVisible(false);
//					btnAbajo.setVisible(false);
//					btnDerecha.setVisible(false);
//					btnIzquierda.setVisible(false);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnAbajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
					jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
					jugador.setLugarTableroX(jugador.getLugarTableroX() + 1);
					movimientoJugador(jugador, "abajo");
//					btnArriba.setVisible(false);
//					btnAbajo.setVisible(false);
//					btnDerecha.setVisible(false);
//					btnIzquierda.setVisible(false);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
					jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
					jugador.setLugarTableroY(jugador.getLugarTableroY() + 1);
					movimientoJugador(jugador, "derecha");
//					btnArriba.setVisible(false);
//					btnAbajo.setVisible(false);
//					btnDerecha.setVisible(false);
//					btnIzquierda.setVisible(false);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
					jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
					jugador.setLugarTableroY(jugador.getLugarTableroY() - 1);
					movimientoJugador(jugador, "izquierda");
//					btnArriba.setVisible(false);
//					btnAbajo.setVisible(false);
//					btnDerecha.setVisible(false);
//					btnIzquierda.setVisible(false);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnArriba.setBounds(300, 500, 89, 23);
		add(btnArriba);
		btnAbajo.setBounds(300, 600, 89, 23);
		add(btnAbajo);
		btnIzquierda.setBounds(200, 550, 89, 23);	
		add(btnIzquierda);
		btnDerecha.setBounds(400, 550, 89, 23);
		add(btnDerecha);
		
		btnArriba.setVisible(false);
		btnAbajo.setVisible(false);
		btnDerecha.setVisible(false);
		btnIzquierda.setVisible(false);
	
		repaint();
		
		for (String string : direcciones) {
			if(i%3==0 && string != null) {
				if(string.equals("arriba")) {
					btnArriba.setVisible(true);
//					btnArriba.setBounds(300, 500, 89, 23);
//					add(btnArriba);
				}
				if(string.equals("abajo")) {
					btnAbajo.setVisible(true);
//					btnAbajo.setBounds(300, 600, 89, 23);
//					add(btnAbajo);
				}
					
				if(string.equals("izquierda")) {
					btnIzquierda.setVisible(true);
//					btnIzquierda.setBounds(200, 550, 89, 23);	
//					add(btnIzquierda);
				}
					
				if(string.equals("derecha")) {
					btnDerecha.setVisible(true);
//					btnDerecha.setBounds(400, 550, 89, 23);
//					add(btnDerecha);
				}
					
			}	
			
			i++;
		}
		Thread.sleep(5000);
		btnArriba.setVisible(false);
		btnAbajo.setVisible(false);
		btnDerecha.setVisible(false);
		btnIzquierda.setVisible(false);
	}

}


