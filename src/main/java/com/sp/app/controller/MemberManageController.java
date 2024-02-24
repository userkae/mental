package com.sp.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sp.app.common.MyUtil;
import com.sp.app.domain.Member;
import com.sp.app.service.MemberManageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/memberManage/*")
public class MemberManageController {
	
	@Autowired
	private MemberManageService service;
	
	@Autowired
	private MyUtil myUtil;

	@GetMapping("list")
	public String memberManage(@RequestParam(name="page", defaultValue = "1") int current_page,
								HttpServletRequest req,
								HttpSession session,
								Model model) throws Exception{
		
		Member dto = new Member();
		List<Member> list = new ArrayList<>();
		
		int size = 10;
		int total_page;
		int dataCount;
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int offset = (current_page - 1) * size;
		if(offset < 0) offset = 0;
		
		dataCount = service.dataCount(map);
		
		total_page = myUtil.pageCount(dataCount, size);
		if (total_page < current_page) {
			current_page = total_page;
		}

		map.put("offset", offset);
		map.put("size", size);
		
		list = service.memberManageList(map);
		
		String cp = req.getContextPath();
		//String query = "";
		String listUrl = cp + "/memberManage/list";
		String articleUrl = cp + "/memberManage/article?page="+current_page;
		
		String paging = myUtil.paging(current_page, total_page, listUrl);
		
		model.addAttribute("list", list);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("size", size);
		model.addAttribute("total_page", total_page);
		model.addAttribute("articleUrl", articleUrl);
		model.addAttribute("page", current_page);
		model.addAttribute("paging", paging);
		model.addAttribute("dto", dto);
		
		return "memberManage/list";
	}
	
	@GetMapping("update/{memberIdx}")
	public String updateForm(@PathVariable("memberIdx") long memberIdx,
	                         @RequestParam(name="page") String page,
	                         HttpSession session,
	                         Model model) {
	    
	    try {
	        List<Member> stateCodeList = service.stateCodeList();
	        Member dto = service.updateUserInfo(memberIdx);
	        
	        if(dto != null) { // dto가 null이 아닌 경우에만 모델에 추가
	            model.addAttribute("stateCodeList", stateCodeList);
	            model.addAttribute("dto", dto);
	            model.addAttribute("page", page);
	        } else {
	            // dto가 null인 경우 처리할 로직 추가
	        }
	    } catch (Exception e) {
	        // 예외 처리
	    }
	    
	    return "memberManage/update";
	}

	
	@PostMapping("update")
	@ResponseBody
	public Map<String, Object> updateSubmit(@RequestParam(name="memberIdx") long memberIdx
											, @RequestParam(name="membership") int membership
											, @RequestParam(name="stateCode") int stateCode
											, @RequestParam(name="page") String page
											, Member dto
											, HttpSession session) throws Exception{
		
		String state = "success";
		try {
			Map<String, Object> member = new HashMap<>();
			member.put("memberIdx", memberIdx);
			member.put("membership", membership);
			service.updateMember(member);
			
			Map<String, Object> mManage = new HashMap<>();
			mManage.put("memberIdx", memberIdx);
			mManage.put("stateCode", stateCode);
			service.updatemManage(mManage);
			
			if(dto.getStateCode() == 0) {
				service.updateEnabled1(memberIdx);
			}else {
				service.updateEnabled0(memberIdx);
			}
			
		} catch (Exception e) {
			state="false";
		}
		
		Map<String, Object> model = new HashMap<>();
		model.put("state", state);
		model.put("page", page);
		
		return model;
	}

}
