package clienteServidor;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.google.gson.Gson;

import clienteServidor.PanelLobby.Botones;
import grafica.VentanaTablero;
import logica.Dado;
import logica.Jugador;
import logica.Partida;
import logica.Ronda;
import logica.Tablero;

public class PanelLobby extends JPanel {
	private static final long serialVersionUID = 2763110189112444391L;
	JButton unirseSala, crearSala, salir, aceptarSala, aceptarCrearSala, iniciarPartida, salirEspera;
	JList<String> opcionesSalas = new JList<String>();
	JLabel etiquetaSalaEspera = new JLabel("Se creo la sala");
	DefaultListModel<String> opcionesSalas2 = new DefaultListModel<String>();
	private JScrollPane scrollLista = new JScrollPane();;
	private List<String> salasDisponibles = new ArrayList<String>();
	
	private JTextField nombreSala, rondasMax, puntosObjetivos, tinfoPartida;
	private JLabel labelnombre, labelrondasMax, labelpuntosObjetivos, labelcantJugadores;
	private JLabel salasDisp = new JLabel("Salas Disponibles");
	private JLabel tcrearSala = new JLabel("Crear Sala");
	private JLabel MensajeSalaEspera = new JLabel("Espere a que se Inicie la partida");
	private JComboBox<String> cantJugadores = new JComboBox<String>();

	WindowListener exitListener;
	VentanaTablero ventana;
	
	public  void setSalaDisponibles(List<String> nuevas){
		this.salasDisponibles=nuevas;
		
	}
	
	public  void settinfoPartida(String nuevo){
		tinfoPartida.setText(nuevo);	
	}
	
	public void mostrarPartida(String partida){
		System.out.println("llegue");
		System.out.println(partida);
		String nombrePartida=partida.split(";")[0];
		int maxRondas=Integer.parseInt(partida.split(";")[1]);
		int puntosObjetivo=Integer.parseInt(partida.split(";")[2]);
		int cantJugadores=Integer.parseInt(partida.split(";")[3]);
		List<Jugador> jugadores=new LinkedList<Jugador>();
		int limite=(cantJugadores*2)+4;
		
		for (int i = 4; i < limite; i+=2) {
			Jugador n=new Jugador(partida.split(";")[i],partida.split(";")[i+1]);
			jugadores.add(n);
		}
		
		Partida p = new Partida(nombrePartida,maxRondas,puntosObjetivo,cantJugadores,jugadores);
		Tablero table=p.elegirTablero();
		p.setTablero(table);		
		p.getTablero().getVentanaTablero().verTablero();
		p.setRonda(new Ronda(p.getTurnos())); 
		p.determinarOrdenTurno(p.getJugadores());
		VentanaPartida ventanaJuego= new VentanaPartida(VentanaLobby.getSocketClienteServidor(),VentanaLobby.getSocketServidorCliente(),p,new Dado());
	}
	
	
	public PanelLobby() {
		iniciarPartida = new JButton("Iniciar Partida");
		unirseSala = new JButton("Unirse a una sala");
		crearSala = new JButton("Crear sala");
		salir = new JButton("Salir");
		aceptarSala = new JButton("Aceptar Sala");
		aceptarCrearSala = new JButton("Aceptar");
		salirEspera = new JButton("Salir");
		Botones botones = new Botones();
		etiquetaSalaEspera.setVisible(false);
		add(etiquetaSalaEspera);
		iniciarPartida.addActionListener(botones);
		salirEspera.addActionListener(botones);
		aceptarSala.addActionListener(botones);
		aceptarCrearSala.addActionListener(botones);
		unirseSala.addActionListener(botones);
		crearSala.addActionListener(botones);
		salir.addActionListener(botones);
		tinfoPartida = new JTextField();
		tinfoPartida.setVisible(false);
		opcionesSalas.setVisible(false);
		aceptarSala.setVisible(false);
		aceptarCrearSala.setVisible(false);
		scrollLista.setVisible(false);
		iniciarPartida.setVisible(false);
		salirEspera.setVisible(false);
		// componentes del formulario de crear sala
		labelnombre = new JLabel("");
		labelcantJugadores = new JLabel("");
		labelpuntosObjetivos = new JLabel("");
		labelrondasMax = new JLabel("");
		nombreSala = new JTextField();
		puntosObjetivos = new JTextField();
		rondasMax = new JTextField();

		cantJugadores.addItem("2");
		cantJugadores.addItem("3");
		cantJugadores.addItem("4");
		nombreSala.setVisible(false);
		rondasMax.setVisible(false);
		puntosObjetivos.setVisible(false);
		cantJugadores.setVisible(false);
		add(labelnombre);
		add(labelcantJugadores);
		add(labelpuntosObjetivos);
		add(labelrondasMax);
		add(nombreSala);
		add(puntosObjetivos);
		add(rondasMax);
		add(cantJugadores);
		add(tinfoPartida);

		add(scrollLista);
		add(opcionesSalas);
		salasDisp.setVisible(false);
		add(salasDisp);
		tcrearSala.setVisible(false);
		add(tcrearSala);
		MensajeSalaEspera.setVisible(false);
		add(MensajeSalaEspera);
		// Botones
		add(aceptarCrearSala);
		add(aceptarSala);
		add(unirseSala);
		add(crearSala);
		add(salir);
		add(iniciarPartida);
		add(salirEspera);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		labelnombre.setBounds(80, 100, 86, 20);
		labelcantJugadores.setBounds(78, 150, 150, 20);
		labelpuntosObjetivos.setBounds(80, 200, 86, 20);
		labelrondasMax.setBounds(80, 250, 86, 20);
		nombreSala.setBounds(200, 100, 86, 20);
		cantJugadores.setBounds(200, 150, 86, 20);
		puntosObjetivos.setBounds(200, 200, 86, 20);
		rondasMax.setBounds(200, 250, 86, 20);
		aceptarCrearSala.setBounds(350, 400, 100, 20);
		aceptarSala.setBounds(350, 400, 130, 20);
		scrollLista.setBounds(20, 50, 300, 300);
		scrollLista.setViewportView(opcionesSalas);
		salasDisp.setLocation(20, 10);
		tcrearSala.setLocation(20, 10);
		iniciarPartida.setBounds(250, 400, 200, 20);
		MensajeSalaEspera.setBounds(100, 250, 200, 20);
		tinfoPartida.setBounds(20, 50, 250, 100);
		salirEspera.setBounds(100, 400, 100, 20);
		unirseSala.setLocation(10, 10);
		crearSala.setLocation(200, 10);
		salir.setLocation(300, 10);
	}

