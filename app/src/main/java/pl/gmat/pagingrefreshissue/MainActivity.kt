package pl.gmat.pagingrefreshissue

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()
    private val pagingAdapter = ItemAdapter()
    private val swipeRefreshLayout: SwipeRefreshLayout by lazy { findViewById(R.id.swipeRefreshLayout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAdapter()
        setupRecyclerView()
        setupSwipeRefreshLayout()
        observePagingData()
    }

    private fun setupAdapter() {
        pagingAdapter.addLoadStateListener {
            val state = it.source.refresh
            Log.d("MainActivity", "state = $state")
            swipeRefreshLayout.isRefreshing = state is LoadState.Loading
        }
    }

    private fun setupRecyclerView() {
        findViewById<RecyclerView>(R.id.recyclerView).adapter = pagingAdapter
    }

    private fun observePagingData() {
        lifecycleScope.launch {
            viewModel.itemsFlow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(pagingAdapter::refresh)
    }
}
