package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Model.Company;



@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

	@Query(value = "SELECT company_id FROM company", nativeQuery = true)
	Long[] getAllCompanies();
}