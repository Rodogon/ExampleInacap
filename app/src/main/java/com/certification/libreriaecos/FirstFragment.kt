package com.certification.libreriaecos


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.certification.libreriaecos.data.local.entitties.BookLocal
import com.certification.libreriaecos.databinding.FragmentFirstBinding
import com.certification.libreriaecos.ui.BooksAdapter
import com.certification.libreriaecos.ui.OnItemClickListener
import com.certification.libreriaecos.viewmodel.BookViewModel
import com.certification.libreriaecos.viewmodel.BookViewModelFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), OnItemClickListener {

    private val bookViewModel: BookViewModel by activityViewModels {
        BookViewModelFactory((activity?.application as AppBook).repository)
    }

    private lateinit var adapter: BooksAdapter

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
        val recyclerView = _binding?.recyclerview
        if (recyclerView != null) {
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        adapter = BooksAdapter(this)
        if (recyclerView != null) {
            recyclerView.adapter = adapter
        }
        obserbData()


    }

    private fun obserbData() {
        bookViewModel.getAllBooksFromDB().observe(viewLifecycleOwner){
            Log.d("RODO", "UI observer $it")
            adapter.submitList(it)
        }
    }

    override fun onItemClick(book: BookLocal) {
        bookViewModel.getOneBoardGame(book)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}