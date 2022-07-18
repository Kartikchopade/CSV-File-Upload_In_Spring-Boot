package com.example.FileUploadInSpringBoot.controller;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.FileUploadInSpringBoot.entity.Contact;
import com.example.FileUploadInSpringBoot.repository.Repository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;


@RestController
public class Controller
{
	@Autowired
	Repository repo;
	
	
	@PostMapping("/upload")
	public String uploadData(@RequestParam("file") MultipartFile file) throws Exception
	{
		List<Contact> list=new ArrayList<>();
		InputStream inputStream=file.getInputStream();
		CsvParserSettings setting=new CsvParserSettings();
		setting.setHeaderExtractionEnabled(true);
		CsvParser parser=new CsvParser(setting);
		List<Record> parseAllRecords=parser.parseAllRecords(inputStream);
		parseAllRecords.forEach(record ->
		{
			Contact contact=new Contact();			
			contact.setFirstname(record.getString("firstname"));
			contact.setMiddlename(record.getString("middlename"));
			contact.setLastname(record.getString("lastname"));
			list.add(contact);
		});
		repo.saveAll(list);
		return "Upload Successfully";
	}
}


