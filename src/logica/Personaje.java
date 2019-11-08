package logica;
public class Personaje{
	private String nombre;
	private double x, y;
	private int radio;
	
	public Personaje(String nombre, double x, double y, int radio) {
		this.nombre=nombre;
		this.x=x;
		this.y=y;
		this.radio = radio;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}

}
