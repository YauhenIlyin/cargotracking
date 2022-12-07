package by.ilyin.web.feign;

import by.ilyin.web.dto.report.GetClientReportResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "reportCoreFeignClient", url = "${feign.client.core.url}")
public interface ReportCoreFeignClient {

    @GetMapping(value = "/api/reports/client")
    GetClientReportResponseDTO getClientReport(@RequestParam Long clientId,
                                               @RequestParam String initialDate,
                                               @RequestParam String finalDate);

}
