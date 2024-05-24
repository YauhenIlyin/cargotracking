package by.ilyin.web.service;

import by.ilyin.web.dto.report.GetClientReportResponseDTO;
import by.ilyin.web.dto.report.GetSysadminReportResponseDTO;
import by.ilyin.web.entity.CustomUser;
import by.ilyin.web.feign.ReportCoreFeignClient;
import by.ilyin.web.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportCoreFeignClient reportCoreFeignClient;

    public GetClientReportResponseDTO getClientReport(String initialDate, String finalDate) {
        CustomUser customUser = ((CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getCustomUser();
        return reportCoreFeignClient.getClientReport(
                customUser.getClient().getId(),
                initialDate,
                finalDate);
    }

    public GetSysadminReportResponseDTO getSysadminReport(String initialDate, String finalDate) {
        return reportCoreFeignClient.getSysadminReport(initialDate, finalDate);
    }

}
