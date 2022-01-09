package EcommerceProject.Store.openfeign;

import EcommerceProject.Store.entity.SendPayment;
import net.minidev.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;

@FeignClient(name="My-Fatoorah",  url="https://apitest.myfatoorah.com/") //url
public interface MyFatoorahProxy {

    @PostMapping("/v2/SendPayment") // endpoint
    public JSONObject retrievePaymentValues(@Valid @RequestBody SendPayment sendPayment, @RequestHeader String authorization); // passing requestBody & Header
}
