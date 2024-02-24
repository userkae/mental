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
import com.sp.app.domain.Diary;
import com.sp.app.domain.SessionInfo;
import com.sp.app.service.DiaryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/diary/*")
public class DairyController {

	@Autowired
	private DiaryService diaryService;
	
	@Autowired
	private MyUtil myUtil;
	
	@GetMapping("list")
	public String list(@RequestParam(name = "page", defaultValue = "1") int current_page,
					   HttpServletRequest req,
					   HttpSession session,
					   Model model) {
		
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		if(info == null) {
			return "redirect:/member/login";
		}
		
		Diary dto = new Diary();
		
		int size = 6;
		int total_page;
		int dataCount;

		Map<String, Object> map = new HashMap<String, Object>();
		
		dto.setMemberIdx(info.getMemberIdx());
		
		dataCount = diaryService.dataCount(map);
		total_page = myUtil.pageCount(dataCount, size);
		
		if (total_page < current_page) {
			current_page = total_page;
		}

		int offset = (current_page - 1) * size;
		if(offset < 0) offset = 0;

		map.put("offset", offset);
		map.put("size", size);
		map.put("memberIdx", dto.getMemberIdx());
		
		List<Diary> list = diaryService.listDiary(map);
		
		String cp = req.getContextPath();
		String query = "";
		String listUrl = cp + "/diary/list";
		String articleUrl = cp + "/diary/article?page=" + current_page;
		
		if (query.length() != 0) {
			listUrl = cp + "/diary/list?" + query;
			articleUrl = cp + "/diary/article?page=" + current_page + "&" + query;
		}
		
		String paging = myUtil.paging(current_page, total_page, listUrl);
		
		model.addAttribute("list", list);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("size", size);
		model.addAttribute("total_page", total_page);
		model.addAttribute("articleUrl", articleUrl);
		model.addAttribute("page", current_page);
		model.addAttribute("paging", paging);
		
		return "diary/list";
	}
	
	@GetMapping("write")
	public String writeForm(HttpSession session, Model model) throws Exception{
		
		model.addAttribute("mode", "write");
		model.addAttribute("dto", new Diary());
		
		return "diary/write";
	}
	
	@PostMapping("write")
	public String writeSubmit(Diary dto, HttpSession session) throws Exception{
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		String root = session.getServletContext().getRealPath("/");
		String pathname = root + "uploads" + File.separator + "diary";
		
		try {
			dto.setMemberIdx(info.getMemberIdx());
			diaryService.insertDiary(dto, pathname);
		} catch (Exception e) {
		
		}
		
		return "redirect:/diary/list";
	}
	
	@GetMapping("article/{diaryNum}")
	public String article(@PathVariable(name = "diaryNum") long diaryNum,
			@RequestParam(name = "page") String page,
			Model model) throws Exception{
		
		String query = "page="+page;
		
		Diary dto = diaryService.articleDiary(diaryNum);
		if(dto == null) {
			return "redirect:/diary/list?"+query;
		}
		
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		
		return "diary/article";
	}
	
	@GetMapping("update")
	public String updateForm(@RequestParam(name="diaryNum") long diaryNum,
							@RequestParam(name="page") String page,
							HttpSession session,
							Model model) throws Exception{
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		Diary dto = diaryService.articleDiary(diaryNum);
		if(dto == null || info.getMemberIdx() != dto.getMemberIdx()) {
			return "redirect:/diary/list?page="+page;
		}
		
		model.addAttribute("mode", "update");
		model.addAttribute("page", page);
		model.addAttribute("dto", dto);
		
		return "diary/write";
	}
	
	@PostMapping("update")
	public String updateSubmit(Diary dto,
							  @RequestParam(name="page") String page,
							  HttpSession session) throws Exception{
		
		String root = session.getServletContext().getRealPath("/");
		String pathname = root + "uploads" + File.separator + "diary";
		
		try {
			diaryService.updateDiary(dto, pathname);
		} catch (Exception e) {
			
		}
		
		return "redirect:/diary/list?page="+page;
	}
	
	@GetMapping("delete")
	public String deleteDiary(@RequestParam(name="diaryNum") long diaryNum
							, @RequestParam(name="page") String page
							, @RequestParam(name="fileName") String fileName
							, HttpSession session) throws Exception{
		
		String root = session.getServletContext().getRealPath("/");
		String pathname = root + "uploads" + File.separator + "diary";

		try {
			diaryService.deleteDiary(diaryNum, pathname);
		} catch (Exception e) {
		}
		
		return "redirect:/diary/list?page=" + page;
	}
}
