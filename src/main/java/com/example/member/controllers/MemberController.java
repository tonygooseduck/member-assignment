package com.example.member.controllers;

import com.example.member.model.Member;
import com.example.member.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemberController {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/members")
    public String addMember(Member member) {
        memberRepository.add(member);
        return "redirect:/" ;
    }

    @GetMapping("/search")
    public String findMembers(@RequestParam(required = false, defaultValue = "") String id, @RequestParam(required = false, defaultValue = "") String email, Model model) {
        if (email.equals("")) {
            List<Member> membersById = memberRepository.findById(id);
            model.addAttribute("membersById", membersById);
        } else {
            List<Member> membersByMail = memberRepository.findByEmail(email);
            model.addAttribute("membersByMail", membersByMail);
        }
        return "index";
    }

    @PostMapping("/update")
    public String updateMember(Member member, Model model) {
        int rowsAffected = memberRepository.update(member);
        if (rowsAffected == 1) {
            model.addAttribute("memberUpdated", member);
        }
        return "index";
    }
}
