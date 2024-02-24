package com.sp.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.app.common.MyUtil;
import com.sp.app.domain.ComfortManage;
import com.sp.app.domain.SessionInfo;
import com.sp.app.service.ComfortManageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/comfort/*")
public class ComfortController {

	@Autowired
	private ComfortManageService comfortManageService;
	
	@Autowired
	private MyUtil myUtil;
	
	@GetMapping("list")
	public String list(@RequestParam(name = "page", defaultValue = "1") int current_page,
					   HttpServletRequest req,
					   HttpSession session,
					   Model model) throws Exception{
		
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		if(info == null) {
			return "redirect:/member/login";
		}
		
		ComfortManage dto = new ComfortManage();
		
		int size = 5;
		int total_page;
		int dataCount;
		
		Map<String, Object> map = new HashMap<String, Object>();
		dto.setMemberIdx(info.getMemberIdx());
		
		dataCount = comfortManageService.dataCount(map);
		total_page = myUtil.pageCount(dataCount, size);
		
		if (total_page < current_page) {
			current_page = total_page;
		}

		int offset = (current_page - 1) * size;
		if(offset < 0) offset = 0;

		map.put("offset", offset);
		map.put("size", size);
		map.put("memberIdx", dto.getMemberIdx());
		
		List<ComfortManage> list = comfortManageService.comfortList(map);
		
		String cp = req.getContextPath();
		String listUrl = cp + "/comfort/list";
		
		String paging = myUtil.paging(current_page, total_page, listUrl);
		
		model.addAttribute("list", list);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("size", size);
		model.addAttribute("total_page", total_page);
		model.addAttribute("page", current_page);
		model.addAttribute("paging", paging);
		
		return "comfort/list";
	}
}
