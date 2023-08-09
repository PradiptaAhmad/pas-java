import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    static Scanner inputString = new Scanner(System.in);
    static Scanner inputInt = new Scanner(System.in);
    public static void menu() throws IOException {
        System.out.println("Toko kelontong 10 PPLG 2 ");
        System.out.println("==============================");
        System.out.println("Pilihan Menu");
        System.out.println("==============================");
        System.out.println("1. Masukkan data barang ");
        System.out.println("2. Lihat list barang");
        System.out.println("3. Transaksi");
        System.out.print("\ninput Your Choice : ");
        String choice = inputString.nextLine();
        if (choice.equals("1")) {
            inputData();
        } else if (choice.equals("2")) {
            bacaBarang();
        } else if (choice.equals("3")){
            Transaksi menjual = new Transaksi();
            menjual.transaksi();
        }
        else {
            System.out.println("Your input is wrong");
            menu();
        }
    }
    public static void inputData() throws IOException {
        ArrayList <ListBarang> barangku = new ArrayList<>();
        ListBarang barangKU = new ListBarang();
        System.out.print("Masukkan Nama barang   : ");
        barangKU.setNamaBarang(inputString.nextLine());
        System.out.print("Masukkan Jenis barang  : ");
        barangKU.setJenisBarang(inputString.nextLine());
        System.out.print("Masukkan Kode barang   : ");      // enkapsulasi dan arraylist
        barangKU.setKodeBarang(inputString.nextLine());
        System.out.print("Masukkan jumlah barang : ");
        barangKU.setJumlahBarang(inputString.nextLine());
        System.out.print("Masukkan harga barang  : ");
        barangKU.setHargaBarang(inputInt.nextInt());
        barangku.add(barangKU);

        ListBarang barangbarang = barangku.get(0);
        File file = new File("C:\\Users\\user\\IdeaProjects\\Tugas pas\\src\\database\\" +barangbarang.getNamaBarang() + ".txt");

        BufferedWriter tulis = new BufferedWriter(new FileWriter("C:\\Users\\user\\IdeaProjects\\Tugas pas\\src\\database\\list.txt", true));
        int lines = (int) Files.lines(Path.of("C:\\Users\\user\\IdeaProjects\\Tugas pas\\src\\database\\list.txt")).count();
        if (lines != 0) {
            tulis.newLine();
        }
        tulis.write(barangbarang.getNamaBarang()+"");
        tulis.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(barangbarang.getNamaBarang()+"");
        writer.newLine();
        writer.write(barangbarang.getJenisBarang()+"");
        writer.newLine();
        writer.write(barangbarang.getKodeBarang()+"");
        writer.newLine();
        writer.write(barangbarang.getJumlahBarang()+"");
        writer.newLine();
        writer.write(Long.toString(barangbarang.getHargaBarang()) +"");
        writer.close();
        System.out.println("Your data has written in database");

        System.out.println("Press enter to return to menu");
        String exit = inputString.nextLine();
        menu();
    }
    public static void bacaBarang() throws IOException{
        String choice;
        int pilihan = 0;
        String text = "";
        Path path = Paths.get("C:\\Users\\user\\IdeaProjects\\Tugas pas\\src\\database\\list.txt");
        int lines = (int) Files.lines(path).count();
        if (lines <= 0 ) {
            System.out.println("============={ list barang }=============");
            System.out.println("          \nData barang kosong\n         ");
            System.out.println("=========================================");
            System.out.println("Press enter to return to menu");
            String exit = inputString.nextLine();
            menu();
        }
        System.out.println("============={ list barang }=============");
        BufferedReader baca = new BufferedReader(new FileReader("C:\\Users\\user\\IdeaProjects\\Tugas pas\\src\\database\\list.txt"));
        for (int i = 1; i <= lines; i++) {
            System.out.println(i+ ". "+baca.readLine());
        }
        baca.close();
        System.out.println("=========================================");
        System.out.print("Masukkan pilihan anda : ");
        choice = inputInt.nextLine();
        try {
            pilihan = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("ONLY NUMBER!!");
            bacaBarang();
        }

        System.out.println("=========================================");
        System.out.println("1. Lihat data");
        System.out.println("2. Hapus data");
        System.out.println("=========================================");
        System.out.print("Masukkan pilihan anda : ");
        choice = inputInt.nextLine();
        int pilihanmu = 0;
        try {
            pilihanmu = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("ONLY NUMBER!!");
            bacaBarang();
            choice = null;
        }
        switch (pilihanmu) {
            case 1 -> lihatData(pilihan);
            case 2 -> hapusData(pilihan);
        }
    }
    public static void hapusData(int pilihan) throws IOException{
        File inputFile = new File("src\\database\\list.txt");
        File tempFile = new File("src\\database\\tempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        System.out.println(pilihan);
        String lineToRemove = Files.readAllLines(Paths.get("src\\database\\list.txt")).get(pilihan - 1);
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) continue;
            System.out.println(trimmedLine);
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();

        Files.delete(Paths.get("src\\database\\list.txt"));
        tempFile.renameTo(inputFile);
        Files.delete(Paths.get("src\\database\\" +lineToRemove+ ".txt"));
        System.out.println("Press enter to return to menu");
        System.out.println("Your data has been deleted");
        String exit = inputString.nextLine();
        if (!exit.equals("*")) {
            menu();
        }
    }
    public static void lihatData(int pilihan) throws IOException {
        String getLine = Files.readAllLines(Paths.get("src\\database\\list.txt")).get(pilihan - 1);
        System.out.println(getLine);
        BufferedReader read = new BufferedReader(new FileReader("src\\database\\" +getLine +".txt"));
        ArrayList <ListBarang> myBarang = new ArrayList<>();
        ListBarang barangbarang = new ListBarang();
        barangbarang.setNamaBarang(read.readLine());
        barangbarang.setJenisBarang(read.readLine());
        barangbarang.setKodeBarang(read.readLine());
        barangbarang.setJumlahBarang(read.readLine());
        barangbarang.setHargaBarang(Integer.parseInt(read.readLine()));
        myBarang.add(barangbarang);

        barangbarang = myBarang.get(0);
        System.out.println("Nama barang : " + barangbarang.getNamaBarang());
        System.out.println("Jenis barang : " + barangbarang.getJenisBarang());
        System.out.println("Kode barang : " + barangbarang.getKodeBarang());
        System.out.println("Jumlah barang : " + barangbarang.getJumlahBarang());
        System.out.println("Harga barang : " + barangbarang.getHargaBarang());

        System.out.println("Press enter to return to menu");
        String exit = inputString.nextLine();
        if (!exit.equals("*")) {
            menu();
        }
    }
    public static void list() throws IOException
    {
        int pilihan = 0;
        String text = "";
        Path path = Paths.get("C:\\Users\\user\\IdeaProjects\\Tugas pas\\src\\database\\list.txt");
        int lines = (int) Files.lines(path).count();
        if (lines <= 0 ) {
            System.out.println("============={ list barang }=============");
            System.out.println("          \nData barang kosong\n         ");
            System.out.println("=========================================");
            System.out.println("Press enter to return to menu");
            String exit = inputString.nextLine();
            menu();
        }
        System.out.println("============={ list barang }=============");
        BufferedReader baca = new BufferedReader(new FileReader("C:\\Users\\user\\IdeaProjects\\Tugas pas\\src\\database\\list.txt"));
        for (int i = 1; i <= lines; i++) {
            System.out.println(i+ ". "+baca.readLine());
        }
        baca.close();
        System.out.println("=========================================");
    }
}
