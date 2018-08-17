
import java.io.IOException;

import java.util.Scanner;

/**
 * 
 * @author Juan Pablo Gonzalez P. - 201424703
 * @author Ivan Dario Chavarro G. - 201423319
 *
 */
public class ProblemaA
{

	public static void main(String[] args) throws IOException { 
		
		Scanner scanner = new Scanner(System.in);//inicializa objeto scanner para lectura

		while (scanner.hasNextLine()) 
		{
			try{
				String[] arrS = scanner.nextLine().trim().split(" ");
				int n = 0;
				int c = 0;
				int a[] = null;
				

				if(arrS.length>=2)//verificamos que esten los parametros necesarios 
				{
					n = Integer.parseInt(arrS[0]);//tamano arreglo			
					c = Integer.parseInt(arrS[1]);//numero de ceros
					a = new int[n];

					if(arrS.length==n+2) {
						for(int i=0;i<n;i++) {
							a[i] = Integer.parseInt(arrS[i+2]);
						}
						System.out.println(subarreglo(a,c));//llamado a metodo que da como resultado el tamaño de subarrelo mas largo
					}
					else 
					{
						System.out.println("error");					
					}
					
				}
				else 
				{
					System.out.println("error");					
				}
				
			}
			catch (Exception e)
			{
		        scanner.next();
			}			
		}
		scanner.close();
	}
	
	public static int subarreglo(int ar[], int c)
	{
		int maxlenght = 0;
		int ceros = c;
		int i = 0;
		int pos = 1;
		
		if(c>ar.length)return 0;
		
		while(i<ar.length) {
			if(ar[i] == 0) ceros--;
			if(ceros==-1) {
				maxlenght=i; 
				break; 
			}
			if(i==ar.length-1 && ceros==0) return ar.length;
			if(i==ar.length-1 && ceros!=0) return 0;
			i++;
		}
		while(pos<ar.length) {
			int a = porArriba(ar,c,pos);
			int b = porAbajo(ar,c,pos);
			if(a>b) {
				if(a>maxlenght) {
					maxlenght= a;
					if(pos>2)
						return a;
				}
			}
			else {
				if(b>maxlenght) {
					maxlenght= b;
					if(pos>2)
						return b;
				}
			}
			if(maxlenght>ar.length/2)break;

			pos++;
		}
		
		return maxlenght;
	}
	//acota por la parte izquierda el arreglo y retorna el tamaño encontrado de un subarreglo
	public static int porArriba(int ar[], int c, int i)
	{
		
		int res = 0;
		int ceros = c;

		for(int h=i;h<ar.length;h++) {

			if(ar[h] == 0) ceros--;
			if(ceros==-1) {
				res=h; 
				break; 
			}
			if(h==ar.length-1 && ceros==0) return h+1-i;
			if(h==ar.length-1 && ceros!=0) return 0;
		}

		return res-i;

	}
	//acota por la parte derecha el arreglo y retorna un tamaño
	public static int porAbajo(int ar[], int c, int i)
	{
		int res = 0;
		int ceros = c;

		for(int h=ar.length-1;h>i;h--) {

			if(ar[h] == 0) ceros--;

			if(ceros==-1) {

				res=h; 
				break; 
			}

			if(h==i+1 && ceros==0) {res = h;break;}
			if(h==i+1 && ceros!=0) return 0;
		}
		if(ar.length-1==i) {
			if(ar[i] == 0) return 1;
			else return 0;
		}

		return ar.length-1-res;
	}
	

}
