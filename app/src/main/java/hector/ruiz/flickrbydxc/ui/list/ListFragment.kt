package hector.ruiz.flickrbydxc.ui.list

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
    private val loadingObserver = Observer<Boolean> {
        binding?.photoProgress?.isVisible = it
    }
    private val requestObserver = Observer<Boolean> {
        if (it) {
            binding?.photoNoResults?.isVisible = true
            binding?.photoList?.isVisible = false
            snackBarLong(R.string.error_request)
        }
    }

    @Inject
    lateinit var photoAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.searcher, menu)
        val searchItem = menu.findItem(R.id.menu_search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { keyword ->
                        searchItem.collapseActionView()
                        listViewModel.searchPhotos(keyword)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewModel.isLoading.observe(viewLifecycleOwner, {
            binding?.photoProgress?.isVisible = it
        })

        listViewModel.photoList.observe(viewLifecycleOwner, { photoList ->
            val photos = photoList.map {
                it.data?.photo
            }
            if (photos.isNotEmpty()) {
                binding?.photoNoResults?.isVisible = false
                binding?.photoList?.isVisible = true
            } else {
                binding?.photoNoResults?.isVisible = true
                binding?.photoList?.isVisible = false
            }
            photoAdapter.setList(photos)
            photoAdapter.notifyItemRangeInserted(photoAdapter.itemCount, photos.size)
        })

        listViewModel.errorRequest.observe(viewLifecycleOwner, requestObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        listViewModel.isLoading.removeObserver(loadingObserver)
        listViewModel.errorRequest.removeObserver(requestObserver)
    }

    private companion object {
        const val COLUMNS_NUMBER = 3
    }
}
