package by.ilyin.web.controller;

import by.ilyin.web.dto.report.GetClientReportResponseDTO;
import by.ilyin.web.dto.report.GetSysadminReportResponseDTO;
import by.ilyin.web.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reports")
public class ReportWebController {
    //todo validate date format
    private final ReportService reportService;

    @GetMapping("/client")
    public GetClientReportResponseDTO getClientReport(@RequestParam(required = false) String initialDate,
                                                      @RequestParam(required = false) String finalDate) {
        return reportService.getClientReport(initialDate, finalDate);
    }

    @GetMapping("/sysadmin")
    public GetSysadminReportResponseDTO getSysadminReport(@RequestParam(required = false) String initialDate,
                                                          @RequestParam(required = false) String finalDate) {
        return reportService.getSysadminReport(initialDate, finalDate);
    }

}
