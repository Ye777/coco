package com.company;
import java.util.ArrayList;
import java.util.Calendar;

abstract class Ingredient{
    public String name;
    public Calendar date;
    public Calendar deadline;
    Ingredient(String name,int year,int month,int ri)
    {
        this.name=name;
        date=Calendar.getInstance();
        date.set(year,month,ri);
        deadline=Calendar.getInstance();
        deadline.set(year,month,ri);
    }
    String getName()
    {
        return name;
    }
}
class Bubble extends Ingredient{
    final int baozhiqi=7;
    Bubble(String name,int year,int month,int ri)
    {
        super(name,year,month,ri);
        deadline.add(deadline.DATE,baozhiqi);
    }
      public String toString ()
     {
         return "该珍珠的名字为"+name+",生产日期为"+date.getTime()+",保质期为7天。";
     }
 }
 class Cocount extends Ingredient{
    final int baozhiqi=5;
     Cocount(String name,int year,int month,int ri)
     {
         super(name,year,month,ri);
         deadline.add(deadline.DATE,baozhiqi);
     }
     public String toString ()
     {
         return "该椰果的名字"+name+",生产日期为"+date.getTime()+",保质期为5天。";
     }
 }
class MilkTea{
    String name;
    Ingredient a;
    MilkTea(String name1)
    {
        name=name1;
    }
    MilkTea() {}
    String getName()
    {
        return name;
    }
    public String toString()
    {
        if(a instanceof Bubble)
        {return "这杯奶茶的名字为"+name+",配料是珍珠,"+a.toString();}
        else
        {
            return "这杯奶茶的名字为"+name+",配料是椰果,"+a.toString();
        }
    }
}
 class Teashop implements Shop{
     public ArrayList<Bubble> Bubbles;
     public ArrayList<Cocount> Cocouts;
     Teashop()
     {
         Bubbles=new ArrayList<Bubble>();
         Cocouts=new ArrayList<Cocount>();
     }

     @Override
     public void jinhuo(Ingredient a) {
         if(a instanceof Bubble)
             add((Bubble) a);
         if(a instanceof Cocount)
             add((Cocount) a);
     }

     public void sale(String milkname,String inname,Calendar today) {
         MilkTea a=new MilkTea(milkname);
         int k=0,j=0;
         for(int i=0;i<Bubbles.size();i++)
         {
             if(today.after(Bubbles.get(i).deadline))
             {
                 Bubbles.remove(i);
                 i--;
             }

         }
         for(int i=0;i<Cocouts.size();i++)
         {
             if(today.after(Cocouts.get(i).deadline))
             {
                 Cocouts.remove(i);
                 i--;
             }

         }
         if(inname.equals("Bubble")) {

             try {
                 judge(inname);
                Bubbles.remove(Bubbles.size()-1);
                System.out.println("已售出");
             } catch (SoldOutException se) {
                 System.out.println("该种种类的配料已售完");
             }
         }




    }
    public  void judge(String inname) throws SoldOutException
    {
        if(inname.equals("Bubble"))
        {
            if(Bubbles.isEmpty())
                throw new SoldOutException();
        }
        if(inname.equals("Cocout"))
        {
            if(Cocouts.isEmpty())
                throw new SoldOutException();
        }

    }
     private void add(Bubble x)
     {
         Bubbles.add(x);
     }
     private void add(Cocount x) {
         Cocouts.add(x);
     }
 }
 class  SoldOutException extends Exception{}
 interface Shop{
    public void jinhuo(Ingredient a);
    public void sale(String milkname,String inname,Calendar today);
}
 class Main {

    public static void main(String[] args) {
	// write your code her
        Calendar Today=Calendar.getInstance();
        Teashop XIER=new Teashop();
        Bubble bubble1=new Bubble("珍珠1",2019,11,3);
        Bubble bubble2=new Bubble("珍珠2",2019,10,25);
        Cocount cocout1=new Cocount("椰果1",2019,11,27);
        Cocount cocout2=new Cocount("椰果2",2019,11,3);

        XIER.jinhuo(bubble1);XIER.jinhuo(bubble2);XIER.jinhuo(cocout1);XIER.jinhuo(cocout2);
        System.out.println(XIER.Bubbles.get(0).date.getTime());
        System.out.println(XIER.Bubbles.get(1).date.getTime());
        System.out.println(XIER.Bubbles.get(0).deadline.getTime());
        System.out.println(XIER.Bubbles.get(1).deadline.getTime());
        XIER.sale("奶茶1","Bubble",Today);
        XIER.sale("奶茶1","Bubble",Today);

    }
}
