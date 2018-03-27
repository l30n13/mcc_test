package com.mcc.l30n.mcc.model;

/**
 * Created by leon on 27/3/18.
 */

public class Profile {
    private String name, email, phoneNumber, image;
    private int profileId, age;

    public Profile(String name, String email, String phoneNumber, String image, int age, int profileId) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.age = age;
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getImage() {
        return image;
    }

    public int getAge() {
        return age;
    }

    public int getProfileId() {
        return profileId;
    }
}
