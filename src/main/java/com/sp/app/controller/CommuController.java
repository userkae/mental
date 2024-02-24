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
import com.sp.app.domain.Commu;
import com.sp.app.domain.CommuReply;
import com.sp.app.domain.SessionInfo;
import com.sp.app.service.CommuService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/commu/*")
public class CommuController {

	@Autowired
	private CommuService commuService;
	
	@Autowired
	private MyUtil myUtil;
	
	@GetMapping("list")
	public String list(@RequestParam(name="page", defaultValue = "1") int current_page,
						HttpServletRequest req,
						HttpSession session,
						Model model) throws Exception{

		
		Commu dto = new Commu();
		List<Commu> list = new ArrayList<>();
		
		int size = 10;
		int total_page;
		int dataCount;
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int offset = (current_page - 1) * size;
		if(offset < 0) offset = 0;
		
		dataCount = commuService.dataCount(map);
		
		total_page = myUtil.pageCount(dataCount, size);
		if (total_page < current_page) {
			current_page = total_page;
		}


		map.put("offset", offset);
		map.put("size", size);
		
		list = commuService.commuList(map);
		
		String cp = req.getContextPath();
		//String query = "";
		String listUrl = cp + "/commu/list";
		String articleUrl = cp + "/commu/article?page="+current_page;
		
		String paging = myUtil.paging(current_page, total_page, listUrl);
		
		model.addAttribute("list", list);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("size", size);
		model.addAttribute("total_page", total_page);
		model.addAttribute("articleUrl", articleUrl);
		model.addAttribute("page", current_page);
		model.addAttribute("paging", paging);
		model.addAttribute("dto", dto);
			
		return "commu/list";
	}
	
	@GetMapping("article/{commuNum}")
	public String article(@PathVariable(name="commuNum") long commuNum,
						  @RequestParam(name="page") String page,
						  HttpSession session,
						  Model model) throws Exception{
		
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		String query = "page="+page;
		
		Commu dto = commuService.articleCommu(commuNum);
		commuService.updateHitCount(commuNum);
		
		
		if(info == null) {
			return "redirect:/member/login";
		}
		
		long infoMemberIdx = info.getMemberIdx();
		
		model.addAttribute("infoMemberIdx", infoMemberIdx);
		model.addAttribute("dto", dto);
		model.addAttribute("query", query);
		
		
		List<CommuReply> list = new ArrayList<>();
		list = commuService.commuReplyList(commuNum);
		
		model.addAttribute("list", list);
		
		return "commu/article";
	}
	
	@PostMapping("insertReply")
	@ResponseBody
	public Map<String, Object> insertReply(@RequestParam(name="commuNum") long commuNum,
											@RequestParam(name="reContent") String reContent,
											@RequestParam(name="profile") String profile,
											CommuReply dto,
											HttpSession session) throws Exception{
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		dto.setMemberIdx(info.getMemberIdx());
		String state = "success";
		
		try {
			commuService.insertReply(dto);
			dto.setReplyNum(commuService.maxReplyNum());
			dto = commuService.selectReply(dto.getReplyNum());
		} catch (Exception e) {
			state = "false";
		}
		
		Map<String, Object> model = new HashMap<>();
		model.put("state", state);

		model.put("nickName", dto.getNickName());
		model.put("reRegDate", dto.getReRegDate());
		model.put("reContent", dto.getReContent());
		model.put("replyNum", dto.getReplyNum());
		
		return model;
	}
	
	@GetMapping("write")
	public String writeForm(HttpSession session, Model model) throws Exception{
		
		model.addAttribute("mode", "write");
		model.addAttribute("dto", new Commu());
		
		return "commu/write";
	}
	
	@PostMapping("write")
	public String writeSubmit(Commu dto, HttpSession session) throws Exception{
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		try {
			dto.setMemberIdx(info.getMemberIdx());
			commuService.insertCommu(dto);
		} catch (Exception e) {

		}
		
		return "redirect:/commu/list";
	}
	
	@GetMapping("update")
	public String updateForm(@RequestParam(name="commuNum") long commuNum,
							 @RequestParam(name="page") String page,
							 HttpSession session,
							 Model model) throws Exception{
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		Commu dto = commuService.articleCommu(commuNum);
		if(dto == null || info.getMemberIdx() != dto.getMemberIdx()) {
			return "redirect:/commu/list?page="+page;
		}
		
		model.addAttribute("mode", "update");
		model.addAttribute("page", page);
		model.addAttribute("dto", dto);
		
		return "commu/write";
	}
	
	@PostMapping("update")
	public String updateSubmit(Commu dto, @RequestParam(name="page") String page, HttpSession session) throws Exception{
		
		try {
			commuService.updateCommu(dto);
		} catch (Exception e) {

		}
		
		return "redirect:/commu/list?page="+page;
	}
	
	@GetMapping("delete")
	public String deleteCommu(@RequestParam(name="commuNum") long commuNum
							, @RequestParam(name="page") String page
							, HttpSession session) throws Exception{
		
		try {
			commuService.deleteCommu(commuNum);
		} catch (Exception e) {

		}
		
		return "redirect:/commu/list?page="+page;
		
	}
	
	@PostMapping("replyDelete")
	@ResponseBody
	public Map<String, Object> deleteReply(@RequestParam(name="commuNum") long commuNum
								, @RequestParam(value="replyNum") long replyNum
								, @RequestParam(name="page") String page
								, HttpSession session) throws Exception{
		
		String state = "success";
		
		try {
			System.out.println(">>>>>>>>>>>>>"+replyNum);
			commuService.deleteReply(replyNum);
		} catch (Exception e) {
			state = "false";
		}
		
		Map<String, Object> model = new HashMap<>();
		model.put("state", state);
		model.put("page", page);
		model.put("commuNum", commuNum);
		
		return model;
	}
	
}
