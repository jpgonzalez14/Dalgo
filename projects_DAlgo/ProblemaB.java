
import java.io.IOException;

import java.util.Scanner;

/**
 * 
 * @author Juan Pablo Gonzalez P. - 201424703
 * @author Ivan Dario Chavarro G. - 201423319
 *
 */
public class ProblemaB
{

	public static void main(String[] args) throws IOException { 
		
		Scanner scanner = new Scanner(System.in);//creacio de un objeto scanner

		while (scanner.hasNextLine()) 
		{

			try{
				String[] arrS = scanner.nextLine().trim().split(" ");
				int k = 0;
				char r[] = null;
				String ans = "error entrada parametros";
				

				if(arrS.length==2)//se verifica que entres los parametros necesarios
				{
					k = Integer.parseInt(arrS[0]);//numero de iteraciones					
					r = arrS[1].toCharArray();//numero a procesar. este se convierte en tipo char para poder analizar cada caracter de numero
					ans = minPerMaxAnterior(r);//metodo que da el numero mayor al actual y menor a las demas iteraciones

					for(int i=1;i<k;i++) {//se realiza el metodo anterior k iteraciones dadas
						if(ans.equalsIgnoreCase("*")) break;
						ans = minPerMaxAnterior(ans.toCharArray());
					}

				}
				else 
				{
					System.out.println("error");					
				}
				
				
				System.out.println(ans);


				System.out.println();
				
			}
			catch (Exception e)
			{
		        scanner.next();
			}			
		}
		scanner.close();
	}

	public static String minPerMaxAnterior(char ere[])
	{
		String res = "";
		//pivote
		int p=-1, rep=-1, min=999999999;

		
		for(int i=ere.length-1; i>=0; i--) {

			if(i==0)break;
			
			if(ere[i-1] < ere[i] && i>0) { 
				p=i-1;
			
				for(int j=p+1;j<ere.length; j++) {
						
					int d=ere[j]-ere[p];
					
					if(d<min && d>0) {
						rep=j;
						min=d;
					}
				}
				char temp = ere[p];
				ere[p] = ere[rep];
				ere[rep] = temp;
				
				for(i=0; i<=p;i++) {
					res += ere[i];
				}
				for(i=ere.length-1; i>p;i--) {
					res += ere[i];
				}
				break;
			}
			
		}
		if(p==-1)return"*";
		return res;
	}
	

}
