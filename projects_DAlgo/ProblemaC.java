

import java.util.Scanner;
/**
 * 
 * @author Juan Pablo Gonzalez P. - 201424703
 * @author Ivan Dario Chavarro G. - 201423319
 *
 */
public class ProblemaC {

	static int division = 0;
	static int[] espacios =new int[10000];
	static int numEspacios=0;
	static int[][] espA =new int[1000][1000];
	static int[][] espB = new int[1000][1000];
	static int divisionFinal = 0;
	static int rioTemp = 0;
	static int rioFinal = 0;
	static int posx = 0;
	static int lineas = 0;
	static boolean imprimir = false;

	public static void main(String[] args) {

		System.out.println ("Obteniendo el rio mas largo por parrafo");
        System.out.println ("Por favor introduzca el texto:");
        String cadena = "";
        Scanner scanner = new Scanner (System.in);   //Creaci�n de un objeto Scanner
        
        
        while (scanner.hasNextLine()) 
		{
			try{
				
		        cadena = scanner.nextLine (); 	//Invocamos un m�todo sobre un objeto Scanner
		        procesaCadena(cadena.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", ""));
		        division = 0;
	    		numEspacios = 0;
	    		divisionFinal = 0;
	    		rioTemp = 0;
	    		rioFinal = 0;
	    		posx = 0;
	    		lineas = 0;
			}
			catch (Exception e)
			{
		        scanner.next();
			}			
		}
		scanner.close();
        
   
	}
	
	
	public static void procesaCadena(String cadena) {

        // Busca la palabra mas larga y los espacios
        palabraEspacios (cadena);

        for (int i = division; i < cadena.length(); i++) {
        	// Dividimos la cadena en lineas
        	divisionCadena(i);

        	// Si la division es menor que rio obtenido anteriormente salimos
            if (lineas < rioFinal)
            	break;
        	
        	rioTemp=0;
            for (int y = 0; y <= lineas; y++) { 
                for (int x = 0; x < 1000; x++) { 
                	if (espB[y][x] == 0)
                		break;
                	buscaRio(y, x, espB[y][x], 1);
                }
                if (lineas - y < rioFinal)
                	break;
            }
            if (imprimir) {
                System.out.println ("Division = " + i + " rioTemp = " + rioTemp);
            }
            if (rioFinal < rioTemp) {
            	divisionFinal = i;
            	rioFinal = rioTemp;
            }   
        }
        if(cadena.split(" ").length==1)rioFinal = 0;
        System.out.println (divisionFinal + " " + rioFinal);

	}
	
    //	Buscamos la palabra mas larga y obtenemos los espacios en un array "espacios"
	//	Al final del array ponemos un 0
	//	La variable numEspacios tiene la cantidad de espacios
	public static void palabraEspacios (String cadena) {
		int aux;
		numEspacios = 0;
        for (int n = 0; n < cadena.length(); n++) { 
        	char c = cadena.charAt(n);
        	if (c == ' ') {
        		espacios[numEspacios] = n + 1;
        		if (numEspacios > 0) {
        			aux = espacios[numEspacios] - espacios[numEspacios - 1];
        			if (division < aux)
        				division = aux;
        		}
        		numEspacios++;
        	}
    		espacios[numEspacios] = 0;
        }
        if (imprimir) {
            System.out.println ("Palabra mas larga: " + division);
            System.out.println ("Espacios:");
            for (int n = 0; n < numEspacios; n++) { 
                System.out.println (espacios[n] + " - ");
            }
        }
	}
	
    //	Divide la "cadena" en lineas, 
	//	Obtenemos un array con los espacios por linea "Array espA"
	//	Obtenemos un array con la posicion del espacio en la linea "Array espB" 
	public static void divisionCadena(int division) {
        posx = 0; 
        lineas = 0;
        int aux = 0;
        int espacioFilaAnterior = 0;
        String cadenaAux;
        for (int n = 0; n < numEspacios; n++) { 
        	if ((espacios[n] - espacioFilaAnterior) <= (division + 1)) {
        		aux = espacios[n];
        		espA[lineas][posx] = espacios[n];
        		posx++;
        	}
        	else {
        		espA[lineas][posx] = 0;
        		espacioFilaAnterior = aux;
        		lineas++;
        		posx = 0;
        		aux = espacios[n];
        		espA[lineas][posx] = espacios[n];
        		posx++;
        	}
        }
        
        if (imprimir) {
            System.out.println ("Arreglo Espacios A:");
            for (int y = 0; y <= lineas; y++) { 
            	cadenaAux = "";
                for (int x = 0; x < 1000; x++) { 
                	if (espA[y][x] == 0)
                		break;
                	cadenaAux = cadenaAux + espA[y][x] + " - ";
                }
                System.out.println (cadenaAux);
            }
        }

        espacioFilaAnterior = 0;
        for (int y = 0; y <= lineas; y++) { 
            for (int x = 0; x < 1000; x++) { 
            	if(espA[y][x+1] == 0) {
                    espacioFilaAnterior = espA[y][x];
                    espB[y][x] = 0;
            		break;
            	}
            	espB[y][x] = espA[y][x] - espacioFilaAnterior;
            }
        }
        if (imprimir) {
            System.out.println ("Arreglo Espacios B:");
            for (int y = 0; y <= lineas; y++) { 
            	cadenaAux = "";
                for (int x = 0; x < 1000; x++) { 
                	if (espB[y][x] == 0)
                		break;
                	cadenaAux = cadenaAux + espB[y][x] + " - ";
                }
                System.out.println (cadenaAux);
            }
        }
	}
	
	//	Busca Rio recursivamente
	public static void buscaRio (int y, int x, int numero, int rio) {
		y++;
		if (y > lineas) {
			if(rioTemp < rio)
				rioTemp = rio;
			return;
		}
		for (int j = 0; j < 50; j++) {
			if (espB[y][j] == 0) {
				if(rioTemp < rio)
					rioTemp = rio;
				return;
			}
			if (espB[y][j] == numero -1 || espB[y][j] == numero || espB[y][j] == numero  + 1) {
				buscaRio (y, x, espB[y][j], rio + 1);
			}
		}
	}

}
