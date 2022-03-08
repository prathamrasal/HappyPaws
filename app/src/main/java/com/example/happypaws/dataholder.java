package com.example.happypaws;

public class dataholder
{
 String name, contact , address, breed, image ;

 public dataholder(String name, String contact, String address, String breed, String image) {
  this.name = name;
  this.contact = contact;
  this.address = address;
  this.breed = breed;
  this.image = image;
 }

 public String getImage() {
  return image;
 }

 public void setImage(String image) {
  this.image = image;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public String getContact() {
  return contact;
 }

 public void setContact(String contact) {
  this.contact = contact;
 }

 public String getAddress() {
  return address;
 }

 public void setAddress(String address) {
  this.address = address;
 }

 public String getBreed() {
  return breed;
 }

 public void setBreed(String breed) {
  this.breed = breed;
 }
}
