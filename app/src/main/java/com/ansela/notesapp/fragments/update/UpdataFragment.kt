package com.ansela.notesapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ansela.notesapp.R
import com.ansela.notesapp.UpdataFragmentArgs
import com.ansela.notesapp.data.model.NoteData
import com.ansela.notesapp.data.viewModelsData.NotesViewModel
import com.ansela.notesapp.databinding.FragmentUpdataBinding

class UpdataFragment : Fragment(){

    private val args by navArgs<UpdataFragmentArgs>()
    private val mSharedViewModels : SharedViewModels by viewModels()
    private val mNotesViewModel : NotesViewModel by viewModels()

    private var _binding : FragmentUpdataBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdataBinding.inflate(inflater,container,false)
        binding.args = args
        binding.spUpdate.onItemSelectedListener = mSharedViewModels.listener
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu,menu)
        }
    

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_save -> updataItem()
            R.id.menu_delete -> confirmItemRemoval()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmItemRemoval() {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete ${args.currentItem.title}' ?")
            .setMessage("Are you sure want to remove '${args.currentItem.title}' ?")
            .setPositiveButton("Yes"){_, _ ->
                mNotesViewModel.deleteData(args.currentItem)
                Toast.makeText(
                    requireContext(),"Succesfully Removed : ${args.currentItem.title}",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_updataFragment_to_listFragment)
            }
            .setNegativeButton("No"){_, _->}
            .create()
            .show()

    }

    private fun updataItem() {
        var title = binding.etUptitle.text.toString()
        val description = binding.etDescUpdate.text.toString()
        val getPriority = binding.spUpdate.selectedItem.toString()

        val validation = mSharedViewModels.verifyDataFromUser(title, description)
        if (validation){
            val updateItem = NoteData(
                args.currentItem.id,
                title,
                mSharedViewModels.parsePriority(getPriority),
                description
            )
            mNotesViewModel.updateData(updateItem)
            Toast.makeText(requireContext(),"Berhasil di update", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updataFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Tolong isi semua persyaratan", Toast.LENGTH_SHORT)
                .show()
        }
    }


}


