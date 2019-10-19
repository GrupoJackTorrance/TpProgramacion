package grafica;
import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import java.awt.geom.Rectangle2D;


import java.util.List;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import logica.Jugador;
import logica.Tablero;

public class PanelVentanaTablero extends JPanel {

	private Tablero tablero;

	private List<Jugador> jugadores;
	private Graphics2D g2;
	
	private JButton btnArriba = new JButton("Arriba");
	private JButton btnAbajo = new JButton("Abajo");
	private JButton btnDerecha = new JButton("Derecha");
	private JButton btnIzquierda = new JButton("Izquierda");
	private PixelJugador[] jugadoresGraficos;
	private Jugador jugador;
	private double anchoAlturaCasilla = 50;
	private double ubicacionX = 40;
	private double ubicacionY = 100;
	private JLabel turnoDe = new JLabel("Turno de: ");
	private JLabel textdado = new JLabel("");
	private JLabel turnoJugador = new JLabel("");
	private JLabel elegirLado = new JLabel("Que camino quieres seguir?");
	private JLabel objetos= new JLabel("");


	public PanelVentanaTablero(Tablero tablero) {
		
		btnArriba.setVisible(false);
		btnAbajo.setVisible(false);
		btnIzquierda.setVisible(false);
		btnDerecha.setVisible(false);
		Botones botonesListener = new Botones();
		btnDerecha.addActionListener(botonesListener);
		btnIzquierda.addActionListener(botonesListener);
		btnAbajo.addActionListener(botonesListener);
		btnArriba.addActionListener(botonesListener);
		add(btnAbajo);
		add(btnArriba);
		add(btnDerecha);
		add(btnIzquierda);
		add(turnoDe);
		add(objetos);

		add(textdado);

		add(turnoJugador);

		this.tablero = tablero;
		
		this.jugadores = tablero.getJugadores();
		jugadoresGraficos = new PixelJugador[this.jugadores.size()];
		for (int i = 0; i < jugadoresGraficos.length; i++) {
			jugadoresGraficos[i] = new PixelJugador(this.jugadores.get(i));
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		
		setBackground(new Color(174,174,65));

		elegirLado.setLocation(200, 3);
		btnAbajo.setLocation(300,55);
		btnIzquierda.setLocation(210, 30);
		btnDerecha.setLocation(370, 30);
		btnArriba.setLocation(300, 30);
	

		textdado.setLocation(10, 25);
		turnoDe.setLocation(10, 5);
		turnoJugador.setLocation(90, 5);
		objetos.setLocation(10, 45);
		textdado.setOpaque(false);
		turnoDe.setOpaque(false);
		turnoJugador.setOpaque(false);
		textdado.setFont(new Font("Tahoma", Font.BOLD, 15));
		turnoDe.setFont(new Font("Tahoma", Font.BOLD, 15));
		turnoJugador.setFont(new Font("Tahoma", Font.BOLD, 15));
		elegirLado.setFont(new Font("Tahoma", Font.BOLD, 15));
		objetos.setFont(new Font("Tahoma", Font.BOLD, 15));

		Rectangle2D rectangulo = new Rectangle2D.Double(120, 100, anchoAlturaCasilla, anchoAlturaCasilla);
		//Rectangle2D rectangulo = new Rectangle2D.Double(120, 100, anchoAlturaCasilla, anchoAlturaCasilla);

		int filasMapa = this.tablero.getMapa().length;
		int columnasMapa =this.tablero.getMapa()[0].length;
		
		// dibujo el tablero
		for (int i = 0; i < filasMapa; i++) {
			for (int j = 0; j < columnasMapa; j++) {
				rectangulo.setFrame(ubicacionX + (j * anchoAlturaCasilla), ubicacionY + (i * anchoAlturaCasilla),
						anchoAlturaCasilla, anchoAlturaCasilla);
				if (this.tablero.getMapa()[i][j] != null) {
					g2.setPaint(this.tablero.getMapa()[i][j].getColor());
					g2.fill(rectangulo);
					g2.setPaint(Color.BLACK);
					g2.draw(rectangulo);
				}

				// dibujo a los jugadores
				Ellipse2D figJugador = new Ellipse2D.Double(120, 100, anchoAlturaCasilla / 2, anchoAlturaCasilla / 2);
				int h = 0;
				for (PixelJugador jugador : jugadoresGraficos) {
					figJugador.setFrame(jugador.pixelX, jugador.pixelY, anchoAlturaCasilla / 2, anchoAlturaCasilla / 2);
					if (h == 0) {
						g2.setPaint(Color.BLUE);
						h++;
					} else if (h == 1) {
						g2.setPaint(Color.ORANGE);
						h++;
					} else if (h == 2) {
						g2.setPaint(Color.PINK);
						h++;
					} else {
						g2.setPaint(Color.WHITE);
					}
					g2.fill(figJugador);
				}

				int v = 0;
				h = 0;
				Ellipse2D figJugadorP = new Ellipse2D.Double(120, 100, anchoAlturaCasilla / 2, anchoAlturaCasilla / 2);
				for (PixelJugador jugador : jugadoresGraficos) {
					figJugadorP.setFrame(215, 540 + v, anchoAlturaCasilla / 4, anchoAlturaCasilla / 4);
					if (h == 0) {
						g2.setPaint(Color.BLUE);
						h++;
					} else if (h == 1) {
						g2.setPaint(Color.ORANGE);
						h++;
					} else if (h == 2) {
						g2.setPaint(Color.PINK);
						h++;
					} else {
						g2.setPaint(Color.WHITE);
					}
					g2.fill(figJugadorP);
					v += 30;

				}
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("Tahoma", Font.BOLD, 16));

				for (int k = 0, y = 0; k < jugadores.size(); k++, y += 30) {
					g2.drawString(jugadores.get(k).getNombre() + "         Puntos   " + jugadores.get(k).getPuntos(),
							250, 550 + y);
				}

			}

		}

	}

