package play;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("What do you want");
		System.out.println("1) Encrypt");
		System.out.println("2) Decrypt");

		Scanner sc = new Scanner(System.in);
		
		int number = sc.nextInt();
		
		if(number == 1) {
			encrypt();
		}
		else if(number == 2) {
			decrypt();
		}
		
	}
	
	public static void encrypt() {
		
		Scanner sc = new Scanner(System.in);
		
		String alfabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
		
		System.out.println("Enter sentence what you want to encypt!");
		String wordToEncrypt = sc.nextLine();
		
		System.out.println("Enter key word");
		String wordKey = sc.nextLine()+alfabet;
		
		String wordToEncryptWithoutSpace = deleteSpace(wordToEncrypt);
		String wordKeyWithoutSpace = deleteSpace(wordKey);
		
		String wordToEncryptUpperCaseWithoutSpace = wordToEncryptWithoutSpace.toUpperCase();
		String wordKeyUpperCaseWithoutSpace = wordKeyWithoutSpace.toUpperCase();
		
		String wordToEncryptUpperCaseWithoutSpaceAndDoubleLetter = addingXInDoubleLetter(wordToEncryptUpperCaseWithoutSpace);
		String wordKeyUpperCaseWithoutSpaceAndDoubleLetter = deleteDoubleLetter(wordKeyUpperCaseWithoutSpace);
		
		wordToEncryptUpperCaseWithoutSpaceAndDoubleLetter=deleteSpace(wordToEncryptUpperCaseWithoutSpaceAndDoubleLetter);
		wordKeyUpperCaseWithoutSpaceAndDoubleLetter=deleteSpace(wordKeyUpperCaseWithoutSpaceAndDoubleLetter);
		
		if(wordToEncryptUpperCaseWithoutSpaceAndDoubleLetter.length()%2!=0)
			wordToEncryptUpperCaseWithoutSpaceAndDoubleLetter+="X";
		
		String encypt = encypting(wordToEncryptUpperCaseWithoutSpaceAndDoubleLetter, wordKeyUpperCaseWithoutSpaceAndDoubleLetter);


		System.out.println(wordToEncryptUpperCaseWithoutSpaceAndDoubleLetter);
		System.out.println(wordKeyUpperCaseWithoutSpaceAndDoubleLetter);
		
		System.out.println(encypt);
		
	}

	public static void decrypt() {
		
		Scanner sc = new Scanner(System.in);
		String alfabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
		
		System.out.println("Enter sentence what you want to decrypt!");
		String wordToDecrypt = sc.nextLine();
		
		System.out.println("Enter key word");
		String wordKey = sc.nextLine()+alfabet;
		
		String wordToDecryptUpperCase = wordToDecrypt.toUpperCase();
		String wordKeyUpperCase = wordKey.toUpperCase();
		
		String wordToDecryptWithoutSpaceUpperCase = deleteSpace(wordToDecryptUpperCase);
		String wordKeyWithoutSpaceUpperCase = deleteSpace(wordKeyUpperCase);
		
		String wordKeyUpperCaseWithoutSpaceAndDoubleLetter = deleteDoubleLetter(wordKeyWithoutSpaceUpperCase);
		
		if(wordToDecryptWithoutSpaceUpperCase.length()%2 != 0) {
			System.out.println("The sentence has odd number of letter");
			return;
		}
		
		String decrypt = decrypting(wordToDecryptWithoutSpaceUpperCase, wordKeyUpperCaseWithoutSpaceAndDoubleLetter); 
		
		System.out.println(wordToDecryptWithoutSpaceUpperCase);
		System.out.println(wordKeyUpperCaseWithoutSpaceAndDoubleLetter);
		System.out.println(decrypt);
		
	}
	
	public static String deleteSpace(String word) {
		ArrayList<Character> arrayWord = new ArrayList<>();
		for (int i = 0; i < word.length(); i++) {
			if(word.charAt(i) != ' ')
				arrayWord.add(word.charAt(i));
		}
		word="";
		for (Character character : arrayWord) {
			word+=character;
		}
		return word;
	}
	
	public static String addingXInDoubleLetter(String word) {
		ArrayList<Character> arrayWord = new ArrayList<>();
		for (int i = 0; i < word.length(); i++) {
			arrayWord.add(word.charAt(i));
		}
		
		arrayWord.add(' ');
		arrayWord.add(' ');
		
		for (int i = 0; i < arrayWord.size()-3; i++) {
			if(arrayWord.get(i) == arrayWord.get(i+1)) {
				arrayWord.add(i+1, 'X');
				arrayWord.add(i+3, 'X');
			}
		}
		word="";
		for (Character character : arrayWord) {
			word+=character;
		}
		return word;
	}
	
	public static String deleteDoubleLetter(String word) {
		ArrayList<Character> arrayWord = new ArrayList<>();
		for (int i = 0; i < word.length(); i++) {
			arrayWord.add(word.charAt(i));
		}
		
		for (int i = 0; i < arrayWord.size()-1; i++) {
			for (int j = i+1; j < arrayWord.size(); j++) {
				if(arrayWord.get(i)==arrayWord.get(j)) {
					arrayWord.remove(j);
					j--;
				}
			}
		}
		
		word="";
		for (Character character : arrayWord) {
			word+=character;
		}
		
		return word;
	}
	
	public static String encypting(String word, String key) {
		ArrayList<Character> arrayWord = new ArrayList<>();
		for (int i = 0; i < word.length(); i++) {
			arrayWord.add(word.charAt(i));
		}
		ArrayList<Character> arrayKey = new ArrayList<>();
		for (int i = 0; i < key.length(); i++) {
			arrayKey.add(key.charAt(i));
		}
		
		int firstLetterPosition = 0;
		int secondLetterPostion = 0;
		
		for (int i = 0; i < arrayWord.size(); i+=2) {
			for (int j = 0; j < arrayKey.size(); j++) {
				if(arrayWord.get(i) == arrayKey.get(j)) {
					for (int j2 = 0; j2 < arrayKey.size(); j2++) {
						if(arrayWord.get(i+1) == arrayKey.get(j2)) {
							
							firstLetterPosition=j;
							secondLetterPostion=j2;
							
							if(firstLetterPosition/5 == secondLetterPostion/5) {
								
								if(firstLetterPosition%5 == 4) {
									firstLetterPosition=j-4;
									secondLetterPostion=++j2;
								}
								else if(secondLetterPostion%5 == 4) {
									firstLetterPosition=++j;
									secondLetterPostion=j2-4;
								}
								else {
									firstLetterPosition++;
									secondLetterPostion++;
								}

							}
							
							else if(firstLetterPosition%5 == secondLetterPostion%5) {
								
								if(firstLetterPosition/5 == 4) {
									firstLetterPosition=j%5;
									secondLetterPostion=j2+5;
								}
								else if(secondLetterPostion/5 == 4) {
									firstLetterPosition=j+5;
									secondLetterPostion=j2%5;
								}
								else {
									firstLetterPosition=j+5;
									secondLetterPostion=j2+5;
								}

							}
							
							else {
								
								if(firstLetterPosition/5<secondLetterPostion/5 && firstLetterPosition%5<secondLetterPostion%5) {
									firstLetterPosition=j+(j2-j)%5;
									secondLetterPostion=j2-(j2-j)%5;
								}
								else if(firstLetterPosition/5<secondLetterPostion/5 && firstLetterPosition%5>secondLetterPostion%5) {
									firstLetterPosition=j-(5-(j2-j)%5);
									secondLetterPostion=j2+(5-(j2-j)%5);
								}
								else if(firstLetterPosition/5>secondLetterPostion/5 && firstLetterPosition%5<secondLetterPostion%5) {
									firstLetterPosition=j+(5-(j-j2)%5);
									secondLetterPostion=j2-(5-(j-j2)%5);
								}
								else if(firstLetterPosition/5>secondLetterPostion/5 && firstLetterPosition%5>secondLetterPostion%5) {
									firstLetterPosition=j-(j-j2)%5;
									secondLetterPostion=j2+(j-j2)%5;
								}
								
							}
							
							
							System.out.println(firstLetterPosition+" "+secondLetterPostion);
						}
					}
				}
			}
			
			arrayWord.set(i, arrayKey.get(firstLetterPosition));
			arrayWord.set(i+1, arrayKey.get(secondLetterPostion));
			
		}
		
		
		
		
		
		
		word="";
		for (Character character : arrayWord) {
			word+=character;
		}
		
		return word;
		
	}
	
	public static String decrypting(String word, String key) {
		
		ArrayList<Character> arrayWord = new ArrayList<>();
		for (int i = 0; i < word.length(); i++) {
			arrayWord.add(word.charAt(i));
		}
		ArrayList<Character> arrayKey = new ArrayList<>();
		for (int i = 0; i < key.length(); i++) {
			arrayKey.add(key.charAt(i));
		}
		
		int firstLetterPosition = 0;
		int secondLetterPostion = 0;
		
		for (int i = 0; i < arrayWord.size(); i+=2) {
			for (int j = 0; j < arrayKey.size(); j++) {
				if(arrayWord.get(i) == arrayKey.get(j)) {
					for (int j2 = 0; j2 < arrayKey.size(); j2++) {
						if(arrayWord.get(i+1) == arrayKey.get(j2)) {
							
							firstLetterPosition=j;
							secondLetterPostion=j2;
							
							if(firstLetterPosition/5 == secondLetterPostion/5) {
								
								if(firstLetterPosition%5 == 0) {
									firstLetterPosition=j+4;
									secondLetterPostion=j2-1;
								}
								else if(secondLetterPostion%5 == 0) {
									firstLetterPosition=j-1;
									secondLetterPostion=j2+4;
								}
								else {
									firstLetterPosition--;
									secondLetterPostion--;
								}

							}
							
							else if(firstLetterPosition%5 == secondLetterPostion%5) {
								
								if(firstLetterPosition/5 == 0) {
									firstLetterPosition=j+20;
									secondLetterPostion=j2-5;
								}
								else if(secondLetterPostion/5 == 0) {
									firstLetterPosition=j-5;
									secondLetterPostion=j2+20;
								}
								else {
									firstLetterPosition=j-5;
									secondLetterPostion=j2-5;
								}

							}
							
							else {
								
								if(firstLetterPosition/5<secondLetterPostion/5 && firstLetterPosition%5<secondLetterPostion%5) {
									firstLetterPosition=j+(j2-j)%5;
									secondLetterPostion=j2-(j2-j)%5;
								}
								else if(firstLetterPosition/5<secondLetterPostion/5 && firstLetterPosition%5>secondLetterPostion%5) {
									firstLetterPosition=j-(5-(j2-j)%5);
									secondLetterPostion=j2+(5-(j2-j)%5);
								}
								else if(firstLetterPosition/5>secondLetterPostion/5 && firstLetterPosition%5<secondLetterPostion%5) {
									firstLetterPosition=j+(5-(j-j2)%5);
									secondLetterPostion=j2-(5-(j-j2)%5);
								}
								else if(firstLetterPosition/5>secondLetterPostion/5 && firstLetterPosition%5>secondLetterPostion%5) {
									firstLetterPosition=j-(j-j2)%5;
									secondLetterPostion=j2+(j-j2)%5;
								}
								
							}
							
							
							System.out.println(firstLetterPosition+" "+secondLetterPostion);
						}
					}
				}
			}
			
			arrayWord.set(i, arrayKey.get(firstLetterPosition));
			arrayWord.set(i+1, arrayKey.get(secondLetterPostion));
			
		}
		
		
		word="";
		for (Character character : arrayWord) {
			word+=character;
		}
		
		return word;
	}
	
}
