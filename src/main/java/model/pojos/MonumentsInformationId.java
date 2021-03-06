package model.pojos;
// Generated 31-May-2016 22:35:14 by Hibernate Tools 4.3.1



/**
 * MonumentsInformationId generated by hbm2java
 */
public class MonumentsInformationId  implements java.io.Serializable {


     private int monumentsId;
     private String pieceName;

    public MonumentsInformationId() {
    }

    public MonumentsInformationId(int monumentsId, String pieceName) {
       this.monumentsId = monumentsId;
       this.pieceName = pieceName;
    }
   
    public int getMonumentsId() {
        return this.monumentsId;
    }
    
    public void setMonumentsId(int monumentsId) {
        this.monumentsId = monumentsId;
    }
    public String getPieceName() {
        return this.pieceName;
    }
    
    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof MonumentsInformationId) ) return false;
		 MonumentsInformationId castOther = ( MonumentsInformationId ) other; 
         
		 return (this.getMonumentsId()==castOther.getMonumentsId())
 && ( (this.getPieceName()==castOther.getPieceName()) || ( this.getPieceName()!=null && castOther.getPieceName()!=null && this.getPieceName().equals(castOther.getPieceName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getMonumentsId();
         result = 37 * result + ( getPieceName() == null ? 0 : this.getPieceName().hashCode() );
         return result;
   }   


}


