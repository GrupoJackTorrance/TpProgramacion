package clienteServidor;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import logica.AbstractAdapter;
import logica.EfectoDarObjeto;
import logica.Sala;

public class VentanaLobby  extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8896812117446574226L;
	private static Socket socketCliente;	
	
	public static Socket getSocketCliente() {
		return socketCliente;
	}

	public static void setSocketCliente(Socket socketCliente) {
		VentanaLobby.socketCliente = socketCliente;
	}

	PanelLobby panel= new PanelLobby();
	public static void main(String[] args) {
		//VentanaLobby ventana= new VentanaLobby(socketCliente);
	}

	public VentanaLobby(Socket socketCliente) {
		this.socketCliente= socketCliente;
		add(panel);
		setSize(500,500);
		setVisible(true);
	}
}
class PanelLobby extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2763110189112444391L;
	JButton unirseSala,crearSala,salir,aceptarSala,aceptar;
	JList<String> opcionesSalas= new JList<String>();
	JLabel etiquetaSalaEspera= new JLabel("Se creo la sala");
	DefaultListModel<String> opcionesSalas2 = new DefaultListModel<String>();
	private JScrollPane scrollLista= new JScrollPane();;
	List<String>salasDisponibles= new ArrayList<String>();
	private JTextField nombreSala,rondasMax, puntosObjetivos,cantJugadores;
	private JLabel labelnombre,labelrondasMax, labelpuntosObjetivos, labelcantJugadores;
	private JLabel salasDisp= new JLabel("Salas Disponibles");
	
	public PanelLobby() {
		unirseSala=new JButton("Unirse a una sala");
		crearSala=new JButton("Crear sala");
		salir=new JButton("Salir");
		aceptarSala=new JButton("Aceptar Sala");
		aceptar= new JButton("Aceptar");
		Botones botones = new Botones();
		etiquetaSalaEspera.setVisible(false);
		add(etiquetaSalaEspera);
		aceptar.addActionListener(botones);
		unirseSala.addActionListener(botones);
		crearSala.addActionListener(botones);
		salir.addActionListener(botones);
		opcionesSalas.setVisible(false);
		aceptarSala.setVisible(false);
		aceptar.setVisible(false);
		scrollLista.setVisible(false);
		//componentes del formulario de crear sala
		labelnombre= new JLabel("");
		labelcantJugadores= new JLabel("");
		labelpuntosObjetivos= new JLabel("");
		labelrondasMax= new JLabel("");
		nombreSala= new JTextField();
		puntosObjetivos= new JTextField();
		rondasMax= new JTextField();
		cantJugadores= new JTextField();
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
		
		add(scrollLista);
		add(opcionesSalas);
		salasDisp.setVisible(false);
		add(salasDisp);
		//Botones
		add(aceptar);
		add(aceptarSala);
		add(unirseSala);
		add(crearSala);
		add(salir);
		
	}
public void paintComponent(Graphics g) {
	super.paintComponent(g);
	
	labelnombre.setBounds(100, 100, 86, 20);
	labelcantJugadores.setBounds(100, 150, 100, 20);
	labelpuntosObjetivos.setBounds(100, 200, 86, 20);
	labelrondasMax.setBounds(100, 250, 86, 20);
	nombreSala.setBounds(200, 100, 86, 20);
	cantJugadores.setBounds(200,150 , 86, 20);
	puntosObjetivos.setBounds(200, 200, 86, 20);
	rondasMax.setBounds(200, 250, 86, 20);
	aceptar.setBounds(350, 400, 100, 20);
	aceptarSala.setBounds(350, 400, 130, 20);
	scrollLista.setBounds(20,50,300, 300);
	scrollLista.setViewportView(opcionesSalas);
	salasDisp.setLocation(20,10);
}

public void mostrarSalasDisponibles() {
	for (String salasDisp : salasDisponibles) {
		opcionesSalas2.addElement(salasDisp);
	}
	opcionesSalas.setModel(opcionesSalas2);
	opcionesSalas.setLocation(200,200);
	opcionesSalas.setVisible(true);
	aceptarSala.setVisible(true);
	repaint();
}

