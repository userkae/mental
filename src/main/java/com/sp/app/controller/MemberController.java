package com.sp.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sp.app.domain.Member;
import com.sp.app.domain.SessionInfo;
import com.sp.app.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("login")
	public String loginForm() {
		
		return "member/login";
	}
	
	@PostMapping("login")
	public String loginSubmit(@RequestParam(name="userId") String userId,
							  @RequestParam(name="userPwd") String userPwd,
						      HttpSession session,
						      Model model){
	
		Member dto = memberService.findById(userId);
		if (dto == null || ! userPwd.equals(dto.getUserPwd())) {
			model.addAttribute("message", "아이디 또는 패스워드가 일치하지 않습니다.");
			return "member/login";
		}
		
		if(dto.getEnabled() == 0) {
			model.addAttribute("message", "계정이 비활성화되었습니다.");
			return "member/login";
		}
	
		// 세션에 로그인 정보 저장
		SessionInfo info = new SessionInfo();
		info.setUserId(dto.getUserId());
		info.setNickName(dto.getNickName());
		info.setMemberIdx(dto.getMemberIdx());
		info.setMembership(memberService.findByMembership(info.getMemberIdx()));
	
		session.setAttribute("member", info);
		
		// 로그인 이전 URI로 이동
		String uri = (String) session.getAttribute("preLoginURI");
		session.removeAttribute("preLoginURI");
		if (uri == null) {
			uri = "redirect:/main";
		} else {
			uri = "redirect:" + uri;
		}
	
		return uri;
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("member");

		session.invalidate();

		return "redirect:/main";
	}
	
	@GetMapping("signup")
	public String signupForm(Model model) {
		
		model.addAttribute("mode", "signup");
		model.addAttribute("dto", new Member());
		
		return "member/signup";
	}
	
	@PostMapping("signup")
	public String signupSubmit(Member dto, final RedirectAttributes reAttr, Model model) {
		try {
			memberService.insertMember(dto);
		} catch (DuplicateKeyException e) {
			// 기본키 중복에 의한 제약 조건 위반
			model.addAttribute("mode", "signup");
			model.addAttribute("message", "아이디 중복으로 회원가입이 실패했습니다.");
			return "member/signup";
		} catch (DataIntegrityViolationException e) {
			// 데이터형식 오류, 참조키, NOT NULL 등의 제약조건 위반
			model.addAttribute("mode", "signup");
			model.addAttribute("message", "제약 조건 위반으로 회원가입이 실패했습니다.");
			return "member/signup";
		} catch (Exception e) {
			model.addAttribute("mode", "signup");
			model.addAttribute("message", "회원가입이 실패했습니다.");
			return "member/signup";
		}

		StringBuilder sb = new StringBuilder();
		sb.append(dto.getNickName() + "님의 회원 가입이 정상적으로 처리되었습니다.<br>");
		sb.append("메인화면으로 이동하여 로그인 하시기 바랍니다.<br>");

		// 리다이렉트된 페이지에 값 넘기기
		reAttr.addFlashAttribute("message", sb.toString());
		reAttr.addFlashAttribute("title", "회원가입 완료");

		return "redirect:/member/complete";
	}
	
	@GetMapping("complete")
	public String complete(@ModelAttribute("message") String message) throws Exception {

		// 컴플릿 페이지(complete.jsp)의 출력되는 message와 title는 RedirectAttributes 값이다.
		// F5를 눌러 새로 고침을 하면 null이 된다.

		if (message == null || message.length() == 0) // F5를 누른 경우
			return "redirect:/main";

		return "/member/complete";
	}
	
	@PostMapping("userIdCheck")
	@ResponseBody
	public Map<String, Object> idCheck(@RequestParam(name="userId") String userId) throws Exception {
		// ID 중복 검사
		String p = "true";
		Member dto = memberService.findById(userId);
		if (dto != null) {
			p = "false";
		}

		Map<String, Object> model = new HashMap<>();
		model.put("passed", p);
		return model;
	}
	
	@GetMapping("update")
	public String updateInfoForm(Member dto, HttpSession session, Model model) throws Exception{
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		dto.setMemberIdx(info.getMemberIdx());
		
		dto = memberService.findByInfo(dto.getMemberIdx());
		
		String[] emailParts = dto.getEmail().split("@");
		
		dto.setEmail1(emailParts[0]);
		dto.setEmail2(emailParts[1]);
		
		model.addAttribute("mode", "update");
		model.addAttribute("dto", dto);
		
		return "member/signup";
	}
	
	@PostMapping("update")
	public String updateSubmit(Member dto, HttpSession session, Model model) throws Exception{
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		dto.setMemberIdx(info.getMemberIdx());
		memberService.updateInfo(dto);
		
		info.setNickName(dto.getNickName());
		
		return "redirect:/main";
	}
}
