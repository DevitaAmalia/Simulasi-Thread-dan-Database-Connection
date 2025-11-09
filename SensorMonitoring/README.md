**Nama: Devita Amalia Mulyono**
**NIM: F1D02310007**

---

# Simulasi Monitoring Sensor Suhu

## Summary
Project ini adalah latihan untuk mata kuliah PBO dengan topik Thread. Pada project ini saya membuat simulasi monitoring sensor suhu menggunakan dua komponen utama, yaitu producer (penghasil data sensor secara berkala) dan consumer (penyimpan data sensor ke database).
Pada project ini, `SensorProducer` berperan sebagai producer yang menghasilkan data sensor suhu secara berkala dan memasukkannya ke dalam `BlockingQueue`. Sedangkan `DbWriter` berperan sebagai consumer yang mengambil data dari queue tersebut dan menyimpannya ke dalam database MySQL menggunakan JDBC. Dengan demikian, proses pengambilan data dan penyimpanan dapat berjalan secara paralel pada thread yang berbeda.

---

## Struktur File
```
sensor-monitoring/
│
├─ lib/                    # JDBC driver
├─ src/
│   ├─ Main.java           # Menjalankan aplikasi, membuat queue, memulai & menghentikan thread
│   ├─ Db.java             # Menyediakan koneksi ke MySQL
│   ├─ SensorReading.java  # Menyimpan data sensor
│   ├─ SensorProducer.java # Simulasi sensor suhu, menghasilkan nilai acak, memasukkan ke queue
│   └─ DbWriter.java       # Mengambil data dari queue, menyimpan ke DB via JDBC, mencetak di terminal
└─ out/
```
---

## Alur Kerja
1. Program dimulai melalui Main
    - Membuat queue (`BlockingQueue`)
    - Membuat thread untuk 2 sensor (Producer)
    - Membuat thread untuk writer (Consumer)
2. `SensorProducer` berjalan
    - Menghasilkan nilai suhu acak (simulasi data sensor)
    - Setiap beberapa detik memasukkan data ke queue
3. Queue menyimpan data sementara
    - Menjadi penghubung antara producer dan consumer
4. `DbWriter` berjalan
    - Mengambil data sensor dari queue
    - Menyimpan data ke database MySQL menggunakan JDBC
    - Menampilkan data yang disimpan ke terminal
5. Setelah durasi tertentu (pada project ini adalah 60 detik), `Main` menghentikan semua thread
    - Program selesai

---

## Cara Menjalankan Program
1. Compile source code
```
javac -cp ".;lib/mysql-connector-j-9.5.0.jar" -d out src/*.java
```
2. Jalankan program
```
java -cp "out;lib/mysql-connector-j-9.5.0.jar" Main
```

---

## Output
<img width="654" height="762" alt="image" src="https://github.com/user-attachments/assets/c04c3a5e-2bac-4d0a-8e24-a3d2e52ad1e6" />
<img width="1048" height="709" alt="image" src="https://github.com/user-attachments/assets/2d982d2c-9e33-4011-9250-d29b771c5cc4" />

