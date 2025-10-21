package com.example.math;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class MathController {
    @GetMapping("/collatzsequence")
    public String sequence(Integer numero){
        System.out.println();
        System.out.println(generatesequence(13));
        String response = "{\"operation\": \"collatzsequence\",\"input\":" + numero  + "," +  "output:" + generatesequence(numero).toString() + "}";
        System.out.println(response);

        return response;
    }
    private ArrayList<Integer> generatesequence(Integer numero){
        ArrayList<Integer> secuencia = new ArrayList<>();
        secuencia.add(numero);
        while (numero!= 1){
            if(numero%2==0){
                numero = numero/2;
            }
            else{
                numero = (numero*3)+1;
            }
            secuencia.add(numero);
        }
        return secuencia;
    }
}
