import java.util.Scanner;
class task2 {
    
    public static void main(String []args){
        System.out.println("<--Grade Calculator-->");
        System.out.println("It will calculate your total marks,percentageand grade");

        Scanner sc=new Scanner(System.in);
        System.out.print("How Many Sunject Do you have ? ");
        int subject=sc.nextInt();
        int []marks=new int[subject];
        float sum=0;
        System.out.println("\nEnter Marks of All Subject out of 100");
        for(int i=1;i<=subject;i++){
            System.out.print("\n Marks of Subject "+i+" :- ");
            marks[i-1]=sc.nextInt();
            sum+=marks[i-1];
        }
        System.out.println("\n<--Final Data-->");
        System.out.println("Total Marks : "+sum);
        float percentage=(sum/subject);
        System.out.println("Percentage : "+percentage);
        String grade=(percentage>=90 && percentage<=100)?"A":(percentage>=70)?"B":(percentage>=50)?"C":(percentage>=33)?"D":"Fail";
        System.out.println("Grade : "+grade);
        sc.close();
    }
}