	private class PixelJugador {
		double pixelX;
		double pixelY;

		public PixelJugador(Jugador jugador) {
			this.pixelX = ubicacionX + (anchoAlturaCasilla / 4) + jugador.getLugarTableroY() * (anchoAlturaCasilla);
			this.pixelY = ubicacionY + (anchoAlturaCasilla / 4) + jugador.getLugarTableroX() * (anchoAlturaCasilla);
		}

		public void setearUbicaciones(double pixelX, double pixelY) {
			this.pixelX = pixelX;
			this.pixelY = pixelY;
		}

	}

	public void movimientoJugador(Jugador jugador, String direccion) throws InterruptedException {

		int indexJugador = jugadores.indexOf(jugador);
		long millis = (25);
		double inicio;
		double fin;
		// muevo al jugador pixel por pixel desde el inicio hasta el fin
		if (direccion.equals("arriba")) {
			inicio = jugadoresGraficos[indexJugador].pixelY;
			fin = jugadoresGraficos[indexJugador].pixelY - anchoAlturaCasilla;
			for (double j = inicio; j >= fin; j--) {
				jugadoresGraficos[indexJugador].setearUbicaciones(jugadoresGraficos[indexJugador].pixelX, j);
				repaint();
				Thread.sleep(millis);
			}
		} else if (direccion.equals("abajo")) {
			inicio = jugadoresGraficos[indexJugador].pixelY;
			fin = jugadoresGraficos[indexJugador].pixelY + anchoAlturaCasilla;
			for (double j = inicio; j <= fin; j++) {
				jugadoresGraficos[indexJugador].setearUbicaciones(jugadoresGraficos[indexJugador].pixelX, j);
				repaint();
				Thread.sleep(millis);
			}
		} else if (direccion.equals("izquierda")) {
			inicio = jugadoresGraficos[indexJugador].pixelX;
			fin = jugadoresGraficos[indexJugador].pixelX - anchoAlturaCasilla;
			for (double j = inicio; j >= fin; j--) {
				jugadoresGraficos[indexJugador].setearUbicaciones(j, jugadoresGraficos[indexJugador].pixelY);
				repaint();
				Thread.sleep(millis);
			}
		} else if (direccion.equals("derecha")) {
			inicio = jugadoresGraficos[indexJugador].pixelX;
			fin = jugadoresGraficos[indexJugador].pixelX + anchoAlturaCasilla;
			for (double j = inicio; j <= fin; j++) {
				jugadoresGraficos[indexJugador].setearUbicaciones(j, jugadoresGraficos[indexJugador].pixelY);
				repaint();
				Thread.sleep(millis);
			}
		}

	}

