package com.uknowho.sample.rest.api;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uknowho.sample.rest.abstracts.APIAbstract;
import com.uknowho.sample.rest.advice.APIPathAdvice;
import com.uknowho.sample.rest.exception.APIException;
import com.uknowho.sample.rest.exception.ResponseException;
import com.uknowho.sample.rest.service.CatalogueService;
import com.uknowho.sample.rest.xmlmodel.CatalogueModel;
import com.uknowho.sample.rest.xmlmodel.DeleteCatalogueBody;
import com.uknowho.sample.rest.xmlmodel.GetCatalogueBody;
import com.uknowho.sample.rest.xmlmodel.ListCatalogueBody;
import com.uknowho.sample.rest.xmlmodel.PaginationModel;
import com.uknowho.sample.rest.xmlmodel.ResponseModel;
import com.uknowho.sample.rest.xmlmodel.SaveCatalogueBody;
import com.uknowho.sample.rest.xmlmodel.SortModel;

/**
 * This CatalogueAPI class is define catalogue controller. 
 * 
 * Created date <12-Sep-2016>
 * 
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 */

@RestController
@RequestMapping(APIPathAdvice.DEFAULT_API)
public class CatalogueAPI extends APIAbstract {

	private static final Logger logger = LoggerFactory.getLogger(CatalogueAPI.class);
	
	@Autowired
	private CatalogueService catalogueService;
	
	@RequestMapping(value = APIPathAdvice.CATALOGUE, 
			method = RequestMethod.GET, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CatalogueModel> getCatalogueAPI(
			HttpServletRequest request, 
			@QueryParam("id") final Integer id) throws APIException {	
		CatalogueModel responseModel = null;
		try {
			
			logger.debug("getCatalogueAPI called.");
			
			GetCatalogueBody requestBody = new GetCatalogueBody();
			requestBody.setCatalogueID(id);
			
			responseModel = catalogueService.getCatalogueService(requestBody);
			
		} catch (ResponseException e) {
			logger.error("getCatalogueAPI throws APIException.");
			throw new APIException(
					HttpStatus.BAD_REQUEST, 
					APIPathAdvice.CATALOGUE, 
					e.getResponseModel());
		} 
		return new ResponseEntity<CatalogueModel>(responseModel, HttpStatus.OK); 
	}
	
	@RequestMapping(value = APIPathAdvice.CATALOGUES, 
			method = RequestMethod.GET, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CatalogueModel>> listCatalogueAPI(
			HttpServletRequest request, 
			@QueryParam("parentid") final Integer parentid, 
			@QueryParam("typeid") final Integer typeid, 
			@QueryParam("groupid") final Integer groupid,
			@QueryParam("active") final Boolean active,
			@QueryParam("sort") final String[] sort,
			@QueryParam("page") final Integer page,
			@QueryParam("perpage") final Integer perpage) throws APIException {		
		List<CatalogueModel> responseList = null;
		
		try {
			
			logger.debug("listCatalogueTypeAPI called.");
			
			List<SortModel> sortList = popularSortModelList(sort);
			PaginationModel pagination = popularPaginationModel(page, perpage);
			
			ListCatalogueBody requestBody = new ListCatalogueBody();
			requestBody.setParentID(parentid);
			requestBody.setTypeID(typeid);
			requestBody.setGroupID(groupid);
			requestBody.setActive(active);
			requestBody.getSort().addAll(sortList);
			requestBody.setPagination(pagination);
			
			responseList = catalogueService.listCatalogueService(requestBody);
			
		} catch (ResponseException e) {
			logger.error("listCatalogueTypeAPI throws APIException.");
			throw new APIException(
					HttpStatus.BAD_REQUEST, 
					APIPathAdvice.CATALOGUES, 
					e.getResponseModel());
		} 
		return new ResponseEntity<List<CatalogueModel>>(responseList, HttpStatus.OK); 
	}
	
	@RequestMapping(value = APIPathAdvice.CATALOGUE, 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> saveCatalogueAPI(
			HttpServletRequest request, 
			@RequestBody SaveCatalogueBody requestBody) throws APIException {	
		ResponseModel responseModel = null;
		HttpStatus status = null;
		try {
			
			logger.debug("saveCatalogueAPI called.");
			
			responseModel = catalogueService.saveCatalogueService(requestBody);
			
			status = generateHttpStatus(responseModel);
			
		} catch (ResponseException e) {
			logger.error("saveCatalogueAPI throws APIException.");
			throw new APIException(
					HttpStatus.BAD_REQUEST, 
					APIPathAdvice.CATALOGUE, 
					e.getResponseModel());
		} 
		return new ResponseEntity<ResponseModel>(responseModel, status); 
	}
	
	@RequestMapping(value = APIPathAdvice.CATALOGUE, 
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> updateCatalogueAPI(
			HttpServletRequest request, 
			@RequestBody SaveCatalogueBody requestBody) throws APIException {	
		ResponseModel responseModel = null;
		HttpStatus status = null;
		try {
			
			logger.debug("updateCatalogueAPI called.");
			
			responseModel = catalogueService.updateCatalogueService(requestBody);
			
			status = generateHttpStatus(responseModel);
			
		} catch (ResponseException e) {
			logger.error("updateCatalogueAPI throws APIException.");
			throw new APIException(
					HttpStatus.BAD_REQUEST, 
					APIPathAdvice.CATALOGUE, 
					e.getResponseModel());
		} 
		return new ResponseEntity<ResponseModel>(responseModel, status); 
	}
	
	@RequestMapping(value = APIPathAdvice.CATALOGUE, 
			method = RequestMethod.DELETE, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> deleteCatalogueAPI(
			HttpServletRequest request, 
			@QueryParam("id") final Integer[] id) throws APIException {	
		ResponseModel responseModel = null;
		HttpStatus status = null;
		try {
			
			logger.debug("deleteCatalogueAPI called.");
			
			DeleteCatalogueBody requestBody = new DeleteCatalogueBody();
			if (id != null) {
				requestBody.getCatalogueIDList().addAll(Arrays.asList(id));
			}
			
			responseModel = catalogueService.deleteCatalogueService(requestBody);
			
			status = generateHttpStatus(responseModel);
			
		} catch (ResponseException e) {
			logger.error("deleteCatalogueAPI throws APIException.");
			throw new APIException(
					HttpStatus.BAD_REQUEST, 
					APIPathAdvice.CATALOGUE, 
					e.getResponseModel());
		} 
		return new ResponseEntity<ResponseModel>(responseModel, status); 
	}
}
