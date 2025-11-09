##Simulasi Monitoring Sensor Suhu

Project ini adalah latihan untuk mata kuliah PBO dengan topik Thread. Pada project ini saya membuat simulasi monitoring sensor suhu menggunakan dua komponen utama, yaitu producer (penghasil data sensor secara berkala) dan consumer (penyimpan data sensor ke database).

Sistem ini mensimulasikan data suhu secara acak, di mana setiap sensor berjalan pada thread terpisah, kemudian hasil pembacaan sensor tersebut dimasukkan ke dalam antrian (BlockingQueue) dan diproses oleh thread khusus untuk disimpan ke database MySQL menggunakan JDBC. Setiap data yang berhasil tersimpan juga ditampilkan secara langsung di terminal.