	public void mostrarOpciones(String[] direcciones, Jugador jugador,int cantidad) throws InterruptedException {
		int i = 1;

		
		this.jugador = jugador;
		add(elegirLado);
		elegirLado.setText(elegirLado.getText()+" te quedan "+cantidad + " movimientos");
		elegirLado.setVisible(true);
		
		for (String string : direcciones) {
			if (i % 3 == 0 && string != null) {
				if (string.equals("arriba")) {
					btnArriba.setVisible(true);
				//	btnArriba.setBounds(250, 300, 89, 23);
				//	add(btnArriba);
				}
				if (string.equals("abajo")) {
					btnAbajo.setVisible(true);
				//	btnAbajo.setBounds(280, 300, 89, 23);
				//	add(btnAbajo);
				}

				if (string.equals("izquierda")) {
					btnIzquierda.setVisible(true);
				//	btnIzquierda.setBounds(150, 300, 89, 23);
				//	add(btnIzquierda);
				}

				if (string.equals("derecha")) {
					btnDerecha.setVisible(true);
				}

			}

			i++;
		}

		/*
		 * Thread.sleep(5000);
		 * 
		 * Thread.sleep(6000); elegirLado.setVisible(false);
		 * 
		 * btnAbajo.setVisible(false); btnArriba.setVisible(false);
		 * btnDerecha.setVisible(false); btnIzquierda.setVisible(false);
		 */
		synchronized (jugador) {
			jugador.wait();
		}

	}

	class Botones implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnArriba) {
				jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
				jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
				jugador.setLugarTableroX(jugador.getLugarTableroX() - 1);
				elegirLado.setText("Que camino quieres seguir?");

				elegirLado.setVisible(false);
				btnArriba.setVisible(false);
				btnAbajo.setVisible(false);
				btnDerecha.setVisible(false);
				btnIzquierda.setVisible(false);

				try {
					movimientoJugador(jugador, "arriba");
				} catch (InterruptedException e1) {
					// TODO Bloque catch generado automáticamente
					e1.printStackTrace();
					
				}

				synchronized (jugador) {
					jugador.notify();
				}

			}
			if (e.getSource() == btnAbajo) {
				jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
				jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
				jugador.setLugarTableroX(jugador.getLugarTableroX() + 1);
				elegirLado.setText("Que camino quieres seguir?");
				elegirLado.setVisible(false);
				btnArriba.setVisible(false);
				btnAbajo.setVisible(false);
				btnDerecha.setVisible(false);
				btnIzquierda.setVisible(false);
				try {
					movimientoJugador(jugador, "abajo");
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				synchronized (jugador) {
					jugador.notify();
				}
			}
			if (e.getSource() == btnDerecha) {
				jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
				jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
				jugador.setLugarTableroY(jugador.getLugarTableroY() + 1);
				elegirLado.setText("Que camino quieres seguir?");
				btnArriba.setVisible(false);
				btnAbajo.setVisible(false);
				btnDerecha.setVisible(false);
				btnIzquierda.setVisible(false);
				elegirLado.setVisible(false);
				try {
					movimientoJugador(jugador, "derecha");
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				synchronized (jugador) {
					jugador.notify();
				}

			}
			if (e.getSource() == btnIzquierda) {
				jugador.setPosicionAnteriorX(jugador.getLugarTableroX());
				jugador.setPosicionAnteriorY(jugador.getLugarTableroY());
				jugador.setLugarTableroY(jugador.getLugarTableroY() - 1);
				elegirLado.setText("Que camino quieres seguir?");
				elegirLado.setVisible(false);
				btnArriba.setVisible(false);
				btnAbajo.setVisible(false);
				btnDerecha.setVisible(false);
				btnIzquierda.setVisible(false);
				try {
					movimientoJugador(jugador, "izquierda");
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				synchronized (jugador) {
					jugador.notify();
				}

			}

		}
	}

		public void setearTurnoJugador(Jugador jugador) {
			turnoDe.setVisible(true);
			turnoJugador.setText(jugador.getNombre());
			turnoJugador.setVisible(true);
		}

		public void mostrardado(int cantidad) {
			textdado.setText("Dado: " + cantidad);
		}

		public void setearObjetos(Jugador jugador) {
			objetos.setText("Objetos: "+jugador.getObjEfectos()+" ");
			objetos.setVisible(true);
		}
	
}
