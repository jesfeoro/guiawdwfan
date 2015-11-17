package modelo;

import java.util.ArrayList;
import java.util.Map;

public class Restaurante {
private String Nombre;
private String TipoRes;
private String ImagenP;
private String Descrip;
private String BDescrip;
private String ImagenG;
private String []Tipos;
private Map<Integer,Restaurante>caracter;
private String Caracteristica;
private String TipoC;
private String ValorC;
private int Nmenu;
private int Nulo2;
private String[] Tmenus;
private ArrayList<Restaurante> ListaR;
public Restaurante() {
	
}
public Restaurante(String nombre, String tipoRes, String imagenP) {
	super();
	Nombre = nombre;
	TipoRes = tipoRes;
	ImagenP = imagenP;
}

public Restaurante(String nombre, String bDescrip,
		String imagenG, String[]tmenus) {
	super();
	Nombre = nombre;
	BDescrip = bDescrip;
	ImagenG = imagenG;
	Tmenus = tmenus;
}

public Restaurante(String caracteristica, String tipoC, String valorC,
		int nulo1, int nulo2) {
	super();
	Caracteristica = caracteristica;
	TipoC = tipoC;
	ValorC = valorC;
	Nmenu = nulo1;
	Nulo2 = nulo2;
}
public String getNombre() {
	return Nombre;
}

public void setNombre(String nombre) {
	Nombre = nombre;
}
public String getTipoRes() {
	return TipoRes;
}
public void setTipoRes(String tipoRes) {
	TipoRes = tipoRes;
}
public String getImagenP() {
	return ImagenP;
}
public void setImagenP(String imagenP) {
	ImagenP = imagenP;
}

public String getDescrip() {
	return Descrip;
}
public void setDescrip(String descrip) {
	Descrip = descrip;
}
public String getBDescrip() {
	return BDescrip;
}
public void setBDescrip(String bDescrip) {
	BDescrip = bDescrip;
}
public String getImagenG() {
	return ImagenG;
}
public void setImagenG(String imagenG) {
	ImagenG = imagenG;
}

public String[] getTipos() {
	return Tipos;
}
public void setTipos(String[] tipos) {
	Tipos = tipos;
}

public Map<Integer, Restaurante> getCaracter() {
	return caracter;
}
public void setCaracter(Map<Integer, Restaurante> caracter) {
	this.caracter = caracter;
}

public String getCaracteristica() {
	return Caracteristica;
}
public void setCaracteristica(String caracteristica) {
	Caracteristica = caracteristica;
}
public String getTipoC() {
	return TipoC;
}
public void setTipoC(String tipoC) {
	TipoC = tipoC;
}
public String getValorC() {
	return ValorC;
}
public void setValorC(String valorC) {
	ValorC = valorC;
}

public int getNmenu() {
	return Nmenu;
}
public void setNmenu(int nmenu) {
	Nmenu = nmenu;
}
public int getNulo2() {
	return Nulo2;
}
public void setNulo2(int nulo2) {
	Nulo2 = nulo2;
}

public String[] getTmenus() {
	return Tmenus;
}
public void setTmenus(String[] tmenus) {
	Tmenus = tmenus;
}

public ArrayList<Restaurante> getListaR() {
	return ListaR;
}
public void setListaR(ArrayList<Restaurante> listaR) {
	ListaR = listaR;
}

}
