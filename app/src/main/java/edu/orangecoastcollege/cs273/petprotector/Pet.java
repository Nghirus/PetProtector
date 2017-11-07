package edu.orangecoastcollege.cs273.petprotector;

/**
 * Created by Nghir on 11/7/2017.
 */

public class Pet {

    private String mDetails;
    private int mID;
    private String mImageURI;
    private String mName;
    private String mPhone;

    public Pet(int ID, String name, String details, String phone, String imageURI) {
        mDetails = details;
        mID = ID;
        mImageURI = imageURI;
        mName = name;
        mPhone = phone;
    }

    public Pet(String name, String details, String phone, String imageURI) {
        mDetails = details;
        mImageURI = imageURI;
        mName = name;
        mPhone = phone;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getDetails() {

        return mDetails;
    }

    public void setDetails(String details) {
        mDetails = details;
    }

    public String getImageURI() {
        return mImageURI;
    }

    public void setImageURI(String imageURI) {
        mImageURI = imageURI;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "mDetails='" + mDetails + '\'' +
                ", mID=" + mID +
                ", mImageURI='" + mImageURI + '\'' +
                ", mName='" + mName + '\'' +
                ", mPhone='" + mPhone + '\'' +
                '}';
    }
}
