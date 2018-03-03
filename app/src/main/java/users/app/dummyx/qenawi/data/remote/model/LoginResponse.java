package users.app.dummyx.qenawi.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Andorid-win on 2/6/2018.
 */

public class LoginResponse implements Parcelable
{
        /**
         * status : done
         * message : تم تسجيل دخولك بنجاح.
         * userid : 2
         * enable : 1
         */
        private String status;
        private String message;
        private String userid;
        private String enable;

    protected LoginResponse(Parcel in) {
            status = in.readString();
            message = in.readString();
            userid = in.readString();
            enable = in.readString();
        }

        public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
            @Override
            public LoginResponse createFromParcel(Parcel in) {
                return new LoginResponse(in);
            }

            @Override
            public LoginResponse[] newArray(int size) {
                return new LoginResponse[size];
            }
        };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(message);
        dest.writeString(userid);
        dest.writeString(enable);
    }
}