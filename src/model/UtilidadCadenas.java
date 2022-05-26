package model;

public class UtilidadCadenas {

		
	/**
         * @param cadena
	 * @param tok: expresi�n por la cual se puede partir una cadena
	 * @return un conjunto de subcadenas "partidas por el token dado"
	 */
	public static String[] tokenizador(String cadena, String tok){
		String [] tokens=cadena.split(tok);
		return tokens;
	}
	
	/**
	 * @param cad: cadena a la que se le va a eliminar espacios
	 * @return la cadena con solo un espacio entre las palabras
	 */
	public static String eliminarEspaciosAdicionales(String cad){
		
		return cad.replaceAll("\\s+"," ");
	}
	
	/**
	 * @param cad: cadena a la que se le va a eliminar los espacios iniciales y adicionales. Adem�s, s�lo deja un espacio entre las palabras
	 * @return
	 */
	public static String limpiarCadena(String cad){
		cad=UtilidadCadenas.eliminarEspaciosAdicionales(cad);
		cad=cad.trim();
		return cad;
	}
	
	/**
	 * @param cad1
	 * @param cad2
	 * @return iguales si las cadenas son iguales ante espacios, sin importar min�sculas y may�scula. Diferentes de lo contrario
	 */
	public static String comparar(String cad1, String cad2){
		// Se deja sin espacios adicionales, iniciales o finales cada una de las cadenas
		// Se deja s�lo con un espacio entre palabras
		cad1=UtilidadCadenas.eliminarEspaciosAdicionales(cad1);
		// Se eliminan los espacios iniciales y finales
		cad1=cad1.trim();
		cad2=UtilidadCadenas.eliminarEspaciosAdicionales(cad2);
		cad2=cad2.trim();
		
		if (cad1.equalsIgnoreCase(cad2)){
			return "iguales";
		}
		else
			return "diferentes";
		
	}
	
}
