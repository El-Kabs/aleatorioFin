package aleatorio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class ingredientes {
	
	public static final String datosA = "./data/ingredientes.csv";
	public static void main(String[] args) throws IOException {
		ingredientes ii = new ingredientes();
		Long x=System.currentTimeMillis();
		ArrayList<String> nombres = ii.cargarNombresM(datosA);
		ArrayList<VOIngrediente> datos = new ArrayList<VOIngrediente>();
		for(int i = 0; i<=1000000; i++) {
			VOIngrediente agregar = new VOIngrediente();
			agregar.setIngrediente(ii.combinaciones(nombres));
			agregar.setDescripcion("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in.");
			agregar.setTraduccion("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in.");
			System.out.println(agregar.getIngrediente());
			System.out.println("Van "+i+" pirobos");
			datos.add(agregar);
		}
		
		ii.agregarCSV(datos);
		
		Long resta = System.currentTimeMillis()-x;
		System.out.println("Tiempo: "+resta); 
		
		
	}

	private String combinaciones(ArrayList<String> nombres) {
			double rand = Math.abs(Math.random()*nombres.size()-2);
			int randF = (int) rand;
			double rand2 = Math.abs(Math.random()*nombres.size()-2);
			int randF2 = (int) rand2;
			double rand3 = Math.abs(Math.random()*nombres.size()-2);
			int randF3 = (int) rand3;
			String parte1 = nombres.get(randF);
			String parte2 = nombres.get(randF2);
			String parte3 = nombres.get(randF3);
			
			String pFinal = parte1+" "+parte2+" "+parte3;
			return pFinal;
		
	}
	
	public void agregarCSV(ArrayList<VOIngrediente> datos) {
		String outputFile = "C:\\Users\\ka.babativa\\Documents\\ingredientes.csv";
		boolean alreadyExists = new File(outputFile).exists();

		if(alreadyExists){
			File Archivo = new File(outputFile);
			Archivo.delete();
		}        

		try {

			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');

			csvOutput.write("NOMBRE");
			csvOutput.write("DESCRIPCION");
			csvOutput.write("TRADUCCION");
			csvOutput.endRecord();

			for(int i = 0; i<datos.size(); i++){
				VOIngrediente user = datos.get(i);
				csvOutput.write(user.getIngrediente());
				csvOutput.write(user.getDescripcion());
				csvOutput.write(user.getTraduccion());
				csvOutput.endRecord();                   
			}

			csvOutput.close();

		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public ArrayList<String> cargarNombresM(String file) throws IOException{
		CsvReader nombresM = new CsvReader(file);
		nombresM.readHeaders();
		ArrayList<String> retorno = new ArrayList<String>();
		while(nombresM.readRecord()) {
			String nombre = nombresM.getRawRecord();
			String[] split = nombre.split(",");
			String algo = "";
			for(int i = 0; i<split.length; i++) {
				algo = split[i];
				retorno.add(algo);
				System.out.println("Nombre: "+algo);
			}
		}
		return retorno;
	}
}
