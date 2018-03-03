package users.app.dummyx.qenawi.presentation.base;


public interface MvpPresenter<V extends MvpView>
{
    void attachView(V view);
    void detechView();
}
