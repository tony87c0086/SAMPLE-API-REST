package com.uknowho.sample.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.uknowho.sample.rest.api.CatalogueAPI;
import com.uknowho.sample.rest.xmlmodel.CatalogueModel;

/**
 * This APITest class provides test cases for API cases.
 * 
 * Created date <21-Jul-2016>
 * 	
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

public class APITest extends TestCasePreload {

	@Autowired
	private CatalogueAPI catalogueAPI;
	
	@Test
	public void testListCatalogueAPI() {
		
		HttpServletRequest request = null;
		Integer parentid = null;
		Integer typeid = null;
		Integer groupid = null;
		Boolean active = null;
		String[] sort = null;
		Integer page = null;
		Integer perpage = null;
		
		ResponseEntity<List<CatalogueModel>> response = catalogueAPI.listCatalogueAPI(
				request, 
				parentid, 
				typeid, 
				groupid, 
				active, 
				sort, 
				page, 
				perpage);
		
		List<CatalogueModel> modelList = response.getBody();
		
		assertNotNull(modelList);
	}
	
}
