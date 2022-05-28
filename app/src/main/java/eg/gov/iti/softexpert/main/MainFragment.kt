package eg.gov.iti.softexpert.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.google.android.material.snackbar.Snackbar
import eg.gov.iti.softexpert.R
import eg.gov.iti.softexpert.business.entities.DataState
import eg.gov.iti.softexpert.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!
    lateinit var carAdapter: CarAdapter
    lateinit var carViewModel: CarViewModel
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
        carAdapter = CarAdapter()
        carViewModel = ViewModelProvider(this)[CarViewModel::class.java]
        binding.carsRV.adapter = carAdapter
        binding.swipeRefresh.setOnRefreshListener {
            carViewModel.getCars()
            binding.swipeRefresh.isRefreshing = false
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}