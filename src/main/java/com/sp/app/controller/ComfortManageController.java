package com.sp.app.controller;

import java.io.File;
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

import com.sp.app.common.MyUtil;
import com.sp.app.domain.ComfortManage;
import com.sp.app.domain.SessionInfo;
import com.sp.app.service.ComfortManageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/comfortManage/*")
public class ComfortManageController {
	
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
		//String query = "";
		String listUrl = cp + "/comfortManage/list";
		String articleUrl = cp + "/comfortManage/article?page=" + current_page;
		
		String paging = myUtil.paging(current_page, total_page, listUrl);
		
		model.addAttribute("list", list);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("size", size);
		model.addAttribute("total_page", total_page);
		model.addAttribute("articleUrl", articleUrl);
		model.addAttribute("page", current_page);
		model.addAttribute("paging", paging);
		
		return "comfortManage/list";
	}

	@GetMapping("write")
	public String writeForm(HttpSession session,
							Model model) throws Exception{
		
		model.addAttribute("mode", "write");
		model.addAttribute("dto", new ComfortManage());
		
		return "comfortManage/write";
	}
	
	@PostMapping("write")
	public String writeSubmit(ComfortManage dto, HttpSession session) throws Exception{
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		String root = session.getServletContext().getRealPath("/");
		String pathname = root + "uploads" + File.separator + "comfortManage";
		
		try {
			dto.setMemberIdx(info.getMemberIdx());
			comfortManageService.insertcomfort(dto, pathname);
		} catch (Exception e) {

		}
		
		return "redirect:/comfortManage/list";
	}
	
	@GetMapping("article/{comfortNum}")
	public String article(@PathVariable(name="comfortNum") long comfortNum,
						  @RequestParam(name="page") String page,
						  Model model) throws Exception{
		
		ComfortManage dto = comfortManageService.articleComfort(comfortNum);
		if(dto == null) {
			return "redirect:/comfortManage/list?"+page;
		}
		
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
		
		return "comfortManage/article";
	}
	
	@GetMapping("update")
	public String updateForm(@RequestParam(name="comfortNum") long comfortNum,
						 	 @RequestParam(name="page") String page,
						 	 HttpSession session,
						 	 Model model) throws Exception{
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		ComfortManage dto = comfortManageService.articleComfort(comfortNum);
		if(dto == null || info.getMemberIdx() != dto.getMemberIdx()) {
			return "redirect:/comfortManage/list?page="+page;
		}
		
		model.addAttribute("mode", "update");
		model.addAttribute("page", page);
		model.addAttribute("dto", dto);
		
		return "comfortManage/write";
	}
	
	@PostMapping("update")
	public String updateSubmit(ComfortManage dto,
							   @RequestParam(name="page") String page,
							   HttpSession session) throws Exception{
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		String root = session.getServletContext().getRealPath("/");
		String pathname = root + "uploads" + File.separator + "comfortManage";
		
		try {
			dto.setMemberIdx(info.getMemberIdx());
			comfortManageService.updateComfort(dto, pathname);
			
		} catch (Exception e) {

		}
		
		return "redirect:/comfortManage/list?page="+page;
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam(name="comfortNum") long comfortNum,
						 @RequestParam(name="page") String page,
						 @RequestParam(name="fileName") String fileName,
						 HttpSession session) throws Exception{
		
		String root = session.getServletContext().getRealPath("/");
		String pathname = root + "uploads" + File.separator + "comfortManage";
		
		try {
			comfortManageService.deleteComfort(comfortNum, pathname);
		} catch (Exception e) {

		}
		
		return "redirect:/comfortManage/list?page="+page;
	}


}
