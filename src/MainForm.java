
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author muham
 */
public class MainForm extends javax.swing.JFrame {

    public MainForm() {
        initComponents();
        tampilDataKaryawan(); // Tampil data karyawan saat form pertama kali dimuat
        tampilDataPelanggan();  // Tampil data pelanggan saat form pertama kali dimuat
        tampilDataPC();  // Tampil data PC saat form pertama kali dimuat
        tampilDataTransaksi();  // Tampil data transaksi saat form pertama kali dimuat
        loadPelangganAndPC();   // Memuat data pelanggan dan PC ke ComboBox

        txtLamaSewa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                // Panggil metode untuk menghitung total harga ketika lama sewa berubah
                hitungTotalHarga();
            }
        });

        tblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Menangani klik pada baris tabel
                int row = tblTransaksi.getSelectedRow();
                if (row != -1) {
                    // Mengambil data dari tabel berdasarkan baris yang dipilih
                    String pelanggan = tblTransaksi.getValueAt(row, 1).toString(); // Kolom 1: Nama Pelanggan
                    String pc = tblTransaksi.getValueAt(row, 2).toString(); // Kolom 2: Merk PC
                    String lamaSewa = tblTransaksi.getValueAt(row, 3).toString(); // Kolom 3: Lama Sewa (ganti Tanggal Sewa dengan Lama Sewa)
                    String totalHarga = tblTransaksi.getValueAt(row, 4).toString(); // Kolom 4: Total Harga

                    // Mengisi ComboBox Pelanggan dan PC dengan pilihan yang sesuai
                    cmbPelanggan.setSelectedItem(pelanggan);  // Set ComboBox Pelanggan
                    cmbPC.setSelectedItem(pc);  // Set ComboBox PC

                    // Mengisi JTextField dengan data yang dipilih
                    txtLamaSewa.setText(lamaSewa); // Set Lama Sewa
                    txtTotalHarga.setText(totalHarga); // Set Total Harga
                }
            }
        });

        // Menambahkan MouseListener untuk klik pada tabel
        tblPC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Menangani klik pada baris tabel
                int row = tblPC.getSelectedRow();
                if (row != -1) {
                    // Ambil data dari tabel berdasarkan baris yang dipilih
                    String idPC = tblPC.getValueAt(row, 0).toString();
                    String merkPC = tblPC.getValueAt(row, 1).toString();
                    String tipePC = tblPC.getValueAt(row, 2).toString();
                    String hargaSewaPC = tblPC.getValueAt(row, 3).toString();

                    // Masukkan data yang diambil ke dalam JTextField
                    txtMerkPC.setText(merkPC);
                    txtTipePC.setText(tipePC);
                    txtHargaSewaPC.setText(hargaSewaPC);
                }
            }
        });

        // Menambahkan MouseListener untuk klik pada tabel
        tblPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Menangani klik pada baris tabel
                int row = tblPelanggan.getSelectedRow();
                if (row != -1) {
                    // Ambil data dari tabel berdasarkan baris yang dipilih
                    String idPelanggan = tblPelanggan.getValueAt(row, 0).toString();
                    String namaPelanggan = tblPelanggan.getValueAt(row, 1).toString();
                    String alamatPelanggan = tblPelanggan.getValueAt(row, 2).toString();
                    String noTeleponPelanggan = tblPelanggan.getValueAt(row, 3).toString();

                    // Masukkan data yang diambil ke dalam JTextField
                    txtNamaPelanggan.setText(namaPelanggan);
                    txtAlamatPelanggan.setText(alamatPelanggan);
                    txtNoTeleponPelanggan.setText(noTeleponPelanggan);
                }
            }
        });

        tblPC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Menangani klik pada baris tabel
                int row = tblPC.getSelectedRow();
                if (row != -1) {
                    // Ambil data dari tabel berdasarkan baris yang dipilih
                    String idPC = tblPC.getValueAt(row, 0).toString();
                    String merkPC = tblPC.getValueAt(row, 1).toString();
                    String tipePC = tblPC.getValueAt(row, 2).toString();
                    String hargaSewaPC = tblPC.getValueAt(row, 3).toString();

                    // Masukkan data yang diambil ke dalam JTextField
                    txtMerkPC.setText(merkPC);
                    txtTipePC.setText(tipePC);
                    txtHargaSewaPC.setText(hargaSewaPC);
                }
            }
        });

        tblKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Menangani klik pada baris tabel
                int row = tblKaryawan.getSelectedRow();
                if (row != -1) {
                    // Ambil data dari tabel berdasarkan baris yang dipilih
                    String idKaryawan = tblKaryawan.getValueAt(row, 0).toString();
                    String namaKaryawan = tblKaryawan.getValueAt(row, 1).toString();
                    String alamatKaryawan = tblKaryawan.getValueAt(row, 2).toString();
                    String noTeleponKaryawan = tblKaryawan.getValueAt(row, 3).toString();

                    // Masukkan data yang diambil ke dalam JTextField
                    txtNamaKaryawan.setText(namaKaryawan);
                    txtAlamatKaryawan.setText(alamatKaryawan);
                    txtNoTeleponKaryawan.setText(noTeleponKaryawan);
                }
            }
        });

        btnExportTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Memanggil fungsi exportToCSV untuk tabel transaksi
                exportToCSV(tblTransaksi, "transaksi_report.csv");
            }
        });

        btnExportPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Memanggil fungsi exportToCSV untuk tabel pelanggan
                exportToCSV(tblPelanggan, "pelanggan_report.csv");
            }
        });

        btnExportPc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Memanggil fungsi exportToCSV untuk tabel pelanggan
                exportToCSV(tblPelanggan, "pc_report.csv");
            }
        });

        btnExportKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Memanggil fungsi exportToCSV untuk tabel pelanggan
                exportToCSV(tblPelanggan, "karyawan_report.csv");
            }
        });

    }

    private void tampilkanData() {
        try {
            String sql = "SELECT * FROM transaksi"; // Ambil semua data transaksi
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            // Asumsi Anda memiliki DefaultTableModel untuk mengisi data ke tabel
            DefaultTableModel model = (DefaultTableModel) tblTransaksi.getModel();
            model.setRowCount(0); // Menghapus data lama dari tabel

            // Menambahkan data baru ke tabel
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id_transaksi"),
                    rs.getString("nama"),
                    rs.getString("merk"),
                    rs.getInt("lama_sewa"),
                    rs.getDouble("total_harga")
                };
                model.addRow(row); // Menambahkan baris baru ke tabel
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menampilkan data transaksi.");
        }
    }

    private void refreshTransaksi() {
        // Memanggil fungsi untuk memuat ulang data di combobox
        loadPelangganAndPC(); // Memuat data terbaru untuk combobox

        // Jika Anda ingin memuat ulang data untuk tabel transaksi, pastikan Anda memiliki metode seperti tampilkanData()
        tampilkanData();  // Memanggil metode yang menampilkan data transaksi terbaru di tabel
    }

    private void loadPelangganAndPC() {
        try {
            // Kosongkan ComboBox sebelum diisi data baru
            cmbPelanggan.removeAllItems();
            cmbPC.removeAllItems();

            // Memuat data Pelanggan ke ComboBox
            String sqlPelanggan = "SELECT nama FROM pelanggan";  // Hanya mengambil kolom 'nama'
            try (PreparedStatement pstPelanggan = DatabaseConnection.getConnection().prepareStatement(sqlPelanggan);
                    ResultSet rsPelanggan = pstPelanggan.executeQuery()) {
                while (rsPelanggan.next()) {
                    cmbPelanggan.addItem(rsPelanggan.getString("nama"));
                }
            }

            // Memuat data PC ke ComboBox
            String sqlPC = "SELECT merk, tipe FROM pc";  // Mengambil merk dan tipe dari pc
            try (PreparedStatement pstPC = DatabaseConnection.getConnection().prepareStatement(sqlPC);
                    ResultSet rsPC = pstPC.executeQuery()) {
                while (rsPC.next()) {
                    // Menambahkan item dengan format "merk - tipe"
                    cmbPC.addItem(rsPC.getString("merk") + " - " + rsPC.getString("tipe"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat memuat data");
        }
    }

    private void hitungTotalHarga() {
        try {
            // Mengambil harga sewa dari PC yang dipilih
            String pcSelected = cmbPC.getSelectedItem().toString();
            String[] pcParts = pcSelected.split(" - ");
            String merkPC = pcParts[0];

            String sqlPC = "SELECT harga_sewa FROM pc WHERE merk=?";
            PreparedStatement pstPC = DatabaseConnection.getConnection().prepareStatement(sqlPC);
            pstPC.setString(1, merkPC);
            ResultSet rsPC = pstPC.executeQuery();
            int hargaSewaPC = 0;
            if (rsPC.next()) {
                hargaSewaPC = rsPC.getInt("harga_sewa");
            }

            // Mengambil lama sewa dari text field
            int lamaSewa = Integer.parseInt(txtLamaSewa.getText());
            // Menghitung total harga
            int totalHarga = lamaSewa * hargaSewaPC;

            // Mengisi total harga ke text field
            txtTotalHarga.setText(String.valueOf(totalHarga));

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghitung total harga");
        } catch (NumberFormatException e) {
            // Tangani kesalahan jika lama sewa tidak valid (misalnya kosong atau bukan angka)
            txtTotalHarga.setText("");
        }
    }

    private void tampilDataPC() {
        try {
            String sql = "SELECT * FROM pc";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            Vector<String> columnNames = new Vector<>();
            int columnCount = rsmd.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }

            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }

            tblPC.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal Menampilkan Data PC");
        }
    }

    // Menampilkan data karyawan pada JTable
    private void tampilDataKaryawan() {
        try {
            String sql = "SELECT * FROM karyawan";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            Vector<String> columnNames = new Vector<>();
            int columnCount = rsmd.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }

            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }

            // Menambahkan data ke JTable
            tblKaryawan.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal Menampilkan Data Karyawan");
        }
    }

    private void tampilDataTransaksi() {
        try {
            // Memperbarui query SQL untuk menyesuaikan dengan perubahan kolom
            String sql = "SELECT t.id_transaksi, p.nama AS pelanggan, pc.merk AS pc, t.lama_sewa, t.total_harga "
                    + "FROM transaksi t "
                    + "JOIN pelanggan p ON t.id_pelanggan = p.id_pelanggan "
                    + "JOIN pc pc ON t.id_penyewaan = pc.id_pc";

            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            Vector<String> columnNames = new Vector<>();
            int columnCount = rsmd.getColumnCount();

            // Menambahkan nama kolom untuk tabel
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }

            Vector<Vector<Object>> data = new Vector<>();

            // Menambahkan data ke dalam tabel
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }

            // Mengatur model tabel dengan data yang diambil
            tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal Menampilkan Data Transaksi");
        }
    }

    private void tampilDataPelanggan() {
        try {
            String sql = "SELECT * FROM pelanggan";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            Vector<String> columnNames = new Vector<>();
            int columnCount = rsmd.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }

            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }

            tblPelanggan.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal Menampilkan Data Pelanggan");
        }
    }

    private void resetFormPC() {
        txtMerkPC.setText("");
        txtTipePC.setText("");
        txtHargaSewaPC.setText("");
    }

    private void resetFormKaryawan() {
        txtNamaKaryawan.setText("");
        txtAlamatKaryawan.setText("");
        txtNoTeleponKaryawan.setText("");
    }

    private void resetFormPelanggan() {
        txtNamaPelanggan.setText("");
        txtAlamatPelanggan.setText("");
        txtNoTeleponPelanggan.setText("");
    }

    private void resetTransaksiForm() {
        // Mengosongkan semua JTextField
        txtLamaSewa.setText("");
        txtTotalHarga.setText("");

        // Mengosongkan ComboBox (Pelanggan dan PC)
        cmbPelanggan.setSelectedIndex(0);  // Pilihan pertama di ComboBox Pelanggan
        cmbPC.setSelectedIndex(0);  // Pilihan pertama di ComboBox PC
    }

    public void exportToCSV(JTable table, String fileName) {
        try {
            // Membuka file writer
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            // Mendapatkan model tabel (untuk mengambil data)
            TableModel model = table.getModel();

            // Menulis header tabel
            for (int i = 0; i < model.getColumnCount(); i++) {
                writer.write(model.getColumnName(i)); // Menulis nama kolom
                if (i < model.getColumnCount() - 1) {
                    writer.write(","); // Menambah koma setelah nama kolom
                }
            }
            writer.newLine(); // Pindah ke baris baru

            // Menulis data tabel
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    writer.write(model.getValueAt(i, j).toString()); // Menulis nilai dari cell
                    if (j < model.getColumnCount() - 1) {
                        writer.write(","); // Menambah koma setelah data cell
                    }
                }
                writer.newLine(); // Pindah ke baris baru
            }

            writer.close(); // Menutup writer setelah selesai
            JOptionPane.showMessageDialog(this, "Data berhasil diexport ke CSV");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat mengekspor data ke CSV");
        }
    }

    public String chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih Lokasi dan Nama File");
        fileChooser.setSelectedFile(new java.io.File("data.csv")); // Nama file default

        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return null; // Jika pengguna membatalkan
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNamaKaryawan = new javax.swing.JTextField();
        txtAlamatKaryawan = new javax.swing.JTextField();
        txtNoTeleponKaryawan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKaryawan = new javax.swing.JTable();
        btnExportKaryawan = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnSimpanKaryawan = new javax.swing.JButton();
        btnHapusKaryawan = new javax.swing.JButton();
        btnUpdateKaryawan = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNamaPelanggan = new javax.swing.JTextField();
        txtAlamatPelanggan = new javax.swing.JTextField();
        txtNoTeleponPelanggan = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPelanggan = new javax.swing.JTable();
        btnExportPelanggan = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btnSimpanPelanggan = new javax.swing.JButton();
        btnHapusPelanggan = new javax.swing.JButton();
        btnUpdatePelanggan = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMerkPC = new javax.swing.JTextField();
        txtTipePC = new javax.swing.JTextField();
        txtHargaSewaPC = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPC = new javax.swing.JTable();
        btnSimpanPC = new javax.swing.JButton();
        btnHapusPC = new javax.swing.JButton();
        btnUpdatePC = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        btnExportPc = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cmbPelanggan = new javax.swing.JComboBox<>();
        cmbPC = new javax.swing.JComboBox<>();
        txtLamaSewa = new javax.swing.JTextField();
        txtTotalHarga = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        btnSimpanTransaksi = new javax.swing.JButton();
        btnExportTransaksi = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btnUpdateTransaksi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("APLIKASI PENYEWAAN PC");

        jLabel2.setText("Nama");

        jLabel3.setText("Alamat");

        jLabel4.setText("No Telepon");

        tblKaryawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblKaryawan);

        btnExportKaryawan.setText("Report");
        btnExportKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportKaryawanActionPerformed(evt);
            }
        });

        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnSimpanKaryawan.setText("Tambah");
        btnSimpanKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanKaryawanActionPerformed(evt);
            }
        });

        btnHapusKaryawan.setText("Hapus");
        btnHapusKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusKaryawanActionPerformed(evt);
            }
        });

        btnUpdateKaryawan.setText("Edit");
        btnUpdateKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateKaryawanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(77, 77, 77)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNoTeleponKaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(txtNamaKaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(txtAlamatKaryawan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSimpanKaryawan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdateKaryawan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapusKaryawan))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExportKaryawan)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNamaKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAlamatKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNoTeleponKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpanKaryawan)
                    .addComponent(btnHapusKaryawan)
                    .addComponent(btnUpdateKaryawan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExportKaryawan)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Karyawan", jPanel2);

        jLabel5.setText("Nama");

        jLabel6.setText("Alamat");

        jLabel7.setText("No Telepon");

        tblPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblPelanggan);

        btnExportPelanggan.setText("Report");
        btnExportPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportPelangganActionPerformed(evt);
            }
        });

        jButton5.setText("Reset");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btnSimpanPelanggan.setText("Tambah");
        btnSimpanPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanPelangganActionPerformed(evt);
            }
        });

        btnHapusPelanggan.setText("Hapus");
        btnHapusPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusPelangganActionPerformed(evt);
            }
        });

        btnUpdatePelanggan.setText("Edit");
        btnUpdatePelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatePelangganActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(78, 78, 78)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNamaPelanggan)
                            .addComponent(txtAlamatPelanggan)
                            .addComponent(txtNoTeleponPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSimpanPelanggan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdatePelanggan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapusPelanggan))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExportPelanggan)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAlamatPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNoTeleponPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpanPelanggan)
                    .addComponent(btnHapusPelanggan)
                    .addComponent(btnUpdatePelanggan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExportPelanggan)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Pelanggan", jPanel3);

        jLabel8.setText("Merk");

        jLabel9.setText("Tipe");

        jLabel10.setText("Harga Sewa");

        tblPC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblPC);

        btnSimpanPC.setText("Tambah");
        btnSimpanPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanPCActionPerformed(evt);
            }
        });

        btnHapusPC.setText("Hapus");
        btnHapusPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusPCActionPerformed(evt);
            }
        });

        btnUpdatePC.setText("Edit");
        btnUpdatePC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatePCActionPerformed(evt);
            }
        });

        jButton9.setText("Reset");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        btnExportPc.setText("Report");
        btnExportPc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportPcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnSimpanPC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdatePC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapusPC))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(72, 72, 72)
                                        .addComponent(txtHargaSewaPC))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(114, 114, 114)
                                        .addComponent(txtMerkPC, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(117, 117, 117)
                                        .addComponent(txtTipePC, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExportPc)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMerkPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTipePC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtHargaSewaPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanPC)
                    .addComponent(btnHapusPC)
                    .addComponent(btnUpdatePC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(btnExportPc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("PC", jPanel4);

        jLabel11.setText("Pelanggan");

        jLabel12.setText("PC");

        jLabel13.setText("Lama Sewa (bulan)");

        jLabel14.setText("Total");

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tblTransaksi);

        btnSimpanTransaksi.setText("Simpan");
        btnSimpanTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanTransaksiActionPerformed(evt);
            }
        });

        btnExportTransaksi.setText("Report");

        jButton7.setText("Reset");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btnUpdateTransaksi.setText("Edit");
        btnUpdateTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateTransaksiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(86, 86, 86)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLamaSewa, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                    .addComponent(txtTotalHarga))
                                .addGap(474, 474, 474))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cmbPC, javax.swing.GroupLayout.Alignment.LEADING, 0, 186, Short.MAX_VALUE)
                                    .addComponent(cmbPelanggan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSimpanTransaksi)
                            .addComponent(btnUpdateTransaksi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExportTransaksi)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cmbPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cmbPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtLamaSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(btnUpdateTransaksi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanTransaksi)
                    .addComponent(btnExportTransaksi)
                    .addComponent(jButton7))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Transaksi", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(59, 59, 59)
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanKaryawanActionPerformed
        try {
            String sql = "INSERT INTO karyawan (nama, alamat, no_telepon) VALUES (?, ?, ?)";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(sql);
            pst.setString(1, txtNamaKaryawan.getText());
            pst.setString(2, txtAlamatKaryawan.getText());
            pst.setString(3, txtNoTeleponKaryawan.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Karyawan Berhasil Disimpan");
            tampilDataKaryawan();  // Update tabel setelah simpan
            resetFormKaryawan();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan");
        }
    }//GEN-LAST:event_btnSimpanKaryawanActionPerformed

    private void btnUpdateKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateKaryawanActionPerformed
        try {
            String sql = "UPDATE karyawan SET nama=?, alamat=?, no_telepon=? WHERE id_karyawan=?";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(sql);
            pst.setString(1, txtNamaKaryawan.getText());
            pst.setString(2, txtAlamatKaryawan.getText());
            pst.setString(3, txtNoTeleponKaryawan.getText());
            pst.setInt(4, Integer.parseInt(tblKaryawan.getValueAt(tblKaryawan.getSelectedRow(), 0).toString()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Karyawan Berhasil Diupdate");
            tampilDataKaryawan();  // Update tabel setelah update
            resetFormKaryawan();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan");
        }
    }//GEN-LAST:event_btnUpdateKaryawanActionPerformed

    private void btnHapusKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusKaryawanActionPerformed
        try {
            String sql = "DELETE FROM karyawan WHERE id_karyawan=?";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(tblKaryawan.getValueAt(tblKaryawan.getSelectedRow(), 0).toString()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Karyawan Berhasil Dihapus");
            tampilDataKaryawan();
            resetFormKaryawan();// Update tabel setelah hapus
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan");
        }
    }//GEN-LAST:event_btnHapusKaryawanActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        resetFormKaryawan();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnSimpanPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanPelangganActionPerformed
        try {
            String sql = "INSERT INTO pelanggan (nama, alamat, no_telepon) VALUES (?, ?, ?)";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(sql);
            pst.setString(1, txtNamaPelanggan.getText());
            pst.setString(2, txtAlamatPelanggan.getText());
            pst.setString(3, txtNoTeleponPelanggan.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Pelanggan Berhasil Disimpan");
            tampilDataPelanggan();  // Update tabel setelah simpan
            resetFormPelanggan();   // Reset form setelah simpan
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan");
        }
    }//GEN-LAST:event_btnSimpanPelangganActionPerformed

    private void btnUpdatePelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePelangganActionPerformed
        try {
            String sql = "UPDATE pelanggan SET nama=?, alamat=?, no_telepon=? WHERE id_pelanggan=?";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(sql);
            pst.setString(1, txtNamaPelanggan.getText());
            pst.setString(2, txtAlamatPelanggan.getText());
            pst.setString(3, txtNoTeleponPelanggan.getText());
            pst.setInt(4, Integer.parseInt(tblPelanggan.getValueAt(tblPelanggan.getSelectedRow(), 0).toString()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Pelanggan Berhasil Diupdate");
            tampilDataPelanggan();  // Update tabel setelah update
            resetFormPelanggan();   // Reset form setelah update
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan");
        }
    }//GEN-LAST:event_btnUpdatePelangganActionPerformed

    private void btnHapusPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusPelangganActionPerformed
        try {
            String sql = "DELETE FROM pelanggan WHERE id_pelanggan=?";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(tblPelanggan.getValueAt(tblPelanggan.getSelectedRow(), 0).toString()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Pelanggan Berhasil Dihapus");
            tampilDataPelanggan();  // Update tabel setelah hapus
            resetFormPelanggan();   // Reset form setelah hapus
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan");
        }
    }//GEN-LAST:event_btnHapusPelangganActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        resetFormPelanggan();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnSimpanPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanPCActionPerformed
        try {
            String sql = "INSERT INTO pc (merk, tipe, harga_sewa) VALUES (?, ?, ?)";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(sql);
            pst.setString(1, txtMerkPC.getText());
            pst.setString(2, txtTipePC.getText());
            pst.setInt(3, Integer.parseInt(txtHargaSewaPC.getText()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data PC Berhasil Disimpan");
            tampilDataPC();  // Update tabel setelah simpan
            resetFormPC();   // Reset form setelah simpan
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan");
        }
    }//GEN-LAST:event_btnSimpanPCActionPerformed

    private void btnUpdatePCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePCActionPerformed
        try {
            String sql = "UPDATE pc SET merk=?, tipe=?, harga_sewa=? WHERE id_pc=?";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(sql);
            pst.setString(1, txtMerkPC.getText());
            pst.setString(2, txtTipePC.getText());
            pst.setInt(3, Integer.parseInt(txtHargaSewaPC.getText()));
            pst.setInt(4, Integer.parseInt(tblPC.getValueAt(tblPC.getSelectedRow(), 0).toString()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data PC Berhasil Diupdate");
            tampilDataPC();  // Update tabel setelah update
            resetFormPC();   // Reset form setelah update
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan");
        }
    }//GEN-LAST:event_btnUpdatePCActionPerformed

    private void btnHapusPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusPCActionPerformed
        try {
            String sql = "DELETE FROM pc WHERE id_pc=?";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(tblPC.getValueAt(tblPC.getSelectedRow(), 0).toString()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data PC Berhasil Dihapus");
            tampilDataPC();  // Update tabel setelah hapus
            resetFormPC();   // Reset form setelah hapus
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan");
        }
    }//GEN-LAST:event_btnHapusPCActionPerformed

    private void btnSimpanTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanTransaksiActionPerformed
        try {
            // Menghitung total harga (contoh: lama sewa * harga sewa per PC)
            String pcSelected = cmbPC.getSelectedItem().toString();
            String[] pcParts = pcSelected.split(" - ");
            String merkPC = pcParts[0];

            // Mengambil harga sewa dari PC yang dipilih
            String sqlPC = "SELECT harga_sewa FROM pc WHERE merk=?";
            PreparedStatement pstPC = DatabaseConnection.getConnection().prepareStatement(sqlPC);
            pstPC.setString(1, merkPC);
            ResultSet rsPC = pstPC.executeQuery();
            int hargaSewaPC = 0;
            if (rsPC.next()) {
                hargaSewaPC = rsPC.getInt("harga_sewa");
            }

            // Mengambil lama sewa dari input
            int lamaSewa = Integer.parseInt(txtLamaSewa.getText());
            int totalHarga = lamaSewa * hargaSewaPC;

            // Mengambil ID Pelanggan berdasarkan nama yang dipilih di ComboBox
            String pelangganSelected = cmbPelanggan.getSelectedItem().toString();
            String sqlPelanggan = "SELECT id_pelanggan FROM pelanggan WHERE nama=?";
            PreparedStatement pstPelanggan = DatabaseConnection.getConnection().prepareStatement(sqlPelanggan);
            pstPelanggan.setString(1, pelangganSelected);
            ResultSet rsPelanggan = pstPelanggan.executeQuery();
            int idPelanggan = 0;
            if (rsPelanggan.next()) {
                idPelanggan = rsPelanggan.getInt("id_pelanggan");
            } else {
                JOptionPane.showMessageDialog(this, "Pelanggan tidak ditemukan!");
                return; // Tidak lanjut jika ID Pelanggan tidak ditemukan
            }

            // Mengambil ID Penyewaan (PC) berdasarkan merk yang dipilih di ComboBox
            String sqlPenyewaan = "SELECT id_pc FROM pc WHERE merk=?";
            PreparedStatement pstPenyewaan = DatabaseConnection.getConnection().prepareStatement(sqlPenyewaan);
            pstPenyewaan.setString(1, merkPC);
            ResultSet rsPenyewaan = pstPenyewaan.executeQuery();
            int idPenyewaan = 0;
            if (rsPenyewaan.next()) {
                idPenyewaan = rsPenyewaan.getInt("id_pc");
            }

            // Menyimpan transaksi ke database
            String sql = "INSERT INTO transaksi (id_pelanggan, id_penyewaan, lama_sewa, total_harga) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(sql);

            // Set ID Pelanggan dan ID Penyewaan yang sudah ditemukan
            pst.setInt(1, idPelanggan); // ID Pelanggan
            pst.setInt(2, idPenyewaan); // ID Penyewaan

            // Menggunakan lama sewa (bulan) sebagai integer
            pst.setInt(3, lamaSewa);  // Kolom lama_sewa

            // Set total harga
            pst.setInt(4, totalHarga);

            // Eksekusi update
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Transaksi Berhasil Disimpan");
            tampilDataTransaksi();  // Update tabel setelah simpan
            resetTransaksiForm(); // Reset form setelah simpan

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan transaksi");
        }


    }//GEN-LAST:event_btnSimpanTransaksiActionPerformed

    private void btnUpdateTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTransaksiActionPerformed
        try {
            // Mengambil nama pelanggan dan merk PC yang dipilih dari ComboBox
            String pelangganSelected = cmbPelanggan.getSelectedItem().toString();
            String pcSelected = cmbPC.getSelectedItem().toString();
            String[] pcParts = pcSelected.split(" - ");
            String merkPC = pcParts[0]; // Merk PC dipisahkan dengan " - "

            // Mengambil ID Pelanggan berdasarkan nama yang dipilih
            String sqlPelanggan = "SELECT id_pelanggan FROM pelanggan WHERE nama=?";
            PreparedStatement pstPelanggan = DatabaseConnection.getConnection().prepareStatement(sqlPelanggan);
            pstPelanggan.setString(1, pelangganSelected);
            ResultSet rsPelanggan = pstPelanggan.executeQuery();
            int idPelanggan = 0;
            if (rsPelanggan.next()) {
                idPelanggan = rsPelanggan.getInt("id_pelanggan");
            } else {
                JOptionPane.showMessageDialog(this, "Pelanggan tidak ditemukan!");
                return; // Tidak lanjut jika ID Pelanggan tidak ditemukan
            }

            // Mengambil ID Penyewaan (PC) berdasarkan merk yang dipilih
            String sqlPC = "SELECT id_pc FROM pc WHERE merk=?";
            PreparedStatement pstPC = DatabaseConnection.getConnection().prepareStatement(sqlPC);
            pstPC.setString(1, merkPC);
            ResultSet rsPC = pstPC.executeQuery();
            int idPC = 0;
            if (rsPC.next()) {
                idPC = rsPC.getInt("id_pc");
            }

            // Mengambil lama sewa dari input
            int lamaSewa = Integer.parseInt(txtLamaSewa.getText()); // Lama Sewa (dalam bulan)
            int totalHarga = Integer.parseInt(txtTotalHarga.getText()); // Total Harga

            // Mengambil ID Transaksi dari tabel yang dipilih
            int idTransaksi = Integer.parseInt(tblTransaksi.getValueAt(tblTransaksi.getSelectedRow(), 0).toString());

            // Menyiapkan PreparedStatement untuk update
            String sql = "UPDATE transaksi SET id_pelanggan=?, id_penyewaan=?, lama_sewa=?, total_harga=? WHERE id_transaksi=?";
            PreparedStatement pst = DatabaseConnection.getConnection().prepareStatement(sql);
            pst.setInt(1, idPelanggan); // ID Pelanggan yang valid
            pst.setInt(2, idPC); // ID Penyewaan (PC)
            pst.setInt(3, lamaSewa); // Lama Sewa dalam bulan
            pst.setInt(4, totalHarga); // Total Harga
            pst.setInt(5, idTransaksi); // ID Transaksi untuk update

            // Menjalankan update
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Transaksi Berhasil Diupdate");
            tampilDataTransaksi();  // Update tabel setelah update

            // Memanggil ulang loadPelangganAndPC untuk memastikan data ter-refresh
            loadPelangganAndPC();
            resetTransaksiForm(); // Refresh ComboBox agar data terbaru tampil
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat mengupdate transaksi");
        }

    }//GEN-LAST:event_btnUpdateTransaksiActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        resetTransaksiForm();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        resetFormPC();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void btnExportKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportKaryawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportKaryawanActionPerformed

    private void btnExportPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportPelangganActionPerformed

    private void btnExportPcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportPcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportPcActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportKaryawan;
    private javax.swing.JButton btnExportPc;
    private javax.swing.JButton btnExportPelanggan;
    private javax.swing.JButton btnExportTransaksi;
    private javax.swing.JButton btnHapusKaryawan;
    private javax.swing.JButton btnHapusPC;
    private javax.swing.JButton btnHapusPelanggan;
    private javax.swing.JButton btnSimpanKaryawan;
    private javax.swing.JButton btnSimpanPC;
    private javax.swing.JButton btnSimpanPelanggan;
    private javax.swing.JButton btnSimpanTransaksi;
    private javax.swing.JButton btnUpdateKaryawan;
    private javax.swing.JButton btnUpdatePC;
    private javax.swing.JButton btnUpdatePelanggan;
    private javax.swing.JButton btnUpdateTransaksi;
    private javax.swing.JComboBox<String> cmbPC;
    private javax.swing.JComboBox<String> cmbPelanggan;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblKaryawan;
    private javax.swing.JTable tblPC;
    private javax.swing.JTable tblPelanggan;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField txtAlamatKaryawan;
    private javax.swing.JTextField txtAlamatPelanggan;
    private javax.swing.JTextField txtHargaSewaPC;
    private javax.swing.JTextField txtLamaSewa;
    private javax.swing.JTextField txtMerkPC;
    private javax.swing.JTextField txtNamaKaryawan;
    private javax.swing.JTextField txtNamaPelanggan;
    private javax.swing.JTextField txtNoTeleponKaryawan;
    private javax.swing.JTextField txtNoTeleponPelanggan;
    private javax.swing.JTextField txtTipePC;
    private javax.swing.JTextField txtTotalHarga;
    // End of variables declaration//GEN-END:variables
}
