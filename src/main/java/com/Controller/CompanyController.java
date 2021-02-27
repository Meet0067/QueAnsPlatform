package com.Controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Model.Company;
import com.Service.CompanyService;

@RestController
public class CompanyController {
	@Autowired
	CompanyService companyService;

	@PostMapping("/company")
	public ResponseEntity<?> addCompany(@RequestParam String company_name){		
		try {
			Company company = new Company();
			company.setCompany_name(company_name);
			long cid = companyService.addCompany(company);
			return new ResponseEntity<>("company Created with ID "+cid, HttpStatus.ACCEPTED);			
		}catch (Exception e) {
			return new ResponseEntity<>("company Not Created", HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/company")
	public ResponseEntity<?> updatecompany(@RequestParam String company_name,@RequestParam long company_id){		
		try {
			Company company = new Company();
			company.setCompany_name(company_name);
			company.setCompany_id(company_id);
			Optional<Company> company_found = companyService.getCompanyById(company_id);
			if (company_found.isPresent()) {
				companyService.updateCompany(company);
				return new ResponseEntity<>("company Updated", HttpStatus.ACCEPTED);		
			}else {
				throw new Exception();
			}
				
		}catch (Exception e) {
			return new ResponseEntity<>("company Not Updated Due to unavailable company_id", HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/company")
	public ResponseEntity<?> getcompany(@RequestParam long company_id){		
		try {
			Optional<Company> company = companyService.getCompanyById(company_id);
			if(company.isPresent()) {
				return new ResponseEntity<>(company.get(), HttpStatus.ACCEPTED);
			}else {
				throw new Exception();
			}
						
		}catch (Exception e) {
			return new ResponseEntity<>("company Not Found", HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/company")
	public ResponseEntity<?> deleteCompany(@RequestParam long company_id){		
		try {
			companyService.deleteById(company_id);
			 return new ResponseEntity<>("company Deleted", HttpStatus.ACCEPTED);			
		}catch (Exception e) {
			return new ResponseEntity<>("company Not Deleted Due to unavailable Company_id", HttpStatus.BAD_REQUEST);
		}
	}
	
}