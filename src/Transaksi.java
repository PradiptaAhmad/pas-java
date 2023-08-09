import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;


class membayar extends Menu {
    int uangMasuk;
    int uangKembali;
}
class Transaksi extends membayar {              //inheritance
    static Scanner forString = new Scanner(System.in);
    static Scanner forInt = new Scanner(System.in);
    static int hitung (int harga, int jumlah){
        int hasil;
        hasil = harga * jumlah;             // method dengan return
        return hasil;
    }
    static ArrayList <ListTransaksi> ListBarang = new ArrayList<>();

    public static void transaksi() throws IOException {
        long lineCount;
        Path path = Path.of("src\\database\\list.txt");
        try (Stream<String> stream = Files.lines(path)) {
            lineCount = stream.count();
        }

        BufferedReader read = new BufferedReader(new FileReader("src\\database\\list.txt"));
        System.out.printf("\n--------------------------------------------------------------------------------%n");
        System.out.printf("                               Toko Kelontong                                %n");
        System.out.printf("--------------------------------------------------------------------------------%n");
        System.out.printf("|%-3s | %-12s | %-11s | %-12s | %-15s | %-1s | %n", "No.", "Nama Barang", "Kode Barang", "Jenis Barang", "Jumlah Barang", "Harga Barang");
        System.out.printf("--------------------------------------------------------------------------------%n");
        int i;
        if (lineCount == 0) {
            System.out.println("List barang anda kosong.");
            System.out.println("tekan enter untuk kembali ke menu membayar ");
            forString.nextLine();

        } else {
            for ( i = 0; i < lineCount; i++) {
                String read1 = "src\\database\\" +read.readLine() +".txt";
                path = Path.of(read1);
                String namaBarang = Files.readAllLines(path).get(0);
                int jumlahBarang = Integer.parseInt(Files.readAllLines(path).get(3));
                int hargaBarang = Integer.parseInt(Files.readAllLines(path).get(4));
                String kodeBarang = Files.readAllLines(path).get(2);
                String jenisBarang = Files.readAllLines(path).get(2);
                System.out.printf("| %-3s | %-12s | %-11s | %-12s | %-15s | %-10s | %n", (i + 1) ,namaBarang, kodeBarang, jenisBarang, jumlahBarang, hargaBarang);
            }
        }
        System.out.print("Masukkan pilihan anda : ");
        int choice = forInt.nextInt();
        int jmlh;
        String namabarang = Files.readAllLines(path).get(choice - 1);
        int jumlahBarang = Integer.parseInt(Files.readAllLines(Paths.get("src\\database\\"+namabarang+ ".txt")).get(3));
        while (true) {
            System.out.print("Masukkan jumlah barang yang ingin dibeli : ");
            jmlh = forInt.nextInt();
            if (jmlh > jumlahBarang) {
                System.out.println("Jumlah barang kurang. Silahkan input ulang");
                continue;
            } else {
                break;
            }
        }
        String namaBarang = Files.readAllLines(path).get(choice - 1);
        int hargaBarang = Integer.parseInt(Files.readAllLines(Paths.get("src\\database\\" +namaBarang+ ".txt")).get(4));
        int total = hitung(hargaBarang, jmlh);
        System.out.println("Apakah anda ingin menambah barang lain ? [Ya/Tidak]: ");
        String pilih = forString.nextLine();
        if (pilih.equals("ya") || pilih.equals("Ya") || pilih.equals("YA") || pilih.equals("y")) {
            addBarang(namaBarang, total);
        } else {
            for (i = 0; i < 1; i++) {
                ListTransaksi[] data = new ListTransaksi[999];
                data[i] = new ListTransaksi();
                data[i].setNama(namaBarang);
                data[i].setHarga(total);
                ListBarang.add(data[i]);
            }
            outputData();
        }

    }
    public static void totalan()  {
        Transaksi bayar = new Transaksi();
        int angka1;
        if (ListBarang.size() == 0) {
            ListTransaksi harga = ListBarang.get(0);
            int total = harga.getHarga();
            System.out.println("Total Belanja anda adalah : " +total);
            while (true) {
                System.out.println("Masukkan uang anda : ");
                bayar.uangMasuk = forInt.nextInt();
                if (bayar.uangMasuk < total) {
                    System.out.println("Uang anda kurang silahkan input ulang");
                    continue;
                } else if (bayar.uangMasuk > total){
                    bayar.uangKembali = bayar.uangMasuk - total;
                    System.out.println("Uang anda lebih, anda berhak mendapatkan kembalian uang sebesar : " +bayar.uangKembali);
                    System.out.println("Terima kasih telah berbelanja di toko kami");
                    System.exit(0);
                } else if (bayar.uangMasuk == total) {
                    System.out.println("Uang anda pas. terima kasih telah berbelanja di toko kami");
                    System.exit(0);
                }
            }
        } else if (ListBarang.size() > 0) {
            int total = 0;
            for (int i = 0; i < ListBarang.size(); i++) {
                ListTransaksi harga1 = ListBarang.get(i);
                angka1 = harga1.getHarga();
                total = total + angka1;
            }
            System.out.println("Total Belanja anda adalah : " +total);
            while (true) {
                System.out.println("Masukkan uang anda : ");
                bayar.uangMasuk = forInt.nextInt();
                if (bayar.uangMasuk < total) {
                    System.out.println("Uang anda kurang silahkan input ulang");
                    System.exit(0);
                } else if (bayar.uangMasuk > total) {
                    bayar.uangKembali = bayar.uangMasuk - total;
                    System.out.println("Uang anda lebih, anda berhak mendapatkan kembalian uang sebesar : " + bayar.uangKembali);
                    System.out.println("Terima kasih telah berbelanja di toko kami");
                    System.exit(0);
                } else if (bayar.uangMasuk == total) {
                    System.out.println("Uang anda pas. terima kasih telah berbelanja di toko kami");
                    System.exit(0);
                }
            }
        }
    }
    public static void addBarang(String nama, int harga ) throws IOException{
        for (int i = 0; i < (1); i++) {
            ListTransaksi[] data = new ListTransaksi[1];
            data[i] = new ListTransaksi();
            data[i].setNama(nama);
            data[i].setHarga(harga);
            ListBarang.add(data[i]);
            System.out.println(harga);
            transaksi();
        }
    }
    public static void outputData() throws IOException {
        BufferedReader read = new BufferedReader(new FileReader("src\\database\\list.txt"));
        System.out.printf("\n--------------------------------------------------------------------------------%n");
        System.out.printf("                               List Barang Yang Akan Dibeli                       %n");
        System.out.printf("--------------------------------------------------------------------------------%n");
        System.out.printf("|%-3s | %-12s | %-11s | %-12s | %-15s | %-14s | %n", "No.", "Nama Barang", "Kode Barang", "Jenis Barang", "Jumlah Barang", "Harga Barang");
        System.out.printf("--------------------------------------------------------------------------------%n");
        int i = 0;
        if (ListBarang.size() < 0) {
            System.out.println("List barang anda kosong.");
            System.out.println("tekan enter untuk kembali ke menu membayar ");
            String enterkey = forString.nextLine();
            transaksi();

        } else {
            for ( i = 0; i < ListBarang.size(); i++) {
                ListTransaksi transaksi = ListBarang.get(i);
                String nama = transaksi.getNama();
                String read1 = "src\\database\\" +nama +".txt";
                Path path = Path.of(read1);
                String namaBarang = Files.readAllLines(path).get(0);
                int jumlahBarang = Integer.parseInt(Files.readAllLines(path).get(3));
                int hargaBarang = Integer.parseInt(Files.readAllLines(path).get(4));
                String kodeBarang = Files.readAllLines(path).get(2);
                String jenisBarang = Files.readAllLines(path).get(2);
                System.out.printf("| %-3s | %-12s | %-11s | %-12s | %-15s | %-14s | %n", (i + 1) ,namaBarang, kodeBarang, jenisBarang, jumlahBarang, hargaBarang);
            }
        }
        System.out.print("\nLanjutkan Transaksi? (y/n) : ");
        String bayar = forString.nextLine();
        if (bayar.equalsIgnoreCase("y")) {
            totalan();
        } else if (bayar.equalsIgnoreCase("n")) {
            System.out.println("Program Selesai");
        } else {
            System.out.println("Program Selesai");
        }
    }

}


