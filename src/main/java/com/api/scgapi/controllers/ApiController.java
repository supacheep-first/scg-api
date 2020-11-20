package com.api.scgapi.controllers;

import com.api.scgapi.models.ABCModel;
import com.api.scgapi.models.ApiResponseModel;
import com.api.scgapi.services.ApiService;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/DOSCG")
public class ApiController {

    @Autowired
    ApiService apiService;

    @GetMapping("/doFindXYZ")
    public Object doFindXYZ(@RequestParam List<String> param) {
        param = param.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> P = Arrays.asList("X", "Y", "Z");

        for (String x : P) {
            if (!param.contains(x)) return "required X,Y,Z";
        }
        var n = 0;
        n = param.indexOf("X");
        param.set(n, apiService.formula(n) + "");
        n = param.indexOf("Y");
        param.set(n, apiService.formula(n) + "");
        n = param.indexOf("Z");
        param.set(n, apiService.formula(n) + "");

        ApiResponseModel res = new ApiResponseModel();
        res.setBody(param.toString());
        res.setStatus(HttpStatus.OK);
        return ResponseEntity.ok().body(param);
    }


    @GetMapping("/doFindBC")
    public Object doFindBC(@RequestParam List<String> param) {
        var a =0;
        var ab = 0;
        var ac = 0;
        if (param.size() == 3) {
            try {
                a = Integer.parseInt(param.get(0));
                ab = Integer.parseInt(param.get(1));
                ac = Integer.parseInt(param.get(2));

                var abc = new ABCModel();
                abc.setA(a);
                abc.setB(ab - a);
                abc.setC(ac - a);
                return ResponseEntity.ok().body(abc);
            } catch (Exception e) {
                return "param is not number.";
            }
        } else {
            return "input 3 param.";
        }
    }

    @GetMapping("/map")
    public Object map() throws IOException {
        ResponseBody response = null;
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/directions/json?\n" +
                        "origin=13.8030651,100.536095&destination=13.7466304,100.5393351\n" +
                        "&mode=driving\n" +
                        "&key=AIzaSyB_0eG7a3hwL7kmdl1-iivcHKDtDaLMBKk\n")
                .build();

        try {
            response = client.newCall(request).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(response.string());
    }

}
