import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Random;

//class Karyawan{
//	String kode;
//	String jabatan;
//	String nama;
//	int gaji;
//	int no;
//	String jeniskelamin;
//};


public class Main {
	public static int idx = 0;
	
	public static void main(String[] args) throws IOException{
		
		Scanner terminalInput = new Scanner(System.in);
		String pilihanUser;
		
		boolean islanjutkan = true;
		
		while (islanjutkan) {
// 		clearScreen(); 
			//uncommand kalau pakai console
		 System.out.println(" __/\\__	  ..... .......  ...... ..... ..... ......  .... ..");
		 System.out.println(" \\_\\/_/	  .   ..  ..     . .  . .     .   .   ..    .  . ..");
		 System.out.println(" /_/\\_\\	  . ..    ..     . .  . ..... .   .   ..    .  . ..");
		 System.out.println("   \\/     ...	  ..     . .  . .     .   .   ..    .  . ..");
		 System.out.println("          ..	  ..  x  . .  . ..... .   .   ..    .... ......");
		 System.out.println("----------------------------------");
		 System.out.println("[1].... Lihat Seluruh Karyawan");
		 System.out.println("[2].... Update Data Karyawan");
		 System.out.println("[3].... Tambah Data Karyawan");
		 System.out.println("[4].... Hapus Data Karyawan");
		 System.out.println("[5].... Cari Karyawan");
		 System.out.println("----------------------------------");
		 
		 System.out.print("\n>>Pilihan anda: ");
		 pilihanUser = terminalInput.next();
		 
		 switch (pilihanUser) {
		 case "1"://done
			 System.out.println("================================");
			 System.out.println("LIST SELURUH KARYAWAN PT.MENTOL");
			 System.out.println("================================");
			 tampilkanData();
			 break;
		 case "2"://done
			 System.out.println("================================");
			 System.out.println("UPDATE KARYAWAN");
			 System.out.println("================================");
			 updateData();
			 break;
		 case "3"://done
			 System.out.println("================================");
			 System.out.println("TAMBAH KARYAWAN BARU");
			 System.out.println("================================");
			 tambahData();
			 tampilkanData();
			 break;
		 case "4"://done
			 System.out.println("================================");
			 System.out.println("HAPUS KARYAWAN");
			 System.out.println("================================");
			 deleteData();
			 break;
		 case "5"://done
			 System.out.println("================================");
			 System.out.println("CARI KARYAWAN");
			 System.out.println("================================");
			 cariData();
			 break;
			 default:
				 System.err.println("\nInput anda tidak ditemukan\nSilahkan pilih [1-4]!"); 
		 }
		 islanjutkan = getYesorNo("Apakah anda ingin melanjutkan");
		}
			 
	}
	
	private static boolean getYesorNo(String message){
        Scanner terminalInput = new Scanner(System.in);
        System.out.print("\n"+message+" (y/n)? ");
        String pilihanUser = terminalInput.next();

        while(!pilihanUser.equalsIgnoreCase("y") && !pilihanUser.equalsIgnoreCase("n")) {
            System.err.println("Pilihan anda bukan y atau n");
            System.out.print("\n"+message+" (y/n)? ");
            pilihanUser = terminalInput.next();
        }

        return pilihanUser.equalsIgnoreCase("y");
    }
	
	private static void tampilkanData() throws IOException{
		 FileReader fileInput;
	        BufferedReader bufferInput;
	        try {
	            fileInput = new FileReader("database.txt");
	            bufferInput = new BufferedReader(fileInput);
	        } catch (Exception e){
	            System.err.println("Database Tidak ditemukan");
	            System.err.println("Silahkan tambah data terlebih dahulu!");
	            tambahData();
	            return;
	        }
	        System.out.println("\n| No |\tKode     |\tNama                   |   Jenis-Kelamin     |\tJabatan      |\t Gaji");
	        System.out.println("----------------------------------------------------------------------------------------------------------");

	        String data = bufferInput.readLine();
	        int nomorData = 0;
	        while(data != null) {
	        	
	            nomorData++;

	            StringTokenizer stringToken = new StringTokenizer(data, ",");

//	            stringToken.nextToken();
	            System.out.printf("| %2d ", nomorData);
	            System.out.printf("|\t%7s  ", stringToken.nextToken());
	            System.out.printf("|\t%-20s   ", stringToken.nextToken());
	            System.out.printf("|\t%-10s   ", stringToken.nextToken());
	            System.out.printf("|\t%-10s   ", stringToken.nextToken());
	            System.out.printf("|\t%s   ", stringToken.nextToken());
	            System.out.print("\n");

	            data = bufferInput.readLine();
	        }

	        System.out.println("----------------------------------------------------------------------------------------------------------");
	  }

