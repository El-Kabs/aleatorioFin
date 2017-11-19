package aleatorio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class productos {
	public static final String datosA = "./data/foods.csv";
	
	public static void main(String[] args) throws IOException {
		productos pp = new productos();
		Long x=System.currentTimeMillis();
		ArrayList<String> nombres = pp.cargarNombres(datosA);
		
		ArrayList<String> nombres2 = new ArrayList<>();
		for(int i = 0; i<1000000; i++){
			String nombre = pp.putearTodo(nombres);
			nombres2.add(nombre);
		}
		
		LinkedHashSet<String> prueba = new LinkedHashSet<String>();
		ArrayList<String> repetidos = new ArrayList<String>();
		
		for(int i = 0; i<nombres2.size(); i++){
			if(prueba.add(nombres2.get(i))==false){
				repetidos.add(nombres2.get(i));
			}
		}
		
		for(int i = 0; i<repetidos.size(); i++){
			System.out.println("Repetido: "+repetidos.get(i));
		}
		
		ArrayList<String> listaF = new ArrayList<String>(prueba);
		ArrayList<VOProducto> listaFF = new ArrayList<VOProducto>();
		
		
		for(int i = 0; i<listaF.size(); i++){
			VOProducto agregar = new VOProducto();
			agregar.setProducto(listaF.get(i));
			agregar.setInformacion("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in.");
			agregar.setPreparacion("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in.");
			agregar.setTraduccion("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in.");
			agregar.setCategoria(pp.categoria());
			agregar.setCosto("2000");
			agregar.setPrecio("5000");
			System.out.println("Nombre: "+agregar.producto);
			System.out.println("Van: "+i+" hijueputas.");
			listaFF.add(agregar);
		}
		
		pp.agregarCSV(listaFF);
		Long resta = System.currentTimeMillis()-x;
		System.out.println("Primeros: "+nombres.size());
		System.out.println("Tamaño del leer: "+nombres2.size());
		System.out.println("Tamaño final: "+listaF.size());
		System.out.println("Repetidos: "+repetidos.size());
		System.out.println("Tiempo: "+resta); 
		
		
	}
	
	public void agregarCSV(ArrayList<VOProducto> datos) {
		String outputFile = "C:\\Users\\ka.babativa\\Documents\\productos.csv";
		boolean alreadyExists = new File(outputFile).exists();

		if(alreadyExists){
			File Archivo = new File(outputFile);
			Archivo.delete();
		}        

		try {

			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');

			csvOutput.write("NOMBRE");
			csvOutput.write("PREPARACION");
			csvOutput.write("TRADUCCION");
			csvOutput.write("INFORMACION");
			csvOutput.write("CATEGORIA");
			csvOutput.write("COSTO");
			csvOutput.write("PRECIO");
			csvOutput.endRecord();

			for(int i = 0; i<datos.size(); i++){
				VOProducto user = datos.get(i);
				csvOutput.write(user.getProducto());
				csvOutput.write(user.getPreparacion());
				csvOutput.write(user.getTraduccion());
				csvOutput.write(user.getInformacion());
				csvOutput.write(user.getCategoria());
				csvOutput.write(user.getCosto());
				csvOutput.write(user.getPrecio());
				csvOutput.endRecord();                   
			}

			csvOutput.close();

		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	
	public ArrayList<String> cargarNombres(String file) throws IOException{
		CsvReader nombresH = new CsvReader(file);
		nombresH.readHeaders();
		ArrayList<String> retorno = new ArrayList<String>();
		while(nombresH.readRecord()) {
			String nombre = nombresH.get("name");
			retorno.add(nombre);
			System.out.println("Nombre: "+nombre);
		}
		return retorno;
	}
	
	public String putearTodo(ArrayList<String> nombres){
		double rand = Math.abs(Math.random()*nombres.size()-2);
		int randF = (int) rand;
		double rand2 = Math.abs(Math.random()*nombres.size()-2);
		int randF2 = (int) rand2;
		double rand3 = Math.abs(Math.random()*nombres.size()-2);
		int randF3 = (int) rand3;
		double rand4 = Math.abs(Math.random()*nombres.size()-2);
		int randF4 = (int) rand4;
		double rand5 = Math.abs(Math.random()*nombres.size()-2);
		int randF5 = (int) rand5;
		
		String parte1 = nombres.get(randF);
		String parte2 = nombres.get(randF2);
		String parte3 = nombres.get(randF3);
		String parte4 = nombres.get(randF4);
		String parte5 = nombres.get(randF5);
		
		String pFinal = parte1+" "+parte2+" "+parte3+" "+parte4+" "+parte5;
		return pFinal;
	}
	
	public String categoria(){
		double numero = Math.random()*6;
		int numeroF = (int) numero;
		String categoria = "";
		if(numeroF==0){
			categoria = "ENTRADA";
		}
		else if(numeroF==1){
			categoria = "PLATO_FUERTE";
		}
		else if(numeroF==2){
			categoria = "BEBIDA";
		}
		else if(numeroF==3){
			categoria = "POSTRE";
		}
		else if(numeroF==1){
			categoria = "ACOMPAÑAMIENTO";
		}
		else{
			categoria = "ENTRADA";
		}
		return categoria;
	}
	
}