	public void cerrarConexion() {
		try {
			VentanaLobby.getSocketClienteServidor().close();
			VentanaLobby.salir();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarSalasDisponibles() {
		for (String salasDisp : salasDisponibles) {
			//opcionesSalas2.addElement(salasDisp.substring(15, salasDisp.indexOf(",") - 1));
			opcionesSalas2.addElement(salasDisp);
		
		}
		opcionesSalas.setModel(opcionesSalas2);
		opcionesSalas.setLocation(200, 200);
		opcionesSalas.setVisible(true);
		aceptarSala.setVisible(true);
		repaint();
	}

	class Botones implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == iniciarPartida) {
				try {
					// comunicarse con el servidor para iniciar partida
					DataOutputStream flujoSalida = new DataOutputStream(
							VentanaLobby.getSocketClienteServidor().getOutputStream());
					PaqueteMensaje mensaje = new PaqueteMensaje("iniciarPartida", nombreSala.getText());
					Gson gson = new Gson();
					flujoSalida.writeUTF(gson.toJson(mensaje));
					/*// recibe algo para iniciar la partida a nivel grafico
					/*DataInputStream flujoEntrada = new DataInputStream(
							VentanaLobby.getsocketClienteServidor().getInputStream());
					String entrada = flujoEntrada.readUTF();
					if (entrada.split(";")[0].equals("InicioPartida")) {
						System.out.println("entro a la verdadera accion");
						String partida=(String)entrada;
						String part=partida.substring(partida.indexOf(';')+1);
						System.out.println(part);
						VentanaLobby.panel.mostrarPartida(part);
						//setVisible(false);
						//System.exit(1);
						
					}*/

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == unirseSala) {
				try {
					// comunicarse con el servidor para pedirle la lista de Salas
					DataOutputStream flujoSalida = new DataOutputStream(
							VentanaLobby.getSocketClienteServidor().getOutputStream());
					PaqueteMensaje mensaje = new PaqueteMensaje("mostrarSalas", null);
					Gson gson = new Gson();
					flujoSalida.writeUTF(gson.toJson(mensaje));
					// recibir Salas
					DataInputStream flujoEntrada = new DataInputStream(
							VentanaLobby.getSocketClienteServidor().getInputStream());
					String entrada = flujoEntrada.readUTF();

					salasDisponibles = gson.fromJson(entrada, ArrayList.class);
					visualizarUnirseASala();

					// cuando se apreta el boton " aceptar" se envia la opcion elegida
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (e.getSource() == crearSala) {
				visualizarFormularioCrearSala();
			} else if (e.getSource() == salir) {
				System.out.println("salir");
				DataOutputStream flujoSalida;
				try {
					flujoSalida = new DataOutputStream(VentanaLobby.getSocketClienteServidor().getOutputStream());
					PaqueteMensaje mensaje = new PaqueteMensaje("Salir", null);
					Gson gson = new Gson();
					flujoSalida.writeUTF(gson.toJson(mensaje));
					DataInputStream flujoEntrada = new DataInputStream(
							VentanaLobby.getSocketClienteServidor().getInputStream());
					// String entrada=flujoEntrada.readUTF();
					// if(entrada.equals("OK"))
					cerrarConexion();
				} catch (IOException e2) {
					e2.printStackTrace();
				}

			} else if (e.getSource() == aceptarSala) {
				System.out.println(
						"Se verifica que se puede ingresar a la sala seleccionada luego de presionar aceptar.");
				opcionesSalas.setVisible(false);
				aceptarSala.setVisible(false);

				int index = opcionesSalas.getSelectedIndex();
				String nombreSala = (String) opcionesSalas.getSelectedValue(); // es la opcion seleccionada en el
																				// momento que se apreto "aceptar"
//    	   String elegida = salasDisponibles.get

				try {
					DataOutputStream flujoSalida = new DataOutputStream(
							VentanaLobby.getSocketClienteServidor().getOutputStream());
					// envio la sala seleccionada al servidor
					PaqueteMensaje mensaje = new PaqueteMensaje("Unirse", nombreSala);
					System.out.println("Sala seleccionada: " + salasDisponibles.get(index));
					Gson gson = new Gson();
					DataInputStream flujoEntrada = new DataInputStream(
							VentanaLobby.getSocketClienteServidor().getInputStream());
					
					flujoSalida.writeUTF(gson.toJson(mensaje));
//		
					String respuesta = flujoEntrada.readUTF();
					visibilizarSalaEspera(respuesta,2, "unirse");
				} catch (IOException e2) {
					e2.printStackTrace();
				}

			} else if (e.getSource() == aceptarCrearSala) {
				dejarDeMostrarFormulario();
				try {
					Gson gson = new Gson();
					DataOutputStream flujoSalida = new DataOutputStream(
							VentanaLobby.getSocketClienteServidor().getOutputStream());
					String datosSala =nombreSala.getText()+";"+cantJugadores.getSelectedItem()+";"+puntosObjetivos.getText()+";"+rondasMax.getText();
									
					PaqueteMensaje mensaje2 = new PaqueteMensaje("crearSala",datosSala);// nombreSala.getText());
//   
					flujoSalida.writeUTF(gson.toJson(mensaje2));
					DataInputStream flujoEntrada = new DataInputStream(
							VentanaLobby.getSocketClienteServidor().getInputStream());
					String respuesta = flujoEntrada.readUTF();
					String infoSala = "Sala:"+respuesta+"   Jugadores: 1/"+cantJugadores.getSelectedItem();
					visibilizarSalaEspera(infoSala,1, "creadorSala");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			} else if (e.getSource() == salirEspera) {
				System.out.println("Sale de la espera (se canso de esperar a q inicien la partida)");
				DataOutputStream flujoSalida;
				try {
					flujoSalida = new DataOutputStream(VentanaLobby.getSocketClienteServidor().getOutputStream());
					PaqueteMensaje mensaje = new PaqueteMensaje("sacarJugadorSala", nombreSala.getText());
					Gson gson = new Gson();
					flujoSalida.writeUTF(gson.toJson(mensaje));
					visualizarLobby();
				} catch (IOException e2) {
					e2.printStackTrace();
				}

			}

		}

	}
	

	public void visualizarFormularioCrearSala() {
		unirseSala.setVisible(false);
		crearSala.setVisible(false);
		salir.setVisible(false);
		labelnombre.setText("Nombre: ");
		labelcantJugadores.setText("Cant Max Jugadores:");
		labelpuntosObjetivos.setText("Puntos Obj: ");
		labelrondasMax.setText("Cant Rondas: ");
		tcrearSala.setFont(new Font("Tahoma", Font.BOLD, 15));
		tcrearSala.setVisible(true);
		labelcantJugadores.setVisible(true);
		labelpuntosObjetivos.setVisible(true);
		labelrondasMax.setVisible(true);
		labelnombre.setVisible(true);

		nombreSala.setVisible(true);
		rondasMax.setVisible(true);
		puntosObjetivos.setVisible(true);
		cantJugadores.setVisible(true);

		aceptarCrearSala.setVisible(true);
	}

	public void dejarDeMostrarFormulario() {
		labelcantJugadores.setVisible(false);
		labelpuntosObjetivos.setVisible(false);
		labelrondasMax.setVisible(false);
		labelnombre.setVisible(false);
		nombreSala.setVisible(false);
		rondasMax.setVisible(false);
		puntosObjetivos.setVisible(false);
		cantJugadores.setVisible(false);
		aceptarCrearSala.setVisible(false);
	}

	public void visualizarUnirseASala() {
		unirseSala.setVisible(false);
		crearSala.setVisible(false);
		salir.setVisible(false);
		scrollLista.setVisible(true);
		salasDisp.setFont(new Font("Tahoma", Font.BOLD, 15));
		salasDisp.setVisible(true);
		mostrarSalasDisponibles();
		aceptarSala.setVisible(true);
	}

	public void visibilizarSalaEspera(String sala,int cant, String quien) {
		System.out.println("Entro a la sala de espera");
		tcrearSala.setVisible(false);
		labelcantJugadores.setVisible(false);
		labelpuntosObjetivos.setVisible(false);
		labelrondasMax.setVisible(false);
		labelnombre.setVisible(false);
		nombreSala.setVisible(false);
		rondasMax.setVisible(false);
		puntosObjetivos.setVisible(false);
		cantJugadores.setVisible(false);
		aceptarCrearSala.setVisible(false);
		unirseSala.setVisible(false);
		crearSala.setVisible(false);
		salir.setVisible(false);
		salirEspera.setVisible(true);
		tinfoPartida.setEditable(false);
		tinfoPartida.setText(sala);
		tinfoPartida.setVisible(true);
		scrollLista.setVisible(false);
		salasDisp.setVisible(false);
		salasDisp.setText("Sala");
		salasDisp.setVisible(true);
		if(cant==1)
			iniciarPartida.setEnabled(false);
		else
			iniciarPartida.setEnabled(true);
		
		if (quien.equals("creadorSala"))
			iniciarPartida.setVisible(true);
		else
			MensajeSalaEspera.setVisible(true);
	}
	

	
	public void repintarSalaEspera(int cant) {
		System.out.println("Se repinto la sala de espera");
		tcrearSala.setVisible(false);
		labelcantJugadores.setVisible(false);
		labelpuntosObjetivos.setVisible(false);
		labelrondasMax.setVisible(false);
		labelnombre.setVisible(false);
		nombreSala.setVisible(false);
		rondasMax.setVisible(false);
		puntosObjetivos.setVisible(false);
		cantJugadores.setVisible(false);
		aceptarCrearSala.setVisible(false);
		unirseSala.setVisible(false);
		crearSala.setVisible(false);
		salir.setVisible(false);
		salirEspera.setVisible(true);
		tinfoPartida.setEditable(false);
		tinfoPartida.setVisible(true);
		scrollLista.setVisible(false);
		salasDisp.setVisible(false);
		salasDisp.setText("Sala");
		salasDisp.setVisible(true);
		if(cant==1)
			iniciarPartida.setEnabled(false);
		else
			iniciarPartida.setEnabled(true);
		repaint();
	}

	public void visualizarLobby() {
		tcrearSala.setVisible(false);
		labelcantJugadores.setVisible(false);
		labelpuntosObjetivos.setVisible(false);
		labelrondasMax.setVisible(false);
		labelnombre.setVisible(false);
		nombreSala.setVisible(false);
		rondasMax.setVisible(false);
		puntosObjetivos.setVisible(false);
		cantJugadores.setVisible(false);
		aceptarCrearSala.setVisible(false);
		unirseSala.setVisible(false);
		crearSala.setVisible(false);
		salir.setVisible(false);
		salirEspera.setVisible(false);
		tinfoPartida.setVisible(false);
		scrollLista.setVisible(false);
		salasDisp.setVisible(false);
		iniciarPartida.setVisible(false);
		MensajeSalaEspera.setVisible(false);
		unirseSala.setVisible(true);
		crearSala.setVisible(true);
		salir.setVisible(true);
		repaint();
	}

}
