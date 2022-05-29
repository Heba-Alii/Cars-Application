package eg.gov.iti.softexpert.main.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class Pagination(
    private var pageNumber: Int,
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount

        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        if (totalItemCount != 0 && layoutManager.findLastCompletelyVisibleItemPosition() == totalItemCount - 1) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                pageNumber++
                loadMoreItems(pageNumber)
            }
        }
    }

    protected abstract fun loadMoreItems(pageNumber: Int)
}