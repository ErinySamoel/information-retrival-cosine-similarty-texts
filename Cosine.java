package IR3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class Cosine {
	static ArrayList<String> readfile(String path_file, ArrayList<String> doc_strings) throws IOException {
		 File file = new File(path_file);
		 BufferedReader br = new BufferedReader(new FileReader(file)); 
			String st;
			 while ((st = br.readLine()) != null) {
				 String[] words = st.split("\\W+");
                for (String word : words) {
               	 word = word.toLowerCase();
               	 doc_strings.add(word);
                    
                }
			 }
		 return doc_strings;
	}
//Calculate term frequency domain for documents
	static double D_(ArrayList<String> alldocs_strings,ArrayList<String> doc_strings){//doc_strings:unique_words
	    double sum = 0;

		for(String i:alldocs_strings) {
			if(doc_strings.contains(i)) {
				sum++;
			}
			
		}
		return sum;
		
	}
	/*
	//Calculate binary frequency domain for documents
	
	static ArrayList<Integer> D(ArrayList<String> alldocs_strings,ArrayList<String> doc_strings,ArrayList<Integer> D_doc){//doc_strings:unique_words
 HashSet<String> unique_words = new HashSet<String>(alldocs_strings);//alldocs_strings:all words union in document
		for(String i:unique_words) {
			if(doc_strings.contains(i)) {
				D_doc.add(1);
			}
			else {
				D_doc.add(0);
			}
			
		}
		return D_doc;
		
	}
	*/
	static double cosine_similarity_(double D1,double D2) {
		int d1_dot_d2=0;
		for(int i=0;i<D1;i++) {
			d1_dot_d2+=D1*D2;
		
		}
		int normD1=0;
		for(int i=0;i<D1;i++) {
			normD1+=D1*D1;
		}
		double root1=0.0;
		root1=Math.sqrt(normD1);
		
		int normD2=0;
		for(int i=0;i<D2;i++) {
			normD2+=D2*D2;
		}
		double root2=0.0;
		root2=Math.sqrt(normD2);
		double cosine=d1_dot_d2/(root1*root2);
		return cosine;
		
	}
	static double cosine_similarity(ArrayList<Integer> D1,ArrayList<Integer> D2) {
		int d1_dot_d2=0;
		for(int i=0;i<D1.size();i++) {
			d1_dot_d2+=D1.get(i)*D2.get(i);
		
		}
		int normD1=0;
		for(int i=0;i<D1.size();i++) {
			normD1+=D1.get(i)*D1.get(i);
		}
		double root1=0.0;
		root1=Math.sqrt(normD1);
		
//System.out.println("***||norm D1|| "+root1);//*root1
		int normD2=0;
		for(int i=0;i<D2.size();i++) {
			normD2+=D2.get(i)*D2.get(i);
		}
		double root2=0.0;
		root2=Math.sqrt(normD2);
		//double normD2=normalize_D(D2);
//System.out.println("***||norm D2|| "+root2);//*root2
		double cosine=d1_dot_d2/(root1*root2);
		return cosine;
		
	}
	public static void main(String[] args) throws IOException {
		String path1="C:\\Users\\ErinySamoel\\Downloads\\ir1\\10.txt";
		ArrayList<String> doc1_strings = new ArrayList<String>();
		doc1_strings=readfile(path1,doc1_strings);
		System.out.println("doc1"+doc1_strings);
		
		String path2="C:\\Users\\ErinySamoel\\Downloads\\ir1\\11.txt";
		ArrayList<String> doc2_strings = new ArrayList<String>();
		doc2_strings=readfile(path2,doc2_strings);
		System.out.println("doc2"+doc2_strings);
		
		String path3="C:\\Users\\ErinySamoel\\Downloads\\ir1\\100.txt";
		ArrayList<String> doc3_strings = new ArrayList<String>();
		doc3_strings=readfile(path3,doc3_strings);
		System.out.println("doc3"+doc3_strings);
	    
		
		String path4="C:\\Users\\ErinySamoel\\Downloads\\ir1\\101.txt";
		ArrayList<String> doc4_strings = new ArrayList<String>();
		doc4_strings=readfile(path4,doc4_strings);
		System.out.println("doc4"+doc4_strings);


		ArrayList<String> alldocs12_strings = new ArrayList<String>();
		alldocs12_strings.addAll(doc1_strings);
		alldocs12_strings.addAll(doc2_strings);
		//***********
		double dd1=D_(alldocs12_strings,doc1_strings);
		double dd=D_(alldocs12_strings,doc1_strings);

		double cos12=cosine_similarity_(dd1,dd);
   System.out.println("D1 and D2 cosine_similarity= "+cos12);
		double score=Math.round(Math.toDegrees(Math.acos(cos12)));
//ystem.out.println("seta = arccos("+score+")");
	
	ArrayList<String> alldocs13_strings = new ArrayList<String>();
	alldocs13_strings.addAll(doc1_strings);
	alldocs13_strings.addAll(doc3_strings);
	double d1_13=D_(alldocs13_strings,doc1_strings);
	double d3=D_(alldocs13_strings,doc3_strings);
	double cos13=cosine_similarity_(d1_13,d3);
    System.out.println("D1 and D3 cosine_similarity= "+cos13);
    //	double score2=Math.round(Math.toDegrees(Math.acos(cos13)));

//      System.out.println("seta = arccos("+score2+")");


ArrayList<String> alldocs14_strings = new ArrayList<String>();
alldocs14_strings.addAll(doc1_strings);
alldocs14_strings.addAll(doc4_strings);
double d1_14=D_(alldocs14_strings,doc1_strings);
double d4=D_(alldocs14_strings,doc4_strings);
double cos14=cosine_similarity_(d1_14,d4);
System.out.println("D1 and D4 cosine_similarity= "+cos14);
//double score14=Math.round(Math.toDegrees(Math.acos(cos14))); 

//System.out.println("seta = arccos("+score14+")");


ArrayList<String> alldocs23_strings = new ArrayList<String>();
alldocs23_strings.addAll(doc2_strings);
alldocs23_strings.addAll(doc3_strings);
double d2_23=D_(alldocs23_strings,doc2_strings);
double d3_23=D_(alldocs23_strings,doc3_strings);
double cos23=cosine_similarity_(d2_23,d3_23);
System.out.println("D2 and D3 cosine_similarity= "+cos23);
//double score23=Math.round(Math.toDegrees(Math.acos(cos23)));
//System.out.println("seta = arccos("+score23+")");


ArrayList<String> alldocs24_strings = new ArrayList<String>();
alldocs24_strings.addAll(doc2_strings);
alldocs24_strings.addAll(doc4_strings);
double d2_24=D_(alldocs24_strings,doc2_strings);
double d4_24=D_(alldocs24_strings,doc4_strings);
double cos24=cosine_similarity_(d2_24,d4_24);
System.out.println("D2 and D4 cosine_similarity=  "+cos24);
//double score24=Math.round(Math.toDegrees(Math.acos(cos24)));
//System.out.println("seta = arccos("+score24+")");

ArrayList<String> alldocs34_strings = new ArrayList<String>();
alldocs34_strings.addAll(doc3_strings);
alldocs34_strings.addAll(doc4_strings);
double d3_34=D_(alldocs34_strings,doc3_strings);
double d4_34=D_(alldocs34_strings,doc4_strings);
double cos34=cosine_similarity_(d3_34,d4_34);
System.out.println("D3 and D4 cosine_similarity=  "+cos34);
//double score34=Math.round(Math.toDegrees(Math.acos(cos34)));
//System.out.println("seta = arccos("+score34+")");


	System.out.println("*************************************\n sorted descendingly more similar");

		Map<String,Double>cosine= new HashMap<String, Double>();
//cosine.put("\nD1 and D2 cosine similarity =" , cosine12);
		cosine.put("\nD1 and D2 cosine similarity =", cos12);
		cosine.put("\nD1 and D3 cosine similarity =", cos13);
		cosine.put("\nD1 and D4 cosine similarity =", cos14);
		cosine.put("\nD2 and D3 cosine similarity =", cos23);
		cosine.put("\nD2 and D4 cosine similarity =", cos24);
		cosine.put("\nD3 and D4 cosine similarity =", cos34);



		LinkedHashMap<String, Double> map2 = 
				cosine.entrySet()
			       .stream()             
			       .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			       .collect(Collectors.toMap(e -> e.getKey(), 
			                                 e -> e.getValue(), 
			                                 (e1, e2) -> null, // or throw an exception
			                                 () -> new LinkedHashMap<String, Double>()));
	     
		System.out.println(map2);

	

	}




		// TODO Auto-generated method stub
		
		

	}


//public class Cosine {







