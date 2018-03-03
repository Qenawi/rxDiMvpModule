package users.app.dummyx.qenawi.presentation.login;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.RelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import users.app.dummyx.qenawi.App;
import users.app.dummyx.qenawi.R;
import users.app.dummyx.qenawi.data.remote.model.LoginResponse;
import users.app.dummyx.qenawi.presentation.base.BaseActivity;
import users.app.dummyx.qenawi.usecase.loginUC.LoginUsecase;
import users.app.dummyx.qenawi.utils.typefaceitems;
import users.app.dummyx.qenawi.utils.typefaceitemstext;


public class LoginActivity extends BaseActivity implements Logincontract.LoginView {
    @Inject
    LoginUsecase loginusecase;
    loginPresenter presenter;

    @BindView(R.id.my_awesome_rl)
    RelativeLayout myrl;
    @BindView(R.id.input_phone)
    typefaceitems in_phone;
    @BindView(R.id.input_pass2)
    typefaceitems in_pass;
    @BindView(R.id.loginbtn)
    typefaceitemstext login_btn;

    @Override
    protected void initializeDagger()
    {
        ((App) getApplicationContext()).getNetComponent().inject(LoginActivity.this);
    }
    @Override
    protected void initializePresenter()
    {
        presenter = new loginPresenter(AndroidSchedulers.mainThread(), Schedulers.io(), loginusecase);
        presenter.attachView(this);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void showError(String msg) {
        Snackbar.make(myrl, msg, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void showLoading() {
        Snackbar.make(myrl, "loadding staret", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Snackbar.make(myrl, "Hide loading", Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void showLoginSuccess(LoginResponse login)
    {
        Snackbar.make(myrl, login.getMessage(), Snackbar.LENGTH_SHORT).show();
       // startActivity(new Intent(this.getBaseContext(), TalbatActivity.class));
    }

    @Override
    public void navigateToRestScreen()
    {

    }

    @OnClick(R.id.loginbtn)
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.loginbtn:
                presenter.Login(in_phone.getText().toString(), in_pass.getText().toString());
                break;
        }
    }

    @Override
    public void Kill_ME(String flag) {

    }

    @Override
    public void Refresh_ME(String flag) {

    }

    @Override
    public void Call_AnotherFrag(String Flag) {

    }
}
