package com.example.ravitejam.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

@SerializedName("pinNumber")
@Expose
private String pinNumber;
@SerializedName("status")
@Expose
private String status;

public String getPinNumber() {
return pinNumber;
}

public void setPinNumber(String pinNumber) {
this.pinNumber = pinNumber;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

}