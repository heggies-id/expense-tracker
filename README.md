# Expense Tracker
Aplikasi Expense Tracker (Pelacak Pengeluaran) adalah aplikasi full-stack yang dirancang untuk membantu pengguna melacak pengeluaran harian mereka. Aplikasi ini dibangun dengan backend Java Spring Boot, frontend React, dan database PostgreSQL. Seluruh aplikasi dikemas menggunakan Docker untuk kemudahan instalasi dan penyiapan.

## Daftar Isi
* Instalasi & Menjalankan Aplikasi
    * Prasyarat
    * Menjalankan dengan Docker
* Struktur Proyek
    * Backend
    * Frontend
* Endpoint API

## Instalasi & Menjalankan Aplikasi
### Prasyarat
* Pastikan Anda telah menginstal perangkat lunak berikut di mesin Anda:
    * Docker
    * Docker Compose

### Menjalankan dengan Docker
1. Clone Repository

> git clone [https://github.com/heggies-id/expense-tracker.git](https://github.com/heggies-id/expense-tracker.git) && cd expense-tracker

2. Jalankan Aplikasi

Gunakan Docker Compose untuk membangun dan menjalankan semua layanan (backend, frontend, dan database) dengan satu perintah dari direktori root proyek.
> docker-compose up --build

3. Akses Aplikasi
* Frontend React akan berjalan di: `http://localhost:3000`
* Backend Spring Boot akan berjalan di: `http://localhost:8080`
* Database PostgreSQL akan dapat diakses di port: `5432`

## Struktur Proyek
Berikut adalah gambaran umum tentang struktur direktori proyek.

### Backend
Layanan backend dibangun menggunakan Spring Boot dan Maven.

```
backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/expensetracker/
│   │   │       ├── auth/           # DTOs untuk permintaan/respons Auth
│   │   │       ├── config/         # Konfigurasi aplikasi (CORS)
│   │   │       ├── controller/     # Kontroler REST (Auth & Expense)
│   │   │       ├── expense/        # Entitas Expense
│   │   │       ├── repository/     # Repositori JPA
│   │   │       ├── security/       # Konfigurasi keamanan & utilitas JWT
│   │   │       ├── service/        # Logika bisnis
│   │   │       └── user/           # Entitas User
│   │   └── resources/
│   │       ├── application.properties # Konfigurasi Spring Boot
│   └── test/
├── Dockerfile              # Instruksi untuk membangun image Docker backend
└── pom.xml                 # Dependensi & konfigurasi proyek Maven
```

### Frontend
Layanan frontend adalah aplikasi React.
```
frontend/
├── public/                 # Aset statis dan file index.html
├── src/
│   ├── api/                # Konfigurasi klien Axios
│   ├── components/         # Komponen React yang dapat digunakan kembali
│   ├── context/            # Konteks React (AuthContext)
│   ├── hooks/              # Custom hooks
│   ├── pages/              # Komponen halaman (Login, Register, Expenses)
│   ├── App.js              # Komponen root aplikasi
│   └── index.js            # Titik masuk aplikasi
├── Dockerfile              # Instruksi untuk membangun image Docker frontend
└── package.json            # Dependensi & skrip frontend
```

## Endpoint API
Berikut adalah endpoint API yang tersedia dari layanan backend.

### Autentikasi (`/api/auth`)
| Method  | Endpoint | Deskripsi |
|------|-----------|-|
|`POST`|`/register`| Mendaftarkan pengguna baru. |
|`POST`|`/login`   | Mengautentikasi pengguna dan mengembalikan token JWT |

### Pengeluaran (`/api/expenses`)
| Method  | Endpoint | Deskripsi |
|--------|-------|-|
|`GET`   |`/`    | Mendapatkan daftar semua pengeluaran untuk pengguna yang diautentikasi. |
|`GET`   |`/{id}`| Mendapatkan detail pengeluaran berdasarkan ID. |
|`POST`  |`/`    | Membuat catatan pengeluaran baru. |
|`PUT`   |`/{id}`| Memperbarui catatan pengeluaran yang ada. |
|`DELETE`|`/{id}`| Menghapus catatan pengeluaran. |
