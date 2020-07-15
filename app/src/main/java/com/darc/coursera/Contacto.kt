package com.darc.coursera

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Patterns
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.darc.coursera.base.BaseActivityInterna
import com.darc.coursera.databinding.ActivityContactoBinding
import com.google.gson.Gson
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import java.lang.Exception
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class Contacto : BaseActivityInterna(),ContactoVM.Listener {


    companion object {

        /**
         * Ingresar User y Pass
         */
        const val USER = ""
        const val PASS = ""

        fun startActivity(context: Context){
            val intent = Intent(context,Contacto::class.java)
            context.startActivity(intent)

        }
    }

    lateinit var binding : ActivityContactoBinding

    lateinit var viewModel : ContactoVM

    private var session : Session? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_contacto)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(ContactoVM::class.java)
        viewModel.listener = this

        binding.viewModel = viewModel
    }

    override fun onEnviarDatos() {
        if(USER.isEmpty() || PASS.isEmpty()){
            toast("Por favor ingresar las credenciales de tu cuenta en el codigo para probar esta funcionalidad")
            return
        }

        if ((viewModel.nombre.get() ?: "").trim().isEmpty()) {
            binding.tvNombre.error = "Campo vacio"
            return
        } else
            binding.tvNombre.error = null

        if (!Patterns.EMAIL_ADDRESS.matcher(viewModel.user.get() ?: "").matches()) {
            binding.tvEmail.error = "Valor incorrecto"
            return
        } else
            binding.tvEmail.error = null

        if ((viewModel.desc.get() ?: "").trim().isEmpty()) {
            binding.tvDesc.error = "Campo vacio"
            return

        } else
            binding.tvDesc.error = null


        val police = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(police)

        val properties =  Properties()

        properties["mail.smtp.host"] = "smtp.googlemail.com"
        properties["mail.smtp.socketFactory.port"] = "465"
        properties["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
        properties["mail.smtp.auth"] = "true"
        properties["mail.smtp.posrt"] = "465"

        try {
            session = Session.getDefaultInstance(properties,object : Authenticator(){
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(USER,PASS)
                }
            })

            session?.let {
                val message = MimeMessage(session)
                message.setFrom(InternetAddress(USER))
                message.subject = "Envio de correo Petagram de ${viewModel.nombre.get() ?: ""}"
                message.setRecipients(Message.RecipientType.TO, arrayOf(InternetAddress(viewModel.user.get() ?: "")))
                message.setContent(viewModel.desc.get() ?: "","text/html; charset=utf-8")

                Transport.send(message)
            }
        } catch (e : Exception) {
            longToast("Error enviando el mensaje")
        }
    }
}

class ContactoVM : ViewModel() {

    var listener : Listener? = null

    val nombre = ObservableField("")

    val user = ObservableField("")

    val desc = ObservableField("")

    interface Listener {
        fun onEnviarDatos()
    }
}