	private static void updateData() throws IOException{
		File database = new File("database.txt");
		FileReader fileInput = new FileReader(database);
		BufferedReader bufferedInput = new BufferedReader(fileInput);
		
		File tempDB = new File("tempDB.txt");
		FileWriter fileOutput = new FileWriter(tempDB);
		BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);
		
		System.out.println(">>List Karyawan<<");
		tampilkanData();
		
		Scanner terminalInput = new Scanner(System.in);
		System.out.print("\nMasukan nomor karyawan yang ingin diupdate: ");
		int updateNum = terminalInput.nextInt();
		
		String data = bufferedInput.readLine();
		int entryCounts = 0;
		while (data != null) {
			entryCounts++;
			StringTokenizer st = new StringTokenizer(data, ",");
			if (updateNum == entryCounts) {
				System.out.println("\nData yang ingin diupdate adalah:");
				System.out.println("-----------------------------------");
				System.out.println("Kode             : "+st.nextToken());
				System.out.println("Nama             : "+st.nextToken());
				System.out.println("Jenis Kelamin    : "+st.nextToken());
				System.out.println("Jabatan          : "+st.nextToken());
				System.out.println("Gaji             : "+st.nextToken());
				System.out.println("-----------------------------------");
				//update
				
				//ambil nama baru, jenis kelamin baru, jabatan baru, gaji baru
				
				String[] fieldData = {"nama","gender","jabatan","gaji"};
				String[] tempData = new String[5];
				
				st = new StringTokenizer(data, ",");//refresh token
				String originalData = st.nextToken();
				tempData[0] = originalData;
				for(int i = 0; i < fieldData.length; i++) {
					boolean isUpdate = getYesorNo("Apakah anda ingin mengubah "+fieldData[i]);
				originalData = st.nextToken();
					if (isUpdate) {
						if (i == 0) {
							boolean ValidName = false;
//							terminalInput = new Scanner(System.in);
//							System.out.print("Masukan "+fieldData[i]+" baru: ");
							while (!ValidName) {
								terminalInput = new Scanner(System.in);
								System.out.print("Masukan "+fieldData[i]+" baru: ");
								tempData[i+1] = terminalInput.nextLine();
								ValidName = validasiNama(tempData[i+1]);
							}
//							tempData[i+1] = terminalInput.nextLine();
						}else if (i == 1) {
							boolean ValidGender = false;
							while (!ValidGender) {
								terminalInput = new Scanner(System.in);
								System.out.print("Masukan "+fieldData[i]+" baru: ");
								tempData[i+1] = terminalInput.nextLine();
								ValidGender = validasiGender(tempData[i+1]);
							}
							
						}else if (i == 2) {
							boolean ValidJob = false;
							while (!ValidJob) {
								terminalInput = new Scanner(System.in);
								System.out.print("Masukan "+fieldData[i]+" baru: ");
								tempData[i+1] = terminalInput.nextLine();
								ValidJob = validasiJabatan(tempData[i+1]);
							}
						}else {
							terminalInput = new Scanner(System.in);
						System.out.print("Masukan "+fieldData[i]+" baru: ");
						tempData[i+1] = terminalInput.nextLine();
						}
					}else {
						tempData[i+1] = originalData;
					}
				}
				//tampikan data baru ke layar
				st = new StringTokenizer(data, ",");//refresh token
				st.nextToken();
				System.out.println("\nData baru anda adalah:");
				System.out.println("-----------------------------------");
				System.out.println("Nama             : "+st.nextToken()+" --> "+tempData[1]);
				System.out.println("Jenis Kelamin    : "+st.nextToken()+" --> "+tempData[2]);
				System.out.println("Jabatan          : "+st.nextToken()+" --> "+tempData[3]);
				System.out.println("Gaji             : "+st.nextToken()+" --> "+tempData[4]);
				System.out.println("-----------------------------------");
				
				boolean isUpdate = getYesorNo("apakah anda yakin ingin mengupdate data tersebut");
				if (isUpdate) {
					boolean isExist = cekKaryawan(tempData,false);
					if (isExist) {
						System.err.println("data ini sudah ada");
					}else {
						//format data baru kedalam database
						String kode = tempData[0];
						String nama = tempData[1];
						String gender = tempData[2];
						String jabatan = tempData[3];
						String gaji = tempData[4];
						bufferedOutput.write(kode+","+nama+","+gender+","+jabatan+","+gaji);
//						bufferedOutput.newLine();
					}
				}else {
					//skip aja
					bufferedOutput.write(data);
//					bufferedOutput.newLine();
				}
				
				
			}else {
				//copy
				bufferedOutput.write(data);
//				bufferedOutput.newLine();
			}
			bufferedOutput.newLine();
			data = bufferedInput.readLine();
		}
		bufferedOutput.flush();
		File tempDB2 = new File("tempDB.txt");
		FileReader fileTempInput = new FileReader(tempDB2);
		BufferedReader bufferedTempInput = new BufferedReader(fileTempInput);
		
