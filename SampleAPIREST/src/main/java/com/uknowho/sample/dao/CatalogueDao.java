package com.uknowho.sample.dao;

import java.util.List;

import com.uknowho.sample.entity.Catalogue;
import com.uknowho.sample.exception.ServiceException;
import com.uknowho.sample.xmlmodel.PaginationModel;
import com.uknowho.sample.xmlmodel.SortModel;

/**
 * This interface to define CatalogueDao specific Data Catalogue operation methods.
 * 
 * Created date <08-Sep-2016>
 * 	
 * @version 1.0.1
 * @since 1.0.1
 * 
 * @author <a href="mailto:tony87c0086@hotmail.com"> Xiaoyu Zhang (Tony) </a>
 * 
 * @param <Catalogue> a Catalogue Entity object 
 * @param <Integer> the primary key of the Catalogue
 *
 */

public interface CatalogueDao extends GenericDao<Catalogue, Integer> {

	/**
     * List Catalogue record by script type
     * @param parentID
     * @param typeID
     * @param groupID
     * @param active
     * @return Catalogue object list
     */
	List<Catalogue> list(
			Integer parentID, 
			Integer typeID, 
			Integer groupID, 
			Boolean active) throws ServiceException;
	
	/**
     * List Catalogue record by script type
     * @param parentID
     * @param typeID
     * @param groupID
     * @param active
     * @param sortList
     * @param pagination
     * @return Catalogue object list
     */
	List<Catalogue> list(
			Integer parentID,
			Integer typeID, 
			Integer groupID, 
			Boolean active, 
			List<SortModel> sortList, 
			PaginationModel pagination) throws ServiceException;
}
