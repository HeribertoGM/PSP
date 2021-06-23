//.b=44

package proyecto;

public class Contador {
    
    private boolean multicommentStatus;
    private int countBlank;
    private int countComment;
    private int countCode;
    private int totalCount;
    
    //.i
    public Contador(){
        multicommentStatus = false;
        countBlank = 0;
        countComment = 0;
        countCode = 0;
        totalCount = 0;
    }
    
    //.i
    public boolean getMulticommentStatus(){
        return multicommentStatus;
    }
    
    //.i
    public int getCountBlank(){
        return countBlank;
    }
    
    //.i
    public int getCountComment(){
        return countComment;
    }
    
    //.i
    public int getCountCode(){
        return countCode;
    }
    
    //.i
    public int getTotalCount(){
        return totalCount;
    }
    
    //.i
    public void setCommentStatus(boolean status){
        multicommentStatus = status;
    }
    
    //.i
    public void countLine(int op){
        totalCount++;
        if(multicommentStatus){
            countComment++;
            //.d=1
            //System.out.println("add comment: "+countComment);
        }
        else{
            switch(op){
                //blank
                case 1: //.m
                    countBlank++;//.m
                    //.d=1
                    //System.out.println("add blank: "+countBlank);
                    break;
                //comment
                case 2://.m
                    countComment++;//.m
                    //.d=1
                    //System.out.println("add comment: "+countComment);
                    break;
                //code
                case 3://.m
                    countCode++;//.m
                    //.d=1
                    //System.out.println("add code: "+countCode);
                    break;
            }
        }
    }
}
