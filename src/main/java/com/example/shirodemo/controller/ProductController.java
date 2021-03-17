package com.example.shirodemo.controller;

import com.example.shirodemo.constants.StatusCode;
import com.example.shirodemo.vo.ProductVo;
import com.example.shirodemo.vo.Response;
import com.example.shirodemo.vo.VoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author jocken
 * @date 2021/3/17
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Response addProduct(@RequestBody @Validated ProductVo productVo) throws Exception {
        if (productVo.getPrice() == 12) {
            throw new Exception("price invalid");
        }
        log.info("add product:" + productVo);
        return Response.buildSuccess("success");
    }

    @RequestMapping(path = "/info", method = RequestMethod.GET)
    public VoResponse getProductInfo(@RequestParam(required = true) String productId) throws Exception {
        ProductVo vo = new ProductVo();
        vo.setProductId("P00001");
        vo.setName("stb_fos");
        vo.setType("stb");
        vo.setPrice(100);
        return new VoResponse<>(StatusCode.UI.UI_0, "success", vo);
    }

}