		//buat database sementara
		File database2 = new File("database.txt");
		FileWriter fileFinalOutput = new FileWriter(database2);
		BufferedWriter bufferedFinalOutput = new BufferedWriter(fileFinalOutput);
		String copydata = bufferedTempInput.readLine();//baca perbaris
		while (copydata != null) {
				bufferedFinalOutput.write(copydata);
//				System.out.println(copydata);
				bufferedFinalOutput.newLine();
			copydata = bufferedTempInput.readLine();
		}
		bufferedFinalOutput.flush();
		tampilkanData();
	}
	
	private static void deleteData() throws IOException{
		//kita ambil database original
		File database = new File("database.txt");
		FileReader fileInput = new FileReader(database);
		BufferedReader bufferedInput = new BufferedReader(fileInput);
		
		//buat database sementara
		File tempDB = new File("tempDB.txt");
		FileWriter fileOutput = new FileWriter(tempDB);
		BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);
		//tampilkan data
		System.out.println("List Karyawan");
		tampilkanData();
		//kita ambil userinput u/ delete data
		Scanner terminalInput = new Scanner(System.in);
		System.out.print("\nMasukan no Karyawan yg mau dihapus: ");
		int deleteNum = terminalInput.nextInt();
		//looping u/ membaca tiap data dan skip data yg akan didelete
		boolean isFound = false;
		int entryCounts = 0;
		String data = bufferedInput.readLine();//baca perbaris
		while (data != null) {
			entryCounts++;
			boolean isDelete = false;
			//tampilkan data yg ingin dihapus
			StringTokenizer st = new StringTokenizer(data, ",");
			
			if (deleteNum == entryCounts) {
				System.out.println("\nData yg ingin anda hapus:");
				System.out.println("----------------------------");
				System.out.println("Kode: "+st.nextToken());
				System.out.println("Nama: "+st.nextToken());
				System.out.println("Gender: "+st.nextToken());
				System.out.println("Jabatan: "+st.nextToken());
				System.out.println("Gaji: "+st.nextToken());
				System.out.println("----------------------------");
				
				isDelete = getYesorNo("Apakah anda yakin mau hapus");
				isFound = true;
			}
			if (isDelete) {
				//skip pindahkan data dari original ke sementara
				System.out.println("Data berhasil dihapus");
			}else {
				//pindahin data dari original ke sementara
				bufferedOutput.write(data);
				bufferedOutput.newLine();
			}
			data = bufferedInput.readLine();
		}
		if (!isFound) {
			System.err.println("Karyawan tidak ditemukan!");
		}
		bufferedOutput.flush();
		
		
		File tempDB2 = new File("tempDB.txt");
		FileReader fileTempInput = new FileReader(tempDB2);
		BufferedReader bufferedTempInput = new BufferedReader(fileTempInput);
		
		//buat database sementara
		File database2 = new File("database.txt");
		FileWriter fileFinalOutput = new FileWriter(database2);
		BufferedWriter bufferedFinalOutput = new BufferedWriter(fileFinalOutput);
		String copydata = bufferedTempInput.readLine();//baca perbaris
		while (copydata != null) {
				bufferedFinalOutput.write(copydata);
//				System.out.println(copydata);
				bufferedFinalOutput.newLine();
			copydata = bufferedTempInput.readLine();
		}
		bufferedFinalOutput.flush();
		tampilkanData();
		
	}
	
	
	private static boolean validasiNama(String nama) {
		if ( nama.length() < 3) {
			System.err.println("INVALID NAME :( <<name too short>>");
			return false;
		}else {
			return true;
		}
	}
	
	private static boolean validasiGender(String gender) {
		if (gender.equals("Laki-laki")) {
			return true;
		}else if (gender.equals("Perempuan")) {
			return true;
		}else {
			System.err.println("Invalid Gender :(");
			return false;
		}
	}
	
	private static boolean validasiJabatan(String jabatan) {
		if (jabatan.equals("Admin") || jabatan.equals("Manager")
				|| jabatan.equals("Supervisor")) {
			return true;
		}else {
			System.err.println("Invalid Job :(");
			return false;
		}
	}
	
	private static String getGaji(String jabatan) {
		if (jabatan.equals("Admin")) {
			return "4000000";
		}if (jabatan.equals("Supervisor")) {
			return "6000000";
		}if (jabatan.equals("Manager")) {
			return "8000000";
		}else {
			return "0";
		}
	}
	
	
	private static void cariData() throws IOException{
		//baca database ada atau tidak
		try {
           File file = new File("database.txt");
        } catch (Exception e){
            System.err.println("Database Tidak ditemukan");
            System.err.println("Silahkan tambah data terlebih dahoeloe");
            return;
        }
		//ambil keyword dari user
		Scanner terminalInput = new Scanner(System.in);
		System.out.print("Masukan NAMA untuk mencari karyawan: ");
		String cariString = terminalInput.nextLine();
		System.out.println(cariString);
		String[] keywords = cariString.split("\\s+");
		//kita cek keyword di database
		cekKaryawan(keywords, true);
	}
	private static boolean cekKaryawan(String[] keywords, boolean isDisplay) throws IOException{
		FileReader fileInput = new FileReader("database.txt");
		BufferedReader bufferInput = new BufferedReader(fileInput);
		String data = bufferInput.readLine();
		boolean isExist = false;
		int nomorData = 0;
		if (isDisplay) {
			System.out.println("\n| No |\tKode     |\tNama                   |   Jenis-Kelamin     |\tJabatan      |\t Gaji");
	        System.out.println("----------------------------------------------------------------------------------------------------------");
		}

		while(data != null){
			
			//cek keyword didalam baris
			isExist = true;
//			System.out.println(data);
//			System.out.println(Arrays.toString(keywords));
			for(String keyword:keywords) {
				isExist = isExist && data.toLowerCase().contains(keyword.toLowerCase());
			}
//			System.out.println(isExist);
			//jika cocok tampilkan

			if(isExist) {
				if (isDisplay) {
					nomorData++;
//					System.out.println(data);
					StringTokenizer stringToken = new StringTokenizer(data, ",");

//		            stringToken.nextToken();
		            System.out.printf("| %2d ", nomorData);
		            System.out.printf("|\t%7s  ", stringToken.nextToken());
		            System.out.printf("|\t%-20s   ", stringToken.nextToken());
		            System.out.printf("|\t%-10s   ", stringToken.nextToken());
		            System.out.printf("|\t%-10s   ", stringToken.nextToken());
		            System.out.printf("|\t%s   ", stringToken.nextToken());
		            System.out.print("\n");
				}
	            break;
			}
			data = bufferInput.readLine();
		}
		if (isDisplay) {
			System.out.println("----------------------------------------------------------------------------------------------------------");
		}
		 return isExist;
	}
	
	private static void tambahData() throws IOException{
		boolean ValidName = false;
		boolean ValidGender = false;
		boolean ValidJob = false;
		FileWriter fileOutput = new FileWriter("database.txt",true);
        BufferedWriter bufferOutput = new BufferedWriter(fileOutput);
        Scanner terminalInput = new Scanner(System.in);
        
        String nama = null, jeniskelamin = null, jabatan = null, gaji = null;
//        System.out.print("Masukan nama: ");
//        nama = terminalInput.nextLine();
        while (!ValidName) {
        	System.out.print("Masukan nama (Min. 3 Alphabet): ");
            nama = terminalInput.nextLine();
            ValidName = validasiNama(nama);
        }
        
        while (!ValidGender) {
        	System.out.print("Masukan gender(Laki-laki / Perempuan): ");
            jeniskelamin = terminalInput.nextLine();
            ValidGender = validasiGender(jeniskelamin);
        }
        
        while (!ValidJob) {
        	System.out.print("Masukan jabatan(Supervisor/Admin/Manager): ");
            jabatan = terminalInput.nextLine();
            ValidJob = validasiJabatan(jabatan);
            gaji = getGaji(jabatan);
            if (gaji != "0") {
            	System.out.println("Gaji: "+gaji);
            }
        }
        
        //check buku di database
        System.out.println(nama+","+jeniskelamin+","+jabatan);
        String[] keywords = {nama+","+jeniskelamin+","+jabatan};
        
        boolean isExist = cekKaryawan(keywords, false);
        if (isExist) {
        	System.err.println("Karyawan ini sudah terdaftar!");
        }
        //nulis data di database
        if (!isExist) {
        	char[] kodeUnik = {'X','X', '-', 'X', 'X', 'X','X'};
            char m1 = 'A';
            Random rand = new Random();
            int m1plus = rand.nextInt(26);
            for (int i = 0; i < m1plus; i++) {
            	if (i == 1) {
            		m1 = 'A';
            	}if (i == 2) {
            		m1 = 'B';
            	}if (i == 3) {
            		m1 = 'C';
            	}if (i == 4) {
            		m1 = 'D';
            	}if (i == 5) {
            		m1 = 'E';
            	}if (i == 6) {
            		m1 = 'F';
            	}if (i == 7) {
            		m1 = 'G';
            	}if (i == 8) {
            		m1 = 'H';
            	}if (i == 9) {
            		m1 = 'I';
            	}if (i == 10) {
            		m1 = 'J';
            	}if (i == 11) {
            		m1 = 'K';
            	}if (i == 12) {
            		m1 = 'L';
            	}if (i == 13) {
            		m1 = 'M';
            	}if (i == 14) {
            		m1 = 'N';
            	}if (i == 15) {
            		m1 = 'O';
            	}if (i == 16) {
            		m1 = 'P';
            	}if (i == 17) {
            		m1 = 'Q';
            	}if (i == 18) {
            		m1 = 'R';
            	}if (i == 19) {
            		m1 = 'S';
            	}if (i == 20) {
            		m1 = 'T';
            	}if (i == 21) {
            		m1 = 'U';
            	}if (i == 22) {
            		m1 = 'V';
            	}if (i == 23) {
            		m1 = 'W';
            	}if (i == 24) {
            		m1 = 'X';
            	}if (i == 25) {
            		m1 = 'Y';
            	}if (i == 26) {
            		m1 = 'Z';
            	}
            }
            kodeUnik[0]= m1;
            char m2 = 'A';
            int m2plus = rand.nextInt(26);
            for (int i = 0; i < m2plus; i++) {
            	if (i == 1) {
            		m2 = 'A';
            	}if (i == 2) {
            		m2 = 'B';
            	}if (i == 3) {
            		m2 = 'C';
            	}if (i == 4) {
            		m2 = 'D';
            	}if (i == 5) {
            		m2 = 'E';
            	}if (i == 6) {
            		m2 = 'F';
            	}if (i == 7) {
            		m2 = 'G';
            	}if (i == 8) {
            		m2 = 'H';
            	}if (i == 9) {
            		m2 = 'I';
            	}if (i == 10) {
            		m2 = 'J';
            	}if (i == 11) {
            		m2 = 'K';
            	}if (i == 12) {
            		m2 = 'L';
            	}if (i == 13) {
            		m2 = 'M';
            	}if (i == 14) {
            		m2 = 'N';
            	}if (i == 15) {
            		m2 = 'O';
            	}if (i == 16) {
            		m2 = 'P';
            	}if (i == 17) {
            		m2 = 'Q';
            	}if (i == 18) {
            		m2 = 'R';
            	}if (i == 19) {
            		m2 = 'S';
            	}if (i == 20) {
            		m2 = 'T';
            	}if (i == 21) {
            		m2 = 'U';
            	}if (i == 22) {
            		m2 = 'V';
            	}if (i == 23) {
            		m2 = 'W';
            	}if (i == 24) {
            		m2 = 'X';
            	}if (i == 25) {
            		m2 = 'Y';
            	}if (i == 26) {
            		m2 = 'Z';
            	}
            }
            kodeUnik[1]= m2;
            char d1 = '0';
            int d1plus = rand.nextInt(9);
            for (int i = 0; i < d1plus; i++) {
            	if (i == 0) {
            		d1 = '0';
            	}if (i == 1) {
            		d1 = '0';
            	}if (i == 2) {
            		d1 = '2';
            	}if (i == 3) {
            		d1 = '3';
            	}if (i == 4) {
            		d1 = '4';
            	}if (i == 5) {
            		d1 = '5';
            	}if (i == 6) {
            		d1 = '6';
            	}if (i == 7) {
            		d1 = '7';
            	}if (i == 8) {
            		d1 = '8';
            	}if (i == 9) {
            		d1 = '9';
            	}
            }
            kodeUnik[3]= d1;
            char d2 = '0';
            int d2plus = rand.nextInt(9);
            for (int i = 0; i < d2plus; i++) {
            	if (i == 0) {
            		d2 = '0';
            	}if (i == 1) {
            		d2 = '0';
            	}if (i == 2) {
            		d2 = '2';
            	}if (i == 3) {
            		d2 = '3';
            	}if (i == 4) {
            		d2 = '4';
            	}if (i == 5) {
            		d2 = '5';
            	}if (i == 6) {
            		d2 = '6';
            	}if (i == 7) {
            		d2 = '7';
            	}if (i == 8) {
            		d2 = '8';
            	}if (i == 9) {
            		d2 = '9';
            	}
            }
            kodeUnik[4]= d2;
            char d3 = '0';
            int d3plus = rand.nextInt(9);
            for (int i = 0; i < d3plus; i++) {
            	if (i == 0) {
            		d3 = '0';
            	}if (i == 1) {
            		d3 = '0';
            	}if (i == 2) {
            		d3 = '2';
            	}if (i == 3) {
            		d3 = '3';
            	}if (i == 4) {
            		d3 = '4';
            	}if (i == 5) {
            		d3 = '5';
            	}if (i == 6) {
            		d3 = '6';
            	}if (i == 7) {
            		d3 = '7';
            	}if (i == 8) {
            		d3 = '8';
            	}if (i == 9) {
            		d3 = '9';
            	}
            }
            kodeUnik[5]= d3;
            char d4 = '0';
            int d4plus = rand.nextInt(9);
            for (int i = 0; i < d4plus; i++) {
            	if (i == 0) {
            		d4 = '0';
            	}if (i == 1) {
            		d4 = '0';
            	}if (i == 2) {
            		d4 = '2';
            	}if (i == 3) {
            		d4 = '3';
            	}if (i == 4) {
            		d4 = '4';
            	}if (i == 5) {
            		d4 = '5';
            	}if (i == 6) {
            		d4 = '6';
            	}if (i == 7) {
            		d4 = '7';
            	}if (i == 8) {
            		d4 = '8';
            	}if (i == 9) {
            		d4 = '9';
            	}
            }
            kodeUnik[6]= d4;
            String strKode = "XX-XXXX";
            strKode = String.valueOf(kodeUnik);
            System.out.println("<<Data Insert Berhasil>>\nKode "+nama+": "+strKode);
            
            System.out.println("\nData yang di-insert adalah:");
            System.out.println("===========================");
            System.out.println("Primary Key: "+strKode);
            System.out.println("Nama: "+nama);
            System.out.println("Gender: "+jeniskelamin);
            System.out.println("Jabatan: "+jabatan);
            System.out.println("Gaji: "+gaji);
            System.out.println("===========================");
            
            boolean isTambah= getYesorNo("Apakah anda ingin menambah data tersebut");
            if (isTambah) {
            	bufferOutput.write(strKode+","+nama+","+jeniskelamin+","+jabatan+","+gaji);
            	bufferOutput.newLine();
            	bufferOutput.flush();
            }
        }
        
	}
	
	private static void clearScreen(){
	        try {
	            if (System.getProperty("os.name").contains("Windows")){
	                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
	            } else {
	                System.out.print("\033\143");
	            }
	        } catch (Exception ex){
	            System.err.println("tidak bisa clear screen");
	        }
	}
	
}
