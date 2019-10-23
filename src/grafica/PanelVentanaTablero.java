package grafica;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import java.awt.geom.Rectangle2D;


import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.*;

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
	private int anchoAlturaCasilla = 60;
	private int ubicacionX = 40;
	private int ubicacionY = 100;
	private JLabel turnoDe = new JLabel("Turno de: ");
	private JLabel textdado = new JLabel("");
	private JLabel textTurno = new JLabel("");
	private JLabel textTurnoJugador = new JLabel("");
	private JLabel textPuntos = new JLabel("");
	private JLabel turnoJugador = new JLabel("");
	private JLabel elegirLado = new JLabel("Que camino quieres seguir?");
	private JLabel objetos= new JLabel("");
	private ImageIcon imagen = new ImageIcon(getClass().getResource("../fondoLejos.jpg"));
	private JLabel dado = new JLabel("");
	private JLabel moneda = new JLabel("");
	private JLabel numerodado = new JLabel("");
	private JLabel tirodado = new JLabel("Tiro dado");
	private ImageIcon imagen2 = new ImageIcon(getClass().getResource("../moneda2.jpg"));

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
		add(tirodado);
		add(textTurno);
		add(textTurnoJugador);
		add(moneda);
		add(textdado);
		add(dado);
		add(textPuntos);
		add(numerodado);

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

		elegirLado.setLocation(200, 3);
		btnAbajo.setLocation(300,55);
		btnIzquierda.setLocation(210, 30);
		btnDerecha.setLocation(370, 30);
		btnArriba.setLocation(300, 30);
	
		textTurno.setLocation(400, 0);
		textTurnoJugador.setLocation(400, 40);
		
		
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
		
		numerodado.setFont(new Font("Tahoma", Font.BOLD,50));
		
		textTurno.setFont(new Font("Dialog", Font.BOLD, 50));
		textTurnoJugador.setFont(new Font("Dialog", Font.BOLD, 50));
		
		textTurno.setForeground(new Color(204,006,005));
		textTurnoJugador.setForeground(new Color(204,006,005));
		
		Dimension height = getSize();
		g.drawImage(imagen.getImage(), 0, 0, height.width, height.height, null);
		
		int filasMapa = this.tablero.getMapa().length;
		int columnasMapa =this.tablero.getMapa()[0].length;
		
		// dibujo el tablero
		for (int i = 0; i < filasMapa; i++) {
			for (int j = 0; j < columnasMapa; j++) {
				if (this.tablero.getMapa()[i][j] != null) {
					int x=(int) (ubicacionX + (j * anchoAlturaCasilla));
					int y=(int) (ubicacionY + (i * anchoAlturaCasilla));
					g2.drawImage(this.tablero.getMapa()[i][j].getImagen(),x,y,anchoAlturaCasilla, anchoAlturaCasilla,null);
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
					figJugadorP.setFrame(30 + v, 600 , anchoAlturaCasilla / 4, anchoAlturaCasilla / 4);
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
					v += 200;

				}
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("Tahoma", Font.BOLD, 16));

				for (int k = 0, y = 0; k < jugadores.size(); k++, y += 200) {
					g2.drawString(jugadores.get(k).getNombre()+" ",50 + y, 615);
					g2.drawImage(imagen2.getImage(), 50 + y, 615,null);
					g2.drawString(" "+jugadores.get(k).getPuntos(),80 + y, 635);
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

		public void mostrarModificacionPts(int cantidad) throws InterruptedException {
			textPuntos.setLocation(400, 20);
			textPuntos.setOpaque(false);
			textPuntos.setFont(new Font("Tahoma", Font.BOLD, 30));
			moneda.setOpaque(false);
			moneda.setIcon(new ImageIcon(getClass().getResource("../moneda2.jpg")));
			moneda.setLocation(400,20);
			moneda.setVisible(true);
			if(cantidad > 0)
				textPuntos.setText("+" + cantidad);
			else
				textPuntos.setText(" " + cantidad);
			textPuntos.setVisible(true);
			Thread.sleep(1200);
			textPuntos.setVisible(false);
			moneda.setVisible(false);
			
		}


		public void setearObjetos(Jugador jugador) {
			objetos.setText("Objetos: "+jugador.getObjEfectos()+" ");
			objetos.setVisible(true);
		}
	
		public void empiezaTurno(Jugador jugador) throws InterruptedException {
			textTurno.setText("Empieza: ");
			textTurnoJugador.setText(jugador.getNombre()+" ");
			textTurno.setVisible(true);
			textTurnoJugador.setVisible(true);
			Thread.sleep(1000);
			textTurno.setVisible(false);
			textTurnoJugador.setVisible(false);
		}
		
		public int tiraDado(Jugador jugador) throws InterruptedException {
			tirodado.setLocation(jugador.getLugarTableroX()-10, jugador.getLugarTableroY()-10);
			tirodado.setVisible(true);
			dado.setIcon(new ImageIcon("gif_mario.gif"));
			dado.setLocation(jugador.getLugarTableroX(), jugador.getLugarTableroY());
			dado.setVisible(true);
			int cantidad=jugador.tirarDado();
			Thread.sleep(2000);
			numerodado.setText(cantidad+" ");
			numerodado.setLocation(jugador.getLugarTableroX(), jugador.getLugarTableroY());
			dado.setVisible(false);
			numerodado.setVisible(true);
			Thread.sleep(1000);
			tirodado.setVisible(false);
			numerodado.setVisible(false);
			return cantidad;
		}
		
		
		
}

