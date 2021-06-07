package dev.mr_ashok.vehicle_profile.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dev.mr_ashok.vehicle_profile.bindErrorViews
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

    protected fun setListData(dataList: List<String>) {
        adapter.submitList(dataList)
        binding?.run {
            if (dataList.isEmpty()) {
                recyclerView.visibility = View.GONE
                emptySection.root.visibility = View.VISIBLE
                emptySection.emptySubtitle.visibility = View.GONE
            } else {
                recyclerView.visibility = View.VISIBLE
                emptySection.root.visibility = View.GONE
            }


            errorSection.root.visibility = View.GONE
            loadingSection.root.visibility = View.GONE

        }
    }

    protected fun setupErrorView(data: ErrorData) {
        binding?.run {
            recyclerView.visibility = View.GONE
            errorSection.root.visibility = View.VISIBLE
            loadingSection.root.visibility = View.GONE
            emptySection.root.visibility = View.GONE
            bindErrorViews(errorSection, data)
        }
    }

    protected fun setupLoadingView(@StringRes loadingTextRes: Int? = null) {
        binding?.run {
            recyclerView.visibility = View.GONE
            errorSection.root.visibility = View.GONE
            loadingSection.root.visibility = View.VISIBLE
            emptySection.root.visibility = View.GONE
            if (loadingTextRes != null) {
                loadingSection.loadingLabel.setText(loadingTextRes)
            }
        }
    }

    protected abstract fun onDataSelected(data: String)

}