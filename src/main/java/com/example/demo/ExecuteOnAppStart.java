package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExecuteOnAppStart implements CommandLineRunner{
	@Override
	public void run(String ...args) {
		System.out.println("From ExecuteOnAppStart "+Arrays.toString(args));
	}
}
