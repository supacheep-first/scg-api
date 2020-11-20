package com.api.scgapi.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiService {
    public int formula(int n){
        int res =0;
        res= (int) Math.pow(n,2)-n+3;
        return res;
    }
}
