package com.example.fingerprintdemo

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.os.CancellationSignal
import android.widget.Toast
import androidx.core.app.ActivityCompat

class FingerPrintHelper(private val context: Context) : FingerprintManager.AuthenticationCallback() {
    lateinit var cancellationSignal: CancellationSignal

    fun startAuth(manager: FingerprintManager, cryptoObject: FingerprintManager.CryptoObject) {
        cancellationSignal = CancellationSignal()

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null)
    }

    override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
        super.onAuthenticationError(errorCode, errString)
        Toast.makeText(context, "Erro de autenticação", Toast.LENGTH_LONG).show()
    }

    override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
        super.onAuthenticationHelp(helpCode, helpString)
        Toast.makeText(context, "Ajuda", Toast.LENGTH_LONG).show()
    }

    override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult?) {
        super.onAuthenticationSucceeded(result)
        Toast.makeText(context, "Autenticação concluída", Toast.LENGTH_LONG).show()
        context.startActivity(Intent(context, MinistryOfTheEnvironment::class.java))
    }

    override fun onAuthenticationFailed() {
        super.onAuthenticationFailed()
        Toast.makeText(context, "Falha de autenticação", Toast.LENGTH_LONG).show()
    }
}