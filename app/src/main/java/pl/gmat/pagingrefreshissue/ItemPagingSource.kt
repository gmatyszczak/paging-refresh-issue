package pl.gmat.pagingrefreshissue

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay

class ItemPagingSource : PagingSource<Int, String>() {

    override fun getRefreshKey(state: PagingState<Int, String>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        val page = params.key ?: 0
        val response = generateResponse(page)
        return LoadResult.Page(
            data = response,
            prevKey = null,
            nextKey = page + 1
        )
    }

    private suspend fun generateResponse(page: Int): List<String> {
        delay(1000)
        return (page * 30..page * 30 + 30).map { "item $it" }
    }
}