package dev.mr_ashok.vehicle_profile.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dev.mr_ashok.vehicle_profile.databinding.BasicListFragmentBinding

abstract class BasicListFragment : BaseFragment<BasicListFragmentBinding>() {

    private lateinit var adapter: BasicListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BasicListFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = BasicListAdapter(this::onDataSelected)
        binding?.run {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
    }

    protected fun setListData(dataList: List<String>) = adapter.submitList(dataList)

    protected fun setupErrorView(data: ErrorData) {
        // TODO: Implement this method
    }

    protected fun setupLoadingView() {
        // TODO: Implement this method
    }

    protected abstract fun onDataSelected(data: String)

}