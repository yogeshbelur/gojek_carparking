package com.gojek.parkinglot;

/**
 * @author Yogesh
 *
 */
public interface ParkingLotQueryProcessor {
	/**
	 * Passes the all query processing parameter to be used for
	 * {@link ParkingLotQueryService}
	 * 
	 * @param queryParam
	 */
	public void process(String[] queryParam);
}
