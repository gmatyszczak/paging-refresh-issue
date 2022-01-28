package pl.gmat.pagingrefreshissue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class MainViewModel : ViewModel() {

    private val pager = Pager(PagingConfig(pageSize = 30)) { ItemPagingSource() }
    val itemsFlow = pager.flow.cachedIn(viewModelScope)
}
