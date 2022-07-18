package com.example.FileUploadInSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FileUploadInSpringBoot.entity.Contact;

public interface Repository extends JpaRepository<Contact, Integer>{

}
