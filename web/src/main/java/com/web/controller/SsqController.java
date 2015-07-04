package com.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.web.service.SsqService;
import com.web.service.impl.ServiceManager;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.web.soupe.dto.SoupeWebModel;
import com.web.soupe.web.Roll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = SsqController.PATH)
public class SsqController extends AbstractBaseController {
	protected static final String PATH ="foundation/ssq";
	private Logger logger = Logger.getLogger(SsqController.class);

	@RequestMapping(value = "/kill/getNums", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public SoupeWebModel submit(
			@RequestParam(value = "red", required = false) String red,
			@RequestParam(value = "blue", required = false) String blue,
			@RequestParam(value = "num", required = false) String num,
			@RequestParam(value = "sum", required = false) String sum,
			@RequestParam(value = "sort", required = false) String sort,
			HttpServletRequest request) {
		SoupeWebModel soupewebModel = new SoupeWebModel();
		try {
		    String[] reds =null ;
		    String[] blues =null;
		    String[] sums =new String[1] ;
		    int number =0;
		    if(StringUtils.isEmpty(sort)){
		      sort="-1";	
		    }
			if(!StringUtils.isEmpty(red)){
				reds =StringUtils.split(red, ",");
			}
			if(!StringUtils.isEmpty(blue)){
				blues =StringUtils.split(blue,",");
			}
           
			if(!StringUtils.isEmpty(num)){
				number =Integer.parseInt(num);
			}
			if(!StringUtils.isEmpty(sum)){
				sums =StringUtils.split(sum, "-");
			}
			int sum1 =21;
			int sum2 =200;
			if(sum!=null&&sums.length==2){
			   	sum1=Integer.parseInt(sums[0]);
				sum2=Integer.parseInt(sums[1]);
			}
				
		    
			List<Roll> rolls =this.serviceManager.getSsqService().getNumbersOfKill(reds, blues, number, sum1, sum2, sort);
			soupewebModel.setData(rolls);
			soupewebModel.setSuccess(true);
		} catch (Exception ex) {
			logger.error("验证登录信息出错，" + ex.getMessage(), ex);
			soupewebModel.setMessage(ex.getMessage());
		
		}
		return soupewebModel;
	}
	
	@RequestMapping(value="/kill/findNums",method=RequestMethod.GET)
	@ResponseBody
	public SoupeWebModel findNums(@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "type", required = false) String type,
			HttpServletRequest request){
		SoupeWebModel soupewebModel = new SoupeWebModel();
		try{
			if(!StringUtils.isEmpty(sort)){
				List<Roll> rolls =this.serviceManager.getSsqService().findNums(sort, type);
				soupewebModel.setData(rolls);
			}
			soupewebModel.setSuccess(true);
		}catch(Exception ex){
			logger.error("验证登录信息出错，" + ex.getMessage(), ex);
			soupewebModel.setMessage(ex.getMessage());
		}
	     return soupewebModel;
		
	}
	
	
	@RequestMapping(value="/kill/deleteNums",method=RequestMethod.POST)
	@ResponseBody
	public SoupeWebModel delete(@RequestParam(value = "ids", required = false) String ids,
			HttpServletRequest request){
		SoupeWebModel soupewebModel = new SoupeWebModel();
		try{
			if(!StringUtils.isEmpty(ids)){
                this.serviceManager.getSsqService().deleteNums(ids);
			}
			soupewebModel.setSuccess(true);
		}catch(Exception ex){
			logger.error("验证登录信息出错，" + ex.getMessage(), ex);
			soupewebModel.setMessage(ex.getMessage());
		}
	     return soupewebModel;
		
	}
	
	
	
	@RequestMapping(value = "/choose/getNums", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public SoupeWebModel submitOfChoose(
			@RequestParam(value = "red", required = false) String red,
			@RequestParam(value = "blue", required = false) String blue,
			@RequestParam(value = "num", required = false) String num,
			@RequestParam(value = "sum", required = false) String sum,
			@RequestParam(value = "sort", required = false) String sort,
			HttpServletRequest request) {
		SoupeWebModel soupewebModel = new SoupeWebModel();
		try {
		    String[] reds =null ;
		    String[] blues =null;
		    String[] sums =new String[1] ;
		    int number =0;
		    if(StringUtils.isEmpty(sort)){
		      sort="-1";	
		    }
			if(!StringUtils.isEmpty(red)){
				reds =StringUtils.split(red, ",");
			}
			if(!StringUtils.isEmpty(blue)){
				blues =StringUtils.split(blue,",");
			}
           
			if(!StringUtils.isEmpty(num)){
				number =Integer.parseInt(num);
			}
			if(!StringUtils.isEmpty(sum)){
				sums =StringUtils.split(sum, "-");
			}
			int sum1 =21;
			int sum2 =200;
			if(sum!=null&&sums.length==2){
			   	sum1=Integer.parseInt(sums[0]);
				sum2=Integer.parseInt(sums[1]);
			}
				
		    
			List<Roll> rolls =this.serviceManager.getSsqService().getNumbersOfChoose(reds, blues, number, sum1, sum2, sort);
			soupewebModel.setData(rolls);
			soupewebModel.setSuccess(true);
		} catch (Exception ex) {
			logger.error("删除数据失败，" + ex.getMessage(), ex);
			soupewebModel.setMessage(ex.getMessage());
		
		}
		return soupewebModel;
	}
	
	
	@RequestMapping(value="/choose/findNums",method=RequestMethod.GET)
	@ResponseBody
	public SoupeWebModel findNumsOfChoose(@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "type", required = false) String type,
			HttpServletRequest request){
		SoupeWebModel soupewebModel = new SoupeWebModel();
		try{
			if(!StringUtils.isEmpty(sort)){
				List<Roll> rolls =this.serviceManager.getSsqService().findNums(sort, type);
				soupewebModel.setData(rolls);
			}
			soupewebModel.setSuccess(true);
		}catch(Exception ex){
			logger.error("验证登录信息出错，" + ex.getMessage(), ex);
			soupewebModel.setMessage(ex.getMessage());
		}
	     return soupewebModel;
		
	}
	
	
	@RequestMapping(value="/choose/deleteNums",method=RequestMethod.POST)
	@ResponseBody
	public SoupeWebModel deleteOfChoose(@RequestParam(value = "ids", required = false) String ids,
			HttpServletRequest request){
		SoupeWebModel soupewebModel = new SoupeWebModel();
		try{
			if(!StringUtils.isEmpty(ids)){
                this.serviceManager.getSsqService().deleteNums(ids);
			}
			soupewebModel.setSuccess(true);
		}catch(Exception ex){
			logger.error("删除数据失败 ，" + ex.getMessage(), ex);
			soupewebModel.setMessage(ex.getMessage());
		}
	     return soupewebModel;
		
	}
}
