package datastr;

import java.util.ArrayList;

public class MyArrayList {

	private int[] list;
	private final int LIST_DEFAULT_SIZE = 10;
	private int size = LIST_DEFAULT_SIZE;
	private int counter = 0;
	
	//constructors
	public MyArrayList() {
		list = new int[size];
	}
	
	public MyArrayList(int inputSize) {
		if(inputSize > 0) {
			size = inputSize;
		}
		list = new int[size];
	}
	
	//isEmpty
	public boolean isEmpty() {
		//1. long if-else
		/*if(counter==0)
			return true;
		else
			return false;
		*/
		//2. short if-else
		//return (counter==0)? true : false;
		//3.
		return (counter==0);
	}
	
	//isFull
	public boolean isFull() {
		return (counter == size);
	}
	
	//howManyElements
	public int howManyElements() {
		return counter;
	}
	
	//1. funkcijas deklarācija
	private void resize()
	{
	//3. apreķināt newSize
		int newSize = (counter <= 100)? size * 2 : (int)(size * 1.5);
	//4. izveidot listNew ar newSize izmeru
		int[] listNew = new int[newSize];
	//5. veikt kopēsanu no veca masīva uz jauno
		for(int i = 0; i < size; i++) {
			listNew[i] = list[i];
		}
		
	//6. nomainam list refernci uz listNew
		list = listNew;
	//7. izsaukt Garbage Collector
		System.gc();
	//8. size noaminām uz newSize
		size = newSize;
	}
	
	

	//1. funkcijas deklarācija
	public void add(int element)
	{
		//2. pārbaude isFull - tad resize izsaukums
		if(isFull()) resize();
		//3. ieliekam jauno elemntu kā pēdējo sarakstā
		list[counter++] = element;
		//4 palielinām counter par 1
		//counter++;
	}
	

	//1. funkcijas deklarācija
	public void add(int index, int element) throws Exception 
	{
	//2. pārbaudes
	//2.1. par indeksu, ja nav pareizs, tad izmest izņēmumu
		if(index < 0 || index > counter) 
			throw new Exception("Incorrect index");
		
	//2.2 pārbaude isFull - tad resize izsaukums
		if(isFull()) resize();

		if(index == counter) add(element);
		else
		{	//3. veikt pārkopēšanu, lai elementi sākot no noradīta indeksa
			//tiek pavirzīti pa labi
			for(int i = counter; i > index; i--) {
				list[i] = list[i-1];
			}
			//4. ievietojam indeksa šūnā pasu elementu
			list[index] = element;
			//5. palielinām counter par 1
			counter++;
		}
	}
	
	
	//1. funkcijas deklarācija
	public void remove(int index) throws Exception
	{
		//2. pārbaudes
		//2.1 index
		//2.2. isEmpty
		if(index < 0 || index >= counter )
			throw new Exception("Incorrect index");
		
		if(isEmpty()) throw new Exception("Array is empty and it "
				+ "is not possible to remove element");

		//3. veikt kopesanu uz kreiso pusi līdz indexa elementam
		for(int i = index; i < counter -1; i++)
		{
			list[i] = list[i+1];
		}
		//4. counter jāsamazina par 1
		counter--;

	}
	
	//TODO int funkcijas tips jamaina uz citu tipu pēc nepieciešamībs
	public int get(int index) throws Exception{
		if(index < 0 || index >= counter )
			throw new Exception("Incorrect index");
		
		if(isEmpty()) throw new Exception("Array is empty and it "
				+ "is not possible to return element");
				
		return list[index];
	}
	
	public ArrayList search(int element) throws Exception{
		if(isEmpty()) throw new Exception("Array is empty and it "
				+ "is not possible to seacrh element");
		
		ArrayList indexes = new ArrayList();
		
		for(int i = 0; i < counter; i++)
		{
			if(list[i] == element)
			{
				indexes.add(i);
			}
		}
		
		if(indexes.size() == 0) 
			throw new Exception("Element is not found");
		
		return indexes;

	}
	
	public int[] getNeighbours(int element) throws Exception{
		ArrayList indexes = search(element);
		
		int neighboursSize = indexes.size();
		//(Integer)indexes.get(indexes.size()-1) <-- pēdejais indexes sunas vērtība
		if((Integer)indexes.get(indexes.size()-1) == (counter-1))
			neighboursSize--;
		
		int[] neighbours = new int[neighboursSize];
		for(int i = 0; i < neighboursSize; i++) {
			int indexFromSearchTemp = (int)indexes.get(i);
			int indexNeighbourTemp = indexFromSearchTemp+1;
			neighbours[i]  = list[indexNeighbourTemp];
			//īsāka forma
			//neighbours[i] = list[(int)indexes.get(i)+1]
		}
		return neighbours;		
		
	}
	
	
	
	public void print() throws Exception {
		
		if(isEmpty()) throw new Exception("Array is empty and it "
				+ "is not possible to print elements");
		
		for(int i = 0; i < counter;i++) {
			System.out.print(list[i] + " ");
		}
		System.out.println();
	}
	
	
	public void makeEmpty() {
		counter = 0;
		size = LIST_DEFAULT_SIZE;
		list = new int[size];
		System.gc();	
	}
	
	//TODO add asc or desc
	public void sort() throws Exception {
		if(isEmpty()) throw new Exception("Array is empty and it "
				+ "is not possible to sort");
		
		for(int i = 0; i < counter; i++) {
			for(int j = 0; j < counter; j++) {
				if(list[i]> list[j]) {
					swap(i, j);
				}
			}
		}
		
	}
	
	private void swap(int index1, int index2) {
		int temp = list[index1];
		list[index1] = list[index2];
		list[index2] = temp;
		
	}
	
	
	
	
}
