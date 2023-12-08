package Юзабельные_классы;

public class Date {
    int year;
    int month;
    int date;

    public Date(int date, int month, int year) {
        this.year = year;
        this.month = month;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s.%s.%s", this.date, this.month, this.year);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }
}

