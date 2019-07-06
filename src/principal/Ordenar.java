package principal;

import java.util.Scanner;

public class Ordenar {
	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);

		System.out.println("Tamaño del vector a ordenar:");
		int size = entrada.nextInt();
		System.out.println("Valor maximo de entero a almacenar:");
		int valor = entrada.nextInt();
		int[] vector = new int[size];
		rellenarArrary(vector,valor);

		// Descomentar para comprobar el vector antes de ser ordenado.
		System.out.println("Antes de ordenar");
		mostrar(vector);

		System.out.println("Metodo de ordenacion:");
		System.out.println("1- burbuja");
		System.out.println("2- burbujaMejoradaV1");
		System.out.println("3- burbujaMejoradaV2");
		System.out.println("4- insercion");
		System.out.println("5- seleccion");
		System.out.println("6- shellSort");
		System.out.println("7- mergeSort");
		System.out.println("8- Quicksort");
		System.out.println("Elija opcion [1-8]:");
		int opcion = entrada.nextInt();

		// Instante antes de ordenar
		long before = System.currentTimeMillis();

		switch (opcion) {
		case 1:
			System.out.println("Seleccionado el metodo burbuja.");
			burbuja(vector);
			break;
		case 2:
			System.out.println("Seleccionado el metodo burbujaMejoradaV1.");
			burbujaMejoradaV1(vector);
			break;
		case 3:
			System.out.println("Seleccionado el metodo burbujaMejoradaV2.");
			burbujaMejoradaV2(vector);
			break;
		case 4:
			System.out.println("Seleccionado el metodo insercion.");
			insercion(vector);
			break;
		case 5:
			System.out.println("Seleccionado el metodo seleccion.");
			seleccion(vector);
			break;
		case 6:
			System.out.println("Seleccionado el metodo shellSort.");
			shellSort(vector);
			break;
		case 7:
			System.out.println("Seleccionado el metodo mergeSort.");
			mergeSort(vector, 0, vector.length);
			break;
		case 8:
			System.out.println("Seleccionado el metodo Quicksort.");
			Quicksort(vector, 0, vector.length - 1);
			break;
		default:
			System.out.println("Opcion no valida");
			break;

		}

		// Instante despues de ordenar
		long after = System.currentTimeMillis();

		long duration = after - before;

		int segundos = (int) Math.abs(duration / 1000);
		int minutos = (int) Math.abs(duration / (60 * 1000));

		// Descomentar para comprobar que el vector ha sido ordenado.
		System.out.println("Despues de ordenar");
		mostrar(vector);

		System.out.println("Tiempo empleado en la ordenacion para un vector de " + size + " elementos.");
		System.out.println("Milisegundos:\t " + duration + "ms.");
		System.out.println("Segundos:\t " + segundos + "s.");
		System.out.println("Minutos:\t " + minutos + "m. " + (segundos - minutos * 60) + "s.");

		entrada.close();
	}

	/*
	 * burbuja El metodo de la burbuja es uno de los mas simples, es tan facil
	 * como comparar todos los elementos de una lista contra todos, si se cumple
	 * que uno es mayor o menor a otro, entonces los intercambia de posición. La
	 * burbuja mas simple de todas es la que compara todos con todos, generando
	 * comparaciones extras, por ejemplo, no tiene sentido que se compare con
	 * sigo mismo o que se compare con los valores anteriores a el, ya que
	 * supuestamente, ya estan ordenados.
	 */
	public static void burbuja(int[] arreglo) {
		int i, j;

		// Recorremos todo el vector de izq a derecha
		// comparando cada elemento con su vecino de la derecha
		// se intercambian si son igualles.
		for (i = 1; i < arreglo.length; i++) {
			// System.out.println("Pasada " + i);
			for (j = 0; j < arreglo.length - 1; j++) {
				if (arreglo[j] > arreglo[j + 1]) {
					intercambiar(arreglo, j, j + 1);
					// mostrar(arreglo);
				}
			}
		}
	}

	/*
	 * burbujaMejorada Una nueva version del metodo de la burbuja seria
	 * limitando el numero de comparaciones, dijimos que era inutil que se
	 * compare consigo misma. Si tenemos una lista de 10.000 elementos, entonces
	 * son 10.000 comparaciones que estan sobrando. Imaginemos si tenemos
	 * 1.000.000 de elementos. El metodo seria mucho mas optimo con “n”
	 * comparaciones menos (n = total de elementos). Si a ese cambio le sumamos
	 * otro cambio, el hecho que los elementos que estan detrás del que se esta
	 * comparando, ya estan ordenados, las comparaciones serian aun menos y el
	 * metodo seria aun mas efectivo. Si tenemos una lista de 10 elementos y
	 * estamos analizando el quinto elemento, que sentido tiene que el quinto se
	 * compare con el primero, el segundo o el tercero, si supuestamente, ya
	 * estan ordenados? Entonces optimizamos mas aun el algoritmo, quedando
	 * nuestra version final del algoritmo optimizado
	 */
	public static void burbujaMejoradaV1(int[] arreglo) {

		int i, j;

		// Recorremos todo el vector de izq a derecha
		for (i = 0; i < arreglo.length; i++) {
			System.out.println("Iteracion " + (i + 1));
			// Recorremos todos los valores a la derecha de i buscando el
			// menor valor e intercambiandolo si es el caso
			for (j = i + 1; j < arreglo.length; j++) {
				if (arreglo[i] > arreglo[j]) {
					intercambiar(arreglo, i, j);
					mostrar(arreglo);
				}
			}
		}
	}

	/*
	 * burbujaMejorada Existe una otra forma muy obvia para mejorar el algoritmo
	 * de la burbuja. Basta con tener en cuenta la posibilidad de que el
	 * conjunto esté ordenado en algún paso intermedio. Si el bucle interno no
	 * necesita realizar ningún intercambio en alguna pasada, el conjunto estará
	 * ya ordenado.
	 */
	public static void burbujaMejoradaV2(int[] arreglo) {
		int i, j = 0;
		boolean intercambio = true;
		// Hacemos recorridos del vector mientras
		// se vayan produciendo intercambios y no
		// hagamos todos los recorridos posibles n*n

		while (j < arreglo.length && intercambio == true) {

			// inicializamos la variable intercambio a false
			intercambio = false;
			System.out.println("Iteracion " + (j + 1));

			for (i = 0; i < arreglo.length - 1 - j; i++) {

				// Si el elemento de la izquierda es mayor que el de la derecha
				// se intercambia y se actualiza la variable intercambio a true
				if (arreglo[i] > arreglo[i + 1]) {
					intercambiar(arreglo, i, i + 1);
					mostrar(arreglo);
					intercambio = true;
				}
			}

			j++;
		}
	}

	/*
	 * seleccion Este método considera que el array está formado por 2 partes:
	 * una parte ordenada (la izquierda) que estará vacía al principio y al
	 * final comprende todo el array; y una parte desordenada (la derecha) que
	 * al principio comprende todo el array y al final estará vacía. El
	 * algoritmo toma elementos de la parte derecha y los coloca en la parte
	 * izquierda; empieza por el menor elemento de la parte desordenada y lo
	 * intercambia con el que ocupa su posición en la parte ordenada. Así, en la
	 * la primera iteración se busca el menor elemento y se intercambia con el
	 * que ocupa la posición 0; en la segunda, se busca el menor elemento entre
	 * la posición 1 y el final y se intercambia con el elemento en la posición
	 * 1. De esta manera las dos primeras posiciones del array están ordenadas y
	 * contienen los dos elementos menores dentro del array. Este proceso
	 * continúa hasta ordenar todos los elementos del array. En cada pasada se
	 * coloca un elemento en su lugar, y la variable j marca donde empezar la
	 * búsqueda en la parte desordenada, que será secuencial si no tenemos más
	 * información. La búsqueda del siguiente elemento menor comienza suponiendo
	 * que dicho elemento es j. Se comprueba la hipótesis comparándolo con cada
	 * uno de los restantes. Si se encuentra uno menor, se intercambia. El
	 * número de comparaciones que realiza este algoritmo es independiente de la
	 * ordenación inicial. El bucle interno hace length-1 comparaciones la
	 * primera vez, length-2 la segunda,..., y 1 la última. El bucle externo
	 * hace length-1 búsquedas. El total de comparaciones es
	 * (length^2-length)/2. Por tanto el orden de complejidad es cuadrático
	 * (O(length^2)).
	 */
	public static void seleccion(int[] arreglo) {
		int j, i, posMenor;
		for (j = 0; j < (arreglo.length - 1); j++) {
			posMenor = j;

			// Busco el elemento mas pequeño del vector y lo pongo
			// a la derecha de la parte del vector ya ordenado.
			for (i = j + 1; i < arreglo.length; i++) {
				if (arreglo[i] < arreglo[posMenor]) {
					posMenor = i;
				}
			}

			intercambiar(arreglo, j, posMenor);
			mostrar(arreglo);
		}
	}

	/*
	 * insercion Se utiliza un método similar al anterior, tomando un elemento
	 * de la parte no ordenada para colocarlo en su lugar en la parte ordenada.
	 * El primer elemento del array (arreglo[0]) se considerado ordenado (la
	 * lista inicial consta de un elemento). A continuación se inserta el
	 * segundo elemento (arreglo[1]) en la posición correcta (delante o detrás
	 * de arreglo[0]) dependiendo de que sea menor o mayor que arreglo[0].
	 * Repetimos esta operación sucesivamente de tal modo que se va colocando
	 * cada elemento en la posición correcta. El proceso se repetirá length-1
	 * veces. Para colocar el dato en su lugar, se debe encontrar la posición
	 * que le corresponde en la parte ordenada y hacerle un hueco de forma que
	 * se pueda insertar. Para encontrar la posición se puede hacer una búsqueda
	 * secuencial desde el principio del conjunto hasta encontrar un elemento
	 * mayor que el dado. Para hacer el hueco hay que desplazar los elementos
	 * pertinentes una posición a la derecha. El orden de complejidad de este
	 * algoritmo es cuadrático (O(length^2)).
	 */
	public static void insercion(int[] arreglo) {
		int buffer;
		int i, j, k;
		for (i = 1; i < arreglo.length; i++) {
			j = 0;
			buffer = arreglo[i];

			// Avanzamos por el arreglo hasta encontrar la posicion donde
			// colocar el valor de buffer
			while (arreglo[j] < buffer) {
				j++;
			}

			// Comprobamos si su posicion no es la ultima de la parte ordenada
			if (j < i) {
				// Movemos todos los valores a la derecha de la posicion un
				// puesto a la derecha.
				for (k = i; k > j; k--) {
					intercambiar(arreglo, k, k - 1);
				}
			}
		}
	}

	/*
	 * shellSort Este metodo es una mejora del algoritmo de ordenamiento por
	 * Insercion (Insertsort). Si tenemos en cuenta que el ordenamiento por
	 * insercion es mucho mas eficiente si nuestra lista de numeros esta
	 * semi-ordenada y que desplaza un valor una unica posicion a la vez.
	 * Durante la ejecucion de este algoritmo, los numeros de la lista se van
	 * casi-ordenando y finalmente, el ultimo paso o funcion de este algoritmo
	 * es un simple metodo por insercion que, al estar casi-ordenados los
	 * numeros, es más eficiente.
	 * 
	 * https://www.youtube.com/watch?v=AeCeFdSoPEM
	 */
	public static void shellSort(int[] arreglo) {
		for (int incremento = arreglo.length / 2; incremento > 0; incremento = (incremento == 2 ? 1
				: (int) Math.round(incremento / 2.2))) {
			for (int i = incremento; i < arreglo.length; i++) {
				for (int j = i; j >= incremento && arreglo[j - incremento] > arreglo[j]; j -= incremento) {
					mostrar(arreglo);
					intercambiar(arreglo, j, j - incremento);
				}
			}
		}
	}

	/*
	 * mergeSort Este algoritmo consiste basicamente en dividir en partes
	 * iguales la lista de numeros y luego mezclarlos comparandolos, dejandolos
	 * ordenados. Si se piensa en este algoritmo recursivamente, podemos
	 * imaginar que dividirá la lista hasta tener un elemento en cada lista,
	 * luego lo compara con el que está a su lado y según corresponda, lo situa
	 * donde corresponde.
	 */
	public static void mergeSort(int[] arreglo, int inicio, int longitud) {

		int longitud1; // Longitud de la primera mitad del arreglo
		int longitud2; // Longitud de la segunda mitad del arreglo
		if (longitud > 1) {
			longitud1 = longitud / 2;
			longitud2 = longitud - longitud1;
			// Se realizan divisiones recursivas del arreglo y llama al funcion
			// merge para mezclarlos.
			mergeSort(arreglo, inicio, longitud1);
			mergeSort(arreglo, inicio + longitud1, longitud2);
			merge(arreglo, inicio, longitud1, longitud2);
		}
	}

	/*
	 * merge Este algoritmo nos permite mezclar los elementos divididos por
	 * mergeSort según corresponda.
	 */
	public static void merge(int[] arreglo, int inicio, int longitud1, int longitud2) {
		// Vector utilizado temporalmente para ordenar el vector
		int[] arregloBuffer = new int[longitud1 + longitud2];

		// Indice del vector temporal
		int indiceBuffer = 0;

		// Indice del vector que representa la primera mitad
		int indice1 = 0;

		// Indice del vector que representa la segunda mitad
		int indice2 = 0;

		// Indice utilizado para el volcado del buffer en el vector original
		int i;

		// Mientras los subindices no lleguen al final de sus respectivos
		// subvectores, se van colocando de menor a mayor los elementos en el
		// vector temporal.
		while ((indice1 < longitud1) && (indice2 < longitud2)) {
			if (arreglo[inicio + indice1] < arreglo[inicio + longitud1 + indice2])
				arregloBuffer[indiceBuffer++] = arreglo[inicio + (indice1++)];
			else
				arregloBuffer[indiceBuffer++] = arreglo[inicio + longitud1 + (indice2++)];
		}

		// Cuando alguno de los subindices llega al final se copia el contenido
		// del
		// otro subvector en el vector temporal
		while (indice1 < longitud1) {
			arregloBuffer[indiceBuffer++] = arreglo[inicio + (indice1++)];
		}
		while (indice2 < longitud2) {
			arregloBuffer[indiceBuffer++] = arreglo[inicio + longitud1 + (indice2++)];
		}

		// Una vez se tiene el vector temporal ordenado se vuelcan los datos en
		// el
		// vector original.
		for (i = 0; i < longitud1 + longitud2; i++) {
			arreglo[inicio + i] = arregloBuffer[i];
		}
	}

	/*
	 * Quicksort El método de ordenación rápida (Quicksort) para ordenar los
	 * elementos del array se basa en el hecho de que es más rápido y fácil
	 * ordenar dos listas pequeñas que una lista grande. Su nombre se debe a que
	 * este método, en general, puede ordenar una lista de datos mucho más
	 * rápido que cualquier otro método. El método se basa en la estrategia
	 * típica de "divide y vencerás". El array a ordenar se divide en dos
	 * partes: una contendrá todos los valores menores o iguales a un cierto
	 * valor (que se suele denominar pivote) y la otra con los valores mayores
	 * que dicho valor. El primer paso es dividir el array original en dos
	 * subarrays y un valor que sirve de separación, esto es, el pivote. Así, el
	 * array se dividirá en tres partes:
	 * 
	 * • La parte izquierda, que contendrá valores inferiores o iguales al
	 * pivote. • El pivote. • La parte derecha, que contiene valores superiores
	 * o iguales al pivote.
	 * 
	 * Inicialmente, las partes izquierda y derecha no estarán ordenadas,
	 * excepto en el caso de que estén compuestas por un único elemento. La
	 * corrección de este algoritmo se base en los siguientes hechos:
	 * 
	 * • El subconjunto de elementos menores que el pivote se ordenará
	 * correctamente, en virtud del proceso recursivo. • El mayor elemento en el
	 * subconjunto de elementos menores no es mayor que el pivote, debido a cómo
	 * se realiza la partición. • El menor elemento en el subconjunto de
	 * elementos mayores no es menor que el pivote, debido a cómo se realiza la
	 * partición. • El subconjunto de elementos mayores se ordena correctamente,
	 * en virtud del proceso recursivo.
	 * 
	 * El mejor caso para Quicksort se presenta cuando el pivote divide al
	 * conjunto en dos subconjuntos de igual tamaño. En este caso tendremos dos
	 * llamadas recursivas con un tamaño igual a la mitad del original y el
	 * tiempo de ejecución es O(length*log(length^2)).
	 * 
	 * Ya que los subconjuntos de igual tamaño son los mejores para Quicksort,
	 * es de esperar que los de muy distinto tamaño sean los peores y,
	 * efectivamente, así es. Suponiendo, por ejemplo, que el elemento que se
	 * toma como pivote es el menor del conjunto el subconjunto de la izquierda
	 * (elementos menores) estará vacío, y el de la derecha (elementos mayores)
	 * contendrá a todos los elementos menos al pivote. Poniéndonos en el peor
	 * de los casos, aquél en el que siempre obtenemos uno de los dos
	 * subconjuntos vacíos y el otro contiene n-1 elementos, la complejidad del
	 * algoritmo sería O(length^2). En el caso medio, si el algoritmo es
	 * implementado cuidadosamente y los subconjuntos de elementos generados en
	 * cada partición contienen aproximadamente el mismo número de elementos,
	 * puede demostrarse que el tiempo de ejecución es O(length*log(length^2)).
	 * Para conseguir esta implementación cuidadosa, es crucial determinar
	 * adecuadamente el elemento pivote.
	 */
	public static void Quicksort(int[] arreglo, int a, int b) {

		int izq = a, der = b;
		int pivote = arreglo[(izq + der) / 2];

		// Avanzamos izq hasta encontrar un elemento que deba estar a la derecha
		// del pivote,
		// igualmente el indice derecho hasta encontrar un elemento que deba
		// estar a la izquierda
		// del pivote. Si los indices no se han cruzado realizamos el
		// intercambio.
		do {
			while (arreglo[izq] < pivote) {
				izq++;
			}
			while (arreglo[der] > pivote) {
				der--;
			}
			if (izq <= der) {
				intercambiar(arreglo, izq, der);
				izq++;
				der--;
			}
		} while (izq <= der);

		// Si el indiador derecho se ha desplazado realizamos _Quicksort de la
		// parte derecha
		if (a < der) {
			Quicksort(arreglo, a, der);
		}

		// Si el indiador izquierdo se ha desplazado realizamos _Quicksort de la
		// parte derecha
		if (izq < b) {
			Quicksort(arreglo, izq, b);
		}
	}

	// Metodo para mostrar un vector
	public static void mostrar(int[] arreglo) {
		for (int i = 0; i < arreglo.length; i++) {
			System.out.print(arreglo[i] + " ");
		}
		System.out.println();
	}

	// Metodo para intercambiar dos elementos de un vector
	public static void intercambiar(int[] arreglo, int i, int j) {
		int buffer = arreglo[j];
		arreglo[j] = arreglo[i];
		arreglo[i] = buffer;
	}

	// Metodo para rellenar un vector con enteros aleatorios
	public static void rellenarArrary(int[] anArray,int valor) {
		for (int i = 0; i < anArray.length; i++) {
			anArray[i] = (int) (Math.random() * valor);
		}
	}
}
