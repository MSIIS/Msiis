package com.web.service;


import org.com.soupe.web.Roll;

import java.util.List;

public interface SsqService {

	/**
	 * 双色球杀号方法
	 * @param reds
	 * @param blues
	 * @param num
	 * @param sum1
	 * @param sum2
	 * @param sort
	 * @return
	 */
	List<Roll> getNumbersOfKill(String[] reds, String[] blues, int num, int sum1, int sum2, String sort);
	
	/**
	 * 双色球精选方法
	 * @param reds
	 * @param blues
	 * @param num
	 * @param sum1
	 * @param sum2
	 * @param sort
	 * @return
	 */
	List<Roll> getNumbersOfChoose(String[] reds, String[] blues, int num, int sum1, int sum2, String sort);
	
	/**
	 * 双色球查询
	 * @param sort
	 * @return
	 */
	List<Roll> findNums(String sort, String dataTye);

	/**
	 * 批量删除
	 * @param ids
	 */
	void deleteNums(String ids);
}