class Botones implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== unirseSala) {
			try {
				//comunicarse con el servidor para pedirle la lista de Salas
				DataOutputStream flujoSalida= new DataOutputStream(VentanaLobby.getSocketCliente().getOutputStream());
				PaqueteMensaje mensaje= new PaqueteMensaje("mostrarSalas",null);
				Gson gson = new Gson();				
				flujoSalida.writeUTF(gson.toJson(mensaje));				
				// recibir Salas
				DataInputStream flujoEntrada= new DataInputStream(VentanaLobby.getSocketCliente().getInputStream());
				String entrada=flujoEntrada.readUTF();
				
				salasDisponibles=gson.fromJson(entrada,ArrayList.class);				
				visualizarUnirseASala();
				flujoSalida.close();
				flujoEntrada.close();
				 
				// cuando se apreta el boton " aceptar" se envia la opcione elegida		
			} catch (IOException e1) {
				// TODO Bloque catch generado automáticamente
				e1.printStackTrace();
			}
		}
		else if (e.getSource()== crearSala) {
			try {
				visualizarFormularioCrearSala();
				DataOutputStream flujoSalida= new DataOutputStream(VentanaLobby.getSocketCliente().getOutputStream());
				PaqueteMensaje mensaje= new PaqueteMensaje("crearSala", "nombreSala");
				GsonBuilder builder = new GsonBuilder();
				builder.registerTypeAdapter(EfectoDarObjeto.class, new AbstractAdapter());
				Gson gson = builder.create();
				flujoSalida.writeUTF(gson.toJson(mensaje));
				DataInputStream flujoEntrada= new DataInputStream(VentanaLobby.getSocketCliente().getInputStream());
				String respuesta=flujoEntrada.readUTF();
				//visibilizarSalaEspera(respuesta);
			} catch (IOException e1) {
				// TODO Bloque catch generado automáticamente
				e1.printStackTrace();
			}
			
		}
       else if (e.getSource()== salir) {
			System.out.println("salir");
		}
       else if ( e.getSource() == aceptarSala) {
    	   opcionesSalas.setVisible(false);
    	   aceptarSala.setVisible(false);
    	   int index= opcionesSalas.getSelectedIndex();
    	   String nombreSala= (String) opcionesSalas.getSelectedValue(); // es la opcion seleccionada en el momento que se apreto "aceptar"
    	   DataOutputStream flujoSalida;
    	   
		try {
			flujoSalida = new DataOutputStream(VentanaLobby.getSocketCliente().getOutputStream());
			// envio la sala seleccionada al servidor
			Gson gson = new Gson();			
			//String mensaje= gson.toJson(salasDisponibles.(nombreSala));
			flujoSalida.writeUTF(nombreSala);
			
		} catch (IOException e2) {
			// TODO Bloque catch generado automáticamente
			e2.printStackTrace();
		}
		
       }else if ( e.getSource() == aceptar) {
    	   
    	   
       }
		
	}
	
}

public void visualizarFormularioCrearSala() {
	unirseSala.setVisible(false);
	crearSala.setVisible(false);
	salir.setVisible(false);
	labelnombre.setText("Nombre: ");
	labelcantJugadores.setText("Cant Jugadores:");
	labelpuntosObjetivos.setText("Puntos Obj: ");
	labelrondasMax.setText("Cant Rondas: ");
	
	labelcantJugadores.setVisible(true);
	labelpuntosObjetivos.setVisible(true);
	labelrondasMax.setVisible(true);
	labelnombre.setVisible(true);
	nombreSala.setVisible(true);
	rondasMax.setVisible(true);
	puntosObjetivos.setVisible(true);
	cantJugadores.setVisible(true);
	aceptar.setVisible(true);
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

public void visibilizarSalaEspera(String valoresActuales) {
	GsonBuilder builder = new GsonBuilder();
	builder.registerTypeAdapter(EfectoDarObjeto.class, new AbstractAdapter());
	Gson gson = builder.create();
	etiquetaSalaEspera.setVisible(true);
	Sala sala= gson.fromJson(valoresActuales, Sala.class);
	//opcionesSalas.setVisible(true);
	//opcionesSalas.addItem(String.valueOf(sala.getId()));
}
	
}