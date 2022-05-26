package model;

import java.io.*;
import java.util.*;
import model.Nomina;

public class Archivos {

	// Metodo que permite Escribir a un archivo de texto	
		public static void generarNomina(Nomina nom){
			//llena la lista con todos los salario de la siguiente forma (nombre,id,salario)
			List<String> nomina=nom.calcularSalario();
			try{
	 	      	//se crea un flujo para escribir el archivo Reporte.txt
	        	OutputStreamWriter out= new OutputStreamWriter(new FileOutputStream("Reporte.txt"));
	        	for (int i=0; i<nomina.size(); i++){
	        		String cad=nomina.get(i);
	        		out.write(cad + "\n");	
				}
	        	out.close();
			    }
	    		catch(Exception e){
	    			System.out.println("Ocurrio un error escribiendo a un archivo el reporte: " + e); 	
                        }	
		}

		//carga los empleados
		public static Nomina cargarEmpleados(){
			//crea una nomina vacia para llenarla con el archivo de texto
			Nomina nom=new Nomina();
			try{
				//crea un flujo para leer el archivo de texto Nomina.txt
			InputStreamReader input=new InputStreamReader(new FileInputStream("Nomina.txt"));
			BufferedReader fa=new BufferedReader(input);

			//lee toda la primera linea, que contiene los datos de el primer empleado
			String cad=fa.readLine();
			
			// Se lee hasta el FIN
			while (!cad.equalsIgnoreCase("FIN")){	
				
				//elimina todos los espacios adionales
				cad=UtilidadCadenas.limpiarCadena(cad);
				
				//se divide la linea leida cada vez que se encuentra un %
				String[] tokens=UtilidadCadenas.tokenizador(cad,"%");
				// En la posicion 0 queda el nombre del empleado, en la 1 queda su identificacion y en la 2 su tipo (profesor, monitor o empleado)

				//Si el tipo es Profesor
				if (tokens[2].equalsIgnoreCase("Profesor")){
					//Se lee la siguiente cadena para conocer su escalafon
					String esc=fa.readLine();
					// Se crea el objeto Profesor y se adiciona a la lista de empleados de Nomina
					
					Profesor p=nom.adicionarProfesor(tokens[0], Integer.parseInt(tokens[1]), 0, tokens[2], esc);
					//se empieza a leer la siguiente linea que contiene la priemra asigantura del profesor
					String cur=fa.readLine(); 
					
					//Mientras haya cursos a leer
					while (!cur.equalsIgnoreCase("#")){
						cur=UtilidadCadenas.limpiarCadena(cur);
						//se separa la linea cada vez que encuentre una ,
						String[] asignaturas=UtilidadCadenas.tokenizador(cur,",");
						
						//Se adiciona a lista de asignaturas del profesor que acabamos de crear
						p.adicionarAsignatura(asignaturas[0], Integer.parseInt(asignaturas[1]));

						// Se lee la siguiente linea para seguir leyendo asignaturas o llegar al "#" y cambiar de empleado
						cur=fa.readLine(); 
					}
				}
				//si es un monitor
				if (tokens[2].equalsIgnoreCase("Monitor")){

					Monitor m=nom.adicionarMonitor(tokens[0], Integer.parseInt(tokens[1]), 0, tokens[2]);
					//Se lee la siguiente l�nea para empezar a analizar los cursos de los cuales es monitor
					String cur=fa.readLine(); 
					
					while (!cur.equalsIgnoreCase("#")){
						cur=UtilidadCadenas.limpiarCadena(cur);
						//Se parte en tokens para obtener el nombre de la asignatura y n�mero de horas. El separador es ","
						String[] asignaturas=UtilidadCadenas.tokenizador(cur,",");
						//Se adiciona la asignatura al Monitor. asignaturas[0] es el nombre y asignaturas[1] es el n�mero de horas
						//El Integer.parseInt() sirve para convertir Cadenas de caracteres a int
						m.adicionarAsignatura(asignaturas[0], Integer.parseInt(asignaturas[1]));
						// Se lee la siguiente l�nea para seguir leyendo asignaturas o llegar al "#" y cambiar de empleado
						cur=fa.readLine(); 
					}
				}
				//si es un empleado
				if (tokens[2].equalsIgnoreCase("Empleado")){
					// la siguiente linea contiene la cantidad de salarios minimos del empleado
					String nVeces=fa.readLine();
					// Se adiciona el empleado a la nomina
					Empleado e=nom.adicionarEmpleado(tokens[0], Integer.parseInt(tokens[1]),Integer.parseInt(nVeces), tokens[2]);
					// Se lee la siguiente l�nea para llegar al "#" y cambiar de empleado
					cad=fa.readLine();
				}
				
				//Se lee la siguiente l�nea del archivo para quedar ubicados en el siguiente empleado o en "FIN"
				cad=fa.readLine();           
			}// Fin del while
			}
			catch (Exception e){
				System.out.println("Ocurrio un problema al cargar el archivo de texto " + e);
			}
			return nom;
		}

}
