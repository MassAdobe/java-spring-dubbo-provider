package com.massadobe.attempt.application.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.massadobe.attempt.application.exception.ExceptionUtils;
import com.massadobe.attempt.application.service.UserInterface;
import com.massadobe.attempt.common.enums.ErrorCodeMsg;
import com.massadobe.attempt.common.pojo.ResponseStruct;
import com.massadobe.provider.DubboUserInterface;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: Hello
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-13 14:50
 * @Version: 1.0.0
 * @param: * @param null
 */
@RestController
@RequestMapping(value = "/hello")
public class Hello {

    @Autowired
    private UserInterface userInterface;

    @Reference
    private DubboUserInterface dubboUserInterface;


    @GetMapping("/read")
    @SentinelResource("read")
    public ResponseStruct Read() {
        return ResponseStruct.success(this.userInterface.listRead());
    }

    @GetMapping("/write")
    @SentinelResource("write")
    public ResponseStruct Slave() {
        return ResponseStruct.success(this.userInterface.listWrite());
    }

    @GetMapping("/printSth")
    @SentinelResource("printSth")
    public ResponseStruct PrintSth() {
        return ResponseStruct.success("hello world!");
    }

    @GetMapping("/dubboRead")
    public ResponseStruct DubboRead() {
        return ResponseStruct.success(this.dubboUserInterface.dubboListRead());
    }

    @GetMapping("/dubboWrite")
    public ResponseStruct DubboWrite() {
        return ResponseStruct.success(this.dubboUserInterface.dubboListWrite());
    }

    @GetMapping("/returnError")
    public ResponseStruct ReturnError() {
        return ExceptionUtils.ReturnError(ErrorCodeMsg.RETURN_ERROR_EXAMPLE, "abc", "efg");
    }

    @GetMapping("/otherError")
    public ResponseStruct OtherError() throws Exception {
        System.out.println("abc");
        System.out.println("efg");
        throw new Exception("直接抛出异常");
    }
}
