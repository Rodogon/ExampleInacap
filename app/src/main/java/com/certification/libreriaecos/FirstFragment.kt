package com.certification.libreriaecos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.certification.libreriaecos.databinding.FragmentFirstBinding
import com.certification.libreriaecos.viewmodel.BookViewModel
import com.certification.libreriaecos.viewmodel.BookViewModelFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val bookViewModel: BookViewModel by activityViewModels {
        BookViewModelFactory((activity?.application as AppBook).repository)
    }

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        obserbData()

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun obserbData() {
        bookViewModel.getAllBooksFromDB().observe(viewLifecycleOwner){
            Log.d("RODO", "UI observer $it")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}