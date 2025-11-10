package com.example.tugasuts

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Konstanta untuk nilai default (menghindari hardcode dan memudahkan perubahan)
    private val DEFAULT_USERNAME = "usm123"
    private val DEFAULT_PASSWORD = "usmjaya"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi semua view berdasarkan ID dari layout XML
        val email = findViewById<EditText>(R.id.editEmail)
        val username = findViewById<EditText>(R.id.editUsername)
        val firstName = findViewById<EditText>(R.id.editFirstName)
        val lastName = findViewById<EditText>(R.id.editLastName)
        val password = findViewById<EditText>(R.id.editPassword)
        val confirmPassword = findViewById<EditText>(R.id.editConfirmPassword)
        val submitButton = findViewById<Button>(R.id.btnSubmit)
        val cancelButton = findViewById<Button>(R.id.btnCancel)

        // Listener untuk tombol Kirim: Jalankan validasi formulir
        submitButton.setOnClickListener {
            validateForm(
                email.text.toString().trim(),
                username.text.toString().trim(),
                firstName.text.toString().trim(),
                lastName.text.toString().trim(),
                password.text.toString(),
                confirmPassword.text.toString()
            )
        }

        // Listener untuk tombol Batal: Bersihkan form dan tampilkan pesan
        cancelButton.setOnClickListener {
            resetForm(email, username, firstName, lastName, password, confirmPassword)
            Toast.makeText(this, "Pendaftaran dibatalkan.", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Metode untuk memvalidasi formulir pendaftaran.
     * Melakukan pengecekan: field kosong, username default, password default, dan konfirmasi password.
     */
    private fun validateForm(
        emailText: String,
        usernameText: String,
        firstNameText: String,
        lastNameText: String,
        passwordText: String,
        confirmPasswordText: String
    ) {
        // Validasi 1: Semua inputan tidak boleh kosong
        if (emailText.isEmpty() || usernameText.isEmpty() || firstNameText.isEmpty() ||
            lastNameText.isEmpty() || passwordText.isEmpty() || confirmPasswordText.isEmpty()) {
            Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show()
            return
        }

        // Validasi 2: Username harus sesuai default
        if (usernameText != DEFAULT_USERNAME) {
            Toast.makeText(this, "Username harus '$DEFAULT_USERNAME'!", Toast.LENGTH_SHORT).show()
            return
        }

        // Validasi 3: Password harus sesuai default
        if (passwordText != DEFAULT_PASSWORD) {
            Toast.makeText(this, "Password salah! Gunakan '$DEFAULT_PASSWORD'.", Toast.LENGTH_SHORT).show()
            return
        }

        // Validasi 4: Password dan konfirmasi password harus sama
        if (passwordText != confirmPasswordText) {
            Toast.makeText(this, "Password dan Konfirmasi Password tidak cocok!", Toast.LENGTH_SHORT).show()
            return
        }

        // Jika semua validasi berhasil, tampilkan pesan sukses dengan nama gabungan
        val fullName = "$firstNameText $lastNameText"
        Toast.makeText(this, "Pendaftaran Berhasil: $fullName", Toast.LENGTH_LONG).show()
    }

    /**
     * Metode untuk mereset formulir (menghapus semua input).
     */
    private fun resetForm(
        email: EditText,
        username: EditText,
        firstName: EditText,
        lastName: EditText,
        password: EditText,
        confirmPassword: EditText
    ) {
        email.text.clear()
        username.text.clear()
        firstName.text.clear()
        lastName.text.clear()
        password.text.clear()
        confirmPassword.text.clear()
    }
}
