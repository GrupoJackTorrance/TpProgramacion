package clienteServidor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import logica.EfectoDarObjeto;
import logica.Jugador;
import logica.Main;
import logica.ObjDescuentaPuntos;
import logica.ObjDuplicarPuntaje;
import logica.ObjRobarPuntos;
import logica.Partida;
import logica.Sala;
import logica.Tablero;

public class VentanaPreIngreso extends JFrame {
	private JPanel contentPane;
	private JButton botonIngreso, botonJuego;
	static VentanaPreIngreso ventana;
	private JLabel label;
	
	
	public static void main(String[] args) {
		ventana= new VentanaPreIngreso();
		ventana.setVisible(true);
	}
	
	public VentanaPreIngreso() {
		setTitle("Como desea jugar?");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		
		label = new JLabel("Seleccione una modalidad de juego(Directo/ConIngreso)");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setBounds(100, 40, 300, 30);
		label.setVisible(true);
		contentPane.add(label);
		
		botonIngreso= new JButton("Ingreso");
		botonIngreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ventana.dispose();
					JVentanaIngreso.main(null);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		});
		botonIngreso.setBounds(130, 250, 100, 30);
		botonIngreso.setVisible(true);
		contentPane.add(botonIngreso);
		
		botonJuego= new JButton("Jugar");
		botonJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						Jugador jugador1 = new Jugador("pepe","Pedro");
						Jugador jugador2 = new Jugador("Dragon", "Maria");
						Jugador jugador3 = new Jugador("Rana", "Julian");
						Jugador jugador4 = new Jugador("Mono", "Marlen");
						EfectoDarObjeto robaPuntos = new ObjRobarPuntos();
						EfectoDarObjeto duplicaPuntaje = new ObjDuplicarPuntaje();
						EfectoDarObjeto descuentaPuntos = new ObjDescuentaPuntos();
						jugador1.setObjEfectos(descuentaPuntos);
						jugador2.setObjEfectos(duplicaPuntaje);
						jugador3.setObjEfectos(robaPuntos);
						jugador4.setObjEfectos(robaPuntos);
						jugador1.setObjEfectos(duplicaPuntaje);			
						int puntosobj = 40;
						Sala sala = jugador1.crearSala("Test", 4, puntosobj, 20);
						sala.addJugadorSala(jugador2);
						sala.addJugadorSala(jugador3);
						sala.addJugadorSala(jugador4);
						Partida partida1 = sala.crearPartida();
						Tablero table=partida1.elegirTablero();
						partida1.setTablero(table);		
						partida1.getTablero().getVentanaTablero().verTablero();
						partida1.InicioPartida();
						partida1.getTablero().getVentanaTablero().getPanelTablero().repaint();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
		botonJuego.setBounds(130, 250, 100, 30);
		botonJuego.setVisible(true);
		contentPane.add(botonJuego);
		add(contentPane);
	}
	
}
