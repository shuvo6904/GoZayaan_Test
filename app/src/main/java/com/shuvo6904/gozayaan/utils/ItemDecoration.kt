import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) // Item position
        val column = position % spanCount // Item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount // Spacing on the left edge
            outRect.right = (column + 1) * spacing / spanCount // Spacing on the right edge

            if (position < spanCount) { // Top edge
                outRect.top = spacing
            }
            outRect.bottom = spacing // Item bottom
        } else {
            outRect.left = column * spacing / spanCount // Spacing on the left edge
            outRect.right = spacing - (column + 1) * spacing / spanCount // Spacing on the right edge
            if (position >= spanCount) {
                outRect.top = spacing // Item top
            }
        }
    }
}
