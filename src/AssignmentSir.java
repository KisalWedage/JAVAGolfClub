//Name : S.M.K.C.Wedage
//IIT ID : 2018368
//UoW ID : w1742101
import java.util.ArrayList;
import java.util.Scanner;

public class AssignmentSir{

    private static Scanner scan = new Scanner(System.in);   //declare a scanner option
    private static ArrayList<String> golferNameList=new ArrayList<>(); //declare an array list for golfers name
    private static ArrayList<Integer> golferScoreList=new ArrayList<>();   //declare a array list for golfer score
    private static ArrayList<String> golferNameListNew=new ArrayList<>();   //declare a array list for golfer score for cloning
    private static ArrayList<Integer> golferScoreListNew=new ArrayList<>();   //declare a array list for golfer score for cloning
    private static ArrayList<String> golferNameListRemoved=new ArrayList<>();
    private static ArrayList<Integer> golferScoreListRemoved=new ArrayList<>();
    private static int sizeN=0;
    private static int size=0;
    private static int golferScoreEdit=0;
    private static int beforeEditScore=0;
    private static int indexOfDup=0;
    private static int delScore=0;
    private static String delName;
    private static int subLoop;
    private static int subLoop2;

    public static void main(String[] args) {
        int option;
        System.out.println("------------------------------------");
        System.out.println("Welcome to Springfield Golf Club");
        do {
            System.out.println("------------------------------------");
            System.out.println("Select one of the below options");
            System.out.println("1. Enter scores");
            System.out.println("2. Find Golfer");
            System.out.println("3. Display Scoreboard");
            System.out.println("4. Exit Program");
            System.out.print(">");
            while(!scan.hasNextInt())   //check the validity
            {
                String wrongInput=scan.next();
                System.out.println(wrongInput+" is not a valid input");
                System.out.println("Enter a correct option");
            }
            option = scan.nextInt(); // take the user option as an integer
            switch(option){
                case 1:
                    enterScore();
                    break;
                case 2:
                    findGolfer();
                    break;
                case 3:
                    displayScoreboard();
                    break;
                case 4:
                    System.out.println("Thank you\nProgram Terminated");
                    break;
                default:
                    System.out.println("Unidentified option. Enter again");
            }
        }while(option!=4);  // loop until user input 4 as the option.
    }
/*
this is for the sub menu
 */
    private static void enterScore() {
        int option1;
        do {
            System.out.println("Enter your option");
            System.out.println("1. Enter player details");
            System.out.println("2. Edit data");
            System.out.println("3. Delete data");
            System.out.println("4. Restore data");
            System.out.println("5. Undo action");
            System.out.println("6. Redo action");
            System.out.println("7. Exit");
            System.out.print(">");
            while(!scan.hasNextInt())   //check the validity
            {
                String wrongInput=scan.next();
                System.out.println(wrongInput+" is not a valid input");
                System.out.println("Enter a correct option");
            }
            option1=scan.nextInt();
            switch(option1){
                case 1:
                    enterPlayerDetails();
                    subLoop=1;
                    break;
                case 2:
                    editData();
                    break;
                case 3:
                    deleteRecord();
                    break;
                case 4:
                    restoreRecord();
                    break;
                case 5:
                    undoAction();
                    subLoop=5;
                    break;
                case 6:
                    redoAction();
                    break;
                case 7:
                    System.out.println("Redirected to main menu");
                    break;
                default:
                    System.out.println("Unidentified option. Enter again");
            }
        }while(option1!=7);
    }

