package nz.co.jonker.skyrewards.data.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonker on 6/09/15.
 */
public class Account implements Parcelable {
    long accountNumber;
    String accountHolderName;

    List<Subscription> subscriptionList = new ArrayList<>();

    public Account() {
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    protected Account(Parcel in) {
        accountNumber = in.readLong();
        accountHolderName = in.readString();
        if (in.readByte() == 0x01) {
            subscriptionList = new ArrayList<Subscription>();
            in.readList(subscriptionList, Subscription.class.getClassLoader());
        } else {
            subscriptionList = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(accountNumber);
        dest.writeString(accountHolderName);
        if (subscriptionList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(subscriptionList);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Account> CREATOR = new Parcelable.Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };
}