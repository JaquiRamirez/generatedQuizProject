package com.mycompany.finalproject;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.*;
import javax.swing.JOptionPane;
/**
 * @author jaquelineramirez
 */
public class FinalProject {

    public static void main(String[] args)throws FileNotFoundException, IOException {
      Scanner keyboard = new Scanner(System.in);
      Random random = new Random();
      
      //Accessing the Userinfo file
      File myInfo = new File("//Users//jaquelineramirez//Documents//UsersInfo.txt");
      Scanner inputInfo = new Scanner(myInfo);
      
      String[] sameLineArr = new String[30];
  
      int rowUserPosition = 0;
      int attempts = 0;
      boolean found = false;
      
      //Putting the UserInfo information into an array
      for(int i=0; inputInfo.hasNext(); i++){
            String line;
            line = inputInfo.nextLine();
            sameLineArr[i] = line; //store the whole line in the sameLineArr
      }
      inputInfo.close();
      
      while(attempts < 3){
        String username = JOptionPane.showInputDialog("Username: ");
        String password = JOptionPane.showInputDialog("Password: "); 
        int rowPosition = -1; //To determine which row was the username found
      //for loop to find if the user exits in the infoUser 
        for(int i=0; i < sameLineArr.length; i++){ 
            String[] sameColumn = sameLineArr[i].split("\t"); //splits the columns
            rowPosition = rowPosition + 1;//adds one everytime we skip a row where the user is not found     
                if(sameColumn[2].equals(username)){//check if the username is found
                    String[] userInfoArr = sameLineArr[rowPosition].split("\t"); //splits the users info into an arr
                    String correctPass = userInfoArr[3];//We put the "correct" password that we find in the file and save it as a string
                    if(correctPass.equals(password)){//We check if what the user put is the same as the pass from the file
                        JOptionPane.showMessageDialog(null,"Logged in","Successful",JOptionPane.INFORMATION_MESSAGE);
                        found = true; 
                        rowUserPosition = rowPosition; 
                        break; // break out the for statement
                       }
                 
                    }
          }
        if(found){ //if found
           break; //then we break out the while loop
            }
        
        JOptionPane.showMessageDialog(null,"Username or Password Incorrect","Fail",JOptionPane.INFORMATION_MESSAGE);
        attempts = attempts + 1; //if user or pass not found then we add one attempt
        
      }
      
     if(attempts == 3){ //when attempts equal 3, we exit the program
         JOptionPane.showMessageDialog(null,"Login Fail. Try again in 30 minutes","Fail",JOptionPane.INFORMATION_MESSAGE);
         System.exit(0);
        
     }
       
      
      //Reading out the question file
      File myQuestions = new File("//Users//jaquelineramirez//Documents//TestBank.txt");
      Scanner inputQuestions = new Scanner(myQuestions);
      ArrayList<String> questionsArray = new ArrayList<String>();
      //Putting the questions into an array
      while(inputQuestions.hasNext()){
          String line = inputQuestions.nextLine();
          questionsArray.add(line);
         }
      
      inputQuestions.close();
    
      //Reading out the answer file
      File myAnswers = new File("//Users//jaquelineramirez//Documents//Answers.txt");
      Scanner inputAnswers = new Scanner(myAnswers);
      ArrayList<String> answersArray = new ArrayList<String>();
      //Putting the questions into an arrayList
      while(inputAnswers.hasNext()){
           String line = inputAnswers.nextLine();
           answersArray.add(line);
         }
      inputAnswers.close();       
     
      int correct = 0;
      int bound = 125;
      //Printing out 10 questions
      for(int i = 1; i < 11; i++){
               int randomNum = random.nextInt(bound);
               
               //Printing the question
               System.out.println(i + ") " + questionsArray.get(randomNum));
               
               //Getting the correct answer
               String answer1 = answersArray.get(randomNum);
               String answer1Low = answer1.toLowerCase();
               char answer1First = answer1Low.charAt(0);           
               
               //Getting the user's answer
               System.out.print("Answer: ");
               String answer2 = keyboard.nextLine();
               String answer2Lower = answer2.toLowerCase();
              
               //Only True or False answers
               while(!answer2Lower.equals("true") && !answer2Lower.equals("false") &&
                       !answer2Lower.equals("t") && !answer2Lower.equals("f")){
                    System.out.println("Only True or False (T or F)");
                    System.out.print("Answer: ");
                    answer2 = keyboard.nextLine();
                    answer2Lower = answer2.toLowerCase();                 
                    }
               
               
               char answer2First = answer2Lower.charAt(0);
               
               if(answer1First == answer2First){
                   correct = 1 + correct;
                    }
               
               
               //Remove the question so it doesn't repeat
               questionsArray.remove(randomNum);
               answersArray.remove(randomNum);
               bound = bound - 1;
               System.out.println("---------------------------------");
          
                }
        double total = 10;
        double grade = (correct / total) * 100;
      
        String[] userInfoArr = sameLineArr[rowUserPosition].split("\t"); //splits the users info into an arr
        String firstName = userInfoArr[0];//first name
        String lastName = userInfoArr[1];//last name
        String userName = userInfoArr[2];//username
        
        System.out.println("Result");
        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("Score: " + grade);
        System.out.println("File Name: " + userName + "_CSCI_2493_Quiz");
       
        PrintWriter outputFile = new PrintWriter ("//Users//jaquelineramirez//Documents//" + userName + "_CSCI_2493_Quiz");
        outputFile.println("Test Results");
        outputFile.println("First name: " + firstName);
        outputFile.println("Last name: " + lastName);
        outputFile.println("Score: " + grade);
        outputFile.close();

        
        
        
      
    }

}
