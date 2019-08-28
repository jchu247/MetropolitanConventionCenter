package io.mcc.customerservice.model;

import java.util.List;

public class UserData {
    private List<CustomerList> userData;

    public List<CustomerList> getUserData() {
        return userData;
    }

    public void setUserData(List<CustomerList> userData) {
        this.userData = userData;
    }
}
