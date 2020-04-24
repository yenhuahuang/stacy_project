package finaltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import java.util.HashMap;

import org.hamcrest.Matcher;
import org.junit.Test;

import GA.Ga;
import GA.Population;
import edu.neu.coe.info6205.life.base.Game;

public class GATest {

	@Test
	public void mutate() {
		Ga ga=new Ga();
		HashMap<String,String> individual=new HashMap<>();
		individual.put("0011111100000000100001101001101001110000", "-5 5,0 0,2 2,3 3,1 -1");
		//-5 5,0 0,2 2,3 3,1 -1
		HashMap<String,String> newindividual=ga.mutate(individual);
		assertNotEquals(individual.values(),newindividual.values());
	}
	

	
	
	@Test
	//generation = 1
	public void GetFitnessScoreTestG1() {
		Ga ga=new Ga();
	    Population p = new Population();
		HashMap<String,String> individual=new HashMap<>();
		HashMap<String,Integer>putpattern=new HashMap<>();
		String Key = "0011111100000000100001101001101001110000";
	    String Value =  "-5 5,0 0,2 2,3 3,1 -1";
		individual.put(Key,Value);
		//-5 5,0 0,2 2,3 3,1 -1
		 String runpattern = individual.get(Key);
		final Game.Behavior generations = Game.run(0L, runpattern);
		putpattern.put(Key, (int)generations.generation);
		System.out.println("Generation:"+(int)generations.generation);
		assertEquals(10001,ga.GetfitnessScore(putpattern).get(Key).intValue());
	}
	
	@Test
	//generation = 106
	public void GetFitnessScoreTestG106() {
		Ga ga=new Ga();
		HashMap<String,String> individual=new HashMap<>();
		HashMap<String,Integer>putpattern=new HashMap<>();
		String Key = "1000010000110110101110100101111000100100110000101011100000010010111110111100000011011100100110011111111101011111010101011000010100010011";
	    String Value =  "1 1,-3 3,-3 -3,4 0,-1 1,0 -1,-2 -2,0 2,-4 0,0 -0,0 -3,3 3,-5 0,5 0,3 0,2 2,0 3";
		individual.put(Key,Value);
		//1 1,-3 3,-3 -3,4 0,-1 1,0 -1,-2 -2,0 2,-4 0,0 -0,0 -3,3 3,-5 0,5 0,3 0,2 2,0 3
		String runpattern = individual.get(Key);
		final Game.Behavior generations = Game.run(0L, runpattern);
		putpattern.put(Key, (int)generations.generation);
		System.out.println("Generation:"+(int)generations.generation);
		assertEquals(10107,ga.GetfitnessScore(putpattern).get(Key).intValue());
	}
	
