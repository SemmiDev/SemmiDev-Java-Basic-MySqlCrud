package javacrud;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import static javacrud.JavaCRUD.JDBC_DRIVER;
import static javacrud.JavaCRUD.input;

public class DatabaseSiswa {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/databasesiswa";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);
    
     public static void main(String[] args) {
        try {
            // register driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (!conn.isClosed()) {
                showMenu();
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
      static void showMenu() {
        System.out.println("\n========= MENU UTAMA =========");
        System.out.println("1. Tampilkan Data Siswa");
        System.out.println("2. Tambah Data Siswa");
        System.out.println("3. Ubah Data Siswa");
        System.out.println("4. Hapus Data Siswa");
        System.out.println("0. Keluar");
        System.out.println("");
        System.out.print("PILIHAN> ");

        try {
            int pilihan = Integer.parseInt(input.readLine());

            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    tampilkanSiswa();
                    break;
                case 2:
                    tambahSiswa();
                    break;
                case 3:
                    ubahSiswa();
                    break;
                case 4:
                    hapusSiswa();
                    break;
                default:
                    System.out.println("Pilihan salah!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void tampilkanSiswa() {
        String sql = "SELECT * FROM siswa";

        try {
            rs = stmt.executeQuery(sql);
            
            System.out.println("+--------------------------------+");
            System.out.println("|  DATABASE SISWA SMAN 1 TALAMAU |");
            System.out.println("+--------------------------------+");

            while (rs.next()) {
                int idSiswa = rs.getInt("id_siswa");
                String nama = rs.getString("nama");
                String kelas = rs.getString("kelas");
                String nisn = rs.getString("nisn");
                String kelamin = rs.getString("jenis_kelamin");
                String umur = rs.getString("umur");
                String alamat = rs.getString("alamat");
                String email = rs.getString("email");
                
                
                System.out.println(
                        "No            : " + idSiswa + "\n" +
                        "Nama          : " + nama + "\n" +
                        "Kelas         : " + kelas + "\n" +
                        "NISN          : " + nisn + "\n" +
                        "Jenis Kelamin : " + kelamin + "\n" +
                        "Umur          : " + umur + "\n" +
                        "Alamat        : " + alamat + "\n" + 
                        "Email         : " + email + "\n"                
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    static void tambahSiswa() {
        try {
            // ambil input dari user
            System.out.print("Nama            : ");
            String nama = input.readLine().trim();
            
            System.out.print("Kelas           : ");
            String kelas = input.readLine().trim();
            
            System.out.print("NISN            : ");
            String nisn = input.readLine().trim();
            
            System.out.print("Jenis Kelamin   : ");
            String kelamin = input.readLine().trim();
            
            System.out.print("Umur            : ");
            String umur = input.readLine().trim();
            
            System.out.print("Alamat          : ");
            String alamat = input.readLine().trim();
            
            System.out.print("Email           : ");
            String email = input.readLine().trim();
 
            // query simpan
            String sql = "INSERT INTO siswa (nama,kelas,nisn,jenis_kelamin,umur,alamat,email) VALUE('%s','%s','%s','%s','%s','%s','%s')";
            sql = String.format(nama,kelas,nisn,kelamin,umur,alamat,email);

            // simpan buku
            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
                    
        }
    }
    
    static void ubahSiswa() {
        try {
            
            System.out.print("ID yang mau diedit: ");
            int idSiswa = Integer.parseInt(input.readLine());
            System.out.print("Nama            : ");
            String nama = input.readLine().trim();
            System.out.print("Kelas           : ");
            String kelas = input.readLine().trim();
            System.out.println("NISN          : ");
            String nisn = input.readLine().trim();
            System.out.println("Jenis Kelamin : ");
            String kelamin = input.readLine().trim();
            System.out.println("Umur          : ");
            String umur = input.readLine().trim();
            System.out.println("Alamat        : ");
            String alamat = input.readLine().trim();
            System.out.println("Email         : ");
            String email = input.readLine().trim();

            // query update
            String sql = "UPDATE siswa SET nama='%s', kelas='%s', nisn='%s', jenis_kelamin='%s', umur='%s', alamat='%s', email='%s' WHERE id_siswa=%d";
            sql = String.format(sql, nama,kelas,nisn,kelamin,umur,alamat,email, idSiswa);

            // update data buku
            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void hapusSiswa() {
        try {
            
            // ambil input dari user
            System.out.print("ID yang mau dihapus: ");
            int idSiswa = Integer.parseInt(input.readLine());
            
            // buat query hapus
            String sql = String.format("DELETE FROM siswa WHERE id_siswa=%d", idSiswa);

            // hapus data
            stmt.execute(sql);
            
            System.out.println("Data telah terhapus...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
