package test;

public class Province
{
    private String pro_name;//行政区名
    private int confirmedNum;//确诊人数
    public String getPro_Name()
    {
        return pro_name;
    }
    public void setPro_name(String name)
    {
        this.pro_name=name;
    }
    public int getConfirmedNum()
    {
        return confirmedNum;
    }
    public void setConfirmedNum(int confirmedNum)
    {
        this.confirmedNum=confirmedNum;
    }
    public String toString()
    {
        return pro_name+"确诊总人数为"+confirmedNum;
    }
    public Province(String name,int num)
    {
        pro_name=name;
        confirmedNum=num;
    }
}