    /*
    this is used for enter player details
     */
    private static void enterPlayerDetails(){
        System.out.println("Enter the number of golfers");
        while(!scan.hasNextInt())   //check the validity
        {
            String wrongInput=scan.next();
            System.out.println(wrongInput+" is not a valid input");
            System.out.println("Enter a valid input");
        }
        size = scan.nextInt();
        sizeN=size+sizeN;
        for (int i = 0; i < size; i++) //a for loop to input the golfer details. this will loop until i equals to size-1
        {
            System.out.println("Enter the golfers name");
            String golferName = scan.next();
            if (golferNameList.contains(golferName))    //check if the array list has the same input once again
            {
                i--;     //therefor for this record is not valid. so i need to run loop again one more time.
                System.out.println("You have entered this name once\nDo you need to replace the score?Y/N");
                String replace = scan.next().toLowerCase();
                if (replace.equals("y"))    // if the user input y, which stand for update the existing score
                {
                    System.out.println("Enter the golfers score");
                    while(!scan.hasNextInt())   //check the validity
                    {
                        String wrongInput=scan.nextLine();
                        System.out.println(wrongInput+" is not a valid input");
                        System.out.println("Enter a valid score");
                    }
                    int golferScore = scan.nextInt();
                    if (golferScore <= 108 && golferScore >= 18)    //check the golferScore is in the range of 18-108
                    {
                        indexOfDup = golferNameList.indexOf(golferName);    //get the index of repeated name
                        golferScoreList.set(indexOfDup,golferScore);    //updating the score
                    }
                    else //if the score is not in range
                    {
                        System.out.println("Your value is not in range");
                    }
                }
                else if (replace.equals("n"))     //if user inputs n
                {
                    System.out.println("Your score will not be updated ");
                }
                else //if user inputs a non recognized input
                {
                    System.out.println("Not an expected input. Input again");
                }
            }
            else  //if the elements are not duplicated
            {
                System.out.println("Enter the golfers score");
                while(!scan.hasNextInt())   //check the validity
                {
                    String wrongInput=scan.next();
                    System.out.println(wrongInput+" is not a valid input");
                    System.out.println("Enter a valid score");
                }
                int golferScore = scan.nextInt();   //ask the score from the user
                if (golferScore <= 108 && golferScore >= 18)    //check the golferScore is in the range of 18-108
                {
                    golferNameList.add(golferName); //append the golfer name to array list
                    golferScoreList.add(golferScore);   //append the golfer score to the array list
                }
                else
                {    //if the score is not in range
                    System.out.println("Your value is not in range");
                    i--;    //im deducting one and give change to run the loop again, because the score is not in range
                }
            }
        }
    }

    /*
    to edit data
     */

    private static void editData(){
        displayScoreboard();    //display the scoreboard
        System.out.println("------------------------------------");
        System.out.println("Enter the name that you want to edit");
        String tempName=scan.next();
        if (golferNameList.contains(tempName)){
            subLoop=2;
            System.out.println("Enter the golfers score");   //asking for the new golfer score
            while(!scan.hasNextInt())   //check the validity
            {
                String wrongInput=scan.nextLine();
                System.out.println(wrongInput+" is not a valid input");
                System.out.println("Enter a valid score");
            }
            golferScoreEdit = scan.nextInt();    //get the new golfer score
            if (golferScoreEdit <= 108 && golferScoreEdit >= 18)    //check the golferScore is in the range of 18-108
            {
                int indexOfDup = golferNameList.indexOf(tempName);    //get the index of the name
                beforeEditScore=golferScoreList.get(indexOfDup);
                golferScoreList.set(indexOfDup,golferScoreEdit);    //updating the score
            }
            else //if the score is not in range
            {
                System.out.println("Your value is not in range");
            }
        }else{
            System.out.println("To edit your requirement your name have not entered once ");
        }
    }

    /*
    used for delete records
     */

    private static void deleteRecord(){
        for(int item:golferScoreList){  //clone the array list
            golferScoreListNew.add(item);
        }
        for(String item:golferNameList){    //clone the array
            golferNameListNew.add(item);
        }
        System.out.println("Enter the name that you want to delete");
        String delName=scan.next();
        if (golferNameList.contains(delName)){ //check weather the the list contains the name to delete
            subLoop=3;
            int index=golferNameList.indexOf(delName);
            delScore=golferScoreList.get(index);
            golferNameList.remove(index);
            golferScoreList.remove(index);
            sizeN--;
            System.out.println("Record deleted");
        }else{
            System.out.println("To delete your record your name doesn't exist");
        }
    }

    /*
    used to restore the deleted record
     */

    private static void restoreRecord(){
        System.out.println("Enter your deleted name to restore");
        String name=scan.next();
        if (golferNameListNew.contains(name)&&!golferNameList.contains(name)){  // to avoid repeat i used &&
            subLoop=4;
            int index=golferNameListNew.indexOf(name);
            golferNameList.add(name);
            int tempScore=golferScoreListNew.get(index);
            golferScoreList.add(tempScore);
            sizeN++;
            System.out.println(name+"'s score is restored");
        }else{
            System.out.println("To restore you have not deleted this name once ");
        }
    }

