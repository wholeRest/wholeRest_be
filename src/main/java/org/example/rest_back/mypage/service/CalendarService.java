package org.example.rest_back.mypage.service;

import lombok.AllArgsConstructor;
import org.example.rest_back.mypage.dto.CalendarDto;
import org.example.rest_back.mypage.dto.TodoDto;
import org.example.rest_back.mypage.entity.Calendar;
import org.example.rest_back.mypage.entity.Member;
import org.example.rest_back.mypage.repository.CalendarRepository;
import org.example.rest_back.mypage.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final MemberRepository memberRepository;

    //get All
    public List<CalendarDto> getAllCalendar(){
        List<Calendar> calendars = calendarRepository.findAll();
        return calendars.stream()
                .map(CalendarDto::from)
                .collect(Collectors.toList());
    }

    //Read
    public CalendarDto getCalendarByMemberId(String member_id){
        Member member = memberRepository.findById(member_id).orElse(null);
        Calendar calendar = calendarRepository.findByMember(member);
        return CalendarDto.from(calendar);
    }

    //Create
    public void createCalendar(CalendarDto calendarDto){
        Member member = memberRepository.findById(calendarDto.getMember_id()).orElse(null);
        Calendar calendar = new Calendar();
        calendar.setMember(member);
        calendarRepository.save(calendar);
    }
}
