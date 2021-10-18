package hector.ruiz.flickrbydxc.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import hector.ruiz.flickrbydxc.R
import hector.ruiz.flickrbydxc.databinding.ListFragmentBinding
import hector.ruiz.flickrbydxc.extensions.snackBarLong
import hector.ruiz.presentation.list.ListViewModel
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding
    private val listViewModel: ListViewModel by viewModels()

    @Inject
    lateinit var photoAdapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding?.root
    }

    private fun initRecyclerView() {
        binding?.photoList?.layoutManager = GridLayoutManager(
            context,
            COLUMNS_NUMBER,
            RecyclerView.VERTICAL,
            false
        )
        binding?.photoList?.adapter = photoAdapter
        photoAdapter.onDetailClick = {
            it?.let {
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(it)
                findNavController().navigate(action)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (listViewModel.photoList.value.isNullOrEmpty()) {
            listViewModel.searchPhotos("externocleidomastoideo")
        }

        listViewModel.isLoading.observe(viewLifecycleOwner, {
            binding?.photoProgress?.isVisible = it
        })

        listViewModel.photoList.observe(viewLifecycleOwner, { photoList ->
            val photos = photoList.map {
                it.data?.photo
            }
            photoAdapter.setList(photos)
            photoAdapter.notifyItemRangeInserted(photoAdapter.itemCount, photos.size)
        })

        listViewModel.errorRequest.observe(viewLifecycleOwner, {
            if (it) {
                snackBarLong(R.string.error_request)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        const val COLUMNS_NUMBER = 2
    }
}
