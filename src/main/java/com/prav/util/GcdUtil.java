package com.prav.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.prav.dao.model.Numbers;

public class GcdUtil {

	public static int calculateGcd(String input1, String input2) {
		BigInteger i1 = new BigInteger(input1);
		BigInteger i2 = new BigInteger(input2);
		return i1.gcd(i2).intValue();
	}

	public static List<Integer> calculateGcdList(List<Numbers> numbers) {
		List<Integer> gcdList = new ArrayList<>();
		for (Numbers number : numbers) {
			BigInteger i1 = new BigInteger(String.valueOf(number.getNumber1()));
			BigInteger i2 = new BigInteger(String.valueOf(number.getNumber2()));
			gcdList.add(i1.gcd(i2).intValue());
		}
		return gcdList;
	}

	public static int gcdSum(List<Numbers> numbers) {
		return sum(calculateGcdList(numbers));
	}

	private static int sum(List<Integer> list) {
		int sum = 0;
		for (int i : list) {
			sum += i;
		}
		return sum;
	}

}
