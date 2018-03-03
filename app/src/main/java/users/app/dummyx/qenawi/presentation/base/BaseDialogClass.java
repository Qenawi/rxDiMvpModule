package users.app.dummyx.qenawi.presentation.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Andorid-win on 2/27/2018.
 */

public abstract class BaseDialogClass extends Dialog
{
    private Unbinder unbinder;
    protected abstract void initializeDagger();
    protected abstract void initializePresenter();
    public abstract int getLayoutId();
    public BaseDialogClass(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        initializeDagger();
        initializePresenter();
    }

}
