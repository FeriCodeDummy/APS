package si.feri.aps.vaje;

import java.util.Arrays;

public class Uredi {

	public int[] array;
	private final String algoritem;
	int counter = 0;

	private class AlgorithmDoesntExistException extends Exception {
		public AlgorithmDoesntExistException(){
			super("Algorithm " + algoritem + "isn't supported");
		}
	}

	public Uredi(int[] array, String algoritem){
		this.array = array;
		this.algoritem = algoritem;
	}

	public int[] DualPivotQuickSort(){
		Arrays.sort(this.array);
		return this.array;
	}

	private void QuickSortRec(int [] arr, int dno, int vrh){


		if(dno<vrh){
			int j = QuickSortDivide(arr, dno, vrh);
			QuickSortRec(arr, dno, j-1);
			QuickSortRec(arr, j+1, vrh);
		}
	}

	public int[] uredi(){
	//public int[] uredi() throws AlgorithmDoesntExistException {
		switch(this.algoritem){

			case "QuickSort":
				QuickSortRec(this.array, 0, this.array.length-1);
				return this.array;

			case "DualPivotQuickSort":
				return DualPivotQuickSort();

			case "QuickSortMedian":
				//QuickSortMedianRec(this.array, 0, this.array.length-1);
				QuickSortMedianRec(this.array, 0, this.array.length-1);
				return this.array;

			default:
				//throw new AlgorithmDoesntExistException();
				System.out.println("Algoritem ne obstaja");
				return new int[]{0};
		}
	}



	private int QuickSortDivide(int[] arr, int dno, int vrh){
		int w = arr[vrh];

		int i = dno-1;
		for (int j = dno; j<vrh; j++){
			if(arr[j] <= w){
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		int temp = arr[i+1];
		arr[i+1] = arr[vrh];
		arr[vrh] = temp;
		return i+1;
	}


	private void QuickSortMedianRec(int[]arr, int dno,int vrh){
		if(dno<vrh){
			int j = QuickSortDivideMedian(arr, dno, vrh);
			QuickSortMedianRec(arr, dno, j-1);
			QuickSortMedianRec(arr, j+1, vrh);
		}
	}

	private int QuickSortDivideMedian(int[] arr, int dno,int vrh){
		int m = dno + ((vrh-dno)/2);
		int temp = arr[dno];
		arr[dno] = arr[m];
		arr[m] = temp;

		int pe = arr[dno];
		int l = dno;
		int d = vrh;
		while (l<d){
			while (arr[l] <= pe && l < vrh){
				l++;
			}
			while(arr[d] >= pe && d>dno){
				d--;
			}

			if (l<d){
				temp = arr[l];
				arr[l] = arr[d];
				arr[d] = temp;
			}
		}
		temp = arr[dno];
		arr[dno] = arr[d];
		arr[d] = temp;
		return d;
	}

	public int getMedian(int left,int right){
		int center = (left+right)/2;

		if(this.array[left] > this.array[center])
			swap(left,center);

		if(this.array[left] > this.array[right])
			swap(left, right);

		if(this.array[center] > this.array[right])
			swap(center, right);

		swap(center, right);
		return this.array[right];
	}

	public void swap(int left,int right){
		int temp = this.array[left];
		this.array[left] = this.array[right];
		this.array[right] = temp;
	}

}


