package com.example.happypaws;

public class MainModel {
    String address,name,breed,image,contact,petimage;

    MainModel(){

    }
    public MainModel(String address, String name, String breed, String image, String contact,String petimage) {
        this.address = address;
        this.name = name;
        this.breed = breed;
        this.image = image;
        this.contact = contact;
        this.petimage=petimage;
    }

    public String getPetimage() {
        return petimage;
    }

    public void setPetimage(String petimage) {
        this.petimage = petimage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
