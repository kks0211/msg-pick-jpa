package com.msgpick.module.notice.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notices")
public class NoticeController {

    @GetMapping
    public String noticeListPage() {
        return "notices/list";
    }

}
