package eg.gov.iti.softexpert.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import eg.gov.iti.softexpert.R
import eg.gov.iti.softexpert.business.entities.DataState
import eg.gov.iti.softexpert.databinding.FragmentMainBinding
import eg.gov.iti.softexpert.main.adapter.CarAdapter
import eg.gov.iti.softexpert.main.utils.Pagination
import eg.gov.iti.softexpert.main.viewModel.CarViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment @Inject constructor() : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    @Inject
    lateinit var carAdapter: CarAdapter

    private val carViewModel: CarViewModel by viewModels()

    private var pagination: Pagination? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        subscribeOnObservers()

    }

    private fun subscribeOnObservers() {
        carViewModel.carDataState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    binding.isLoading = false
                    carAdapter.submitList(it.data.data)
                    pagination?.isLastPage(it.data.data.size-1)
                }
                is DataState.Failure -> {
                    Snackbar.make(binding.root, "Network Error", Snackbar.LENGTH_SHORT).show()
                    binding.isLoading = false

                }
                is DataState.Loading -> {
                    binding.isLoading = true
                }
            }
        }

    }

    private fun initUi() {
        startListen()
        binding.carsRV.adapter = carAdapter
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
            pagination?.reload()
        }
    }

    private fun startListen() {
        if (binding.carsRV.layoutManager is LinearLayoutManager) {
            pagination = object :
                Pagination(
                    1,
                    binding.carsRV.layoutManager as LinearLayoutManager
                ) {
                override fun loadMoreItems(pageNumber: Int) {
                    carViewModel.getCars(pageNumber)
                }
            }
            pagination?.let { binding.carsRV.addOnScrollListener(it) }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}