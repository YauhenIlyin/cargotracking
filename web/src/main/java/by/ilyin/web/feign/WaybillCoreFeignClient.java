package by.ilyin.web.feign;

import by.ilyin.web.dto.WaybillDTO;
import by.ilyin.web.dto.page.PageDTO;
import by.ilyin.web.dto.response.CreateWaybillResponseDTO;
import by.ilyin.web.entity.Waybill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@FeignClient(name = "waybillCoreFeignClient", url = "${feign.client.core.url}")
public interface WaybillCoreFeignClient {

    @PostMapping(value = "/api/waybills", consumes = "application/json")
    CreateWaybillResponseDTO createUser(WaybillDTO waybillDTO);

    @GetMapping(value = "api/waybills", consumes = "application/json")
    PageDTO<Waybill> getWaybills(@RequestParam Integer page,
                                 @RequestParam Integer size,
                                 @RequestParam Set<String> carriageStatuses);

}
