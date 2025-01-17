-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 10, 2025 at 08:37 AM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penyewaan_pc`
--

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `id_karyawan` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `no_telepon` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`id_karyawan`, `nama`, `alamat`, `no_telepon`) VALUES
(1, 'Roman', 'Sei Andai', '0854789655412'),
(2, 'Dayat Casper', 'Sei Gampa', '085475632110'),
(4, 'Afriza Basirih Gaming', 'Basirih Mantuil', '6285755412039'),
(6, 'Roman 1', 'Buntok', '086788435678');

-- --------------------------------------------------------

--
-- Table structure for table `pc`
--

CREATE TABLE `pc` (
  `id_pc` int(11) NOT NULL,
  `merk` varchar(50) NOT NULL,
  `tipe` varchar(50) DEFAULT NULL,
  `harga_sewa` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pc`
--

INSERT INTO `pc` (`id_pc`, `merk`, `tipe`, `harga_sewa`) VALUES
(1, 'Asus', 'Gaming Notebook', 500000),
(2, 'Acer', 'Notebook', 300000),
(4, 'Lenovo', 'Ideapad', 120000),
(6, 'Xiaomi', 'Notepad', 120000),
(7, 'Apple', 'Macbook', 100000),
(8, 'Vivo', 'Laptop', 12000),
(9, 'Oppo', 'Laptop', 120000);

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `no_telepon` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama`, `alamat`, `no_telepon`) VALUES
(1, 'Gilang Fahreza Gandhi', 'Palangkaraya', '08545123644'),
(2, 'Afriza Basirih', 'Basirih Gaming', '056321145211'),
(4, 'Juna Riam', 'Sei Riam', '65212454545');

-- --------------------------------------------------------

--
-- Table structure for table `penyewaan`
--

CREATE TABLE `penyewaan` (
  `id_penyewaan` int(11) NOT NULL,
  `merk` varchar(50) DEFAULT NULL,
  `tipe` varchar(50) DEFAULT NULL,
  `harga_sewa` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penyewaan`
--

INSERT INTO `penyewaan` (`id_penyewaan`, `merk`, `tipe`, `harga_sewa`) VALUES
(1, 'Dell', 'XPS 15', 50000),
(2, 'HP', 'Omen 15', 45000),
(3, 'Lenovo', 'ThinkPad', 40000),
(4, 'Asus', 'laptop', 150000),
(5, 'Acer', 'laptop', 250000),
(6, 'Lenovo', 'laptop', 120000),
(7, 'Asus', 'laptop', 150000),
(8, 'Asus', 'laptop', 150000),
(9, 'Asus', 'laptop', 150000),
(10, 'Asus', 'laptop', 150000),
(11, 'Asus', 'laptop', 150000),
(12, 'Asus', 'laptop', 150000),
(13, 'Asus', 'laptop', 150000),
(14, 'Asus', 'laptop', 150000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `id_pelanggan` int(11) DEFAULT NULL,
  `lama_sewa` int(11) DEFAULT NULL,
  `total_harga` int(11) DEFAULT NULL,
  `id_penyewaan` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_pelanggan`, `lama_sewa`, `total_harga`, `id_penyewaan`) VALUES
(3, 1, 1, 500000, 1),
(4, 2, 1, 900000, 2),
(5, 1, 2, 1000000, 1),
(8, 4, 2, 1000000, 1),
(9, 4, 4, 2000000, 1),
(10, 2, 1, 500000, 1),
(18, 1, 1, 300000, 2),
(19, 2, 1, 120000, 4),
(20, 1, 1, 100000, 7),
(21, 1, 2, 240000, 9);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indexes for table `pc`
--
ALTER TABLE `pc`
  ADD PRIMARY KEY (`id_pc`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indexes for table `penyewaan`
--
ALTER TABLE `penyewaan`
  ADD PRIMARY KEY (`id_penyewaan`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_pelanggan` (`id_pelanggan`),
  ADD KEY `fk_id_penyewaan` (`id_penyewaan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `karyawan`
--
ALTER TABLE `karyawan`
  MODIFY `id_karyawan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `pc`
--
ALTER TABLE `pc`
  MODIFY `id_pc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `id_pelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `penyewaan`
--
ALTER TABLE `penyewaan`
  MODIFY `id_penyewaan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `fk_id_penyewaan` FOREIGN KEY (`id_penyewaan`) REFERENCES `penyewaan` (`id_penyewaan`) ON DELETE CASCADE,
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
