package users.app.dummyx.qenawi.presentation.base;


import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Andorid-win on 9/23/2017.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(int position);


}