	@Test
	public void decideTerminationTest() {
		HashMap<String,Integer> test=new HashMap<>();
		Ga ga=new Ga();
		test.put("001001010100000001010000001111110000100011011000", (int) 8);
		assertEquals(0,ga.decideTermination(test));
	}
	
	
	@Test
	public void SelectionTest() {
		Ga ga=new Ga();
		HashMap putP=new HashMap<String,Integer>();
		HashMap putT=new HashMap<String,String>();
		HashMap RETURN=new HashMap<String,String>();
		putP.put("001001010100000001010000001111110000100011011000", 8);putP.put("1101110010111001111101010011001101101110", 1);putP.put("00001110101001100010100110001111011101110100101011100000", 1);
		putP.put("101001011100000100100001100000111101001101111010100101000001011", 6);putP.put("1110000111101111000001000100110010000000110001101011101001111010", 10);
		putP.put("1110111000010001011010100011010010000101011101011000111000111100010010111010000010000001", 4);putP.put("0001101011101000001100101010101001100110001011111011011101011111", 1);
		putP.put("10110011011000100100110111101001010010011100010011100010", 3);putP.put("1010111111110000110011001010110000000101011101100010101010001000", 3);putP.put("000100011101000010100001110110110111011111011010110011001000001000010011000111010101110011100100100101110100001000101011", 16);
	
		putT.put("001001010100000001010000001111110000100011011000", "2 2,0 0,1 0,-5 5,0 1,0 -2");putT.put("1101110010111001111101010011001101101110", "0 -3,-3 -3,-3 0,-3 3,3 -3");putT.put("00001110101001100010100110001111011101110100101011100000", "0 3,-2 -2,-2 2,4 4,4 -4,2 0,-0 0");
		putT.put("1101001011100000100100001100000111101001101111010100101000001011", "0 -2,-0 0,1 1,0 -1,-2 0,-4 -4,2 0,0 3");putT.put("1110000111101111000001000100110010000000110001101011101001111010", "-1 0,-4 0,0 1,2 0,0 0,0 -2,-3 -3,3 -3");putT.put("1110111000010001011010100011010010000101011101011000111000111100010010111010000010000001", "-3 0,0 2,2 -2,-2 2,2 2,3 -3,3 3,-3 3,3 0,-0 -0,1 1");
		putT.put("0001101011101000001100101010101001100110001011111011011101011111", "0 3,-1 0,-2 2,-2 -2,2 -2,-4 4,-4 -4,5 0");putT.put("10110011011000100100110111101001010010011100010011100010", "-3 -3,1 -1,3 0,-2 0,2 0,0 -1,-1 0");putT.put("1010111111110000110011001010110000000101011101100010101010001000", "-4 -4,-1 0,0 -2,-2 -2,0 2,3 -3,-2 2,1 1");
		putT.put("000100011101000010100001110110110111011111011010110011001000001000010011000111010101110011100100100101110100001000101011", "0 2,0 -1,-1 -1,0 -4,4 -4,0 -3,0 -2,1 1,0 3,0 4,3 0,-1 0,4 4,1 0,-3 3");
		
//		RETURN.put("001001010100000001010000001111110000100011011000", "-2 2,0 0,1 0,-5 5,0 1,0 -2");RETURN.put("1101001011100000100100001100000111101001101111010100101000001011", "0 -2,-0 0,1 1,0 -1,-2 0,-4 -4,2 0,0 3");RETURN.put("1110000111101111000001000100110010000000110001101011101001111010", "-1 0,-4 0,0 1,2 0,0 0,0 -2,-3 -3,3 -3");
//		RETURN.put("1110111000010001011010100011010010000101011101011000111000111100010010111010000010000001", "-3 0,0 2,2 -2,-2 2,2 2,3 -3,3 3,-3 3,3 0,-0 -0,1 1");RETURN.put("000100011101000010100001110110110111011111011010110011001000001000010011000111010101110011100100100101110100001000101011", "0 2,0 -1,-1 -1,0 -4,4 -4,0 -3,0 -2,1 1,0 3,0 4,3 0,-1 0,4 4,1 0,-3 3");
		
		RETURN.put("001001010100000001010000001111110000100011011000", "2 2,0 0,1 0,-5 5,0 1,0 -2");RETURN.put("1110000111101111000001000100110010000000110001101011101001111010", "-1 0,-4 0,0 1,2 0,0 0,0 -2,-3 -3,3 -3");
		RETURN.put("1110111000010001011010100011010010000101011101011000111000111100010010111010000010000001", "-3 0,0 2,2 -2,-2 2,2 2,3 -3,3 3,-3 3,3 0,-0 -0,1 1");
		RETURN.put("000100011101000010100001110110110111011111011010110011001000001000010011000111010101110011100100100101110100001000101011", "0 2,0 -1,-1 -1,0 -4,4 -4,0 -3,0 -2,1 1,0 3,0 4,3 0,-1 0,4 4,1 0,-3 3");
		//assertThat(ga.Selection(putP, putT), is(RETURN));
		assertEquals(ga.Selection(putP, putT),RETURN);
	}
//
//	private Matcher is(HashMap rETURN) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
}