    /*
    to undo the previous
     */
    private static void undoAction(){
        switch(subLoop){
            case 1:
                if (!golferScoreList.isEmpty()) {
                    for (int i = sizeN - 1; i >= size-1; i--) {
                        String tempName = golferNameList.get(i);
                        int tempScore = golferScoreList.get(i);
                        golferNameListRemoved.add(tempName);
                        golferScoreListRemoved.add(tempScore);
                        golferNameList.remove(i);
                        golferScoreList.remove(i);
                    }
                    System.out.println("Previous action is undone");
                    subLoop2 = 1;
                    sizeN = sizeN - size;
                }else{
                    System.out.println("No records entered");
                }
                break;
            case 2:
                golferScoreList.set(indexOfDup,beforeEditScore);
                System.out.println("Previous action is undone");
                subLoop2=2;
                break;
            case 3:
                golferNameList.add(delName);
                golferScoreList.add(delScore);
                System.out.println("Previous action is undone");
                subLoop2=3;
                break;
            case 4:
                golferNameList.remove(sizeN-1);
                golferScoreList.remove(sizeN-1);
                sizeN--;
                System.out.println("Previous action is undone");
                subLoop2=4;
                break;
            default:
                System.out.println("To undo you have not done any action");
        }
    }

    /*
    to redo the action
     */
    private static void redoAction(){
            if (subLoop==5) {
                switch(subLoop2){
                    case 1:
                        for(int item:golferScoreListRemoved){
                            golferScoreList.add(item);
                        }
                        for(String item:golferNameListRemoved){
                            golferNameList.add(item);
                        }
                        System.out.println("Previous undo is redone");
                        sizeN=size;
                        break;
                    case 2:
                        golferScoreList.set(indexOfDup,golferScoreEdit);
                        System.out.println("Previous undo is redone");
                        break;
                    case 3:
                        golferNameList.remove(sizeN-1);
                        golferScoreList.remove(sizeN-1);
                        System.out.println("Previous undo is redone");
                        break;
                    case 4:
                        golferNameList.add(delName);
                        golferScoreList.add(delScore);
                        System.out.println("Previous undo is redone");
                        break;
                }
            }else{
                System.out.println("To redo action you have not undone an action");
            }
        }


    /*
    used to find the golfer
     */

    private static void findGolfer() {
        String name="";
        while(!name.equals("e"))
        {
            System.out.println("Enter the name of the Golfer : ");
            name = scan.next();  //taking the name from the user
            if (golferNameList.contains(name))  //check if the name is in the array
            {
                int indexName = golferNameList.indexOf(name);   // getting the index of the name
                int score = golferScoreList.get(indexName);    //because i append the score , im taking the relevant score inorder with the index name
                System.out.println("Golfer score : " + score);
                name="e";
            }
            else if(name.equals("e"))//if the name doesn't exist
            {
                System.out.println("Thank you");
            }
            else
            {
                System.out.println("The name is not recognized\nIf you need to exit enter e\n");
            }
        }
    }

    /*
    used to display scoreboard
     */

    private static void displayScoreboard() {
        System.out.println("------------------------------------");
        System.out.println("Golfer Name\t\t\t\tGolfer Score");
        System.out.println("------------------------------------");
        //bubble sort
        if (!golferScoreList.isEmpty()) {
            for (int i = 0; i < golferScoreList.size() - 1; i++) {
                for (int j = 0; j < golferScoreList.size() - 1; j++) {
                    if (golferScoreList.get(j) > golferScoreList.get(j + 1)) {
                        int tempScore = golferScoreList.get(j); //Take jth element of golferScoreList as tempScore
                        String tempName = golferNameList.get(j);
                        golferScoreList.set(j, golferScoreList.get(j + 1));   //Update the jth  element of golferScoreList which were in j+1th element
                        golferNameList.set(j, golferNameList.get(j + 1));
                        golferScoreList.set(j + 1, tempScore);   //Update j+1th element to tempScore in golferScoreArray
                        golferNameList.set(j + 1, tempName);
                    }
                }
            }
            for (int i = 0; i < sizeN; i++)    //loop to print the scoreboard
            {
                System.out.printf("%-16s%-24s\n", golferNameList.get(i), "\t\t\t" + golferScoreList.get(i));
            }
        }else{
            System.out.println("No records");
        }
    }
}
/*
for bubble sorting i used this web site https://www.geeksforgeeks.org/java-program-for-bubble-sort/
to align colomns in scoreboard i refered https://stackoverflow.com/questions/39312589/aligning-columns
 */