package com.certification.libreriaecos

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.certification.libreriaecos.data.local.entitties.BookDetailLocal
import com.certification.libreriaecos.data.local.entitties.BookLocal
import com.certification.libreriaecos.databinding.FragmentSecondBinding
import com.certification.libreriaecos.viewmodel.BookViewModel
import com.certification.libreriaecos.viewmodel.BookViewModelFactory
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private val bookViewModel: BookViewModel by activityViewModels {
        BookViewModelFactory((activity?.application as AppBook).repository)
    }

    lateinit var bookLocal: BookLocal

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        bookViewModel.selected.observe(viewLifecycleOwner){ local ->
            bookLocal = local
            bookViewModel.getOneBookFromDB(bookLocal.id).observe(viewLifecycleOwner){
                _binding!!.tvtitledetail.text = it.title
                _binding!!.tvauthordetail.text = it.author
                _binding!!.tvpages.text = "${it.pages} Pages"
                _binding!!.tvlink.text = "Link Detalle: ${it.link}"
                Picasso.get()
                    .load(it.imageLink)
                    .resize(150, 200)
                    .into(_binding!!.ivimagedetail)

            }
        }




        binding.buttonSecond.setOnClickListener {
            intentMail()
        }
    }

    private fun intentMail() {
        val destination = "ventas@ecosbooks.cl"
        val subject = "Consulta por libro {TITLE} id {ID}"
        val body = "Hola\n" +
                "Vi el libro {TITLE} de c??digo {ID} y me gustar??a que me contactaran a este correo o al siguiente n??mero _________\n" +
                "Quedo atento."
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, destination)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }
        startActivity(intent    )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}