package sorting.divideAndConquer.quicksort3;

import util.Util;

import sorting.AbstractSorting;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array.length > 1 && rightIndex - leftIndex >= 1) {
					
					sortPivot(array, leftIndex, rightIndex);
					int posPivot = part(array, leftIndex + 1, rightIndex - 1);
					
					if (rightIndex - leftIndex > 2) {
						sort(array, leftIndex, posPivot - 1);
						sort(array, posPivot + 1, rightIndex);
					} else {
						sortThree(array, leftIndex, rightIndex);
					}
		}
	}
	
private void sortPivot(T[] array, int inicio, int fim) {
		
		int mid = (inicio + fim) / 2;
		if (array[inicio].compareTo(array[mid]) > 0) {
			Util.swap(array, inicio, mid);
		}
		
		if (array[inicio].compareTo(array[fim]) > 0) {
			Util.swap(array, inicio, fim);
		}
		
		if (array[mid].compareTo(array[fim]) > 0) {
			Util.swap(array, mid, fim);
		}
		
		Util.swap(array, mid, fim - 1);
	}

	private void sortThree(T[] array, int leftIndex, int rightIndex) {
		
		int size = rightIndex - leftIndex;
		if (size > 0 && array[leftIndex].compareTo(array[rightIndex]) > 0) {
			Util.swap(array, leftIndex, rightIndex);
		}
		
		if (size > 1) {
			int mid = (leftIndex + rightIndex) / 2;
			if (array[leftIndex].compareTo(array[mid]) > 0) {
				Util.swap(array, leftIndex, mid);
			}
			
			if (array[mid].compareTo(array[rightIndex]) > 0) {
				Util.swap(array, mid, rightIndex);
			}
		}
		
	}

	private int part(T[] array, int inicio, int fim) {
		int j = inicio - 1;
		int i = inicio;
		T pivot = array[fim];

		while (i < fim && j < fim) {
			if (array[i].compareTo(pivot) < 0 && i > j) {
				j++;
				Util.swap(array, j, i);
			} else {
				i++;
			}
		}
		j++;
		while (fim > j) {
			Util.swap(array, fim, fim - 1);
			fim--;
		}
		return j;
	}
}
