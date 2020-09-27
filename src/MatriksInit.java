import java.io.File;
import java.util.Scanner;

public class MatriksInit {
	Scanner scan;
	
	//Attributes
	int NBrsEff = 0;
	int NKolEff = 0;
	double[][] matrix;
	String fileName;
	
	//Constructor
	MatriksInit(int pilihan){
		
		scan = new Scanner(System.in);
		System.out.println ("Pilih salah satu cara input di bawah ini: ");
        System.out.println ("1. Baca dari keyboard");
        System.out.println ("2. Baca dari file yang sudah ada");
        boolean inputSuccess = false;
        
        while (!inputSuccess) {
        	int caraInput = scan.nextInt();
        	if (caraInput == 1) {
        		if (pilihan==1) {
        			matrixInitKeyboard();
        			System.out.println ("Masukkan elemen-elemen matriks A, dengan urutan: ");
        			System.out.println ("a11, a12, ..., a1m, a21, a22, ..., anm");
                	enterMatrix(scan, this.NBrsEff, this.NKolEff-1);
                	System.out.println ("Masukkan elemen-elemen vektor b, dengan urutan: ");
                	System.out.println ("b1, b2, ..., bn");
                	enterSolution(scan);
                	inputSuccess = true;
        		}
        		else if (pilihan==2 || pilihan==3) {
        			matrixInitKeyboard2();
        			System.out.println ("Masukkan elemen-elemen matriks A, dengan urutan: ");
        			System.out.println ("a11, a12, ..., a1m, a21, a22, ..., anm");
                	enterMatrix(scan, this.NBrsEff, this.NKolEff);
                	inputSuccess = true;
        		}
        		
        		else if (pilihan==4) {
        			matrixInitKeyboard3();
        			System.out.println ("Masukkan pasangan titik x dan y sebanyak n, dengan format: ");
        			System.out.println ("x1 y1");
        			System.out.println ("x2 y2");
        			System.out.println ("...");
        			System.out.println ("xn yn");
                	enterMatrix(scan, this.NBrsEff, this.NKolEff);
                	inputSuccess = true;
        		}
        		
            	
            } else if (caraInput == 2){
            	System.out.println("Masukkan nama file: ");
            	Scanner fileNameScanner = new Scanner(System.in);
            	fileName = fileNameScanner.next();
            	fileNameScanner.close();
            	openFile(fileName);
        		matrixInitFile();
        		enterMatrix(scan, this.NBrsEff, this.NKolEff);
        		closeFile();
        		inputSuccess = true;
            } else {
            	System.out.println("Pilihan tidak valid, silakan coba lagi");
            }
        }
        
		
		
	}
	
	//Open File (entry from file)
	public void openFile(String namaFile) {
		try {
			scan = new Scanner(new File(namaFile));
		} catch(Exception e){
			System.out.println("File not found");
		}
	}
	
	//Close File (entry from file)
	public void closeFile() {
		scan.close();
	}
	
	//Matrix initialization (entry from file)
	public void matrixInitFile() {
		while(scan.hasNextLine()) {
			this.NBrsEff += 1;
			Scanner bacaKolom = new Scanner(scan.nextLine());
			while(bacaKolom.hasNextDouble()) {
				this.NKolEff += 1;
				bacaKolom.nextDouble();
			}
		}
		
		this.NKolEff /= this.NBrsEff;
		this.matrix = new double[NBrsEff][NKolEff];
		
		closeFile();
		openFile(fileName);
	}
	
	
	//Matrix initialization (entry from keyboard - versi 1) - SPL
	public void matrixInitKeyboard() {
		scan = new Scanner(System.in);
		System.out.println("Masukkan jumlah baris matriks: ");
	    this.NBrsEff = scan.nextInt();
	    System.out.println("Masukkan jumlah kolom matriks: ");
	    this.NKolEff = scan.nextInt()+1;
	    
	    this.matrix = new double[NBrsEff][NKolEff];
	}
	
	//Matrix initialization (entry from keyboard - versi 2) - determinan, invers
	public void matrixInitKeyboard2() {
		scan = new Scanner(System.in);
		System.out.println("Masukkan n: ");
	    this.NBrsEff = scan.nextInt();
	    this.NKolEff = this.NBrsEff;
	    
	    this.matrix = new double[NBrsEff][NKolEff];
	}
	
	//Matrix initialization (entry from keyboard - versi 3) - interpolasi
	public void matrixInitKeyboard3() {
		scan = new Scanner(System.in);
		System.out.println("Masukkan n: ");
	    this.NBrsEff = scan.nextInt();
	    this.NKolEff = 2;
	    
	    this.matrix = new double[NBrsEff][NKolEff];
	}
	
	
	//Enter Matrix Data Value
	public void enterMatrix (Scanner scan, int n, int m) {			
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				this.matrix[i][j] = scan.nextDouble();
			}
		}
	}
	
	public void enterSolution (Scanner scan) {
		for (int i = 0; i < this.NBrsEff; i++) {
			this.matrix[i][this.NKolEff-1] = scan.nextDouble();
		}
	}
	
	//MatriksInit to Matriks
	void toMatriks(Matriks M) {
		for (int i = 0; i < this.NBrsEff; i++) {
			for (int j = 0; j < this.NKolEff; j++) {
				M.Elmt[i][j] = this.matrix[i][j];
			}
		}
	}
	
}
