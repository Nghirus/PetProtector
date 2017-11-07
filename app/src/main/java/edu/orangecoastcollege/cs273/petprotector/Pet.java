package edu.orangecoastcollege.cs273.petprotector;

/**
 * Created by Nghir on 11/7/2017.
 */

/**
 * A pet class that has a name, id, image, details, and phone number.
 */
public class Pet {

    private String mDetails;
    private int mID;
    private String mImageURI;
    private String mName;
    private String mPhone;

    /**
     * Creates a new pet
     * @param ID auto assigned id
     * @param name the pet's name
     * @param details details about the pet
     * @param phone the contact phone number
     * @param imageURI the string of the image uri
     */
    public Pet(int ID, String name, String details, String phone, String imageURI) {
        mDetails = details;
        mID = ID;
        mImageURI = imageURI;
        mName = name;
        mPhone = phone;
    }

    /**
     *Creates a new pet
     * @param name the pet's name
     * @param details details about the pet
     * @param phone the contact phone number
     * @param imageURI the string of the image uri
     */
    public Pet(String name, String details, String phone, String imageURI) {
        mDetails = details;
        mImageURI = imageURI;
        mName = name;
        mPhone = phone;
    }

    /**
     * Returns the detail of the pet
     * @return the detail of the pet
     */
    public String getDetails() {

        return mDetails;
    }

    /**
     * Sets the details of the pet
     * @param details the details of the pet
     */
    public void setDetails(String details) {
        mDetails = details;
    }

    /**
     * returns the imageUri
     * @return the imageUri
     */
    public String getImageURI() {
        return mImageURI;
    }

    /**
     * Sets the image uri
     * @param imageURI the image uri
     */
    public void setImageURI(String imageURI) {
        mImageURI = imageURI;
    }

    /**
     * Returns the name of the pet
     * @return the name of the pet
     */
    public String getName() {
        return mName;
    }

    /**
     * Set the name of the pet
     * @param name
     */
    public void setName(String name) {
        mName = name;
    }

    /**
     * Returns the contact phone number
     * @return the contact phone number
     */
    public String getPhone() {
        return mPhone;
    }

    /**
     * Set the contact phone number
     * @param phone the contact phone number
     */
    public void setPhone(String phone) {
        mPhone = phone;
    }

    /**
     * Parse the object into a string format
     * @return string with all the variables in string format
     */
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
