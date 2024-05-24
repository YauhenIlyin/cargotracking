package by.ilyin.core.controller;

import by.ilyin.core.dto.report.GetClientReportResponseDTO;
import by.ilyin.core.entity.Client;
import by.ilyin.core.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reports")
public class ReportCoreController {

    private final ReportService reportService;

    @GetMapping(value = "/client")
    public GetClientReportResponseDTO getClientReport(@RequestParam Long clientId,
                                                      @RequestParam(required = false) String initialDate,
                                                      @RequestParam(required = false) String finalDate) {
        return reportService.getClientReport(clientId, initialDate, finalDate);
    }

}
