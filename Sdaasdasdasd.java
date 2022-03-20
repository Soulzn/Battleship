package qweadsasdadasdf;

import java.util.*;
import java.util.stream.Collectors;

public class Sdaasdasdasd {

	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>();
		Collections.addAll(list, 1, 2, 3, 4, 5);
		List<Integer> target = new ArrayList<>();
		list.stream()
		  .skip(2)
		  .forEachOrdered(target::add);
		String targetString = list.stream().map(Object::toString)
				.skip(1)
				.collect(Collectors.joining(", "));
		System.out.println(targetString);
		int[] shitter = new int[]{5,4,3,2,1};
        System.out.println(Arrays.toString(shitter));
        System.out.println("");
        int shipRandom = (int)(Math.random() * 5);
        System.out.println(shipRandom);
        String bepsi = Integer.toString(shitter[0]);
        System.out.println(bepsi);
        
        for (int i = 0; i < 3; i++) {
        	double fuckyou = Math.random() * 5;
            int fuckyoutoo = (int) (fuckyou);
        	System.out.println(Double.toString(fuckyou) + ", " + Integer.toString(fuckyoutoo) + ", " + Integer.toString(i));
        	}
	}

}
