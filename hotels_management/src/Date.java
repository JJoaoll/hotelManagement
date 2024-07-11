//import testeImport.Meses; FOI BEM SUCEDIDO!!!

public class Date {
    private byte day;
    private byte month;
    private short year; // por enquanto sem anos bissextos
    //private Meses m = new Meses();
    /*public Date(byte newDay, byte newMonth, short newYear){
        day = newDay;
        month = newMonth;
        year = newYear;

    }*/

    public Date(byte newDay, byte newMonth, short newYear){
        day = newDay;
        month = newMonth;
        year = newYear;
        this.fixDate();

    }

    public byte getDay() {
        return day;
    }

//    public void teste(){
//        Meses m = new Meses();
//        System.out.println(m);
//    }
    public byte getMonth() {
        return month;
    }

    public short getYear() {
        return year;
    }

    public void setDay(byte new_day){
        this.day = new_day;
        this.fixDate();
    }

    public void setMonth(byte new_month){
        this.month = new_month;
        this.fixDate();
    }

    public void setYear(short new_year){
        this.year = new_year;

    }

    public boolean isBeforeThan(Date otherDate){
        boolean b = true;
        if(year > otherDate.getYear())
            b = false;
        else if(year == otherDate.getYear()){
            if(month > otherDate.getMonth())
                b = false;
            else if(month == otherDate.getMonth())
                if(day >= otherDate.getDay())
                    b = false;
        }
        return b;
    }

    public Date tomorrow(){
        day++;
        this.fixDate();
        return this;
    }

    public void fixDate(){
        // por enquanto todos os meses com 30 dias e ainda 100 Anos bissextos
        while(day > 30){
            day-= 30;
            month++;
        }

        while(month > 12){
            month-= 12;
            year++;
        }
    }




}
