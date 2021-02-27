package com.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Model.Company;
import com.Repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	public long addCompany(Company company) {
		return companyRepository.save(company).getCompany_id();
		// TODO Auto-generated method stub
		
	}

	public void updateCompany(Company company) {
		// TODO Auto-generated method stub
		
		companyRepository.save(company);
	}

	public Optional<Company> getCompanyById(long company_id) {
		// TODO Auto-generated method stub
		return companyRepository.findById(company_id);
	}

	public void deleteById(long company_id) {
		// TODO Auto-generated method stub
		companyRepository.deleteById(company_id);
	}

	public Long[] getAllCompanies() {
		// TODO Auto-generated method stub
		return companyRepository.getAllCompanies();
	}
}